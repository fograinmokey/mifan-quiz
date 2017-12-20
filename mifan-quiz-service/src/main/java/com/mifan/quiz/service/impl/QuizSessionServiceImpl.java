package com.mifan.quiz.service.impl;

import com.mifan.quiz.dao.QuizSessionDao;
import com.mifan.quiz.domain.QuizSession;
import com.mifan.quiz.domain.Quizs;
import com.mifan.quiz.service.BaseServiceAdapter;
import com.mifan.quiz.service.QuizSessionService;

import java.util.UUID;

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
        if(!Services.exists(Quizs.class, entity.getQuizId()))
            throw new IllegalStateException("参数不存在！");
        
        String sessionCode = UUID.randomUUID().toString();
        entity.setSessionCode(sessionCode);
        return super.save(entity);
    }
}
