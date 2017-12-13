package com.mifan.quiz.service.impl;

import com.mifan.quiz.dao.QuizSessionDao;
import com.mifan.quiz.domain.QuizSession;
import com.mifan.quiz.service.BaseServiceAdapter;
import com.mifan.quiz.service.QuizSessionService;
import org.springframework.stereotype.Service;

/**
 * @author ZYW
 * @version 1.0
 * @since 2017-12-12
 */
@Service
public class QuizSessionServiceImpl extends BaseServiceAdapter<QuizSession, QuizSessionDao> implements QuizSessionService {
}
