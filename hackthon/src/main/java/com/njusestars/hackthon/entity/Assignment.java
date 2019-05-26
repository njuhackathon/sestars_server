package com.njusestars.hackthon.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class Assignment {
    private Long id;
    private String title;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;
    private Set<Commitment> commitments;
}
