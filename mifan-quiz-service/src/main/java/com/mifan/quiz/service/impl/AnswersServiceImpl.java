package com.mifan.quiz.service.impl;

import com.mifan.quiz.dao.AnswersDao;
import com.mifan.quiz.domain.Answers;
import com.mifan.quiz.service.AnswersService;
import com.mifan.quiz.service.BaseServiceAdapter;

import org.springframework.stereotype.Service;

/**
 * @author ZYW
 * @version 1.0
 * @since 2017-12-12
 */
@Service
public class AnswersServiceImpl extends BaseServiceAdapter<Answers, AnswersDao> implements AnswersService {
}
