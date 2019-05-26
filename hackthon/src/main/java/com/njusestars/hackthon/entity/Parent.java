package com.njusestars.hackthon.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "parent")
public class Parent extends User {
    @ManyToMany
    private Set<Student> childrenSet;
}
