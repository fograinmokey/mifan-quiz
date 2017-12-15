package com.mifan.quiz.domain;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.moonframework.model.mybatis.domain.BaseEntity;
import org.moonframework.validation.ValidationGroups.Post;

/**
 * @author ZYW
 * @version 1.0
 * @since 2017-12-12
 */
public class Quizs extends BaseEntity {

    public static final String TABLE_NAME = "quizs";

    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String BACK_IMG = "back_img";
    public static final String STATE = "state";
    public static final String QUESTION_NUM = "question_num";

    private static final long serialVersionUID = 2554034364669780132L;

    @NotBlank(groups = {Post.class}, message = "NotNull.Quizs.title")
    private String title;
    private String description;
    @NotBlank(groups = {Post.class}, message = "NotNull.Quizs.backImg")
    private String backImg;
    @Null(groups = {Post.class}, message = "{MustNull.Quizs.state}")
    private Integer state;
    private Integer questionNum;
    
    @NotEmpty(groups = {Post.class}, message = "NotEmpty.Quizs.questions")
    @Valid
    private List<Questions> questions;

    public Quizs() {
    }

    public Quizs(Long id) {
        super(id);
    }

    /**
     * @return 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * @return 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * @return 背景图
     */
    public String getBackImg() {
        return backImg;
    }

    /**
     * @param backImg 背景图
     */
    public void setBackImg(String backImg) {
        this.backImg = backImg;
    }
    /**
     * @return 0：待发布，1：发布中，2，结束发布
     */
    public Integer getState() {
        return state;
    }

    /**
     * @param state 0：待发布，1：发布中，2，结束发布
     */
    public void setState(Integer state) {
        this.state = state;
    }
    /**
     * @return 题目个数
     */
    public Integer getQuestionNum() {
        return questionNum;
    }

    /**
     * @param questionNum 题目个数
     */
    public void setQuestionNum(Integer questionNum) {
        this.questionNum = questionNum;
    }

    public List<Questions> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Questions> questions) {
        this.questions = questions;
    }

}
