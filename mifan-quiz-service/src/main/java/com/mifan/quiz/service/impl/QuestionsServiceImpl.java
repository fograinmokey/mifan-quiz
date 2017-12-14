package com.mifan.quiz.service.impl;

import com.mifan.quiz.dao.QuestionsDao;
import com.mifan.quiz.domain.Options;
import com.mifan.quiz.domain.Questions;
import com.mifan.quiz.service.BaseServiceAdapter;
import com.mifan.quiz.service.QuestionsService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.moonframework.model.mybatis.criterion.Restrictions;
import org.moonframework.model.mybatis.domain.Fields;
import org.moonframework.model.mybatis.service.Services;
import org.springframework.stereotype.Service;

/**
 * @author ZYW
 * @version 1.0
 * @since 2017-12-12
 */
@Service
public class QuestionsServiceImpl extends BaseServiceAdapter<Questions, QuestionsDao> implements QuestionsService {
    
    @Override
    public int saveQuestions(List<Questions> entities) {
        List<Questions> updates = entities.stream().filter(q -> q.getId() != null).collect(Collectors.toList());
        List<Long> updateIds = updates.stream().map(q -> q.getId()).collect(Collectors.toList());
        if(CollectionUtils.isNotEmpty(updates)) {
            super.update(updates);
        }
        
        List<Questions> olds = super.findAll(Restrictions.eq(Questions.QUIZ_ID, entities.get(0).getQuizId()), Fields.builder().add(Questions.ID).build());
        if(CollectionUtils.isNotEmpty(olds)) {
            List<Long> oldIds = olds.stream().map(q -> q.getId()).collect(Collectors.toList());
            oldIds.removeAll(updateIds);
            if(CollectionUtils.isNotEmpty(oldIds)) {
                for(Long questionId : oldIds) {
                    List<Long> optionIds = Services.findAll(Options.class,
                                            Restrictions.eq(Options.QUESTION_ID, questionId), Fields.builder().add(Options.ID).build()).
                                            stream().map(Options::getId).collect(Collectors.toList());
                    Services.delete(Options.class, optionIds);
                }
                super.delete(oldIds);
            }
        }
        
        List<Questions> adds = entities.stream().filter(q -> q.getId() == null).collect(Collectors.toList());
        super.save(adds);
        
        List<Options> options = new ArrayList<Options>();
        for(Questions q : entities) {
            for(Options o : q.getOptions()) {
                o.setQuestionId(q.getId());
                options.add(o);
            }
        }
        return 1;
    }
}
