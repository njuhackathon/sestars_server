package com.njusestars.hackthon.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "student")
public class Student extends User {

    @OneToOne
    private Classroom classroom;

    @ManyToMany
    private Set<Parent> parents;
}
