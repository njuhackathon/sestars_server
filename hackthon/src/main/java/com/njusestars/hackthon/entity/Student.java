package com.njusestars.hackthon.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "student")
public class Student extends User {
    private Classroom classroom;
    
    private Set<Parent> parents;
}
