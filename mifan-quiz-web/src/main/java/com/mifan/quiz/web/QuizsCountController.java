package com.mifan.quiz.web;
import javax.servlet.http.HttpServletRequest;

import org.moonframework.web.core.RestfulController;
import org.moonframework.web.jsonapi.Response;
import org.moonframework.web.jsonapi.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mifan.quiz.domain.Questions;
import com.mifan.quiz.domain.QuizSession;
import com.mifan.quiz.service.QuizCountService;

@RestController
@RequestMapping("/quizsCount")
public class QuizsCountController extends RestfulController<Questions> {
	@Autowired
	private  QuizCountService quizCountService;
	/**
	 * 每道题答对率
	 * @param 题目的id
	 * @return
	 */
	@RequestMapping(value = "/{id}/ratio",method = RequestMethod.GET)
	public ResponseEntity<Response> quesanswersRate(@PathVariable Long id){
		
		Questions question = quizCountService.quesanswersRate(id);
		return ResponseEntity.ok(Responses.builder().data(question));
	}
	/**
	 * 每道题的每个选项的被选中次数
	 * @param 题目的id
	 * @return
	 */
	@RequestMapping(value = "/{id}/count",method = RequestMethod.GET)
	public ResponseEntity<Response> optionsCount(@PathVariable Long id){
		
		Questions questions = quizCountService.optionsCount(id);
		return ResponseEntity.ok(Responses.builder().data(questions));
	}
	
	/**
	 * 分页展示问卷答案
	 * @param page
	 * @param size
	 * @param quizId
	 * @return
	 */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Response> doGetPageByQuiz(
            @RequestParam(required = false, name = "page[number]", defaultValue = "1") int page,
            @RequestParam(required = false, name = "page[size]", defaultValue = "10") int size,
            @RequestParam(required = true, name = "filter[quizId]") Long quizId) {
        HttpServletRequest request = getHttpServletRequest();    
        Page<QuizSession> pages = quizCountService.findQuestionAnswers(quizId,page,size);
        return ResponseEntity.ok(Responses.builder().page(pages, "/quizsCount", request.getParameterMap()).data(pages.getContent()));
    }

	
}
