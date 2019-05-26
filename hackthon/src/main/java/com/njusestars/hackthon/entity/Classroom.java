package com.njusestars.hackthon.entity;

import lombok.Data;

import java.util.Set;

@Data
public class Classroom {
    private Long id;
    private Set<Teacher> teachers;
    private Set<Student> students;
}
