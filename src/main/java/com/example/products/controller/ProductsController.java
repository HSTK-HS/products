package com.example.products.controller;

import com.alibaba.fastjson.JSON;
import com.example.products.dao.ProductsDao;
import com.example.products.pojo.CarProduct;
import com.example.products.pojo.Product;
import com.example.products.service.ProductsService;
import com.example.products.util.Layui;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xf on 2018/11/8 10:47
 **/
@Controller
@RequestMapping("query")
public class ProductsController {
    private final ProductsService productsService;

    private final ProductsDao productsDao;

    @Autowired
    public ProductsController(ProductsService productsService, ProductsDao productsDao) {
        this.productsService = productsService;
        this.productsDao = productsDao;
    }

    /**
     * 查询所有产品
     *
     * @param
     * @return
     */
    @RequestMapping("/all")
    @ResponseBody
    public Layui queryAll() {
        List<Product> list = productsService.selectAll();
        int count = list.size();
        return Layui.data(list, count, "查询成功");
    }

    /**
     * 根据id查询产品
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/id")
    public String selectByPrimaryKey(Model model, @Param("id") String id) {
        Product product = productsService.selectByPrimaryKey(Integer.parseInt(id));
        model.addAttribute("product", product);
        return "index";
    }

    /**
     * 根据id查询产品 用于购物车信息查询
     *
     * @param model
     * @param ids
     * @returns
     */
    @RequestMapping("/ids")
    @ResponseBody
    public Layui selectByPrimaryKeys(Model model, @RequestParam("ids") String ids) {
        String[] idList = ids.split(",");
        List<CarProduct> result = new ArrayList<>();
        for (String s : idList) {
            Product p = productsService.selectByPrimaryKey(Integer.parseInt(s));
            CarProduct cp = new CarProduct(p.getId(),p.getProductNumber(),p.getProductName(),p.getCategory(),p.getCategoryBig(),
            p.getCategorySmall(),p.getDirection(),p.getStandard(),p.getRemarks(),p.getUrl(),"1");
            result.add(cp);
        }
        return Layui.data(result, idList.length, "查询成功");

    }

    /**
     * 根据page查询产品
     *
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public Layui selectByPage(@RequestParam(value = "page", defaultValue = "1") String page, @RequestParam(value = "size", defaultValue = "20") String size) {
        List<Product> product = productsService.selectByPage(Integer.parseInt(page), Integer.parseInt(size));
        List<Product> list = productsService.selectAll();
        return Layui.data(product, list.size(), "查询成功");
    }

    /**
     *  侧边栏查询 根据产品种类 在tab上显示所有该类信息
     * @param category
     * @return
     */
    @RequestMapping("/sideCategory")
    @ResponseBody
    public Layui selectByCategory(@Param("category") String category){
        List<Product> list = productsDao.queryByKey(category);
        return Layui.data(list, list.size(), "查询成功");
    }


    /**
     * 多条件查询产品  只能用equals 不然会出错
     * @param product
     * @return
     */
    @RequestMapping("/product")
    @ResponseBody
    public String selectByProduct(@Param("product") Product product) {
        if (product.getCategory().contains("请选择") || product.getCategory().equals("")) {
            product.setCategory(null);
        }
        if (product.getCategoryBig().contains("请选择") || product.getCategoryBig().equals("")) {
            product.setCategoryBig(null);
        }
        if (product.getCategorySmall().contains("请选择") || product.getCategorySmall().equals("")) {
            product.setCategorySmall(null);
        }
        if (product.getProductNumber().contains("请选择") || product.getProductNumber().equals("")) {
            product.setProductNumber(null);
        }
        List<Product> list = null;
        list = productsService.selectByProduct(product);
        return JSON.toJSONString(list);
    }

    /**
     * 多条件查询产品   不能用equals 会出错
     * @param product
     * @return
     */
    @RequestMapping("/product2")
    @ResponseBody
    public String selectByProduct2(@Param("product") Product product) {

        if (product.getCategory() == "") {
            product.setCategory(null);
        }
        if (product.getCategoryBig() == "") {
            product.setCategoryBig(null);
        }
        if (product.getCategorySmall() == "") {
            product.setCategorySmall(null);
        }
        List<Product> list = null;
        list = productsService.selectByProduct(product);
        return JSON.toJSONString(list);
    }

