package com.njusestars.hackthon.bl;

import com.njusestars.hackthon.enums.Result;
import com.njusestars.hackthon.enums.UserType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @author lzb
 * @date 2019/5/26 14:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserBLServiceImplTest {

    @Autowired
    private UserBLService userBLService;


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void loginTeacher() {
        String username = getRandomString();
        String realName = getRandomString();
        String password = getRandomString();
        UserType userType = UserType.TEACHER;
        Result result = this.userBLService.register(username,realName,password,userType);
        assertEquals(result,Result.SUCCESS);

        //正确账号密码登录
        Result loginResult = this.userBLService.login(username,password);
        assertEquals(Result.SUCCESS,loginResult);

        //正确账号，错误密码登录
        assertEquals(Result.FAILED,userBLService.login(username,getRandomString()));

        //错误账号，正确密码
        assertEquals(Result.NOT_EXIST,userBLService.login(getRandomString(),password));

        //错误账号，错误密码
        assertEquals(Result.NOT_EXIST,userBLService.login(getRandomString(),getRandomString()));

    }

    @Test
    public void registerTeacher() {
        String username = getRandomString();
        String realName = getRandomString();
        String password = getRandomString();
        UserType userType = UserType.TEACHER;
        Result result = this.userBLService.register(username,realName,password,userType);
        assertEquals(result,Result.SUCCESS);
    }

    @Test
    public void registerParent() {
        String username = getRandomString();
        String realName = getRandomString();
        String password = getRandomString();
        UserType userType = UserType.PARENT;
        Result result = this.userBLService.register(username,realName,password,userType);
        assertEquals(result,Result.SUCCESS);
    }


    @Test
    public void registerStudent() {
        String username = getRandomString();
        String realName = getRandomString();
        String password = getRandomString();
        UserType userType = UserType.STUDENT;
        Result result = this.userBLService.register(username,realName,password,userType);
        assertEquals(result,Result.SUCCESS);
    }




    private String getRandomString(){
        return UUID.randomUUID().toString();
    }

}