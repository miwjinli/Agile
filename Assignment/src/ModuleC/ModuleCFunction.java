/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleC;
import domain.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Calendar;
import java.util.Date;
/**
 *
 * @author ong
 */
public class ModuleCFunction {
    Scanner s = new Scanner(System.in);
    
    public ModuleCFunction() {
    }
    
    public void CustomerLogin(List<Restaurant> restaurant,List<Food> food,List<Customer> customer,List<Orders> order,List<OrderDetail> orderdetail){
        String name, password;
        int check = 0;
        System.out.println("--------------");
        System.out.println("Customer Login");
        System.out.println("--------------");
        System.out.print("Name:");
        name = s.nextLine();
        name = name.toUpperCase();
        System.out.print("Password:");
        password = s.nextLine();
        
        for(int i=0 ; i<customer.size() ; i++){
            if(name.equals(customer.get(i).getCustName().toUpperCase())){
                if(password.equals(customer.get(i).getCustPass())){
                    check++;
                    System.out.println("Login Successful");
                    CustomerMenu(customer.get(i),restaurant,food,customer,order,orderdetail);
                }
                else{
                    System.out.println("Password is Invalid");
                    CustomerLogin(restaurant,food,customer,order,orderdetail);
                }
            }
        }
        if(check==0){
            System.out.println("This customer name is not exist");
            CustomerLogin(restaurant,food,customer,order,orderdetail);
        }
    }
    
    public void CustomerMenu(Customer current,List<Restaurant> restaurant,List<Food> food,List<Customer> customer,List<Orders> order,List<OrderDetail> orderdetail){
        String selection = "0";
        System.out.println("--------------");
        System.out.println("Customer Menu");
        System.out.println("--------------");
        System.out.println("Please select the below option");
        System.out.println("1. Make Order");
        System.out.println("2. View Order Cart");
        System.out.println("3. Cancel Order");
        System.out.println("4. Logout");
        while (!selection.equals("1") && !selection.equals("2") && !selection.equals("3") &&!selection.equals("4")) {
            System.out.print("Option: ");
            selection = s.nextLine();
            
            switch (selection) {
                case "1": {
                    SelectRestaurant(current,restaurant,food,customer,order,orderdetail);
                    break;
                }
                case "2": {
                    System.out.println("Under Construction");
                    break;
                }
                case "3": {
                    System.out.println("Under Construction");
                    break;
                }
                case "4": {
                    System.out.println("\n\n\n\n");
                    break;
                }
                default: {
                    System.out.println("Error, Please Key In Again.");
                    CustomerMenu(current,restaurant,food,customer,order,orderdetail);
                    break;
                }
            }
        }
    }
    
    public void SelectRestaurant(Customer current,List<Restaurant> restaurant,List<Food> food,List<Customer> customer,List<Orders> order,List<OrderDetail> orderdetail){
        boolean find = false,checkout=false;
        int resIndex=0;
        String selection = "0",foodid = "0", nextID = "0";
        List<Food> CurrentFood = new ArrayList<>();
        System.out.println("--------------");
        System.out.println("Restaurant List");
        System.out.println("--------------");
        System.out.println("Please select the below option");
        for(int i=0 ; i<restaurant.size() ; i++){
            System.out.println((i+1)+". "+restaurant.get(i).getRestaurantName());
        }
        System.out.print("Please Enter the Restaurant Name (Example:KFC) B to Back: ");
        selection = s.nextLine();
        if(selection.equals("B")){
            CustomerMenu(current,restaurant,food,customer,order,orderdetail);
        }
        else{
        for(int i=0 ; i<restaurant.size()&&find==false ; i++){
            if(selection.equals(restaurant.get(i).getRestaurantName())){
                find = true;
                resIndex = i;
                for(int j=0 ; j<food.size() ; j++){
                    if(food.get(j).getRestaurant().getRestaurantName().equals(selection)&&food.get(j).getFoodAvailability()=='1'){
                        CurrentFood.add(food.get(j));
                    }
                }
            }
        }
        if(find==false){
            System.out.println();
            System.out.println("Please Enter Again");
            SelectRestaurant(current,restaurant,food,customer,order,orderdetail);
        }
        else{
            if(order == null){
                nextID = "OR000001";
            }
            else{
                String currentID = order.get(order.size()-1).getOrdersID();
                int ID = Integer.parseInt(currentID.replace("OR", ""));
                ID++;
                nextID = "OR" + String.format("%06d",ID);
            }
            Orders currentOrder = new Orders(restaurant.get(resIndex),current,nextID,0.00,0.00,"Confirmed",0,0,0,0,0);
            List<OrderDetail> currentDetail = new ArrayList<>();
            System.out.println("Below are the foods provided by "+selection);
            System.out.println("-------------------------------------------");
            for(int k=0 ; k<CurrentFood.size() ; k++){
                System.out.println("Food ID->"+CurrentFood.get(k).getFoodID());
                System.out.println("Food Name->"+CurrentFood.get(k).getFoodName());
                System.out.println("Food Price-> RM"+CurrentFood.get(k).getFoodPrice());
                System.out.println("---------------------------------");
            }
            while(!foodid.equals("E")&&!foodid.equals("B")&&checkout==false){
                System.out.println("Please Enter the Food ID that You Want (Press C to checkout, B to back and cancel):");
                foodid = s.nextLine();
                if(foodid.equals("C")){
                    //getting the system date
                    Date date = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    int hour = cal.get(Calendar.HOUR);
                    int minute = cal.get(Calendar.MINUTE);
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH)+1;
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    currentOrder.setOrdersDay(day);
                    currentOrder.setOrdersHour(hour);
                    currentOrder.setOrdersMinute(minute);
                    currentOrder.setOrdersMonth(month);
                    currentOrder.setOrdersYear(year);
                    order.add(currentOrder);
                    for(int i=0 ; i<currentDetail.size() ; i++){
                        orderdetail.add(currentDetail.get(i));
                    }
                    
                    for(int j=0 ; j<orderdetail.size() ; j++){
                        System.out.println("---------------------------------");
                        System.out.println("Order Date Time->"+orderdetail.get(j).getOrders().DatetoString());
                        System.out.println("Restaurant Name->"+orderdetail.get(j).getOrders().getRestaurant().getRestaurantName());
                        System.out.println("Food ID->"+orderdetail.get(j).getFood().getFoodID());
                        System.out.println("Quantity->"+orderdetail.get(j).getQuantity());
                    }
                    s.nextLine();
                    checkout=true;
                }
                else if(foodid.equals("B")){
                    SelectRestaurant(current,restaurant,food,customer,order,orderdetail);
                }
                else{
                System.out.println("Please Enter the Quantity:");
                int quantity = s.nextInt();
                s.nextLine();
                for(int z=0 ; z<CurrentFood.size() ; z++){
                    if(CurrentFood.get(z).getFoodID().equals(foodid)){
                        currentDetail.add(new OrderDetail(currentOrder,CurrentFood.get(z),quantity));
                    }
                }
                }
            };
        }
        }
    }
}
