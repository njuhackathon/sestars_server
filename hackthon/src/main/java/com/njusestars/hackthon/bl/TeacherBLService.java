package com.njusestars.hackthon.bl;

import com.njusestars.hackthon.entity.Assignment;
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
     * 教师发布作业
     * @param assignment 对参数有假设，待定
     * @return 真实形成的作业布置项
     */
    Assignment publishAssignment(Assignment assignment);

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
    List<Assignment> getToDoAssignmentList(Teacher teacher);


    /**
     * 得到当前教师已完成的作业
     * @param teacher 对参数有假设，具有id
     * @return assignment list 按发布时间由近到远排序
     */
    List<Assignment> getDoneAssignmentList(Teacher teacher);


}
