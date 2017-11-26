/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuleA;

import domain.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author HP
 */
public class ModuleAFunction {

    private List<Restaurant> restaurant = new ArrayList<>();
    private List<Food> food = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public ModuleAFunction() {
    }

    public void RestaurantRegistration() {

        System.out.println("-----------------------");
        System.out.println("Affiliate Registration");
        System.out.println("-----------------------");

        int totalRest = restaurant.size();
        String restid = String.format("RE%06d", totalRest + 1);
        System.out.println("Restaurant ID: " + restid);
        System.out.println("(Please remember the ID for further use.)");
        System.out.print("Restaurant Name: ");
        String rName = sc.nextLine();
        System.out.print("Owner Name: ");
        String oName = sc.nextLine();
        System.out.print("Address: ");
        String add = sc.nextLine();
        System.out.print("Contact No: ");
        String no = sc.nextLine();
        System.out.print("Area: ");
        String area = sc.nextLine();
        System.out.print("Latitude: ");
        String latitude = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();

        boolean b = true;
        for (int i = 0; i < restaurant.size(); i++) {
            if (add.equals(restaurant.get(i).getAddress())) {
                System.out.println("Restaurant already exists in the system.");
                RestaurantRegistration();
                b = false;
            }
        }
        if (b == true) {
            Restaurant rest = new Restaurant(restid, rName, oName, add, no, area, latitude, pass);
            restaurant.add(rest);
            System.out.println("Successfully Registered.");
        }
    }

    public boolean RestaurantLogin(Restaurant r) {
        int idcount = 0;
        boolean password = false;
        while (idcount == 0) {
            System.out.println("----------------");            
            System.out.println("Affiliate Login");
            System.out.println("----------------");
            System.out.print("Restaurant ID: ");
            String id = sc.nextLine().toUpperCase();
            for (int i=0;i < restaurant.size();i++) {
                if (id.equals(restaurant.get(i).getRestaurantID())) {
                    while (!password) {
                        System.out.print("Password: ");
                        String pass = sc.nextLine();
                        idcount = 1;
                        if (pass.equals(restaurant.get(i).getPassword())) {
                            password = true;
                            System.out.println("Successfully Login");
                            r = restaurant.get(i);
                            RestaurantMenu(r);
                        } else {
                            System.out.println("Invalid password");
                        }
                    }
                }
            }
            if (idcount == 0) {
                System.out.println("Invalid ID");
            }
        }
        return password;
    }
    
    public void addFood(Restaurant r){
        System.out.println("--------");
        System.out.println("Add Food");
        System.out.println("--------");
        
        int totalFood = food.size();
        String foodid = String.format("FM%06d", totalFood + 1);
        System.out.println("Food ID: " + foodid);
        System.out.print("Food Name: ");
        String fName = sc.nextLine();
        System.out.print("Price: ");
        Double price = sc.nextDouble();
        sc.nextLine();
        System.out.print("Food Type: ");
        String fType = sc.nextLine();
        char fAval = 'A';
        
        Food f = new Food(foodid, fName, price, fType, fAval, r);
        food.add(f);
        
        System.out.println("Food Successfully Added");
        System.out.println("Food ID: " + foodid);
        System.out.println("Food Name: " + fName);
        System.out.println("Price: RM" + price);
        System.out.println("Food Type: " + fType);
        System.out.println("Food Availability: Available");
        //System.out.println(r);
        
        RestaurantMenu(r);
    }
    
    public void foodMenu(Restaurant r){
        for(int j=0 ; j<food.size() ; j++){
            if(r.equals(food.get(j).getRestaurant())&&food.get(j).getFoodAvailability()=='A'||food.get(j).getFoodAvailability()=='N'){
                System.out.println("Food ID: "+food.get(j).getFoodID()+" Food Name: "+food.get(j).getFoodName()+" Food Price: RM"
                +food.get(j).getFoodPrice()+" Food Availability: "+food.get(j).getFoodAvailability());   
            }
        }
    }
    
