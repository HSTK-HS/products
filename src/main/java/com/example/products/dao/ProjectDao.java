package com.example.products.dao;

import com.example.products.pojo.ProjectInfo;

import java.util.List;

/**
 * Created by xf on 2019/5/8 8:49
 **/
public interface ProjectDao {

    ProjectInfo selectByPrimaryKey(Integer id);

    ProjectInfo selectByProjectId(String projectId);

    List<ProjectInfo> selectByAuthor(String author);

    List<ProjectInfo> selectAll();

    Integer insert(ProjectInfo projectInfo);

    Integer deleteByProjectId(String projectId);
}
