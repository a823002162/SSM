package com.atguigu.servlet;

import com.atguigu.bean.Cart;
import com.atguigu.bean.User;
import com.atguigu.service.OrderService;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.OrderServiceImpl;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.servlet.base.MethodBaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/6/2023 8:48 AM
 */
@WebServlet("/order")
public class OrderServlet extends MethodBaseServlet {
    UserService userService = new UserServiceImpl();
    OrderService orderService = new OrderServiceImpl();
    private void checkout(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        //1.首先根据用户名获取用户,因为order里面需要用户id
        //获取session
        HttpSession session = req.getSession();
        //根据username拿到session里面的username属性
        String username = (String) session.getAttribute("username");
        //使用userService根据username获取到用户
        User user = userService.getUserByUsername(username);

        //2.获取到cart对象
        Cart cart = (Cart) session.getAttribute("cart");

        //3.调用orderService方法,将order和orderItem插入数据库中
        String orderSequence = orderService.addOrder(cart,user);

        //4.将订单编号设置到session里面
        session.setAttribute("orderSequence",orderSequence);

        //5.结账后需要清空购物车
        session.removeAttribute("cart");

        //6.thymeleaf渲染页面
        processTemplate("cart/checkout",req,resp);
    }

}
