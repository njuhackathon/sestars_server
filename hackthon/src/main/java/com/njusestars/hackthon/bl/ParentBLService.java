package com.njusestars.hackthon.bl;

import com.njusestars.hackthon.entity.Parent;
import org.springframework.stereotype.Service;

/**
 * 学生家长的接口
 * @author lzb
 * @date 2019/5/26 10:39
 */
@Service
public interface ParentBLService {

    Parent createParent(Parent parent);


}
