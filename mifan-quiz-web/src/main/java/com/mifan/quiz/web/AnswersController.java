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

import com.mifan.quiz.domain.Answers;

@RestController
@RequestMapping("/answers")
public class AnswersController extends RestfulController<Answers> {
	
	/**
	   *  提交答案，记录quiz_session，返回是否答对
	   * @return
	   */
	//@RequiresAuthentication
    @RequestMapping(method = RequestMethod.POST,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> commitAnswers(@RequestBody Data<Answers> data) {
    	  Answers entity = data.getData();
    	  Long userId = getCurrentUserId();
          if (userId != null) {
              entity.setCreator(userId);
              entity.setModifier(userId);
          }
          hasError(validate(entity, Post.class));
          afterPostValidate(entity);
    	 switch (Services.save(entityClass, entity)) {
         case 0:
             throw new IllegalStateException();
         case ACCEPTED:
             return ResponseEntity.accepted().build();
         case NO_CONTENT:
             return ResponseEntity.noContent().build();
         default:
             Map<String, Object> result = new HashMap<>();
             result.put("allDone", entity.getAllDone());
             result.put("isRight", entity.getIsRight());
             result.put("type", "Answers");
             result.put("id", entity.getId());
             return ResponseEntity.created(URI.create("/answers" + "/" + entity.getId())).body(Responses.builder().data(result));
     }
	  }

}
