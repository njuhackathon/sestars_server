package com.njusestars.hackthon.controller;

import com.njusestars.hackthon.bl.StatisticBLService;
import com.njusestars.hackthon.bl.TeacherBLService;
import com.njusestars.hackthon.entity.Assignment;
import com.njusestars.hackthon.entity.Question;
import com.njusestars.hackthon.util.ResultMessage;
import com.njusestars.hackthon.vo.AssignStaVO;
import com.njusestars.hackthon.vo.QuesStaVO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 统计Controller
 * <br>
 * created on 2019/5/27
 *
 * @author Xunner
 **/
@RestController
@CrossOrigin
public class StatisticsController {
    private final StatisticBLService statisticBLService;
    private final TeacherBLService teacherBLService;

    public StatisticsController(StatisticBLService statisticBLService, TeacherBLService teacherBLService) {
        this.statisticBLService = statisticBLService;
        this.teacherBLService = teacherBLService;
    }

    @GetMapping(value = "/teacher/assignment/statistic")
    public ResultMessage getTeacherAssignmentStatistic(@RequestParam Long assignmentId) {
        Assignment assignment = teacherBLService.getAssignmentById(assignmentId);
        AssignStaVO assignStaVO = new AssignStaVO(assignmentId, assignment.getTitle(), assignment.getEndDate(),
                statisticBLService.getDoneStuNum(assignmentId), statisticBLService.getAverScore(assignmentId),
                statisticBLService.getHighestScore(assignmentId), statisticBLService.getLowestScore(assignmentId),
                0, this.getQuestions(assignment.getQuestionList()));
        return new ResultMessage(null, true, assignStaVO);
    }

    @GetMapping(value = "/student/assignment/statistic")
    public ResultMessage getStudentAssignmentStatistic(@RequestParam Long assignmentId, @RequestParam String studentUsername) {
        Assignment assignment = teacherBLService.getAssignmentById(assignmentId);
        Integer rank = statisticBLService.getRankInAssignment(assignmentId, studentUsername);
        AssignStaVO assignStaVO = new AssignStaVO(assignmentId, assignment.getTitle(), assignment.getEndDate(),
                statisticBLService.getDoneStuNum(assignmentId), statisticBLService.getAverScore(assignmentId),
                statisticBLService.getHighestScore(assignmentId), statisticBLService.getLowestScore(assignmentId),
                rank, this.getQuestions(assignment.getQuestionList()));
        return new ResultMessage(null, true, assignStaVO);
    }

    private List<QuesStaVO> getQuestions(List<Question> questions) {
        List<QuesStaVO> ret = new ArrayList<>();
        for (Question question : questions) {
            Long questionId = question.getId();
            ret.add(new QuesStaVO(question.getId(), question.getTitle(), question.getScore(),
                    statisticBLService.getDoneStuNumByQuestion(questionId), statisticBLService.getToDoStuNumByQuestion(questionId),
                    statisticBLService.getAverScoreByQuestion(questionId), statisticBLService.getHighestScoreByQuestion(questionId),
                    statisticBLService.getLowestScoreByQuestion(questionId), question.getImagePath()));
        }
        return ret;
    }
}
