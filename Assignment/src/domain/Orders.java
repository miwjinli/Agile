/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Calendar;

/**
 *
 * @author ong
 */
public class Orders {
    private Restaurant restaurant;
    private Customer customer;
    private String OrdersID;
    private double Subtotal;
    private double Total;
    private String OrderStatus;
    private Calendar OrdersDateTime;
    
    public Orders(){
        
    }
    
    public Orders(Restaurant restaurant, Customer customer, String OrdersID, double Subtotal, double Total, String OrderStatus, Calendar OrdersDateTime) {
        this.restaurant = restaurant;
        this.customer = customer;
        this.OrdersID = OrdersID;
        this.Subtotal = Subtotal;
        this.Total = Total;
        this.OrderStatus = OrderStatus;
        this.OrdersDateTime = OrdersDateTime;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String OrderStatus) {
        this.OrderStatus = OrderStatus;
    }

    public String getOrdersID() {
        return OrdersID;
    }

    public void setOrdersID(String OrdersID) {
        this.OrdersID = OrdersID;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(double Subtotal) {
        this.Subtotal = Subtotal;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    public Calendar getOrdersDateTime() {
        return OrdersDateTime;
    }

    public void setOrdersDateTime(Calendar OrdersDateTime) {
        this.OrdersDateTime = OrdersDateTime;
    }
    
}
