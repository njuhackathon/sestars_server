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
public class UserVO {
    String username;
    String realname;
    /** 0:教师，1:学生，2:家长 */
    int type;

    public UserVO() {
    }

    public UserVO(String username, String realname, int type) {
        this.username = username;
        this.realname = realname;
        this.type = type;
    }
}
