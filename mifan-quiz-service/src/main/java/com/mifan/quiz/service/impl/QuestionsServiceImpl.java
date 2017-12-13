package com.mifan.quiz.service.impl;

import com.mifan.quiz.dao.QuestionsDao;
import com.mifan.quiz.domain.Questions;
import com.mifan.quiz.service.BaseServiceAdapter;
import com.mifan.quiz.service.QuestionsService;
import org.springframework.stereotype.Service;

/**
 * @author ZYW
 * @version 1.0
 * @since 2017-12-12
 */
@Service
public class QuestionsServiceImpl extends BaseServiceAdapter<Questions, QuestionsDao> implements QuestionsService {
}
