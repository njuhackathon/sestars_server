package com.njusestars.hackthon.dao;

import com.njusestars.hackthon.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lzb
 * @date 2019/5/26 13:04
 */
public interface ClassroomDao extends JpaRepository<Classroom,Long> {
}
