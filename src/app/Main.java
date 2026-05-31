package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.LoginPage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        LoginPage loginPage = new LoginPage(stage);

        Scene scene = new Scene(
                loginPage.getView()
        );

        // load CSS
        scene.getStylesheets().add(
                getClass().getResource("/css/style.css").toExternalForm()
        );

        stage.setTitle("🍔 SwiftBite - Food Delivery");
        stage.setScene(scene);

        //  open in full maximized window
        stage.setMaximized(true);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}