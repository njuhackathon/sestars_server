package com.njusestars.hackthon.util;

import com.njusestars.hackthon.bl.StudentBLService;
import com.njusestars.hackthon.bl.TeacherBLService;
import com.njusestars.hackthon.bl.UserBLService;
import com.njusestars.hackthon.entity.*;
import com.njusestars.hackthon.enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

/**
 * @author lzb
 * @date 2019/5/27 0:35
 */
@Service
public class MockUtilService {

    @Autowired
    private UserBLService userBLService;

    @Autowired
    private TeacherBLService teacherBLService;

    @Autowired
    private StudentBLService studentBLService;

    public String getRandomString(){
        return UUID.randomUUID().toString();
    }

    public Teacher getRandomTeacher(){
        String username = MockUtil.getRandomString();
        String realName = MockUtil.getRandomString();
        String password = MockUtil.getRandomString();
        UserType userType = UserType.TEACHER;
        User result = this.userBLService.register(username,realName,password,userType);

        Teacher teacher = teacherBLService.getTeacherByUsername(username);
        assertNotNull(teacher);

        return teacher;
    }

    public Student getRandomStudent(){
        String username = MockUtil.getRandomString();
        String realName = MockUtil.getRandomString();
        String password = MockUtil.getRandomString();
        UserType userType = UserType.STUDENT;
        User result = this.userBLService.register(username,realName,password,userType);

        Student student = studentBLService.getStudentByUsername(username);
        return student;
    }

    public Classroom getRandomClassroom(){
        Classroom classroom = new Classroom();
        classroom.setClassroomName(this.getRandomString());
        classroom = this.teacherBLService.createClassroom(classroom);
        return classroom;
    }

    public Assignment getRandomAssignment(){
        Assignment assignment = new Assignment();
        assignment.setTitle(getRandomString());
        assignment.setBeginDate(LocalDateTime.now());
        assignment.setEndDate(LocalDateTime.of(2021, Month.APRIL,9,14,0));

        Teacher teacher = this.getRandomTeacher();
        assignment.setTeacher(teacher);
        assignment = this.teacherBLService.publishAssignment(assignment);
        return assignment;
    }

    public Assignment getRandomAssignment(Teacher teacher){
        Assignment assignment = new Assignment();
        assignment.setTitle(getRandomString());
        assignment.setBeginDate(LocalDateTime.now());
        assignment.setEndDate(LocalDateTime.of(2021, Month.APRIL,9,14,0));

        assignment.setTeacher(teacher);
        assignment = this.teacherBLService.publishAssignment(assignment);
        return assignment;
    }



}
