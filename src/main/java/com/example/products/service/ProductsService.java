package com.example.products.service;

import com.example.products.pojo.Product;

import java.util.List;
import java.util.Map;

/**
 * Created by xf on 2018/11/8 11:00
 **/
public interface ProductsService {

    Product selectByPrimaryKey(Integer id);

    List<Product> selectAll();

    Product selectByPrimaryKey(String id);

    List<Product> selectByProduct(Product product);

    List<Product> selectByPage(int curr,int page);

    int deleteByProductId(Integer id);

    int insert(Product product);

    int updateByProduct(Product product);

    List<Map> getTableData();

    List<Map> queryCategory();

    List<Map> queryCategoryBig();

    List<Map> queryCategorySmall();

    List<Map> queryCategoryBigKinds(String category);

    List<Map> queryCategorySmallKinds(String category,String categoryBig);

    List<Map> queryCategorySmallByCategory(String category);
}
