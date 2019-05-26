package com.njusestars.hackthon.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * 学生
 * <br>
 * created on 2019/05/26
 *
 * @author 巽
 **/
@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@Entity(name = "student")
public class Student extends User {
    @ManyToOne
    private Classroom classroom;

    @ManyToMany
    private Set<Parent> parents;

    @OneToMany
    private Set<Commitment> commitmentSet;

}
