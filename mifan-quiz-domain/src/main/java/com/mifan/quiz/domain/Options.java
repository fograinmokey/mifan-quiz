package com.mifan.quiz.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.moonframework.model.mybatis.domain.BaseEntity;
import org.moonframework.validation.ValidationGroups.Post;

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
    @NotBlank(groups = {Post.class}, message = "NotNull.Options.optionTitle")
    private String optionTitle;
    @NotNull(groups = {Post.class}, message = "NotNull.Options.isCorrect")
    @Range(min = 0, max = 1, groups = {Post.class}, message = "{Error.Options.isCorrect}")
    private Integer isCorrect;

    private Integer answerCount;
    
    public Integer getAnswerCount() {
		return answerCount;
	}

	public void setAnswerCount(Integer answerCount) {
		this.answerCount = answerCount;
	}

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
