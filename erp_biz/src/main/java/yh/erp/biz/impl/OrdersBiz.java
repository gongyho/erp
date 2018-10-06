package yh.erp.biz.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.redsum.bos.ws.Waybilldetail;
import com.redsum.bos.ws.impl.IWaybillWs;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import yh.erp.biz.IOrdersBiz;
import yh.erp.dao.IEmpDao;
import yh.erp.dao.IOrdersDao;
import yh.erp.dao.ISupplierDao;
import yh.erp.entity.Emp;
import yh.erp.entity.OrderDetail;
import yh.erp.entity.Orders;
import yh.erp.exception.ErpException;

public class OrdersBiz extends BaseBiz<Orders> implements IOrdersBiz {
	private IOrdersDao ordersDao;
	private IEmpDao empDao;
	private ISupplierDao supplierDao;
	private IWaybillWs waybillWs;

	public void setWaybillWs(IWaybillWs waybillWs) {
		this.waybillWs = waybillWs;
	}

	public void setEmpDao(IEmpDao empDao) {
		this.empDao = empDao;
	}

	public void setSupplierDao(ISupplierDao supplierDao) {
		this.supplierDao = supplierDao;
	}

	public void setOrdersDao(IOrdersDao ordersDao) {
		this.ordersDao = ordersDao;
		super.setBaseDao(ordersDao);
	}

	/**
	 * 	保存订单
	 */
	public void saveOrUpdate(Orders t) {
		t.setCreateTime(new Date());
		//t.setType(Orders.TYPE_IN);
		t.setState("0");
		double total = 0;
		for (OrderDetail detail : t.getOrderDetails()) {
			total += detail.getMoney();
			detail.setOrders(t);
			detail.setState(OrderDetail.STATE_NOT_IN);//状态
		}
		t.setTotalMoney(total);
		ordersDao.saveOrUpdate(t);
	}

	@Override
	public List<Orders> getList(Orders t1, Orders t2, Object param, int firstResult, int maxResults) {
		List<Orders> ordersList = super.getList(t1, t2, param, firstResult, maxResults);
		//员工缓存
		Map<Integer, String> empMap = new HashMap<Integer, String>();
		//供应商缓存
		Map<Integer, String> supplierMap = new HashMap<Integer, String>();
		for (Orders order : ordersList) {
			order.setCreaterName(getEmpName(order.getCreater(), empMap));
			order.setCheckerName(getEmpName(order.getChecker(), empMap));
			order.setStarterName(getEmpName(order.getStarter(), empMap));
			order.setEnderName(getEmpName(order.getEnder(), empMap));
			order.setSupplierName(getSupplierName(order.getSupplierUuid(), supplierMap));
		}
		return ordersList;
	}

	/**
	 * 审核
	 */
	@RequiresPermissions("采购订单审核")
	public void doCheck(int uuid, int empUuid) {
		Orders orders = ordersDao.get(uuid);
		//判断订单是否已经审核
		if (!Orders.STATE_CREATE.equals(orders.getState())) {
			throw new ErpException("订单已经审核过了！");
		}
		orders.setState(Orders.STATE_CHECK);
		orders.setCheckTime(new Date());
		orders.setChecker(empUuid);
	}

	/**
	 * 确认
	 */
	@RequiresPermissions("采购订单确认")
	public void doStart(int uuid, int empUuid) {
		Orders orders = ordersDao.get(uuid);
		//判断订单是否已经审核
		if (!Orders.STATE_CHECK.equals(orders.getState())) {
			throw new ErpException("订单已经确认过了！");
		}
		orders.setState(Orders.STATE_START);
		orders.setStartTime(new Date());
		orders.setStarter(empUuid);
	}

