package com.example.products.dao.Impl;

import com.example.products.dao.HistoryDao;
import com.example.products.pojo.History;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xf on 2019/5/8 8:34
 **/
@Repository
public class HistoryDaoImpl implements HistoryDao {
    private final SqlSession sqlSessionTemplate;

    @Autowired
    public HistoryDaoImpl(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public History selectByPrimaryKey(Integer id) {
        return sqlSessionTemplate.selectOne("com.example.products.dao.HistoryMapper.selectByPrimaryKey",id);
    }

    @Override
    public List<History> selectAll() {
        return sqlSessionTemplate.selectList("com.example.products.dao.HistoryMapper.selectAll");
    }

    @Override
    public List<History> selectByProject(String projectId) {
        return sqlSessionTemplate.selectList("com.example.products.dao.HistoryMapper.selectByProject",projectId);
    }

    @Override
    public Integer insert(History history) {
        return sqlSessionTemplate.insert("com.example.products.dao.HistoryMapper.insert",history);
    }

    @Override
    public Integer deleteByProjectId(String projectId) {
        return sqlSessionTemplate.delete("com.example.products.dao.HistoryMapper.deleteByProjectId",projectId);
    }
}
