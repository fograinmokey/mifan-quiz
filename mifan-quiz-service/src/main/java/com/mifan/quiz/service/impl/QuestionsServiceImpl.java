package com.mifan.quiz.service.impl;

import com.mifan.quiz.dao.QuestionsDao;
import com.mifan.quiz.domain.Answers;
import com.mifan.quiz.domain.Options;
import com.mifan.quiz.domain.Questions;
import com.mifan.quiz.domain.QuizSession;
import com.mifan.quiz.service.BaseServiceAdapter;
import com.mifan.quiz.service.OptionsService;
import com.mifan.quiz.service.QuestionsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    
    /*
     * 当有人答题时    分页查询 试卷的试题   
     *  (non-Javadoc)
     * @see com.mifan.quiz.service.QuestionsService#findAlll(java.lang.Long, int, int, java.lang.String)
     */
    @Override
    public Page<Questions> findAlll(Long quizId,int page,String sessionCode){
        PageRequest pageRequest =  Pages.builder().page(page).size(1).sort(Pages.sortBuilder().add(Questions.DISPLAY_ORDER,true).build()).build();
        Criterion criterion = Restrictions.and(Restrictions.eq(Questions.QUIZ_ID, quizId),Restrictions.eq(Questions.ENABLED, 1));
        Page<Questions> pages = super.findAll(criterion, pageRequest);
        QuizSession find = new QuizSession();
        find.setSessionCode(sessionCode);
        find.setQuizId(quizId);
        QuizSession quizSession = Services.findOne(QuizSession.class, find);
        if (quizSession == null) {
        	throw new IllegalStateException("没有该会话！请重新开始问卷");
		}
        if(pages.hasContent()) {
            List<Questions> questions = pages.getContent();
            for (Questions question : questions) {
                List<Long> correctOptions = new ArrayList<>();//必须放在循环内部，否则每个题目的正确选项都是一样的，bug了
            	Answers answers = Services.findOne(Answers.class, Restrictions.and(Restrictions.eq(Answers.SESSION_ID, quizSession.getId()),
            			Restrictions.eq(Answers.QUESTION_ID, question.getId())));
            	if (answers != null) {
					//如果用户提交的答案不为null 则 说明这道题用户做过回答  则返回其提交的答案
            		question.setAnswers(answers);
            		List<Options> optionList = Services.findAll(Options.class, Restrictions.and(Restrictions.eq(Options.QUESTION_ID, 
            				question.getId()), Restrictions.eq(Options.IS_CORRECT, 1)));
            		for (Options options : optionList) {
            		    correctOptions.add(options.getId());
            		}
            		answers.setCorrectOptions(correctOptions);
				}
			}
            Long[] questionIds = questions.stream().map(Questions::getId).collect(Collectors.toList()).toArray(new Long[questions.size()]);
            List<Options> options = Services.findAll(Options.class, Restrictions.in(Options.QUESTION_ID, questionIds));
            Map<Long,List<Options>> map = options.stream().collect(Collectors.groupingBy(Options::getQuestionId));
            questions.stream().forEach(q -> q.setOptions(map.get(q.getId())));
        }
        
        return pages;
    }


}
