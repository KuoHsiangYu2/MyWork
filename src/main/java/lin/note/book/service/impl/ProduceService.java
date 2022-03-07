package lin.note.book.service.impl;

import lin.note.book.dao.IProductDao;
import lin.note.book.dao.impl.ProductDao;
import lin.note.book.model.ProductBean;
import lin.note.book.service.IProduceService;

public class ProduceService implements IProduceService {

    @Override
    public ProductBean getProductByBooknum(String booknum) {
        ProductBean result = null;
        IProductDao productDao = new ProductDao();
        result = productDao.getProductByBooknum(booknum);
        return result;
    }

}
