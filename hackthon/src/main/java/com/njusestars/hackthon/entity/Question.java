package com.njusestars.hackthon.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 题目
 * <br>
 * created on 2019/5/26
 *
 * @author Xunner
 **/
@Data
@Entity(name = "question")
public class Question {
    @Id
    private Long id;
    private String title;
    private String imagePath;
}
