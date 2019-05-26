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
import java.util.ArrayList;
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
    public Teacher updateTeacher(Teacher teacher) {
        return teacherDao.save(teacher);
    }

    @Override
    public Classroom createClassroom(Classroom classroom) {
        Classroom newClassroom = classroomDao.save(classroom);
        return newClassroom;
    }

    @Override
    public Teacher joinClassroom(Classroom classroom, Teacher teacher) {
        classroom.addTeacher(teacher);
        classroom = classroomDao.save(classroom);

        teacher.addClassroom(classroom);
        teacher = teacherDao.save(teacher);
        return teacher;
    }

    @Override
    public Assignment publishAssignment(Assignment assignment) {
        //参数检验
        //保存
        assignment = assignmentDao.save(assignment);
        return assignment;
    }

    @Override
    public Assignment updateAssignment(Assignment assignment) {
        return assignmentDao.save(assignment);
    }

    @Override
    public boolean cancelAssignment(Assignment toBeDelete) {
        assignmentDao.deleteById(toBeDelete.getId());
        return true;
    }

    @Override
    public List<Assignment> getToDoAssignmentList(Teacher teacher) {
        Teacher realTeacher = teacherDao.findById(teacher.getUsername()).orElse(null);
        if (realTeacher == null) {
            System.err.println("todoAssignmentList() 这里传入的teacher为null");
            return new ArrayList<>();
        }
        Set<Assignment> assignmentSet = realTeacher.getAssignmentSet();
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
        Teacher realTeacher = getTeacherByUsername(teacher.getUsername());
        Set<Assignment> assignmentSet = realTeacher.getAssignmentSet();
        List<Assignment> assignmentList = assignmentSet.parallelStream()
                .filter(assignment -> assignment.getEndDate().isBefore(LocalDateTime.now()))
                .sorted((a,b)->{
                    boolean flag = a.getEndDate().isBefore(b.getEndDate());
                    return (flag)?1:-1;
                })
                .collect(Collectors.toList());
        return assignmentList;
    }

    @Override
    public Assignment getAssignmentById(Long id) {
        Assignment assignment = assignmentDao.findById(id).orElse(null);
        if (assignment == null) {
            System.err.println("getAssignment(),这里拿到了一个null");
        }
        return assignment;
    }

    @Override
    public List<Classroom> getAllClassroom() {
        List<Classroom> classroomList = classroomDao.findAll();
        return classroomList;
    }

    @Override
    public Classroom getClassroomById(Long id) {
        Classroom classroom = classroomDao.findById(id).orElse(null);
        if (classroom == null) {
            System.err.println("getClassroomById() 这里传入的id不存在");
        }
        return classroom;
    }

    @Override
    public List<Teacher> getAllTeacherList() {
        return teacherDao.findAll();
    }
}
