package com.example.products.dao;

import com.example.products.pojo.Product;

import java.util.List;
import java.util.Map;

/**
 * Created by xf on 2018/11/8 10:53
 **/
public interface ProductsDao {

    Product selectByPrimaryKey(Integer id);

    List<Product> selectAll();

    List<Product> selectByProduct(Product product);

    List<Product> selectByPage(int curr,int page);

    int deleteByProductId(Integer id);

    int insert(Product product);

    int updateByProduct(Product product);


    List<String> queryCategoryKinds();

    List<String> queryCategoryBigKinds(String category);

    List<String> queryCategorySmallKinds(String category,String categoryBig);

    List<String> queryProductNumber(String category,String categoryBig,String categorySmall );

    int queryCategoryNumber(String category);

    List<String> queryCategoryBig();

    List<String> queryCategorySmall();

    List<Product> queryByKey(String some);

    List<Product> queryAllByCategory(String category);

    List<String> queryCategorySmallByCategory(String category);

}
