package com.njusestars.hackthon.controller;

import com.njusestars.hackthon.dao.ClassroomDao;
import com.njusestars.hackthon.entity.Classroom;
import com.njusestars.hackthon.util.ResultMessage;
import com.njusestars.hackthon.vo.*;
import org.junit.Before;
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
public class TeacherControllerTest {
    @Autowired
    private TeacherController teacherController;
    @Autowired
    private UserController userController;
    @Autowired
    private ClassroomDao classroomDao;

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
        assertEquals(1, after - before);
    }

    @Test
    public void getMyClassroom() {
        ResultMessage resultMessage = teacherController.getMyClassroom(teacherVO.username);
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
        questionList.add(new QuestionVO("test", "test.png"));
        createAssigmentVO.setQuestionList(questionList);
        List<Long> classroomIds = new ArrayList<>();
        classroomIds.add(ret.get(0).getId());
        createAssigmentVO.setClassroomIds(classroomIds);
        ResultMessage resultMessage = teacherController.createAssignment(createAssigmentVO);
        assertTrue(resultMessage.success);
        assertNull(resultMessage.message);
        assertNull(resultMessage.data);

    }
}