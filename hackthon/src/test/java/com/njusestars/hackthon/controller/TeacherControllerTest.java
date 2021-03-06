package com.njusestars.hackthon.controller;

import com.njusestars.hackthon.dao.ClassroomDao;
import com.njusestars.hackthon.dao.TeacherDao;
import com.njusestars.hackthon.entity.*;
import com.njusestars.hackthon.util.MockUtilService;
import com.njusestars.hackthon.util.ResultMessage;
import com.njusestars.hackthon.vo.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
@Ignore
public class TeacherControllerTest {
    @Autowired
    private TeacherController teacherController;
    @Autowired
    private UserController userController;
    @Autowired
    private MockUtilService mockUtilService;
    @Autowired
    private ClassroomDao classroomDao;
    @Autowired
    private TeacherDao teacherDao;

    SignUpVO teacherVO;

    CreateClassroomVO createClassroomVO;

    @Before
    public void setUp() throws Exception {
        // 注册教师
        teacherVO = new SignUpVO(LocalDateTime.now().toString(),
                LocalDateTime.now().toString(), LocalDateTime.now().toString(), 0);
        userController.signUp(teacherVO);

        // 创建班级
        createClassroomVO = new CreateClassroomVO(teacherVO.username, LocalDateTime.now().toString());
        teacherController.createClassroom(createClassroomVO);
    }

    @Test
    public void createClassroom() {
        // 正常创建
        CreateClassroomVO vo = new CreateClassroomVO(teacherVO.username, LocalDateTime.now().toString());
        ResultMessage resultMessage = teacherController.createClassroom(vo);
        assertTrue(resultMessage.success);
        assertNull(resultMessage.message);
        assertNull(resultMessage.data);

        // 再来一次
        resultMessage = teacherController.createClassroom(vo);
        assertTrue(resultMessage.success);
        assertNull(resultMessage.message);
        assertNull(resultMessage.data);
    }

    @Test
    public void joinClassroom() {
        List<Classroom> ret = classroomDao.findAll();
        assertFalse(ret.isEmpty());
        ResultMessage resultMessage = teacherController.joinClassroom(teacherVO.username, ret.get(0).getId());
        assertTrue(resultMessage.success);
        assertNull(resultMessage.message);
        assertNull(resultMessage.data);
    }

    @Test
    public void getAllClassroom() {
        ResultMessage resultMessage = teacherController.getAllClassroom(teacherVO.username);
        assertTrue(resultMessage.success);
        assertNull(resultMessage.message);
        assertTrue(resultMessage.data instanceof List);
        int before = ((List<ClassroomVO>) resultMessage.data).size();

        // 创建班级
        createClassroomVO = new CreateClassroomVO(teacherVO.username, LocalDateTime.now().toString());
        teacherController.createClassroom(createClassroomVO);

        resultMessage = teacherController.getAllClassroom(teacherVO.username);
        assertTrue(resultMessage.success);
        assertNull(resultMessage.message);
        assertTrue(resultMessage.data instanceof List);
        int after = ((List<ClassroomVO>) resultMessage.data).size();
//        assertEquals(1, after - before);
    }

    @Test
    public void getMyClassroom() {
        // 创建班级
        Classroom classroom = new Classroom();
        classroom.setClassroomName(LocalDateTime.now().toString());
        classroom = classroomDao.save(classroom);

        ResultMessage resultMessage = teacherController.getMyClassroom(teacherVO.username);
        assertTrue(resultMessage.success);
        assertNull(resultMessage.message);
        assertTrue(resultMessage.data instanceof List);
        int before = ((List<ClassroomVO>) resultMessage.data).size();

        Teacher teacher = teacherDao.findById(teacherVO.username).orElse(null);
        Set<Teacher> teacherSet = new HashSet<>();
        teacherSet.add(teacher);
        classroom.setTeacherSet(teacherSet);
        classroomDao.save(classroom);

        resultMessage = teacherController.getMyClassroom(teacherVO.username);
        assertTrue(resultMessage.success);
        assertNull(resultMessage.message);
        assertTrue(resultMessage.data instanceof List);
        List<ClassroomVO> list = (List<ClassroomVO>) resultMessage.data;
        int after = list.size();
        assertEquals(1, after - before);
    }

    @Test
    public void createAssignment() {
        List<Classroom> ret = classroomDao.findAll();
        assertFalse(ret.isEmpty());

        // 正常发布作业
        CreateAssigmentVO createAssigmentVO = new CreateAssigmentVO();
        createAssigmentVO.setTitle("test");
        createAssigmentVO.setEndTime(LocalDateTime.now());
        createAssigmentVO.setTeacherUsername(teacherVO.username);
        List<QuestionVO> questionList = new ArrayList<>();
        questionList.add(new QuestionVO("test", "test.png", 0.0));
        createAssigmentVO.setQuestionList(questionList);
        List<Long> classroomIds = new ArrayList<>();
        classroomIds.add(ret.get(0).getId());
        createAssigmentVO.setClassroomIds(classroomIds);
        ResultMessage resultMessage = teacherController.createAssignment(createAssigmentVO);
        assertTrue(resultMessage.success);
        assertNull(resultMessage.message);
        assertNull(resultMessage.data);

    }

