package com.pluralsight;

import java.util.ArrayList;

public class Dealership {

    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;

        this.inventory = new ArrayList<Vehicle>();
    }

    //methods for the most part will get called from the UI

    //filter inventory list by price and return that list
    public ArrayList<Vehicle> getVehiclesByPrice(double priceMin, double priceMax){
        ArrayList<Vehicle> carArray = new ArrayList<>();
        for(Vehicle vehicle : this.inventory){
            if(priceMin <= vehicle.getPrice() && priceMax >= vehicle.getPrice()){
                carArray.add(vehicle);
            }
        }
        return carArray;
    }

    //return the entire list
    public ArrayList<Vehicle> getAllVehicles(){
        return this.inventory;
    }

    //add veichle created in the UI to the list
    public void addVehicle(Vehicle vehicle){
        this.inventory.add(vehicle);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<Vehicle> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Vehicle> inventory) {
        this.inventory = inventory;
    }
}