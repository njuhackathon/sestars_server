package com.njusestars.hackthon.bl;

import com.njusestars.hackthon.dao.AssignmentDao;
import com.njusestars.hackthon.dao.StudentDao;
import com.njusestars.hackthon.dao.TeacherDao;
import com.njusestars.hackthon.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.zip.DeflaterOutputStream;

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
    public Double getAverScore(Long assignmentId) {
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

    @Override
    public Integer getToDoStuNum(Long assignId) {
        Assignment assignment = assignmentDao.findById(assignId).orElse(null);
        if (assignment == null) {
            System.err.println("getToDoStuNum() assign not exists");
            return null;
        }
        //查找classroom
        Set<Classroom> classroomSet = assignment.getClassroomSet();
        if (classroomSet == null || classroomSet.size()==0) {
            //没有classroom或为空
            return 0;
        }
        //返回classroom里所有的学生数
        Integer sum = 0;
        for (Classroom eachClass : classroomSet) {
            Set<Student> studentSet = eachClass.getStudentSet();
            if (studentSet == null || studentSet.size()==0) {
                continue;
            }
            sum += studentSet.size();
        }
        return sum;
    }

    @Override
    public Integer getDoneStuNum(Long assignId) {
        if (assignId == null) {
            System.err.println("assign id is null ~!");
            return null;
        }
        Assignment assignment = this.assignmentDao.findById(assignId).orElse(null);
        if (assignment == null) {
            System.err.println("can not find assignment");
            return null;
        }
        Integer doneSum = assignment.getCommitments().size();
        return doneSum;
    }

    @Override
    public Double getHighestScore(Long assignId) {
        List<Double> scoreList = getScoreList(assignId);
        if (scoreList == null || scoreList.size()==0) {
            System.err.println("getHighestScore(): score list is empty");
            return 0.0;
        }
        Double highestScore = scoreList.get(0);
        return highestScore;
    }

    @Override
    public Double getLowestScore(Long assignId) {
        List<Double> scoreList = getScoreList(assignId);
        if (scoreList == null || scoreList.size()==0) {
            System.err.println("getLowestScore(): score list is empty");
            return 0.0;
        }
        Double lowest = scoreList.get(scoreList.size()-1);
        return lowest;
    }
}
