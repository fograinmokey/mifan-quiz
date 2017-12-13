package com.mifan.quiz.service.impl;

import com.mifan.quiz.dao.QuizCountDao;
import com.mifan.quiz.domain.QuizCount;
import com.mifan.quiz.service.BaseServiceAdapter;
import com.mifan.quiz.service.QuizCountService;
import org.springframework.stereotype.Service;

/**
 * @author ZYW
 * @version 1.0
 * @since 2017-12-12
 */
@Service
public class QuizCountServiceImpl extends BaseServiceAdapter<QuizCount, QuizCountDao> implements QuizCountService {
}
