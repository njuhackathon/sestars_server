package com.njusestars.hackthon.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class Commitment {
    private Long id;
    private Assignment assignment;
    private String text;
    private Double score;
    private String comment;
    private LocalDateTime submitTime;
    private Set<String> imagePaths;
}
