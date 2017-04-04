/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author sharanya
 */
public class HomeScreenController implements Initializable {

    @FXML
    private Button teacherlogin;
    @FXML
    private Button stlogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void TeacherScreen(MouseEvent event) {
    }

    @FXML
    private void StudentScreen(MouseEvent event) {
    }
    
}
