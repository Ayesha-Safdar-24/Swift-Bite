package ui;

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.UserDatabase;

public class LoginPage {

    private Parent root;

    public LoginPage(Stage stage) {

        // =========================
        // LEFT PANEL
        // =========================

        Label logo = new Label("🍔 SwiftBite");

        logo.setStyle(
                "-fx-font-size:42;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:white;"
        );

        Label subtitle = new Label(
                "Fast Food Delivery System"
        );

        subtitle.setStyle(
                "-fx-font-size:22;" +
                "-fx-text-fill:white;"
        );

        Label features = new Label(
                "✔ Fresh Food\n\n" +
                "✔ Fast Delivery\n\n" +
                "✔ Online Ordering\n\n" +
                "✔ Secure Payment\n\n" +
                "✔ Order Tracking\n\n" +
                "✔ Best Quality Service"
        );

        features.setStyle(
                "-fx-font-size:20;" +
                "-fx-text-fill:white;"
        );

        VBox leftPanel = new VBox(
                30,
                logo,
                subtitle,
                features
        );

        leftPanel.setAlignment(Pos.CENTER_LEFT);
        leftPanel.setPadding(new Insets(60));

        leftPanel.setStyle(
                "-fx-background-color: linear-gradient(to bottom,#041C4A,#062A6B);"
        );

        // =========================
        // RIGHT PANEL
        // =========================

        Label welcome = new Label("Welcome Back");

        welcome.setStyle(
                "-fx-font-size:34;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:#1F2937;"
        );

        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter Username");
        usernameField.setPrefWidth(320);
        usernameField.setPrefHeight(45);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");
        passwordField.setPrefWidth(320);
        passwordField.setPrefHeight(45);

        Button loginBtn = new Button("Login");

        loginBtn.setPrefWidth(320);
        loginBtn.setPrefHeight(45);

        loginBtn.setStyle(
                "-fx-background-color:#FF6B00;" +
                "-fx-text-fill:white;" +
                "-fx-font-size:16;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:10;"
        );

        Button signupBtn = new Button("Create Account");

        signupBtn.setPrefWidth(320);
        signupBtn.setPrefHeight(45);

        signupBtn.setStyle(
                "-fx-background-color:#E5E7EB;" +
                "-fx-font-size:15;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:10;"
        );

        VBox loginCard = new VBox(
                20,
                welcome,
                usernameField,
                passwordField,
                loginBtn,
                signupBtn
        );

        loginCard.setAlignment(Pos.CENTER);

        loginCard.setPadding(new Insets(40));

        loginCard.setMaxWidth(450);

        loginCard.setStyle(
                "-fx-background-color:white;" +
                "-fx-background-radius:20;" +
                "-fx-border-radius:20;" +
                "-fx-effect:dropshadow(gaussian,rgba(0,0,0,0.20),20,0,0,5);"
        );

        VBox rightPanel = new VBox(loginCard);

        rightPanel.setAlignment(Pos.CENTER);

        rightPanel.setStyle(
                "-fx-background-color: linear-gradient(to bottom,#F8FAFC,#E5E7EB);"
        );

        // =========================
        // MAIN LAYOUT
        // =========================

        HBox layout = new HBox();

        HBox.setHgrow(leftPanel, Priority.ALWAYS);
        HBox.setHgrow(rightPanel, Priority.ALWAYS);

        leftPanel.setMaxWidth(Double.MAX_VALUE);
        rightPanel.setMaxWidth(Double.MAX_VALUE);

        layout.getChildren().addAll(
                leftPanel,
                rightPanel
        );

        root = layout;

        // =========================
        // LOGIN BUTTON
        // =========================

        loginBtn.setOnAction(e -> {

            String username =
                    usernameField.getText();

            String password =
                    passwordField.getText();

            if (UserDatabase.validateLogin(
                    username,
                    password
            )) {

                DashboardPage dashboard =
                        new DashboardPage(stage);

                stage.getScene().setRoot(
                        dashboard.getView()
                );

            } else {

                Alert alert =
                        new Alert(
                                Alert.AlertType.ERROR
                        );

                alert.setHeaderText(
                        "Login Failed"
                );

                alert.setContentText(
                        "Invalid Username or Password"
                );

                alert.showAndWait();
            }
        });

        // =========================
        // SIGNUP BUTTON
        // =========================

        signupBtn.setOnAction(e -> {

            SignupPage signupPage =
                    new SignupPage(stage);

            stage.getScene().setRoot(
                    signupPage.getView()
            );
        });

        // =========================
        // ANIMATION
        // =========================

        FadeTransition fade =
                new FadeTransition(
                        Duration.seconds(1.2),
                        layout
                );

        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    public Parent getView() {
        return root;
    }
}