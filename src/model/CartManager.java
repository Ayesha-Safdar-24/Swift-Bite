package model;

public class CartManager {

    private static Cart cart =
            new Cart();

    public static Cart getCart() {
        return cart;
    }

    public static void clearCart() {

        cart.getItems().clear();
    }
}