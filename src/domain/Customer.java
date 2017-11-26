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
public class Customer {
    private String CustID;
    private String CustName;
    private String CustAddress;
    private String CustArea;
    private String CustTelNo;
    private String CustIC;
    private String CustPass;

    public Customer(String CustID, String CustName, String CustAddress, String CustArea, String CustTelNo, String CustIC, String CustPass) {
        this.CustID = CustID;
        this.CustName = CustName;
        this.CustAddress = CustAddress;
        this.CustArea = CustArea;
        this.CustTelNo = CustTelNo;
        this.CustIC = CustIC;
        this.CustPass = CustPass;
    }

    public String getCustID() {
        return CustID;
    }

    public void setCustID(String CustID) {
        this.CustID = CustID;
    }

    public String getCustName() {
        return CustName;
    }

    public void setCustName(String CustName) {
        this.CustName = CustName;
    }

    public String getCustAddress() {
        return CustAddress;
    }

    public void setCustAddress(String CustAddress) {
        this.CustAddress = CustAddress;
    }

    public String getCustTelNo() {
        return CustTelNo;
    }

    public void setCustTelNo(String CustTelNo) {
        this.CustTelNo = CustTelNo;
    }

    public String getCustIC() {
        return CustIC;
    }

    public void setCustIC(String CustIC) {
        this.CustIC = CustIC;
    }

    public String getCustPass() {
        return CustPass;
    }

    public void setCustPass(String CustPass) {
        this.CustPass = CustPass;
    }

    public String getCustArea() {
        return CustArea;
    }

    public void setCustArea(String CustArea) {
        this.CustArea = CustArea;
    }

    @Override
    public String toString() {
        return "Customer{" + "CustID=" + CustID + ", CustName=" + CustName + ", CustAddress=" + CustAddress + ", CustArea=" + CustArea + ", CustTelNo=" + CustTelNo + ", CustIC=" + CustIC + ", CustPass=" + CustPass + '}';
    }

    
}
