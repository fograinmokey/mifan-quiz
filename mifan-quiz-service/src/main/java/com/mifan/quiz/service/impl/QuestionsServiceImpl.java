package com.mifan.quiz.service.impl;

import com.mifan.quiz.dao.QuestionsDao;
import com.mifan.quiz.domain.Options;
import com.mifan.quiz.domain.Questions;
import com.mifan.quiz.service.BaseServiceAdapter;
import com.mifan.quiz.service.OptionsService;
import com.mifan.quiz.service.QuestionsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.moonframework.model.mybatis.criterion.Criterion;
import org.moonframework.model.mybatis.criterion.Restrictions;
import org.moonframework.model.mybatis.domain.Fields;
import org.moonframework.model.mybatis.domain.Pages;
import org.moonframework.model.mybatis.service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author ZYW
 * @version 1.0
 * @since 2017-12-12
 */
@Service
public class QuestionsServiceImpl extends BaseServiceAdapter<Questions, QuestionsDao> implements QuestionsService {
    @Autowired
    private OptionsService optionsService;
	
    @Override
    public Page<Questions> findAll(Long quizId,int page,int size){
        PageRequest pageRequest =  Pages.builder().page(page).size(size).sort(Pages.sortBuilder().add(Questions.DISPLAY_ORDER,true).build()).build();
        Criterion criterion = Restrictions.and(Restrictions.eq(Questions.QUIZ_ID, quizId),Restrictions.eq(Questions.ENABLED, 1));
        Page<Questions> pages = super.findAll(criterion, pageRequest);
        
        if(pages.hasContent()) {
            List<Questions> questions = pages.getContent();
            Long[] questionIds = questions.stream().map(Questions::getId).collect(Collectors.toList()).toArray(new Long[questions.size()]);
            List<Options> options = Services.findAll(Options.class, Restrictions.in(Options.QUESTION_ID, questionIds));
            Map<Long,List<Options>> map = options.stream().collect(Collectors.groupingBy(Options::getQuestionId));
            questions.stream().forEach(q -> q.setOptions(map.get(q.getId())));
        }
        
        return pages;
    }
    
    @Override
    public int saveQuestions(List<Questions> entities) {
        List<Questions> updates = entities.stream().filter(q -> q.getId() != null).collect(Collectors.toList());
        List<Long> updateIds = updates.stream().map(q -> q.getId()).collect(Collectors.toList());
        super.update(updates);
        
        List<Questions> olds = super.findAll(Restrictions.and(Restrictions.eq(Questions.QUIZ_ID, entities.get(0).getQuizId()),
                                    Restrictions.eq(Questions.ENABLED, 1)), Fields.builder().add(Questions.ID).build());
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
            if(q.getType() == 1) {//类型为单选题时，正确答案只能有一个
                int sum = q.getOptions().stream().map(o -> o.getIsCorrect()).reduce((s,c) -> s + c).get();
                if(sum > 1) {
                    throw new IllegalStateException("单选题不应该有两个正确答案！");
                }
            }
            for(Options o : q.getOptions()) {
                o.setQuestionId(q.getId());
                o.setCreator(q.getCreator());
                o.setModifier(q.getModifier());
                options.add(o);
            }
        }
        optionsService.saveOptions(options);
        return 1;
    }


}
