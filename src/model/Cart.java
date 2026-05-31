package model;

import java.util.ArrayList;

public class Cart {

    private ArrayList<FoodItem> items =
            new ArrayList<>();

    public void addItem(FoodItem item) {

        for(FoodItem food : items) {

            if(food.getName()
                    .equals(item.getName())) {

                food.increaseQuantity();
                return;
            }
        }

        items.add(item);
    }

    public void removeItem(FoodItem item) {
        items.remove(item);
    }

    public ArrayList<FoodItem> getItems() {
        return items;
    }

    public double getTotalPrice() {

        double total = 0;

        for(FoodItem item : items) {

            total += item.getTotalPrice();
        }

        return total;
    }
}