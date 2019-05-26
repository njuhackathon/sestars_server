package com.njusestars.hackthon.vo;

import lombok.Data;

@Data
public class SignUpVO {
    public String username;
    public String realName;
    public String password;
    /** 0:教师，1:学生，2:家长 */
    public int type;

    public SignUpVO() {
    }

    public SignUpVO(String username, String realName, String password, int type) {
        this.username = username;
        this.realName = realName;
        this.password = password;
        this.type = type;
    }
}
