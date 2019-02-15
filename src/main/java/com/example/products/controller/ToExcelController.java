package com.example.products.controller;

import com.example.products.dao.ProductsDao;
import com.example.products.pojo.Product;
import com.example.products.service.ProductsService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xf on 2018/11/29 13:06
 **/
@Controller
@RequestMapping("toExcel")
public class ToExcelController {
    private final ProductsService productsService;

    private final ProductsDao productsDao;

    @Autowired
    public ToExcelController(ProductsService productsService, ProductsDao productsDao) {
        this.productsService = productsService;
        this.productsDao = productsDao;
    }

    @RequestMapping("/get")
    public void makeExcel(HttpServletRequest request, HttpServletResponse response, @RequestParam("ids") String ids) {
        String[] idList = ids.split(",");
        List<Product> result = new ArrayList<>();
        for (String s : idList) {
            Product product = productsService.selectByPrimaryKey(Integer.parseInt(s));
            result.add(product);
        }
        //==========================  建表  ===================================================

        String fileName = "报价单.xls";
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");

        //----------  标题 字体样式  -------
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER); // 水平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 竖直居中

        //创建字体
        HSSFFont font = workbook.createFont();
        //设置字体类型
        font.setFontName("宋体");
        //设置字体是否加粗
        font.setBold(true);
        //设置字体是否倾斜
        font.setItalic(false);
        //设置字号
        font.setFontHeightInPoints((short) 15);
        //设置字体颜色
        font.setColor(IndexedColors.BLACK.index);
        //将字体加入样式
        cellStyle.setFont(font);


        // ---------- 栏目名 字体样式   --------------------
        HSSFCellStyle cellStyle2 = workbook.createCellStyle();
        cellStyle2.setAlignment(HorizontalAlignment.CENTER); // 水平居中
        cellStyle2.setVerticalAlignment(VerticalAlignment.CENTER);// 竖直居中
        cellStyle2.setBorderBottom(BorderStyle.MEDIUM);
        cellStyle2.setBorderLeft(BorderStyle.THIN);
        cellStyle2.setBorderRight(BorderStyle.THIN);
        cellStyle2.setBorderTop(BorderStyle.MEDIUM);

        //创建字体
        HSSFFont font2 = workbook.createFont();
        //设置字体类型
        font2.setFontName("宋体");
        //设置字体是否加粗
        font2.setBold(true);
        //设置字体是否倾斜
        font2.setItalic(false);
        //设置字号
        font2.setFontHeightInPoints((short) 12);
        //设置字体颜色
        font2.setColor(IndexedColors.BLACK.index);
        //将字体加入样式
        cellStyle2.setFont(font2);

        // ---------- 数据 字体样式   -------------
        HSSFCellStyle cellStyle3 = workbook.createCellStyle();
        cellStyle3.setAlignment(HorizontalAlignment.CENTER); // 水平居中
        cellStyle3.setVerticalAlignment(VerticalAlignment.CENTER);// 竖直居中
        cellStyle3.setBorderBottom(BorderStyle.THIN);
        cellStyle3.setBorderLeft(BorderStyle.THIN);
        cellStyle3.setBorderRight(BorderStyle.THIN);

        //创建字体
        HSSFFont font3 = workbook.createFont();
        //设置字体类型
        font3.setFontName("宋体");
        //设置字体是否加粗
        font3.setBold(true);
        //设置字体是否倾斜
        font3.setItalic(false);
        //设置字号
        font3.setFontHeightInPoints((short) 11);
        //设置字体颜色
        font3.setColor(IndexedColors.BLACK.index);
        //将字体加入样式
        cellStyle3.setFont(font3);

        //===================    标题生成    =================
        CellRangeAddress range = new CellRangeAddress(0, 0, 0, 7);
        sheet.addMergedRegion(range);
        //sheet.setDefaultColumnWidth(18);
        int[] width = {2000,8000,6000,2000,2000,4000,4000,4000};
        for(int i = 0;i<width.length;i++) {
            sheet.setColumnWidth(i, width[i]);
        }

        HSSFRow rowH = sheet.createRow(0);
        rowH.setHeightInPoints(40);
        HSSFCell cellh = rowH.createCell(0);
        //加载单元格样式
        cellh.setCellValue("xxxx项目报价表");
        //将设置好的样式加入Cell中
        cellh.setCellStyle(cellStyle);




        HSSFRow row = sheet.createRow(1);
        row.setHeightInPoints(25);
        //在excel表中添加表头
        String[] headers = {"序号", "货品名称", "产品型号", "数量", "单位", "单价", "金额", "备注"};
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(cellStyle2);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }



        int num = 1;
        int rowNum = 2;

        //在表中存放查询到的数据放入对应的列
        for (Product product : result) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.setHeightInPoints(18);

            Cell col0 = row1.createCell(0);
            col0.setCellValue(num++);
            col0.setCellStyle(cellStyle3);


            Cell col1 = row1.createCell(1);
            col1.setCellValue(product.getProductName());
            col1.setCellStyle(cellStyle3);

            Cell col2 = row1.createCell(2);
            col2.setCellValue(product.getProductNumber());
            col2.setCellStyle(cellStyle3);

            Cell col3 = row1.createCell(3);
            col3.setCellValue("");
            col3.setCellStyle(cellStyle3);

            Cell col4 = row1.createCell(4);
            col4.setCellValue("");
            col4.setCellStyle(cellStyle3);

            Cell col5 = row1.createCell(5);
            col5.setCellValue("");
            col5.setCellStyle(cellStyle3);

            Cell col6 = row1.createCell(6);
            col6.setCellValue("");
            col6.setCellStyle(cellStyle3);

            Cell col7 = row1.createCell(7);
            col7.setCellValue("");
            col7.setCellStyle(cellStyle3);
            rowNum++;
        }


        try {
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" +
                    new String(fileName.getBytes("utf-8"), "ISO8859-1"));
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            response.flushBuffer();
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
