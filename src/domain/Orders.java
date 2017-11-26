/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

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
    private int OrdersHour;
    private int OrdersMinute;
    private int OrdersDay;
    private int OrdersMonth;
    private int OrdersYear;
    
    public Orders(){
        
    }
    
    public Orders(Restaurant restaurant, Customer customer, String OrdersID, double Subtotal, double Total, String OrderStatus, int OrdersHour, int OrdersMinute, int OrdersDay, int OrdersMonth, int OrdersYear) {
        this.restaurant = restaurant;
        this.customer = customer;
        this.OrdersID = OrdersID;
        this.Subtotal = Subtotal;
        this.Total = Total;
        this.OrderStatus = OrderStatus;
        this.OrdersHour = OrdersHour;
        this.OrdersMinute = OrdersMinute;
        this.OrdersDay = OrdersDay;
        this.OrdersMonth = OrdersMonth;
        this.OrdersYear = OrdersYear;
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

    public int getOrdersHour() {
        return OrdersHour;
    }

    public void setOrdersHour(int OrdersHour) {
        this.OrdersHour = OrdersHour;
    }

    public int getOrdersMinute() {
        return OrdersMinute;
    }

    public void setOrdersMinute(int OrdersMinute) {
        this.OrdersMinute = OrdersMinute;
    }

    public int getOrdersDay() {
        return OrdersDay;
    }

    public void setOrdersDay(int OrdersDay) {
        this.OrdersDay = OrdersDay;
    }

    public int getOrdersMonth() {
        return OrdersMonth;
    }

    public void setOrdersMonth(int OrdersMonth) {
        this.OrdersMonth = OrdersMonth;
    }

    public int getOrdersYear() {
        return OrdersYear;
    }

    public void setOrdersYear(int OrdersYear) {
        this.OrdersYear = OrdersYear;
    }
    
    public String DatetoString(){
        return OrdersDay+"/"+OrdersMonth+"/"+OrdersYear+" "+OrdersHour+":"+OrdersMinute;
    }
    
    @Override
    public String toString() {
        return "Orders{" + "restaurant=" + restaurant + ", customer=" + customer + ", OrdersID=" + OrdersID + ", Subtotal=" + Subtotal + ", Total=" + Total + ", OrderStatus=" + OrderStatus + ", OrdersHour=" + OrdersHour + ", OrdersMinute=" + OrdersMinute + ", OrdersDay=" + OrdersDay + ", OrdersMonth=" + OrdersMonth + ", OrdersYear=" + OrdersYear + '}';
    }
    
}
