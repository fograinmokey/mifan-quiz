package com.mifan.quiz.web;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.moonframework.model.mybatis.criterion.Criterion;
import org.moonframework.model.mybatis.criterion.Restrictions;
import org.moonframework.model.mybatis.domain.BaseEntity;
import org.moonframework.model.mybatis.service.Services;
import org.moonframework.web.core.RestfulController;
import org.moonframework.web.jsonapi.Data;
import org.moonframework.web.jsonapi.Response;
import org.moonframework.web.jsonapi.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mifan.quiz.domain.Quizs;
import com.mifan.quiz.service.QuizsService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author ZYW
 * 2017年12月13日
 */
@RestController
@RequestMapping("/quizs")
public class QuizsController extends RestfulController<Quizs> {
    
    @Autowired
    private QuizsService quizsService;
    
    @RequiresAuthentication
    @RequestMapping(method = RequestMethod.GET)
    @Override
    public ResponseEntity<Response> doGetPage(
            @RequestParam(required = false, name = "page[number]", defaultValue = "1") int page,
            @RequestParam(required = false, name = "page[size]", defaultValue = "10") int size,
            @RequestParam(required = false) String[] sort,
            @RequestParam(required = false) String[] include) {
                
        return super.doGetPage(page, size, sort, include);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Override
    public ResponseEntity<Response> doGet(@PathVariable Long id,
            @RequestParam(required = false) String[] include) {
        return super.doGet(id, include);
    }
    @RequiresAuthentication
    @RequestMapping(value = "/{id}/admin", method = RequestMethod.GET)
    public ResponseEntity<Response> doGetForAdmin(@PathVariable Long id) {
        Quizs quiz = quizsService.findOneForAdmin(id);
        hasPermissions(quiz.getCreator());
        return ResponseEntity.ok(Responses.builder().data(quiz));
    }
    @RequiresAuthentication
    @RequestMapping(method = RequestMethod.POST,consumes = APPLICATION_JSON_VALUE,produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> doPost(@RequestBody Data<Quizs> data){
        if(data.getData().getId() != null) {
            Quizs quiz = Services.findOne(Quizs.class, data.getData().getId());
            hasPermissions(quiz.getCreator());
        }
        return super.doPost(data);
    }
//    @RequiresAuthentication
    @RequestMapping(value = "/{id}",method = RequestMethod.PATCH,consumes = APPLICATION_JSON_VALUE,produces = APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<Response> doPatch(@PathVariable Long id, @RequestBody Data<Quizs> data){
        Quizs quiz = Services.findOne(Quizs.class, id);
        hasPermissions(quiz.getCreator());
        return super.doPatch(id, data);
    }
//  @RequiresAuthentication
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Override
    public ResponseEntity<Response> doDelete(@PathVariable Long id) {
        Quizs quiz = Services.findOne(Quizs.class, id);
        hasPermissions(quiz.getCreator());
        return super.doDelete(id);
    }
    protected Criterion criterion(Criterion criterion) {
        return Restrictions.and(Restrictions.eq(Quizs.CREATOR, getCurrentUserId()),
                Restrictions.eq(BaseEntity.ENABLED, 1),
                criterion);
    }
}
