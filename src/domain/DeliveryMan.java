/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author MY
 */
public class DeliveryMan extends Employee {

    private int TotalPendingDelivery;
    private String CurrentAvailable;
    private String CurrentLocation;
    WorkStatus ws = new WorkStatus();

    public DeliveryMan() {
    }

    public DeliveryMan(int TotalPendingDelivery, String CurrentAvailable, String CurrentLocation) {
        this.TotalPendingDelivery = TotalPendingDelivery;
        this.CurrentAvailable = CurrentAvailable;
        this.CurrentLocation = CurrentLocation;
    }

    public DeliveryMan(int TotalPendingDelivery, String CurrentAvailable, String CurrentLocation, String StaffID, String StaffPw, String StaffName, String StaffIC, String StaffPhNo, char StaffGender, String StaffAdds, String StaffEmail, String StaffPosition, String WorkingStatus, double Salary, double TotalPay) {
        super(StaffID, StaffPw, StaffName, StaffIC, StaffPhNo, StaffGender, StaffAdds, StaffEmail, StaffPosition, WorkingStatus, Salary, TotalPay);
        this.TotalPendingDelivery = TotalPendingDelivery;
        this.CurrentAvailable = CurrentAvailable;
        this.CurrentLocation = CurrentLocation;
    }

    public WorkStatus getWs() {
        return ws;
    }

    public void setWs(WorkStatus ws) {
        this.ws = ws;
    }

    public int getTotalPendingDelivery() {
        return TotalPendingDelivery;
    }

    public void setTotalPendingDelivery(int TotalPendingDelivery) {
        this.TotalPendingDelivery = TotalPendingDelivery;
    }

    public String getCurrentAvailable() {
        return CurrentAvailable;
    }

    public void setCurrentAvailable(String CurrentAvailable) {
        this.CurrentAvailable = CurrentAvailable;
    }

    public String getCurrentLocation() {
        return CurrentLocation;
    }

    public void setCurrentLocation(String CurrentLocation) {
        this.CurrentLocation = CurrentLocation;
    }

    @Override
    public void calculateSalary() {
        super.setTotalPay(super.getSalary() + (ws.getTotalDeliveredOrder() * 0.5));
    }

}
