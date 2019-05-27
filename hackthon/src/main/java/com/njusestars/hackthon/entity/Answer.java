package com.njusestars.hackthon.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * 回答
 * <br>
 * created on 2019/5/26
 *
 * @author Xunner
 **/
@Setter
@Getter
@Entity(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private Double score;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Commitment commitment;

    @ManyToOne
    private Question question;

    @ElementCollection
    private Set<String> imagePaths;

    /**
     * 该回答是不是真的写了答案的
     *
     * @return true：是
     */
    public boolean answered() {
        return (text != null && !text.isEmpty()) || (imagePaths != null && !imagePaths.isEmpty());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(getId(), answer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
