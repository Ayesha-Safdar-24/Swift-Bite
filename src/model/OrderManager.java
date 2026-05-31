package model;

import java.util.ArrayList;

public class OrderManager {

    private static ArrayList<Order> orders = new ArrayList<>();

    public static void placeOrder(Order order) {
        orders.add(order);
    }

    public static ArrayList<Order> getOrdersForUser(String username) {

        ArrayList<Order> userOrders = new ArrayList<>();

        for (Order order : orders) {
            if (order.getUsername().equals(username)) {
                userOrders.add(order);
            }
        }

        return userOrders;
    }

    public static ArrayList<Order> getAllOrders() {
        return orders;
    }
}