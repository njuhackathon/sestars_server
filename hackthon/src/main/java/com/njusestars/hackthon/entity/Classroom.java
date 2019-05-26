package com.njusestars.hackthon.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity(name = "classroom")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String classroomName;

    @ManyToMany
    private Set<Teacher> teacherSet;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "classroom")
    private Set<Student> studentSet;

    @ManyToMany(targetEntity = Assignment.class, mappedBy = "classroomSet")
    private Set<Assignment> assignmentSet;



    public void addTeacher(Teacher teacher){
        if (teacher == null) {
            System.err.println("addTeacher() 输入的teacher为null");
            return;
        }
        if (teacherSet == null) {
            teacherSet = new HashSet<>();
        }
        teacherSet.add(teacher);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classroom classroom = (Classroom) o;
        return classroom.getId().equals(this.getId());
    }


}
