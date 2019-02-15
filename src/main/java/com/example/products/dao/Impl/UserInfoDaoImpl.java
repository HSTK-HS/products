package com.example.products.dao.Impl;

import com.example.products.dao.UserInfoDao;
import com.example.products.pojo.UserInfo;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xf on 2018/11/19 11:05
 **/
@Repository
public class UserInfoDaoImpl implements UserInfoDao {

    private final SqlSession sqlSessionTemplate;

    @Autowired
    public UserInfoDaoImpl(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public UserInfo selectByInfo(UserInfo userInfo) {
        return sqlSessionTemplate.selectOne("com.example.products.dao.UserInfoMapper.selectByInfo",userInfo);
    }

    @Override
    public List<UserInfo> selectAll() {
        return sqlSessionTemplate.selectList("com.example.products.dao.UserInfoMapper.selectAll");
    }

    @Override
    public Integer insert(UserInfo userInfo) {
        return sqlSessionTemplate.insert("com.example.products.dao.UserInfoMapper.insert",userInfo);
    }

    @Override
    public Integer delete(UserInfo userInfo) {
        return sqlSessionTemplate.delete("com.example.products.dao.UserInfoMapper.delete",userInfo.getId());
    }


}
