package com.example;

import javax.persistence.*;

@Entity(name="order")
@Table(name="tbl_order")
public class Order {
    @Id
    @Column(name="order_code")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int orderCode;

    @Column(name="order_date")
    private String orderDate;
    @Column(name="order_time")
    private String orderTime;
    @Column(name="total_order_price")
    private int totalPrice;

    public Order() { }

    public Order(int orderCode, String orderDate, String orderTime, int totalPrice) {
        this.orderCode = orderCode;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.totalPrice = totalPrice;
    }

    public int getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(int orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderCode=" + orderCode +
                ", orderDate='" + orderDate + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
