package com.mifan.quiz.service.impl;

import com.google.common.base.Joiner;
import com.mifan.quiz.dao.AnswersDao;
import com.mifan.quiz.domain.Answers;
import com.mifan.quiz.domain.Options;
import com.mifan.quiz.domain.Questions;
import com.mifan.quiz.domain.QuizSession;
import com.mifan.quiz.domain.Quizs;
import com.mifan.quiz.service.AnswersService;
import com.mifan.quiz.service.BaseServiceAdapter;

import java.util.ArrayList;
import java.util.List;

import org.moonframework.model.mybatis.criterion.Criterion;
import org.moonframework.model.mybatis.criterion.Restrictions;
import org.moonframework.model.mybatis.domain.Fields;
import org.moonframework.model.mybatis.service.Services;
import org.springframework.stereotype.Service;

/**
 * @author ZYW
 * @version 1.0
 * @since 2017-12-12
 */
@Service
public class AnswersServiceImpl extends BaseServiceAdapter<Answers, AnswersDao> implements AnswersService {
	
	@Override
	public int save(Answers entity) {
	    Questions question = Services.findOne(Questions.class, entity.getQuestionId(),Fields.builder().add(Questions.QUIZ_ID).build());
		//查询会话
	    Criterion criterion = Restrictions.and(Restrictions.eq(QuizSession.SESSION_CODE, entity.getSessionCode()), 
    			              Restrictions.eq(QuizSession.ENABLED, 1),
    			              Restrictions.eq(QuizSession.QUIZ_ID, question.getQuizId()));
    	QuizSession quizSession = Services.findOne(QuizSession.class, criterion);
    	if (quizSession == null) {
    	    throw new IllegalStateException("随机码不存在！该会话不存在");
		}
		//查询正确的选项  考虑到多选题  则查出来就是List集合
		List<Long> correctOptions = new ArrayList<>();
		List<Options> optionList = Services.findAll(Options.class, Restrictions.and(Restrictions.eq(Options.QUESTION_ID, 
				                    entity.getQuestionId()), Restrictions.eq(Options.IS_CORRECT, 1)));
		for (Options options : optionList) {
		    correctOptions.add(options.getId());
		}
		
		List<Long> answersList = entity.getAnswersList();
		//与正确选项对比  返回是否正确
		int isRight = 1 ;
		if (correctOptions.size() == answersList.size()) {
			isRight = correctOptions.containsAll(answersList) ? 1 : 0 ;
		}else {
			isRight = 0 ;
		}
		// 查询问卷  
		Quizs quizs = Services.findOne(Quizs.class, quizSession.getQuizId());
		//答对题数
		if (isRight == 1) {
			quizSession.setRightNum(quizSession.getRightNum()+1);
		}
		//答题总数
		quizSession.setAnswerNum(quizSession.getAnswerNum()+1);
		
		//答完之后修改 是否全部打完
    	if (quizSession.getAnswerNum().equals(quizs.getQuestionNum())) {
    		quizSession.setAllDone(1);
		}
    	
    	//保存答案表
    	String answers = Joiner.on(",").join(answersList);  
    	entity.setCorrectOptions(correctOptions);
    	entity.setAnswers(answers);
    	entity.setAllDone(quizSession.getAllDone());
    	entity.setIsRight(isRight);
    	entity.setSessionId(quizSession.getId());
    	Services.update(QuizSession.class, quizSession);
    	int n = super.save(entity);
		return n ;
	}

	
}
