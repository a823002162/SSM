package com.atguigu.bean;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/4/2023 4:25 PM
 */
public class CartItem {
    private Book book;
    private Integer count;
    private Double itemPrice;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getItemPrice() {
        BigDecimal countBigDecimal = new BigDecimal(this.count+"");
        BigDecimal priceBigDecimal = new BigDecimal(this.book.getPrice()+"");
        //将结果再转换成double类型的值
        return this.itemPrice = countBigDecimal.multiply(priceBigDecimal).doubleValue();
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }
    public void increaseCount(){
        this.count++;
    }

    public void decreaseCount(){
        this.count--;
    }
}
