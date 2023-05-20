package com.atguigu.mybatis.handler;

import com.atguigu.mybatis.entity.Address;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/15/2023 4:32 PM
 */
public class AddressTypeHandler extends BaseTypeHandler<Address> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Address address, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public Address getNullableResult(ResultSet resultSet, String s) throws SQLException {
        //获取查寻的字段的字符串
        String columnAddress = resultSet.getString(s);
        //创建address对象
        Address address = new Address();
        //拆分字符串并进行赋值
        if(columnAddress != null && !"".equals(columnAddress)){
            String[] split = columnAddress.split(",");
             address.setProvince(split[0]);
             address.setCity(split[1]);
             address.setStreet(split[2]);
        }
        return address;
    }

    @Override
    public Address getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public Address getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
