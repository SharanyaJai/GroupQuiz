/*
 * This program will load the selected csv file and put it into the database and creates four different tables
 */
package groupquiz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.util.List;
import com.opencsv.CSVReader;
import java.time.Duration;
import java.util.Timer;

/**
 *
 * @author Lakshmi Sanjana
 */
public class InstructorController extends Application {
    private String framework = "network";
    private String protocol = "jdbc:derby://localhost:1527/";
    static Connection conn1 = null;
    static ArrayList<Statement> statements1 = new ArrayList<Statement>(); 
    static PreparedStatement psInsert1;
    static PreparedStatement psUpdate1; 
    static Statement s1; 
    static ResultSet rs1 = null;
 
    /**
     * Main class creates an FXML with the following purpose
     * FXML has three buttons which are load questions,report and logout
     * Load questions button will load the file into the database
     * Report will generate the report of the instructor
     * Logout button will allow the instructor to logout
     * @param args
     * @throws FileNotFoundException
     * @throws SQLException
     */
    public static void main(String[] args) throws FileNotFoundException, SQLException {
    launch(args);
    System.out.println("please close the dialog box");
    }
    @FXML
    private Button loadquestions;// creating the load questions button
    @FXML
    private Button report;// creating the report button
    @FXML
    private Button logout;// creating the logout button

