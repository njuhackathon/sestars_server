package com.njusestars.hackthon.bl;

import com.njusestars.hackthon.entity.Assignment;
import com.njusestars.hackthon.entity.Classroom;
import com.njusestars.hackthon.entity.Teacher;
import com.njusestars.hackthon.entity.User;
import com.njusestars.hackthon.enums.Result;
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
 * @date 2019/5/26 15:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherBLServiceImplTest {

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

    private Classroom getRandomClassroom(){
        Classroom classroom = TestUtil.getRandomClassroom();
        classroom = teacherBLService.createClassroom(classroom);
        assertNotNull(classroom);
        return classroom;
    }

    @Test
    public void getTeacherByUsername() {
        Teacher teacher = getRandomTeacher();
        assertEquals(teacher,teacherBLService.getTeacherByUsername(teacher.getUsername()));
    }

    @Test
    public void createClassroom() {
        Classroom classroom = getRandomClassroom();
        classroom = teacherBLService.createClassroom(classroom);
        assertNotNull(classroom);
    }

    @Test
    public void joinClassroom() {
        Classroom classroom = this.getRandomClassroom();
        Teacher teacher = this.getRandomTeacher();
        assertNotNull(teacher);
        assertNotNull(classroom);
        teacher = teacherBLService.joinClassroom(classroom,teacher);
        assertNotNull(classroom);

        teacher = teacherBLService.getTeacherByUsername(teacher.getUsername());
        assertNotNull(teacher);

//        assertTrue(teacher.getClassroomSet().contains(classroom));
        //这里判断正确的条件需要重写

    }

    @Test
    public void publishAssignment() {

        Assignment assignment = TestUtil.getRandomAssignment();
        assignment = teacherBLService.publishAssignment(assignment);
        assertNotNull(assignment);



    }

    @Test
    public void updateAssignment() {
    }

    @Test
    public void cancelAssignment() {
        Assignment assignment = TestUtil.getRandomAssignment();
        assertNotNull(assignment);
        assignment =  teacherBLService.publishAssignment(assignment);
        assertNotNull(assignment);
        boolean ifDelete = teacherBLService.cancelAssignment(assignment);
        Assignment result = teacherBLService.getAssignmentById(assignment.getId());
        assertNull(result);

    }

    @Test
    public void getToDoAssignmentList() {
    }

    @Test
    public void getDoneAssignmentList() {
    }
}