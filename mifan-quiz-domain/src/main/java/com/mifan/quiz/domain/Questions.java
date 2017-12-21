package com.mifan.quiz.domain;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.moonframework.model.mybatis.domain.BaseEntity;
import org.moonframework.validation.ValidationGroups.Post;

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
    @NotBlank(groups = {Post.class}, message = "NotNull.Questions.questionTitle")
    private String questionTitle;
    @NotNull(groups = {Post.class}, message = "NotNull.Questions.type")
    @Range(min = 1, max = 2, groups = {Post.class}, message = "{Error.Questions.type}")
    private Integer type;
    @NotNull(groups = {Post.class}, message = "NotNull.Questions.displayOrder")
    @Min(groups = {Post.class}, message = "Error.Questions.displayOrder", value = 1)
    private Integer displayOrder;
    
    @NotEmpty(groups = {Post.class}, message = "NotEmpty.Questions.options")
    @Valid
    private List<Options> options;


    private long rightNum;  //这道题 答对的人数
     
    private long allNum;   // 答这道题的总人数 
     
    private String ratio;  //每道题的答对率
    
    private Answers answers ;  // 用户做这道题提交的答案
    
    public Answers getAnswers() {
		return answers;
	}

	public void setAnswers(Answers answers) {
		this.answers = answers;
	}

	public long getRightNum() {
		return rightNum;
	}

	public void setRightNum(long rightNum) {
		this.rightNum = rightNum;
	}

	public long getAllNum() {
		return allNum;
	}

	public void setAllNum(long allNum2) {
		this.allNum = allNum2;
	}

	public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

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

    public List<Options> getOptions() {
        return options;
    }

    public void setOptions(List<Options> options) {
        this.options = options;
    }

	@Override
	public String toString() {
		return "Questions [quizId=" + quizId + ", questionTitle=" + questionTitle + ", type=" + type + ", displayOrder="
				+ displayOrder + ", options=" + options + ", rightNum=" + rightNum + ", allNum=" + allNum + ", ratio="
				+ ratio + ", answers=" + answers + "]";
	}
    
    
}
