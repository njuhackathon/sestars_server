package com.njusestars.hackthon.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity(name = "assignment")
public class Assignment {
    @Id
    private Long id;
    private String title;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;
    @OneToMany
    private Set<Commitment> commitments;
}
