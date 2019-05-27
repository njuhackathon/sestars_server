package com.njusestars.hackthon.controller;

import com.njusestars.hackthon.bl.TeacherBLService;
import com.njusestars.hackthon.entity.*;
import com.njusestars.hackthon.util.ResultMessage;
import com.njusestars.hackthon.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
        Set<Classroom> classroomSet = new HashSet<>();
        for (Long classroomId : createAssigmentVO.getClassroomIds()) {
            classroomSet.add(teacherBLService.getClassroomById(classroomId));
        }
        assignment.setClassroomSet(classroomSet);
        List<Question> questionSet = new ArrayList<>();
        for (QuestionVO questionVO : createAssigmentVO.getQuestionList()) {
            questionSet.add(new Question(questionVO.getTitle(), questionVO.getImageUrl(), assignment));
        }
        assignment.setQuestionList(questionSet);
        assignment = teacherBLService.publishAssignment(assignment);
        if (assignment == null) {
            return new ResultMessage(FAILED, false, null);
        } else {
            return new ResultMessage(null, true, null);
        }
    }

    @GetMapping(value = "/teacher/assignment/all")
    public ResultMessage getAllAssignment(@RequestParam String teacherUsername) {
        Teacher teacher = teacherBLService.getTeacherByUsername(teacherUsername);
        List<Assignment> assignmentList = teacherBLService.getToDoAssignmentList(teacher);
        assignmentList.addAll(teacherBLService.getDoneAssignmentList(teacher));
        List<AssignmentVO> assignmentVOS = new ArrayList<>();
        for (Assignment assignment : assignmentList) {
            assignmentVOS.add(new AssignmentVO(assignment.getId(), assignment.getTitle()));
        }
        return new ResultMessage(null, true, assignmentVOS);
    }

    @GetMapping(value = "/teacher/assignment/")
    public ResultMessage getAssignment(@RequestParam Long assignmentId) {
        Assignment assignment = teacherBLService.getAssignmentById(assignmentId);
        AssignmentMarkingVO assignmentVO = new AssignmentMarkingVO(assignment.getId(), assignment.getTitle(),
                assignment.getEndDate(), assignment.getTeacher().getUsername());
        Map<Question, List<AnswerVO>> questionListMap = new HashMap<>();
        for (Commitment commitment : assignment.getCommitments()) {
            for (Answer answer : commitment.getAnswerSet()) {
                Question question = answer.getQuestion();
                List<AnswerVO> list = questionListMap.get(question);
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(this.toAnswerVO(answer));
                questionListMap.put(question, list);
            }
        }
        Map<QuestionVO, List<AnswerVO>> questionAnswers = new HashMap<>();
        for (Map.Entry<Question, List<AnswerVO>> entry : questionListMap.entrySet()) {
            Question question = entry.getKey();
            QuestionVO questionVO = new QuestionVO(question.getId(), question.getTitle(), question.getImagePath(), question.getScore());
            questionAnswers.put(questionVO, entry.getValue());
        }
        assignmentVO.setQuestionAnswers(questionAnswers);
        return new ResultMessage(null, true, assignmentVO);
    }

    @GetMapping(value = "/teacher/assignment/mark")
    public ResultMessage markAnswer(@RequestParam Long answerId, @RequestParam Double score) {
//        teacherBLService.
        return null;
    }

    private AnswerVO toAnswerVO(Answer answer) {
        return new AnswerVO(answer.getId(), answer.getText(), new ArrayList<>(answer.getImagePaths()));
    }
}
