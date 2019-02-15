package com.example.products.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xf on 2018/11/29 14:08
 **/
public class ManageInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        Object userName = request.getSession().getAttribute("loginUser");

        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "text/html;charset=UTF-8");//这句话是解决乱码的
        response.setHeader("Access-Control-Allow-Origin", response.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        if(!userName.equals("admin")){
            request.setAttribute("msg","您没有操作权限");
            response.sendRedirect("/login/index2");
            return false;
        }
        return true;
    }
}
