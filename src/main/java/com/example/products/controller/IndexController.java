package com.example.products.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xf on 2018/11/20 9:50
 **/
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("userInfo")
    public String userInfo(){
        return "userInfo";
    }

    @RequestMapping("table")
    public String table(){
        return "echarts";
    }

    @RequestMapping("tab")
    public String tab(@Param("key") String key, Model model){
        model.addAttribute("category",key);
        return "tabContent";
    }
}
