package com.njusestars.hackthon.bl;

import com.njusestars.hackthon.dao.TeacherDao;
import com.njusestars.hackthon.entity.Assignment;
import com.njusestars.hackthon.entity.Classroom;
import com.njusestars.hackthon.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzb
 * @date 2019/5/26 12:56
 */
@Service
public class TeacherBLServiceImpl implements TeacherBLService {

    @Autowired
    private TeacherDao teacherDao;


    @Override
    public Teacher getTeacherByUsername(String username) {
        return teacherDao.findById(username).orElse(null);
    }

    @Override
    public Classroom createClassroom(Classroom classroom) {
        return null;
    }

    @Override
    public Teacher joinClassroom(Classroom classroom, Teacher teacher) {
        return null;
    }

    @Override
    public Assignment publishAssignment(Assignment assignment) {
        return null;
    }

    @Override
    public boolean cancelAssignment(Assignment toBeDelete) {
        return false;
    }

    @Override
    public List<Assignment> getToDoAssignmentList(Teacher teacher) {
        return null;
    }

    @Override
    public List<Assignment> getDoneAssignmentList(Teacher teacher) {
        return null;
    }
}