    public void updateFoodInterface(Restaurant r){
        boolean id = false;
        System.out.println("------------------");
        System.out.println("Update Food Detail");
        System.out.println("------------------");
        System.out.println("Login as " + r.getRestaurantName() + " Restaurant");
        foodMenu(r);
        System.out.print("Enter Food ID to update: ");
        String fid = sc.nextLine().toUpperCase();
        int i=0; 
        while(i<food.size()){
            if(fid.equals(food.get(i).getFoodID())&& r.equals(food.get(i).getRestaurant())){        
                System.out.println("FoodID: " + food.get(i).getFoodID());
                System.out.println("Food Name: " + food.get(i).getFoodName());
                System.out.println("Price: RM" + food.get(i).getFoodPrice());
                System.out.println("Food Type: " + food.get(i).getFoodType());
                System.out.println("Food Availability: " + food.get(i).getFoodAvailability()+" (A-Available N-Temporary not available)");
                Food f = food.get(i);
                id = true; 
                updateFood(r,f);
                
                   
            }
            i++;
        }
            if(id == false){
                System.out.println("Invalid Food ID, Please try again.");
                updateFoodInterface(r);
            }      
    }
    
    public void updateFood(Restaurant r, Food f){
        System.out.println("Which you want to update?");
        System.out.println("1 - Food Name");
        System.out.println("2 - Food Price");
        System.out.println("3 - Food Availability");
        System.out.println("4 - Back");
        System.out.print("Option: ");
        int option =sc.nextInt();
        switch(option){
            case 1:{
                System.out.println("Current Food Name: " + f.getFoodName());
                System.out.print("Updated Food Name: ");
                sc.nextLine();
                String uName = sc.nextLine();
                f.setFoodName(uName);
                System.out.println("Successfully updated");
                RestaurantMenu(r);
                break;
            }
            case 2:{
                System.out.println("Current Food Price: RM" + f.getFoodPrice());
                System.out.print("Updated Food Price: RM");
                double uPrice = sc.nextDouble();
                f.setFoodPrice(uPrice);
                System.out.println("Successfully updated");
                RestaurantMenu(r);
                break;
            }
            case 3:{
                int a = 0;
                System.out.println("Current Food Availability: "+ f.getFoodAvailability());
                System.out.println("A-Available N-Temporary not available");
                    while(a==0){
                    System.out.print("Updated Availability: ");
                    char aval = Character.toUpperCase(sc.next().charAt(0));
                        switch(aval){
                            case 'A':
                            case 'N':{
                            f.setFoodAvailability(aval);
                            a=1;
                            RestaurantMenu(r);
                            break;
                        }
                            default:{
                                System.out.println("Invalid Input");
                                a = 0;
                            }
                        }
                    }
                break;
            }
            case 4:{
                RestaurantMenu(r);
                break;
            }
            default:{
                System.out.println("Invalid. Please try again.");
            }
        }
    }
    
    public void deleteFood(Restaurant r){
        int f=0;
        System.out.println("----------------");
        System.out.println("Delete Food Menu");
        System.out.println("----------------");
        foodMenu(r);
        System.out.println("Which one you wish to delete");
        System.out.print("Enter Food ID to delete: ");
        String fid = sc.nextLine().toUpperCase();
        for(int i =0;i<food.size();i++){
                if(fid.equals(food.get(i).getFoodID())&& r.equals(food.get(i).getRestaurant())){
                    System.out.println("Are you sure you want to delete?(y - yes n - no)");
                    System.out.print("Option:");
                    char option = Character.toUpperCase(sc.nextLine().charAt(0));
                    switch(option){
                        case 'Y':{
                            System.out.println("Successfully deleted");
                            food.get(i).setFoodAvailability('D');
                            f=1;
                            RestaurantMenu(r);
                            break;
                        }
                        case 'N':{
                            f=1;
                            RestaurantMenu(r);
                            break;
                        }
                        default:{
                            f=0;
                            System.out.println("Invalid input");
                            RestaurantMenu(r);
                            break;
                        }
                    }
                }
            }
            if(f==0){
                System.out.println("Invalid input");
                deleteFood(r);
            }
        
    }
    
    public void RestaurantMenu(Restaurant r) {
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
                addFood(r);
                break;
            }
            case 2: {
                updateFoodInterface(r);
                sc.nextLine();
                break;
            }
            case 3: {
                deleteFood(r);
                break;
            }
            case 0: {
                System.out.println("Successfully Logout");
                //menu();
                RestaurantLogin(r);
                break;
            }
            default: {
                System.out.println("Error. Please key in again.");
                RestaurantMenu(r);
                break;
            }
        }
    }

    public void setRestaurant(List<Restaurant> restaurant) {
        this.restaurant = restaurant;
    }
    
    public void setFood(List<Food> food){
        this.food = food;
    }
}
