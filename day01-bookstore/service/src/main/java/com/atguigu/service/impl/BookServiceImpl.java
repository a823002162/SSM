package com.atguigu.service.impl;

import com.atguigu.bean.Book;
import com.atguigu.dao.BookDAO;
import com.atguigu.dao.impl.BookDAOImpl;
import com.atguigu.service.BookService;

import java.sql.SQLException;
import java.util.List;


/**
 * @Description:
 * @Author: Gavin
 * @Date: 4/26/2023 9:19 PM
 */
public class BookServiceImpl implements BookService {
    private BookDAO bookdao = new BookDAOImpl();

    @Override
    public List<Book> getBookList() throws SQLException {
        return bookdao.getBookList();
    }

    @Override
    public boolean removeBook(Integer ID) throws SQLException {
        return bookdao.removeBook(ID);
    }

    @Override
    public boolean addBook(Book book) throws SQLException {
        return bookdao.addBook(book);
    }

    @Override
    public boolean editBook(Book book) throws SQLException {
        return bookdao.editBook(book);
    }

    @Override
    public Book getBookById(Integer ID) throws SQLException {
        return bookdao.getBookById(ID);
    }

    @Override
    public boolean updateStockAndSales(Object[][] params) throws SQLException {
        return bookdao.updateStockAndSales(params);
    }
}
