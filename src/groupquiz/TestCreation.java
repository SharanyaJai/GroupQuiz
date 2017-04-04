/*
 *This program is for selecting the quiz
 */
package groupquiz;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author rituparnaj
 */
public class TestCreation extends Application 
{
    public static void main(String[] args) throws FileNotFoundException, SQLException 
    {
        
        launch(args); //launches javafx 
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception 
    {
   
            Parent root = FXMLLoader.load(getClass().getResource("testSelection.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Quiz");
            primaryStage.show();
    }

}
