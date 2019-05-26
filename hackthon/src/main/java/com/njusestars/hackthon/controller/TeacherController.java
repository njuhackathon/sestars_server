package com.njusestars.hackthon.controller;

import com.njusestars.hackthon.bl.TeacherBLService;
import com.njusestars.hackthon.entity.*;
import com.njusestars.hackthon.util.ResultMessage;
import com.njusestars.hackthon.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 教师相关Controller
 * <br>
 * created on 2019/5/26
 *
 * @author Xunner
 **/
@RestController
@CrossOrigin
public class TeacherController {
    private static final String FAILED = "FAILED";
    private final TeacherBLService teacherBLService;

    @Autowired
    public TeacherController(TeacherBLService teacherBLService) {
        this.teacherBLService = teacherBLService;
    }

    @PostMapping(value = "/teacher/classroom/create")
    public ResultMessage createClassroom(@RequestBody CreateClassroomVO createClassroomVO) {
        Classroom classroom = new Classroom();
        classroom.setClassroomName(createClassroomVO.getClassroomName());
        classroom = teacherBLService.createClassroom(classroom);
        if (classroom == null) {
            return new ResultMessage(FAILED, false, null);
        } else {
            return this.joinClassroom(createClassroomVO.getTeacherUsername(), classroom.getId());
        }
    }

    @GetMapping(value = "/teacher/classroom/join")
    public ResultMessage joinClassroom(@RequestParam String teacherUsername, @RequestParam Long classroomId) {
        Teacher teacher = teacherBLService.joinClassroom(teacherBLService.getClassroomById(classroomId), teacherBLService.getTeacherByUsername(teacherUsername));
        if (teacher == null) {
            return new ResultMessage(FAILED, false, null);
        } else {
            return new ResultMessage(null, true, null);
        }
    }

    @GetMapping(value = "/teacher/classroom/get-all")
    public ResultMessage getAllClassroom(@RequestParam String teacherUsername) {
        List<Classroom> classrooms = teacherBLService.getAllClassroom();
        List<ClassroomVO> classroomVOS = new ArrayList<>();
        for (Classroom classroom : classrooms) {
            ClassroomVO classroomVO = new ClassroomVO(classroom.getId(), classroom.getClassroomName());
            for (Teacher teacher : classroom.getTeacherSet()) {
                if (teacherUsername.equals(teacher.getUsername())) {
                    classroomVO.setJoined(true);
                    break;
                }
            }
            classroomVOS.add(classroomVO);
        }
        return new ResultMessage(null, true, classroomVOS);
    }

    @GetMapping(value = "/teacher/classroom/my")
    public ResultMessage getMyClassroom(@RequestParam String teacherUsername) {
        List<Classroom> classrooms = teacherBLService.getAllClassroom();
        List<ClassroomVO> classroomVOS = new ArrayList<>();
        for (Classroom classroom : classrooms) {
            for (Teacher teacher : classroom.getTeacherSet()) {
                if (teacherUsername.equals(teacher.getUsername())) {
                    ClassroomVO classroomVO = new ClassroomVO(classroom.getId(), classroom.getClassroomName());
                    classroomVO.setJoined(true);
                    classroomVOS.add(classroomVO);
                    break;
                }
            }
        }
        return new ResultMessage(null, true, classroomVOS);
    }

    @PostMapping(value = "/teacher/assignment/create")
    public ResultMessage createAssignment(@RequestBody CreateAssigmentVO createAssigmentVO) {
        Teacher teacher = teacherBLService.getTeacherByUsername(createAssigmentVO.getTeacherUsername());
        Assignment assignment = new Assignment();
        assignment.setTitle(createAssigmentVO.getTitle());
        assignment.setTeacher(teacher);
        assignment.setEndDate(createAssigmentVO.getEndTime());
        Set<Question> questionSet = new HashSet<>();
        for (QuestionVO questionVO : createAssigmentVO.getQuestionList()) {
            questionSet.add(new Question(questionVO.getTitle(), questionVO.getImageUrl(), assignment));
        }
        assignment.setQuestionSet(questionSet);
        assignment = teacherBLService.publishAssignment(assignment);
        if (assignment == null) {
            return new ResultMessage(FAILED, false, null);
        } else {
            return new ResultMessage(null, true, null);
        }
    }

}
