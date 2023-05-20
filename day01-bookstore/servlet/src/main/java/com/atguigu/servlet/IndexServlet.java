package com.atguigu.servlet;

import com.atguigu.bean.Book;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.servlet.base.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 4/26/2023 7:01 PM
 */
@WebServlet("/index.html")
public class IndexServlet extends ViewBaseServlet {
    BookService bookService = new BookServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> bookList = null;
        try {
            bookList = bookService.getBookList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // System.out.println(bookList);
        req.setAttribute("bookList",bookList);
        try {
            processTemplate("../index",req,resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
