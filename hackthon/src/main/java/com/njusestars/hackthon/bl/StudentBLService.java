package com.njusestars.hackthon.bl;

import com.njusestars.hackthon.entity.Assignment;
import com.njusestars.hackthon.entity.Commitment;
import com.njusestars.hackthon.entity.Question;
import com.njusestars.hackthon.entity.Student;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 *
 * 学生操作的逻辑层接口
 * @author lzb
 * @date 2019/5/26 10:39
 */
@Service
public interface StudentBLService {


    /**
     * 学生提交作业
     * @param commitment 对参数有特定要求
     * @return 最终形成的提交信息
     */
    Commitment commitAssignment(@NotNull Commitment commitment);


    /**
     * 学生查看未提交的作业列表
     * @param student 对参数有假设
     * @return
     */
    List<Assignment> getToDoAssignmentList(@NotNull Student student);


    List<Assignment> getCheckedAssign(@NotNull String stuName);

    /**
     * 学生查看未截止的作业列表
     * @param stuName
     * @return
     */
    List<Assignment> getUnfinishedAssign(@NotNull String stuName);


    /**
     * 根据用户名获取student
     * @param username
     * @return
     */
    Student getStudentByUsername(String username);


    /**
     * 根据id找到question
     * @param id
     * @return
     */
    Question getQuestionById(Long id);


    /**
     * 加入班级
     * @param classroomID
     * @param studentName
     * @return
     */
    Student joinClassroom(Long classroomID, String studentName);


}
