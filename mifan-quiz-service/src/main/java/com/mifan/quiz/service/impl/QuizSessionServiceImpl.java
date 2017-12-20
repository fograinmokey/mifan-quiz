package com.mifan.quiz.service.impl;

import com.mifan.quiz.dao.QuizSessionDao;
import com.mifan.quiz.domain.QuizSession;
import com.mifan.quiz.domain.Quizs;
import com.mifan.quiz.service.BaseServiceAdapter;
import com.mifan.quiz.service.QuizSessionService;

import java.util.UUID;

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
        return super.save(entity);
    }
}
