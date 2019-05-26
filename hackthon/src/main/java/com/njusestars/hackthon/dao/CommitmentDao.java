package com.njusestars.hackthon.dao;

import com.njusestars.hackthon.entity.Commitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lzb
 * @date 2019/5/26 10:12
 */
@Repository
public interface CommitmentDao extends JpaRepository<Commitment, Long> {

    boolean deleteByStudentUsername(String username);

}
