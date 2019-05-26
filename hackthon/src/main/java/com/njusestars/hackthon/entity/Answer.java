package com.njusestars.hackthon.entity;

import lombok.Data;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private Double score;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Commitment commitment;

    @OneToOne
    private Question question;

    @ElementCollection
    private Set<String> imagePaths;
}
