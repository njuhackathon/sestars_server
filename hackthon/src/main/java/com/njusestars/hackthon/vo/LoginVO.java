package com.njusestars.hackthon.vo;

import lombok.Data;

@Data
public class LoginVO {
    public String username;
    public String password;

    public LoginVO() {
    }

    public LoginVO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
