package com.atguigu.Filter;

import com.atguigu.tools.ConnectionTools;

import javax.servlet.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/8/2023 7:02 PM
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 先获取数据库的连接
        Connection connection = ConnectionTools.getConnection();

        try {
            // 关闭数据库的自动提交功能
            connection.setAutoCommit(false);

            // System.out.println("transactionFilter放行前");

            // 放行request请求
            filterChain.doFilter(servletRequest, servletResponse);

            // System.out.println("transactionFilter放行后");
            // 提交事务
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("出异常了");
            try {
                // 如果提交失败需要进行rollback
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            try {
                // 无论是否成功,最后都要启用自动提交
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
