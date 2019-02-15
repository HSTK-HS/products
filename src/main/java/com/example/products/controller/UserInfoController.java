package com.example.products.controller;

import com.alibaba.fastjson.JSON;
import com.example.products.dao.UserInfoDao;
import com.example.products.pojo.UserInfo;
import com.example.products.util.Layui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xf on 2018/11/30 13:41
 **/
@Controller
@RequestMapping("user")
public class UserInfoController {
    private final UserInfoDao userInfoDao;

    @Autowired
    public UserInfoController(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    @RequestMapping("/all")
    @ResponseBody
    public Layui queryAll(){
        List list = userInfoDao.selectAll();
        return Layui.data(list, list.size(), "查询成功");
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String deleteById(@RequestParam("id")String id ){
        UserInfo u = new UserInfo();
        u.setId(Integer.parseInt(id));
        int flag = userInfoDao.delete(u);
        Map<String, String> result = new HashMap<String, String>();
        if(flag == 0){
            result.put("msg", "删除失败");
        }else result.put("msg", "删除成功");
        return JSON.toJSONString(result);
    }

    @RequestMapping("/insert")
    @ResponseBody
    public String insert(@RequestParam("name")String name,@RequestParam("password")String password){
        UserInfo u = new UserInfo();
        u.setName(name);
        u.setPassword(password);
        int flag = userInfoDao.insert(u);
        Map<String, String> result = new HashMap<String, String>();
        if (flag == 1) {
            result.put("msg", "添加成功");
        } else
            result.put("msg", "添加失败");
        return JSON.toJSONString(result);
    }

}
