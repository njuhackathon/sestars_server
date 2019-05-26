package com.njusestars.hackthon.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * <br>
 * created on 2019/5/26
 *
 * @author Xunner
 **/
@Data
public class AssignmentVO {
    Long id;
    String title;
    LocalDateTime endDate;
    String teacherUsername;
    List<QuestionVO> questionList;

    public AssignmentVO() {
    }

    public AssignmentVO(Long id, String title, LocalDateTime endDate, String teacherUsername, List<QuestionVO> questionList) {
        this.id = id;
        this.title = title;
        this.endDate = endDate;
        this.teacherUsername = teacherUsername;
        this.questionList = questionList;
    }
}
