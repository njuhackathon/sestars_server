package com.njusestars.hackthon.controller;

import com.njusestars.hackthon.dao.ClassroomDao;
import com.njusestars.hackthon.dao.StudentDao;
import com.njusestars.hackthon.entity.Assignment;
import com.njusestars.hackthon.entity.Classroom;
import com.njusestars.hackthon.entity.Student;
import com.njusestars.hackthon.util.ResultMessage;
import com.njusestars.hackthon.vo.AssignmentVO;
import com.njusestars.hackthon.vo.CommitmentVO;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

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
public class StudentControllerTest {
    @Autowired
    private StudentController studentController;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ClassroomDao classroomDao;

    private Student student;

    @Before
    public void setUp() throws Exception {
        Classroom classroom = new Classroom();
        classroom.setClassroomName(LocalDateTime.now().toString());
        classroomDao.save(classroom);

        // 注册学生
        student = new Student();
        student.setUsername(LocalDateTime.now().toString());
        student.setRealName(LocalDateTime.now().toString());
        student.setPassword(LocalDateTime.now().toString());
        student.setClassroom(classroom);
        student = studentDao.save(student);
    }

    @Test
    @Ignore
    public void getAllAssignment() {
        ResultMessage resultMessage = studentController.getAllAssignment(student.getUsername());
        assertTrue(resultMessage.success);
        assertTrue(resultMessage.data instanceof List);
        List<AssignmentVO> assignmentVOS = ((List<AssignmentVO>) resultMessage.data);
        assertTrue(assignmentVOS.isEmpty());

        Assignment assignment = new Assignment();

    }

    @Test
    public void getAssignment() {
        ResultMessage resultMessage = studentController.getAssignment(1L);
    }

    @Test
    public void submitCommitment() {
        CommitmentVO commitmentVO = new CommitmentVO();
        ResultMessage resultMessage = studentController.submitCommitment(commitmentVO);
    }

    @Test
    public void getClassroomNotJoin() {
        ResultMessage resultMessage = studentController.getClassroomNotJoin("");
    }

    @Test
    public void getMyClassroom() {
        ResultMessage resultMessage = studentController.getMyClassroom("");
    }

    @Test
    public void joinClassroom() {
        ResultMessage resultMessage = studentController.joinClassroom(student.getUsername(), 1L);
    }
}