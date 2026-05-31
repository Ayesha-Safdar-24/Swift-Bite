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

public class MenuPage {

    private BorderPane root;

    public MenuPage(Stage stage) {

        root = new BorderPane();

        // =========================
        // NAVBAR
        // =========================

        Label appTitle = new Label("🍔 SwiftBite");
        appTitle.getStyleClass().add("app-title");

        Label pageTitle = new Label("📋 Food Menu");
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
        // MENU GRID
        // =========================

        Label heading = new Label("Our Menu");
        heading.getStyleClass().add("page-heading");

        GridPane menuGrid = new GridPane();
        menuGrid.setAlignment(Pos.CENTER);
        menuGrid.setHgap(25);
        menuGrid.setVgap(25);

        menuGrid.add(createItem("🍔 Zinger Burger", 450),  0, 0);
        menuGrid.add(createItem("🍕 Chicken Pizza", 1200), 1, 0);
        menuGrid.add(createItem("🍟 Fries",          250), 2, 0);
        menuGrid.add(createItem("🥤 Coke",           150), 0, 1);
        menuGrid.add(createItem("🌭 Hot Dog",         350), 1, 1);
        menuGrid.add(createItem("🍗 Broast",          700), 2, 1);

        VBox center = new VBox(25, heading, menuGrid);
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

    private VBox createItem(String name, double price) {

        Label foodName = new Label(name);
        foodName.getStyleClass().add("food-title");

        Label foodPrice = new Label("Rs. " + (int) price);
        foodPrice.getStyleClass().add("food-price");

        Button addBtn = new Button("Add To Cart");
        addBtn.getStyleClass().add("add-cart-btn");

        addBtn.setOnAction(e -> {
            CartManager.getCart().addItem(new FoodItem(name, price));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Added To Cart");
            alert.setContentText(name + " added successfully.");
            alert.showAndWait();
        });

        VBox card = new VBox(12, foodName, foodPrice, addBtn);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(20));
        card.setPrefSize(250, 170);
        card.getStyleClass().add("food-card");

        return card;
    }

    public Parent getView() {
        return root;
    }
}