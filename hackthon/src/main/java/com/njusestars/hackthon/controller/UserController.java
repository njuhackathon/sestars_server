package com.njusestars.hackthon.controller;

import com.njusestars.hackthon.util.ResultMessage;
import com.njusestars.hackthon.vo.LoginVO;
import com.njusestars.hackthon.vo.SignUpVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class UserController {
    @PostMapping(value = "/user/login")
    public ResultMessage login(@RequestBody LoginVO loginVO) {
        return null;
    }

    @PostMapping(value = "/user/sign-up")
    public ResultMessage signUp(@RequestBody SignUpVO signUpVO) {
        return null;
    }
}
