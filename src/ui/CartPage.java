package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import model.CartManager;
import model.FoodItem;

public class CartPage {

    private BorderPane root;
    private VBox itemsBox;
    private Label totalLabel;

    public CartPage(Stage stage) {

        root = new BorderPane();

        // =========================
        // NAVBAR
        // =========================

        Label appTitle = new Label("🍔 SwiftBite");
        appTitle.getStyleClass().add("app-title");

        Label pageTitle = new Label("🛒 My Cart");
        pageTitle.getStyleClass().add("welcome-text");

        VBox titleBox = new VBox(5, appTitle, pageTitle);

        Button logoutBtn = new Button("Logout");
        logoutBtn.getStyleClass().add("logout-btn");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox navbar = new HBox(20, titleBox, spacer, logoutBtn);
        navbar.setPadding(new Insets(15));
        navbar.setAlignment(Pos.CENTER_LEFT);
        navbar.getStyleClass().add("navbar");

        root.setTop(navbar);

        // =========================
        // SIDEBAR
        // =========================

        Button dashboardBtn = new Button("🏠 Dashboard");
        Button menuBtn      = new Button("📋 Menu");
        Button cartBtn      = new Button("🛒 Cart");
        Button ordersBtn    = new Button("📦 Orders");
        Button profileBtn   = new Button("👤 Profile");

        Button[] buttons = {dashboardBtn, menuBtn, cartBtn, ordersBtn, profileBtn};
        for (Button b : buttons) {
            b.getStyleClass().add("sidebar-button");
        }

        VBox sidebar = new VBox(15, buttons);
        sidebar.setPadding(new Insets(20));
        sidebar.setAlignment(Pos.TOP_CENTER);
        sidebar.setPrefWidth(220);
        sidebar.getStyleClass().add("sidebar");

        root.setLeft(sidebar);

        // =========================
        // CART ITEMS
        // =========================

        Label heading = new Label("Your Cart Items");
        heading.getStyleClass().add("page-heading");

        itemsBox = new VBox(15);
        itemsBox.setAlignment(Pos.CENTER);
        itemsBox.setPadding(new Insets(10));

        totalLabel = new Label();
        totalLabel.setStyle(
                "-fx-font-size:22;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:#FF6B00;"
        );

        Button placeOrderBtn = new Button("✅ Place Order");
        placeOrderBtn.setPrefWidth(220);
        placeOrderBtn.setPrefHeight(42);
        placeOrderBtn.setStyle(
                "-fx-background-color:#28A745;" +
                "-fx-text-fill:white;" +
                "-fx-font-size:15;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:10;"
        );

        placeOrderBtn.setOnAction(e -> {
            stage.getScene().setRoot(new CheckoutPage(stage).getView());
        });

        VBox center = new VBox(20, heading, itemsBox, totalLabel, placeOrderBtn);
        center.setAlignment(Pos.TOP_CENTER);
        center.setPadding(new Insets(30));

        root.setCenter(center);

        // =========================
        // SIDEBAR ACTIONS
        // =========================

        dashboardBtn.setOnAction(e -> {
            stage.getScene().setRoot(new DashboardPage(stage).getView());
        });

        menuBtn.setOnAction(e -> {
            stage.getScene().setRoot(new MenuPage(stage).getView());
        });

        cartBtn.setOnAction(e -> {
            stage.getScene().setRoot(new CartPage(stage).getView());
        });

        ordersBtn.setOnAction(e -> {
            stage.getScene().setRoot(new OrderPage(stage).getView());
        });

        profileBtn.setOnAction(e -> {
            stage.getScene().setRoot(new ProfilePage(stage).getView());
        });

        logoutBtn.setOnAction(e -> {
            stage.getScene().setRoot(new LoginPage(stage).getView());
        });

        refreshCart();
    }

    private void refreshCart() {

        itemsBox.getChildren().clear();

        for (FoodItem item : CartManager.getCart().getItems()) {

            Label name = new Label(item.getName());
            name.setStyle("-fx-font-size:16;-fx-font-weight:bold;");

            Label qty = new Label("x " + item.getQuantity());
            qty.setStyle("-fx-font-size:15;");

            Label price = new Label("Rs. " + item.getTotalPrice());
            price.setStyle("-fx-font-size:15;-fx-text-fill:#ef4444;-fx-font-weight:bold;");

            Button plus = new Button("+");
            Button minus = new Button("-");
            Button remove = new Button("🗑 Remove");

            plus.setStyle(
                    "-fx-background-color:#f97316;" +
                    "-fx-text-fill:white;" +
                    "-fx-font-weight:bold;" +
                    "-fx-background-radius:8;"
            );

            minus.setStyle(
                    "-fx-background-color:#6C757D;" +
                    "-fx-text-fill:white;" +
                    "-fx-font-weight:bold;" +
                    "-fx-background-radius:8;"
            );

            remove.setStyle(
                    "-fx-background-color:#ef4444;" +
                    "-fx-text-fill:white;" +
                    "-fx-font-weight:bold;" +
                    "-fx-background-radius:8;"
            );

            plus.setOnAction(e -> { item.increaseQuantity(); refreshCart(); });
            minus.setOnAction(e -> { item.decreaseQuantity(); refreshCart(); });
            remove.setOnAction(e -> { CartManager.getCart().removeItem(item); refreshCart(); });

            HBox row = new HBox(15, name, qty, price, plus, minus, remove);
            row.setAlignment(Pos.CENTER);
            row.setPadding(new Insets(15));
            row.setStyle(
                    "-fx-background-color:white;" +
                    "-fx-background-radius:12;" +
                    "-fx-border-radius:12;" +
                    "-fx-border-color:#e5e7eb;" +
                    "-fx-effect:dropshadow(gaussian,rgba(0,0,0,0.08),8,0,0,2);"
            );

            itemsBox.getChildren().add(row);
        }

        if (CartManager.getCart().getItems().isEmpty()) {
            Label empty = new Label("🛒 Your cart is empty!");
            empty.setStyle("-fx-font-size:18;-fx-text-fill:#9ca3af;");
            itemsBox.getChildren().add(empty);
        }

        totalLabel.setText("Grand Total : Rs. " + CartManager.getCart().getTotalPrice());
    }

    public Parent getView() {
        return root;
    }
}