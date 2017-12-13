package com.mifan.quiz.domain;

import org.moonframework.model.mybatis.domain.BaseEntity;

/**
 * @author ZYW
 * @version 1.0
 * @since 2017-12-12
 */
public class Questions extends BaseEntity {

    public static final String TABLE_NAME = "questions";

    public static final String QUIZ_ID = "quiz_id";
    public static final String QUESTION_TITLE = "question_title";
    public static final String TYPE = "type";
    public static final String DISPLAY_ORDER = "display_order";

    private static final long serialVersionUID = 5944281008955464067L;

    private Long quizId;
    private String questionTitle;
    private Integer type;
    private Integer displayOrder;

    public Questions() {
    }

    public Questions(Long id) {
        super(id);
    }

    /**
     * @return 问卷id
     */
    public Long getQuizId() {
        return quizId;
    }

    /**
     * @param quizId 问卷id
     */
    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }
    /**
     * @return 问题标题
     */
    public String getQuestionTitle() {
        return questionTitle;
    }

    /**
     * @param questionTitle 问题标题
     */
    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }
    /**
     * @return 类型，1：单选 2：多选
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type 类型，1：单选 2：多选
     */
    public void setType(Integer type) {
        this.type = type;
    }
    /**
     * @return 排序
     */
    public Integer getDisplayOrder() {
        return displayOrder;
    }

    /**
     * @param displayOrder 排序
     */
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

}
