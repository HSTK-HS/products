package com.example.products.service.impl;

import com.example.products.dao.ProductsDao;
import com.example.products.pojo.Product;
import com.example.products.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xf on 2018/11/8 11:01
 **/
@Service
public class ProductServiceImpl implements ProductsService {

    private final ProductsDao productsDao;

    @Autowired
    public ProductServiceImpl(ProductsDao productsDao) {
        this.productsDao = productsDao;
    }

    @Override
    public Product selectByPrimaryKey(Integer id) {
        return productsDao.selectByPrimaryKey(id);
    }

    @Override
    public List<Product> selectAll() {
        return productsDao.selectAll();
    }

    @Override
    public List<Product> selectByProduct(Product product) {
        return productsDao.selectByProduct(product);
    }

    @Override
    public List<Product> selectByPage(int curr, int page) {
        return productsDao.selectByPage(curr,page);
    }

    @Override
    public int deleteByProductId(Integer id) {
        return productsDao.deleteByProductId(id);
    }


    @Override
    public int insert(Product product) {
        return productsDao.insert(product);
    }

    @Override
    public int updateByProduct(Product product) {
        return productsDao.updateByProduct(product);
    }

    @Override
    public List<Map> getTableData() {
        List<Map> result = new ArrayList<Map>();
        List<String> list = productsDao.queryCategoryKinds();
        for(String category:list){
            int num = productsDao.queryCategoryNumber(category);
            HashMap h = new HashMap();
            h.put("value",num);
            h.put("name",category);
            result.add(h);
        }
        return result;
    }

    @Override
    public List<Map> queryCategory() {
        List<Map> result = new ArrayList<Map>();
        List<String> list = productsDao.queryCategoryKinds();
        toMap(result, list);
        return result;
    }

    @Override
    public List<Map> queryCategoryBig() {
        List<Map> result = new ArrayList<Map>();
        List<String> list = productsDao.queryCategoryBig();
        toMap(result, list);
        return result;
    }

    @Override
    public List<Map> queryCategorySmall() {
        List<Map> result = new ArrayList<Map>();
        List<String> list = productsDao.queryCategorySmall();
        toMap(result, list);
        return result;
    }

    @Override
    public List<Map> queryCategoryBigKinds(String category) {
        List<Map> result = new ArrayList<Map>();
        List<String> list = productsDao.queryCategoryBigKinds(category);
        toMap(result, list);
        return result;
    }

    @Override
    public List<Map> queryCategorySmallKinds(String category,String categoryBig) {
        List<Map> result = new ArrayList<Map>();
        List<String> list = productsDao.queryCategorySmallKinds(category,categoryBig);
        toMap(result, list);
        return result;
    }

    @Override
    public List<Map> queryCategorySmallByCategory(String category) {
        List<Map> result = new ArrayList<Map>();
        List<String> list = productsDao.queryCategorySmallByCategory(category);
        toMap(result, list);
        return result;
    }

    private void toMap(List<Map> result, List<String> list) {
        for(String category:list){
            HashMap h = new HashMap();
            h.put("id",category);
            h.put("text",category);
            result.add(h);
        }
    }
}
