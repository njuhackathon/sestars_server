package com.njusestars.hackthon.vo;

import lombok.Data;

import java.util.List;

/**
 *
 * <br>
 * created on 2019/5/26
 *
 * @author Xunner
 **/
@Data
public class CommitmentVO {
    String studentUsername;
    Long assignmentId;
    List<AnswerVO> answerList;
}
