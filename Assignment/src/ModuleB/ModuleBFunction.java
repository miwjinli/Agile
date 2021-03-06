/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleB;

import domain.Admin;
import domain.DeliveryMan;
import domain.DeliveryStatus;
import domain.Employee;
import domain.HR;
import domain.WorkStatus;
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
    private List<DeliveryStatus> DSList = new ArrayList<>();
    private List<WorkStatus> WSList = new ArrayList<>();
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
        String Position = "Admin";
        double Salary = 0;
        String ID = String.format("AD%06d", TotalOwner + 1);
        System.out.println("\n\nID: " + ID + "(This will be the username)");
        System.out.print("Enter Password: ");
        String Pw = s.nextLine();
        System.out.print("Enter Name: ");
        String Name = s.nextLine();
        System.out.print("Enter IC: ");
        String IC = s.nextLine();
        System.out.print("Enter Phone No: ");
        String PhNo = s.nextLine();
        System.out.print("Enter Gender(M/F): ");
        char Gender = Character.toUpperCase(s.nextLine().charAt(0));
        System.out.print("Enter Address: ");
        String Adds = s.nextLine();
        System.out.print("Enter Email: ");
        String Email = s.nextLine();
        boolean parseDouble = false;
        double annualSale = 0;
        while (!parseDouble) {
            try {
                System.out.print("Enter Total Annual Sales: ");
                annualSale = s.nextDouble();
                parseDouble = true;
                s.nextLine();
            } catch (Exception e) {
                s.nextLine();
                System.out.println("Annual Sales Must Be Only In Integer!");
            }
        }
        String WorkingStatus = "Employed";
        Admin admin = new Admin(annualSale, ID, Pw, Name, IC, PhNo, Gender, Adds, Email, Position, WorkingStatus, Salary, 0);
        admin.calculateSalary();
        AddNewAdmin(admin);
    }

    public void DisplayHRRegistration(int TotalHR) {
        String Position = "HR";
        double Salary = 0;
        String ID = String.format("HR%06d", TotalHR + 1);
        System.out.println("\n\nID: " + ID + "(This will be the username)");
        System.out.print("Enter Password: ");
        String Pw = s.nextLine();
        System.out.print("Enter Name: ");
        String Name = s.nextLine();
        System.out.print("Enter IC: ");
        String IC = s.nextLine();
        System.out.print("Enter Phone No: ");
        String PhNo = s.nextLine();
        System.out.print("Enter Gender(M/F): ");
        char Gender = Character.toUpperCase(s.nextLine().charAt(0));
        System.out.print("Enter Address: ");
        String Adds = s.nextLine();
        System.out.print("Enter Email: ");
        String Email = s.nextLine();
        System.out.print("Enter Total Staff Managed: ");
        int TotalStaffManaged = s.nextInt();
        System.out.print("Enter Basic Salary: ");
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
        System.out.println("\n\nID: " + ID + "(This will be the username)");
        System.out.print("Enter Password: ");
        String Pw = s.nextLine();
        System.out.print("Enter Name: ");
        String Name = s.nextLine();
        System.out.print("Enter IC: ");
        String IC = s.nextLine();
        System.out.print("Enter Phone No: ");
        String PhNo = s.nextLine();
        System.out.print("Enter Gender(M/F): ");
        char Gender = s.nextLine().charAt(0);
        System.out.print("Enter Address: ");
        String Adds = s.nextLine();
        System.out.print("Enter Email: ");
        String Email = s.nextLine();
        Position = "Delivery Man";
        System.out.print("Enter Basic Salary: ");
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
                if (DM.getWorkingStatus().equals("Employed")) {
                    String choice = "0";
                    System.out.println("\n\nName: " + DM.getStaffName() + "\nIC: " + DM.getStaffIC());
                    System.out.print("Choose A Reason\n 1. Retired\n 2. Resigned\n");
                    while (!choice.equals("1") && !choice.equals("2")) {
                        System.out.print("Your Choice: ");
                        choice = s.nextLine();
                        switch (choice) {
                            case "1": {
                                updateDeliveryManStatus(DM, choice);
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
                } else {
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
        System.out.println("New Admin Added Successfully!");
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
        } else {
            DM.setWorkingStatus("Resigned");
        }
        for (int j = 0; j < deliveryMen.size(); j++) {
            if (deliveryMen.get(j).getStaffID().equals(DM.getStaffID())) {
                deliveryMen.set(j, DM);

            }
        }
        System.out.println("Update Status Successfully! New Status: " + DM.getWorkingStatus());

    }

    public void updateDeliveryManContactDetails(DeliveryMan DM) {
        System.out.println("\n\nYour ID: " + DM.getStaffID() + "\nYour Name: " + DM.getStaffName() + "\nYour Phone No: " + DM.getStaffPhNo() + "\nYour Address: " + DM.getStaffAdds() + "\nYour Email: " + DM.getStaffEmail());
        String choice = "None";
        System.out.println("What You want do update:\n1. Phone No\n2. Address\n3. Email\n4. Back");
        while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4")) {
            System.out.print("Choice: ");
            choice = s.nextLine();
            switch (choice) {
                case "1": {
                    boolean PhNoFormat = false;
                    String newPhNo = null;
                    while (!PhNoFormat) {
                        PhNoFormat = true;
                        System.out.println("\n\nYour Current Phone No: " + DM.getStaffPhNo());
                        System.out.print("New Phone No: ");
                        newPhNo = s.nextLine();
                        for (int i = 0; i < newPhNo.length(); i++) {
                            if (!Character.isDigit(newPhNo.charAt(i))) {
                                if (i != 3) {
                                    PhNoFormat = false;
                                } else {
                                    if (newPhNo.charAt(i) != '-' && newPhNo.charAt(i) != ' ') {
                                        PhNoFormat = false;
                                    }
                                }
                            }
                        }
                        if (!PhNoFormat) {
                            System.out.println("Format of Phone Contact Number should only contains INTEGERs and One(1) Dash!\n");
                        }
                    }
                    DM.setStaffPhNo(newPhNo);
                    System.out.println("Update Phone No Successfully! Press Enter to Continue...");
                    s.nextLine();
                    break;
                }
                case "2": {
                    System.out.println("\n\nYour Current Address: " + DM.getStaffAdds());
                    System.out.print("New Address: ");
                    String newAdds = s.nextLine();
                    DM.setStaffAdds(newAdds);
                    System.out.println("Update Address Successfully! Press Enter to Continue...");
                    s.nextLine();
                    break;
                }
                case "3": {
                    System.out.println("\n\nYour Current Email: " + DM.getStaffEmail());
                    System.out.print("New Email: ");
                    String newEmail = s.nextLine();
                    DM.setStaffEmail(newEmail);
                    System.out.println("Update Email Successfully! Press Enter to Continue...");
                    s.nextLine();
                }
                case "4": {
                    break;
                }
                default: {
                    System.out.println("Please Enter Again...");
                    choice = "None";
                    break;
                }
            }
        }
    }

    public void RetrieveDeliveryManPendingDeliveryMenu() {
        String selection = "0";
        System.out.println("Please Select The Option Below");
        System.out.println("1. View All Pending Delivery");
        System.out.println("2. View Delivery Man Pending Delivery");
        System.out.println("3. Back");
        while (!selection.equals("1") && !selection.equals("2") && !selection.equals("3")) {
            System.out.print("Option: ");
            selection = s.nextLine();
            switch (selection) {
                case "1": {
                    int count = 0;
                    for (int i = 0; i < 75; i++) {
                        System.out.print("*");
                    }
                    System.out.println("\nStaff ID\tStaff Name\tCurrent Available\tTotal Pending Order");
                    for (int i = 0; i < 75; i++) {
                        System.out.print("*");
                    }
                    for (int i = 0; i < deliveryMen.size(); i++) {
                        if (deliveryMen.get(i).getTotalPendingDelivery() > 0) {
                            System.out.print("\n" + deliveryMen.get(i).getStaffID() + "\t"
                                    + deliveryMen.get(i).getStaffName() + "\t" + deliveryMen.get(i).getCurrentAvailable() + "\t\t"
                                    + deliveryMen.get(i).getTotalPendingDelivery());
                            count++;
                        }
                    }
                    if (count == 0) {
                        System.out.print("\n\t\tNone Record(s) Found...");
                    }
                    System.out.print("\n");
                    for (int i = 0; i < 75; i++) {
                        System.out.print("*");
                    }
                    System.out.println("\n\n\nPress Enter To Continue,..");
                    s.nextLine();
                    break;
                }
                case "2": {
                    RetrieveDeliveryManPendingDelivery();
                    break;
                }
                case "3": {
                    break;
                }
                default: {
                    System.out.println("Please Enter Again...");
                    break;
                }
            }
        }
    }

    public void RetrieveDeliveryManPendingDelivery() {

        int count = 0;
        boolean found = false;
        while (!found) {
            System.out.print("\nEnter Delivery Man ID: ");
            String id = s.nextLine();
            for (int i = 0; i < deliveryMen.size(); i++) {
                if (id.equals(deliveryMen.get(i).getStaffID())) {
                    found = true;
                    System.out.println("\n\nID: " + deliveryMen.get(i).getStaffID() + "\nName: " + deliveryMen.get(i).getStaffName());
                    for (int j = 0; j < 140; j++) {
                        System.out.print("*");
                    }
                    System.out.println("\nCustomer ID\tOrder No\tAssigned Date\tAssigned Time\tDelivered Date\t\tDelivered Time\t\tDelivery Status");
                    for (int j = 0; j < 140; j++) {
                        System.out.print("*");
                    }
                    for (int j = 0; j < DSList.size(); j++) {
                        if (DSList.get(j).getDM().getStaffID().equals(id)) {
                            System.out.print("\n" + DSList.get(j).getOrder().getCustomer().getCustID() + "\t"
                                    + DSList.get(j).getOrder().getOrdersID() + "\t" + "11/25/2017"/*DSList.get(j).getAssignedDate()*/ + "\t"
                                    + "10:36 A.M."/*DSList.get(j).getAssignedTime()*/ + "\t");
                            if (DSList.get(j).getAssignedDate() != null) {
                                System.out.print(DSList.get(j).getDeliveredDate() + "\t" + DSList.get(j).getDeliveredDate() + "\t"
                                        + DSList.get(j).getDeliveredTime() + "\t" + DSList.get(j).getDeliveryStatus());
                            } else {
                                System.out.print("Not Yet Delivered\tNot Yet Delivered\t" + DSList.get(j).getDeliveryStatus());
                            }
                            count++;
                        }
                    }
                    System.out.print("\n");
                    for (int j = 0; j < 140; j++) {
                        System.out.print("*");
                    }
                }
            }
            if (!found) {
                System.out.println("Wrong Delivery Man ID Entered!Try Again!\n");
            }
        }
        if (count == 0) {
            System.out.print("\n\t\tNone Record(s) Found...");
        }
        System.out.println("\n\n\nPress Enter To Continue,..");
        s.nextLine();
    }

    public void generateTotalDeliveriesReportMenu() {
        System.out.println("Please Select The Option Below\n1. Generate Today Total Deliveries Report\n2. Generate Specific Date Total Deliveries Report\n3. Back");
        String choice = "0";
        while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3")) {
            System.out.print("Your Choice: ");
            choice = s.nextLine();
            switch (choice) {
                case "1": {
                    generateTodayTotalDeliveries();
                    break;
                }
                case "2": {
                    generateSpecificDateTotalDeliveries();
                    break;
                }
                case "3": {
                    break;
                }
                default: {
                    System.out.println("Please Enter Again...");
                    break;
                }
            }
        }
    }

    public void generateTodayTotalDeliveries() {
        int count = 0;
        for (int i = 0; i < 100; i++) {
            System.out.print("*");
        }
        System.out.println("\nStaff ID\tStaff Name\tCheck In Date\t\tCheck Out Date\t\tTotal Deliveries");
        for (int i = 0; i < 100; i++) {
            System.out.print("*");
        }
        java.util.Calendar today = java.util.Calendar.getInstance();
        java.text.SimpleDateFormat SDF = new java.text.SimpleDateFormat("dd/MM/yyyy");
        java.text.SimpleDateFormat SDF2 = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String todayDate = SDF.format(today.getTime());
        for (int i = 0; i < WSList.size(); i++) {
            String compareDate = SDF.format(WSList.get(i).getCheckIn().getTime());
            if (WSList.get(i).getTotalDeliveredOrder() > 0 && todayDate.equals(compareDate)) {
                System.out.println("\n" + WSList.get(i).getDM().getStaffID() + "\t" + WSList.get(i).getDM().getStaffName() + "\t" + SDF2.format(WSList.get(i).getCheckIn().getTime()) + "\t"
                        + SDF2.format(WSList.get(i).getCheckOut().getTime()) + "\t" + WSList.get(i).getTotalDeliveredOrder());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("\n\t\tNo Record(s) Found...");
        }
        for (int i = 0; i < 100; i++) {
            System.out.print("*");
        }
        System.out.println("\n\n\nPress Enter To Continue,..");
        s.nextLine();
    }

    public void generateSpecificDateTotalDeliveries() {
        int count = 0;
        boolean parseDate = false;
        while (!parseDate) {
            System.out.print("Enter A Date (DD/MM/YYYY): ");
            String date = s.nextLine();
            try {
                java.text.SimpleDateFormat SDF = new java.text.SimpleDateFormat("dd/MM/yyyy");
                java.util.Date newDate = SDF.parse(date);
                String compareDate = SDF.format(newDate);
                parseDate = true;
                for (int i = 0; i < 100; i++) {
                    System.out.print("*");
                }
                System.out.println("\nStaff ID\tStaff Name\tCheck In Date\t\tCheck Out Date\t\tTotal Deliveries");
                for (int i = 0; i < 100; i++) {
                    System.out.print("*");
                }
                java.text.SimpleDateFormat SDF2 = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                for (int i = 0; i < WSList.size(); i++) {
                    if (WSList.get(i).getTotalDeliveredOrder() > 0 && compareDate.equals(SDF.format(WSList.get(i).getCheckIn().getTime()))) {
                        System.out.println("\n" + WSList.get(i).getDM().getStaffID() + "\t" + WSList.get(i).getDM().getStaffName() + "\t" + SDF2.format(WSList.get(i).getCheckIn().getTime()) + "\t"
                                + SDF2.format(WSList.get(i).getCheckOut().getTime()) + "\t" + WSList.get(i).getTotalDeliveredOrder());
                        count++;
                    }
                }
                if (count == 0) {
                    System.out.println("\n\t\tNo Record(s) Found...");
                }
                for (int i = 0; i < 100; i++) {
                    System.out.print("*");
                }
                System.out.println("\n\n\nPress Enter To Continue,..");
                s.nextLine();
            } catch (Exception e) {
                System.out.println("Wrong Date Format! Try Again!");
            }
        }
    }

    public void generateTotalDistanceReportMenu(){
        System.out.println("Please Select The Option Below\n1. Generate Today Total Distance Report\n2. Generate Specific Date Total Distance Report\n3. Back");
        String choice = "0";
        while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3")) {
            System.out.print("Your Choice: ");
            choice = s.nextLine();
            switch (choice) {
                case "1": {
                    generateTodayTotalDistance();
                    break;
                }
                case "2": {
                    generateSpecificDateTotalDistance();
                    break;
                }
                case "3": {
                    break;
                }
                default: {
                    System.out.println("Please Enter Again...");
                    break;
                }
            }
        }
    }
    
    public void generateTodayTotalDistance(){
        int count = 0;
        for (int i = 0; i < 100; i++) {
            System.out.print("*");
        }
        System.out.println("\nStaff ID\tStaff Name\tCheck In Date\t\tCheck Out Date\t\tTotal Distance(in KM)");
        for (int i = 0; i < 100; i++) {
            System.out.print("*");
        }
        java.util.Calendar today = java.util.Calendar.getInstance();
        java.text.SimpleDateFormat SDF = new java.text.SimpleDateFormat("dd/MM/yyyy");
        java.text.SimpleDateFormat SDF2 = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String todayDate = SDF.format(today.getTime());
        for (int i = 0; i < WSList.size(); i++) {
            String compareDate = SDF.format(WSList.get(i).getCheckIn().getTime());
            if (WSList.get(i).getTotalDistance() > 0 && todayDate.equals(compareDate)) {
                System.out.println("\n" + WSList.get(i).getDM().getStaffID() + "\t" + WSList.get(i).getDM().getStaffName() + "\t" + SDF2.format(WSList.get(i).getCheckIn().getTime()) + "\t"
                        + SDF2.format(WSList.get(i).getCheckOut().getTime()) + "\t" + WSList.get(i).getTotalDistance());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("\n\t\tNo Record(s) Found...");
        }
        for (int i = 0; i < 100; i++) {
            System.out.print("*");
        }
        System.out.println("\n\n\nPress Enter To Continue,..");
        s.nextLine();
    }
    
    public void generateSpecificDateTotalDistance(){
        int count = 0;
        boolean parseDate = false;
        while (!parseDate) {
            System.out.print("Enter A Date (DD/MM/YYYY): ");
            String date = s.nextLine();
            try {
                java.text.SimpleDateFormat SDF = new java.text.SimpleDateFormat("dd/MM/yyyy");
                java.util.Date newDate = SDF.parse(date);
                String compareDate = SDF.format(newDate);
                parseDate = true;
                for (int i = 0; i < 100; i++) {
                    System.out.print("*");
                }
                System.out.println("\nStaff ID\tStaff Name\tCheck In Date\t\tCheck Out Date\t\tTotal Distance(in KM)");
                for (int i = 0; i < 100; i++) {
                    System.out.print("*");
                }
                java.text.SimpleDateFormat SDF2 = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                for (int i = 0; i < WSList.size(); i++) {
                    if (WSList.get(i).getTotalDistance() > 0 && compareDate.equals(SDF.format(WSList.get(i).getCheckIn().getTime()))) {
                        System.out.println("\n" + WSList.get(i).getDM().getStaffID() + "\t" + WSList.get(i).getDM().getStaffName() + "\t" + SDF2.format(WSList.get(i).getCheckIn().getTime()) + "\t"
                                + SDF2.format(WSList.get(i).getCheckOut().getTime()) + "\t" + WSList.get(i).getTotalDistance());
                        count++;
                    }
                }
                if (count == 0) {
                    System.out.println("\n\t\tNo Record(s) Found...");
                }
                for (int i = 0; i < 100; i++) {
                    System.out.print("*");
                }
                System.out.println("\n\n\nPress Enter To Continue,..");
                s.nextLine();
            } catch (Exception e) {
                System.out.println("Wrong Date Format! Try Again!");
            }
        }
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

    public List<DeliveryStatus> getDSList() {
        return DSList;
    }

    public void setDSList(List<DeliveryStatus> DSList) {
        this.DSList = DSList;
    }

    public List<WorkStatus> getWSList() {
        return WSList;
    }

    public void setWSList(List<WorkStatus> WSList) {
        this.WSList = WSList;
    }

}
