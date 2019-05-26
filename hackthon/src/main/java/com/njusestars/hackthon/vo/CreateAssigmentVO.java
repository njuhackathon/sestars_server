package com.njusestars.hackthon.vo;

import com.njusestars.hackthon.entity.Question;
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
public class CreateAssigmentVO {
    String title;
    String teacherUsername;
    List<QuestionVO> questionList;
    LocalDateTime endTime;
    List<Long> classroomIds;
}
