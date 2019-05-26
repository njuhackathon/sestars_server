package com.njusestars.hackthon.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 教师
 * <br>
 * created on 2019/05/26
 *
 * @author 巽
 **/
@Setter
@Getter
@Entity(name = "teacher")
public class Teacher extends User {

    @ManyToMany
    private Set<Classroom> classroomSet;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Assignment> assignmentSet;


    public void addClassroom(Classroom classroom){
        if (classroom == null){
            System.err.println("addClassroom() 这里的classroom是null");
        }
        if (classroomSet == null) {
            classroomSet = new HashSet<>();
        }
        this.classroomSet.add(classroom);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return teacher.getUsername().equals(this.getUsername());
    }


}
