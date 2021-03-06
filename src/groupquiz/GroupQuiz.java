/*
 * This program will create the home login screen for both teacher and instructor
 */
package groupquiz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author sharanya
 */
public class GroupQuiz extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));// creates the home screen
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main method will access the other method and runs the program
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
