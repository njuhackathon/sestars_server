package com.njusestars.hackthon.bl;

import com.njusestars.hackthon.entity.Answer;
import com.njusestars.hackthon.entity.Assignment;
import com.njusestars.hackthon.entity.Classroom;
import com.njusestars.hackthon.entity.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzb
 * @date 2019/5/26 10:39
 */
@Service
public interface TeacherBLService {



    /**
     * 根据teacher username返回教师信息
     * @param username
     * @return
     */
    Teacher getTeacherByUsername(String username);


    Teacher updateTeacher(Teacher teacher);

    /**
     * 创建新的班级
     * @param classroom ,要求具有classroomName
     * @return
     */
    Classroom createClassroom(Classroom classroom);


    /**
     *
     * @param classroom ,完整的classroom
     * @param teacher，完整的teacher
     * @return
     */
    Teacher joinClassroom(Classroom classroom,Teacher teacher);


    /**
     * 教师发布作业
     * @param assignment 对参数有假设，具有title
     * @return 真实形成的作业布置项
     */
    Assignment publishAssignment(Assignment assignment);

    /**
     * 更新assignment
     * @param assignment
     * @return
     */
    Assignment updateAssignment(Assignment assignment);

    /**
     * 取消作业布置
     * @param toBeDelete
     * @return
     */
    boolean cancelAssignment(Assignment toBeDelete);


    /**
     * 得到当前教师未完成的作业
     * @param teacher 对参数有假设，具有id
     * @return assignment list 按发布时间由近到远排序
     */
    List<Assignment> getUnfinishedAssignList(Teacher teacher);


    /**
     * 得到当前教师已完成的作业
     * @param teacher 对参数有假设，具有id
     * @return assignment list 按发布时间由近到远排序
     */
    List<Assignment> getFinishedAssignList(Teacher teacher);


    /**
     * 获取assignment
     * @param id
     * @return
     */
    Assignment getAssignmentById(Long id);


    /**
     * 获取当前系统中所有班级
     * @return 班级列表
     */
    List<Classroom> getAllClassroom();


    /**
     * 通过id得到classroom
     * @param id
     * @return
     */
    Classroom getClassroomById(Long id);

    /**
     * 得到所有的教师列表
     * @return
     */
    List<Teacher> getAllTeacherList();


    /**
     * 批改答案，修改数据库
     * @param answerId
     * @param score
     * @return
     */
    Answer checkAnswer(Long answerId, Double score);


    /**
     * 根据id拿到ans
     * @param answerId
     * @return
     */
    Answer getAnswerById(Long answerId);




}
