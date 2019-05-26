package com.njusestars.hackthon.bl;

import com.njusestars.hackthon.enums.Result;
import org.springframework.stereotype.Service;

/**
 * 用户Service
 * <br>
 * created on 2019/5/26
 *
 * @author Xunner
 **/
@Service
public interface UserBLService {
    /**
     * 所有用户类型通用登录
     * @param username 唯一用户名
     * @param password 密码
     * @return SUCCESS:成功, FAILED:密码不正确, NOT_EXIST:用户名不存在
     */
    Result login(String username, String password);
}
