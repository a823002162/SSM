package com.atguigu.dao.impl;

import com.atguigu.bean.Book;
import com.atguigu.dao.BaseDAO;
import com.atguigu.dao.BookDAO;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 4/26/2023 9:18 PM
 */
public class BookDAOImpl extends BaseDAO implements BookDAO {
    @Override
    public boolean addBook(Book book) throws SQLException {
        return update("insert into t_book values(0,?,?,?,?,?,?)",book.getBook_name(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImg_path());
    }

    @Override
    public List<Book> getBookList() throws SQLException {
        return getBeans("select * from t_book",Book.class);
    }

    @Override
    public boolean removeBook(Integer ID) throws SQLException {
        return update("delete from t_book where book_id = ?",ID);
    }

    @Override
    public boolean editBook(Book book) throws SQLException {
        return update("update t_book set price=?,sales=?,stock=? where book_id=?",book.getPrice(),book.getSales(),book.getStock(),book.getBook_id());
    }

    @Override
    public Book getBookById(Integer id) throws SQLException {
        return getBean("select * from t_book where book_id = ?",Book.class,id);
    }

    @Override
    public boolean updateStockAndSales(Object[][] params) throws SQLException {
        return update("update t_book set sales=sales+?,stock=stock-? where book_id = ? ",params);
    }
}
