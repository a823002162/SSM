package com.atguigu.servlet;

import com.atguigu.servlet.base.MethodBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 4/26/2023 8:48 PM
 */
@WebServlet("/admin")
public class AdminServlet extends MethodBaseServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processTemplate("manager/manager",req,resp);
    }
}
