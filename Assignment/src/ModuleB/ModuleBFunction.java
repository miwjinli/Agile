/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleB;

import domain.Admin;
import domain.DeliveryMan;
import domain.Employee;
import domain.HR;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author MY
 */
public class ModuleBFunction {

    private List<DeliveryMan> deliveryMen = new ArrayList<>();
    private List<HR> HRList = new ArrayList<>();
    private List<Admin> adminList = new ArrayList<>();
    Scanner s = new Scanner(System.in);

    public ModuleBFunction() {
    }

    public Employee StaffLogin(String username) {
        boolean login = false;
        for (int i = 0; i < deliveryMen.size(); i++) {
            if (deliveryMen.get(i).getStaffID().equals(username)) {
                while (login == false) {
                    System.out.print("Please Enter Password: ");
                    String pw = s.nextLine();
                    if (deliveryMen.get(i).getStaffPw().equals(pw)) {
                        login = true;
                        System.out.println("Login As " + deliveryMen.get(i).getStaffPosition() + " Successfully!");
                        DeliveryMan loginDM = deliveryMen.get(i);
                        return loginDM;
                    } else {
                        System.out.println("Error. Wrong Password Entered!Try Again.");
                    }
                }
            }
        }
        for (int i = 0; i < HRList.size(); i++) {
            if (HRList.get(i).getStaffID().equals(username)) {
                while (login == false) {
                    System.out.print("Please Enter Password: ");
                    String pw = s.nextLine();
                    if (HRList.get(i).getStaffPw().equals(pw)) {
                        login = true;
                        System.out.println("Login As " + HRList.get(i).getStaffPosition() + " Successfully!");
                        HR loginHR = HRList.get(i);
                        return loginHR;
                    } else {
                        System.out.println("Error. Wrong Password Entered!Try Again.");
                    }
                }
            }
        }
        for (int i = 0; i < adminList.size(); i++) {
            if (adminList.get(i).getStaffID().equals(username)) {
                while (login == false) {
                    System.out.print("Please Enter Password: ");
                    String pw = s.nextLine();
                    if (adminList.get(i).getStaffPw().equals(pw)) {
                        login = true;
                        System.out.println("Login As " + adminList.get(i).getStaffPosition() + " Successfully!");
                        Admin loginAdmin = adminList.get(i);
                        return loginAdmin;
                    } else {
                        System.out.println("Error. Wrong Password Entered!Try Again.");
                    }
                }
            }
        }
        return null;
    }

    public void DisplayAdminRegistration(int TotalOwner) {
        String Position = "Owner";
        double Salary = 0;
        String ID = String.format("AD%06d", TotalOwner + 1);
        System.out.print("Enter New Admin Password: ");
        String Pw = s.nextLine();
        System.out.print("Enter New Admin Name: ");
        String Name = s.nextLine();
        System.out.print("Enter New Admin IC: ");
        String IC = s.nextLine();
        System.out.print("Enter New Admin Phone No: ");
        String PhNo = s.nextLine();
        System.out.print("Enter New Admin Gender(M/F): ");
        char Gender = Character.toUpperCase(s.nextLine().charAt(0));
        System.out.print("Enter New Admin Address: ");
        String Adds = s.nextLine();
        System.out.print("Enter New Admin Email: ");
        String Email = s.nextLine();
        System.out.print("Enter Total Annual Sales: ");
        double annualSale = s.nextDouble();
        s.nextLine();
        String WorkingStatus = "Employed";
        Admin admin = new Admin(annualSale, ID, Pw, Name, IC, PhNo, Gender, Adds, Email, Position, WorkingStatus, Salary, 0);
        admin.calculateSalary();
        AddNewAdmin(admin);
    }

    public void DisplayHRRegistration(int TotalHR) {
        String Position = "HR";
        double Salary = 0;
        String ID = String.format("HR%06d", TotalHR + 1);
        System.out.print("Enter New HR Password: ");
        String Pw = s.nextLine();
        System.out.print("Enter New HR Name: ");
        String Name = s.nextLine();
        System.out.print("Enter New HR IC: ");
        String IC = s.nextLine();
        System.out.print("Enter New HR Phone No: ");
        String PhNo = s.nextLine();
        System.out.print("Enter New HR Gender(M/F): ");
        char Gender = Character.toUpperCase(s.nextLine().charAt(0));
        System.out.print("Enter New HR Address: ");
        String Adds = s.nextLine();
        System.out.print("Enter New HR Email: ");
        String Email = s.nextLine();
        System.out.print("Enter Total Staff Managed: ");
        int TotalStaffManaged = s.nextInt();
        System.out.print("Enter HR Basic Salary: ");
        Salary = s.nextDouble();
        s.nextLine();
        String WorkingStatus = "Employed";
        HR hr = new HR(TotalStaffManaged, ID, Pw, Name, IC, PhNo, Gender, Adds, Email, Position, WorkingStatus, Salary, 0);
        hr.calculateSalary();
        AddNewHR(hr);
    }

