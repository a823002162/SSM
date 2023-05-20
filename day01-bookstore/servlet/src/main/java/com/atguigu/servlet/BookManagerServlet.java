package com.atguigu.servlet;
import com.atguigu.bean.Book;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.servlet.base.MethodBaseServlet;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/BookManager")
public class BookManagerServlet extends MethodBaseServlet {
    BookService bookService = new BookServiceImpl();
    private void toManager(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processTemplate("manager/manager",req,resp);
    }
    private void showBookList(HttpServletRequest req, HttpServletResponse resp){
        List<Book> bookList = null;
        try {
            bookList = bookService.getBookList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // System.out.println(bookList);
        req.setAttribute("bookList",bookList);
        try {
            processTemplate("manager/book_manager",req,resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void removeBook(HttpServletRequest req, HttpServletResponse resp){
        String id = req.getParameter("id");
        Integer ID = Integer.valueOf(id);
        try {
            boolean b = bookService.removeBook(ID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //删除完成之后要重新回到这个页面
        try {
            resp.sendRedirect(req.getContextPath()+"/BookManager?flag=showBookList");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void toOrderManager(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processTemplate("manager/order_manager",req,resp);
    }
    private void addBook(HttpServletRequest req, HttpServletResponse resp) throws IOException, InvocationTargetException, IllegalAccessException, SQLException {
        Map<String, String[]> map = req.getParameterMap();
        Book book = new Book();
        BeanUtils.populate(book,map);
        bookService.addBook(book);
        //添加成功之后,重新回到图书管理页面
        resp.sendRedirect(req.getContextPath()+"/BookManager?flag=showBookList");

    }
    private void editBook(HttpServletRequest req, HttpServletResponse resp) throws IOException, InvocationTargetException, IllegalAccessException, SQLException {
        Map<String, String[]> map = req.getParameterMap();
        Book book = new Book();
        BeanUtils.populate(book,map);

        //获取到要修改的那本书的ID然后把这个ID设置给editBook要修改的对象
        HttpSession session = req.getSession();
        Book book1 = (Book)session.getAttribute("book");
        book.setBook_id(book1.getBook_id());

        bookService.editBook(book);
        //添加成功之后,重新回到图书管理页面
        resp.sendRedirect(req.getContextPath()+"/BookManager?flag=showBookList");
    }
    private void toEditBookPage(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String id = req.getParameter("id");
        Integer ID = Integer.valueOf(id);
        //根据ID再查询到这本书
        Book book = bookService.getBookById(ID);
        HttpSession session = req.getSession();
        session.setAttribute("book",book);
        processTemplate("manager/book_edit",req,resp);
    }
    private void toAddBookPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processTemplate("manager/book_add",req,resp);
    }
}
