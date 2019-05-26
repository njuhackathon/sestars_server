package com.njusestars.hackthon.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author lzb
 * @date 2019/5/27 1:18
 */
@Service
public class StatisticBLServiceImpl implements StatisticBLService {



    @Override
    public Integer getRankInAssignment(Long assignmentId, String studentName) {
        return null;
    }

    @Override
    public Double getAverageInAssignment(Long assignmentId) {
        return null;
    }

    @Override
    public List<Integer> getScoreRatio(Long assignmentId, int cutNum) {
        return null;
    }

    @Override
    public Map<String, Double> getTotalScore(Long assignmentId) {
        return null;
    }
}
