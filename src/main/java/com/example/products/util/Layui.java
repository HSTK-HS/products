package com.example.products.util;

import java.util.HashMap;
import java.util.List;

/**
 * Created by xf on 2018/6/7 10:25
 **/
public class Layui extends HashMap<String,Object> {
    public static Layui data(List<?> data,Integer count,String msg){
        Layui r = new Layui();
        r.put("msg", msg);
        r.put("code", 0);
        r.put("data", data);
        r.put("count", count);
        return r;
    }

}
