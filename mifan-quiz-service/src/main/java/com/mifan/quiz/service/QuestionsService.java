package com.mifan.quiz.service;

import com.mifan.quiz.domain.Questions;

import java.util.List;

import org.moonframework.model.mybatis.service.BaseService;
import org.springframework.data.domain.Page;

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
    
    /**
     * 分页查询试卷的试题
     * @param quizId
     * @param page
     * @param size
     * @return
     */
    public Page<Questions> findAll(Long quizId,int page,int size);
    
    /**
     * 统计每道题答对率
     * @param questionId
     * @return
     */
    
    public Questions quesanswersRate(Long questionId);
    /**
     * 统计每道题的每个选项的被选中次数
     * @param questionId
     * @return
     */
    public Questions optionsCount(Long questionId);
    
}
