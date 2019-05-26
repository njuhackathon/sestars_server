package com.njusestars.hackthon.dao;

import org.hibernate.dialect.MySQL57Dialect;

/**
 * @author lzb
 * @date 2019/5/26 15:27
 */
public class MYDialect extends MySQL57Dialect {
    @Override
    public String getTableTypeString() {
//        return super.getTableTypeString();
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
