package yh.erp.test;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.IOException;

public class caeateOrderTemplate {
    public static void main(String[] args){
        HSSFWorkbook wb=new HSSFWorkbook();
        HSSFSheet sheet=wb.createSheet("测试");
        HSSFRow row=null;

        //创建表格样式
        HSSFCellStyle style_content=wb.createCellStyle();
        style_content.setBorderBottom(BorderStyle.THIN);//下边框
        style_content.setBorderTop(BorderStyle.THIN);//上边框
        style_content.setBorderLeft(BorderStyle.THIN);//左边框
        style_content.setBorderRight(BorderStyle.THIN);//右边框
        //设置居中
        style_content.setAlignment(HorizontalAlignment.CENTER);
        style_content.setVerticalAlignment(VerticalAlignment.CENTER);
        //创建标题样式
        HSSFCellStyle style_title=wb.createCellStyle();
        style_title.setAlignment(HorizontalAlignment.CENTER);
        style_title.setVerticalAlignment(VerticalAlignment.CENTER);

        //字体设置
        HSSFFont font_content=wb.createFont();
        font_content.setFontName("宋体");
        font_content.setFontHeightInPoints((short) 11);
        style_content.setFont(font_content);

        HSSFFont font_title=wb.createFont();
        font_title.setFontName("黑体");
        font_title.setBold(true);
        font_title.setFontHeightInPoints((short) 18);
        style_title.setFont(font_title);


        //合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
        sheet.addMergedRegion(new CellRangeAddress(2,2,1,3));
        sheet.addMergedRegion(new CellRangeAddress(7,7,0,3));

        //创建标题
        sheet.createRow(0).createCell(0);
        //为单元格设置样式
        for (int i = 2; i < 13; i++) {
            row=sheet.createRow(i);
            for (int j = 0; j < 4; j++) {
                row.createCell(j).setCellStyle(style_content);
                //设置列宽
                sheet.setColumnWidth(j,5000);
            }
            //设置行高
            row.setHeight((short) 500);
        }
        sheet.getRow(0).getCell(0).setCellStyle(style_title);

        //设置名称
        sheet.getRow(0).getCell(0).setCellValue("采购订单");

        sheet.getRow(2).getCell(0).setCellValue("供应商");
        sheet.getRow(3).getCell(0).setCellValue("下单日期");
        sheet.getRow(4).getCell(0).setCellValue("审核时期");
        sheet.getRow(5).getCell(0).setCellValue("采购日期");
        sheet.getRow(6).getCell(0).setCellValue("入库日期");
        sheet.getRow(3).getCell(2).setCellValue("经办人");
        sheet.getRow(4).getCell(2).setCellValue("经办人");
        sheet.getRow(5).getCell(2).setCellValue("经办人");
        sheet.getRow(6).getCell(2).setCellValue("经办人");

        sheet.getRow(7).getCell(0).setCellValue("订单明细");
        sheet.getRow(8).getCell(0).setCellValue("商品名称");
        sheet.getRow(8).getCell(1).setCellValue("数量");
        sheet.getRow(8).getCell(2).setCellValue("价格");
        sheet.getRow(8).getCell(3).setCellValue("金额");

        //设置行高
        sheet.getRow(0).setHeight((short) 1000);

        File file=new File("d://temlplate.xls");
        try {
            wb.write(file);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}