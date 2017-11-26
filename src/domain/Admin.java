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
public class Admin extends Employee {

    private double annualSale;

    public Admin() {
    }

    public Admin(double annualSale) {
        this.annualSale = annualSale;
    }

    public Admin(double annualSale, String StaffID, String StaffPw, String StaffName, String StaffIC, String StaffPhNo, char StaffGender, String StaffAdds, String StaffEmail, String StaffPosition, String WorkingStatus, double Salary, double TotalPay) {
        super(StaffID, StaffPw, StaffName, StaffIC, StaffPhNo, StaffGender, StaffAdds, StaffEmail, StaffPosition, WorkingStatus, Salary, TotalPay);
        this.annualSale = annualSale;
    }

    public double getAnnualSale() {
        return annualSale;
    }

    public void setAnnualSale(double annualSale) {
        this.annualSale = annualSale;
    }

    @Override
    public void calculateSalary() {
        super.setSalary(annualSale * 0.3);
        super.setTotalPay(super.getSalary());
    }
}
