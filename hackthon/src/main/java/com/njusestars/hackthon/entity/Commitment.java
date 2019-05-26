package com.njusestars.hackthon.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@Entity(name = "commitment")
public class Commitment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Assignment assignment;
    private Double score;
    private String comment;
    private LocalDateTime submitTime;

    @ManyToOne
    private Student student;

    @OneToMany
    private Set<Answer> answerSet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commitment that = (Commitment) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
