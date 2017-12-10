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
        System.out.print("Restaurant Type: ");
        String rType = sc.nextLine();
        char rMenu = 'A';
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
            Restaurant rest = new Restaurant(restid, rName, rType, rMenu, oName, add, no, area, latitude, pass);
            restaurant.add(rest);
            System.out.println("Successfully Registered.");
        }
    }

    public boolean RestaurantLogin(Restaurant r) {
        int idcount = 0;
        boolean exit = false;
        int password = 0;
        while (idcount == 0) {
            System.out.println("----------------");            
            System.out.println("Affiliate Login");
            System.out.println("----------------");
            System.out.print("Restaurant ID (press E to exit): ");
            String id = sc.nextLine().toUpperCase();
            if(id.equals("E")){
                exit = true;
                return exit;
            }else{
                for (int i=0;i < restaurant.size();i++) {
                    if (id.equals(restaurant.get(i).getRestaurantID())) {
                        while (password == 0) {
                            System.out.print("Password: ");
                            String pass = sc.nextLine();
                            idcount = 1;
                            if (pass.equals(restaurant.get(i).getPassword())) {
                                password = 1;
                                System.out.println("Successfully Login");
                                r = restaurant.get(i);
                                RestaurantMenu(r);
                            } else {
                                System.out.println("Invalid password");
                            }
                        }
                    }
                }
            }
            if (idcount == 0) {
                System.out.println("Invalid ID");
                RestaurantLogin(r);
            }
        }
        return exit;
        
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
        char pStatus = 'N';
        
        Food f = new Food(foodid, fName, price, fType, fAval, r, pStatus);
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
                System.out.println("Food ID: "+food.get(j).getFoodID()+"\t"+"Food Name: "+food.get(j).getFoodName());
                System.out.println("Food Price: RM"+food.get(j).getFoodPrice()+"\t"+"Food Availability: "+food.get(j).getFoodAvailability());
                System.out.println("Promotional Status: "+food.get(j).getpStatus());
                System.out.println("\n");
            }
        }
    }
    
    public void updateFoodInterface(Restaurant r){
        boolean id = false;
        System.out.println("------------------");
        System.out.println("Update Food Detail");
        System.out.println("------------------");
        System.out.println("Login as " + r.getRestaurantName() + " Restaurant\n");
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
                System.out.println("Food Availability: " + food.get(i).getFoodAvailability()+
                        " (A-Available N-Temporary not available)");
                System.out.println("Promotional Status: " + food.get(i).getpStatus()+
                        " (Y-Under promotion N-Not promotion");
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
        System.out.println("4 - Promotional Status");
        System.out.println("0 - Back");
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
                sc.nextLine();
                RestaurantMenu(r);
                break;
            }
            case 2:{
                int t = 1;
                System.out.println("Current Food Price: RM" + f.getFoodPrice());
                do{
                System.out.print("Updated Food Price: RM");
                try{
                double uPrice = sc.nextDouble();
                f.setFoodPrice(uPrice);
                System.out.println("Successfully updated");
                sc.nextLine();
                RestaurantMenu(r);
                t = 1;
                }catch(Exception e){
                    System.out.println("Invalid input");
                    sc.nextLine();
                    t = 0;
                }
                }while(t == 0);
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
                            sc.nextLine();
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
                int a = 0;
                System.out.println("Current Promotional Status: "+ f.getpStatus());
                System.out.println("Y-Under promotion N-Not promotion");
                while(a==0){
                    System.out.print("Updated Promotional Status: ");
                    char pstat = Character.toUpperCase(sc.next().charAt(0));
                    switch(pstat){
                        case 'Y':
                        case 'N':{
                            f.setpStatus(pstat);
                            a=1;
                            sc.nextLine();
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
            case 0:{
                sc.nextLine();
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
        System.out.println("----------------\n");
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
    
    public void SelectShowFirstMenu(Restaurant r){
        int check = 0;
        System.out.println("----------------------");
        System.out.println("Select Show First Menu");
        System.out.println("----------------------\n");
        
        System.out.println("Please select which you want to show first.");
        System.out.println("A. Show as Normal");
        System.out.println("N. Show Newest Items First");
        System.out.println("P. Show Promotional Items First\n");
        
        System.out.println("Current Showed Menu: " + r.getRMenu());
        System.out.println("(A-Show as Normal N-Newest Items First P-Promotional Items First)");
        do{
        System.out.print("Option: ");
        char select = Character.toUpperCase(sc.next().charAt(0));
        switch(select){
            case 'A':{
                System.out.println("Successful Changed.");
                char sf = 'A';
                r.setRMenu(sf);
                check=1;
                RestaurantMenu(r);
                break;
            }
            case 'N':{
                System.out.println("Successful Changed.");
                char sf = 'N';
                r.setRMenu(sf);
                check=1;
                RestaurantMenu(r);
                break;
            }
            case 'P':{
                System.out.println("Successful Changed.");
                char sf = 'P';
                r.setRMenu(sf);
                check=1;
                RestaurantMenu(r);
                break;
            }
            default:{
                System.out.println("Invalid input.");
                check=0;
            }
        }
        }while(check==0);
 
    }
    
    public void showFoodMenu(Restaurant r1, List<Food> food){
        char rMenu = r1.getRMenu();
        switch(rMenu){
            case 'A':{
                showMenu(r1, food);
                break;
            }
            case 'N':{
                showNewest(r1, food);
                break;
            }
            case 'P':{
                showPromotional(r1, food);
                break;
            }
        }
    }
    
    public void showMenu(Restaurant r1, List<Food> food){
        System.out.println("---------");
        System.out.println("Food Menu");
        System.out.println("---------");
        for(int j=0; j<food.size();j++){
            
            if(r1.getRestaurantName().equals(food.get(j).getRestaurant().getRestaurantName())&&food.get(j).getFoodAvailability()=='A'){
                System.out.println("Food ID: "+food.get(j).getFoodID()+"\t"+"Food Name: "+food.get(j).getFoodName());      
                System.out.println("Food Price: RM"+food.get(j).getFoodPrice()+"\t"+"Food Type: "+food.get(j).getFoodType()+"\n");
            }

        }
    }
    
    public void showNewest(Restaurant r1, List<Food> food){
        System.out.println("---------");
        System.out.println("Food Menu");
        System.out.println("---------");
        System.out.println("From Newest Menu to Oldest Menu");
        for(int j=food.size()-1; j>=0;j--){
            if(r1.getRestaurantName().equals(food.get(j).getRestaurant().getRestaurantName())&&food.get(j).getFoodAvailability()=='A'){
                System.out.println("Food ID: "+food.get(j).getFoodID()+"\t"+"Food Name: "+food.get(j).getFoodName());      
                System.out.println("Food Price: RM"+food.get(j).getFoodPrice()+"\t"+"Food Type: "+food.get(j).getFoodType()+"\n");
            }

        }
    }
    
    public void showPromotional(Restaurant r1, List<Food> food){
        System.out.println("-----------------------");
        System.out.println("Food Under Promotion!!!");
        System.out.println("-----------------------\n");
        for(int j=0 ; j<food.size() ; j++){
            if(r1.getRestaurantName().equals(food.get(j).getRestaurant().getRestaurantName())&&food.get(j).getFoodAvailability()=='A'&&food.get(j).getpStatus()=='Y'){
                System.out.println("Food ID: "+food.get(j).getFoodID()+"\t"+"Food Name: "+food.get(j).getFoodName());      
                System.out.println("Food Price: RM"+food.get(j).getFoodPrice()+"\t"+"Food Type: "+food.get(j).getFoodType()+"\n");
            }
        }
        System.out.println("---------");
        System.out.println("Food Menu");
        System.out.println("---------\n");
        for(int j=0 ; j<food.size() ; j++){
            if((r1.getRestaurantID()).equals(food.get(j).getRestaurant().getRestaurantID())&&food.get(j).getFoodAvailability()=='A'&&food.get(j).getpStatus()=='N'){
                System.out.println("Food ID: "+food.get(j).getFoodID()+"\t"+"Food Name: "+food.get(j).getFoodName());      
                System.out.println("Food Price: RM"+food.get(j).getFoodPrice()+"\t"+"Food Type: "+food.get(j).getFoodType()+"\n");
            }
        }
        
    }
    
    public void RestaurantMenu(Restaurant r) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------");
        System.out.println("Restaurant Menu");
        System.out.println("---------------");
        System.out.println("1. Add New Menu Items");
        System.out.println("2. Update Menu Item Details");
        System.out.println("3. Remove Menu Items");
        System.out.println("4. Select Show First Menu");
        System.out.println("5. Show Menu");
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
            case 4: {
                SelectShowFirstMenu(r);
                break;
            }
            case 5:{
                showFoodMenu(r, food);
                sc.nextLine();
                RestaurantMenu(r);
                break;
            }
            case 0: {
                System.out.println("Successfully Logout");
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
