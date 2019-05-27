package com.njusestars.hackthon.vo;

import lombok.Data;

import java.util.List;

/**
 *
 * <br>
 * created on 2019/5/26
 *
 * @author Xunner
 **/
@Data
public class AnswerVO {
    Long answerId;
    Long questionId;
    String text;
    String studentRealName;
    List<String> imageUrls;

    public AnswerVO() {
    }

    public AnswerVO(Long answerId, Long questionId, String text, String studentRealName, List<String> imageUrls) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.text = text;
        this.studentRealName = studentRealName;
        this.imageUrls = imageUrls;
    }
}
