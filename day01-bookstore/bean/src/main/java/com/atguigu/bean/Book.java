package com.atguigu.bean;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 4/26/2023 9:15 PM
 */

public class Book {
    private Integer book_id;
    private String book_name;
    private String author;
    private Double price;
    private Integer sales;
    private Integer stock;
    private String img_path;

    public Book() {
    }

    public Book(Integer book_id, String book_name, String author, Double price, Integer sales, Integer stock, String img_path) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.author = author;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
        this.img_path = img_path;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + book_id +
                ", bookName='" + book_name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", sales=" + sales +
                ", stock=" + stock +
                ", imgPath='" + img_path + '\'' +
                '}';
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }
}
