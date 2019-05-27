package com.njusestars.hackthon.dao;

import com.njusestars.hackthon.entity.Commitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lzb
 * @date 2019/5/26 10:12
 */
@Repository
public interface CommitmentDao extends JpaRepository<Commitment, Long> {

    void deleteByStudentUsername(String username);

    boolean existsByStudentUsername(String username);

    Commitment findByAssignmentIdAndStudent_Username(Long assignmentId, String stuName);

}
