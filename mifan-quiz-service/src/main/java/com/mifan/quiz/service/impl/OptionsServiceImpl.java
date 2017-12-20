package com.mifan.quiz.service.impl;

import com.mifan.quiz.dao.OptionsDao;
import com.mifan.quiz.domain.Options;
import com.mifan.quiz.service.BaseServiceAdapter;
import com.mifan.quiz.service.OptionsService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.moonframework.model.mybatis.criterion.Restrictions;
import org.moonframework.model.mybatis.domain.Fields;
import org.springframework.stereotype.Service;

/**
 * @author ZYW
 * @version 1.0
 * @since 2017-12-12
 */
@Service
public class OptionsServiceImpl extends BaseServiceAdapter<Options, OptionsDao> implements OptionsService {
    
    @Override
    public int saveOptions(List<Options> entities) {
        List<Options> updates = entities.stream().filter(q -> q.getId() != null).collect(Collectors.toList());
        List<Long> updateIds = updates.stream().map(q -> q.getId()).collect(Collectors.toList());
        super.update(updates);
        
        Map<Long,List<Options>> map = entities.stream().collect(Collectors.groupingBy(Options::getQuestionId));
        
        List<Options> olds = super.findAll(Restrictions.in(Options.QUESTION_ID, map.keySet().toArray()), Fields.builder().add(Options.ID).build());
        if(CollectionUtils.isNotEmpty(olds)) {
            List<Long> oldIds = olds.stream().map(o -> o.getId()).collect(Collectors.toList());
            oldIds.removeAll(updateIds);
            if(CollectionUtils.isNotEmpty(oldIds)) {
                super.delete(oldIds);
            }
        }
        
        List<Options> adds = entities.stream().filter(o -> o.getId() == null).collect(Collectors.toList());
        super.save(adds);
        
        return 1;
    }
}
