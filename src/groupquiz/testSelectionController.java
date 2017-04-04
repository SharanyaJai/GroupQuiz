/*
 * Program for Selecting the desired type of test
 */
package groupquiz;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import static groupquiz.ExamDb.conn;
import static groupquiz.ExamDb.rs;
import static groupquiz.ExamDb.s;
import static groupquiz.ExamDb.statements;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * FXML Controller class
 *
 * @author rituparnaj
 */
public class testSelectionController implements Initializable {

    /**
     *Static variable for the level of test
     */
    public static String user_level;

    /**
     *static variable for the type of test
     */
    public static String user_ttype;

    /**
     *Static variable for the type of question
     */
    public static int user_questions;

    /**
     *Arraylist for multiple answer questions
     */
    public static ArrayList<MA> ma_q = new ArrayList<MA>();

    /**
     *Arraylist for multiple choice questions
     */
    public static ArrayList<MC> mc_q = new ArrayList<MC>();

    /**
     *Arraylist for fill in the blank questions
     */
    public static ArrayList<FIB> fib_q = new ArrayList<FIB>();

    /**
     *Arraylist for true and false questions
     */
    public static ArrayList<TF> tf_q = new ArrayList<TF>();

    /**
     *initializing the variable
     */
    public static int q_no = 0;

    /**
     *initializing the variable
     */
    public static int c_qno = 0;

    /**
     *initializing the variable
     */
    public static int correct_answers = 0;

    /**
     *initializing the variable
     */
    public static int count_e = 0;

    /**
     *initializing the variable
     */
    public static int count_m = 0;

    /**
     *initializing the variable
     */
    public static int count_h = 0;

    /**
     *initializing the variable
     */
    public static int acount_e = 0;

    /**
     *initializing the variable
     */
    public static int acount_m = 0;

    /**
     *initializing the variable
     */
    public static int acount_h = 0;

    @FXML
    private ComboBox<String> difficultyCombo;
    @FXML
    private ComboBox<String> typeCombo;
    @FXML
    private ComboBox<Integer> qNumberCombo;
    @FXML
    private Label label;
    @FXML
    private Button startTest;
    private String framework = "embedded";//creating the database and forming the connection
    private String protocol = "jdbc:derby://localhost:1527/";

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        typeCombo.getItems().removeAll(typeCombo.getItems());
        difficultyCombo.getItems().removeAll(typeCombo.getItems());
        qNumberCombo.getItems().removeAll(typeCombo.getItems());
        typeCombo.getItems().addAll("Multiple Choice", "Multiple Answer", "Fill in the blanks", "True or False", "Combined");
        difficultyCombo.getItems().addAll("Easy", "Medium", "Hard", "Combined");
        qNumberCombo.getItems().addAll(5, 10, 15);

