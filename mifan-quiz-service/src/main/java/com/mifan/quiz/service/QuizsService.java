package com.mifan.quiz.service;

import com.mifan.quiz.domain.Quizs;
import org.moonframework.model.mybatis.service.BaseService;

/**
 * @author ZYW
 * @version 1.0
 * @since 2017-12-12
 */
public interface QuizsService extends BaseService<Quizs> {
    /**
     * 查询问卷详情（包含试题），用于管理问卷
     * @param id
     * @return
     */
    Quizs findOneForAdmin(Long id);
}
