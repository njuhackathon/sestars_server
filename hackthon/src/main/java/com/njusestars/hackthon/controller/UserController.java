package com.njusestars.hackthon.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import util.ResultMessage;
import vo.LoginVO;

public class UserController {
    @PostMapping(value = "/user/login")
    public ResultMessage login(@RequestBody LoginVO loginVO) {
        return null;
    }
    @PostMapping(value = "/user/sign-up")
    public ResultMessage signUp(@RequestBody LoginVO loginVO) {
        return null;
    }
}
