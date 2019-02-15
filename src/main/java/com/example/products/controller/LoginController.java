package com.example.products.controller;

import com.example.products.dao.Impl.UserInfoDaoImpl;
import com.example.products.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Created by xf on 2018/11/19 8:37
 **/
@Controller
@RequestMapping("login")
public class LoginController {
    private final UserInfoDaoImpl userInfoDao;

    @Autowired
    public LoginController(UserInfoDaoImpl userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    /**
     * 验证很简单  只是查询数据库中有没有对应的用户名 密码，唉~
     * @param u
     * @param password
     * @param request
     * @return
     */
    @RequestMapping("/check")
    public String checkUser(@RequestParam("username") String u, @RequestParam("password") String password,
                                  HttpServletRequest request) {
        //ModelAndView mav = new ModelAndView();
        UserInfo userInfo = new UserInfo();
        userInfo.setPassword(password);
        userInfo.setName(u);
        UserInfo user = userInfoDao.selectByInfo(userInfo);
        if (user != null) {
            request.getSession().setAttribute("loginUser", user.getName());
            //mav.setViewName("index");
            return "index";
        } else {
            //mav.addObject("loginUser", "不存在该用户");
            request.getSession().setAttribute("loginUser","不存在该用户");
            //mav.setViewName("login");
            return "login";
        }
        //return mav;
    }

    @RequestMapping("/index")
    public String login(){
        return "login";
    }

    @RequestMapping("/index2")
    public String login2(){
        return "login2";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("loginUser");
        return "login";
    }
}
