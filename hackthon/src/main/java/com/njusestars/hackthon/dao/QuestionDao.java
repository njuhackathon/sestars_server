package com.njusestars.hackthon.dao;

import com.njusestars.hackthon.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lzb
 * @date 2019/5/26 19:32
 */
@Repository
public interface QuestionDao extends JpaRepository<Question,Long> {
}