    public void DisplayDeliveryManRegistration(int TotalDeliveryMan) {
        String Position = "Delivery Man";
        double Salary = 0;
        String ID = String.format("DM%06d", TotalDeliveryMan + 1);
        System.out.print("Enter New Delivery Man Password: ");
        String Pw = s.nextLine();
        System.out.print("Enter New Delivery Man Name: ");
        String Name = s.nextLine();
        System.out.print("Enter New Delivery Man IC: ");
        String IC = s.nextLine();
        System.out.print("Enter New Delivery Man Phone No: ");
        String PhNo = s.nextLine();
        System.out.print("Enter New Delivery Man Gender(M/F): ");
        char Gender = s.nextLine().charAt(0);
        System.out.print("Enter New Delivery Man Address: ");
        String Adds = s.nextLine();
        System.out.print("Enter New Delivery Man Email: ");
        String Email = s.nextLine();
        Position = "Delivery Man";
        System.out.print("Enter New Delivery Man Basic Salary: ");
        Salary = s.nextDouble();
        s.nextLine();
        String WorkingStatus = "Employed";
        String CurrentAvailable = "Not Yet Clock-In";
        String CurrentLocation = "None";
        DeliveryMan DM = new DeliveryMan(0, CurrentAvailable, CurrentLocation, ID, Pw, Name, IC, PhNo, Gender, Adds, Email, Position, WorkingStatus, Salary, 0);
        DM.calculateSalary();
        AddNewDeliveryMan(DM);
    }

    public boolean DisplayDeliveryManUpdateStatus(String StaffID) {
        boolean find = false;
        for (int i = 0; i < deliveryMen.size(); i++) {
            if (deliveryMen.get(i).getStaffID().equals(StaffID)) {
                find = true;
                DeliveryMan DM = deliveryMen.get(i);
                if(DM.getWorkingStatus().equals("Employed")){
                String choice = "0";
                System.out.print("Choose A Reason\n 1. Retired\n 2. Resigned\n");
                while (!choice.equals("1") && !choice.equals("2")) {
                    System.out.print("Your Choice: ");
                    choice = s.nextLine();
                    switch (choice) {
                        case "1": {
                            updateDeliveryManStatus(DM,choice);
                            break;
                        }
                        case "2": {
                            updateDeliveryManStatus(DM, choice);
                            break;
                        }
                        default: {
                            System.out.println("Error, Please Key In Again.");
                            break;
                        }
                    }
                }
            }else{
                System.out.println("Error. Delivery Man Already Not Working With Us!\nPress Enter to continue...");
                s.nextLine();
            }
        }
        }
        if (find == false) {
            System.out.println("Error, Delivery Man Not Found!");
        }
        return find;
    }

    public Employee DisplayStaffDetails(String StaffID) {
        boolean find = false;
        for (int i = 0; i < deliveryMen.size(); i++) {
            if (deliveryMen.get(i).getStaffID().equals(StaffID)) {
                return deliveryMen.get(i);
            }
        }
        if (find == false) {
            for (int i = 0; i < HRList.size(); i++) {
                if (HRList.get(i).getStaffID().equals(StaffID)) {
                    return HRList.get(i);
                }
            }
        }
        if (find == false) {
            for (int i = 0; i < adminList.size(); i++) {
                if (adminList.get(i).getStaffID().equals(StaffID)) {
                    return adminList.get(i);
                }
            }
        }
        return null;
    }

    public void AddNewAdmin(Admin admin) {
        adminList.add(admin);
        System.out.println("New Owner Added Successfully!");
    }

    public void AddNewHR(HR hr) {
        HRList.add(hr);
        System.out.println("New HR Added Successfully!");
    }

    public void AddNewDeliveryMan(DeliveryMan DM) {
        deliveryMen.add(DM);
        System.out.println("New Delivery Man Added Successfully!");
    }

    public void updateDeliveryManStatus(DeliveryMan DM, String i) {
        if (i.equals("1")) {
            DM.setWorkingStatus("Retired");
        } else{
            DM.setWorkingStatus("Resigned");
        }
        for (int j = 0; j < deliveryMen.size(); j++) {
            if (deliveryMen.get(j).getStaffID().equals(DM.getStaffID())) {
                deliveryMen.set(j, DM);
                
            }
        }
        System.out.println("Update Status Successfully!");

    }
    public List<DeliveryMan> getDeliveryMen() {
        return deliveryMen;
    }

    public void setDeliveryMen(List<DeliveryMan> deliveryMen) {
        this.deliveryMen = deliveryMen;
    }

    public List<HR> getHRList() {
        return HRList;
    }

    public void setHRList(List<HR> HRList) {
        this.HRList = HRList;
    }

    public List<Admin> getAdminList() {
        return adminList;
    }

    public void setAdminList(List<Admin> adminList) {
        this.adminList = adminList;
    }

}
