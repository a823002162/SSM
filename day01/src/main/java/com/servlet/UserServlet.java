package com.servlet;

import com.atguigu.bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/9/2023 11:18 AM
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       /* String url = "jdbc:mysql://localhost:3306/bookstore210107?user=root&password=qweasdzxc@13579";
        Connection connection;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String sql = "select user_id id,user_name username,user_pwd password,user_email email from t_user";
        PreparedStatement pst;
        try {
             pst = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int i;
        try {
            ResultSet i1 = pst.executeQuery();
            System.out.println(i1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("hello");
        User user = new User();
        user.setUsername("jack8888");
        String username = user.getUsername();*/

    }
}
