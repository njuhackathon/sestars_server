package com.njusestars.hackthon.bl;

import com.njusestars.hackthon.dao.AssignmentDao;
import com.njusestars.hackthon.dao.StudentDao;
import com.njusestars.hackthon.dao.TeacherDao;
import com.njusestars.hackthon.entity.Assignment;
import com.njusestars.hackthon.entity.Commitment;
import com.njusestars.hackthon.entity.Student;
import com.njusestars.hackthon.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author lzb
 * @date 2019/5/27 1:18
 */
@Service
public class StatisticBLServiceImpl implements StatisticBLService {

    @Autowired
    private AssignmentDao assignmentDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeacherDao teacherDao;

    @Override
    public Integer getRankInAssignment(Long assignmentId, String studentName) {
        if (assignmentId == null || studentName==null || !assignmentDao.existsById(assignmentId)) {
            System.err.println("input params is bad ~! in getRankInAssignment()");
            return null;
        }

        Map<String,Double> stuScoreMap = this.getTotalScore(assignmentId);
        if (stuScoreMap == null || stuScoreMap.size()==0 || !stuScoreMap.containsKey(studentName)) {
            System.err.println("score map is incorrect ~! ");
            return null;
        }

        List<Double> scoreList = this.getScoreList(assignmentId);
        if (scoreList == null || scoreList.size()==0) {
            System.err.println("score list is not correct");
            return null;
        }

        Integer rank = scoreList.indexOf(stuScoreMap.get(studentName)) + 1;//修正排名

        return rank;
    }

    @Override
    public Double getAverageInAssignment(Long assignmentId) {
        if (assignmentId == null || !assignmentDao.existsById(assignmentId)) {
            System.err.println("assignment id not exists");
            return null;
        }
        List<Double> scoreList = this.getScoreList(assignmentId);
        Double sum = 0.0, aver = 0.0;
        for (Double each : scoreList) {
            sum += each;
        }
        aver = sum / scoreList.size();
        return aver;
    }

    @Override
    public Map<String, Double> getTotalScore(Long assignmentId) {
        Assignment assignment = assignmentDao.findById(assignmentId).orElse(null);
        if (assignment == null) {
            System.err.println("assignment id not exists");
            return null;
        }
        Set<Commitment> commitmentSet = assignment.getCommitments();
        Map<String,Double> usernameScoreMap = new HashMap<>();
        for (Commitment commitment : commitmentSet) {
            Student student = commitment.getStudent();
            Double score = commitment.getScore();
            usernameScoreMap.put(student.getUsername(),score);
        }

        return usernameScoreMap;
    }

    @Override
    public List<Double> getScoreList(Long assignmentId) {
        if (assignmentId == null || !assignmentDao.existsById(assignmentId)) {
            System.err.println("getScoreList() assignment id not exists");
            return null;
        }
        Map<String,Double> stuScoreMap = this.getTotalScore(assignmentId);
        if (stuScoreMap == null ||stuScoreMap.size()==0) {
            System.err.println("score map is null or empty");
            return null;
        }
        List<Double> scoreList = new ArrayList<>(stuScoreMap.size());
        scoreList.addAll(stuScoreMap.values());
        scoreList.sort((a,b)-> (a>b)?-1:1);//逆序

        return scoreList;
    }
}
