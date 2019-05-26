package com.njusestars.hackthon.bl;

import com.njusestars.hackthon.dao.CommitmentDao;
import com.njusestars.hackthon.dao.StudentDao;
import com.njusestars.hackthon.entity.Assignment;
import com.njusestars.hackthon.entity.Commitment;
import com.njusestars.hackthon.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        return null;
    }

    @Override
    public List<Assignment> getToDoAssignmentList(Student student) {
        return null;
    }
}
