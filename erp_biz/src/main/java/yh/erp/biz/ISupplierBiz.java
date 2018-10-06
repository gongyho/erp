package yh.erp.biz;

import yh.erp.entity.Supplier;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface ISupplierBiz extends IBaseBiz<Supplier> {
    void export(OutputStream out,Supplier t1);
    void doImport(InputStream in) throws IOException;
}
