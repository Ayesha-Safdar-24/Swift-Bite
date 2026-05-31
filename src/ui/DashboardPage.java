package ui;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import model.CartManager;
import model.FoodItem;
import model.SessionManager;

public class DashboardPage {

    private BorderPane root;

    public DashboardPage(Stage stage) {

        root = new BorderPane();

        // ======================
        // NAVBAR
        // ======================

        Label appTitle = new Label("🍔 SwiftBite");
        appTitle.getStyleClass().add("app-title");

        Label welcome = new Label("Welcome Back, " + SessionManager.getUsername() + "!");
        welcome.getStyleClass().add("welcome-text");

        VBox titleBox = new VBox(5, appTitle, welcome);

        Button logoutBtn = new Button("Logout");
        logoutBtn.getStyleClass().add("logout-btn");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox navbar = new HBox(20, titleBox, spacer, logoutBtn);
        navbar.setPadding(new Insets(15));
        navbar.setAlignment(Pos.CENTER_LEFT);
        navbar.getStyleClass().add("navbar");

        root.setTop(navbar);

        // ======================
        // SIDEBAR
        // ======================

        Button dashboardBtn = new Button("🏠 Dashboard");
        Button menuBtn = new Button("📋 Menu");
        Button cartBtn = new Button("🛒 Cart");
        Button ordersBtn = new Button("📦 Orders");
        Button profileBtn = new Button("👤 Profile");

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

        // ======================
        // CENTER
        // ======================

        Label heading = new Label("Featured Food");
        heading.getStyleClass().add("page-heading");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(25);
        grid.setVgap(25);

        grid.add(createFoodCard("🍔", "Zinger Burger", "Rs.450", 450, stage), 0, 0);
        grid.add(createFoodCard("🍕", "Chicken Pizza", "Rs.1200", 1200, stage), 1, 0);
        grid.add(createFoodCard("🍟", "French Fries", "Rs.250", 250, stage), 2, 0);
        grid.add(createFoodCard("🥤", "Coca Cola", "Rs.150", 150, stage), 0, 1);
        grid.add(createFoodCard("🍗", "Chicken Broast", "Rs.700", 700, stage), 1, 1);
        grid.add(createFoodCard("🌭", "Hot Dog", "Rs.350", 350, stage), 2, 1);

        VBox center = new VBox(25, heading, grid);
        center.setAlignment(Pos.TOP_CENTER);
        center.setPadding(new Insets(30));

        root.setCenter(center);

        // ======================
        // BUTTON ACTIONS
        // ======================

        logoutBtn.setOnAction(e -> {
            LoginPage page = new LoginPage(stage);
            stage.getScene().setRoot(page.getView());
        });

        // FIX: dashboardBtn now reloads dashboard
        dashboardBtn.setOnAction(e -> {
            DashboardPage page = new DashboardPage(stage);
            stage.getScene().setRoot(page.getView());
        });

        menuBtn.setOnAction(e -> {
            MenuPage page = new MenuPage(stage);
            stage.getScene().setRoot(page.getView());
        });

        cartBtn.setOnAction(e -> {
            CartPage page = new CartPage(stage);
            stage.getScene().setRoot(page.getView());
        });

        ordersBtn.setOnAction(e -> {
            OrderPage page = new OrderPage(stage);
            stage.getScene().setRoot(page.getView());
        });

        profileBtn.setOnAction(e -> {
            ProfilePage page = new ProfilePage(stage);
            stage.getScene().setRoot(page.getView());
        });
    }

    // FIX: added double price parameter so we can actually add item to cart
    private VBox createFoodCard(String emoji, String name, String priceLabel, double price, Stage stage) {

        Label icon = new Label(emoji);
        icon.setStyle("-fx-font-size:50;");

        Label foodName = new Label(name);
        foodName.getStyleClass().add("food-title");

        Label foodPrice = new Label(priceLabel);
        foodPrice.getStyleClass().add("food-price");

        Button addCart = new Button("Add To Cart");
        addCart.getStyleClass().add("add-cart-btn");

        // FIX: actually adds item to CartManager and shows confirmation
        addCart.setOnAction(e -> {
            CartManager.getCart().addItem(new FoodItem(name, price));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Added To Cart");
            alert.setContentText(name + " added successfully.");
            alert.showAndWait();
        });

        VBox card = new VBox(12, icon, foodName, foodPrice, addCart);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(20));
        card.setPrefSize(220, 240);
        card.getStyleClass().add("food-card");

        ScaleTransition hover = new ScaleTransition(Duration.millis(200), card);

        card.setOnMouseEntered(e -> {
            hover.setToX(1.05);
            hover.setToY(1.05);
            hover.play();
        });

        card.setOnMouseExited(e -> {
            hover.setToX(1);
            hover.setToY(1);
            hover.play();
        });

        return card;
    }

    public Parent getView() {
        return root;
    }
}