package com.njusestars.hackthon.bl;

import com.njusestars.hackthon.entity.Assignment;
import com.njusestars.hackthon.entity.Commitment;
import com.njusestars.hackthon.entity.Student;
import org.springframework.stereotype.Service;

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
    Commitment commitAssignment(Commitment commitment);


    /**
     * 学生查看未完成作业列表
     * @param student 对参数有假设
     * @return
     */
    List<Assignment> getToDoAssignmentList(Student student);





}
