package com.njusestars.hackthon.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity(name = "assignment")
public class Assignment {
    @Id
    private Long id;
    private String title;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;

    @ManyToOne
    private Teacher teacher;

    @ManyToMany
    private Set<Classroom> classroomSet;

    @OneToMany
    private Set<Commitment> commitments;
}
