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
public class ClassroomVO {
    Long id;
    String classroomName;
    Boolean joined;

//    private List<StudentVO> studentSet;
//
//    private List<AssignmentVO> assignmentSet;


    public ClassroomVO() {
    }

    public ClassroomVO(Long id, String classroomName) {
        this.id = id;
        this.classroomName = classroomName;
    }
}