    /**
     * 根据主键删除产品
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String deleteByProductId(@Param("id") Integer id) {
        Integer flag = 0;
        flag = productsService.deleteByProductId(id);
        Map<String, String> result = new HashMap<String, String>();
        if (flag == 1) {
            result.put("msg", "删除成功");
        } else
            result.put("msg", "删除失败");
        return JSON.toJSONString(result);
    }

    @RequestMapping("/deleteLots")
    @ResponseBody
    public String deleteLots(@RequestBody String[] arr) {
        Integer flag = 0;
        for (String anArr : arr) {
            int a = productsService.deleteByProductId(Integer.parseInt(anArr));
            if (a == 1) flag++;
        }
        Map<String, String> result = new HashMap<String, String>();
        if (flag == arr.length) {
            result.put("msg", "删除成功");
        } else
            result.put("msg", "删除失败");
        return JSON.toJSONString(result);
    }

    /**
     * 添加产品  主键自增
     *
     * @param product
     * @return
     */
    @RequestMapping("/insert")
    @ResponseBody
    public String insert(@RequestBody Product product) {
        Integer flag = 0;
        flag = productsService.insert(product);
        Map<String, String> result = new HashMap<String, String>();
        if (flag == 1) {
            result.put("msg", "添加成功");
        } else
            result.put("msg", "添加失败");
        return JSON.toJSONString(result);
    }


    /**
     * 修改产品信息  必须含有主键
     *
     * @param product
     * @return
     */
    @RequestMapping("/updatePro")
    @ResponseBody
    public String updateByProduct(@RequestBody Product product) {
        Integer flag = 0;
        flag = productsService.updateByProduct(product);
        Map<String, String> result = new HashMap<String, String>();
        if (flag == 1) {
            result.put("msg", "修改成功");
        } else
            result.put("msg", "修改失败");
        return JSON.toJSONString(result);
    }

    @RequestMapping("/kinds")
    @ResponseBody
    public String queryCategory() {
        List<Map> result = new ArrayList<>();
        List<String> category = productsDao.queryCategoryKinds();
        int a = 1;
        for (String ca : category) {
            Map<String, Object> main = new HashMap<>();
            List<Map> cateList = new ArrayList<>();
            List<String> bigKinds = productsDao.queryCategoryBigKinds(ca);
            int b = 1;
            for (String big : bigKinds) {
                Map<String, Object> cate = new HashMap<>();
                List<Map> cateBigList = new ArrayList<>();
                List<String> smallKinds = productsDao.queryCategorySmallKinds(ca, big);
                int c = 1;
                for (String small : smallKinds) {
                    Map<String, Object> bigMap = new HashMap<String, Object>();
                    List<Map> cateSmallList = new ArrayList<>();
                    List<String> productNumber = productsDao.queryProductNumber(ca, big, small);
                    int d = 1;
                    for (String product : productNumber) {
                        Map<String, Object> smallMap = new HashMap<String, Object>();
                        smallMap.put("id", String.format("%02d", a) + String.format("%02d", b) + String.format("%02d", c) + String.format("%02d", d++));
                        smallMap.put("name", product);
                        cateSmallList.add(smallMap);
                    }
                    bigMap.put("id", String.format("%02d", a) + String.format("%02d", b) + String.format("%02d", c++));
                    bigMap.put("name", small);
                    bigMap.put("children", cateSmallList);
                    cateBigList.add(bigMap);
                }
                cate.put("id", String.format("%02d", a) + String.format("%02d", b++));
                cate.put("name", big);
                cate.put("children", cateBigList);
                cateList.add(cate);
            }
            main.put("id", String.format("%02d", a++));
            main.put("name", ca);
            main.put("children", cateList);
            result.add(main);
        }

        return JSON.toJSONString(result);
    }

