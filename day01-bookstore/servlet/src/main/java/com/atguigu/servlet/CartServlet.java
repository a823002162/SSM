package com.atguigu.servlet;

import com.atguigu.bean.Book;
import com.atguigu.bean.Cart;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.servlet.base.MethodBaseServlet;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/4/2023 3:31 PM
 */
@WebServlet("/cart")
public class CartServlet extends MethodBaseServlet {
    BookService bookService = new BookServiceImpl();
    Gson gson = new Gson();
    //点击主页购物车按钮,跳转到购物车里面
    private void showCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processTemplate("cart/cart",req,resp);
    }
    //获取cart对象
    private Cart getCart(HttpServletRequest req){
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(cart == null){
            //如果cart为空,那么就要创建一个Cart对象
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        return cart;
    }
    //负责将图书添加到购物车里面的方法
    private void addToCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //拿取到请求参数里面的bookid
        String id = req.getParameter("ID");
        //根据bookid获取到book对象
        Book book = null;
        //获取cart对象
        Cart cart = getCart(req);
        //创建一个boolean类型的值用来表示添加是否成功
        boolean status;
        try {
            book = bookService.getBookById(Integer.valueOf(id));
            cart.addBook(book);
            status = true;
        } catch (SQLException e) {
            e.printStackTrace();
            status = false;
        }
        //创建要给map集合,存放添加是否成功的状态,然后返回给前端
        Map<String,Object> map = new HashMap<>();
        map.put("status",status);
        map.put("totalCount",cart.getTotalCount());
        String s = gson.toJson(map);
        resp.getWriter().write(s);
    }
    private void getCartDetail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cart cart = getCart(req);
        Map<String,Object> map = new HashMap<>();
        map.put("itemList",cart.getItemMap().values());
        map.put("totalCount",cart.getTotalCount());
        map.put("totalPrice",cart.getTotalPrice());
        String s = gson.toJson(map);
        resp.getWriter().write(s);
    }
    /*private void getCartCount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int totalCount = getCart(req).getTotalCount();
        Map<String,Object> map = new HashMap<>();
        map.put("totalCount",totalCount);
        resp.getWriter().write(gson.toJson(map));
    }*/
    //清空购物车
    private void deleteCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().removeAttribute("cart");
        getCartDetail(req,resp);
    }
    //删除某本书
    private void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("bookId");
        //将id为这个值的书从itemMap中删除
        Cart cart = getCart(req);
        //删除bookId对应的CartItem
        cart.getItemMap().remove(Integer.valueOf(id));

        //更新购物车中的数据并返回给前端
        getCartDetail(req,resp);
    }

    private void modifyCount(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        //先获取到bookid 和 action(具体要执行的操作内容)
        int bookId = Integer.parseInt(req.getParameter("bookId"));
        String action = req.getParameter("action");
        //获取cart对象
        Cart cart = getCart(req);
        switch(action){
            case "increaseCount":
                Book book = new Book();
                book.setBook_id(bookId);
                cart.addBook(book);
                break;
            case "decreaseCount":
                cart.decreaseCount(bookId);
                break;
            case "updateCount":
                int count = Integer.parseInt(req.getParameter("count"));
                cart.updateCount(bookId,count);
                break;
            default:
                break;
        }
        //执行完对应的操作之后,将数据传回给前端
        Map<String,Object> map = new HashMap<>();
        map.put("item",cart.getItemById(bookId));
        map.put("totalCount",cart.getTotalCount());
        map.put("totalPrice",cart.getTotalPrice());
        //将map转为Json对象并返回给前端
        String s = gson.toJson(map);
        resp.getWriter().write(s);
    }

    private void toCheckOutPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processTemplate("cart/checkout",req,resp);
    }
}
