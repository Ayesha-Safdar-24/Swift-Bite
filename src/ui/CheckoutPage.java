package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import model.CartManager;
import model.FoodItem;
import model.Order;
import model.OrderManager;
import model.SessionManager;

public class CheckoutPage {

    private BorderPane root;

    public CheckoutPage(Stage stage) {

        root = new BorderPane();

        // NAVBAR
        Label appTitle = new Label("🍔 SwiftBite");
        appTitle.getStyleClass().add("app-title");

        Label pageTitle = new Label("✅ Checkout");
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

        // SIDEBAR
        Button dashboardBtn = new Button("🏠 Dashboard");
        Button menuBtn      = new Button("📋 Menu");
        Button cartBtn      = new Button("🛒 Cart");
        Button ordersBtn    = new Button("📦 Orders");
        Button profileBtn   = new Button("👤 Profile");

        Button[] buttons = {dashboardBtn, menuBtn, cartBtn, ordersBtn, profileBtn};
        for (Button b : buttons) b.getStyleClass().add("sidebar-button");

        VBox sidebar = new VBox(15, buttons);
        sidebar.setPadding(new Insets(20));
        sidebar.setAlignment(Pos.TOP_CENTER);
        sidebar.setPrefWidth(220);
        sidebar.getStyleClass().add("sidebar");

        root.setLeft(sidebar);

        // ORDER SUMMARY
        Label heading = new Label("Order Summary");
        heading.getStyleClass().add("page-heading");

        VBox itemsBox = new VBox(12);
        itemsBox.setAlignment(Pos.CENTER);

        double total = 0;

        for (FoodItem item : CartManager.getCart().getItems()) {

            Label itemLabel = new Label(
                    "• " + item.getName() +
                    "  x" + item.getQuantity() +
                    "  =  Rs. " + item.getTotalPrice()
            );
            itemLabel.setStyle(
                    "-fx-font-size:16;" +
                    "-fx-font-weight:bold;" +
                    "-fx-text-fill:#1f2937;"
            );

            HBox row = new HBox(itemLabel);
            row.setPadding(new Insets(12, 20, 12, 20));
            row.setStyle(
                    "-fx-background-color:white;" +
                    "-fx-background-radius:10;" +
                    "-fx-border-color:#e5e7eb;" +
                    "-fx-border-radius:10;"
            );
            row.setMaxWidth(600);

            itemsBox.getChildren().add(row);
            total += item.getTotalPrice();
        }

        Label totalLabel = new Label("Grand Total : Rs. " + total);
        totalLabel.setStyle(
                "-fx-font-size:22;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:#FF6B00;"
        );

        Button confirmBtn = new Button("✅ Confirm Order");
        confirmBtn.setPrefWidth(220);
        confirmBtn.setPrefHeight(42);
        confirmBtn.setStyle(
                "-fx-background-color:#28A745;" +
                "-fx-text-fill:white;" +
                "-fx-font-size:15;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:10;"
        );

        Button backBtn = new Button("⬅ Back To Cart");
        backBtn.setPrefWidth(220);
        backBtn.setPrefHeight(42);
        backBtn.setStyle(
                "-fx-background-color:#6C757D;" +
                "-fx-text-fill:white;" +
                "-fx-font-size:15;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:10;"
        );

        HBox btnBox = new HBox(20, confirmBtn, backBtn);
        btnBox.setAlignment(Pos.CENTER);

        VBox center = new VBox(25, heading, itemsBox, totalLabel, btnBox);
        center.setAlignment(Pos.TOP_CENTER);
        center.setPadding(new Insets(30));

        root.setCenter(center);

        // CONFIRM — saves order
        confirmBtn.setOnAction(e -> {

            Order order = new Order(
                    SessionManager.getUsername(),
                    CartManager.getCart().getItems(),
                    CartManager.getCart().getTotalPrice()
            );

            OrderManager.placeOrder(order);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Order Confirmed ✅");
            alert.setContentText(
                    "Your order has been placed!\n" +
                    "Total Paid: Rs. " + CartManager.getCart().getTotalPrice()
            );
            alert.showAndWait();

            CartManager.clearCart();
            stage.getScene().setRoot(new DashboardPage(stage).getView());
        });

        backBtn.setOnAction(e -> {
            stage.getScene().setRoot(new CartPage(stage).getView());
        });

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
            SessionManager.logout();
            stage.getScene().setRoot(new LoginPage(stage).getView());
        });
    }

    public Parent getView() {
        return root;
    }
}