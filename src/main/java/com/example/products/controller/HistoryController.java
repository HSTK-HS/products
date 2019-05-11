package com.example.products.controller;

import com.alibaba.fastjson.JSON;
import com.example.products.dao.HistoryDao;
import com.example.products.dao.ProductsDao;
import com.example.products.dao.ProjectDao;
import com.example.products.dao.UserInfoDao;
import com.example.products.pojo.CarProduct;
import com.example.products.pojo.History;
import com.example.products.pojo.Product;
import com.example.products.pojo.ProjectInfo;
import com.example.products.util.Layui;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xf on 2019/5/8 9:04
 **/
@Controller
@RequestMapping("hist")
public class HistoryController {

    private final HistoryDao historyDao;
    private final ProjectDao projectDao;
    private final UserInfoDao userInfoDao;
    private final ProductsDao productsDao;

    @Autowired
    public HistoryController(HistoryDao historyDao, ProjectDao projectDao, UserInfoDao userInfoDao, ProductsDao productsDao) {
        this.historyDao = historyDao;
        this.projectDao = projectDao;
        this.userInfoDao = userInfoDao;
        this.productsDao = productsDao;
    }

    /**
     * 根据 项目id 查询具体记录
     * @param projectId
     * @return
     */
    @RequestMapping("/projectDetail")
    @ResponseBody
    public Layui projectDetail(@Param("projectId") String projectId){
        ArrayList<CarProduct> cList = new ArrayList<>();
        List<History> list = historyDao.selectByProject(projectId);
        for(History history:list){
            Product product = productsDao.selectByPrimaryKey(history.getProductId());
            CarProduct carProduct = new CarProduct();
            carProduct.setId(product.getId());
            carProduct.setProductNumber(product.getProductNumber());
            carProduct.setProductName(product.getProductName());
            carProduct.setCategory(product.getCategory());
            carProduct.setCategoryBig(product.getCategoryBig());
            carProduct.setCategorySmall(product.getCategorySmall());
            carProduct.setDirection(product.getDirection());
            carProduct.setDirection(product.getDirection());
            carProduct.setRemarks(product.getRemarks());
            carProduct.setUrl(product.getUrl());
            carProduct.setNumber(history.getProductNumber());
            carProduct.setPrice(history.getProductPrice());
            cList.add(carProduct);
        }
        return Layui.data(cList, cList.size(), "查询成功");
    }

    /**
     * 查询所有历史项目记录
     * @return
     */
    @RequestMapping("/selectAll")
    @ResponseBody
    public Layui selectById(){
        List<ProjectInfo> list =  projectDao.selectAll();

        return Layui.data(list, list.size(), "查询成功");
    }

    /**
     * 根据作者 查询历史项目记录
     * @return
     */
    @RequestMapping("/selectByAuthor")
    @ResponseBody
    public Layui selectByAuthor(@Param("author") String author){
        List<ProjectInfo> list =  projectDao.selectByAuthor(author);

        return Layui.data(list, list.size(), "查询成功");
    }

    /**
     * 保存项目记录
     * @param customer
     * @param projectName
     * @param body
     * @param author
     * @param totalPrice
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public String add(@Param("customer")String customer,@Param("projectName")String projectName,@Param("body") String body
    ,@Param("author")String author,@Param("totalPrice")String totalPrice){
        List<CarProduct> puppy = JSON.parseArray(body,CarProduct.class);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String projectId = dateFormat.format(new Date());
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setProjectId(projectId);
        projectInfo.setAuthor(author);
        projectInfo.setAuthorPhone(userInfoDao.selectByName(author).getPassword());
        projectInfo.setCustomer(customer);
        projectInfo.setProjectName(projectName);
        projectInfo.setCreateTime(new Date());
        projectInfo.setTotalPrice(totalPrice);
        projectDao.insert(projectInfo);

        for(int i=0;i<puppy.size();i++){
            History h = new History();
            h.setProjectId(projectId);
            h.setProductId(Integer.toString(puppy.get(i).getId()));
            h.setProductNumber(puppy.get(i).getNumber());
            h.setProductPrice(puppy.get(i).getPrice());
            historyDao.insert(h);
        }

        return JSON.toJSONString(projectInfo);
    }

    @RequestMapping("/deleteByProjectId")
    @ResponseBody
    public Layui deleteByProjectId(@Param("projectId") String projectId){

        historyDao.deleteByProjectId(projectId);
        projectDao.deleteByProjectId(projectId);

        return Layui.data(null, 0, "查询成功");
    }

    @RequestMapping("/historyDetail")
    public String historyDetail() {
        return "historyDetails";
    }

    @RequestMapping("/projectDetails")
    public String projectDetails() {
        return "projectDetails";
    }

}
