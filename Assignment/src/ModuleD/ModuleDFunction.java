package ModuleD;

import domain.Customer;
import domain.DeliveryMan;
import domain.Employee;
import domain.Orders;
import domain.Restaurant;
import domain.WorkStatus;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;

public class ModuleDFunction 
{
    
    private List<DeliveryMan> deliveryMen = new ArrayList<>();
    private List<WorkStatus> workStatus = new ArrayList<>();
    Scanner s = new Scanner(System.in);
    
    public ModuleDFunction()
    {}
    
    //Let Deliveryman clock in clock out
    public Employee DeliveryMenClockInOut(List<DeliveryMan> deliveryMen, String staffID)
    {
        int choose = -99;
        
        for(int i = 0; i < deliveryMen.size(); i++) 
        {
            if(deliveryMen.get(i).getStaffID().equals(staffID))
            {
                String currentStatus = deliveryMen.get(i).getCurrentAvailable();
                
                while(choose == -99)
                {
                    if(currentStatus.equals("Not Available"))
                    {
                        System.out.println("Do you want to Clock In \n1. Yes\n2. No");
                        System.out.print("Option : ");
                        choose = s.nextInt();

                        switch (choose)
                        {
                            case 1:
                            {
                                Calendar cal = Calendar.getInstance();
                                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                                System.out.println("\nYour Clock In Time is " + dateFormat.format(cal.getTime()) + "\n");

                                //Save Clock In Detail
                                WorkStatus WS = new WorkStatus(staffID, cal, cal);
                                workStatus.add(WS);

                                //Set status to available
                                deliveryMen.get(i).setCurrentAvailable("Available");
                            }
                            case 2:
                            {
                                break;
                            }
                            default:
                            {
                                System.out.println("Please Enter Again...");
                                choose = -99;
                            }
                        }
                    }
                    else if(currentStatus.equals("Available") || currentStatus.equals("Break"))
                    {
                        System.out.println("Do you want to Clock Out \n1. Yes\n2. No");
                        System.out.print("Option : ");
                        choose = s.nextInt();

                        switch (choose)
                        {
                            case 1:
                            {
                                Calendar cal = Calendar.getInstance();
                                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                                System.out.println("\nYour Clock Out Time is " + dateFormat.format(cal.getTime()) + "\n");

                                //Update Clock Out Detail
                                for(int y = 0 ; y < workStatus.size() ; y++)
                                {
                                    if(deliveryMen.get(i).getStaffID().equals(workStatus.get(y).getWorkingID()) && workStatus.get(y).getCheckOut().equals(workStatus.get(y).getCheckIn()))
                                    {
                                        workStatus.get(y).setCheckOut(cal);
                                    }
                                }

                                //Set status to not available
                                deliveryMen.get(i).setCurrentAvailable("Not Available");
                            }
                            case 2:
                            {
                                break;
                            }
                            default:
                            {
                                System.out.println("Please Enter Again...");
                                choose = -99;
                            }
                        }
                    }
                    else if(currentStatus.equals("Deliver"))
                    {
                        System.out.println("\nYou must complete your deliver fisrt before Clock Out\n");
                        break;
                    }
                    else
                    {
                        System.out.println("\nERROR\n");
                        break;
                    }
                }
            }
        }
        return null;
    }
    
