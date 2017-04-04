/*
 Creating the student login screen
 */
package groupquiz;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sharanya
 */
public class StudentScreenController implements Initializable {

    /**
     *
     */
    public static String user;//declaring the variable
    @FXML
    private Button signupBtn;//declaring the variable
    @FXML
    private Button login;//declaring the variable
    @FXML
    private ImageView img1;//declaring the variable
    @FXML
    private PasswordField field2;//declaring the variable
    @FXML
    private TextField field1;//declaring the variable
    @FXML
    private TextField field3;//declaring the variable

    /**
     * Initializes the controller class. and creating the FXML for the student login
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void studentLogIn(MouseEvent event) throws SQLException {
        user = field1.getText().toString();
        System.out.println(user);
        String pwd = field2.getText().toString();
        String framework = "network";//connecting in network mode
        String url = "jdbc:derby://localhost:1527/QCASDB;create=true";
        String username = "sai";//username
        String password = "sai";//password
        Statement s;
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            s = conn.createStatement();
            user = user.concat("student");
            ResultSet rs = s.executeQuery("SELECT * FROM userDetails where andrewid='" + user + "'");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            //System.out.println(columnsNumber);
            while (rs.next()) {
                if (rs.getString(2).equalsIgnoreCase(pwd) && rs.getString(1).endsWith("student")) {
                    System.out.println("Valid Credentials");
                    Parent root = FXMLLoader.load(getClass().getResource("testSelection.fxml"));
                    Stage stage = (Stage) login.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    System.out.println("Invalid");
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }
   //FXML for student sign up
    @FXML
    private void studentSignUp(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StudentSignUp.fxml"));
        Stage stage = (Stage) signupBtn.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
