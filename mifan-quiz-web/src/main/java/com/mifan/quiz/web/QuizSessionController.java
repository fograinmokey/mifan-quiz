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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mifan.quiz.domain.QuizSession;
import com.mifan.quiz.service.QuizSessionService;
@RestController
@RequestMapping("/quizSession")
public class QuizSessionController extends RestfulController<QuizSession> {
	
	@Autowired
	private QuizSessionService quizSessionService;
	
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
    
    /**
     * 答完题  查看结果
     * @param sessionCode
     * @return
     */
    @RequestMapping(value = "/score", method = RequestMethod.GET)
    public ResponseEntity<Response> checkResult(@RequestParam String sessionCode){
    	QuizSession quizSession  = quizSessionService.getResult(sessionCode);
    	return ResponseEntity.ok(Responses.builder().data(quizSession));
    }
}
