package com.njusestars.hackthon.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * <br>
 * created on 2019/5/27
 *
 * @author Xunner
 **/
@Data
public class AssignStaVO {
    Long assignmentId;
    String title;
    LocalDateTime endTime;
    Integer commitCount;
    Double average;
    Double max;
    Double min;
    Integer rank;
    List<QuesStaVO> questions;

    public AssignStaVO() {
    }

    public AssignStaVO(Long assignmentId, String title, LocalDateTime endTime, Integer commitCount, Double average,
                       Double max, Double min, Integer rank, List<QuesStaVO> questions) {
        this.assignmentId = assignmentId;
        this.title = title;
        this.endTime = endTime;
        this.commitCount = commitCount;
        this.average = average;
        this.max = max;
        this.min = min;
        this.questions = questions;
    }
}
