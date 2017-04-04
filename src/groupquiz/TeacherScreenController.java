/*
 Program for instructor login screen
 */
package groupquiz;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sharanya
 */
public class TeacherScreenController implements Initializable {

    @FXML
    private TextField field1;// declaring the variable
    @FXML
    private PasswordField field2;// declaring the variable
    @FXML
    private Button loginbtn;// declaring the variable
    @FXML
    private Button signUpbtn;// declaring the variable
    @FXML
    private Label label1;// declaring the variable

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void teacherLogIn(MouseEvent event) {
    String user = field1.getText().toString();
        System.out.println(user);
        String pwd = field2.getText().toString();
        String framework = "network";//creating the database in netrwork mode
        String url = "jdbc:derby://localhost:1527/QCASDB;create=true";//establishing the connection
        String username = "sai";// use the username to connect
        String password = "sai";//use the password to connect
        Statement s;
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
        s = conn.createStatement();
         ResultSet rs = s.executeQuery("SELECT * FROM userDetails where andrewid='" + user + "'");
          ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
           // System.out.println(columnsNumber);
             while (rs.next()) {
                if (rs.getString(2).equalsIgnoreCase(pwd) && !rs.getString(1).endsWith("student") ) {
                   // System.out.println("Valid Credentials");
                    label1.setVisible(false);
                    Parent root = FXMLLoader.load(getClass().getResource("Instructor.fxml"));
                    Stage stage = (Stage) loginbtn.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    label1.setVisible(true);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        }
   // FXML screen for teacher sign up
    @FXML
    private void teacherSignUp(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TeacherSignUp.fxml"));
        Stage stage = (Stage) signUpbtn.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
