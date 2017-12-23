package com.mifan.quiz.web;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.moonframework.web.core.RestfulController;
import org.moonframework.web.jsonapi.Data;
import org.moonframework.web.jsonapi.Response;
import org.moonframework.web.jsonapi.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mifan.quiz.domain.Questions;
import com.mifan.quiz.domain.QuizCount;
import com.mifan.quiz.domain.QuizSession;
import com.mifan.quiz.service.QuizCountService;

@RestController
@RequestMapping("/quizsCounts")
public class QuizsCountController extends RestfulController<QuizCount> {
	@Autowired
	private  QuizCountService quizCountService;
	
	/**
	 * 每道题答对率
	 * @param 题目的id
	 * @return
	 */
	@RequiresAuthentication
    @RequiresRoles(ROLE_ADMIN)
	@RequestMapping(value = "/questions/{id}",method = RequestMethod.GET)
	public ResponseEntity<Response> quesanswersRate(@PathVariable Long id){
		
		Questions question = quizCountService.quesanswersRate(id);
		return ResponseEntity.ok(Responses.builder().data(question));
	}
	/**
	 * 每道题的每个选项的被选中次数
	 * @param 题目的id
	 * @return
	 */
	@RequiresAuthentication
    @RequiresRoles(ROLE_ADMIN)
	@RequestMapping(value = "/questions/{id}/options",method = RequestMethod.GET)
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
	@RequiresAuthentication
    @RequiresRoles(ROLE_ADMIN)
    @RequestMapping(value = "/quizs",method = RequestMethod.GET)
    public ResponseEntity<Response> doGetPageByQuiz(
            @RequestParam(required = false, name = "page[number]", defaultValue = "1") int page,
            @RequestParam(required = false, name = "page[size]", defaultValue = "10") int size,
            @RequestParam(required = true, name = "filter[quizId]") Long quizId) {
        HttpServletRequest request = getHttpServletRequest();    
        Page<QuizSession> pages = quizCountService.findQuestionAnswers(quizId,page,size);
        return ResponseEntity.ok(Responses.builder().page(pages, "/quizsCount", request.getParameterMap()).data(pages.getContent()));
    }

    @RequiresAuthentication
    @RequiresRoles(ROLE_ADMIN)
    @RequestMapping(method = RequestMethod.GET)
    @Override
    public ResponseEntity<Response> doGetPage(
            @RequestParam(required = false, name = "page[number]", defaultValue = "1") int page,
            @RequestParam(required = false, name = "page[size]", defaultValue = "10") int size,
            @RequestParam(required = false) String[] sort,
            @RequestParam(required = false) String[] include) {
        return super.doGetPage(page, size, sort, include);
    }
    @RequiresAuthentication
    @RequiresRoles(ROLE_ADMIN)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Override
    public ResponseEntity<Response> doGet(@PathVariable Long id,
            @RequestParam(required = false) String[] include) {
        return super.doGet(id, include);
    }
    @RequiresAuthentication
    @RequiresRoles(ROLE_ADMIN)
    @RequestMapping(method = RequestMethod.POST,
                    consumes = APPLICATION_JSON_VALUE,
                    produces = APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<Response> doPost(@RequestBody Data<QuizCount> data){
        return super.doPost(data);
    }
    @RequiresAuthentication
    @RequiresRoles(ROLE_ADMIN)
    @RequestMapping(value = "/{id}",method = RequestMethod.PATCH,
                    consumes = APPLICATION_JSON_VALUE,
                    produces = APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<Response> doPatch(@PathVariable Long id, @RequestBody Data<QuizCount> data){
        return super.doPatch(id, data);
    }
    @RequiresAuthentication
    @RequiresRoles(ROLE_ADMIN)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Override
    public ResponseEntity<Response> doDelete(@PathVariable Long id) {
        return super.doDelete(id);
    }
}
