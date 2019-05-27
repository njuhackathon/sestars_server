package com.njusestars.hackthon.vo;

import lombok.Data;

/**
 *
 * <br>
 * created on 2019/5/27
 *
 * @author Xunner
 **/
@Data
public class QuesStaVO {
    Long questionId;
    String title;
    Double score;
    Integer commitCount;
    Integer notCommitCount;
    Double average;
    Double max;
    Double min;
    String imageUrl;

    public QuesStaVO() {
    }

    public QuesStaVO(Long questionId, String title, Double score, Integer commitCount, Integer notCommitCount,
                     Double average, Double max, Double min, String imageUrl) {
        this.questionId = questionId;
        this.title = title;
        this.score = score;
        this.commitCount = commitCount;
        this.notCommitCount = notCommitCount;
        this.average = average;
        this.max = max;
        this.min = min;
        this.imageUrl = imageUrl;
    }
}
