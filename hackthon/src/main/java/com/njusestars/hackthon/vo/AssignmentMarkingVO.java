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
public class AssignmentMarkingVO {
    Long id;
    String title;
    LocalDateTime endDate;
    String teacherUsername;
    List<QuestionAnswersVO> questionAnswers;

    public AssignmentMarkingVO() {
    }

    public AssignmentMarkingVO(Long id, String title, LocalDateTime endDate, String teacherUsername) {
        this.id = id;
        this.title = title;
        this.endDate = endDate;
        this.teacherUsername = teacherUsername;
    }
}
