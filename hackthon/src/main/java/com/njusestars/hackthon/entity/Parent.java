package com.njusestars.hackthon.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

/**
 * 家长
 * <br>
 * created on 2019/05/26
 *
 * @author 巽
 **/
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity(name = "parent")
public class Parent extends User {
    @ManyToMany
    private Set<Student> childrenSet;
}
