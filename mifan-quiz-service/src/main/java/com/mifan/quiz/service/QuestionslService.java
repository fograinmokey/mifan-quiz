package com.mifan.quiz.service;

import com.mifan.quiz.domain.Questions;
import org.moonframework.model.mybatis.service.BaseService;
import org.springframework.data.domain.Page;

/**
 * @author ZYW
 * @version 1.0
 * @since 2017-12-12
 */
public interface QuestionslService extends BaseService<Questions> {
    
    /**
     * 分页查询试卷的试题
     * @param quizId
     * @param page
     * @param size
     * @return
     */
    public Page<Questions> findAlll(Long quizId,int page,int size,String sessionCode);
    
}
