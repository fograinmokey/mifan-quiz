package com.mifan.quiz.service;

import com.mifan.quiz.domain.Options;

import java.util.List;

import org.moonframework.model.mybatis.service.BaseService;

/**
 * @author ZYW
 * @version 1.0
 * @since 2017-12-12
 */
public interface OptionsService extends BaseService<Options> {
    
    /**
     * 用于定制试卷时，保存试卷中包含的选项
     * @param entities
     * @return
     */
    int saveOptions(List<Options> entities);
}
