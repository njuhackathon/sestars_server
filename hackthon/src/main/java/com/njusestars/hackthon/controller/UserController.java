package com.njusestars.hackthon.controller;

import com.njusestars.hackthon.bl.UserBLService;
import com.njusestars.hackthon.enums.Result;
import com.njusestars.hackthon.util.ResultMessage;
import com.njusestars.hackthon.vo.LoginVO;
import com.njusestars.hackthon.vo.SignUpVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {
    private final UserBLService userBLService;

    @Autowired
    public UserController(UserBLService userBLService) {
        this.userBLService = userBLService;
    }

    @PostMapping(value = "/user/login")
    public ResultMessage login(@RequestBody LoginVO loginVO) {
        Result result = userBLService.login(loginVO.username, loginVO.password);
        switch (result) {
            case SUCCESS:
                break;
            case FAILED:
                break;
            case NOT_EXIST:
                break;
            default:
                System.out.println("ERROR：未处理的Enum类型：" + result);
        }
        return null;
    }

    @PostMapping(value = "/user/sign-up")
    public ResultMessage signUp(@RequestBody SignUpVO signUpVO) {
        return null;
    }
}
