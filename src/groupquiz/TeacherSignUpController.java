/*
 * This program allows the instructor to sign up 
 */
package groupquiz;

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
public class TeacherSignUpController implements Initializable {

    @FXML
    private PasswordField field3;
    @FXML
    private Button signup;
    @FXML
    private TextField field1;
    @FXML
    private PasswordField field2;
    @FXML
    private Label label1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      // TODO
    }

    @FXML
    private void SignUp(MouseEvent event) {
    String user = field1.getText().toString();
    String pwd = field2.getText().toString();
    if (!field2.getText().toString().equals(field3.getText().toString())) {
    label1.setVisible(true);
    } else {
    String framework = "network";
    String url = "jdbc:derby://localhost:1527/QCASDB;create=true";
    String username = "sai";
    String password = "sai";
    Statement s;
    String t="teacher";
   
    try (Connection conn = DriverManager.getConnection(url, username, password)) {
    //conn.setAutoCommit(false);
    System.out.println("");
    s = conn.createStatement();
    ResultSet r = s.executeQuery("SELECT Count(*) FROM userDetails where andrewid='" + user + "'");
    while (r.next()) {
    if (r.getString(1).equals("0")) {
    {                    
    s.executeUpdate("insert into userDetails values('" + user + "','" + pwd + "')");
    System.out.println("Successing"+user);
    Parent root = FXMLLoader.load(getClass().getResource("Instructor.fxml"));
    Stage stage = (Stage) signup.getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    break;
    }
    }
    }
    } catch (Exception e) {
    System.out.println(e);
    }
    }
    }
    }

