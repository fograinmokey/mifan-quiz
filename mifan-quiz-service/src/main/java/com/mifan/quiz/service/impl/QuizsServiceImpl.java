package com.mifan.quiz.service.impl;

import com.mifan.quiz.dao.QuizsDao;
import com.mifan.quiz.domain.QuizCount;
import com.mifan.quiz.domain.Quizs;
import com.mifan.quiz.service.BaseServiceAdapter;
import com.mifan.quiz.service.QuestionsService;
import com.mifan.quiz.service.QuizsService;

import org.moonframework.model.mybatis.criterion.Restrictions;
import org.moonframework.model.mybatis.domain.Field;
import org.moonframework.model.mybatis.service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZYW
 * @version 1.0
 * @since 2017-12-12
 */
@Service
public class QuizsServiceImpl extends BaseServiceAdapter<Quizs, QuizsDao> implements QuizsService {
    
    @Autowired
    private QuestionsService questionsService;
    
   @Override
    public Quizs queryForObject(Long id, Iterable<? extends Field> fields){
       Quizs quiz = super.queryForObject(id, fields);
       QuizCount quizCount = Services.findOne(QuizCount.class, Restrictions.eq(QuizCount.QUIZ_ID, id));
       quiz.setQuizCount(quizCount);
       return quiz;
    }
    
    @Override
    public int save(Quizs entity) {
        int n = 0;
        entity.setQuestionNum(entity.getQuestions().size());
        if(entity.getId() != null) {
            n += super.update(entity,Restrictions.eq(Quizs.STATE, 0));//加锁，只能修改状态为待发布的
            if(n == 0)
                throw new IllegalStateException("该问卷不允许修改！");
        }else {
            entity.setState(0);
            n += super.save(entity);
        }
        
        entity.getQuestions().forEach(q -> {q.setQuizId(entity.getId());
                                            q.setCreator(entity.getCreator());
                                            q.setModifier(entity.getModifier());});
        questionsService.saveQuestions(entity.getQuestions());
        
        return n;
    }
    @Override
    public int update(Quizs entity) {
        int n = 0;
        if(entity.getState() == 1) 
            n += super.update(entity);
        else
            n += super.update(entity,Restrictions.eq(Quizs.STATE, 1));
        if(n == 0)
            throw new IllegalStateException("该操作不被允许！");
        return n;
    }
}
