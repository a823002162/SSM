package com.atguigu.dao;

import com.atguigu.bean.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDAO {
    boolean addBook(Book book) throws SQLException;
    List<Book> getBookList() throws SQLException;
    boolean removeBook(Integer ID) throws SQLException;

    boolean editBook(Book book) throws SQLException;

    Book getBookById(Integer id) throws SQLException;

    boolean updateStockAndSales(Object[][]params) throws SQLException;



}
