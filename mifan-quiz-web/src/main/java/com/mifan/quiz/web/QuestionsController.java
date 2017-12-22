package com.mifan.quiz.web;


import javax.servlet.http.HttpServletRequest;

import org.moonframework.web.core.RestfulController;
import org.moonframework.web.jsonapi.Response;
import org.moonframework.web.jsonapi.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mifan.quiz.domain.Questions;
import com.mifan.quiz.service.QuestionsService;
/**
 * @author ZYW
 *
 */
@RestController
@RequestMapping("/questions")
public class QuestionsController extends RestfulController<Questions> {
    
    @Autowired
    private QuestionsService questionsService;
    
//  @RequiresAuthentication
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Response> doGetPageByQuiz(
            @RequestParam(required = false, name = "page[number]", defaultValue = "1") int page,
            @RequestParam(required = false, name = "page[size]", defaultValue = "1") int size,
            @RequestParam(required = true, name = "filter[quizId]") Long quizId,
            @RequestParam(required = true, name = "Session[sessionCode]") String sessionCode) {
        HttpServletRequest request = getHttpServletRequest();    
        Page<Questions> pages = questionsService.findAlll(quizId,page,size,sessionCode);
        return ResponseEntity.ok(Responses.builder().page(pages, "/questions", request.getParameterMap()).data(pages.getContent()));
    }
}
