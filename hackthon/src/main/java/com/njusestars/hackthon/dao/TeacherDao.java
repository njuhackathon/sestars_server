package com.njusestars.hackthon.dao;

import com.njusestars.hackthon.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lzb
 * @date 2019/5/26 10:11
 */
@Repository
public interface TeacherDao extends JpaRepository<Teacher, Long> {
}
