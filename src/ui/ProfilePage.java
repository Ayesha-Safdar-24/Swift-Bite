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
import model.SessionManager;

public class ProfilePage {

    private BorderPane root;

    public ProfilePage(Stage stage) {

        root = new BorderPane();

        // =========================
        // NAVBAR
        // =========================

        Label appTitle = new Label("🍔 SwiftBite");
        appTitle.getStyleClass().add("app-title");

        Label pageTitle = new Label("👤 My Profile");
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
        // PROFILE CONTENT
        // =========================

        Label heading = new Label("Account Details");
        heading.getStyleClass().add("page-heading");

        String name  = SessionManager.getUsername();
        String phone = SessionManager.getPhone();

        int totalItems = 0;
        for (FoodItem item : CartManager.getCart().getItems()) {
            totalItems += item.getQuantity();
        }
        double totalBill = CartManager.getCart().getTotalPrice();

        Label usernameLabel = makeInfoRow("👤  Username", name.isEmpty() ? "Unknown" : name);
        Label phoneLabel    = makeInfoRow("📞  Phone",    phone.isEmpty() ? "Not Available" : phone);
        Label itemsLabel    = makeInfoRow("🛒  Items In Cart", String.valueOf(totalItems));
        Label billLabel     = makeInfoRow("💰  Current Bill", "Rs. " + totalBill);

        VBox infoBox = new VBox(20, usernameLabel, phoneLabel, itemsLabel, billLabel);
        infoBox.setAlignment(Pos.CENTER_LEFT);
        infoBox.setPadding(new Insets(30));
        infoBox.setMaxWidth(500);
        infoBox.setStyle(
                "-fx-background-color:white;" +
                "-fx-background-radius:15;" +
                "-fx-border-radius:15;" +
                "-fx-border-color:#e5e7eb;" +
                "-fx-effect:dropshadow(gaussian,rgba(0,0,0,0.1),12,0,0,4);"
        );

        VBox center = new VBox(25, heading, infoBox);
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
    }

    private Label makeInfoRow(String fieldName, String value) {
        Label lbl = new Label(fieldName + " :   " + value);
        lbl.setStyle(
                "-fx-font-size:17;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:#1f2937;"
        );
        return lbl;
    }

    public Parent getView() {
        return root;
    }
}