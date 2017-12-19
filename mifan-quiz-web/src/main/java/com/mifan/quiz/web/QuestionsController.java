package com.mifan.quiz.web;


import org.moonframework.web.core.RestfulController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mifan.quiz.domain.Questions;
/**
 * @author ZYW
 *
 */
@RestController
@RequestMapping("/questions")
public class QuestionsController extends RestfulController<Questions> {
    
   /* @Autowired
    private QuestionsService questionsService;//此方法废弃,随时可删除
    
//    @RequiresAuthentication
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Response> doGetPageByQuiz(
            @RequestParam(required = false, name = "page[number]", defaultValue = "1") int page,
            @RequestParam(required = false, name = "page[size]", defaultValue = "10") int size,
            @RequestParam(required = true, name = "filter[quizId]") Long quizId) {
        HttpServletRequest request = getHttpServletRequest();    
        Page<Questions> pages = questionsService.findAll(quizId,page,size);
        return ResponseEntity.ok(Responses.builder().page(pages, "/questions", request.getParameterMap()).data(pages.getContent()));
    }*/
}
