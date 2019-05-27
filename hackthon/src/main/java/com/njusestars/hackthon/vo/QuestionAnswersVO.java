package com.njusestars.hackthon.vo;

import lombok.Data;

import java.util.List;

/**
 *
 * <br>
 * created on 2019/5/27
 *
 * @author Xunner
 **/
@Data
public class QuestionAnswersVO {
    QuestionVO question;
    List<AnswerVO> answers;

    public QuestionAnswersVO() {
    }

    public QuestionAnswersVO(QuestionVO question, List<AnswerVO> answers) {
        this.question = question;
        this.answers = answers;
    }
}
