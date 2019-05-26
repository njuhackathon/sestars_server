package com.njusestars.hackthon.controller;

import com.njusestars.hackthon.bl.StudentBLService;
import com.njusestars.hackthon.util.ResultMessage;
import com.njusestars.hackthon.vo.AssignmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 学生相关Controller
 * <br>
 * created on 2019/5/26
 *
 * @author Xunner
 **/
@RestController
@CrossOrigin
public class StudentController {
    private final StudentBLService studentBLService;

    @Autowired
    public StudentController(StudentBLService studentBLService) {
        this.studentBLService = studentBLService;
    }

    @GetMapping(value = "/student/assignment/all")
    public ResultMessage getAllAssignment(@RequestParam Long studentId) {
        return null;
    }

    @GetMapping(value = "/student/assignment/")
    public ResultMessage getAssignment(@RequestParam Long assignmentId) {
        return null;
    }

    @PostMapping(value = "/student/assignment/")
    public ResultMessage submitAssignment(@RequestBody AssignmentVO assignmentVO) {
        return null;
    }
}
