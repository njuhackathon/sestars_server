package com.njusestars.hackthon.bl;

import com.njusestars.hackthon.entity.Assignment;
import com.njusestars.hackthon.entity.Classroom;
import com.njusestars.hackthon.entity.Teacher;
import com.njusestars.hackthon.entity.User;
import com.njusestars.hackthon.enums.UserType;
import com.njusestars.hackthon.util.MockUtil;
import org.junit.After;
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
 * @author lzb
 * @date 2019/5/26 15:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
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
        String username = MockUtil.getRandomString();
        String realName = MockUtil.getRandomString();
        String password = MockUtil.getRandomString();
        UserType userType = UserType.TEACHER;
        User result = this.userBLService.register(username,realName,password,userType);
        assertEquals(Teacher.class, result.getClass());

        Teacher teacher = teacherBLService.getTeacherByUsername(username);
        assertNotNull(teacher);

        return teacher;
    }

    private Classroom getRandomClassroom(){
        Classroom classroom = MockUtil.getRandomClassroom();
        classroom = teacherBLService.createClassroom(classroom);
        assertNotNull(classroom);
        return classroom;
    }


    private Assignment getRandomAssignment(Teacher teacher){
        Assignment assignment = MockUtil.getRandomAssignment();
        assignment.setTeacher(teacher);
        assignment = teacherBLService.publishAssignment(assignment);
        return assignment;
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

        Assignment assignment = MockUtil.getRandomAssignment();
        Teacher teacher = this.getRandomTeacher();
        assignment.setTeacher(teacher);
        assignment = teacherBLService.publishAssignment(assignment);
        assertNotNull(assignment);

    }

    @Test
    public void updateAssignment() {
    }

    @Test
    public void cancelAssignment() {
        Assignment assignment = MockUtil.getRandomAssignment();
        assertNotNull(assignment);
        Teacher teacher = this.getRandomTeacher();
        assignment.setTeacher(teacher);
        assignment =  teacherBLService.publishAssignment(assignment);
        assertNotNull(assignment);


        boolean ifDelete = teacherBLService.cancelAssignment(assignment);
        Assignment result = teacherBLService.getAssignmentById(assignment.getId());
        assertNull(result);

    }

    @Test
    public void getToDoAssignmentListEasy() {
        Teacher teacher = this.getRandomTeacher();
        List<Assignment> assignmentList = teacherBLService.getUnfinishedAssignList(teacher);
        assertTrue(assignmentList.size()==0);
    }

    @Test
    public void getToDoAssignmentListHard() {
        Assignment assignment = MockUtil.getRandomAssignment();
        assignment.setTeacher(this.getRandomTeacher());
        assignment = teacherBLService.publishAssignment(assignment);
        List<Assignment> assignmentList = teacherBLService.getUnfinishedAssignList(assignment.getTeacher());
        assertTrue(assignmentList.size()==1);
//        assertTrue(assignmentList.contains(assignment));
    }

    @Test
    public void getDoneAssignmentList() {

        Teacher teacher = this.getRandomTeacher();
        Assignment assignTodo = this.getRandomAssignment(teacher);
        Assignment assignDone = this.getRandomAssignment(teacher);
        Assignment assignDone2 = this.getRandomAssignment(teacher);

        assignDone.setEndDate(LocalDateTime.now());
        assignDone = teacherBLService.updateAssignment(assignDone);
        assignDone2.setEndDate(LocalDateTime.now());
        assignDone2 = teacherBLService.updateAssignment(assignDone2);

        List<Assignment> assignmentList = teacherBLService.getFinishedAssignList(teacher);
        assertTrue(2==assignmentList.size());

    }
}