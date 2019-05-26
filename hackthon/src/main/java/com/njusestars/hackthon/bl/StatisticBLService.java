package com.njusestars.hackthon.bl;

import com.njusestars.hackthon.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author lzb
 * @date 2019/5/27 1:06
 */
@Service
public interface StatisticBLService {

    /**
     * 得到学生在某次作业中的排名
     * @param assignmentId
     * @param studentName
     * @return
     */
    Integer getRankInAssignment(Long assignmentId,String studentName);

    /**
     * 某次作业的平均分
     * @param assignmentId
     * @return
     */
    Double getAverageInAssignment(Long assignmentId);


    /**
     * 将得分区间分为cutNum个，返回每个得分区间中的人数，从高到底
     * @param assignmentId
     * @param cutNum
     * @return
     */
    List<Integer> getScoreRatio(Long assignmentId,int cutNum);

    /**
     * 得到所有的score分数，返回(studentID --> Score)的map
     * @param assignmentId
     * @return
     */
    Map<String,Double> getTotalScore(Long assignmentId);



}
