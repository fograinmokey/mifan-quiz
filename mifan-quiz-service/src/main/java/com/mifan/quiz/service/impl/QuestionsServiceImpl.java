package com.mifan.quiz.service.impl;

import com.mifan.quiz.dao.QuestionsDao;
import com.mifan.quiz.domain.Answers;
import com.mifan.quiz.domain.Options;
import com.mifan.quiz.domain.Questions;
import com.mifan.quiz.service.AnswersService;
import com.mifan.quiz.service.BaseServiceAdapter;
import com.mifan.quiz.service.OptionsService;
import com.mifan.quiz.service.QuestionsService;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.moonframework.model.mybatis.criterion.Criterion;
import org.moonframework.model.mybatis.criterion.Restrictions;
import org.moonframework.model.mybatis.domain.Fields;
import org.moonframework.model.mybatis.domain.Pages;
import org.moonframework.model.mybatis.service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author ZYW
 * @version 1.0
 * @since 2017-12-12
 */
@Service
public class QuestionsServiceImpl extends BaseServiceAdapter<Questions, QuestionsDao> implements QuestionsService {
    @Autowired
    private OptionsService optionsService;
	@Autowired
	private  AnswersService answersService;
	@Autowired
	private  QuestionsService  questionsService;
	
    @Override
    public Page<Questions> findAll(Long quizId,int page,int size){
        PageRequest pageRequest =  Pages.builder().page(page).size(size).sort(Pages.sortBuilder().add(Questions.DISPLAY_ORDER,true).build()).build();
        Criterion criterion = Restrictions.and(Restrictions.eq(Questions.QUIZ_ID, quizId),Restrictions.eq(Questions.ENABLED, 1));
        Page<Questions> pages = super.findAll(criterion, pageRequest);
        
        if(pages.hasContent()) {
            List<Questions> questions = pages.getContent();
            Long[] questionIds = questions.stream().map(Questions::getId).collect(Collectors.toList()).toArray(new Long[questions.size()]);
            List<Options> options = Services.findAll(Options.class, Restrictions.in(Options.QUESTION_ID, questionIds));
            Map<Long,List<Options>> map = options.stream().collect(Collectors.groupingBy(Options::getQuestionId));
            questions.stream().forEach(q -> q.setOptions(map.get(q.getId())));
        }
        
        return pages;
    }
    
    @Override
    public int saveQuestions(List<Questions> entities) {
        List<Questions> updates = entities.stream().filter(q -> q.getId() != null).collect(Collectors.toList());
        List<Long> updateIds = updates.stream().map(q -> q.getId()).collect(Collectors.toList());
        super.update(updates);
        
        List<Questions> olds = super.findAll(Restrictions.and(Restrictions.eq(Questions.QUIZ_ID, entities.get(0).getQuizId()),
                                    Restrictions.eq(Questions.ENABLED, 1)), Fields.builder().add(Questions.ID).build());
        if(CollectionUtils.isNotEmpty(olds)) {
            List<Long> oldIds = olds.stream().map(q -> q.getId()).collect(Collectors.toList());
            oldIds.removeAll(updateIds);
            if(CollectionUtils.isNotEmpty(oldIds)) {
                for(Long questionId : oldIds) {
                    List<Long> optionIds = Services.findAll(Options.class,
                                            Restrictions.eq(Options.QUESTION_ID, questionId), Fields.builder().add(Options.ID).build()).
                                            stream().map(Options::getId).collect(Collectors.toList());
                    Services.delete(Options.class, optionIds);
                }
                super.delete(oldIds);
            }
        }
        
        List<Questions> adds = entities.stream().filter(q -> q.getId() == null).collect(Collectors.toList());
        super.save(adds);
        
        List<Options> options = new ArrayList<Options>();
        for(Questions q : entities) {
            int rightCount = q.getOptions().stream().map(o -> o.getIsCorrect()).reduce((s,c) -> s + c).get();
            if(rightCount < 1)
                throw new IllegalStateException("题目必须有一个正确选项");
            if(q.getType() == 1 && rightCount > 1)//类型为单选题时，正确答案只能有一个
                throw new IllegalStateException("单选题不应该有两个正确答案！");
            for(Options o : q.getOptions()) {
                o.setQuestionId(q.getId());
                o.setCreator(q.getCreator());
                o.setModifier(q.getModifier());
                options.add(o);
            }
        }
        optionsService.saveOptions(options);
        return 1;
    }

    /**
     * 统计每道题答对率
     */
	@Override
	public Questions quesanswersRate(Long questionId) {
		Questions question = questionsService.findOne(Restrictions.eq(Questions.ID, questionId));
		if(question == null){
			throw new IllegalStateException("该题不存在！");
		}
		//统计总共被答次数
		long allNum = answersService.count(Restrictions.eq(Answers.QUESTION_ID, questionId));
		//统计答对次数
		long rightNum = answersService.count(Restrictions.and(Restrictions.eq(Answers.QUESTION_ID, questionId),Restrictions.eq(Answers.IS_RIGHT, 1)));
		question.setAllNum(allNum);
		question.setRightNum(rightNum);
		float num= (float)rightNum/allNum;   
		DecimalFormat df = new DecimalFormat("0.00");//格式化小数   
		String ratio = df.format(num);
		question.setRatio(ratio);

		Map<String, Object> result = new HashMap<>();
		result.put("id", question.getId());
		result.put("questionTitle", question.getQuestionTitle());
		result.put("rightNum", rightNum);
		result.put("allNum", allNum);
		result.put("ratio", ratio);
		return question;
		
	}
    /**
     *  统计每道题的每个选项的被选中次数
     */
	@Override
	public Questions optionsCount(Long questionId) {
		Questions questions = questionsService.findOne(Restrictions.eq(Questions.ID, questionId));
		if(questions == null){
			throw new IllegalStateException("该题不存在！");
	}
		List<String> answersstr = new ArrayList<>();
		List<Answers> answers = answersService.findAll(Restrictions.eq(Answers.QUESTION_ID, questionId));
		for (Answers answerslist : answers) {
			String answer = answerslist.getAnswers();
			List<String> result = Arrays.asList(answer.split(",")); 
			answersstr.addAll(result);
		}
			
			List<Long> answersstrlong = new ArrayList<>();
			for (String result : answersstr) {
				answersstrlong.add(Long.parseLong(result));
			}
			List<Options> options = new ArrayList<>();
				questions.setOptions(options);
			Options option  ;
		    Map<String, Integer> map = new HashMap<>();  
		    for (String str : answersstr) {  
		       Integer num = map.get(str);    
		       map.put(str, num == null ? 1 : num + 1);  
		    }  
		    Set set = map.entrySet();  
		    Iterator it = set.iterator();
		    Iterator itmap = map.keySet().iterator();
		    while (itmap.hasNext()) {  
		        String key =  (String) itmap.next();
		        Integer answerCoun = map.get(key);
		        Long keyl = Long.parseLong(key);
		        option = Services.findOne(Options.class, keyl);
		        option.setAnswerCount(answerCoun);
		        options.add(option);
		    }
			return questions; 
			}


}
