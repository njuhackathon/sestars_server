package com.njusestars.hackthon.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@Entity(name = "assignment")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Teacher teacher;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "assignment")
    private Set<Question> questionSet;

    @ManyToMany
    private Set<Classroom> classroomSet;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "assignment")
    private Set<Commitment> commitments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assignment that = (Assignment) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getBeginDate(), getEndDate());
    }
}
