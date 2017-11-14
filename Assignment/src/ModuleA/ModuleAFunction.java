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
    Scanner sc = new Scanner(System.in);

    public ModuleAFunction() {
    }

    public void RestaurantRegistration(List<Restaurant> restaurant) {

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
                RestaurantRegistration(restaurant);
                b = false;
            }
        }
        if (b == true) {
            Restaurant rest = new Restaurant(restid, rName, oName, add, no, area, latitude, pass);
            restaurant.add(rest);
            System.out.println("Successfully Registered.");
        }
    }

    public boolean RestaurantLogin(List<Restaurant> restaurant) {
        int idcount = 0;
        boolean password = false;
        while (idcount == 0) {
            System.out.println("----------------");
            System.out.println("Affiliate Login");
            System.out.println("----------------");
            System.out.print("Restaurant ID: ");
            String id = sc.nextLine().toUpperCase();
            for (int i = 0; i < restaurant.size(); i++) {
                if (id.equals(restaurant.get(i).getRestaurantID())) {
                    while (!password) {
                        System.out.print("Password: ");
                        String pass = sc.nextLine();
                        idcount = 1;
                        if (pass.equals(restaurant.get(i).getPassword())) {
                            password = true;
                            System.out.println("Successfully Login");
                            //RestaurantMenu(restaurant);
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

    public void setRestaurant(List<Restaurant> restaurant) {
        this.restaurant = restaurant;
    }
}
