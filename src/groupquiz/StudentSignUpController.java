/*
 * This program is the sign up screen for student where the student can sign up
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
public class StudentSignUpController implements Initializable {

    @FXML
    private PasswordField field3;//declaring the variable
    @FXML
    private Button signup;//declaring the variable
    @FXML
    private TextField field1;//declaring the variable
    @FXML
    private PasswordField field2;//declaring the variable
    @FXML
    private Label label1;//declaring the variable

    /**
     * Initializes the controller class.
     *
     * @param url-using a url
     * @param rb-using a resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void SignUp(MouseEvent event) {
        String user = field1.getText().toString();
        user = user.concat("student");
        System.out.println(user);
        String pwd = field2.getText().toString();
        {

            String framework = "network";//creating a database mode
            String url = "jdbc:derby://localhost:1527/QCASDB;create=true";// using the url to connect
            String username = "sai";//giving username
            String password = "sai";//giving password
            Statement s;//creating the statement
            try (Connection conn = DriverManager.getConnection(url, username, password))//establishing the connection
            {
                System.out.println("");
                s = conn.createStatement();
                ResultSet r = s.executeQuery("SELECT Count(*) FROM userdetails where andrewid='" + user + "'");
                while (r.next()) {
                    if (r.getString(1).equals("0")) {
                        if (!field2.getText().toString().equals(field3.getText().toString())) {
                            label1.setVisible(true);
                            break;
                        } else {
                            s.executeUpdate("insert into userDetails values('" + user + "','" + pwd + "')");//inserting into the table
                            System.out.println("Success" + user);
                            Parent root = FXMLLoader.load(getClass().getResource("testSelection.fxml"));//loading the fxml
                            Stage stage = (Stage) signup.getScene().getWindow();
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                            break;
                        }
                    }
                    ResultSet rs = s.executeQuery("SELECT * FROM userDetails");
                    ResultSetMetaData rsmd = rs.getMetaData();//extracting data from the table
                    int columnsNumber = rsmd.getColumnCount();
                    System.out.println(columnsNumber);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

}
