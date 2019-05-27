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
public class QuesTeaStaVO {
    Long questionId;
    String title;
    Double score;
    Integer commitCount;
    Integer notCommitCount;
    Double average;
    Double max;
    Double min;

    public QuesTeaStaVO() {
    }

    public QuesTeaStaVO(Long questionId, String title, Double score, Integer commitCount, Integer notCommitCount, Double average, Double max, Double min) {
        this.questionId = questionId;
        this.title = title;
        this.score = score;
        this.commitCount = commitCount;
        this.notCommitCount = notCommitCount;
        this.average = average;
        this.max = max;
        this.min = min;
    }
}
