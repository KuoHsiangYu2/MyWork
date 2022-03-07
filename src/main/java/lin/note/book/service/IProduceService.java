package lin.note.book.service;

import lin.note.book.model.ProductBean;

public interface IProduceService {

    // 這裡假設 booknum 是獨一無二的 主鍵【PrimaryKey】，所以只會查詢出一個『書籍』出來。.
    public abstract ProductBean getProductByBooknum(String booknum);
}
