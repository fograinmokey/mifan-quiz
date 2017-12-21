package com.mifan.quiz.domain;

import javax.validation.constraints.NotNull;

import org.moonframework.model.mybatis.domain.BaseEntity;
import org.moonframework.validation.ValidationGroups.Post;

/**
 * @author ZYW
 * @version 1.0
 * @since 2017-12-12
 */
public class QuizCount extends BaseEntity {

    public static final String TABLE_NAME = "quiz_count";

    public static final String QUIZ_ID = "quiz_id";
    public static final String PEOPLES = "peoples";
    public static final String FIRST = "first";
    public static final String SECOND = "second";
    public static final String THIRD = "third";
    public static final String FOURTH = "fourth";
    public static final String FIFTH = "fifth";
    public static final String SIXTH = "sixth";
    public static final String SEVENTH = "seventh";
    public static final String EIGHTH = "eighth";
    public static final String NINTH = "ninth";
    public static final String TENTH = "tenth";

    private static final long serialVersionUID = -2012619555374441769L;
    
    @NotNull(groups = {Post.class}, message = "{NotNull.QuizCount.quizId}")
    private Long quizId;
    private Integer peoples;
    private Integer first;
    private Integer second;
    private Integer third;
    private Integer fourth;
    private Integer fifth;
    private Integer sixth;
    private Integer seventh;
    private Integer eighth;
    private Integer ninth;
    private Integer tenth;

    public QuizCount() {
    }

    public QuizCount(Long id) {
        super(id);
    }

    /**
     * @return 
     */
    public Long getQuizId() {
        return quizId;
    }

    /**
     * @param quizId 
     */
    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }
    /**
     * @return 完成人数
     */
    public Integer getPeoples() {
        return peoples;
    }

    /**
     * @param peoples 完成人数
     */
    public void setPeoples(Integer peoples) {
        this.peoples = peoples;
    }
    /**
     * @return 得分0-10%人数
     */
    public Integer getFirst() {
        return first;
    }

    /**
     * @param first 得分0-10%人数
     */
    public void setFirst(Integer first) {
        this.first = first;
    }
    /**
     * @return 得分10-19%人数
     */
    public Integer getSecond() {
        return second;
    }

    /**
     * @param second 得分10-19%人数
     */
    public void setSecond(Integer second) {
        this.second = second;
    }
    /**
     * @return 得分20-29%人数
     */
    public Integer getThird() {
        return third;
    }

    /**
     * @param third 得分20-29%人数
     */
    public void setThird(Integer third) {
        this.third = third;
    }
    /**
     * @return 得分30-39%人数
     */
    public Integer getFourth() {
        return fourth;
    }

    /**
     * @param fourth 得分30-39%人数
     */
    public void setFourth(Integer fourth) {
        this.fourth = fourth;
    }
    /**
     * @return 得分40-49%人数
     */
    public Integer getFifth() {
        return fifth;
    }

    /**
     * @param fifth 得分40-49%人数
     */
    public void setFifth(Integer fifth) {
        this.fifth = fifth;
    }
    /**
     * @return 得分50-59%人数
     */
    public Integer getSixth() {
        return sixth;
    }

    /**
     * @param sixth 得分50-59%人数
     */
    public void setSixth(Integer sixth) {
        this.sixth = sixth;
    }
    /**
     * @return 得分60-69%人数
     */
    public Integer getSeventh() {
        return seventh;
    }

    /**
     * @param seventh 得分60-69%人数
     */
    public void setSeventh(Integer seventh) {
        this.seventh = seventh;
    }
    /**
     * @return 得分70-79%人数
     */
    public Integer getEighth() {
        return eighth;
    }

    /**
     * @param eighth 得分70-79%人数
     */
    public void setEighth(Integer eighth) {
        this.eighth = eighth;
    }
    /**
     * @return 得分80-89%人数
     */
    public Integer getNinth() {
        return ninth;
    }

    /**
     * @param ninth 得分80-89%人数
     */
    public void setNinth(Integer ninth) {
        this.ninth = ninth;
    }
    /**
     * @return 得分90-100%人数
     */
    public Integer getTenth() {
        return tenth;
    }

    /**
     * @param tenth 得分90-100%人数
     */
    public void setTenth(Integer tenth) {
        this.tenth = tenth;
    }

}
