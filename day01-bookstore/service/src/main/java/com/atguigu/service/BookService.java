package com.atguigu.service;

import com.atguigu.bean.Book;

import java.sql.SQLException;
import java.util.List;


/**
 * @Description:
 * @Author: Gavin
 * @Date: 4/26/2023 9:19 PM
 */
public interface BookService {
    List<Book> getBookList() throws SQLException;
    boolean removeBook(Integer ID) throws SQLException;

    boolean addBook(Book book) throws SQLException;

    boolean editBook(Book book) throws SQLException;

    Book getBookById(Integer ID) throws SQLException;

    boolean updateStockAndSales(Object[][]params) throws SQLException;
}
