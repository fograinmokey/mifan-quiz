package com.mifan.quiz.web;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.moonframework.model.mybatis.service.Services;
import org.moonframework.validation.ValidationGroups.Post;
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
@RestController
@RequestMapping("/quizSession")
public class QuizSessionController extends RestfulController<QuizSession> {
	

	/**
	 * 申请session_code绑定问卷
	 * @param data
	 * @return
	 */
	//@RequiresAuthentication
    /*@RequestMapping(method = RequestMethod.POST)
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
	}*/
	/**
	 * 生成sessionCode，（参考）
	 */
    @RequestMapping(method = RequestMethod.POST,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> doPost(@RequestBody Data<QuizSession> data){
        QuizSession entity = data.getData();
        Long userId = getCurrentUserId();
        if (userId != null) {
            entity.setCreator(userId);
            entity.setModifier(userId);
        }
        hasError(validate(entity, Post.class));
        switch (Services.save(entityClass, entity)) {
            case 0:
                throw new IllegalStateException();
            case ACCEPTED:
                return ResponseEntity.accepted().build();
            case NO_CONTENT:
                return ResponseEntity.noContent().build();
            default:
                Map<String, Object> result = new HashMap<>();
                result.put("sessionCode", entity.getSessionCode());
                result.put("type", "quizSession");
                result.put("id", entity.getId());
                return ResponseEntity.created(URI.create("/quizSession" + "/" + entity.getId())).body(Responses.builder().data(result));
        }
    }
}
