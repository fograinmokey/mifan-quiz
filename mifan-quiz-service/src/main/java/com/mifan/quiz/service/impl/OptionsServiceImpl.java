package com.mifan.quiz.service.impl;

import com.mifan.quiz.dao.OptionsDao;
import com.mifan.quiz.domain.Options;
import com.mifan.quiz.service.BaseServiceAdapter;
import com.mifan.quiz.service.OptionsService;
import org.springframework.stereotype.Service;

/**
 * @author ZYW
 * @version 1.0
 * @since 2017-12-12
 */
@Service
public class OptionsServiceImpl extends BaseServiceAdapter<Options, OptionsDao> implements OptionsService {
}
