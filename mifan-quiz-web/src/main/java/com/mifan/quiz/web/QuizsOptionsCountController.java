package com.mifan.quiz.web;

import org.moonframework.web.jsonapi.Response;
import org.moonframework.web.jsonapi.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mifan.quiz.domain.Questions;
import com.mifan.quiz.service.QuestionsService;

@RestController
@RequestMapping("/optionsCount")
public class QuizsOptionsCountController  {
	 
	@Autowired
	private  QuestionsService  questionsService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Response> optionsCount(Long questionId){
		
		Questions questions = questionsService.optionsCount(questionId);

		return ResponseEntity.ok(Responses.builder().data(questions));
		
		
}
}
