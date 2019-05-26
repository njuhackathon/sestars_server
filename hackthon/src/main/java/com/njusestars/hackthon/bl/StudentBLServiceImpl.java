package com.njusestars.hackthon.bl;

import com.njusestars.hackthon.dao.ClassroomDao;
import com.njusestars.hackthon.dao.CommitmentDao;
import com.njusestars.hackthon.dao.QuestionDao;
import com.njusestars.hackthon.dao.StudentDao;
import com.njusestars.hackthon.entity.*;
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

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private ClassroomDao classroomDao;


    @Override
    public Commitment commitAssignment(Commitment commitment) {
        //参数检查
        if (commitment == null) {
            System.err.println("commitAssignment() commitment is null");
            return null;
        }
        commitmentDao.deleteByStudentUsername(commitment.getStudent().getUsername());
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

    @Override
    public Question getQuestionById(Long id) {
        Question question = this.questionDao.findById(id).orElse(null);
        if (question == null) {
            System.err.println("question id is null");
        }
        return question;
    }

    @Override
    public Student joinClassroom(Long classroomID, String studentName) {
        if (classroomID == null || studentName==null) {
            System.err.println("param is null !!!");
            return null;
        }
        Student student = studentDao.findById(studentName).orElse(null);
        Classroom classroom = classroomDao.findById(classroomID).orElse(null);
        student.setClassroom(classroom);
        student = studentDao.save(student);

        return student;
    }


}
