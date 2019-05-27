package com.njusestars.hackthon.bl;

import com.njusestars.hackthon.entity.*;
import com.njusestars.hackthon.enums.UserType;
import com.njusestars.hackthon.util.MockUtil;
import com.njusestars.hackthon.util.MockUtilService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author lzb
 * @date 2019/5/26 22:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentBLServiceImplTest {

    @Autowired
    private MockUtilService mockUtilService;

    @Autowired
    private StudentBLService studentBLService;

    @Autowired
    private TeacherBLService teacherBLService;

    @Autowired
    private UserBLService userBLService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }




    private Commitment getRandomCommitment(){


        //1.创建一个老师
        Teacher teacher = this.mockUtilService.getRandomTeacher();
        //2.创建一个学生
        Student student = this.mockUtilService.getRandomStudent();
        //3.老师创建一个班级
        Classroom classroom = this.mockUtilService.getRandomClassroom();
        //4.学生加入班级
        student = this.studentBLService.joinClassroom(classroom.getId(),student.getUsername());
        //5.老师发布一个作业
        Assignment assignment = this.mockUtilService.getRandomAssignment();
        //6.学生完成这个作业并提交
        Commitment commitment = new Commitment();
        commitment.setStudent(student);
        commitment.setSubmitTime(LocalDateTime.now());
        commitment.setAssignment(assignment);
        commitment.getAnswerSet();
        //TODO 还需要添加
        return null;

    }



    @Test
    public void commitAssignment() {
        //组装assignment
        Assignment assignment = this.mockUtilService.getRandomAssignment();

        //组装commitment
        Commitment commitment = new Commitment();
        commitment.setAssignment(assignment);
        commitment.setStudent(null);
        commitment.setSubmitTime(LocalDateTime.now());

        Set<Answer> answerSet = new HashSet<>();
        answerSet.add(null);
        commitment.setAnswerSet(null);

//        studentBLService.commitAssignment()

    }

    @Test
    public void getToDoAssignmentList() {

    }

    @Test
    public void getStudentByUsername() {
    }

    @Test
    public void getQuestionById() {
    }

    @Test
    public void joinClassroom() {
    }
}