package com.mifan.quiz.domain;

import org.moonframework.model.mybatis.domain.BaseEntity;

/**
 * @author ZYW
 * @version 1.0
 * @since 2017-12-12
 */
public class Options extends BaseEntity {

    public static final String TABLE_NAME = "options";

    public static final String QUESTION_ID = "question_id";
    public static final String OPTION_TITLE = "option_title";
    public static final String IS_CORRECT = "is_correct";

    private static final long serialVersionUID = -7541523219648103844L;

    private Long questionId;
    private String optionTitle;
    private Integer isCorrect;

    public Options() {
    }

    public Options(Long id) {
        super(id);
    }

    /**
     * @return 问题ID
     */
    public Long getQuestionId() {
        return questionId;
    }

    /**
     * @param questionId 问题ID
     */
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
    /**
     * @return 选项标题
     */
    public String getOptionTitle() {
        return optionTitle;
    }

    /**
     * @param optionTitle 选项标题
     */
    public void setOptionTitle(String optionTitle) {
        this.optionTitle = optionTitle;
    }
    /**
     * @return 是否正确选项,0:否  1:是
     */
    public Integer getIsCorrect() {
        return isCorrect;
    }

    /**
     * @param isCorrect 是否正确选项,0:否  1:是
     */
    public void setIsCorrect(Integer isCorrect) {
        this.isCorrect = isCorrect;
    }

}
