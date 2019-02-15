package com.example.products.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.products.pojo.CarProduct;
import com.example.products.service.ProductsService;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by xf on 2018/12/26 16:55
 **/
@Controller
@RequestMapping("toWord")
public class ToWordController {
    private final ProductsService productsService;

    @Autowired
    public ToWordController(ProductsService productsService) {
        this.productsService = productsService;
    }


    @RequestMapping("/get")
    public void getWord(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //ajax无法返回文件流  前端用js隐藏post表单 传递参数
        List<CarProduct> cp = JSONArray.parseArray(request.getParameter("data"),CarProduct.class);

        //------------创建Word文档--------------------------------------
        XWPFDocument  document= new XWPFDocument();

        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);

        //添加页眉
        CTP ctpHeader = CTP.Factory.newInstance();
        CTR ctrHeader = ctpHeader.addNewR();
        CTText ctHeader = ctrHeader.addNewT();
        String headerText = "昆山德舜昌机械科技有限公司";
        ctHeader.setStringValue(headerText);
        XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, document);

        //设置为左对齐
        headerParagraph.setAlignment(ParagraphAlignment.LEFT);
        XWPFParagraph[] parsHeader = new XWPFParagraph[1];
        parsHeader[0] = headerParagraph;
        policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);

        //设置模板读取的样式  ----失败 无反应
        //XWPFStyles newStyles = document.createStyles();
        //newStyles.setStyles(wordStyles);
        //System.out.println(wordStyles);

        // 直接设置样式  ----失败 无反应
        /*addCustomHeadingStyle(document, "标题 1", 1);
        addCustomHeadingStyle(document, "标题 2", 2);*/


        //添加标题
        XWPFParagraph titleParagraph = document.createParagraph();
        //设置段落居中
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun titleParagraphRun = titleParagraph.createRun();
        titleParagraphRun.setText("XXX配置表");
        titleParagraphRun.setFontSize(20);

        //换行
        XWPFParagraph paragraph1 = document.createParagraph();
        XWPFRun paragraphRun1 = paragraph1.createRun();
        paragraphRun1.setText("\r");


        //---------------------建立表格----------
        XWPFTable ComTable = document.createTable();

        //列宽自动分割
        CTTblWidth comTableWidth = ComTable.getCTTbl().addNewTblPr().addNewTblW();
        comTableWidth.setType(STTblWidth.DXA);
        comTableWidth.setW(BigInteger.valueOf(9072));

        //表格第一行
        XWPFTableRow comTableRowOne = ComTable.getRow(0);
        comTableRowOne.getCell(0).setText("序号");
        comTableRowOne.addNewTableCell().setText("产品型号");
        comTableRowOne.addNewTableCell().setText("货品名称");
        comTableRowOne.addNewTableCell().setText("数量");
        comTableRowOne.addNewTableCell().setText("单位");
        comTableRowOne.addNewTableCell().setText("单价");
        comTableRowOne.addNewTableCell().setText("金额");
        comTableRowOne.addNewTableCell().setText("备注");

        //表格每行内容赋值
        for(int i = 0;i<cp.size();i++){
            XWPFTableRow x = ComTable.createRow();
            x.getCell(0).setText(String.valueOf(i+1));
            x.getCell(1).setText(cp.get(i).getProductNumber());
            x.getCell(2).setText(cp.get(i).getProductName());
            x.getCell(3).setText(cp.get(i).getNumber());
        }

        //换行
        XWPFParagraph p1 = document.createParagraph();
        XWPFRun pr1 = paragraph1.createRun();
        pr1.setText("\r");
        // ---------------  添加具体参数信息 ------------

        XWPFParagraph xp = document.createParagraph();
        XWPFRun r = xp.createRun();
        r.setFontSize(11);
        r.setFontFamily("宋体");
        r.setBold(true);
        r.setText("一、用途：");

        //换行
        XWPFParagraph hx1 = document.createParagraph();
        XWPFRun hx1Run = hx1.createRun();
        hx1Run.setText("\r");

        XWPFParagraph xp2 = document.createParagraph();
        XWPFRun r2 = xp2.createRun();
        r2.setFontSize(11);
        r2.setFontFamily("宋体");
        r2.setBold(true);
        r2.setText("二、 主要配件品牌：");

        //换行
        XWPFParagraph hx2 = document.createParagraph();
        XWPFRun hx2Run = hx2.createRun();
        hx2Run.setText("\r");

        XWPFParagraph xp3 = document.createParagraph();
        XWPFRun r3 = xp3.createRun();
        r3.setFontSize(11);
        r3.setFontFamily("宋体");
        r3.setBold(true);
        r3.setText("三、 主要规格及性能：");

        //换行
        XWPFParagraph hx3 = document.createParagraph();
        XWPFRun hx3Run = hx3.createRun();
        hx3Run.setText("\r");

        XWPFParagraph xp4 = document.createParagraph();
        XWPFRun r4 = xp4.createRun();
        r4.setFontSize(11);
        r4.setFontFamily("宋体");
        r4.setBold(true);
        r4.setText("四、 技术规格：");


        for(int j = 0;j<cp.size();j++){
            XWPFParagraph cs1 = document.createParagraph();
            XWPFRun csRun = cs1.createRun();
            csRun.setFontSize(11);
            csRun.setBold(true);
            csRun.setFontFamily("宋体");
            csRun.setText(Integer.toString(j+1)+"."+cp.get(j).getProductName());

            String[] content = null;
            try {

                String filePath = this.getClass().getClassLoader().getResource("/").getPath()+
                        "/static/upload/"+cp.get(j).getUrl();
                content = build(filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(content!=null){
                for(int i = 0;i<content.length;i++){
                    XWPFParagraph paragraph = document.createParagraph();
                    XWPFRun run = paragraph.createRun();
                    run.setFontSize(11);
                    run.setFontFamily("宋体");
                    run.setText(Integer.toString(i+1)+")."+content[i]);
                    paragraph.setIndentationLeft(400);
                }
            }
        }






        //换行
        XWPFParagraph hx4 = document.createParagraph();
        XWPFRun hx4Run = hx4.createRun();
        hx4Run.setText("\r");
        // -------------  文件创建完成 设置属性 输出 -------------
        System.out.println("create_table document written success.");
        response.reset();
        String fileName = "XXX配置表.doc";
        response.setHeader("Content-Disposition", "attachment; filename="+
        new String(fileName.getBytes("utf-8"),"ISO8859-1"));
        response.setContentType("application/octet-stream;charset=UTF-8");
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        document.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }


    private String[] build(String filePath) throws Exception {
        System.out.println("开始解析"+filePath);
        FileInputStream is = new FileInputStream(filePath);

        WordExtractor extractor = new WordExtractor(is);
        //System.out.println(extractor.getText());

        String paraTexts[] = extractor.getParagraphText();
        /*for (int i=0; i<paraTexts.length; i++) {
            System.out.println("Paragraph " + (i+1) + " : " + paraTexts[i]);
        }*/
        //HWPFDocument doc = new HWPFDocument(is);

        return paraTexts;
    }

    // 自设置样式 --失败
    /*private static void addCustomHeadingStyle(XWPFDocument docxDocument, String strStyleId, int headingLevel) {

        CTStyle ctStyle = CTStyle.Factory.newInstance();
        ctStyle.setStyleId(strStyleId);

        CTString styleName = CTString.Factory.newInstance();
        styleName.setVal(strStyleId);
        ctStyle.setName(styleName);

        CTDecimalNumber indentNumber = CTDecimalNumber.Factory.newInstance();
        indentNumber.setVal(BigInteger.valueOf(headingLevel));

        // lower number > style is more prominent in the formats bar
        ctStyle.setUiPriority(indentNumber);

        CTOnOff onoffnull = CTOnOff.Factory.newInstance();
        ctStyle.setUnhideWhenUsed(onoffnull);

        // style shows up in the formats bar
        ctStyle.setQFormat(onoffnull);

        // style defines a heading of the given level
        CTPPr ppr = CTPPr.Factory.newInstance();
        ppr.setOutlineLvl(indentNumber);
        ctStyle.setPPr(ppr);

        XWPFStyle style = new XWPFStyle(ctStyle);

        // is a null op if already defined
        XWPFStyles styles = docxDocument.createStyles();

        style.setType(STStyleType.PARAGRAPH);
        styles.addStyle(style);
    }
*/
}
