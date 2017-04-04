/*
 *This class generates the report for the student
 */
package groupquiz;

import static groupquiz.testSelectionController.correct_answers;
import static groupquiz.testSelectionController.q_no;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rituparnaj
 */
public class FinishTestController implements Initializable {

    //String grade;
    @FXML
    private Label testover;
    @FXML
    private Button viewreport;
    @FXML
    private Button logoff;
    @FXML
    private Label score;

    /**
     * Initializes the controller class.
     *
     * @param url-initializes using a particular url
     * @param rb-initializes using a particular resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        score.setText("Your Score is: " + correct_answers);

        // TODO
    }

    /**
     * This is an event which creates the scene builder for the report
     *
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    public void reportCreation(MouseEvent event) throws IOException, SQLException {

        Parent root = FXMLLoader.load(getClass().getResource("reportCreation.fxml"));
        root = FXMLLoader.load(getClass().getResource("reportCreation.fxml"));//report is generated
        Stage stage = (Stage) viewreport.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
