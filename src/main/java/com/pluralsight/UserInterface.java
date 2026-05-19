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
                case 2:
                    processGetVehicleByModel();
                    break;
                case 3:
                    processGetVehicleByYear();
                    break;
                case 4:
                    processGetVehicleByColor();
                    break;
                case 5:
                    processGetVehicleByMileage();
                    break;
                case 6:
                    processGetVehicleByType();
                    break;
                case 7:
                    processGetAllVehiclesRequest();
                    break;
                case 8:
                    addVehicle();
                    break;
                case 9:
                    processRemoveVehicle();
                    break;
                case 99:
                    System.out.println("Exiting...");
                    isRunning = false;
                    break;
            }

        }

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
    public void processGetVehicleByModel(){
        //eating scanner line since last scanner input was a double
        theScanner.nextLine();
        //asking the user what make and model theyre looking for then storing it in its own variable
        //to be able to pass to the method that filters through the cars
        System.out.println("What Make are you looking for?");
        String userMake = theScanner.nextLine();;
        System.out.println("Model?");
        String userModel = theScanner.nextLine();
        //grabbing the method that loops and give it its parameters to be able to have a
        //search paramater
        dealership.getVehicleByModel(userMake, userModel);
        ArrayList<Vehicle> carByMandM = dealership.getVehicleByModel(userMake, userModel);
        System.out.println(carByMandM);
    }

    public void processGetVehicleByColor(){
        theScanner.nextLine();
        System.out.println("What color are you looking for?");
        String userColor = theScanner.nextLine();
        dealership.getVehicleByColor(userColor);
        ArrayList<Vehicle> carByColor = dealership.getVehicleByColor(userColor);
        System.out.println(carByColor);
    }

    public void processGetVehicleByYear(){
        System.out.println("What is the minimum year?");
        int minYear = theScanner.nextInt();
        System.out.println("What is the max year?");
        int maxYear = theScanner.nextInt();
        dealership.getVehicleByYear(minYear,maxYear);
        ArrayList<Vehicle> carByYear = dealership.getVehicleByYear(minYear, maxYear);
        System.out.println(carByYear);
    }

    public void processGetVehicleByMileage(){
        System.out.println("What is the maximum mileage?");
        int maxMiles = theScanner.nextInt();
        theScanner.nextLine();
        System.out.println("Would you like to do a minimum mileage?(Y/N)");
        String userInput = theScanner.nextLine();
        if(userInput.equalsIgnoreCase("y")){
            System.out.println("What is the minimum mileage?");
            int minMiles = theScanner.nextInt();
            dealership.getVehicleByMileage(minMiles, maxMiles);
            ArrayList<Vehicle> carByMileage = dealership.getVehicleByMileage(minMiles, maxMiles);
            System.out.println(carByMileage);
        }else{
            dealership.getVehicleByMileage(maxMiles);
            ArrayList<Vehicle> carByMileage = dealership.getVehicleByMileage(maxMiles);
            System.out.println(carByMileage);
        }



    }

    public void processGetVehicleByType(){
        theScanner.nextLine();
        System.out.println("""
                What kind of vehicle are you looking for?:
                (Coupe, Sedan, SUV, Truck, Van)
                """);
        String userType = theScanner.nextLine();
        dealership.getVehicleByType(userType);
        ArrayList<Vehicle> carByType = dealership.getVehicleByType(userType);
        System.out.println(carByType);

    }

    public void addVehicle(){
        System.out.println("What is the Vin of your car?");
        int userVin = theScanner.nextInt();
        System.out.println("What is the year of your car?");
        int userYear = theScanner.nextInt();
        theScanner.nextLine();
        System.out.println("What is the make of your car?");
        String userMake = theScanner.nextLine();
        System.out.println("What is the model of your car?");
        String userModel = theScanner.nextLine();
        System.out.println("What is the type of vehicle?(Sedan,SUV,etc.");
        String userType = theScanner.nextLine();
        System.out.println("What is the color of your car?");
        String userColor = theScanner.nextLine();
        System.out.println("What is the mileage on the odometer?");
        int userMiles = theScanner.nextInt();
        System.out.println("What is the price of the vehicle?");
        double userPrice = theScanner.nextDouble();
        Vehicle newVehicle = new Vehicle(userVin, userYear, userMake, userModel, userType, userColor,userMiles, userPrice);
        dealership.addVehicle(newVehicle);

    }

    public void processRemoveVehicle(){
        System.out.println("What is the Vin number of the vehicle you want to remove?");
        int userVin = theScanner.nextInt();
        Vehicle removeVehicle = null;
        for(Vehicle vehicle : dealership.getAllVehicles()){
            if(vehicle.getVin() == userVin){
                removeVehicle = vehicle;
                break;
            }
        }

        if(removeVehicle != null){
            dealership.removeVehicle(removeVehicle);
        }

    }


}
