package com.atguigu.service.impl;

import com.atguigu.bean.*;
import com.atguigu.dao.OrderDAO;
import com.atguigu.dao.OrderItemDAO;
import com.atguigu.dao.impl.OrderDAOImpl;
import com.atguigu.dao.impl.OrderItemDAOImpl;
import com.atguigu.service.BookService;
import com.atguigu.service.OrderService;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/6/2023 3:49 PM
 */
public class OrderServiceImpl implements OrderService {
    OrderDAO orderDAO = new OrderDAOImpl();
    OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
    BookService bookService = new BookServiceImpl();
    @Override
    public String addOrder(Cart cart, User user) throws SQLException {

        //先完成order对象的创建和赋值
        Order order = new Order();
        //完成orderSequence的赋值
        order.setOrderSequence(UUID.randomUUID().toString().replace("-",""));
        //完成createTime的赋值
        order.setCreateTime(new Date());
        //完成totalCount的赋值
        order.setTotalCount(cart.getTotalCount());
        //完成totalAmount的赋值
        order.setTotalAmount(cart.getTotalPrice());
        //完成orderStatus的赋值
        order.setOrderStatus((int)(Math.random()*4));
        //完成userId的赋值
        order.setUserId(user.getId());
        //调用orderDAO,将order插入数据库
        int orderId = orderDAO.insertOrder(order);

        //创建orderItem对象并完成赋值,可以使用循环,但是效率太低了
        OrderItem orderItem = new OrderItem();

        List<CartItem> itemList = cart.getItemList();
        //动态创建二维数组,用来存放多个订单中的多条记录
        Object[][] params = new Object[itemList.size()][6];
        Object[][] bookParams = new Object[itemList.size()][3];

        for (int i = 0; i < itemList.size(); i++) {
            //先获取到itemList集合里面的item元素
            CartItem item = itemList.get(i);
            params[i][0] = item.getBook().getBook_name();
            params[i][1] = item.getBook().getPrice();
            params[i][2] = item.getBook().getImg_path();
            params[i][3] = item.getCount();
            params[i][4] = item.getItemPrice();
            //这个地方需要插入order之后返回自增主键
            params[i][5] = orderId;

            bookParams[i][0] = item.getCount();
            bookParams[i][1] = item.getCount();
            bookParams[i][2] = item.getBook().getBook_id();

        }
        //将orderItem对象插入数据库中
        orderItemDAO.addOrderItem(params);

        //要同步修改book的销量和库存
        bookService.updateStockAndSales(bookParams);

        // System.out.println("订单结算");

        //将订单编号返回回去,以便在checkout页面上显示
        return order.getOrderSequence();
    }
}
