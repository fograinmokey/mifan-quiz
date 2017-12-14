package com.mifan.quiz.service.impl;

import com.mifan.quiz.dao.OptionsDao;
import com.mifan.quiz.domain.Options;
import com.mifan.quiz.service.BaseServiceAdapter;
import com.mifan.quiz.service.OptionsService;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

/**
 * @author ZYW
 * @version 1.0
 * @since 2017-12-12
 */
@Service
public class OptionsServiceImpl extends BaseServiceAdapter<Options, OptionsDao> implements OptionsService {
    
    public int saveOptions(List<Options> entities) {
        List<Options> updates = entities.stream().filter(q -> q.getId() != null).collect(Collectors.toList());
        List<Long> updateIds = updates.stream().map(q -> q.getId()).collect(Collectors.toList());
        if(CollectionUtils.isNotEmpty(updates)) {
            super.update(updates);
        }
        Options one = entities.get(0);
//        TODO  明天继续
        
        
        return 1;
    }
}
