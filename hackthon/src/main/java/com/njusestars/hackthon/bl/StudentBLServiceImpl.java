package com.njusestars.hackthon.bl;

import com.njusestars.hackthon.dao.CommitmentDao;
import com.njusestars.hackthon.dao.StudentDao;
import com.njusestars.hackthon.entity.Assignment;
import com.njusestars.hackthon.entity.Classroom;
import com.njusestars.hackthon.entity.Commitment;
import com.njusestars.hackthon.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lzb
 * @date 2019/5/26 13:32
 */
@Service
public class StudentBLServiceImpl implements StudentBLService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private CommitmentDao commitmentDao;


    @Override
    public Commitment commitAssignment(Commitment commitment) {
        //TODO 参数检查
        Commitment newCommit = commitmentDao.save(commitment);
        return newCommit;
    }

    @Override
    public List<Assignment> getToDoAssignmentList(Student student) {
        //参数检查
        //查询列表并过滤
        Classroom classroom = student.getClassroom();
        List<Assignment> assignmentList = classroom.getAssignmentSet()
                .parallelStream()
                .filter(assignment -> assignment.getEndDate().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
        return assignmentList;
    }

    @Override
    public Student getStudentByUsername(String username) {
        if (username == null) {
            System.err.println("username==null");
            return null;
        }
        Student student = studentDao.findById(username).orElse(null);
        if (student == null) {
            System.err.println("getStudentById() 这里的username不存在" + username);
        }
        return student;
    }


}
