package com.njusestars.hackthon.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

/**
 * 题目
 * <br>
 * created on 2019/5/26
 *
 * @author Xunner
 **/
@Setter
@Getter
@Entity(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String imagePath;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Assignment assignment;

    public Question() {
    }

    public Question(String title, String imagePath, Assignment assignment) {
        this.title = title;
        this.imagePath = imagePath;
        this.assignment = assignment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(getId(), question.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getImagePath());
    }
}
