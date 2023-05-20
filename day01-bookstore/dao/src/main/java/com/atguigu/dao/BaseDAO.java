package com.atguigu.dao;

import com.atguigu.tools.ConnectionTools;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 4/17/2023 9:28 PM
 */
public class BaseDAO {
    // 先创建一个QueryRunner类型的对象
    // QueryRunner runner = new QueryRunner(ConnectionTools.dataSource);
    QueryRunner runner = new QueryRunner();
    // private Connection connection = ConnectionTools.getConnection();

    // update方法 方法不要try catch 有异常直接往上抛,这样可以最上层的servlet来处理异常,服务器才不会崩
    protected boolean update(String sql, Object... params) throws SQLException {
        int update = runner.update(ConnectionTools.getConnection(),sql, params);
        // int update = runner2.update(connection,sql,params);
        return update != 0;
    }

    protected boolean update(String sql,Object[][]params) throws SQLException {
        //使用runner的批处理方法,完成多个对象插入数据库功能
        int[] batch = runner.batch(ConnectionTools.getConnection(),sql, params);
        // int[] batch = runner2.batch(connection,sql,params);
        //使用StreamAPI流来确认多条语句是否均插入成功,如果是anyMatch,则表示只要有一条插入成功就返回true
        return Arrays.stream(batch).allMatch(i -> i >= 1);
    }

    // 获取List集合方法
    protected <T> List<T> getBeans(String sql, Class<T> clz, Object... params) throws SQLException {

        return runner.query(ConnectionTools.getConnection(),sql, new BeanListHandler<>(clz), params);
        // return runner2.query(connection,sql, new BeanListHandler<>(clz), params);
    }

    // 获取单个对象的方法
    protected <T> T getBean(String sql, Class<T> clz, Object... params) throws SQLException {
        return runner.query(ConnectionTools.getConnection(),sql, new BeanHandler<>(clz), params);
        // return runner2.query(connection,sql, new BeanHandler<>(clz), params);
    }

}
