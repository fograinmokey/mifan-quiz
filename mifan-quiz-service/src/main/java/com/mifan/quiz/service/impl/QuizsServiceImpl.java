package com.mifan.quiz.service.impl;

import com.mifan.quiz.dao.QuizsDao;
import com.mifan.quiz.domain.Questions;
import com.mifan.quiz.domain.Quizs;
import com.mifan.quiz.service.BaseServiceAdapter;
import com.mifan.quiz.service.QuizsService;

import org.moonframework.model.mybatis.service.Services;
import org.springframework.stereotype.Service;

/**
 * @author ZYW
 * @version 1.0
 * @since 2017-12-12
 */
@Service
public class QuizsServiceImpl extends BaseServiceAdapter<Quizs, QuizsDao> implements QuizsService {
    
    @Override
    public int save(Quizs entity) {
        int n = super.save(entity);
        entity.getQuestions().forEach(q -> q.setQuizId(entity.getId()));
//        int[] m = Services.save(Questions.class, entity.getQuestions());
        
        return 0;
    }
}
