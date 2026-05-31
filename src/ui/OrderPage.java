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

public class OrderPage {

    private BorderPane root;

    public OrderPage(Stage stage) {

        root = new BorderPane();

        // =========================
        // NAVBAR
        // =========================

        Label appTitle = new Label("🍔 SwiftBite");
        appTitle.getStyleClass().add("app-title");

        Label pageTitle = new Label("📦 My Orders");
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
        // ORDER HISTORY
        // =========================

        Label heading = new Label("📦 Order History");
        heading.getStyleClass().add("page-heading");

        VBox ordersBox = new VBox(20);
        ordersBox.setAlignment(Pos.TOP_CENTER);
        ordersBox.setPadding(new Insets(10));

        // get all orders for logged in user
        java.util.ArrayList<Order> userOrders =
                OrderManager.getOrdersForUser(SessionManager.getUsername());

        if (userOrders.isEmpty()) {

            Label noOrders = new Label("😕 No orders placed yet!");
            noOrders.setStyle(
                    "-fx-font-size:20;" +
                    "-fx-text-fill:#9ca3af;" +
                    "-fx-font-weight:bold;"
            );
            ordersBox.getChildren().add(noOrders);

        } else {

            int orderNumber = 1;

            for (Order order : userOrders) {

                // order number header
                Label orderTitle = new Label("Order #" + orderNumber);
                orderTitle.setStyle(
                        "-fx-font-size:16;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:#FF6B00;"
                );

                // items inside this order
                VBox itemsBox = new VBox(8);

                for (FoodItem item : order.getItems()) {

                    Label itemLabel = new Label(
                            "• " + item.getName() +
                            "  x" + item.getQuantity() +
                            "  =  Rs. " + item.getTotalPrice()
                    );
                    itemLabel.setStyle(
                            "-fx-font-size:15;" +
                            "-fx-text-fill:#1f2937;"
                    );
                    itemsBox.getChildren().add(itemLabel);
                }

                // total for this order
                Label orderTotal = new Label(
                        "Total Paid : Rs. " + order.getTotalAmount()
                );
                orderTotal.setStyle(
                        "-fx-font-size:16;" +
                        "-fx-font-weight:bold;" +
                        "-fx-text-fill:#28A745;"
                );

                // card for each order
                VBox orderCard = new VBox(10, orderTitle, itemsBox, orderTotal);
                orderCard.setPadding(new Insets(20));
                orderCard.setStyle(
                        "-fx-background-color:white;" +
                        "-fx-background-radius:12;" +
                        "-fx-border-radius:12;" +
                        "-fx-border-color:#e5e7eb;" +
                        "-fx-effect:dropshadow(gaussian,rgba(0,0,0,0.08),8,0,0,2);"
                );
                orderCard.setMaxWidth(700);

                ordersBox.getChildren().add(orderCard);
                orderNumber++;
            }
        }

        VBox center = new VBox(25, heading, ordersBox);
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
            SessionManager.logout();
            stage.getScene().setRoot(new LoginPage(stage).getView());
        });
    }

    public Parent getView() {
        return root;
    }
}