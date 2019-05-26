package com.njusestars.hackthon.vo;

import lombok.Data;

@Data
public class SignUpVO {
    public String username;
    public String realname;
    public String password;
    /** 0:教师，1:学生，2:家长 */
    public int type;
}
