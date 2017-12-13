package com.mifan.quiz.service.impl;

import com.mifan.quiz.dao.QuizsDao;
import com.mifan.quiz.domain.Quizs;
import com.mifan.quiz.service.BaseServiceAdapter;
import com.mifan.quiz.service.QuizsService;

import org.springframework.stereotype.Service;

/**
 * @author ZYW
 * @version 1.0
 * @since 2017-12-12
 */
@Service
public class QuizsServiceImpl extends BaseServiceAdapter<Quizs, QuizsDao> implements QuizsService {
}
