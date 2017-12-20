package com.mifan.quiz.domain;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.moonframework.model.mybatis.domain.BaseEntity;
import org.moonframework.validation.ValidationGroups.Post;

/**
 * @author ZYW
 * @version 1.0
 * @since 2017-12-12
 */
public class Answers extends BaseEntity {

    public static final String TABLE_NAME = "answers";

    public static final String SESSION_ID = "session_id";
    public static final String QUESTION_ID = "question_id";
    public static final String ANSWERS = "answers";
    public static final String IS_RIGHT = "is_right";

    private static final long serialVersionUID = 3763884033459856899L;

    private Long sessionId;
    @NotNull(groups = {Post.class}, message = "{MustNull.QuizSession.questionId}")
    private Long questionId;
    @NotNull(groups = {Post.class}, message = "{MustNull.QuizSession.answers}")
    private String answers;
    private Integer isRight;
    
    private String sessionCode ;  //随机码
    
    private Integer allDone ; //是否全部答完
    
    @NotEmpty(groups = {Post.class}, message = "NotEmpty.QuizSession.answersList")
    private List<Long> answersList ;  //用户提交的答案
    
    public Integer getAllDone() {
		return allDone;
	}

	public void setAllDone(Integer allDone) {
		this.allDone = allDone;
	}

	public List<Long> getAnswersList() {
		return answersList;
	}

	public void setAnswersList(List<Long> answersList) {
		this.answersList = answersList;
	}

	public String getSessionCode() {
		return sessionCode;
	}

	public void setSessionCode(String sessionCode) {
		this.sessionCode = sessionCode;
	}

    public Answers() {
    }

    public Answers(Long id) {
        super(id);
    }

    /**
     * @return 
     */
    public Long getSessionId() {
        return sessionId;
    }

    /**
     * @param sessionId 
     */
    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }
    /**
     * @return 问题标识
     */
    public Long getQuestionId() {
        return questionId;
    }

    /**
     * @param questionId 问题标识
     */
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
    /**
     * @return 用户提交的答案
     */
    public String getAnswers() {
        return answers;
    }

    /**
     * @param answers 用户提交的答案
     */
    public void setAnswers(String answers) {
        this.answers = answers;
    }
    /**
     * @return 是否答对 0：否  1：是
     */
    public Integer getIsRight() {
        return isRight;
    }

    /**
     * @param isRight 是否答对 0：否  1：是
     */
    public void setIsRight(Integer isRight) {
        this.isRight = isRight;
    }

}
