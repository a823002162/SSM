package com.atguigu.Filter;

import com.atguigu.tools.ConnectionTools;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/8/2023 6:49 PM
 */
public class ReleaseFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 转换ServletRequest
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        // System.out.println("releaseFilter放行前");
        // 直接对请求进行放行
        filterChain.doFilter(req, servletResponse);

        // System.out.println("releaseFilter放行后");
        // 调用方法释放连接
        if (!req.getRequestURI().contains("static")) {
            ConnectionTools.releaseConnection();
        }
    }

    @Override
    public void destroy() {

    }
}
