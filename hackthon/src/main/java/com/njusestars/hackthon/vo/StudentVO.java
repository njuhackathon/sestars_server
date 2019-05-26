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
public class StudentVO {
    private String username;
    private String realName;
    private Long classroomId;

    public StudentVO() {
    }

    public StudentVO(String username, String realName, Long classroomId) {
        this.username = username;
        this.realName = realName;
        this.classroomId = classroomId;
    }
}
