package com.njusestars.hackthon.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Entity(name = "classroom")
public class Classroom {
    private Long id;

    @OneToMany
    private Set<Teacher> teachers;

    @OneToMany
    private Set<Student> students;
}
