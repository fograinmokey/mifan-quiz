package com.mifan.quiz.web;

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
import org.springframework.web.bind.annotation.RestController;

import com.mifan.quiz.domain.Answers;
import com.mifan.quiz.service.AnswersService;

@RestController
@RequestMapping("/answers")
public class AnswersController extends RestfulController<Answers> {
	
     @Autowired	
     private AnswersService  answersService ;
	/**
	   *  提交答案，记录quiz_session，返回是否答对
	   * @return
	   */
	//@RequiresAuthentication
    @RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Response> commitAnswers(@RequestBody Data<Answers> data) {
    	  Answers entity = data.getData();
          hasError(validate(entity, Post.class));
          afterPostValidate(entity);
    	Answers answers = answersService.saveAnswers(entity);
		return ResponseEntity.ok(Responses.builder().data(answers)); 
	  }

}
