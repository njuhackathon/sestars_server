package com.njusestars.hackthon.util;

import com.njusestars.hackthon.entity.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

/**
 * @author lzb
 * @date 2019/5/26 15:20
 */
public class MockUtil {

    public static String getRandomString(){
        return UUID.randomUUID().toString();
    }

    public static Teacher getRandomTeacher(){
        String username = MockUtil.getRandomString();
        String realName = MockUtil.getRandomString();
        String password = MockUtil.getRandomString();
        Teacher teacher = new Teacher();
        teacher.setUsername(username);
        teacher.setRealName(realName);
        teacher.setPassword(password);
        return teacher;
    }

    public static Classroom getRandomClassroom(){
        Classroom classroom = new Classroom();
        return classroom;
    }

    public static Assignment getRandomAssignment(){
        Assignment assignment = new Assignment();
        assignment.setTitle(getRandomString());
        assignment.setBeginDate(LocalDateTime.now());
        assignment.setEndDate(LocalDateTime.of(2020, Month.APRIL,9,14,0));
        return assignment;
    }


}
