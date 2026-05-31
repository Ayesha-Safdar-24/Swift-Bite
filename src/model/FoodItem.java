package model;

public class FoodItem {

    private String name;
    private double price;
    private int quantity;

    public FoodItem(String name,
                    double price) {

        this.name = name;
        this.price = price;
        this.quantity = 1;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity() {
        quantity++;
    }

    public void decreaseQuantity() {

        if(quantity > 1) {
            quantity--;
        }
    }

    public double getTotalPrice() {
        return quantity * price;
    }
}