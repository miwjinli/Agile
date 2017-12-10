/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Calendar;

/**
 *
 * @author MY
 */
public class DeliveryStatus {

    private DeliveryMan DM = new DeliveryMan();
    private Orders order = new Orders();
    private Calendar AssignedDate;
    private Calendar AssignedTime;
    private Calendar DeliveredDate;
    private Calendar DeliveredTime;
    private String DeliveryStatus;

    public DeliveryStatus() {
    }
    
    public DeliveryStatus(Calendar AssignedDate, Calendar AssignedTime, Calendar DeliveredDate, Calendar DeliveredTime, String DeliveryStatus) {
        this.AssignedDate = AssignedDate;
        this.AssignedTime = AssignedTime;
        this.DeliveredDate = DeliveredDate;
        this.DeliveredTime = DeliveredTime;
        this.DeliveryStatus = DeliveryStatus;
    }

    public DeliveryMan getDM() {
        return DM;
    }

    public void setDM(DeliveryMan DM) {
        this.DM = DM;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Calendar getAssignedDate() {
        return AssignedDate;
    }

    public void setAssignedDate(Calendar AssignedDate) {
        this.AssignedDate = AssignedDate;
    }

    public Calendar getAssignedTime() {
        return AssignedTime;
    }

    public void setAssignedTime(Calendar AssignedTime) {
        this.AssignedTime = AssignedTime;
    }

    public Calendar getDeliveredDate() {
        return DeliveredDate;
    }

    public void setDeliveredDate(Calendar DeliveredDate) {
        this.DeliveredDate = DeliveredDate;
    }

    public Calendar getDeliveredTime() {
        return DeliveredTime;
    }

    public void setDeliveredTime(Calendar DeliveredTime) {
        this.DeliveredTime = DeliveredTime;
    }

    public String getDeliveryStatus() {
        return DeliveryStatus;
    }

    public void setDeliveryStatus(String DeliveryStatus) {
        this.DeliveryStatus = DeliveryStatus;
    }

}
