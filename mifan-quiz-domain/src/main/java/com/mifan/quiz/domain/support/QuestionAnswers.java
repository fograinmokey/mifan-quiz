package com.mifan.quiz.domain.support;

/**
 * @author ZYW
 *
 */
public class QuestionAnswers {
    
    private Long questionId;
    
    private String questionTitle;
    
    private String answers;
    
    public QuestionAnswers() {
        super();
    }
    
    public QuestionAnswers(Long questionId, String questionTitle) {
        super();
        this.questionId = questionId;
        this.questionTitle = questionTitle;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }
}
