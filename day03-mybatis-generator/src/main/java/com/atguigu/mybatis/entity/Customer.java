package com.atguigu.mybatis.entity;

public class Customer {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_customer.customer_id
     *
     * @mbggenerated Sun May 14 13:43:56 CST 2023
     */
    private Integer customerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_customer.customer_name
     *
     * @mbggenerated Sun May 14 13:43:56 CST 2023
     */
    private String customerName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_customer.customer_id
     *
     * @return the value of t_customer.customer_id
     *
     * @mbggenerated Sun May 14 13:43:56 CST 2023
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_customer.customer_id
     *
     * @param customerId the value for t_customer.customer_id
     *
     * @mbggenerated Sun May 14 13:43:56 CST 2023
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_customer.customer_name
     *
     * @return the value of t_customer.customer_name
     *
     * @mbggenerated Sun May 14 13:43:56 CST 2023
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_customer.customer_name
     *
     * @param customerName the value for t_customer.customer_name
     *
     * @mbggenerated Sun May 14 13:43:56 CST 2023
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}