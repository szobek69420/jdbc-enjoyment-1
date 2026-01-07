package main.java.org;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.org.controller.MainSceneController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends Application {
    private static ArrayList<Connection> unsafeConnections=new ArrayList<>();
    public static Connection clientConnection=null;

    public static void main(String[] args) {
        try{
            launch(args);
        }
        catch(Exception ex)
        {
            for(Connection c : unsafeConnections)
            {
                try{
                    c.close();
                }
                catch (Exception sex)
                {}
            }
        }
    }

    public static void registerUnsafeConnection(Connection c)
    {
        if(!unsafeConnections.contains(c))
            unsafeConnections.add(c);
    }

    public static void unregisterUnsafeConnection(Connection c)
    {
        unsafeConnections.remove(c);
        if(c==clientConnection)
            clientConnection=null;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene;

        FXMLLoader loader= new FXMLLoader(getClass().getResource("/Main.fxml"));
        AnchorPane root=loader.load();
        MainSceneController controller=loader.getController();

        scene=new Scene(root);

        controller.init(primaryStage);

        primaryStage.setTitle("amogus");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}