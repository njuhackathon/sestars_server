package com.njusestars.hackthon.controller;

import com.njusestars.hackthon.bl.TeacherBLService;
import com.njusestars.hackthon.entity.Assignment;
import com.njusestars.hackthon.entity.Classroom;
import com.njusestars.hackthon.entity.Teacher;
import com.njusestars.hackthon.util.ResultMessage;
import com.njusestars.hackthon.vo.CreateAssigmentVO;
import com.njusestars.hackthon.vo.CreateClassroomVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            return new ResultMessage("FAILED", false, null);
        } else {
            return this.joinClassroom(createClassroomVO.getTeacherUsername(), classroom.getId());
        }
    }

    @GetMapping(value = "/teacher/classroom/join")
    public ResultMessage joinClassroom(@RequestParam String teacherUsername, @RequestParam Long classroomId) {
//        Teacher teacher = teacherBLService.joinClassroom(classroomId, teacherUsername);
//        if (teacher == null) {
//            return new ResultMessage("FAILED", false, null);
//        } else {
//            return new ResultMessage(null, true, null);
//        }
        return null;
    }

    @GetMapping(value = "/teacher/classroom/get-all")
    public ResultMessage getAllClassroom() {
        List<Classroom> classrooms = teacherBLService.getAllClassroom();
//        List<ClassroomVO>
        if (classrooms == null) {
            return new ResultMessage("FAILED", false, null);
        } else {
            return new ResultMessage(null, true, classrooms);
        }
    }

    @GetMapping(value = "/teacher/classroom/my")
    public ResultMessage getMyClassroom() {
        return null;
    }

    @PostMapping(value = "/teacher/assignment/create")
    public ResultMessage createAssignment(@RequestBody CreateAssigmentVO createAssigmentVO) {
        Assignment assignment = new Assignment();
        return null;
    }

}