    //For Owner to view the Deliveryman Clock In Clock Out.
    public void ViewDeliverManClockInOut(List<DeliveryMan> deliveryMen)
    {
        boolean found = false;
        
        while(found == false)
        {
            System.out.print("\nPlease Enter Deliverman ID : ");
            String DmID = s.nextLine();
        
        for(int i = 0 ; i < deliveryMen.size() ; i++)
        {
            if(DmID.endsWith(deliveryMen.get(i).getStaffID()))
            {
                System.out.println("ID : " + deliveryMen.get(i).getStaffID());
                System.out.println("Name : " + deliveryMen.get(i).getStaffName());
            }
            else
            {
                System.out.println("No such ID\n");
            }
        }
        
        System.out.println("***********************************************");
        System.out.println("*       Check In      *        Check Out      *");
        System.out.println("***********************************************");
        
        for(int j = 0 ; j < workStatus.size() ; j++)
        {
            if(DmID.equals(workStatus.get(j).getWorkingID()))
            {
                if(workStatus.get(j).getCheckOut().equals(workStatus.get(j).getCheckIn()))
                {
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    System.out.println("* " + dateFormat.format(workStatus.get(j).getCheckIn().getTime()) + " *    Not Yet Check Out  *");
                    found = true;
                }
                else
                {
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    System.out.println("* " + dateFormat.format(workStatus.get(j).getCheckIn().getTime()) + "  *  " + dateFormat.format(workStatus.get(j).getCheckOut().getTime()) + " *");
                    found = true;
                }
            }
        }
            if(found == false)
            {
                System.out.println("*                                             *");
                System.out.println("*                   No Record                 *");
                System.out.println("*                                             *");
                found = true;
            }
        }
        System.out.println("***********************************************");
        System.out.println("             Press Enter to Continue           ");
        s.nextLine();
        System.out.print("\n\n");
    }
    
    //Let Deliveryman Change the deliver status
    public void ChangeDeliverStatus(List<DeliveryMan> deliveryMen, String staffID) 
    {
        
        for(int k = 0 ; k < deliveryMen.size() ; k++)
        {
            if(staffID.equals(deliveryMen.get(k).getStaffID()))
            {
                String currentStatus = deliveryMen.get(k).getCurrentAvailable();
        
                //DeliveryMen.clockInOutStatus
                if(currentStatus.equals("Available"))
                {
                    int choose = -99;

                    while(choose == -99)
                    {
                        s.nextLine();
                        System.out.println("Current Status is " + currentStatus);
                        System.out.println("Change Deliver Status");
                        System.out.println("1. Deliver \n2. Break \n3.Exit");
                        System.out.print("Option : ");
                        choose = s.nextInt();

                        if(choose == 1)
                        {
                            //Set status to deliver
                            deliveryMen.get(k).setCurrentAvailable("Deliver");
                            System.out.println("\nThe Current Status has change to Deliver\n");
                        }
                        else if(choose == 2)
                        {
                            //Set status to break
                            deliveryMen.get(k).setCurrentAvailable("Break");
                            System.out.println("\nThe Current Status has change to Break\n");
                        }
                        else if(choose == 3)
                        {
                            break;
                        }
                        else
                        {
                            System.out.println("Please Enter Again...");
                            choose = -99;
                        }
                    }
                }
                else if(currentStatus.equals("Deliver"))
                {
                    int choose = -99;

                    while(choose == -99)
                    {
                        s.nextLine();
                        System.out.println("Current Status is " + currentStatus);
                        System.out.println("Change Deliver Status");
                        System.out.println("1. Available \n2. Break \n3.Exit");
                        System.out.print("Option : ");
                        choose = s.nextInt();

                        if(choose == 1)
                        {
                            //Set status to available
                            deliveryMen.get(k).setCurrentAvailable("Available");
                            System.out.println("\nThe Current Status has change to Available\n");
                        }
                        else if(choose == 2)
                        {
                            //Set status to break
                            deliveryMen.get(k).setCurrentAvailable("Break");
                            System.out.println("\nThe Current Status has change to Break\n");
                        }
                        else if(choose == 3)
                        {
                            break;
                        }
                        else
                        {
                            System.out.println("Please Enter Again...");
                            choose = -99;
                        }
                    }
                }
                else if(currentStatus.equals("Break"))
                {
                    int choose = -99;

                    while(choose == -99)
                    {
                        s.nextLine();
                        System.out.println("Current Status is " + currentStatus);
                        System.out.println("Change Deliver Status");
                        System.out.println("1. Available \n2. Deliver \n3.Exit");
                        System.out.print("Option : ");
                        choose = s.nextInt();

                        if(choose == 1)
                        {
                            //Set status to available
                            deliveryMen.get(k).setCurrentAvailable("Available");
                            System.out.println("\nThe Current Status has change to Available\n");
                        }
                        else if(choose == 2)
                        {
                            //Set status to deliver
                            deliveryMen.get(k).setCurrentAvailable("Deliver");
                            System.out.println("\nThe Current Status has change to Deliver\n");
                        }
                        else if(choose == 3)
                        {
                            break;
                        }
                        else
                        {
                            System.out.println("Please Enter Again...");
                            choose = -99;
                        }
                    }
                }
                else
                {
                    System.out.println("\nYou must clock in first\n");
                    break;
                }
            }
        }
    }
    
