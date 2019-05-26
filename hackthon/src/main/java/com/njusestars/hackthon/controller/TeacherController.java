package com.njusestars.hackthon.controller;

import com.njusestars.hackthon.bl.TeacherBLService;
import com.njusestars.hackthon.util.ResultMessage;
import com.njusestars.hackthon.vo.CreateAssigmentVO;
import com.njusestars.hackthon.vo.CreateClassroomVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return null;
    }

    @GetMapping(value = "/teacher/classroom/join")
    public ResultMessage joinClassroom(@RequestParam Long teacherId, @RequestParam Long classroomId) {
        return null;
    }

    @GetMapping(value = "/teacher/classroom/get-all")
    public ResultMessage getAllClassroom() {
        return null;
    }

    @GetMapping(value = "/teacher/classroom/my")
    public ResultMessage getMyClassroom() {
        return null;
    }

    @PostMapping(value = "/teacher/assignment/create")
    public ResultMessage createAssignment(@RequestBody CreateAssigmentVO createAssigmentVO) {
        return null;
    }

}
