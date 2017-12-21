package com.mifan.quiz.service;

import com.mifan.quiz.domain.Questions;
import com.mifan.quiz.domain.QuizCount;
import org.moonframework.model.mybatis.service.BaseService;

/**
 * @author ZYW
 * @version 1.0
 * @since 2017-12-12
 */
public interface QuizCountService extends BaseService<QuizCount> {
	
    
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
    public Questions optionsCount(Long optCountquesId);
}
