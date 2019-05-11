package com.example.products.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.products.dao.UserInfoDao;
import com.example.products.pojo.CarProduct;
import com.example.products.service.ProductsService;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xf on 2018/12/26 16:55
 **/
@Controller
@RequestMapping("toWord")
public class ToWordController {
    private final ProductsService productsService;
    private final UserInfoDao userInfoDao;

    @Autowired
    public ToWordController(ProductsService productsService, UserInfoDao userInfoDao) {
        this.productsService = productsService;
        this.userInfoDao = userInfoDao;
    }

    private String Customer ;
    private String ProjectName;
    private String Author;
    private String AuthorPhone;
    private String TotalPrice;

    @RequestMapping("/detail")
    @ResponseBody
    public Map<String, String> getSome(@Param("customer")String customer, @Param("projectName")String projectName
            , @Param("author")String author, @Param("totalPrice")String totalPrice){
        Customer = customer;
        ProjectName = projectName;
        Author = author;
        TotalPrice = totalPrice;
        AuthorPhone = userInfoDao.selectByName(author).getPassword();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("info","ok");
        return hashMap;
    }

    @RequestMapping("/get")
    public void getWord(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //ajax无法返回文件流  前端用js隐藏post表单 传递参数
        List<CarProduct> cp = JSONArray.parseArray(request.getParameter("data"),CarProduct.class);

        DecimalFormat decimalFormat=new DecimalFormat(".00");
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


        XWPFParagraph title1 = document.createParagraph();
        //设置段落居中
        title1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun title1Run = title1.createRun();
        title1Run.setText("昆山德舜昌机械科技有限公司");
        title1Run.addCarriageReturn(); //添加回车换行
        title1Run.setFontSize(26);
        title1Run.setFontFamily("隶书");
        title1Run.setBold(true);

        XWPFParagraph content1 = document.createParagraph();
        //设置段落居中
        content1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun content1Run = title1.createRun();
        content1Run.setText("地址：江苏省昆山开发区太湖路88号");
        content1Run.addCarriageReturn();
        content1Run.setFontSize(11);
        content1Run.setFontFamily("宋体");


        XWPFParagraph content2 = document.createParagraph();
        //设置段落居中
        content2.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun content2Run = title1.createRun();
        content2Run.setText("电话: 0512-55188888 ");
        content2Run.addCarriageReturn();
        content2Run.setFontSize(11);
        content2Run.setFontFamily("宋体");

        XWPFParagraph content3 = document.createParagraph();
        //设置段落居中
        content3.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun content3Run = title1.createRun();
        content3Run.setText("传真：0512-55182218");
        content3Run.addCarriageReturn();
        content3Run.setFontSize(11);
        content3Run.setFontFamily("宋体");

        XWPFParagraph content4 = document.createParagraph();
        //设置段落居中
        content4.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun content4Run = title1.createRun();
        content4Run.setText("Http:   www.chinadsc.cn");
        content4Run.addCarriageReturn();
        content4Run.setFontSize(11);
        content4Run.setFontFamily("宋体");

        XWPFParagraph content5 = document.createParagraph();
        //设置段落居中
        content5.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun content5Run = title1.createRun();
        content5Run.setText("Email:  luhb@chinadsc.cn ");
        content5Run.addCarriageReturn();
        content5Run.setFontSize(11);
        content5Run.setFontFamily("宋体");

        //项目信息表格
        XWPFTable someInfo = document.createTable();
        //列宽自动分割
        CTTblWidth someInfoWidth = someInfo.getCTTbl().addNewTblPr().addNewTblW();
        someInfoWidth.setType(STTblWidth.DXA);
        someInfoWidth.setW(BigInteger.valueOf(9072));

        XWPFTableRow someInfoRowOne = someInfo.getRow(0);
        someInfoRowOne.getCell(0).setText("客户名称：");
        someInfoRowOne.addNewTableCell().setText(Customer);
        someInfoRowOne.addNewTableCell().setText("日期：");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        someInfoRowOne.addNewTableCell().setText(dateFormat.format(new Date()));

        XWPFTableRow someInfoRow = someInfo.createRow();
        someInfoRow.getCell(0).setText("");
        someInfoRow.getCell(1).setText("");
        someInfoRow.getCell(2).setText("报价有效期:");
        someInfoRow.getCell(3).setText("30天");

        XWPFTableRow someInfoRow1 = someInfo.createRow();
        someInfoRow1.getCell(0).setText("");
        someInfoRow1.getCell(1).setText("");
        someInfoRow1.getCell(2).setText("联系人:");
        someInfoRow1.getCell(3).setText(Author);

        XWPFTableRow someInfoRow2 = someInfo.createRow();
        someInfoRow2.getCell(0).setText("");
        someInfoRow2.getCell(1).setText("");
        someInfoRow2.getCell(2).setText("电话:");
        someInfoRow2.getCell(3).setText(AuthorPhone);

        //换行
        XWPFParagraph A = document.createParagraph();
        XWPFRun ARun = A.createRun();
        ARun.setText("\r");
        for(int i = 0;i<10;i++){
            ARun.addCarriageReturn();
        }


        //添加文档标题
        XWPFParagraph projectTitle = document.createParagraph();
        //设置段落居中
        projectTitle.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun projectTitleRun = projectTitle.createRun();
        projectTitleRun.setText(ProjectName+"规格书");
        projectTitleRun.setFontSize(20);
        projectTitleRun.setFontFamily("宋体");

        XWPFParagraph B = document.createParagraph();
        XWPFRun BRun = B.createRun();
        BRun.setText("\r");
        for(int i = 0;i<14;i++){
            BRun.addCarriageReturn();
        }

        //添加表格标题
        XWPFParagraph titleParagraph = document.createParagraph();
        //设置段落居中
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleParagraphRun = titleParagraph.createRun();
        titleParagraphRun.setText(ProjectName+"配置表");
        titleParagraphRun.setFontSize(14);


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

        Double totalPrice = 0.00;
        DecimalFormat df = new DecimalFormat("#.00");
        //表格每行内容赋值
        for(int i = 0;i<cp.size();i++){
            Double eachPrice;
            XWPFTableRow x = ComTable.createRow();
            x.getCell(0).setText(String.valueOf(i+1));
            x.getCell(1).setText(cp.get(i).getProductNumber());
            x.getCell(2).setText(cp.get(i).getProductName());
            x.getCell(3).setText(cp.get(i).getNumber());
            x.getCell(5).setText(df.format(Double.parseDouble(cp.get(i).getPrice())));
            eachPrice = Double.parseDouble(cp.get(i).getPrice())*Double.parseDouble(cp.get(i).getNumber());
            x.getCell(6).setText(df.format(eachPrice));
            totalPrice =  totalPrice + eachPrice;
        }

        XWPFParagraph price = document.createParagraph();
        XWPFRun pr = price.createRun();
        pr.setFontSize(11);
        pr.setFontFamily("宋体");
        pr.setBold(true);
        pr.setText("项目总价："+decimalFormat.format(totalPrice));

        //换行
        XWPFParagraph p1 = document.createParagraph();
        XWPFRun pr1 = p1.createRun();
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

        XWPFParagraph xp21 = document.createParagraph();
        XWPFRun r21 = xp21.createRun();
        r21.setFontSize(11);
        r21.setFontFamily("宋体");
        r21.setText("1.电机：");
        XWPFParagraph xp22 = document.createParagraph();
        XWPFRun r22 = xp22.createRun();
        r22.setFontSize(11);
        r22.setFontFamily("宋体");
        r22.setText("2.变频器：");
        XWPFParagraph xp23 = document.createParagraph();
        XWPFRun r23 = xp23.createRun();
        r23.setFontSize(11);
        r23.setFontFamily("宋体");
        r23.setText("3.触摸屏：");
        XWPFParagraph xp24 = document.createParagraph();
        XWPFRun r24 = xp24.createRun();
        r24.setFontSize(11);
        r24.setFontFamily("宋体");
        r24.setText("4.PLC:");
        XWPFParagraph xp25 = document.createParagraph();
        XWPFRun r25 = xp25.createRun();
        r25.setFontSize(11);
        r25.setFontFamily("宋体");
        r25.setText("5.低压电器：");
        XWPFParagraph xp26 = document.createParagraph();
        XWPFRun r26 = xp26.createRun();
        r26.setFontSize(11);
        r26.setFontFamily("宋体");
        r26.setText("6.轴承：");

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
        String fileName = ProjectName+"规格书.doc";
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
