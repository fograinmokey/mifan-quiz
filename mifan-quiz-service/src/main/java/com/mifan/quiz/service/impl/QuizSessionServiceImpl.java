package com.mifan.quiz.service.impl;

import com.mifan.quiz.dao.QuizSessionDao;
import com.mifan.quiz.domain.QuizCount;
import com.mifan.quiz.domain.QuizSession;
import com.mifan.quiz.domain.Quizs;
import com.mifan.quiz.service.BaseServiceAdapter;
import com.mifan.quiz.service.QuizSessionService;

import java.text.DecimalFormat;
import java.util.UUID;

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
public class QuizSessionServiceImpl extends BaseServiceAdapter<QuizSession, QuizSessionDao> implements QuizSessionService {
    
    @Override
    public int save(QuizSession entity) {
        Quizs quiz = Services.findOne(Quizs.class, entity.getQuizId(),Fields.builder().add(Quizs.STATE).build());
        if(quiz == null || quiz.getState() != 1)
            throw new IllegalStateException("问卷不存在！");
        
        String sessionCode = UUID.randomUUID().toString();
        entity.setSessionCode(sessionCode);
        // 先存如统计表  为后面查询结果做准备
        QuizCount count ;
        Long quizId = entity.getQuizId();
        if (!Services.exists(QuizCount.class, quizId)) {
        	count = new QuizCount();
			count.setQuizId(quizId);
			count.setPeoples(1);
			count.setEnabled(1);
			Services.save(QuizCount.class, count);
		}else {
			 count = Services.findOne(QuizCount.class, quizId);
			 count.setPeoples(count.getPeoples()+1);
			 Services.update(QuizCount.class, count);
		}
        
        return super.save(entity);
    }

	@Override
	public QuizSession getResult(String sessionCode) {
		//查询当前会话
		QuizSession quizSession = Services.findOne(QuizSession.class, Restrictions.eq(QuizSession.SESSION_CODE, sessionCode));
		Integer answerNum = quizSession.getAnswerNum();
		Integer rightNum = quizSession.getRightNum();
		QuizCount count =  Services.findOne(QuizCount.class,Restrictions.eq(QuizCount.QUIZ_ID, quizSession.getQuizId()));
		if (count == null) {
			throw new IllegalStateException("没有统计数据，请重新答题！");
		}
		//转化小数
		float ratio  =   (float)rightNum/answerNum ;
	    DecimalFormat df = new DecimalFormat("0.00");//格式化小数   
	    String scorel = df.format(ratio);
		float score = Float.parseFloat(scorel);
		 
		      if (score>=0 && score<0.1) {
		      count.setFirst(count.getFirst()+1);
		}else if (score>=0.1 && score <=0.19) {
			  count.setSecond(count.getSecond()+1);
		}else if(score>=0.20 && score <=0.29) {
			  count.setThird(count.getThird()+1);
		}else if (score>=0.30 && score <=0.39) {
			  count.setFourth(count.getFourth()+1);
		}else if (score>=0.40 && score <=0.49) {
			  count.setFifth(count.getFifth()+1);
		}else if (score>=0.50 && score <=0.59) {
			  count.setSixth(count.getSixth()+1);
		}else if (score>=0.60 && score <=0.69) {
			  count.setSeventh(count.getSeventh()+1);
		}else if (score>=0.70 && score <=0.79) {
			  count.setEighth(count.getEighth()+1);
		}else if (score>=0.80 && score <=0.89) {
			  count.setNinth(count.getNinth()+1);
		}else if (score>=0.90 && score <=1) {
			  count.setTenth(count.getTenth()+1);
		}
		Services.update(QuizCount.class, count);
		quizSession.setScore(score);
		quizSession.setCount(count);
		return quizSession;
	}

	
}
