package com.njusestars.hackthon.bl;

import com.njusestars.hackthon.entity.Assignment;
import com.njusestars.hackthon.entity.Teacher;
import com.njusestars.hackthon.entity.User;
import com.njusestars.hackthon.enums.UserType;
import com.njusestars.hackthon.util.TestUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author lzb
 * @date 2019/5/26 22:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentBLServiceImplTest {

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

    private Teacher getRandomTeacher(){
        String username = TestUtil.getRandomString();
        String realName = TestUtil.getRandomString();
        String password = TestUtil.getRandomString();
        UserType userType = UserType.TEACHER;
        User result = this.userBLService.register(username,realName,password,userType);
        assertEquals(Teacher.class, result.getClass());

        Teacher teacher = teacherBLService.getTeacherByUsername(username);
        assertNotNull(teacher);

        return teacher;
    }

    @Test
    public void commitAssignment() {
        Assignment assignment = TestUtil.getRandomAssignment();
        Teacher teacher = this.getRandomTeacher();
        assignment.setTeacher(teacher);
        assignment = teacherBLService.publishAssignment(assignment);


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