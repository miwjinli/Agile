/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

import ModuleA.ModuleAFunction;
import ModuleB.ModuleBFunction;
import ModuleC.ModuleCFunction;
import ModuleD.ModuleDFunction;
import java.util.List;
import java.util.ArrayList;
import domain.*;
import java.util.Scanner;

/**
 *
 * @author MY
 */
public class Assignment {

    private ModuleAFunction A = new ModuleAFunction();
    private ModuleBFunction B = new ModuleBFunction();
    private ModuleCFunction C = new ModuleCFunction();
    private ModuleDFunction D = new ModuleDFunction();
    private List<Restaurant> restaurant = new ArrayList<>();
    private List<Food> food = new ArrayList<>();
    private List<Customer> customer = new ArrayList<>();
    private List<Orders> order = new ArrayList<>();
    private List<OrderDetail> orderdetail = new ArrayList<>();
    private List<DeliveryMan> DMList = new ArrayList<>();
    private List<Admin> adminList = new ArrayList<>();
    private List<HR> HRList = new ArrayList<>();
    private List<DeliveryStatus> DSList = new ArrayList<>();
    private List<WorkStatus> WSList = new ArrayList<>();
    private Employee loginStaff;
    private Restaurant r;

    /**
     * @param args the command line arguments
     */
    public void menu() {
        Scanner s = new Scanner(System.in);
        String selection = "0";
        System.out.println("**************************************");
        System.out.println("*Welcome to Faster Deliverymen System*");
        System.out.println("**************************************");
        System.out.println("Please Select The Option Below");
        System.out.println("1. Affiliate Register");
        System.out.println("2. Staff Login");
        System.out.println("3. Customer Login");
        System.out.println("4. Affiliate Login");
        while (!selection.equals("1") && !selection.equals("2") && !selection.equals("3") && !selection.equals("4")) {
            System.out.print("Option: ");
            selection = s.next();
            switch (selection) {
                case "1": {
                    //create class at module A and import the module A at this class and den call the method from here
                    A.RestaurantRegistration();
                    menu();
                    break;
                }
                case "2": {
                    staffMenu();
                    menu();
                    break;
                }
                case "3": {
                    C.CustomerLogin();
                    menu();
                    break;
                }
                case "4": {
                    boolean a = A.RestaurantLogin(r);
                    
                    if (a = true) {
                        menu();
                    }
                    
                    break;
                }
                default: {
                    System.out.println("Error, Please Key In Again.");
                    break;
                }
            }
        }
    }

    public void staffMenu() {
        Scanner s = new Scanner(System.in);
        boolean login = false;
        while (login == false) {
            System.out.print("Please Enter Staff ID: ");
            String username = s.nextLine();
            loginStaff = B.StaffLogin(username.toUpperCase());
            if (loginStaff instanceof DeliveryMan) {
                System.out.println("\n\n\n");
                login = true;
                DeliveryManMenu(loginStaff);
                menu();
            } else if (loginStaff instanceof HR) {
                login = true;
                System.out.println("\n\n\n");
                HRMenu();
            } else if (loginStaff instanceof Admin) {
                System.out.println("\n\n\n");
                login = true;
                AdminMenu();
                menu();
            } else {
                System.out.println("Error. Username Not Found!");
            }
        }
    }

    /*public void RestaurantMenu(List<Restaurant> restaurant) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Add New Menu Items");
        System.out.println("2. Update Menu Item Details");
        System.out.println("3. Remove Menu Items");
        System.out.println("0. Log Out");

        System.out.print("Option: ");
        int selection = sc.nextInt();
        sc.nextLine();
        switch (selection) {
            case 1: {
                System.out.println("Stay Tuned. Under Construction.");
                break;
            }
            case 2: {
                System.out.println("Stay Tuned. Under Construction.");
                break;
            }
            case 3: {
                System.out.println("Stay Tuned. Under Construction.");
                break;
            }
            case 0: {
                System.out.println("Successfully Logout");
                menu();
                break;
            }
            default: {
                System.out.println("Error. Please key in again.");
                RestaurantMenu(restaurant);
                break;
            }
        }
    }*/