    @FXML
    private void loadQuestions(MouseEvent event) throws FileNotFoundException, IOException, SQLException {
    Properties props = new Properties(); //Creating the properties statement
    props.put("user", "sai");// giving the user name
    props.put("password", "sai");// giving the password
    String dbName = "Test"; // the name of the database
    conn1 = DriverManager.getConnection(protocol + dbName
    + ";create=true", props);//creating the connection of the database
    conn1.setAutoCommit(false);
    s1 = conn1.createStatement();
    statements1.add(s1);
    FileInputStream filecontents = null;//reading from the file
    FileChooser fileChooser = new FileChooser();// Choosing the selected file
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
    fileChooser.getExtensionFilters().add(extFilter);
    final Stage primaryStage = null;
    File kilo = fileChooser.showOpenDialog(primaryStage);
    FileReader filereader = new FileReader(kilo);//reading the selected file
    BufferedReader reader=new BufferedReader(filereader);
    CSVReader reader1=new CSVReader(new FileReader(kilo));// reading the csv file
    List<String[]> arraywords=new ArrayList<String[]>();
    arraywords=reader1.readAll();
    for(int i=0;i<arraywords.size();i++){
    if(arraywords.get(i)[0].equals("TF")){                  
    psInsert1 = conn1.prepareStatement(
    "insert into QuizType3TF values (?, ?, ?, ? )");     //Inserting values into the true false table              
    psInsert1.setString(1, arraywords.get(i)[0]);
    psInsert1.setString(2, arraywords.get(i)[1]);
    psInsert1.setString(3, arraywords.get(i)[2]);
    psInsert1.setString(4, arraywords.get(i)[3]);
    psInsert1.executeUpdate();  
    conn1.commit();
     }
     else if(arraywords.get(i)[0].equals("FIB")){
     psInsert1 = conn1.prepareStatement(
     "insert into QuizType2FIB values (?, ?, ?, ? )");      //Inserting values into the fill in the blanks table
      statements1.add(psInsert1);                  
      psInsert1.setString(1, arraywords.get(i)[0]);
      psInsert1.setString(2, arraywords.get(i)[1]);
      psInsert1.setString(3, arraywords.get(i)[2]);
      psInsert1.setString(4, arraywords.get(i)[3]);
      int rowcount = psInsert1.executeUpdate();  
      conn1.commit();
     }
      else if(arraywords.get(i)[0].equals("MA")){
      psInsert1 = conn1.prepareStatement(
      "insert into QuizType4MA values (?, ?, ?, ? , ?, ?, ?, ?, ?, ?, ?)"); //Inserting values into the Multiple answer table
      statements1.add(psInsert1);
      psInsert1.setString(1, arraywords.get(i)[0]);
      psInsert1.setString(2, arraywords.get(i)[1]);
      psInsert1.setString(3, arraywords.get(i)[2]);
      psInsert1.setString(4, arraywords.get(i)[3]);
      psInsert1.setString(5, arraywords.get(i)[4]);
      psInsert1.setString(6, arraywords.get(i)[5]);
      psInsert1.setString(7, arraywords.get(i)[6]);
      psInsert1.setString(8, arraywords.get(i)[7]);
      psInsert1.setString(9, arraywords.get(i)[8]);
      psInsert1.setString(10, arraywords.get(i)[9]);
      psInsert1.setString(11, arraywords.get(i)[10]);
      int rowcount = psInsert1.executeUpdate();  
      conn1.commit();
      }
      else if(arraywords.get(i)[0].equals("MC")){
      psInsert1 = conn1.prepareStatement(
      "insert into QuizType1MC values (?, ?, ?, ? , ?, ?, ?, ?, ?, ?, ?)");//Inserting values into the multiple choice table
      statements1.add(psInsert1);
      psInsert1.setString(1, arraywords.get(i)[0]);
      psInsert1.setString(2, arraywords.get(i)[1]);
      psInsert1.setString(3, arraywords.get(i)[2]);
      psInsert1.setString(4, arraywords.get(i)[3]);
      psInsert1.setString(5, arraywords.get(i)[4]);
      psInsert1.setString(6, arraywords.get(i)[5]);
      psInsert1.setString(7, arraywords.get(i)[6]);
      psInsert1.setString(8, arraywords.get(i)[7]);
      psInsert1.setString(9, arraywords.get(i)[8]);
      psInsert1.setString(10, arraywords.get(i)[9]);
      psInsert1.setString(11, arraywords.get(i)[10]);
      int rowcount = psInsert1.executeUpdate(); 
      conn1.commit();
      }
      }
      psInsert1.close();
      
      String queries="Select * from QuizType3TF ";
      rs1=s1.executeQuery(queries);                
      while(rs1.next()){
      String lastname=(rs1.getString("Qlevel"));
      System.out.println(lastname);  
      String astname=(rs1.getString("question"));
      System.out.println(lastname); 
      }
      String querie="Select * from QuizType2FIB ";
      rs1=s1.executeQuery(querie);                
      while(rs1.next()){
      String lastname=(rs1.getString("Qtype"));
      System.out.println(lastname);
      }
      String quer="Select * from QuizType4MA ";
      rs1=s1.executeQuery(quer);
      while(rs1.next()){
      String lastname=(rs1.getString("Qtype"));
      System.out.println(lastname);  
      }
      String que="Select * from QuizType1MC ";
      rs1=s1.executeQuery(quer);                 
      while(rs1.next()){
      String lastname=(rs1.getString("Qtype"));
      String astname=(rs1.getString("question"));
      System.out.println(lastname);  
      System.out.println(astname); 
      }
      rs1.close();
      s1.close();
     }
           @Override
            public void start(Stage primaryStage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("Instructor.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Quiz");
            primaryStage.show();
            System.out.println("ExamApp starting in " + framework + " mode");
            Properties props = new Properties(); 
            props.put("user", "sai");
            props.put("password", "sai");
            String dbName = "QCASDB"; // the name of the database
            conn1 = DriverManager.getConnection(protocol + dbName
                    + ";create=true", props);
            System.out.println("Connected to and created database " + dbName);//creating the database
            conn1.setAutoCommit(false);
            s1 = conn1.createStatement();
            statements1.add(s1);
            s1.execute("create table location1( type varchar(180), level varchar(180), question varchar(180), answer varchar(140))");
            psInsert1 = conn1.prepareStatement(
                        "insert into location1 values (?, ?, ?, ?)");// creating the table1
            System.out.println("Table1 created");
            s1.execute("create table location2( type varchar(180), level varchar(180), question varchar(180), answer varchar(140))");
            psInsert1 = conn1.prepareStatement(
                        "insert into location2 values (?, ?, ?, ?)");// creating table 2
            System.out.println("Table2 created");
            s1.execute("create table location3( type varchar(180), level varchar(180), question varchar(180), choice1 varchar(140),answer1 varchar(140),choice2 varchar(140),answer2 varchar(140),choice3 varchar(140),answer3 varchar(140),choice4 varchar(140),answer4 varchar(140))");
            psInsert1 = conn1.prepareStatement(
                        "insert into location3 values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");// creating the table 3
            System.out.println("Table3 created");
            s1.execute("create table location4( type varchar(180), level varchar(180), question varchar(180), choice1 varchar(140),answer1 varchar(140),choice2 varchar(140),answer2 varchar(140),choice3 varchar(140),answer3 varchar(140),choice4 varchar(140),answer4 varchar(140))");
            psInsert1 = conn1.prepareStatement(
                        "insert into location4 values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");// creating the table 4
            System.out.println("Table4 created");
            }

         @FXML
        private void viewreport(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("New.fxml"));
        Stage stage = (Stage) report.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
        
       
    Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
    Stage stage = (Stage) logout.getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }
        }
    
        