    //let DeliveryMan to view to undeliver order schedule...
    public void ViewDeliverSchedule(List<Customer> customer, List<DeliveryMan> deliveryMen, List<Orders> orders, List<Restaurant> restaurant, String staffID)
    {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR);
        int minute = cal.get(Calendar.MINUTE);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        
        for(int i = 0 ; i < deliveryMen.size() ; i++)
        {
            if(staffID.endsWith(deliveryMen.get(i).getStaffID()))
            {
                System.out.println("ID : " + deliveryMen.get(i).getStaffID());
                System.out.println("Name : " + deliveryMen.get(i).getStaffName());
                System.out.println("\nDeliver Schedule ");
                System.out.println("Date : " + day + "/" + month + "/" + year);
                System.out.println("Time : " + hour + ":" + minute);
                System.out.println("******************************************");
                
                for(int j = 0 ; j < orders.size() ; j++)
                {
                    if(orders.get(j).getOrdersDay() == day && orders.get(j).getOrdersMonth() == month && orders.get(j).getOrdersYear() == year)
                    {
                        if(orders.get(j).getOrdersHour() == hour)
                        {
                            if(orders.get(j).getOrdersMinute() >= minute)
                            {
                                System.out.println("\nOrder ID : " + orders.get(j).getOrdersID());
                                System.out.println("Order Date : " + orders.get(j).getOrdersDay() + "/" + orders.get(j).getOrdersMonth() + "/" + orders.get(j).getOrdersYear());
                                System.out.println("Order Time : " + orders.get(j).getOrdersHour() + ":" + orders.get(j).getOrdersMinute());
                                System.out.println("Restaurant : " + orders.get(j).getRestaurant().getRestaurantName());
                                System.out.println("Customer Name : " + orders.get(j).getCustomer().getCustName());
                                System.out.println("Customer Hp : " + orders.get(j).getCustomer().getCustTelNo());
                                System.out.println("Customer Address : " + orders.get(j).getCustomer().getCustAddress());
                            }
                        }
                        else if(orders.get(j).getOrdersHour() > hour)
                        {
                            System.out.println("\nOrder ID : " + orders.get(j).getOrdersID());
                            System.out.println("Order Date : " + orders.get(j).getOrdersDay() + "/" + orders.get(j).getOrdersMonth() + "/" + orders.get(j).getOrdersYear());
                            System.out.println("Order Time : " + orders.get(j).getOrdersHour() + ":" + orders.get(j).getOrdersMinute());
                            System.out.println("Restaurant : " + orders.get(j).getRestaurant().getRestaurantName());
                            System.out.println("Customer Name : " + orders.get(j).getCustomer().getCustName());
                            System.out.println("Customer Hp : " + orders.get(j).getCustomer().getCustTelNo());
                            System.out.println("Customer Address : " + orders.get(j).getCustomer().getCustAddress());
                        }
                        else
                        {
                            System.out.println("No deliver order today");
                        }
                    }
                    else
                    {
                        System.out.println("No deliver order today");
                    }
                }
            }
            else
            {
                System.out.println("No such ID\n");
            }
        }
    }
    
    /**Assign Deliverymen to deliver order
    public void AssignDeliverymen(List<DeliveryMan> deliveryMen, List<Orders> orders)
    {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR);
        int minute = cal.get(Calendar.MINUTE);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        
        int newHour = -99;
        int newMinute = -99;
        int pendingMinute = minute + 2;
        
        if(pendingMinute >= 60)
        {
            newMinute = pendingMinute - 60;
            newHour = hour + 1;
        }
        else
        {
            newMinute = pendingMinute;
            newHour = hour;
        }
        
        for(int i = 0 ; i < orders.size() ; i++)
        {
            if(orders.get(i).getOrdersMinute() < newMinute && orders.get(i).getOrdersHour() <= newHour)
            {
                
            }
        }
    }**/
    
    //Let Customer to View Time Remain of Delivery Order
    public void ViewTimeRemainder()
    {
        
    }
}