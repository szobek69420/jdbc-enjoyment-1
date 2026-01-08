package main.java.org.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainSceneController {
    private Stage stage;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;

    public void init(Stage stage)
    {
        this.stage=stage;

        loginButton.setOnMouseClicked(e->goToLoginScreen());
        registerButton.setOnMouseClicked(e->goToRegisterScreen());
    }

    private void goToLoginScreen()
    {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
            Parent root = loader.load();
            LoginSceneController controller=loader.getController();

            controller.init(stage);

            Scene scene = new Scene(root);
            stage.setScene(scene);
        }
        catch (Exception sex)
        {
            System.err.println(sex.getMessage());
        }
    }
    private void goToRegisterScreen()
    {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Register.fxml"));
            Parent root = loader.load();
            RegisterSceneController controller=loader.getController();

            controller.init(stage);

            Scene scene = new Scene(root);
            stage.setScene(scene);
        }
        catch (Exception sex)
        {
            System.err.println(sex.getMessage());
        }
    }
}
