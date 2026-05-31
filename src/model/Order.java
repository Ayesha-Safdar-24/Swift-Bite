package model;

import java.util.ArrayList;

public class Order {

    private String username;
    private ArrayList<FoodItem> items;
    private double totalAmount;

    public Order(String username, ArrayList<FoodItem> items, double totalAmount) {
        this.username    = username;
        this.items       = new ArrayList<>(items);
        this.totalAmount = totalAmount;
    }

    public String getUsername()            
    { 
    	return username;  
    	}
    public ArrayList<FoodItem> getItems() 
    { 
    	return items;      
    	}
    public double getTotalAmount()         
    { 
    	return totalAmount; 
    	}
}