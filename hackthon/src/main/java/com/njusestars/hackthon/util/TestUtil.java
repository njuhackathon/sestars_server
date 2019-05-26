package com.njusestars.hackthon.util;

import com.njusestars.hackthon.entity.Classroom;
import com.njusestars.hackthon.entity.Teacher;

import java.util.UUID;

/**
 * @author lzb
 * @date 2019/5/26 15:20
 */
public class TestUtil {

    public static String getRandomString(){
        return UUID.randomUUID().toString();
    }

    public static Teacher getRandomTeacher(){
        String username = TestUtil.getRandomString();
        String realName = TestUtil.getRandomString();
        String password = TestUtil.getRandomString();
        Teacher teacher = new Teacher();
        return teacher;
    }

    public static Classroom getRandomClassroom(){
        Classroom classroom = new Classroom();
        return classroom;
    }

}
