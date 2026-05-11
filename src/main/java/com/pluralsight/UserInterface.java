package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    static Scanner theScanner = new Scanner(System.in);
    private Dealership dealership;

    public UserInterface(){

    }

    //starts the menu
    public void display(){
        //creates the dealership for this class
        this.init();

        boolean isRunning = true;
        while(isRunning){
            System.out.println("""
                    Please Select from the following options:
                    1 - Find vehicles within a price range
                    2 - Find vehicles by make / model
                    3 - Find vehicles by year range
                    4 - Find vehicles by color
                    5 - Find vehicles by mileage range
                    6 - Find vehicles by type (car, truck, SUV, van)
                    7 - List ALL vehicles
                    8 - Add a vehicle
                    9 - Remove a vehicle
                    99 - Quit
                    """);
            int userInput = theScanner.nextInt();
            switch (userInput){
                case 1:
                    processGetVehicleByPrice();
                    break;
                case 99:
                    System.out.println("Exiting...");
                    isRunning = false;
            }

        }


        //have your switch that calls the methods in the dealship to get the filterd lists


        //imagine they selected getAll
        this.processGetAllVehiclesRequest();
    }

    private void init(){
        DealershipFileManager fileManager = new DealershipFileManager();

        this.dealership = fileManager.getDealership();
    }

    public void processGetAllVehiclesRequest(){
        ArrayList<Vehicle> allTheCars = dealership.getAllVehicles();
        this.displayVehicles(allTheCars);
    }

    private static void displayVehicles(ArrayList<Vehicle> vehicles){
        for(Vehicle vehicle : vehicles){
            System.out.println(vehicle.toString());
        }
    }
    public void processGetVehicleByPrice(){
        //eating line because last scanner was an int
        theScanner.nextLine();
        //asking the user for the min and max to be able to sort through the inventory
        System.out.println("What is the price Maximum?");
        double priceMax = theScanner.nextDouble();
        System.out.println("What is the price Minimum?");
        double priceMin = theScanner.nextDouble();
        //grabbing the method from the dealerhsip class that has the actual logic to sort through the list based off of the userInput
        dealership.getVehiclesByPrice(priceMin, priceMax);
        //creating the new arrayList to populate so we can display the outputs
        ArrayList<Vehicle> carByPrice = dealership.getVehiclesByPrice(priceMin, priceMax);
        System.out.println(carByPrice + "\n");
    }


}
