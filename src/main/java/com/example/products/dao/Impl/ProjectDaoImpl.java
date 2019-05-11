package com.example.products.dao.Impl;

import com.example.products.dao.ProjectDao;
import com.example.products.pojo.ProjectInfo;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xf on 2019/5/8 8:55
 **/
@Repository
public class ProjectDaoImpl implements ProjectDao {
    private final SqlSession sqlSessionTemplate;

    @Autowired
    public ProjectDaoImpl(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public ProjectInfo selectByPrimaryKey(Integer id) {
        return sqlSessionTemplate.selectOne("com.example.products.dao.ProjectInfoMapper.selectByPrimaryKey",id);
    }

    @Override
    public ProjectInfo selectByProjectId(String projectId) {
        return sqlSessionTemplate.selectOne("com.example.products.dao.ProjectInfoMapper.selectByProjectId",projectId);
    }

    @Override
    public List<ProjectInfo> selectByAuthor(String author) {
        return sqlSessionTemplate.selectList("com.example.products.dao.ProjectInfoMapper.selectByAuthor",author);
    }

    @Override
    public List<ProjectInfo> selectAll() {
        return sqlSessionTemplate.selectList("com.example.products.dao.ProjectInfoMapper.selectAll");
    }

    @Override
    public Integer insert(ProjectInfo projectInfo) {
        return sqlSessionTemplate.insert("com.example.products.dao.ProjectInfoMapper.insert",projectInfo);
    }

    @Override
    public Integer deleteByProjectId(String projectId) {
        return sqlSessionTemplate.delete("com.example.products.dao.ProjectInfoMapper.deleteByProjectId",projectId);
    }
}
