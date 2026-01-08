package main.java.org.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginSceneController {
    private Stage stage;

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button loginButton;
    @FXML
    private Button backButton;

    @FXML
    private Text errorMessageText;

    public void init(Stage stage)
    {
        this.stage=stage;

        loginButton.setOnMouseClicked(e->login());
    }

    private void login()
    {
        try{
            if(nameTextField.getText().isBlank())
                throw new Exception("Invalid name");
            if(passwordTextField.getText().isBlank())
                throw new Exception("Invalid password");

            String password=null;

            try(Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "login_handler", "walter"))
            {
                PreparedStatement ps2=c.prepareStatement("SELECT password FROM users WHERE users.name=?;");
                ps2.setString(1, nameTextField.getText().trim());

                ResultSet rs6=ps2.executeQuery();
                rs6.next();
                if(rs6.isLast())
                    password=rs6.getString(1);
            }
            catch(Exception ex)
            {
                throw new Exception("Could not connect to the server nigga");
            }

            if(password==null||!password.equals(passwordTextField.getText().trim()))
                throw new Exception("Invalid password");

            System.out.println("Successful login");
        }
        catch(Exception ex){
            errorMessageText.setText(ex.getMessage());
        }
    }
}
