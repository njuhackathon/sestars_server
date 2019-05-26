package com.njusestars.hackthon.bl;

import com.njusestars.hackthon.dao.AssignmentDao;
import com.njusestars.hackthon.dao.ClassroomDao;
import com.njusestars.hackthon.dao.TeacherDao;
import com.njusestars.hackthon.entity.Assignment;
import com.njusestars.hackthon.entity.Classroom;
import com.njusestars.hackthon.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author lzb
 * @date 2019/5/26 12:56
 */
@Service
public class TeacherBLServiceImpl implements TeacherBLService {

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private ClassroomDao classroomDao;

    @Autowired
    private AssignmentDao assignmentDao;


    @Override
    public Teacher getTeacherByUsername(String username) {
        return teacherDao.findById(username).orElse(null);
    }

    @Override
    public Classroom createClassroom(Classroom classroom) {
        Classroom newClassroom = classroomDao.save(classroom);
        return newClassroom;
    }

    @Override
    public Teacher joinClassroom(Classroom classroom, Teacher teacher) {
        classroom.addTeacher(teacher);
        classroomDao.save(classroom);

        teacher.addClassroom(classroom);
        teacherDao.save(teacher);

        return teacher;
    }

    @Override
    public Assignment publishAssignment(Assignment assignment) {
        //参数检验
        //保存
        Assignment newAssign = assignmentDao.save(assignment);
        return newAssign;
    }

    @Override
    public boolean cancelAssignment(Assignment toBeDelete) {
        this.assignmentDao.delete(toBeDelete);
        return true;
    }

    @Override
    public List<Assignment> getToDoAssignmentList(Teacher teacher) {
        Set<Assignment> assignmentSet = teacher.getAssignmentSet();
        List<Assignment> assignmentList = assignmentSet.parallelStream()
                .filter(assignment -> assignment.getEndDate().isAfter(LocalDateTime.now()))
                .sorted((a,b)->{
                    boolean flag = a.getEndDate().isBefore(b.getEndDate());
                    return (flag)?1:-1;
                })
                .collect(Collectors.toList());
        return assignmentList;
    }

    @Override
    public List<Assignment> getDoneAssignmentList(Teacher teacher) {
        Set<Assignment> assignmentSet = teacher.getAssignmentSet();
        List<Assignment> assignmentList = assignmentSet.parallelStream()
                .filter(assignment -> assignment.getEndDate().isBefore(LocalDateTime.now()))
                .sorted((a,b)->{
                    boolean flag = a.getEndDate().isBefore(b.getEndDate());
                    return (flag)?1:-1;
                })
                .collect(Collectors.toList());
        return assignmentList;
    }
}
