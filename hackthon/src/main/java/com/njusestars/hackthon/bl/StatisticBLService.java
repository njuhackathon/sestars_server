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
     * 得到所有的score分数，返回(studentID --> Score)的map
     * @param assignmentId
     * @return
     */
    Map<String,Double> getTotalScore(Long assignmentId);


    /**
     * 得到得分列表
     * @param assignmentId
     * @return
     */
    List<Double> getScoreList(Long assignmentId);

    /**
     * 总共需要完成的学生数量
     * @param assignId
     * @return
     */
    Integer getToDoStuNum(Long assignId);


    /**
     * 还需要完成的学生数量
     * @param assignId
     * @return
     */
    Integer getDoneStuNum(Long assignId);


    /**
     * 最高分
     * @param assignId
     * @return
     */
    Double getHighestScore(Long assignId);

    /**
     * 最低分
     * @param assignId
     * @return
     */
    Double getLowestScore(Long assignId);

    /**
     * 某次作业的平均分
     * @param assignmentId
     * @return
     */
    Double getAverScore(Long assignmentId);


    /**
     * 得到quesiton的得分列表,从高到低排序
     * @param questionId
     * @return
     */
    List<Double> getScoreListByQuestion(Long questionId);


    /**
     * 每道题的最高分
     * @param questionId
     * @return
     */
    Double getHighestScoreByQuestion(Long questionId);

    /**
     * 每道题的最低分
     * @param questionId
     * @return
     */
    Double getLowestScoreByQuestion(Long questionId);

    /**
     * 每道题的平均分
     * @param questionId
     * @return
     */
    Double getAverScoreByQuestion(Long questionId);


    /**
     * 问题还未作答的人数
     * @param questionId
     * @return
     */
    Integer getToDoStuNumByQuestion(Long questionId);

    /**
     * 问题已经作答的人数
     * @param questionId
     * @return
     */
    Integer getDoneStuNumByQuestion(Long questionId);

}
