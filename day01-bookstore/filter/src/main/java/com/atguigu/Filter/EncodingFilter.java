package com.atguigu.Filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/5/2023 6:46 PM
 */
public class EncodingFilter implements Filter {
    String encoding;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
         encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //设置编码相关
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        req.setCharacterEncoding(encoding);
        resp.setContentType("text/html;charset="+encoding);

        //编码相关的设置完成之后,对请求进行放行
        filterChain.doFilter(req,resp);
    }

    @Override
    public void destroy() {

    }
}
