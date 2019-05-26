package com.njusestars.hackthon.controller;

import com.njusestars.hackthon.util.ResultMessage;
import com.njusestars.hackthon.vo.CreateClassroomVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
public class TeacherControllerTest {
    @Autowired
    private TeacherController teacherController;

    @Test
    public void createClassroom() {
        CreateClassroomVO createClassroomVO = new CreateClassroomVO("", "Grade 1");
//        ResultMessage resultMessage = teacherController.createClassroom();
    }

    @Test
    public void joinClassroom() {
    }

    @Test
    public void getAllClassroom() {
    }

    @Test
    public void getMyClassroom() {
    }

    @Test
    public void createAssignment() {
    }
}