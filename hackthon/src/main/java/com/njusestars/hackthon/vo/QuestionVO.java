package com.njusestars.hackthon.vo;

import lombok.Data;

/**
 *
 * <br>
 * created on 2019/5/26
 *
 * @author Xunner
 **/
@Data
public class QuestionVO {
    Long id;
    String title;
    String imageUrl;
    Double score;

    public QuestionVO() {
    }

    public QuestionVO(String title, String imageUrl, Double score) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.score = score;
    }

    public QuestionVO(Long id, String title, String imageUrl, Double score) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.score = score;
    }
}

