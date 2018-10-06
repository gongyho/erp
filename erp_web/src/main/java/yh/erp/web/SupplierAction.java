package yh.erp.web;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import yh.erp.biz.ISupplierBiz;
import yh.erp.entity.Supplier;
import yh.erp.exception.ErpException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class SupplierAction extends BaseAction<Supplier> {
	private ISupplierBiz supplierBiz;

	public void setSupplierBiz(ISupplierBiz supplierBiz) {
		this.supplierBiz = supplierBiz;
		super.setBaseBiz(supplierBiz);
	}
	
	private String q;
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}

	/**
	 * 得到供应商 客户
	 */
	public void list() {
		if(null == getT1()) {
			setT1(new Supplier());
		}
		getT1().setName(q);
		super.list();
	}

	File file;
	String fileFileName;
	String fileContentType;

	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}


	public void upload(){
		try {
			if(!"application/vnd.ms-excel".equals(fileContentType)){
				ajaxReturn(false,"文件类型不正确");
				return;
			}
			supplierBiz.doImport(new FileInputStream(file));
			ajaxReturn(true,"文件上传成功");
		}catch (ErpException e){
			ajaxReturn(false,e.getMessage());
		}catch (IOException e) {
			ajaxReturn(false,"文件上传失败");
			e.printStackTrace();
		}
	}

	/**
	 * 导出excel
	 */
	public void export(){
		HttpServletResponse response=  ServletActionContext.getResponse();
		String fileName="";
		if(getT1().getType().equals("1")){
			fileName="供应商.xls";
		}
		if(getT1().getType().equals("2")){
			fileName="客户.xls";
		}
		try {
			response.setHeader("Content-Disposition","attachment;filename="+new String(fileName.getBytes(),"ISO-8859-1"));
			supplierBiz.export(response.getOutputStream(),getT1());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