	/**
	 * 导出excel
	 *
	 * @param out
	 * @param uuid
	 */
	public void export(OutputStream out, int uuid) {
		Orders orders = ordersDao.get(uuid);
		Map<Integer, String> empMap = new HashMap<Integer, String>();
		orders.setCreaterName(getEmpName(orders.getCreater(), empMap));
		orders.setCheckerName(getEmpName(orders.getChecker(), empMap));
		orders.setStarterName(getEmpName(orders.getStarter(), empMap));
		orders.setEnderName(getEmpName(orders.getEnder(), empMap));
		orders.setSupplierName(getSupplierName(orders.getSupplierUuid(), new HashMap<Integer, String>()));
		String sheetName = "";
		if (Orders.TYPE_IN.equals(orders.getType())) {
			sheetName = "采购订单";
		}
		if (Orders.TYPE_OUT.equals(orders.getType())) {
			sheetName = "销售订单";
		}

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(sheetName);
		HSSFRow row = null;

		//创建表格样式
		HSSFCellStyle style_content = wb.createCellStyle();
		style_content.setBorderBottom(BorderStyle.THIN);//下边框
		style_content.setBorderTop(BorderStyle.THIN);//上边框
		style_content.setBorderLeft(BorderStyle.THIN);//左边框
		style_content.setBorderRight(BorderStyle.THIN);//右边框
		//设置居中
		style_content.setAlignment(HorizontalAlignment.CENTER);
		style_content.setVerticalAlignment(VerticalAlignment.CENTER);
		//创建标题样式
		HSSFCellStyle style_title = wb.createCellStyle();
		style_title.setAlignment(HorizontalAlignment.CENTER);
		style_title.setVerticalAlignment(VerticalAlignment.CENTER);

		//字体设置
		HSSFFont font_content = wb.createFont();
		font_content.setFontName("宋体");
		font_content.setFontHeightInPoints((short) 11);
		style_content.setFont(font_content);

		HSSFFont font_title = wb.createFont();
		font_title.setFontName("黑体");
		font_title.setBold(true);
		font_title.setFontHeightInPoints((short) 18);
		style_title.setFont(font_title);


		//合并单元格
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 3));
		sheet.addMergedRegion(new CellRangeAddress(7, 7, 0, 3));

		int totalRow = 10 + orders.getOrderDetails().size();
		//创建标题
		sheet.createRow(0).createCell(0);
		//为单元格设置样式
		for (int i = 2; i < totalRow; i++) {
			row = sheet.createRow(i);
			for (int j = 0; j < 4; j++) {
				row.createCell(j).setCellStyle(style_content);
				//设置列宽
				sheet.setColumnWidth(j, 6000);
			}
			//设置行高
			row.setHeight((short) 500);
		}
		sheet.getRow(0).getCell(0).setCellStyle(style_title);

		//设置名称
		sheet.getRow(0).getCell(0).setCellValue(sheetName);

		sheet.getRow(2).getCell(0).setCellValue("供应商");
		sheet.getRow(3).getCell(0).setCellValue("下单日期");
		sheet.getRow(4).getCell(0).setCellValue("审核时期");
		sheet.getRow(5).getCell(0).setCellValue("采购日期");
		if (Orders.TYPE_IN.equals(orders.getType())) {
			sheet.getRow(6).getCell(0).setCellValue("入库日期");
		}
		if (Orders.TYPE_OUT.equals(orders.getType())) {
			sheet.getRow(6).getCell(0).setCellValue("出库日期");
		}
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
		//设置信息
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sheet.getRow(2).getCell(1).setCellValue(orders.getSupplierName());

		if (null != orders.getCreateTime()) {
			sheet.getRow(3).getCell(1).setCellValue(sdf.format(orders.getCreateTime()));
		}
		if (null != orders.getCheckTime()) {
			sheet.getRow(4).getCell(1).setCellValue(sdf.format(orders.getCheckTime()));
		}
		if (null != orders.getStartTime()) {
			sheet.getRow(5).getCell(1).setCellValue(sdf.format(orders.getStartTime()));
		}
		if (null != orders.getEndTime()) {
			sheet.getRow(6).getCell(1).setCellValue(sdf.format(orders.getEndTime()));
		}

		sheet.getRow(3).getCell(3).setCellValue(orders.getCreaterName());
		sheet.getRow(4).getCell(3).setCellValue(orders.getCheckerName());
		sheet.getRow(5).getCell(3).setCellValue(orders.getStarterName());
		sheet.getRow(6).getCell(3).setCellValue(orders.getEnderName());

		int startIndex = 9;
		for (OrderDetail detail : orders.getOrderDetails()) {
			sheet.getRow(startIndex).getCell(0).setCellValue(detail.getGoodsName());
			sheet.getRow(startIndex).getCell(1).setCellValue(detail.getNum());
			sheet.getRow(startIndex).getCell(2).setCellValue(detail.getPrice());
			sheet.getRow(startIndex).getCell(3).setCellValue(detail.getMoney());
			startIndex++;
		}
		sheet.getRow(startIndex).getCell(0).setCellValue("合计");
		sheet.getRow(startIndex).getCell(3).setCellValue(orders.getTotalMoney());

		try {
			wb.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				wb.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 追踪订单详情
	 * @param sn
	 * @return
	 */
	@Override
	public List<Waybilldetail> waybillList(int sn) {
		return waybillWs.waybilldetailList((long) sn);
	}

	/**
	 * 得到员工姓名
	 *
	 * @param id
	 * @param empMap
	 * @return
	 */
	private String getEmpName(Integer id, Map<Integer, String> empMap) {
		//id为空
		if (id == null) {
			return null;
		}
		//有缓存
		if (null != empMap.get(id)) {
			return empMap.get(id);
		}
		String empName = empDao.get(id).getName();
		empMap.put(id, empName);
		return empName;
	}

	/**
	 * 得到供应商或者顾客姓名
	 *
	 * @param id
	 * @param supplierMap
	 * @return
	 */
	private String getSupplierName(Integer id, Map<Integer, String> supplierMap) {
		//id为空
		if (id == null) {
			return null;
		}
		//有缓存
		if (null != supplierMap.get(id)) {
			return supplierMap.get(id);
		}
		String supplierName = supplierDao.get(id).getName();
		supplierMap.put(id, supplierName);
		return supplierName;
	}
}
