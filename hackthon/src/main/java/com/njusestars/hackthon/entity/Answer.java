package com.njusestars.hackthon.entity;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Set;

/**
 * 回答
 * <br>
 * created on 2019/5/26
 *
 * @author Xunner
 **/
@Data
@Entity(name = "answer")
public class Answer {
    @Id
    private Long id;
    private String text;
    private Double score;

    @ManyToOne
    private Commitment commitment;

    @ElementCollection
    private Set<String> imagePaths;
}
