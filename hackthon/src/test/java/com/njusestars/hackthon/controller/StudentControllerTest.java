package com.njusestars.hackthon.controller;

import com.njusestars.hackthon.dao.ClassroomDao;
import com.njusestars.hackthon.dao.StudentDao;
import com.njusestars.hackthon.entity.*;
import com.njusestars.hackthon.util.MockUtilService;
import com.njusestars.hackthon.util.ResultMessage;
import com.njusestars.hackthon.vo.AnswerVO;
import com.njusestars.hackthon.vo.AssignmentVO;
import com.njusestars.hackthon.vo.ClassroomVO;
import com.njusestars.hackthon.vo.CommitmentVO;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @Autowired
    private MockUtilService mockUtilService;

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
    public void getAllAssignment() {
        ResultMessage resultMessage = studentController.getAllAssignment(student.getUsername());
        assertTrue(resultMessage.success);
        assertTrue(resultMessage.data instanceof List);
        List<AssignmentVO> assignmentVOS = ((List<AssignmentVO>) resultMessage.data);
        assertTrue(assignmentVOS.isEmpty());
    }

    @Test
    public void getAssignment() {
        Assignment assignment = mockUtilService.getRandomAssignment();
        ResultMessage resultMessage = studentController.getAssignment(assignment.getId());
        assertTrue(resultMessage.success);
        assertTrue(resultMessage.data instanceof AssignmentVO);
        AssignmentVO assignmentVOS = ((AssignmentVO) resultMessage.data);
        assertNotNull(assignmentVOS);
    }

    @Test
    @Ignore
    public void submitCommitment() {
//        Assignment assignment = mockUtilService.getRandomAssignment();
//        Commitment commitment = mockUtilService.getRandomCommitment(student, assignment);
//        List<AnswerVO> answerList = new ArrayList<>();
//        for (Answer answer : commitment.getAnswerSet()) {
////            answerList.add(new AnswerVO(null, ));
//        }
//        CommitmentVO commitmentVO = new CommitmentVO(student.getUsername(), assignment.getId(), commitment.getAnswerSet());
//        ResultMessage resultMessage = studentController.submitCommitment(commitmentVO);
//        assertTrue(resultMessage.success);
//        assertNull(resultMessage.message);
//        assertNull(resultMessage.data);
    }

    @Test
    public void getClassroomNotJoin() {
        mockUtilService.getRandomClassroom();
        ResultMessage resultMessage = studentController.getClassroomNotJoin(student.getUsername());
        assertTrue(resultMessage.success);
        assertTrue(resultMessage.data instanceof List);
        List<ClassroomVO> classroomVOS = ((List<ClassroomVO>) resultMessage.data);
        assertFalse(classroomVOS.isEmpty());
    }

    @Test
    public void getMyClassroom() {
        ResultMessage resultMessage = studentController.getMyClassroom(student.getUsername());
        assertTrue(resultMessage.success);
        assertTrue(resultMessage.data instanceof ClassroomVO);
        ClassroomVO classroomVO = (ClassroomVO) resultMessage.data;
        assertEquals(student.getClassroom().getId(), classroomVO.getId());
    }

    @Test
    public void joinClassroom() {
        Long id = student.getClassroom().getId();
        Classroom classroom = mockUtilService.getRandomClassroom();
        ResultMessage resultMessage = studentController.joinClassroom(student.getUsername(), classroom.getId());
        assertTrue(resultMessage.success);
        assertTrue(resultMessage.data instanceof Long);
        assertNotEquals((Long) resultMessage.data, id);
    }
}