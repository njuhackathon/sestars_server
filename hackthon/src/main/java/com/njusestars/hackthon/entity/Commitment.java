package com.njusestars.hackthon.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity(name = "commitment")
public class Commitment {
    @Id
    private Long id;
    @ManyToOne
    private Assignment assignment;
    private String text;
    private Double score;
    private String comment;
    private LocalDateTime submitTime;

    private Student student;

    @ElementCollection
    private Set<String> imagePaths;
}
