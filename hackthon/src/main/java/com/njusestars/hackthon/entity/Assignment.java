package com.njusestars.hackthon.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity(name = "assignment")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Teacher teacher;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Question> questionSet;

    @ManyToMany
    private Set<Classroom> classroomSet;

    @OneToMany
    private Set<Commitment> commitments;
}
