package com.njusestars.hackthon.controller;

import com.njusestars.hackthon.bl.StudentBLService;
import com.njusestars.hackthon.bl.TeacherBLService;
import com.njusestars.hackthon.entity.*;
import com.njusestars.hackthon.util.ResultMessage;
import com.njusestars.hackthon.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private static final String FAILED = "FAILED";
    private final StudentBLService studentBLService;
    private final TeacherBLService teacherBLService;

    @Autowired
    public StudentController(StudentBLService studentBLService, TeacherBLService teacherBLService) {
        this.studentBLService = studentBLService;
        this.teacherBLService = teacherBLService;
    }

    @GetMapping(value = "/student/assignment/all")
    public ResultMessage getAllAssignment(@RequestParam String studentUsername) {
        Student student = studentBLService.getStudentByUsername(studentUsername);
        List<AssignmentVO> assignmentVOS = new ArrayList<>();
        for (Assignment assignment : studentBLService.getToDoAssignmentList(student)) {
            assignmentVOS.add(this.toAssignmentVO(assignment));
        }
        return new ResultMessage(null, true, assignmentVOS);
    }

    @GetMapping(value = "/student/assignment/")
    public ResultMessage getAssignment(@RequestParam Long assignmentId) {
        AssignmentVO assignmentVO = this.toAssignmentVO(teacherBLService.getAssignmentById(assignmentId));
        if (assignmentVO == null) {
            return new ResultMessage(FAILED, false, null);
        } else {
            return new ResultMessage(null, true, assignmentVO);
        }
    }

    @PostMapping(value = "/student/commitment/")
    public ResultMessage submitCommitment(@RequestBody CommitmentVO commitmentVO) {
        Commitment commitment = new Commitment();

        commitment.setStudent(studentBLService.getStudentByUsername(commitmentVO.getStudentUsername()));
        commitment.setAssignment(teacherBLService.getAssignmentById(commitmentVO.getAssignmentId()));
        commitment.setSubmitTime(LocalDateTime.now());
        Set<Answer> answerSet = new HashSet<>();
        for (AnswerVO answerVO : commitmentVO.getAnswerList()) {
            answerSet.add(this.toAnswer(answerVO, commitment));
        }
        commitment.setAnswerSet(answerSet);

        commitment = studentBLService.commitAssignment(commitment);
        if (commitment == null) {
            return new ResultMessage(FAILED, false, null);
        } else {
            return new ResultMessage(null, true, null);
        }
    }

    @GetMapping(value = "/student/classroom/not-join")
    public ResultMessage getClassroomNotJoin(@RequestParam String studentUsername) {
        List<Classroom> classrooms = teacherBLService.getAllClassroom();
        List<ClassroomVO> classroomVOS = new ArrayList<>();
        for (Classroom classroom : classrooms) {
            boolean joined = false;
            for (Student student : classroom.getStudentSet()) {
                if (studentUsername.equals(student.getUsername())) {
                    joined = true;
                    break;
                }
            }
            if (!joined) {
                ClassroomVO classroomVO = new ClassroomVO(classroom.getId(), classroom.getClassroomName());
                classroomVO.setJoined(true);
                classroomVOS.add(classroomVO);
            }
        }
        return new ResultMessage(null, true, classroomVOS);
    }

    @GetMapping(value = "/student/classroom/")
    public ResultMessage getMyClassroom(@RequestParam String studentUsername) {
        Student student = studentBLService.getStudentByUsername(studentUsername);
        if (student.getClassroom() == null) {
            return new ResultMessage("NOT_EXIST", false, null);
        } else {
            Classroom classroom = student.getClassroom();
            return new ResultMessage(null, true, new ClassroomVO(classroom.getId(), classroom.getClassroomName()));
        }
    }

    @GetMapping(value = "/student/classroom/join")
    public ResultMessage joinClassroom(@RequestParam String studentUsername, @RequestParam Long classroomId) {
        Student student = studentBLService.joinClassroom(classroomId, studentUsername);
        if (student.getClassroom() == null) {
            return new ResultMessage(FAILED, false, null);
        } else {
            return new ResultMessage(null, true, student.getClassroom().getId());
        }
    }

    private AssignmentVO toAssignmentVO(Assignment assignment) {
        if (assignment == null) return null;
        List<QuestionVO> questionList = new ArrayList<>();
        for (Question question : assignment.getQuestionSet()) {
            questionList.add(new QuestionVO(question.getTitle(), question.getImagePath()));
        }
        return new AssignmentVO(assignment.getId(), assignment.getTitle(), assignment.getEndDate(), assignment.getTeacher().getUsername(), questionList);
    }

    private Answer toAnswer(AnswerVO answerVO, Commitment commitment) {
        Answer answer = new Answer();
        answer.setText(answerVO.getText());
        answer.setCommitment(commitment);
        answer.setQuestion(studentBLService.getQuestionById(answerVO.getQuestionId()));
        Set<String> imagePaths = new HashSet<>(answerVO.getImageUrls());
        answer.setImagePaths(imagePaths);
        return answer;
    }
}
