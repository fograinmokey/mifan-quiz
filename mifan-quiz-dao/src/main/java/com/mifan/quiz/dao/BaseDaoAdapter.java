package com.mifan.quiz.dao;

import org.moonframework.model.mybatis.domain.BaseEntity;
import org.moonframework.model.mybatis.repository.AbstractBaseDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author zyw
 * @version 1.0
 * @since 2017/12/12
 */
public abstract class BaseDaoAdapter<T extends BaseEntity> extends AbstractBaseDao<T> {

    @Autowired
    @Qualifier("quizSqlSessionTemplate")
    @Override
    public void setSession(SqlSessionTemplate session) {
        super.setSession(session);
    }

}
