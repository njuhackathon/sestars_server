package com.njusestars.hackthon.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * 教师
 * <br>
 * created on 2019/05/26
 *
 * @author 巽
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "teacher")
public class Teacher extends User {

    @ManyToMany
    private Set<Classroom> classroomSet;

    @OneToMany
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


}
