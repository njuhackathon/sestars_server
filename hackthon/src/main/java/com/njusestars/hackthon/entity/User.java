package com.njusestars.hackthon.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

/**
 * 用户
 * <br>
 * created on 2019/05/26
 *
 * @author 巽
 **/
@Setter
@Getter
@MappedSuperclass
public class User {
    @Id
    private String username;
    private String realName;
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }
}
