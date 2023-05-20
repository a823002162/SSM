package com.atguigu.tools;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 4/17/2023 6:49 PM
 */
public class ConnectionTools {
    // 声明DataSource成员变量
    public final static DataSource dataSource;
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    // 使用静态代码块初始化datasource
    static {

        /*
        //不需要这行代码druid.properties里面有加这个配置的
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

        // 创建properties对象
        Properties pro = new Properties();

        // 使用类加载器读取配置文件
        InputStream resourceAsStream = ConnectionTools.class.getClassLoader().getResourceAsStream("druid.properties");

        try {
            // properties加载配置文件
            pro.load(resourceAsStream);

            // 创建dataSource连接池对象
            dataSource = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 空参构造私有化
    private ConnectionTools() {
    }

    // 创建get方法
    public static Connection getConnection() {
        Connection connection = threadLocal.get();
        System.out.println(connection);
        if(connection == null){
            try {
                // Connection connection = dataSource.getConnection();
                // System.out.println("connection = " + connection);
                // return connection;
                connection = dataSource.getConnection();
                threadLocal.set(connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        // System.out.println(connection);
        return connection;
    }

    // 创建释放connection的方法
    public static void releaseConnection() {
        Connection connection = threadLocal.get();
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                threadLocal.remove();
            }
        }
    }
}
