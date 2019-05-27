package com.njusestars.hackthon.controller;

import com.njusestars.hackthon.bl.StatisticBLService;
import com.njusestars.hackthon.bl.TeacherBLService;
import com.njusestars.hackthon.entity.Assignment;
import com.njusestars.hackthon.entity.Question;
import com.njusestars.hackthon.util.ResultMessage;
import com.njusestars.hackthon.vo.AssignTeaStaVO;
import com.njusestars.hackthon.vo.QuesTeaStaVO;
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
    public ResultMessage getAssignmentStatistic(@RequestParam Long assignmentId) {
        Assignment assignment = teacherBLService.getAssignmentById(assignmentId);
        List<QuesTeaStaVO> questions = new ArrayList<>();
        for (Question question : assignment.getQuestionList()) {
            Long questionId = question.getId();
            questions.add(new QuesTeaStaVO(question.getId(), question.getTitle(), question.getScore(),
                    statisticBLService.getDoneStuNumByQuestion(questionId), statisticBLService.getToDoStuNumByQuestion(questionId),
                    statisticBLService.getAverScoreByQuestion(questionId), statisticBLService.getHighestScoreByQuestion(questionId),
                    statisticBLService.getLowestScoreByQuestion(questionId)));
        }
        AssignTeaStaVO assignTeaStaVO = new AssignTeaStaVO(assignmentId, assignment.getTitle(), assignment.getEndDate(),
                statisticBLService.getDoneStuNum(assignmentId), statisticBLService.getAverScore(assignmentId),
                statisticBLService.getHighestScore(assignmentId), statisticBLService.getLowestScore(assignmentId), questions);
        return new ResultMessage(null, true, assignTeaStaVO);
    }
}
