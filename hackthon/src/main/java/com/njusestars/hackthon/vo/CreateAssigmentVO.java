package com.njusestars.hackthon.vo;

import com.njusestars.hackthon.entity.Question;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * <br>
 * created on 2019/5/26
 *
 * @author Xunner
 **/
public class CreateAssigmentVO {
    String title;
    Long teacherId;
    List<Question> questionList;
    LocalDateTime endTime;
}
