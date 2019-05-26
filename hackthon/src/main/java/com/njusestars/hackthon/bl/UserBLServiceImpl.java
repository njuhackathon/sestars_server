package com.njusestars.hackthon.bl;

import com.njusestars.hackthon.dao.ParentDao;
import com.njusestars.hackthon.dao.StudentDao;
import com.njusestars.hackthon.dao.TeacherDao;
import com.njusestars.hackthon.entity.Parent;
import com.njusestars.hackthon.entity.Student;
import com.njusestars.hackthon.entity.Teacher;
import com.njusestars.hackthon.entity.User;
import com.njusestars.hackthon.enums.Result;
import com.njusestars.hackthon.enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lzb
 * @date 2019/5/26 12:09
 */
@Service
public class UserBLServiceImpl implements UserBLService {


    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ParentDao parentDao;


    @Override
    public Result login(String username, String password) {
        User user = null;
        if (studentDao.existsById(username)){
            user = studentDao.findById(username).orElse(null);

        }
        else if (teacherDao.existsByUsername(username)){
            user = teacherDao.findById(username).orElse(null);
        }
        else if ((parentDao.existsByUsername(username))){
            user = parentDao.findById(username).orElse(null);
        }

        if (user==null){
            //用户不存在
            return Result.NOT_EXIST;
        }
        if (!user.getPassword().equals(password)){
            //账号密码不对
            return Result.FAILED;
        }

        return Result.SUCCESS;
    }

    @Override
    public Result register(String username, String realName, String password, UserType type) {
        boolean ifExists = this.existsUsername(username);
        if (ifExists) {
            return Result.EXIST;
        }

        User newUser = this.createUser(username,realName,password,type);

        if (newUser == null) {
            //返回null，注册不成功
            return Result.FAILED;
        }



        return Result.SUCCESS;
    }

    /**
     * 返回username是否存在
     * @param username
     * @return
     */
    private boolean existsUsername(String username){
        return teacherDao.existsById(username)
                || studentDao.existsById(username)
                || parentDao.existsById(username);
    }

    private User createUser(String username,String realName,String password,UserType type){
        User newUser = null;
        if (type == UserType.PARENT){
            newUser = new Parent();
            newUser.setUsername(username);
            newUser.setRealName(realName);
            newUser.setPassword(password);
            newUser = parentDao.save((Parent) newUser);
        } else if (type == UserType.TEACHER){
            newUser = new Teacher();
            newUser.setUsername(username);
            newUser.setRealName(realName);
            newUser.setPassword(password);
            newUser = teacherDao.save((Teacher)newUser);
        } else if ((type == UserType.STUDENT)) {
            newUser = new Student();
            newUser.setUsername(username);
            newUser.setRealName(realName);
            newUser.setPassword(password);
            newUser = studentDao.save((Student)newUser);
        }


        return newUser;
    }

}
