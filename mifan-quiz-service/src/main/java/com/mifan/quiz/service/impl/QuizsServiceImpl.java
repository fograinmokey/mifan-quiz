package com.mifan.quiz.service.impl;

import com.mifan.quiz.dao.QuizsDao;
import com.mifan.quiz.domain.Quizs;
import com.mifan.quiz.service.BaseServiceAdapter;
import com.mifan.quiz.service.QuestionsService;
import com.mifan.quiz.service.QuizsService;

import org.moonframework.model.mybatis.criterion.Restrictions;
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
    public int save(Quizs entity) {
        int n = 0;
        if(entity.getId() != null) {
            n += super.update(entity,Restrictions.eq(Quizs.STATE, 0));//加锁，只能修改状态为待发布的
            if(n == 0)
                throw new IllegalStateException("该问卷不允许修改！");
        }else {
            entity.setState(0);
            n += super.save(entity);
        }
        //11111111
        entity.getQuestions().forEach(q -> q.setQuizId(entity.getId()));
        questionsService.saveQuestions(entity.getQuestions());
        //哇哦，做个测试
        return n;
    }
}