    public void DeliveryManMenu(Employee deliveryMen) {
        Scanner s = new Scanner(System.in);
        DeliveryMan DM = new DeliveryMan();
        String choice = "None";
        System.out.println("DeliveryMen Name : " + deliveryMen.getStaffName());
        for (int i = 0; i < DMList.size(); i++) {
            if (DMList.get(i).getStaffID().equals(deliveryMen.getStaffID())) {
                DM=DMList.get(i);
                String currentStatus = DMList.get(i).getCurrentAvailable();
                System.out.println("Current Status : " + currentStatus);
            }
        }
        System.out.println("**********");
        System.out.println("*  Menu  *");
        System.out.println("**********");
        System.out.println("1. Clock In / Clock Out \n2. Change Deliver Status \n3. View Deliver Schedule\n4. Update Personal Contact Details\n5. Retrive Customer Details\n6. Exit");

        while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4") && !choice.equals("5") && !choice.equals("6")) {
            System.out.print("Option : ");
            choice = s.nextLine();
            switch (choice) {
                case "1": {
                    D.DeliveryMenClockInOut(DMList, deliveryMen.getStaffID());
                    break;
                }
                case "2": {
                    D.ChangeDeliverStatus(DMList, deliveryMen.getStaffID());
                    break;
                }
                case"3":{
                   D.ViewDeliverSchedule(customer, DMList, order, restaurant, deliveryMen.getStaffID());
                    break;
                }
                case "4": {
                    B.updateDeliveryManContactDetails(DM);
                    DeliveryManMenu(deliveryMen);
                    break;
                }
                case "5":{
                    C.retrieveCustomer();
                    break;
                }
                case "6":{
                    break;
                }
                default: {
                    System.out.println("Please Enter Again...");
                    choice = "None";
                }
            }
        }
    }
    
    public void AdminMenu(){
      Scanner s = new Scanner(System.in);
        String selection = "0";
        java.util.Date date = new java.util.Date();
        java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Welcome Back, " + loginStaff.getStaffName() + "\nCurrent Date:" + dateFormat.format(date) + "\n");
        System.out.println("Please Select The Option Below");
        System.out.println("1. View Delivery Man Clock In & Clock Out");
        System.out.println("2. Retrieve Delivery Man Pending Delivery");
        System.out.println("3. Generate Total Deliveries Report");
        System.out.println("4. Generate Total Distance Report");
        System.out.println("6. Log Out");
        while (!selection.equals("1") && !selection.equals("2") && !selection.equals("3") && !selection.equals("4") & !selection.equals("5")&& !selection.equals("6")) {
            System.out.print("Option: ");
            selection = s.nextLine();
            switch (selection) {
                case "1": {
                    D.ViewDeliverManClockInOut(DMList);
                    AdminMenu();
                    break;
                }
                case "2": {
                    B.RetrieveDeliveryManPendingDeliveryMenu();
                    AdminMenu();
                    break;
                }
                case "3": {
                    B.generateTotalDeliveriesReportMenu();
                    AdminMenu();
                    break;
                }
                case "4": {
                    B.generateTotalDistanceReportMenu();
                    AdminMenu();
                    break;
                }
                case "6": {
                    menu();
                    break;
                }
                default: {
                    System.out.println("Please Enter Again...");
                    selection = "None";
                    break;
                }
            }
        }
    }
    
    public void HRMenu() {
        Scanner s = new Scanner(System.in);
        String selection = "0";
        java.util.Date date = new java.util.Date();
        java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Welcome Back, " + loginStaff.getStaffName() + "\nCurrent Date:" + dateFormat.format(date) + "\n");
        System.out.println("Please Select The Option Below");
        System.out.println("1. Add New Delivery Man");
        System.out.println("2. Add New Admin");
        System.out.println("3. Add New HR");
        System.out.println("4. Update Delivery Man Status");
        System.out.println("5. View Staff Details");
        System.out.println("6. Assign Deliverymen");
        System.out.println("7. Log Out");
        while (!selection.equals("1") && !selection.equals("2") && !selection.equals("3") && !selection.equals("4") && !selection.equals("5") && !selection.equals("6") && !selection.equals("7")) {
            System.out.print("Option: ");
            selection = s.nextLine();
            switch (selection) {
                case "1": {
                    B.DisplayDeliveryManRegistration(DMList.size());
                    DMList = B.getDeliveryMen();
                    HRMenu();
                    break;
                }
                case "2": {
                    B.DisplayAdminRegistration(adminList.size());
                    adminList = B.getAdminList();
                    HRMenu();
                    break;
                }
                case "3": {
                    B.DisplayHRRegistration(HRList.size());
                    HRList = B.getHRList();
                    HRMenu();
                    break;
                }
                case "4": {
                    s.nextLine();
                    System.out.print("Please Enter A Delivery Man ID: ");
                    String ID = s.nextLine();
                    boolean find = B.DisplayDeliveryManUpdateStatus(ID.toUpperCase());
                    if (find) {
                        DMList = B.getDeliveryMen();
                    }
                    HRMenu();
                    break;
                }
                case "5": {
                    s.nextLine();
                    boolean find = false;
                    while (find == false) {
                        System.out.print("Please Enter A Staff ID: ");
                        String ID = s.nextLine();
                        Employee e = B.DisplayStaffDetails(ID.toUpperCase());
                        if (e == null) {
                            System.out.println("Error. Staff ID Not Found!");
                        } else {
                            find = true;
                            System.out.printf("Staff ID: %s\n"
                                    + "StaffName: %s\n"
                                    + "Staff Phone No: %s\n"
                                    + "Staff Address: %s\n"
                                    + "Staff Position: %s\n"
                                    + "Staff Status: %s\n"
                                    + "Staff Basic Salary: %.2f\n"
                                    + "Press Enter To Continue...", e.getStaffID(), e.getStaffName(), e.getStaffPhNo(), e.getStaffAdds(), e.getStaffPosition(), e.getWorkingStatus(), e.getSalary());
                            s.nextLine();
                        }
                    }
                    HRMenu();
                    break;
                }
                case "6": {
                    D.AssignFunction(order);
                    DSList = D.getDeliveryStatus();
                    menu();
                    break;
                }
                case "7": {
                    System.out.println("\n\n\n\n\n");
                    loginStaff = null;
                    menu();
                    break;
                }
                default: {
                    System.out.println("Error, Please Key In Again.");
                    break;
                }
            }
        }
    }

    public void initializeList() {
        restaurant.add(new Restaurant("RE000001", "Nandos", "Western", 'A', "Tneh Chee Wei", "asd", "016-6666666", "Setapak", "100", "1234567890"));
        restaurant.add(new Restaurant("RE000002", "KFC", "FastFood", 'N', "Tneh Chee Wai", "asd", "016-6666666", "Wangsa Maju", "200", "1234567890"));
        food.add(new Food("FM000001", "Chicken Bolognese", 11.50, "Noodles", 'A', restaurant.get(0), 'N'));
        food.add(new Food("FM000002", "Fish Bolognese", 11.50, "Noodles", 'A', restaurant.get(0), 'N'));
        food.add(new Food("FM000003", "Beef Bolognese", 13.50, "Noodles", 'A', restaurant.get(0), 'N'));
        food.add(new Food("FM000004", "Dinner Plate A", 11.50, "Set", 'A', restaurant.get(1), 'N'));
        food.add(new Food("FM000005", "Dinner Plate B", 12.50, "Set", 'A', restaurant.get(1), 'N'));
        food.add(new Food("FM000006", "Dinner Plate C", 13.50, "Set", 'A', restaurant.get(1), 'N'));
        food.add(new Food("FM000007", "Chicken Rice", 9.50, "Rice", 'A', restaurant.get(0), 'Y'));
        food.add(new Food("FM000008", "Dinner Plate D", 5.50, "Set", 'A', restaurant.get(1), 'Y'));
        food.add(new Food("FM000009", "Dinner Plate E", 6.50, "Set", 'A', restaurant.get(1), 'Y'));
        customer.add(new Customer("CU000001", "Miw Jin Li", "14,Taman Cantik,53300,Setapak,Kuala Lumpur", "Setapak", "0167897898", "971003355333", "1234567890"));
        customer.add(new Customer("CU000001", "Miw Jin Le", "14,Taman Cantik,53300,Wangsa Maju,Kuala Lumpur", "Wangsa Maju", "0167897899", "970104079999", "1234567890"));
        order.add(new Orders(restaurant.get(0), customer.get(0), "OR000001", 0.00, 0.00, "1", 0, 04, 1, 12, 2017));
        order.add(new Orders(restaurant.get(1), customer.get(1), "OR000002", 0.00, 0.00, "1", 0, 04, 1, 12, 2017));
        orderdetail.add(new OrderDetail(order.get(0), food.get(0), 1));
        DMList.add(new DeliveryMan(1, "Not Available", "None", "DM000001", "123456", "Ong Yi Jun", "971009-02-5213", "012-3456789", 'M', "2345 Lorong 3 Jalan ABC, 51020 KL", "OngYiJun@gmail.com", "Delivery Man", "Employed", 3500, 3500));
        DMList.add(new DeliveryMan(1, "Available", "None", "DM000002", "123456", "Chun Leong", "921005-05-5225", "014-5959595", 'M', "5 Lorong 9 Jalan Long, 51020 Selangor", "ChunLeong@gmail.com", "Delivery Man", "Employed", 3500, 3500));
        B.setDeliveryMen(DMList);
        HRList.add(new HR(1, "HR000001", "123456", "Ong Ong Jun", "970707-07-0707", "010-2255533", 'M', "Jalan Prima Setapak, KL", "OngOngJun@hotmail.com", "HR", "Employed", 3500, 3750));
        B.setHRList(HRList);
        adminList.add(new Admin(0, "AD000001", "123456", "ABC", "123456678", "012-345678", 'M', "22A, Deaman Ap, KL", "E@e.com", "Admin", "Employed", 0, 0));
        //DSList.add(new DeliveryStatus(null, null, null, null, "Accepted By Delivery Man"));
        //DSList.get(0).setOrder(order.get(0));
        //DSList.get(0).setDM(DMList.get(0));
        java.util.Calendar today = java.util.Calendar.getInstance();
        java.text.SimpleDateFormat SDF = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String date = SDF.format(today.getTime());
        WSList.add(new WorkStatus("WS000001", today, today, 200, 4, DMList.get(0)));
        D.setDeliveryStatus(DSList);
        D.setDeliveryMen(DMList);
        D.setCustomer(customer);
        D.setOrder(order);
        B.setWSList(WSList);
        B.setAdminList(adminList);
        B.setDSList(DSList);
        A.setRestaurant(restaurant);
        C.setCustomer(customer);
        C.setFood(food);
        C.setOrder(order);
        C.setOrderdetail(orderdetail);
        C.setRestaurant(restaurant);
        A.setFood(food);
        menu();
    }

    public static void main(String[] args) {
        Assignment assignment = new Assignment();
        assignment.initializeList();
    }

}
