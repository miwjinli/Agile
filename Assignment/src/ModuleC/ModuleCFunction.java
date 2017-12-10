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
    Orders currentOrder = new Orders();
    List<OrderDetail> currentDetail = new ArrayList<>();
    double Subtotal = 0.00;
    List<Food> CurrentFood = new ArrayList<>();
    
    List<Restaurant> restaurant = new ArrayList<>();
    List<Food> food = new ArrayList<>();
    List<Orders> order = new ArrayList<>();
    List<OrderDetail> orderdetail = new ArrayList<>();
    List<Customer> customer = new ArrayList<>();
    
    public ModuleCFunction() {
    }
    
    public void CustomerLogin(){
        String name, password;
        int check = 0;
        System.out.println("****************");
        System.out.println("*Customer Login*");
        System.out.println("****************");
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
                    CustomerMenu(customer.get(i));
                }
                else{
                    System.out.println("Password is Invalid");
                    CustomerLogin();
                }
            }
        }
        if(check==0){
            System.out.println("This customer name is not exist");
            CustomerLogin();
        }
    }
    
    public void CustomerMenu(Customer current){
        String selection = "0";
        System.out.println("***************");
        System.out.println("*Customer Menu*");
        System.out.println("***************");
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
                    SelectRestaurant(current);
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
                    CustomerMenu(current);
                    break;
                }
            }
        }
    }
    
    public String getCurrentID(){
        String nextID = "";
        if(order == null){
                nextID = "OR000001";
        }
        else{
            String currentID = order.get(order.size()-1).getOrdersID();
            int ID = Integer.parseInt(currentID.replace("OR", ""));
            ID++;
            nextID = "OR" + String.format("%06d",ID);
        }
        return nextID;
    }
    
    public void SelectRestaurant(Customer current){
        boolean find = false;
        int resIndex=0;
        String selection = "0";
        System.out.println("\n\n*****************");
        System.out.println("*Restaurant List*");
        System.out.println("*****************");
        System.out.println("Please select the below option");
        for(int i=0 ; i<restaurant.size() ; i++){
            System.out.println((i+1)+". "+restaurant.get(i).getRestaurantName());
        }
        System.out.print("Please Enter the Restaurant Name (Example:KFC) B to Back: ");
        selection = s.nextLine();
        if(selection.equals("B")){
            CustomerMenu(current);
        }
        else{
        for(int i=0 ; i<restaurant.size()&&find==false ; i++){
            if(selection.equals(restaurant.get(i).getRestaurantName())){
                find = true;
                resIndex = i;
                for(int j=0 ; j<food.size() ; j++){
                    if(food.get(j).getRestaurant().getRestaurantName().equals(selection)&&food.get(j).getFoodAvailability()=='A'){
                        CurrentFood.add(food.get(j));
                    }
                }
                makeOrder(current,resIndex);
            }
        }
        if(find==false){
            System.out.println();
            System.out.println("Please Enter Again");
            SelectRestaurant(current);
        }
      }
    }
    
    public void makeOrder(Customer current, int resIndex){
        boolean checkout=false, ordered=false;
        String selection = "0",foodid = "0", nextID = "0";
        int quantity;
        nextID = getCurrentID();
        currentOrder.setCustomer(current);
        currentOrder.setOrderStatus("Pending");
        currentOrder.setOrdersDay(0);
        currentOrder.setOrdersHour(0);
        currentOrder.setOrdersID(nextID);
        currentOrder.setOrdersMinute(0);
        currentOrder.setOrdersMonth(0);
        currentOrder.setOrdersYear(0);
        currentOrder.setRestaurant(restaurant.get(resIndex));
        currentOrder.setSubtotal(Subtotal);
        currentOrder.setTotal(Subtotal*1.06);
        System.out.println("\n\nBelow are the foods provided by "+restaurant.get(resIndex).getRestaurantName());
        System.out.println("-------------------------------------------");
        for(int k=0 ; k<CurrentFood.size() ; k++){
            System.out.println("Food ID->"+CurrentFood.get(k).getFoodID());
            System.out.println("Food Name->"+CurrentFood.get(k).getFoodName());
            System.out.printf("Food Price-> RM%.2f\n",CurrentFood.get(k).getFoodPrice());
            System.out.println("---------------------------------");
        }
        while(!foodid.equals("C")&&!foodid.equals("B")&&!foodid.equals("V")&&checkout==false){
            System.out.println("Please Enter the Food ID that You Want");
            System.out.println("(Press C to confirm, B to back and cancel, V to view cart):");
            foodid = s.nextLine();
            foodid = foodid.toUpperCase();
            
            if(foodid.equals("C")){
                checkout = Confirmation(current);
                if(checkout==false){
                    makeOrder(current,resIndex);
                }
            }
            else if(foodid.equals("B")){
                currentOrder = new Orders();
                currentDetail.clear();
                Subtotal = 0.00;
                CurrentFood.clear();
                CustomerMenu(current);
                break;
            }
            else if(foodid.equals("V")){
                boolean again = ViewCart(current);
                if(again){
                    makeOrder(current,resIndex);
                    break;
                }
                else{
                    CustomerMenu(current);
                    break;
                }
            }
            else{
                //check whether food id is exist or not
                boolean foodcheck = false;
                for(int i=0; i<CurrentFood.size()&&foodcheck==false; i++){
                    if(foodid.equals(CurrentFood.get(i).getFoodID())){
                        foodcheck = true;
                        do{
                            System.out.println("Please Enter the Quantity:");
                            while(!s.hasNextInt()){
                                System.out.println("Please Enter the Quantity in Integer:");
                                s.next();
                            }
                            quantity = s.nextInt();
                            s.nextLine();
                        }while(quantity<1);
                        double currentSubtotal = CurrentFood.get(i).getFoodPrice()*quantity;
                        for(int q=0 ; q<currentDetail.size() ; q++){
                            if(currentDetail.get(q).getFood().getFoodID().equals(foodid)){
                                int currentqty = currentDetail.get(q).getQuantity();
                                quantity = quantity + currentqty;
                                currentDetail.get(q).setQuantity(quantity);
                                currentSubtotal = CurrentFood.get(i).getFoodPrice()*(quantity-currentqty);
                                ordered=true;
                            }
                        }

                        if(ordered == false){
                        currentDetail.add(new OrderDetail(currentOrder,CurrentFood.get(i),quantity));
                        }

                        Subtotal+=currentSubtotal;
                        currentOrder.setSubtotal(Subtotal);
                    }
                }
                if(foodcheck==false){
                    System.out.println("Please Enter The Correct Food ID");
                }
                //end of checking
            }
        }
    }
    
    public boolean ViewCart(Customer current){
        String selection = "";
        boolean again = true;
        if(currentDetail.isEmpty()){
            System.out.println("\n\nYou Do Not Order Any Food Yet.");
            System.out.println("Press Enter To Back.");
            s.nextLine();
        }
        else{
            System.out.println("\n\nBelow Are The Foods You Have Ordered Inside Your Cart");
            System.out.println("------------------------------------");
            for(int i=0 ; i<currentDetail.size() ; i++){
                System.out.println("Food ID: "+currentDetail.get(i).getFood().getFoodID());
                System.out.println("Food Name: "+currentDetail.get(i).getFood().getFoodName());
                System.out.println("Quantity: "+currentDetail.get(i).getQuantity());
            }
            System.out.println("------------------------------------");
            System.out.println("\nPlease Select Your Selection");
            System.out.println("1. Edit Food");
            System.out.println("2. Delete Food");
            System.out.println("3. Cancel Order");
            System.out.println("4. Back");
            while(!selection.equals("1") && !selection.equals("2") && !selection.equals("3")){
                System.out.print("Selection: ");
                selection = s.nextLine();
                switch(selection){
                    case "1":{
                        System.out.println("Edit!");
                        break;
                    }
                    case "2":{
                        System.out.println("Delete");
                        break;
                    }
                    case "3":{
                        currentOrder = new Orders();
                        currentDetail.clear();
                        Subtotal = 0.00;
                        CurrentFood.clear();
                        System.out.println("\nCancel Order Successful\n\n");
                        again = false;
                        break;
                    }
                    case "4":{
                        break;
                    }
                    default:{
                        System.out.println("Please Enter Again");
                        break;
                    }
                }
            }
        }
        return again;
    }
    
    public boolean Confirmation(Customer current){
        String selection = "";
        double roundoff = 0.00, total = 0.00;
        System.out.println("\n\nBelow Are The Foods You Have Ordered");
        System.out.println("------------------------------------");
        for(int i=0 ; i<currentDetail.size() ; i++){
            System.out.println("Food ID: "+currentDetail.get(i).getFood().getFoodID());
            System.out.println("Food Name: "+currentDetail.get(i).getFood().getFoodName());
            System.out.println("Quantity: "+currentDetail.get(i).getQuantity());
        }
        System.out.println("------------------------------------");
        System.out.printf("Subtotal: RM%.2f\n",currentOrder.getSubtotal());
        System.out.printf("GST: RM%.2f\n",(currentOrder.getSubtotal()*0.06));
        System.out.printf("Total: RM%.2f\n",(currentOrder.getSubtotal()*1.06));
        System.out.println("\n\nAre You Sure Want To CheckOut?");
        System.out.println("1. Yes");
        System.out.println("2. Back To Food Selection");
        while(!selection.equals("1") && !selection.equals("2")){
        System.out.print("Selection: ");
        selection = s.nextLine();
        switch(selection){
            case "1":{
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
                    currentOrder.setSubtotal(Subtotal);
                    currentOrder.setTotal(Subtotal*1.06);
                    currentOrder.setOrderStatus("1");//change to 1
                    order.add(currentOrder);
                    for(int i=0 ; i<currentDetail.size() ; i++){
                        orderdetail.add(currentDetail.get(i));
                    }
                    System.out.println("\nThank You For Your Order.");
                    System.out.println("You Have Order The Following Items.");
                    System.out.println("-----------------------------------");
                    for(int h=0;h<currentDetail.size();h++){
                        System.out.println("Food Name: "+currentDetail.get(h).getFood().getFoodName());
                        System.out.println("Food ID: "+currentDetail.get(h).getFood().getFoodID());
                        System.out.println("Quantity: "+currentDetail.get(h).getQuantity());
                    }
                    System.out.printf("Subtotal: RM%.2f\n",currentOrder.getSubtotal());
                    System.out.printf("GST: RM%.2f\n",(currentOrder.getSubtotal()*0.06));
                    System.out.printf("Total: RM%.2f\n",currentOrder.getTotal());
                    /*for(int k=0 ; k<order.size() ; k++){
                        System.out.println("---------------------------------");
                        System.out.println("Order Date Time->"+order.get(k).DatetoString());
                        System.out.println("---------------------------------");
                        System.out.println("Restaurant Name->"+order.get(k).getRestaurant().getRestaurantName());
                        for(int j=0 ; j<orderdetail.size() ; j++){
                            if(orderdetail.get(j).getOrders().getOrdersID().equals(order.get(k).getOrdersID())){
                                System.out.println("Food ID->"+orderdetail.get(j).getFood().getFoodID());
                                System.out.println("Quantity->"+orderdetail.get(j).getQuantity());
                            }
                        }
                        System.out.printf("Subtotal->RM%.2f\n",order.get(k).getSubtotal());
                        System.out.printf("Total->RM%.2f\n",order.get(k).getTotal());
                        System.out.println("-----------------------------");
                    }*/
                    currentOrder = new Orders();
                    currentDetail.clear();
                    Subtotal= 0.00;
                    CurrentFood.clear();
                    s.nextLine();
                    return true;
            }
            case "2":{
                return false;
            }
            default:{
                System.out.println("Please Enter Again");
            }
        }
        }
        return true;
    }
    
    public void retrieveCustomer(){
        String contact = "";
        boolean check = false;
        System.out.println("\n\n***************************");
        System.out.println("*Retrieve Customer Details*");
        System.out.println("***************************");
        while(check==false){
            System.out.println("Please Enter The Contact Number :");
            while (s.hasNext("[A-Za-z]+")) {
                System.out.println("Please Enter The Contact Number in Correct Format.");
                s.nextLine();
            }
            contact = s.nextLine();
            for(int i=0 ; i<customer.size(); i++){
                if(contact.equals(customer.get(i).getCustTelNo())){
                    System.out.println("\n\nPersonal Information");
                    System.out.println("---------------------------");
                    System.out.println("Name: "+customer.get(i).getCustName());
                    System.out.println("Address: "+customer.get(i).getCustAddress());
                    System.out.println("Area: "+customer.get(i).getCustArea());
                    System.out.println("IC Number: "+customer.get(i).getCustIC());
                    System.out.println("\n");
                    check=true;
                }
            }
            if(check==false){
                System.out.println("This Contact Number is Not Exist in Customer Database");
                s.nextLine();
            }
        };
    }

    public void setRestaurant(List<Restaurant> restaurant) {
        this.restaurant = restaurant;
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }

    public void setOrder(List<Orders> order) {
        this.order = order;
    }

    public void setOrderdetail(List<OrderDetail> orderdetail) {
        this.orderdetail = orderdetail;
    }

    public void setCustomer(List<Customer> customer) {
        this.customer = customer;
    }
    
    
}
