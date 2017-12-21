package com.mifan.quiz.service.impl;

import com.mifan.quiz.dao.QuizCountDao;
import com.mifan.quiz.domain.Answers;
import com.mifan.quiz.domain.Options;
import com.mifan.quiz.domain.Questions;
import com.mifan.quiz.domain.QuizCount;
import com.mifan.quiz.service.AnswersService;
import com.mifan.quiz.service.BaseServiceAdapter;
import com.mifan.quiz.service.QuestionsService;
import com.mifan.quiz.service.QuizCountService;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.moonframework.model.mybatis.criterion.Restrictions;
import org.moonframework.model.mybatis.service.Services;
import org.springframework.beans.factory.annotation.Autowired;
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
	public Questions optionsCount(Long optCountquesId) {
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