    @Test
    public void getAssignmentPending() {
        Teacher teacher = mockUtilService.getRandomTeacher();

        ResultMessage resultMessage = teacherController.getAssignmentPending(teacher.getUsername());
        assertTrue(resultMessage.success);
        assertNull(resultMessage.message);
        assertTrue(resultMessage.data instanceof List);
        List<AssignmentVO> list = (List<AssignmentVO>) resultMessage.data;
        assertTrue(list.isEmpty());

        mockUtilService.getRandomAssignment(teacher);
        resultMessage = teacherController.getAssignmentPending(teacher.getUsername());

        assertTrue(resultMessage.success);
        assertNull(resultMessage.message);
        assertTrue(resultMessage.data instanceof List);
        list = (List<AssignmentVO>) resultMessage.data;
        assertEquals(1, list.size());
    }

    @Test
    public void getAssignmentTodo() {
        Teacher teacher = mockUtilService.getRandomTeacher();
        ResultMessage resultMessage = teacherController.getAssignmentTodo(teacher.getUsername());
        assertTrue(resultMessage.success);
        assertNull(resultMessage.message);
        assertTrue(resultMessage.data instanceof List);
        List<AssignmentVO> list = (List<AssignmentVO>) resultMessage.data;
        assertTrue(list.isEmpty());

        mockUtilService.getRandomAssignment(2018, teacher);
        resultMessage = teacherController.getAssignmentTodo(teacher.getUsername());

        assertTrue(resultMessage.success);
        assertNull(resultMessage.message);
        assertTrue(resultMessage.data instanceof List);
        list = (List<AssignmentVO>) resultMessage.data;
        assertEquals(1, list.size());
    }

    @Test
    @Ignore
    public void getAssignmentDone() {
        Teacher teacher = mockUtilService.getRandomTeacher();
        ResultMessage resultMessage = teacherController.getAssignmentDone(teacher.getUsername());
        assertTrue(resultMessage.success);
        assertNull(resultMessage.message);
        assertTrue(resultMessage.data instanceof List);
        List<AssignmentVO> list = (List<AssignmentVO>) resultMessage.data;
        assertTrue(list.isEmpty());

        mockUtilService.getRandomAssignment(2018, teacher);
        resultMessage = teacherController.getAssignmentDone(teacher.getUsername());

        assertTrue(resultMessage.success);
        assertNull(resultMessage.message);
        assertTrue(resultMessage.data instanceof List);
        list = (List<AssignmentVO>) resultMessage.data;
        assertEquals(1, list.size());
    }

    @Test
    @Ignore
    public void getAssignment() {
        Assignment assignment = mockUtilService.getRandomAssignment();
        Teacher teacher = assignment.getTeacher();

        Student student = mockUtilService.getRandomStudent();
        mockUtilService.getRandomCommitment(student, assignment);

        ResultMessage resultMessage = teacherController.getAssignment(assignment.getId());
        assertTrue(resultMessage.success);
        assertNull(resultMessage.message);
        assertTrue(resultMessage.data instanceof AssignmentMarkingVO);

        AssignmentMarkingVO assignmentMarkingVO = (AssignmentMarkingVO) resultMessage.data;
        assertEquals(assignment.getId(), assignmentMarkingVO.getId());
        assertEquals(assignment.getTitle(), assignmentMarkingVO.getTitle());
        assertEquals(assignment.getEndDate(), assignmentMarkingVO.getEndDate());
        assertEquals(teacher.getUsername(), assignmentMarkingVO.getTeacherUsername());
        assertEquals(assignment.getQuestionList().size(), assignmentMarkingVO.getQuestionAnswers().size());
    }

    @Test
    @Ignore
    public void markAnswer() {
        Assignment assignment = mockUtilService.getRandomAssignment();
        Student student = mockUtilService.getRandomStudent();
        Commitment commitment = mockUtilService.getRandomCommitment(student, assignment);
        boolean marked = false;
        for (Answer answer : commitment.getAnswerSet()) {
            ResultMessage resultMessage = teacherController.markAnswer(answer.getId(), Math.random() * 100);
            assertTrue(resultMessage.success);
            assertNull(resultMessage.message);
            assertNull(resultMessage.data);
            marked = true;
        }
        assertTrue(marked);
    }
}