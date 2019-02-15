package com.example.products.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by xf on 2018/11/19 12:43
 **/
@Configuration
public class MySpringConfig implements WebMvcConfigurer {
    /**
     * 功能描述:
     *  配置静态资源,避免静态资源请求被拦截
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**")
                .addResourceLocations("classpath:/templates/");
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                //addPathPatterns 用于添加拦截规则
                .addPathPatterns("/**")

                //excludePathPatterns 用于排除拦截
                //.excludePathPatterns("/**")
                .excludePathPatterns("/login/index")
                .excludePathPatterns("/login/check")
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/templates/**");
        registry.addInterceptor(new ManageInterceptor())
                //addPathPatterns 用于添加拦截规则
                .addPathPatterns("/query/change")
                .addPathPatterns("/userInfo")
                //excludePathPatterns 用于排除拦截
                //.excludePathPatterns("/**")
                .excludePathPatterns("/login/index")
                .excludePathPatterns("/login/check")
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/templates/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路径
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                .allowedOrigins("*")
                //是否允许证书 不再默认开启
                .allowCredentials(true)
                //设置允许的方法
                .allowedMethods("*")
                //跨域允许时间
                .maxAge(3600);
    }

    /**
     * 添加默认主页，访问域名或者端口跳转
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:login.html");
    }
}
