package com.njusestars.hackthon.bl;

import com.njusestars.hackthon.entity.Parent;
import com.njusestars.hackthon.entity.Student;
import com.njusestars.hackthon.entity.Teacher;
import com.njusestars.hackthon.entity.User;
import com.njusestars.hackthon.enums.UserType;
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


    @Test
    public void loginTeacher() {
        String username = getRandomString();
        String realName = getRandomString();
        String password = getRandomString();
        UserType userType = UserType.TEACHER;
        User user = this.userBLService.register(username,realName,password,userType);
        assertEquals(Teacher.class, user.getClass());

        //正确账号密码登录
        User loginResult = this.userBLService.login(username,password);
        assertEquals(Teacher.class,loginResult.getClass());

        //正确账号，错误密码登录
        assertNull(userBLService.login(username, getRandomString()));

        //错误账号，正确密码
        assertNull(userBLService.login(getRandomString(), password));

        //错误账号，错误密码
        assertNull(userBLService.login(getRandomString(), getRandomString()));

    }

    @Test
    public void loginStudent() {
        String username = getRandomString();
        String realName = getRandomString();
        String password = getRandomString();
        UserType userType = UserType.STUDENT;
        User result = this.userBLService.register(username,realName,password,userType);
        assertEquals(Student.class, result.getClass());

        //正确账号密码登录
        User loginResult = this.userBLService.login(username,password);
        assertEquals(Student.class,loginResult.getClass());

        //正确账号，错误密码登录
        assertNull(userBLService.login(username,getRandomString()));

        //错误账号，正确密码
        assertNull(userBLService.login(getRandomString(),password));

        //错误账号，错误密码
        assertNull(userBLService.login(getRandomString(),getRandomString()));

    }

    @Test
    public void loginParent() {
        String username = getRandomString();
        String realName = getRandomString();
        String password = getRandomString();
        UserType userType = UserType.PARENT;
        User result = this.userBLService.register(username,realName,password,userType);
        assertEquals(Parent.class, result.getClass());

        //正确账号密码登录
        User loginResult = this.userBLService.login(username,password);
        assertEquals(Parent.class,loginResult.getClass());

        //正确账号，错误密码登录
        assertNull(userBLService.login(username,getRandomString()));

        //错误账号，正确密码
        assertNull(userBLService.login(getRandomString(),password));

        //错误账号，错误密码
        assertNull(userBLService.login(getRandomString(),getRandomString()));

    }

    @Test
    public void registerTeacher() {
        String username = getRandomString();
        String realName = getRandomString();
        String password = getRandomString();
        UserType userType = UserType.TEACHER;
        User result = this.userBLService.register(username,realName,password,userType);
        assertEquals(Teacher.class, result.getClass());
    }

    @Test
    public void registerParent() {
        String username = getRandomString();
        String realName = getRandomString();
        String password = getRandomString();
        UserType userType = UserType.PARENT;
        User result = this.userBLService.register(username,realName,password,userType);
        assertEquals(Parent.class, result.getClass());
    }


    @Test
    public void registerStudent() {
        String username = getRandomString();
        String realName = getRandomString();
        String password = getRandomString();
        UserType userType = UserType.STUDENT;
        User result = this.userBLService.register(username,realName,password,userType);
        assertEquals(Student.class, result.getClass());
    }




    private String getRandomString(){
        return UUID.randomUUID().toString();
    }

}