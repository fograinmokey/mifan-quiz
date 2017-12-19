package com.mifan.quiz.web;


import java.util.UUID;

import org.moonframework.model.mybatis.service.Services;
import org.moonframework.web.core.RestfulController;
import org.moonframework.web.jsonapi.Data;
import org.moonframework.web.jsonapi.Response;
import org.moonframework.web.jsonapi.Responses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mifan.quiz.domain.QuizSession;
import com.mifan.quiz.domain.Quizs;

@RestController
@RequestMapping("/quizSession")
public class QuizSessionController extends RestfulController<QuizSession> {
	

	/**
	 * 申请session_code绑定问卷
	 * @param data
	 * @return
	 */
	//@RequiresAuthentication
    @RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Response> applySessionCode(@RequestBody Data<QuizSession> data){
		// 随机生成 随机码
		String s = UUID.randomUUID().toString();
		//绑定 随机码 和问卷
		data.getData().setSessionCode(s);
		//存入数据库
		super.doPost(data);
		// 获取 并返回 该 客户 要 填写的 问卷信息
		Quizs quizs = Services.findOne(Quizs.class, data.getData().getQuizId());
		
		return ResponseEntity.ok(Responses.builder().data(quizs));
	}
	

}
