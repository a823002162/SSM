package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.entity.Customer;
import com.atguigu.mybatis.entity.CustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_customer
     *
     * @mbggenerated Sun May 14 13:43:56 CST 2023
     */
    int countByExample(CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_customer
     *
     * @mbggenerated Sun May 14 13:43:56 CST 2023
     */
    int deleteByExample(CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_customer
     *
     * @mbggenerated Sun May 14 13:43:56 CST 2023
     */
    int deleteByPrimaryKey(Integer customerId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_customer
     *
     * @mbggenerated Sun May 14 13:43:56 CST 2023
     */
    int insert(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_customer
     *
     * @mbggenerated Sun May 14 13:43:56 CST 2023
     */
    int insertSelective(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_customer
     *
     * @mbggenerated Sun May 14 13:43:56 CST 2023
     */
    List<Customer> selectByExample(CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_customer
     *
     * @mbggenerated Sun May 14 13:43:56 CST 2023
     */
    Customer selectByPrimaryKey(Integer customerId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_customer
     *
     * @mbggenerated Sun May 14 13:43:56 CST 2023
     */
    int updateByExampleSelective(@Param("record") Customer record, @Param("example") CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_customer
     *
     * @mbggenerated Sun May 14 13:43:56 CST 2023
     */
    int updateByExample(@Param("record") Customer record, @Param("example") CustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_customer
     *
     * @mbggenerated Sun May 14 13:43:56 CST 2023
     */
    int updateByPrimaryKeySelective(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_customer
     *
     * @mbggenerated Sun May 14 13:43:56 CST 2023
     */
    int updateByPrimaryKey(Customer record);
}