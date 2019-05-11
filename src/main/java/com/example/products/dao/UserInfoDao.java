package com.example.products.dao;

import com.example.products.pojo.UserInfo;

import java.util.List;

/**
 * Created by xf on 2018/11/19 11:04
 **/
public interface UserInfoDao {

    UserInfo selectByInfo(UserInfo userInfo);

    UserInfo selectByName(String name);

    List<UserInfo> selectAll();

    Integer insert(UserInfo userInfo);

    Integer delete(UserInfo userInfo);
}
