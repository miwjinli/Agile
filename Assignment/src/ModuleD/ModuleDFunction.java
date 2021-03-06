package ModuleD;

import domain.Customer;
import domain.DeliveryMan;
import domain.Employee;
import domain.Orders;
import domain.Restaurant;
import domain.WorkStatus;
import domain.DeliveryStatus;
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
    private List<DeliveryStatus> DSList = new ArrayList<>();
    private List<Customer> CustList = new ArrayList<>();
    private List<Orders> orders = new ArrayList<>();
    
    Scanner s = new Scanner(System.in);
    
    public List<DeliveryMan> getDeliveryMen() {
        return deliveryMen;
    }

    public void setDeliveryMen(List<DeliveryMan> deliveryMen) {
        this.deliveryMen = deliveryMen;
    }
    
    public List<DeliveryStatus> getDeliveryStatus() {
        return DSList;
    }

    public void setDeliveryStatus(List<DeliveryStatus> DSList) {
        this.DSList = DSList;
    }
    
    public List<Customer> getCustomer() {
        return CustList;
    }

    public void setCustomer(List<Customer> CustList) {
        this.CustList = CustList;
    }
    
    public List<Orders> getOrder() {
        return orders;
    }

    public void setOrder(List<Orders> order) {
        this.orders = order;
    }
    
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
    
    //let DeliveryMan to view to undeliver order schedule...(logic error)
    public void ViewDeliverSchedule(List<Customer> customer, List<DeliveryMan> deliveryMen, List<Orders> orders, List<Restaurant> restaurant, String staffID)
    {
        /*Date date = new Date();
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
        }*/
    }
    
    
    //ABC
    public void DisplayPendingOrder()
    {
        
    }
    
    public void AssignFunction(List<Orders> orders)
    {/*
        boolean nextAssign = false;
        do
        {
            String orderIDtoAssign;
            int whileloop = 1;
            int choice;
            boolean orderTime;
            boolean assignFinish = false;
            
            System.out.println("\nPending Order");
            System.out.println("*******************");
            orderTime = CalPendingOrderTime(orders);
        
            if(!orderTime)
            {
                System.out.println("\nNo Order\n");
                whileloop = -99;
            }

            System.out.println("*******************");

            //If have order
            while(whileloop == 1)
            {
                System.out.print("Enter Order ID : ");
                orderIDtoAssign = s.nextLine();

                boolean check = checkOrderID(orders, orderIDtoAssign);
                if(check)
                {
                    for(int i = 0 ; i < orders.size() ; i++)
                    {
                        if(orders.get(i).getOrdersID().equals(orderIDtoAssign))
                        {
                            System.out.println("\nCustomer Order Detail");
                            System.out.println("********************************************");
                            System.out.println(orders.get(i).getCustomer().getCustName());
                            System.out.println(orders.get(i).getCustomer().getCustTelNo());
                            System.out.println(orders.get(i).getCustomer().getCustAddress());
                            System.out.println("********************************************");
                            whileloop = -99;

                            AssignDeliveryMen(orders.get(i));

                            assignFinish = true;
                        }
                    }
                }
                else
                {
                    System.out.println("Please Enter Again......");
                    s.nextLine();
                }
            }

            if(assignFinish)
            {
                boolean wrong = false;

                do
                {
                    System.out.println("\nAny Deliverymen to Assign ?");
                    System.out.print("1. Yes\t2. No\nOption : ");
                    choice = s.nextInt();

                    switch(choice)
                    {
                        case 1: {nextAssign = true; wrong = false; break;}
                        case 2: {nextAssign = false; wrong = false; break;}
                        default: {wrong = true; break;}
                    }
                }while(wrong);
            }
        
        }while(nextAssign);*/
    }
    
    public boolean checkOrderID(List<Orders> orders, String orderIDtoAssign)
    {
        int newHour;
        int newMinute;
        int pendingMinute;
        boolean check = false;
        
        /*for(int i = 0 ; i < orders.size() ; i++)
        {
            if(orders.get(i).getOrdersID().equals(orderIDtoAssign))
            {
                pendingMinute = orders.get(i).getOrdersMinute() + 2;

                if(orders.get(i).getOrderStatus().equals("1"))
                {
                    if(pendingMinute >= 60)
                    {
                        newMinute = pendingMinute - 60;
                        newHour = orders.get(i).getOrdersHour() + 1;
                        check = ComparePendingTime(orders.get(i), newHour, newMinute);
                    }
                    else
                    {
                        newMinute = pendingMinute;
                        newHour = orders.get(i).getOrdersHour();
                        check = ComparePendingTime(orders.get(i), newHour, newMinute);
                    }
                }
            }
        }*/
        return check;
    }
    
    //Check the order whether is "1"=complete then + 2 minute into pending time from order time
    public boolean CalPendingOrderTime(List<Orders> orders)
    {
        int newHour;
        int newMinute;
        int pendingMinute;
        int looping = 0;
        boolean check = false;
        /*
        for(int i = 0 ; i < orders.size() ; i++)
        {
            pendingMinute = orders.get(i).getOrdersMinute() + 2;
            
            if(orders.get(i).getOrderStatus().equals("1"))
            {
                if(pendingMinute >= 60)
                {
                    newMinute = pendingMinute - 60;
                    newHour = orders.get(i).getOrdersHour() + 1;
                    check = ComparePendingTime(orders.get(i), newHour, newMinute);
                    looping++;
                }
                else
                {
                    newMinute = pendingMinute;
                    newHour = orders.get(i).getOrdersHour();
                    check = ComparePendingTime(orders.get(i), newHour, newMinute);
                    looping++;
                }
            }
        }
        
        if(looping == 0)
        {
            check = false;
        }
        */
        return check;
    }
    
    //check order time atleast 2 minute before assign deliverymen
    public boolean ComparePendingTime(Orders orders, int newHour, int newMinute)
    {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR);
        int minute = cal.get(Calendar.MINUTE);
        boolean check = false;
        
        if(newHour == hour)
        {
            if(newMinute <= minute)
            {
                System.out.println(orders.getOrdersID());
                check = true;
            }
        }
        else if(newHour < hour)
        {
            System.out.println(orders.getOrdersID());
            check = true;
        }
        else
        {
            check = false;
        }
        return check;
    }
    
    public void AssignDeliveryMen(Orders orders)
    {
        Calendar cal = Calendar.getInstance();
        int loop = 1;
        boolean DMAvailableList = false;
        String DeliveryMenStatus = "";
        String DeliveryMenID;
        
        for(int i = 0 ; i < deliveryMen.size() ; i++)
        {
            if(deliveryMen.get(i).getCurrentAvailable().equals("Available"))
            {
                if(loop == 1)
                {
                    DeliveryMenStatus = "Deliver";
                    System.out.println("\nCurrent available Deliverymen.");
                    System.out.println("***********************************************************");
                    System.out.println("Staff ID\tStaff Name\tStatus");
                }
                
                DeliveryMenStatus = "Available";
                System.out.println(deliveryMen.get(i).getStaffID() + "\t" + deliveryMen.get(i).getStaffName() + "\t" + deliveryMen.get(i).getCurrentAvailable());
                
                if(loop == 1)
                {
                    System.out.println("***********************************************************");
                }
                
                loop++;
                DMAvailableList = true;
            }
        }
        
        if(!DMAvailableList)
        {
            loop = 1;
            
            for(int i = 0 ; i < deliveryMen.size() ; i++)
            {
                if(deliveryMen.get(i).getCurrentAvailable().equals("Deliver"))
                {
                    if(loop == 1)
                    {
                        DeliveryMenStatus = "Deliver";
                        System.out.println("\nNow do not have any available Deliverymen.");
                        System.out.println("***********************************************************");
                        System.out.println("Staff ID\tStaff Name\tStatus");
                    }

                    System.out.println(deliveryMen.get(i).getStaffID() + "\t" + deliveryMen.get(i).getStaffName() + "\t" + deliveryMen.get(i).getCurrentAvailable());

                    if(loop == 1)
                    {
                        System.out.println("***********************************************************");
                    }
                    loop++;
                    DMAvailableList = true;
                }
            }
        }
        
        //Do not have any Deliverymen
        if(!DMAvailableList)
        {
            System.out.println("Current all Deliverymen are not available...");
            //whileloop = -99;
        }
        
        //If have available or deliver status deliverymen
        while(DMAvailableList)
        {
            int deliverDistance;
            System.out.print("\nEnter Delierymen ID : ");
            DeliveryMenID = s.nextLine();

            for(int i = 0 ; i < deliveryMen.size() ; i++)
            {
                if(DeliveryMenStatus.equals("Available"))
                {
                    if(deliveryMen.get(i).getStaffID().equals(DeliveryMenID) && deliveryMen.get(i).getCurrentAvailable().equals("Available"))
                    {
                        do
                        {
                            System.out.print("Enter the distance of deliver (KM): ");
                            deliverDistance = s.nextInt();
                            
                            if(deliverDistance < 0)
                            {
                                System.out.println("\nDistance cannot less than 0");
                            }
                            
                        }while(deliverDistance < 0);
                        
                        int calDeliverTime = deliverDistance * 5; // 1km = 5minutes
                        
                        Calendar deliverTime = cal;
                        deliverTime.add(Calendar.MINUTE, calDeliverTime + 2);//add deliver time + add 2 minute(preparing) into curent time
                        int newNum = DSList.size();
                        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                        
                        //Change order Status
                        orders.setOrderStatus("3");//2 = Already assign deliverymen to order, 3 = Deliverymen in the progress of deliver
                        
                        //Add new Deliver Status
                        DSList.add(new DeliveryStatus(Calendar.getInstance(), Calendar.getInstance(), deliverTime, deliverTime, "Accepted By Delivery Man"));
                        DSList.get(newNum).setDM(deliveryMen.get(i));
                        DSList.get(newNum).setOrder(orders);
                        
                        //Set the deliverymen current status to deliver
                        deliveryMen.get(i).setCurrentAvailable("Deliver");
                        
                        System.out.print("\nAssign Successfully...");
                        System.out.println("The Deliverymen will deliver the order after 2 minutes\n");
                        
                        //Display====================================================
                        for(int k = 0 ; k < DSList.size() ; k++)
                        {
                            System.out.println("\nAssign Date :" + dateFormat.format(DSList.get(k).getAssignedDate().getTime()));
                            System.out.println("Assign Time :" + timeFormat.format(DSList.get(k).getAssignedTime().getTime()));
                            System.out.println("Expected Delivered Date :" + dateFormat.format(DSList.get(k).getDeliveredDate().getTime()));
                            System.out.println("Expected Delivered Time :" + timeFormat.format(DSList.get(k).getDeliveredTime().getTime()));
                            //System.out.println("ANS : " + Calendar.getInstance().compareTo(DSList.get(k).getDeliveredTime()));
                            System.out.print(DSList.get(k).getDeliveryStatus() + " ");
                            System.out.println(DSList.get(k).getDM().getStaffID());
                        }
                        //============================================================
                        
                        DMAvailableList = false;//Finish
                    }
                }
                else if(DeliveryMenStatus.equals("Deliver"))
                {
                    if(deliveryMen.get(i).getStaffID().equals(DeliveryMenID) && deliveryMen.get(i).getCurrentAvailable().equals("Deliver"))
                    {
                        do
                        {
                            System.out.print("Enter the distance of deliver (KM): ");
                            deliverDistance = s.nextInt();
                            
                            if(deliverDistance < 0)
                            {
                                System.out.println("\nDistance cannot less than 0");
                            }
                            
                        }while(deliverDistance < 0);
                        
                        int diffSeconds = 0;
                        int diffMinutes = 0;
                        int diffHours = 0;
                        int diffDays = 0;
                        int calDeliverTime = deliverDistance * 5; // 1km = 5minutes
                        
                        //Calculate between assign time and current time
                        for(int p = 0 ; p < DSList.size() ; p++)
                        {
                            try 
                            {
                                Date d1 = DSList.get(p).getAssignedTime().getTime();//assign time
                                Date d2 = Calendar.getInstance().getTime();//current time

                                //in milliseconds
                                int diff = (int)(d2.getTime() - d1.getTime());

                                diffSeconds = diff / 1000 % 60;
                                diffMinutes = diff / (60 * 1000) % 60;
                                diffHours = diff / (60 * 60 * 1000) % 24;
                                diffDays = diff / (24 * 60 * 60 * 1000);
                            }
                            catch (Exception e) 
                            {
                                e.printStackTrace();
                            }
                        }
                        
                        int difftSeconds = 0;
                        int difftMinutes = 0;
                        int difftHours = 0;
                        int difftDays = 0;
                        //Calculate next deliver time left
                        for(int p = 0 ; p < DSList.size() ; p++)
                        {
                            try 
                            {
                                Date d1 = DSList.get(p).getDeliveredTime().getTime();//deliver time
                                Date d2 = Calendar.getInstance().getTime();//current time

                                //in milliseconds
                                int difft = (int)(d1.getTime() - d2.getTime());

                                difftSeconds = difft / 1000 % 60;
                                difftMinutes = difft / (60 * 1000) % 60;
                                difftHours = difft / (60 * 60 * 1000) % 24;
                                difftDays = difft / (24 * 60 * 60 * 1000);
                            }
                            catch (Exception e) 
                            {
                                e.printStackTrace();
                            }
                        }
                        
                        int difftTime = difftHours * 60;
                        int diffTime = diffHours * 60;
                        Calendar deliverTime = cal;
                        int total = calDeliverTime + 2 + diffMinutes + diffTime + difftMinutes + difftTime;
                        deliverTime.add(Calendar.MINUTE, total);//add deliver time + add 2 minute(preparing) + Current deliver time into curent time
                        int newNum = DSList.size();
                        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                        
                        //Change order Status(no need change order status)
                        orders.setOrderStatus("2");//2 = Already assign deliverymen to order, 3 = Deliverymen in the progress of deliver
                        
                        //Add new Deliver Status
                        DeliveryStatus DS = new DeliveryStatus(Calendar.getInstance(), Calendar.getInstance(), deliverTime, deliverTime, "Accepted By Delivery Man");
                        DSList.add(DS);
                        DSList.get(newNum).setDM(deliveryMen.get(i));
                        DSList.get(newNum).setOrder(orders);
                        
                        //Set the deliverymen current status to deliver(no need change deliverymen status)
                        //deliveryMen.get(i).setCurrentAvailable("Deliver");
                        
                        int difSeconds = 0;
                        int difMinutes = 0;
                        int difHours = 0;
                        int difDays = 0;
                        //Calculate next deliver time left
                        for(int p = 0 ; p < DSList.size() ; p++)
                        {
                            try 
                            {
                                Date d1 = DSList.get(p).getDeliveredTime().getTime();//deliver time
                                Date d2 = Calendar.getInstance().getTime();//current time

                                //in milliseconds
                                int dif = (int)(d1.getTime() - d2.getTime());

                                difSeconds = dif / 1000 % 60;
                                difMinutes = dif / (60 * 1000) % 60;
                                difHours = dif / (60 * 60 * 1000) % 24;
                                difDays = dif / (24 * 60 * 60 * 1000);
                            }
                            catch (Exception e) 
                            {
                                e.printStackTrace();
                            }
                        }
                        
                        System.out.print("\nAssign Successfully...");
                        //System.out.println("The Deliverymen will deliver the order after " + difHours + " hours " + difMinutes + " minutes\n");
                        
                        //Display====================================================
                        for(int k = 0 ; k < DSList.size() ; k++)
                        {
                            System.out.println("\n" + (k+1) + " Order...");
                            System.out.println("Assign Date :" + dateFormat.format(DSList.get(k).getAssignedDate().getTime()));
                            System.out.println("Assign Time :" + timeFormat.format(DSList.get(k).getAssignedTime().getTime()));
                            System.out.println("Expected Delivered Date :" + dateFormat.format(DSList.get(k).getDeliveredDate().getTime()));
                            System.out.println("Expected Delivered Time :" + timeFormat.format(DSList.get(k).getDeliveredTime().getTime()));
                            //System.out.println("ANS : " + Calendar.getInstance().compareTo(DSList.get(k).getDeliveredTime()));
                            System.out.print(DSList.get(k).getDeliveryStatus() + " ");
                            System.out.println(DSList.get(k).getDM().getStaffID());
                        }
                        //============================================================
                        
                        DMAvailableList = false;//Finish
                    }
                }
                else
                {
                    DMAvailableList = true;
                }
            }
            
            if(DMAvailableList)
            {
                System.out.println("Please Enter Again....");
            }
        }
    }
    
    public void UpdateWorkStatus()
    {
        
    }
    
    //Let Customer to View Time Remain of Delivery Order
    public void ViewTimeRemainder(Customer cust, List<Orders> order)
    {
        System.out.println("Current Order");
        System.out.println("==============================");
        
        for(int i = 0 ; i < order.size() ; i++)
        {
            if(order.get(i).getCustomer().getCustName().equals(cust.getCustName()))
            {
                System.out.println("Order ID : " + order.get(i).getOrdersID());
                if(order.get(i).getOrderStatus().equals("1"))
                {
                    System.out.println("Waiting to assign the deliverymen");
                }
                else if(order.get(i).getOrderStatus().equals("2") || order.get(i).getOrderStatus().equals("3"))
                {
                    System.out.println("In process. Time Left : 10 minute ");
                    
                    for(int j = 0 ; j < DSList.size() ; j++)
                    {
                        System.out.println("\n2\n");
                        int difMinutes = 0;
                        int difHours = 0;
                        int diff = 0;
                        
                        if(DSList.get(j).getOrder().getOrdersID().equals(order.get(i).getOrdersID()))
                        {
                            try 
                            {
                                Date d1 = DSList.get(j).getDeliveredTime().getTime();//assign time
                                Date d2 = Calendar.getInstance().getTime();//current time

                                //in milliseconds
                                diff = (int)(d1.getTime() - d2.getTime());

                                difMinutes = diff / (60 * 1000) % 60;
                                difHours = diff / (60 * 60 * 1000) % 24;
                            }
                            catch (Exception e) 
                            {
                                e.printStackTrace();
                            }
                            if(diff<=0)
                            {
                                System.out.println("Delivered");
                            }
                            else
                            {
                                //System.out.println("In process. Time Left :" + difHours + " hours " + difMinutes + " minutes");
                            }
                        }
                        else
                        {
                            System.out.println("xx");
                        }
                    }
                }
                else
                {
                    System.out.println("Do not have any Deliver Order");
                }
            }
        }
        
        System.out.println("==============================");
    }
}