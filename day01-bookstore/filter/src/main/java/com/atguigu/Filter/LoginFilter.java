package com.atguigu.Filter;

import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/5/2023 6:05 PM
 */
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //先转换类型
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //判断是否有session
        if (req.getSession().getAttribute("username") != null || "getCartDetail".equals(req.getParameter("flag"))) {
            //进来这里面,说明,有登录或者是购物车上方数量显示的ajax请求,直接放行,不拦截
            filterChain.doFilter(req,resp);
        }else{
            //判断是否是ajax请求
            if(req.getParameter("ajax") != null){
                //说明是ajax请求,需要发送登录页面的url和success状态给前端
                Map<String,Object> map = new HashMap<>();
                map.put("url",req.getContextPath()+"/user?flag=toLoginPage");
                map.put("success",false);
                resp.getWriter().write(new Gson().toJson(map));
            }else{
                //说明不是ajax请求,重定向到登录页面
                resp.sendRedirect(req.getContextPath()+"/user?flag=toLoginPage");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
