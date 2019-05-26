package com.njusestars.hackthon.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 用户
 * <br>
 * created on 2019/05/26
 *
 * @author 巽
 **/
@Data
@MappedSuperclass
public class User {
    @Id
    private String username;
    private String realName;
    private String password;
}
