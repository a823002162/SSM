package com.atguigu.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/4/2023 4:25 PM
 */
public class Cart {
    private Map<Integer, CartItem> itemMap = new HashMap<>();
    private Integer totalCount;
    private Double toatalPrice;

    public List<CartItem> getItemList(){
        return new ArrayList<>(this.itemMap.values());
    }

    // 创建添加图书的方法
    public void addBook(Book book) {
        // 首先要获取CartItem对象
        CartItem item = itemMap.get(book.getBook_id());
        if (item == null) {
            // 如果为空,说明是第一次添加图书,创建一个CartItem
            item = new CartItem();
            // 将图书添加进itemMap中
            item.setBook(book);
            // 设置图书数量count
            item.setCount(1);
            // 设置itemPrice
            // item.setItemPrice(item.getCount()*book.getPrice());
            itemMap.put(book.getBook_id(), item);

        } else {
            // 不为空,说明不是第一次添加图书,那么更新图书数量和itemPrice即可
            item.increaseCount();

        }
        item.getItemPrice();
    }

    public Map<Integer, CartItem> getItemMap() {
        return itemMap;
    }

    public int getTotalCount() {
        return /*
        原始写法
        itemMap.values().stream().mapToInt(new ToIntFunction<CartItem>() {
            @Override
            public int applyAsInt(CartItem value) {
                return value.getCount();
            }
        }).reduce(new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                return left + right;
            }
        }).orElse(0);*/
                // 第二种写法itemMap.values().stream().mapToInt( value -> value.getCount()).reduce((left, right) -> (left + right)).orElse(0);
                itemMap.values().stream().mapToInt(CartItem::getCount).reduce(Integer::sum).orElse(0);
    }

    public double getTotalPrice() {
         /*itemMap.values().stream().mapToDouble(new ToDoubleFunction<CartItem>() {
            @Override
            public double applyAsDouble(CartItem value) {
                return value.getItemPrice();
            }
        }).reduce(new DoubleBinaryOperator() {
            @Override
            public double applyAsDouble(double left, double right) {
                return left + right;
            }
        }).orElse(0);*/
        // itemMap.values().stream().mapToDouble( value -> value.getItemPrice()).reduce((left,right)-> left + right).orElse(0);
        return itemMap.values().stream().mapToDouble(CartItem::getItemPrice).reduce((double left, double right) ->
                new BigDecimal(left+"").add(new BigDecimal(right+"")).doubleValue()).orElse(0);
    }

    public void decreaseCount(int bookId) {
        // 获取到对应的item
        CartItem item = this.itemMap.get(bookId);
        if (item.getCount() == 1) {
            // 如果此时item的count数量本来就是1,那么直接将此书对应的CartItem对象从itemMap中删除
            this.itemMap.remove(bookId);
        } else {
            // 如果item的count数量不是1,那么就将count的数量减1
            item.decreaseCount();
        }
        // item.getItemPrice(); 不写也行,因为getTotalPrice里面也会去调用这个方法
    }

    public void updateCount(int bookId, int count) {
        CartItem item = this.itemMap.get(bookId);
        item.setCount(count);
        // item.getItemPrice(); 不写也行,因为getTotalPrice里面也会去调用这个方法
    }

    public CartItem getItemById(int id) {
        return this.itemMap.get(id);
    }
}
