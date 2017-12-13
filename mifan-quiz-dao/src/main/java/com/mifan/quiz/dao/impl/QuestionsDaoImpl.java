package com.mifan.quiz.dao.impl;

import com.mifan.quiz.dao.BaseDaoAdapter;
import com.mifan.quiz.dao.QuestionsDao;
import com.mifan.quiz.domain.Questions;
import org.springframework.stereotype.Repository;

/**
 * @author ZYW
 * @version 1.0
 * @since 2017-12-12
 */
@Repository
public class QuestionsDaoImpl extends BaseDaoAdapter<Questions> implements QuestionsDao {
}