    @RequestMapping("/kindsTable")
    @ResponseBody
    public Layui queryCategoryByEcharts() {
        List l = new ArrayList();
        Map<String, Object> r1 = new HashMap<>();
        List<Map> result = new ArrayList<>();
        List<String> category = productsDao.queryCategoryKinds();
        for (String ca : category) {
            Map<String, Object> main = new HashMap<>();
            List<Map> cateList = new ArrayList<>();
            List<String> bigKinds = productsDao.queryCategoryBigKinds(ca);
            for (String big : bigKinds) {
                Map<String, Object> cate = new HashMap<>();
                List<Map> cateBigList = new ArrayList<>();
                List<String> smallKinds = productsDao.queryCategorySmallKinds(ca, big);
                for (String small : smallKinds) {
                    Map<String, Object> bigMap = new HashMap<String, Object>();
                    List<Map> cateSmallList = new ArrayList<>();
                    List<String> productNumber = productsDao.queryProductNumber(ca, big, small);
                    for (String product : productNumber) {
                        Map<String, Object> smallMap = new HashMap<String, Object>();
                        smallMap.put("name", product);
                        smallMap.put("value", product);
                        cateSmallList.add(smallMap);
                    }
                    bigMap.put("name", small);
                    bigMap.put("children", cateSmallList);
                    cateBigList.add(bigMap);
                }
                cate.put("name", big);
                cate.put("children", cateBigList);
                cateList.add(cate);
            }
            main.put("name", ca);
            main.put("children", cateList);
            result.add(main);
        }
        r1.put("name","种类");
        r1.put("children",result);
        l.add(r1);
        return Layui.data(l,l.size(), "查询成功");
    }

    @RequestMapping("/c")
    @ResponseBody
    public List<Map> getCategory() {
        List<Map> result = productsService.queryCategory();
        return result;
    }

    @RequestMapping("/cb")
    @ResponseBody
    public List<Map> getCategoryBig() {
        List<Map> result = productsService.queryCategoryBig();
        return result;
    }

    /**
     * 查询所有 细分码
     * @return
     */
    @RequestMapping("/cs")
    @ResponseBody
    public List<Map> getCategorySmall() {
        List<Map> result = productsService.queryCategorySmall();
        return result;
    }

    /**
     * 根据 产品种类查询 大类码
     * @param category
     * @return
     */
    @RequestMapping("/cbk")
    @ResponseBody
    public List<Map> getCategoryBigKinds(String category) {
        List<Map> result = productsService.queryCategoryBigKinds(category);
        return result;
    }

    /**
     * 根据 种类 大类码 查询 细分码
     * @param category
     * @param categoryBig
     * @return
     */
    @RequestMapping("/csk")
    @ResponseBody
    public List<Map> getCategorySmallKinds(String category,String categoryBig) {
        List<Map> result = productsService.queryCategorySmallKinds(category,categoryBig);
        return result;
    }

    /**
     * 根据产品种类查出 细分码
     */
    @RequestMapping("/queryCSBC")
    @ResponseBody
    public List<Map> selectCategorySmallByCategory(@Param("category") String category){
        List<Map> result = productsService.queryCategorySmallByCategory(category);
        return result;
    }

    @RequestMapping("/getTable")
    @ResponseBody
    public Layui getTableData() {
        List<Map> result = productsService.getTableData();
        return Layui.data(result, result.size(), "查询成功");
    }

    @RequestMapping("/key")
    @ResponseBody
    public List<Product> selectByKey(String some){
        List<Product> list = productsDao.queryByKey(some);
        return list;
    }

    @RequestMapping("/change")
    public String toChange() {
        return "change";
    }

    @RequestMapping("/add")
    public String toInsert() {
        return "insert";
    }

    @RequestMapping("/update")
    public String toUpdate() {
        return "update";
    }

    @RequestMapping("/index")
    public String toIndex() {
        return "index";
    }

    @RequestMapping("/test")
    public String test() {
        return "login";
    }


    @RequestMapping("/shop")
    public String shop() {
        return "shop";
    }
}
