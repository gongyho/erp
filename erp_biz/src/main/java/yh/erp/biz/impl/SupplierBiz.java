package yh.erp.biz.impl;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import yh.erp.biz.ISupplierBiz;
import yh.erp.dao.ISupplierDao;
import yh.erp.entity.Supplier;
import yh.erp.exception.ErpException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class SupplierBiz extends BaseBiz<Supplier> implements ISupplierBiz {
    private ISupplierDao supplierDao;

    public void setSupplierDao(ISupplierDao supplierDao) {
        this.supplierDao = supplierDao;
        super.setBaseDao(supplierDao);
    }

    /**
     * 导入供应商客户信息信息
     *
     * @param in excel文件输入流
     */
    @Override
    public void doImport(InputStream in) throws IOException {
        HSSFWorkbook wb = null;
        try {
            wb = new HSSFWorkbook(in);
            HSSFSheet sheet = wb.getSheetAt(0);
            //获取类型
            String type = "";
            if (sheet.getSheetName().equals("供应商")) {
                type = "1";
            } else if (sheet.getSheetName().equals("客户")) {
                type = "2";
            } else {
                throw new ErpException("工作表名称不正确");
            }
            //获取条目数
            int rowCount = sheet.getLastRowNum();
            Supplier supplier = null;
            //遍历
            for (int i = 1; i <= rowCount; i++) {
                supplier = new Supplier();
                supplier.setName(sheet.getRow(i).getCell(0).getStringCellValue());
                List<Supplier> list = supplierDao.getList(null, supplier, null);
                if (list.size() > 0) {
                    supplier = list.get(0);
                }
                supplier.setAddress(sheet.getRow(i).getCell(1).getStringCellValue());
                supplier.setContact(sheet.getRow(i).getCell(2).getStringCellValue());
                supplier.setTele(sheet.getRow(i).getCell(3).getStringCellValue());
                supplier.setEmail(sheet.getRow(i).getCell(4).getStringCellValue());

                if (list.size() == 0) {
                    supplier.setType(type);
                    supplierDao.save(supplier);
                }
            }
        } finally {
            if (null != wb) {
                wb.close();
            }
        }
    }

    /**
     * 导出excel
     *
     * @param out 输出流
     * @param t1  查询条件
     */
    public void export(OutputStream out, Supplier t1) {
        List<Supplier> list = supplierDao.getList(t1, null, null);
        //创建工作溥
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建工作表
        String sheetName = "";
        if (t1.getType().equals("1")) {
            sheetName = "供应商";
        }
        if (t1.getType().equals("2")) {
            sheetName = "客户";
        }
        HSSFSheet sheet = workbook.createSheet(sheetName);
        //创建行
        HSSFRow row = sheet.createRow(0);
        //创建单元
        HSSFCell cell = null;
        String[] head = {"名称", "地址", "联系人", "电话", "邮箱"};
        int[] colWidth = {4000, 8000, 2000, 3000, 8000};
        //设置表头
        for (int i = 0; i < 5; i++) {
            cell = row.createCell(i);
            cell.setCellValue(head[i]);
            sheet.setColumnWidth(i, colWidth[i]);
        }
        int countIndex = 1;
        for (Supplier supplier : list) {
            row = sheet.createRow(countIndex);
            row.createCell(0).setCellValue(supplier.getName());
            row.createCell(1).setCellValue(supplier.getAddress());
            row.createCell(2).setCellValue(supplier.getContact());
            row.createCell(3).setCellValue(supplier.getTele());
            row.createCell(4).setCellValue(supplier.getEmail());
            countIndex++;
        }
        try {
            //输出
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
