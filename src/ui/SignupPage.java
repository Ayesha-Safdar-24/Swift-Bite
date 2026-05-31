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
import model.User;
import model.UserDatabase;

public class SignupPage {

    private Parent root;

    public SignupPage(Stage stage) {

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
                "Join SwiftBite Today"
        );

        subtitle.setStyle(
                "-fx-font-size:22;" +
                "-fx-text-fill:white;"
        );

        Label features = new Label(
                "✔ Order Food Online\n\n" +
                "✔ Fast Delivery\n\n" +
                "✔ Secure Login\n\n" +
                "✔ Track Orders\n\n" +
                "✔ Easy Cart System\n\n" +
                "✔ Quick Checkout"
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

        Label signupTitle =
                new Label("Create Account");

        signupTitle.setStyle(
                "-fx-font-size:34;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:#1F2937;"
        );

        TextField usernameField =
                new TextField();

        usernameField.setPromptText(
                "Username"
        );

        usernameField.setPrefWidth(320);
        usernameField.setPrefHeight(45);

        TextField phoneField =
                new TextField();

        phoneField.setPromptText(
                "Phone Number"
        );

        phoneField.setPrefWidth(320);
        phoneField.setPrefHeight(45);

        PasswordField passwordField =
                new PasswordField();

        passwordField.setPromptText(
                "Password"
        );

        passwordField.setPrefWidth(320);
        passwordField.setPrefHeight(45);

        Button createBtn =
                new Button("Create Account");

        createBtn.setPrefWidth(320);
        createBtn.setPrefHeight(45);

        createBtn.setStyle(
                "-fx-background-color:#FF6B00;" +
                "-fx-text-fill:white;" +
                "-fx-font-size:16;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:10;"
        );

        Button backBtn =
                new Button("Back To Login");

        backBtn.setPrefWidth(320);
        backBtn.setPrefHeight(45);

        backBtn.setStyle(
                "-fx-background-color:#E5E7EB;" +
                "-fx-font-size:15;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:10;"
        );

        VBox signupCard =
                new VBox(
                        20,
                        signupTitle,
                        usernameField,
                        phoneField,
                        passwordField,
                        createBtn,
                        backBtn
                );

        signupCard.setAlignment(Pos.CENTER);

        signupCard.setPadding(
                new Insets(40)
        );

        signupCard.setMaxWidth(450);

        signupCard.setStyle(
                "-fx-background-color:white;" +
                "-fx-background-radius:20;" +
                "-fx-border-radius:20;" +
                "-fx-effect:dropshadow(gaussian,rgba(0,0,0,0.20),20,0,0,5);"
        );

        VBox rightPanel =
                new VBox(signupCard);

        rightPanel.setAlignment(
                Pos.CENTER
        );

        rightPanel.setStyle(
                "-fx-background-color: linear-gradient(to bottom,#F8FAFC,#E5E7EB);"
        );

        // =========================
        // MAIN LAYOUT
        // =========================

        HBox layout =
                new HBox();

        HBox.setHgrow(
                leftPanel,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                rightPanel,
                Priority.ALWAYS
        );

        leftPanel.setMaxWidth(
                Double.MAX_VALUE
        );

        rightPanel.setMaxWidth(
                Double.MAX_VALUE
        );

        layout.getChildren().addAll(
                leftPanel,
                rightPanel
        );

        root = layout;

        // =========================
        // CREATE ACCOUNT
        // =========================

        createBtn.setOnAction(e -> {

            String username =
                    usernameField.getText();

            String phone =
                    phoneField.getText();

            String password =
                    passwordField.getText();

            if(username.isEmpty()
                    || phone.isEmpty()
                    || password.isEmpty()) {

                Alert alert =
                        new Alert(
                                Alert.AlertType.ERROR
                        );

                alert.setHeaderText(
                        "Missing Data"
                );

                alert.setContentText(
                        "Please fill all fields."
                );

                alert.showAndWait();

                return;
            }

            User user =
                    new User(
                            username,
                            phone,
                            password
                    );

            UserDatabase.addUser(user);

            Alert alert =
                    new Alert(
                            Alert.AlertType.INFORMATION
                    );

            alert.setHeaderText(
                    "Success"
            );

            alert.setContentText(
                    "Account Created Successfully!"
            );

            alert.showAndWait();

            LoginPage loginPage =
                    new LoginPage(stage);

            stage.getScene().setRoot(
                    loginPage.getView()
            );
        });

        // =========================
        // BACK BUTTON
        // =========================

        backBtn.setOnAction(e -> {

            LoginPage loginPage =
                    new LoginPage(stage);

            stage.getScene().setRoot(
                    loginPage.getView()
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