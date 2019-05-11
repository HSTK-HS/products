package com.example.products.dao.Impl;

import com.example.products.dao.ProductsDao;
import com.example.products.pojo.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xf on 2018/11/8 10:59
 **/
@Repository
public class ProductsDaoImpl implements ProductsDao {

    private final SqlSession sqlSessionTemplate;

    @Autowired
    public ProductsDaoImpl(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public Product selectByPrimaryKey(Integer id) {
        return sqlSessionTemplate.selectOne("com.example.products.dao.ProductMapper.selectByPrimaryKey", id);
    }

    @Override
    public List<Product> selectAll() {
        return sqlSessionTemplate.selectList("com.example.products.dao.ProductMapper.selectAll");
    }

    @Override
    public List<Product> selectByProduct(Product product) {
        return sqlSessionTemplate.selectList("com.example.products.dao.ProductMapper.selectByProduct", product);
    }

    @Override
    public Product selectByPrimaryKey(String id) {
        Integer a = Integer.parseInt(id);
        return sqlSessionTemplate.selectOne("com.example.products.dao.ProductMapper.selectByPrimaryKey", a);
    }


    @Override
    public List<Product> selectByPage(int curr, int page) {
        Map paramMap=new HashMap();
        int start = (curr-1)*page;
        paramMap.put("start",start);
        paramMap.put("page",page);
        return sqlSessionTemplate.selectList("com.example.products.dao.ProductMapper.selectByPage", paramMap);
    }

    @Override
    public int deleteByProductId(Integer id) {
         int a = sqlSessionTemplate.delete("com.example.products.dao.ProductMapper.deleteByProductId", id);
        return a;
    }


    @Override
    public int insert(Product product) {
        System.out.println(product);
        return sqlSessionTemplate.insert("com.example.products.dao.ProductMapper.insert", product);
    }

    @Override
    public int updateByProduct(Product product) {
        return sqlSessionTemplate.update("com.example.products.dao.ProductMapper.updateByProduct", product);
    }

    @Override
    public List<String> queryCategoryKinds() {
        return sqlSessionTemplate.selectList("com.example.products.dao.ProductMapper.queryCategoryKinds");
    }

    @Override
    public List<String> queryCategoryBigKinds(@Param("category") String category) {
        return sqlSessionTemplate.selectList("com.example.products.dao.ProductMapper.queryCategoryBigKinds",category);
    }

    @Override
    public List<String> queryCategorySmallKinds(String category, String categoryBig) {
        Map paramMap=new HashMap();
        paramMap.put("category",category);
        paramMap.put("categoryBig",categoryBig);
        return sqlSessionTemplate.selectList("com.example.products.dao.ProductMapper.queryCategorySmallKinds",paramMap);
    }

    @Override
    public List<String> queryProductNumber(String category, String categoryBig, String categorySmall) {
        Map paramMap=new HashMap();
        paramMap.put("category",category);
        paramMap.put("categoryBig",categoryBig);
        paramMap.put("categorySmall",categorySmall);
        return sqlSessionTemplate.selectList("com.example.products.dao.ProductMapper.queryProductNumber",paramMap);
    }

    @Override
    public int queryCategoryNumber(String category) {
         List<Product> list = sqlSessionTemplate.selectList("com.example.products.dao.ProductMapper.queryCategoryNumber",category);
        return list.size();
    }

    @Override
    public List<Product> queryAllByCategory(String category) {
        List<Product> list = sqlSessionTemplate.selectList("com.example.products.dao.ProductMapper.queryCategoryNumber",category);
        return list;
    }

    @Override
    public List<String> queryCategoryBig() {
        List<String> list = sqlSessionTemplate.selectList("com.example.products.dao.ProductMapper.queryCategoryBig");
        return list;
    }

    @Override
    public List<String> queryCategorySmall() {
        List<String> list = sqlSessionTemplate.selectList("com.example.products.dao.ProductMapper.queryCategorySmall");
        return list;
    }

    @Override
    public List<Product> queryByKey(String some) {
        List<Product> list = sqlSessionTemplate.selectList("com.example.products.dao.ProductMapper.selectByKey",some);
        return list;
    }

    @Override
    public List<String>queryCategorySmallByCategory(String category) {
        return sqlSessionTemplate.selectList("com.example.products.dao.ProductMapper.queryCategorySmallByCategory",category);
    }
}
