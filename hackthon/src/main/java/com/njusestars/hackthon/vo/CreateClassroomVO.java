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
public class CreateClassroomVO {
    String teacherUsername;
    String classroomName;

    public CreateClassroomVO() {
    }

    public CreateClassroomVO(String teacherUsername, String classroomName) {
        this.teacherUsername = teacherUsername;
        this.classroomName = classroomName;
    }
}
