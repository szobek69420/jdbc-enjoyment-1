package main.java.org.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class RegisterSceneController {
    private Stage stage;

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private RadioButton hasBMWRadioButton;

    @FXML
    private Button registerButton;
    @FXML
    private Button backButton;

    @FXML
    private Text errorMessageText;

    public void init(Stage stage)
    {
        this.stage=stage;

        registerButton.setOnMouseClicked(e->register());
        backButton.setOnMouseClicked(e->goBack());
    }

    private void register()
    {
        try{
            if(nameTextField.getText().trim().isEmpty())
                throw new RuntimeException("Name is blank");
            if(passwordTextField.getText().trim().isEmpty())
                throw new RuntimeException("Password is blank");

            try(Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "login_handler", "walter"))
            {
                PreparedStatement ps = con.prepareStatement("INSERT INTO users(name, password, has_bmw) VALUES (?,?,?);");
                ps.setString(1, nameTextField.getText().trim());
                ps.setString(2, passwordTextField.getText().trim());
                ps.setBoolean(3, hasBMWRadioButton.isArmed());

                if(ps.executeUpdate()!=1)
                    throw new RuntimeException("nigga");
            }
            catch(Exception sex)
            {
                throw new RuntimeException("Couldn't connect to the server nigga");
            }

            goBack();
        }
        catch(Exception sex)
        {
            errorMessageText.setText(sex.getMessage());
        }
    }

    private void goBack()
    {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/Main.fxml"));
            Parent root=loader.load();
            MainSceneController controller=loader.getController();

            controller.init(stage);

            Scene scene = new Scene(root);
            stage.setScene(scene);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
