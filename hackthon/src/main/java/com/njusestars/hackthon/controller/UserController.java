package com.njusestars.hackthon.controller;

import com.njusestars.hackthon.bl.UserBLService;
import com.njusestars.hackthon.entity.User;
import com.njusestars.hackthon.enums.UserType;
import com.njusestars.hackthon.util.ResultMessage;
import com.njusestars.hackthon.vo.LoginVO;
import com.njusestars.hackthon.vo.SignUpVO;
import com.njusestars.hackthon.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户相关Controller
 * <br>
 * created on 2019/5/26
 *
 * @author Xunner
 **/
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
        User user = userBLService.login(loginVO.username, loginVO.password);
        ResultMessage resultMessage;
        if (user != null) {
            int type = UserType.valueOf(user.getClass().getSimpleName().toUpperCase()).ordinal();
            resultMessage = new ResultMessage(null, true, new UserVO(user.getUsername(), user.getRealName(), type));
        } else {
            resultMessage = new ResultMessage("FAILED", false, null);
        }
        return resultMessage;
    }

    @PostMapping(value = "/user/sign-up")
    public ResultMessage signUp(@RequestBody SignUpVO signUpVO) {
        User user = userBLService.register(signUpVO.username, signUpVO.realName, signUpVO.password, UserType.values()[signUpVO.type]);
        ResultMessage resultMessage;
        if (user != null) {
            int type = UserType.valueOf(user.getClass().getSimpleName().toUpperCase()).ordinal();
            resultMessage = new ResultMessage(null, true, new UserVO(user.getUsername(), user.getRealName(), type));
        } else {
            resultMessage = new ResultMessage("NOT_EXIST", false, null);
        }
        return resultMessage;
    }
}
