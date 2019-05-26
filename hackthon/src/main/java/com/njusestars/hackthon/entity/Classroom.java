package com.njusestars.hackthon.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Entity(name = "classroom")
public class Classroom {

    @Id
    private Long id;

    private String classroomName;

    @ManyToMany
    private Set<Teacher> teachers;

    @OneToMany
    private Set<Student> students;
}
