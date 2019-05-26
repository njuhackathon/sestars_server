package com.njusestars.hackthon.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "classroom")
public class Classroom {

    @Id
    private Long id;

    private String classroomName;

    @ManyToMany
    private Set<Teacher> teacherSet;

    @OneToMany
    private Set<Student> studentSet;

    @ManyToMany
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

}
