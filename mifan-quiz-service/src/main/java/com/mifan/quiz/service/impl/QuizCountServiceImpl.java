package com.mifan.quiz.service.impl;

import com.mifan.quiz.dao.QuizCountDao;
import com.mifan.quiz.domain.Answers;
import com.mifan.quiz.domain.Options;
import com.mifan.quiz.domain.Questions;
import com.mifan.quiz.domain.QuizCount;
import com.mifan.quiz.domain.QuizSession;
import com.mifan.quiz.service.AnswersService;
import com.mifan.quiz.service.BaseServiceAdapter;
import com.mifan.quiz.service.QuestionsService;
import com.mifan.quiz.service.QuizCountService;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.moonframework.model.mybatis.criterion.Criterion;
import org.moonframework.model.mybatis.criterion.Restrictions;
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
public class QuizCountServiceImpl extends BaseServiceAdapter<QuizCount, QuizCountDao> implements QuizCountService {
	
	@Autowired
	private  AnswersService answersService;
	@Autowired
	private  QuestionsService  questionsService;
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
		return question;
		
	}
    /**
     *  统计每道题的每个选项的被选中次数
     */
	@Override
	public Questions optionsCount(Long questionId) {//优化后代码，请参考
	    Questions questions = questionsService.findOne(Restrictions.eq(Questions.ID, questionId));
        if(questions == null){
            throw new IllegalStateException("该题不存在！");
        }
	    List<Answers> answers = answersService.findAll(Restrictions.eq(Answers.QUESTION_ID, questionId));
	    List<Long> answerAllIds = new ArrayList<Long>();
	    for(Answers answer : answers) {
	      //把字符串转换为集合，同时将字符串类型转为Long
	        List<Long> answerIds = Arrays.asList(answer.getAnswers().split(",")).stream().map(Long::parseLong).collect(Collectors.toList());
	        answerAllIds.addAll(answerIds);
	    }
	    List<Options> options = Services.findAll(Options.class, Restrictions.eq(Options.QUESTION_ID, questionId));//查询该题所有的选项
	    Map<Long,Options> map = options.stream().collect(Collectors.toMap(Options::getId, o -> o));//将选项放入map，id为key，选项为value，因为只有这样才能保证我们统计的选项是全面的（及时它的统计数为0，也应该展示）
	    for(Long optionId : answerAllIds) {//遍历所有答案，统计个数                                //同时也能保证我们统计出来的选项都是我们数据库中已经存在的
	        if(map.containsKey(optionId)) {
	            Options option = map.get(optionId);
	            int answerCount = option.getAnswerCount();
	            option.setAnswerCount(answerCount + 1);
	        }
	    }
	    questions.setOptions(options);
        return questions;
	}
/*	public Questions optionsCount(Long optCountquesId) {
		Questions questions = questionsService.findOne(Restrictions.eq(Questions.ID, optCountquesId));
		if(questions == null){
			throw new IllegalStateException("该题不存在！");
	}
		List<String> answersstr = new ArrayList<>();
		List<Answers> answers = answersService.findAll(Restrictions.eq(Answers.QUESTION_ID, optCountquesId));
		for (Answers answerslist : answers) {
			String answer = answerslist.getAnswers();
			List<String> result = Arrays.asList(answer.split(",")); 
			answersstr.addAll(result);
		}
			List<Options> options = new ArrayList<>();
				questions.setOptions(options);
			Options option  ;
		    Map<String, Integer> map = new HashMap<>();  
		    for (String str : answersstr) {  
		       Integer num = map.get(str);    
		       map.put(str, num == null ? 1 : num + 1);  
		    }  
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
			}*/
	
	
	/**
	 * 分页展示问卷答案
	 * @param quizId
	 * @param page
	 * @param size
	 * @return
	 */
	@Override
	public Page<QuizSession> findAllQuizs(Long quizId, int page, int size) {
        PageRequest pageRequest =  Pages.builder().page(page).size(size).build();
        Criterion criterion = Restrictions.eq(QuizSession.QUIZ_ID, quizId);
        Page<QuizSession> pages = Services.findAll(QuizSession.class, criterion, pageRequest);
        if(pages.hasContent()) {
            List<QuizSession> quizSession = pages.getContent();
            //通过QuizSession取session_id
            Long[] sessionIds = quizSession.stream().map(QuizSession::getId).collect(Collectors.toList()).toArray(new Long[quizSession.size()]);
            //用sessionIds取出answers
            List<Answers> answers = Services.findAll(Answers.class, Restrictions.in(Answers.SESSION_ID, sessionIds));
            //用answers取出questionids
            Long[] questionids = answers.stream().map(Answers::getQuestionId).collect(Collectors.toList()).toArray(new Long[answers.size()]);
            //用questionids查找Questions
            List<Questions> questions = Services.findAll(Questions.class, Restrictions.in(Questions.ID, questionids));	
            Map<Long,List<Questions>> map2 = questions.stream().collect(Collectors.groupingBy(Questions::getId));
            answers.stream().forEach(q -> q.setQuestions(map2.get(q.getQuestionId())));
            Map<Long,List<Answers>> map = answers.stream().collect(Collectors.groupingBy(Answers::getSessionId));
            quizSession.stream().forEach(q -> q.setAnswers(map.get(q.getId())));
        }
        
        return pages;
    }
	
	}