        // TODO
    }

    @FXML
    private void testCreation(MouseEvent event) throws IOException, SQLException {

        
        Properties props = new Properties();
        props.put("user", "sai");               // username for the created database
        props.put("password", "sai");             //password for the created database

        String dbName = "Test"; // the name of the database
        conn = DriverManager.getConnection(protocol + dbName
                + ";create=true", props); //create a database and establish connection

        System.out.println("Connected to and created database " + dbName);

        conn.setAutoCommit(false);

        /* Creating a statement object that we can use for running various
             * SQL statements commands against the database.*/
        s = conn.createStatement();
        statements.add(s);
        user_ttype = typeCombo.getSelectionModel().getSelectedItem().toString();
        user_level = difficultyCombo.getSelectionModel().getSelectedItem().toString();
        user_questions = qNumberCombo.getSelectionModel().getSelectedItem();
        String query;

        if (user_level.equals("Combined")) {
            query = "Select * from QuizType4MA";
        } else {
            query = "Select * from QuizType4MA Where Qlevel=" + "'" + user_level.charAt(0) + "'";
        }
     
        System.out.println(query);
        rs = s.executeQuery(query);
        while (rs.next()) {
            MA ma = new MA();
            ma.setQType(rs.getString("QType"));
            ma.setQlevel(rs.getString("Qlevel"));
            ma.setQuestion(rs.getString("question"));
            ma.setChoiceA(rs.getString("choiceA"));
            ma.setChoiceAAns(rs.getString("choiceAAns"));
            ma.setChoiceB(rs.getString("choiceB"));
            ma.setChoiceBAns(rs.getString("choiceBAns"));
            ma.setChoiceC(rs.getString("choiceC"));
            ma.setChoiceCAns(rs.getString("choiceCAns"));
            ma.setChoiceD(rs.getString("choiceD"));
            ma.setChoiceDAns(rs.getString("choiceDAns"));
            ma_q.add(ma);
        }
        Collections.shuffle(ma_q);
        System.out.println(ma_q.get(1).Qlevel);
        if (user_level.equals("Combined")) {
            query = "Select * from QuizType1MC";
        } else {
            query = "Select * from QuizType1MC Where Qlevel=" + "'" + user_level.charAt(0) + "'";
        }
       rs = s.executeQuery(query);
        while (rs.next()) {

            MC mc = new MC();
            mc.setQType(rs.getString("QType"));
            mc.setQlevel(rs.getString("Qlevel"));
            mc.setQuestion(rs.getString("question"));
            mc.setChoiceA(rs.getString("choiceA"));
            mc.setChoiceAAns(rs.getString("choiceAAns"));
            mc.setChoiceB(rs.getString("choiceB"));
            mc.setChoiceBAns(rs.getString("choiceBAns"));
            mc.setChoiceC(rs.getString("choiceC"));
            mc.setChoiceCAns(rs.getString("choiceCAns"));
            mc.setChoiceD(rs.getString("choiceD"));
            mc.setChoiceDAns(rs.getString("choiceDAns"));
            mc_q.add(mc);
        }
        Collections.shuffle(mc_q);
        if (user_level.equals("Combined")) {
            query = "Select * from QuizType2FIB";
        } else {
            query = "Select * from QuizType2FIB Where Qlevel=" + "'" + user_level.charAt(0) + "'";

        }

        rs = s.executeQuery(query);
        while (rs.next()) {

            FIB fib = new FIB();
            fib.setQType(rs.getString("QType"));
            fib.setQlevel(rs.getString("Qlevel"));
            fib.setQuestion(rs.getString("question"));
            fib.setAnswer(rs.getString("answer"));
            fib_q.add(fib);
        }
        Collections.shuffle(fib_q);
        if (user_level.equals("Combined")) {
            query = "Select * from QuizType3TF";
        } else {
            query = "Select * from QuizType3TF Where Qlevel=" + "'" + user_level.charAt(0) + "'";

        }
        rs = s.executeQuery(query);
        while (rs.next()) {

            TF tf = new TF();
            tf.setQType(rs.getString("QType"));
            tf.setQlevel(rs.getString("Qlevel"));
            tf.setQuestion(rs.getString("question"));
            tf.setAnswer(rs.getString("answer"));
            tf_q.add(tf);
        }
        Collections.shuffle(tf_q);
        switch (user_ttype) {
            case "Multiple Answer":
                q_no = 1;
                Parent root = FXMLLoader.load(getClass().getResource("MQuestions.fxml"));
                Stage stage = (Stage) startTest.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                break;
            case "Multiple Choice":
                q_no = 1;
                root = FXMLLoader.load(getClass().getResource("MQuestions.fxml"));
                stage = (Stage) startTest.getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                break;
            case "Fill in the blanks":
                System.out.println(q_no);
                q_no = 1;
                root = FXMLLoader.load(getClass().getResource("displayQuiz.fxml"));
                stage = (Stage) startTest.getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                break;
            case "True or False":
                q_no = 1;
                root = FXMLLoader.load(getClass().getResource("displayQuiz.fxml"));
                stage = (Stage) startTest.getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                break;
            case "Combined":
                c_qno = 1;
                root = FXMLLoader.load(getClass().getResource("MQuestions.fxml"));
                stage = (Stage) startTest.getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                break;
        }

    }

}
