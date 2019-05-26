package com.njusestars.hackthon.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity(name = "commitment")
public class Commitment {
    @Id
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

}
