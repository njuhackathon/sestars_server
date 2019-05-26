package com.njusestars.hackthon.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
@Data
@Entity(name = "parent")
public class Parent extends User {
    @ManyToMany
    private Set<Student> childrenSet;
}
