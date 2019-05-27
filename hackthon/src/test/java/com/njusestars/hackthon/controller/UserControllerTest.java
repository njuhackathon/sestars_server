package com.njusestars.hackthon.controller;

import com.njusestars.hackthon.util.ResultMessage;
import com.njusestars.hackthon.vo.LoginVO;
import com.njusestars.hackthon.vo.SignUpVO;
import com.njusestars.hackthon.vo.UserVO;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * 测试
 * <br>
 * created on 2019/5/26
 *
 * @author Xunner
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class UserControllerTest {
    @Autowired
    private UserController userController;

    @Test
    public void login() {
        // 注册
        SignUpVO signUpVO = new SignUpVO(LocalDateTime.now().toString(),
                LocalDateTime.now().toString(), LocalDateTime.now().toString(), 0);
        ResultMessage resultMessage = userController.signUp(signUpVO);
        assertTrue(resultMessage.success);
        assertTrue(resultMessage.data instanceof UserVO);
        assertEquals(signUpVO.username, ((UserVO) resultMessage.data).getUsername());
        assertEquals(signUpVO.realName, ((UserVO) resultMessage.data).getRealname());
        assertEquals(signUpVO.type, ((UserVO) resultMessage.data).getType());

        // 正常登录
        LoginVO loginVO = new LoginVO(signUpVO.username, signUpVO.password);
        resultMessage = userController.login(loginVO);
        assertTrue(resultMessage.success);

        // 密码错误
        loginVO = new LoginVO(signUpVO.username, LocalDateTime.now().toString());
        resultMessage = userController.login(loginVO);
        assertFalse(resultMessage.success);

        // 用户名不存在
        loginVO = new LoginVO(LocalDateTime.now().toString(), signUpVO.password);
        resultMessage = userController.login(loginVO);
        assertFalse(resultMessage.success);

        // 都不存在
        loginVO = new LoginVO(LocalDateTime.now().toString(), LocalDateTime.now().toString());
        resultMessage = userController.login(loginVO);
        assertFalse(resultMessage.success);
    }

    @Test
    public void signUp() {
        // 正确的
        SignUpVO signUpVO = new SignUpVO(LocalDateTime.now().toString(), LocalDateTime.now().toString(),
                LocalDateTime.now().toString(), 0);
        ResultMessage resultMessage = userController.signUp(signUpVO);
        assertTrue(resultMessage.success);
        assertNull(resultMessage.message);
        assertNotNull(resultMessage.data);
        assertTrue(resultMessage.data instanceof UserVO);
        assertEquals(signUpVO.username, ((UserVO) resultMessage.data).getUsername());
        assertEquals(signUpVO.realName, ((UserVO) resultMessage.data).getRealname());
        assertEquals(signUpVO.type, ((UserVO) resultMessage.data).getType());

        // 用户名已存在
        SignUpVO another = new SignUpVO(signUpVO.username, LocalDateTime.now().toString(),
                LocalDateTime.now().toString(), 0);
        resultMessage = userController.signUp(another);
        assertFalse(resultMessage.success);
        assertEquals("EXIST", resultMessage.message);
        assertNull(resultMessage.data);

        // 用户名存在但用户类型不同
        another = new SignUpVO(signUpVO.username, LocalDateTime.now().toString(),
                LocalDateTime.now().toString(), 1);
        resultMessage = userController.signUp(another);
        assertFalse(resultMessage.success);
        assertEquals("EXIST", resultMessage.message);
        assertNull(resultMessage.data);
    }
}