package com.mifan.quiz.service.impl;

import com.mifan.quiz.dao.QuestionsDao;
import com.mifan.quiz.domain.Answers;
import com.mifan.quiz.domain.Options;
import com.mifan.quiz.domain.Questions;
import com.mifan.quiz.domain.QuizSession;
import com.mifan.quiz.service.BaseServiceAdapter;
import com.mifan.quiz.service.QuestionslService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.moonframework.model.mybatis.criterion.Criterion;
import org.moonframework.model.mybatis.criterion.Restrictions;
import org.moonframework.model.mybatis.domain.Pages;
import org.moonframework.model.mybatis.service.Services;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author YHF
 * @version 1.0
 * @since 2017-12-12
 */
@Service
public class QuestionslServiceImpl extends BaseServiceAdapter<Questions, QuestionsDao> implements QuestionslService {
	
    @Override
    public Page<Questions> findAlll(Long quizId,int page,int size,String sessionCode){
        PageRequest pageRequest =  Pages.builder().page(page).size(size).sort(Pages.sortBuilder().add(Questions.DISPLAY_ORDER,true).build()).build();
        Criterion criterion = Restrictions.and(Restrictions.eq(Questions.QUIZ_ID, quizId),Restrictions.eq(Questions.ENABLED, 1));
        Page<Questions> pages = super.findAll(criterion, pageRequest);
        QuizSession quizSessionl = new QuizSession();
        quizSessionl.setSessionCode(sessionCode);
        QuizSession quizSession = Services.findOne(QuizSession.class, quizSessionl);
        if (quizSession == null) {
        	throw new IllegalStateException("没有该会话！请重新开始问卷");
		}
        List<Long> idList = new ArrayList<>();
        if(pages.hasContent()) {
            List<Questions> questions = pages.getContent();
            for (Questions question : questions) {
            	Answers answers = Services.findOne(Answers.class, Restrictions.and(Restrictions.eq(Answers.SESSION_ID, quizSession.getId()),
            			Restrictions.eq(Answers.QUESTION_ID, question.getId())));
            	if (answers != null) {
					//如果用户提交的答案不为null 则 说明这道题用户做过回答  则返回其提交的答案
            		question.setAnswers(answers);
            		List<Options> optionList = Services.findAll(Options.class, Restrictions.and(Restrictions.eq(Options.QUESTION_ID, 
            				question.getId()), Restrictions.eq(Options.IS_CORRECT, 1)));
            		for (Options options : optionList) {
            			idList.add(options.getId());
            		}
            		answers.setIdList(idList);
				}
            	
        		
			}
            Long[] questionIds = questions.stream().map(Questions::getId).collect(Collectors.toList()).toArray(new Long[questions.size()]);
            List<Options> options = Services.findAll(Options.class, Restrictions.in(Options.QUESTION_ID, questionIds));
            Map<Long,List<Options>> map = options.stream().collect(Collectors.groupingBy(Options::getQuestionId));
            questions.stream().forEach(q -> q.setOptions(map.get(q.getId())));
        }
        
        return pages;
    }

}
