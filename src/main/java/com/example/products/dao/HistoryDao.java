package com.example.products.dao;

import com.example.products.pojo.History;

import java.util.List;

/**
 * Created by xf on 2019/5/8 8:35
 **/
public interface HistoryDao {

    History selectByPrimaryKey(Integer id);

    List<History> selectAll();

    List<History> selectByProject(String projectId);

    Integer insert(History history);

    Integer deleteByProjectId(String projectId);
}
