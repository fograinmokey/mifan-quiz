package com.mifan.quiz.service;

import com.mifan.quiz.domain.Questions;

import java.util.List;

import org.moonframework.model.mybatis.service.BaseService;

/**
 * @author ZYW
 * @version 1.0
 * @since 2017-12-12
 */
public interface QuestionsService extends BaseService<Questions> {
    
    /**
     * 用于定制试卷时，保存试卷包含的试题
     * @param entities
     * @return
     */
    int saveQuestions(List<Questions> entities);
}
