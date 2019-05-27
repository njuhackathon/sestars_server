package com.njusestars.hackthon.dao;

import com.njusestars.hackthon.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lzb
 * @date 2019/5/27 8:48
 */
@Repository
public interface AnswerDao extends JpaRepository<Answer,Long>{
}
