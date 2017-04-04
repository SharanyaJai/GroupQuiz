/*
 *This program displays the question according the selected type
 */
package groupquiz;

import groupquiz.testSelectionController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static groupquiz.testSelectionController.acount_e;
import static groupquiz.testSelectionController.acount_h;
import static groupquiz.testSelectionController.acount_m;
import static groupquiz.testSelectionController.c_qno;
import static groupquiz.testSelectionController.correct_answers;
import static groupquiz.testSelectionController.user_ttype;
import static groupquiz.testSelectionController.q_no;
import static groupquiz.testSelectionController.user_questions;
import static groupquiz.testSelectionController.count_e;
import static groupquiz.testSelectionController.count_h;
import static groupquiz.testSelectionController.count_m;

/**
 * FXML Controller class
 *
 * @author Harish
 */
public class MQuestionsController implements Initializable {

    /**
     *Declaring the static variable
     */
    public static int cmq = 0; //number of multiple choice questions

    /**
     *Declaring the static variable
     */
    public static int cfib = 0; //number of fill in the blanks
    
    
    @FXML
    private Label qDescription;
    @FXML
    private RadioButton rdButton1;
    @FXML
    private RadioButton rdButton2;
    @FXML
    private RadioButton rdButton3;
    @FXML
    private RadioButton rdButton4;
    @FXML
    private Button next;
    @FXML
    private Label fqtype;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        if (user_ttype == "Multiple Answer") {
            fqtype.setText("Multiple Answer");
            qDescription.setText(testSelectionController.q_no + ". " + testSelectionController.ma_q.get(testSelectionController.q_no).question);
            rdButton1.setText(testSelectionController.ma_q.get(testSelectionController.q_no).choiceA);
            rdButton2.setText(testSelectionController.ma_q.get(testSelectionController.q_no).choiceB);
            rdButton3.setText(testSelectionController.ma_q.get(testSelectionController.q_no).choiceC);
            rdButton4.setText(testSelectionController.ma_q.get(testSelectionController.q_no).choiceD);
        } else if (user_ttype == "Multiple Choice") {
            fqtype.setText("Multiple Choice");
            qDescription.setText(testSelectionController.q_no + ". " + testSelectionController.mc_q.get(testSelectionController.q_no).question);
            rdButton1.setText(testSelectionController.mc_q.get(testSelectionController.q_no).choiceA);
            rdButton2.setText(testSelectionController.mc_q.get(testSelectionController.q_no).choiceB);
            rdButton3.setText(testSelectionController.mc_q.get(testSelectionController.q_no).choiceC);
            rdButton4.setText(testSelectionController.mc_q.get(testSelectionController.q_no).choiceD);
        } else if ((user_ttype == "Combined") && c_qno <= Math.round(user_questions / 4)) {
            fqtype.setText("Multiple Choice");
            qDescription.setText(testSelectionController.c_qno + ". " + testSelectionController.mc_q.get(testSelectionController.c_qno).question);
            rdButton1.setText(testSelectionController.mc_q.get(testSelectionController.c_qno).choiceA);
            rdButton2.setText(testSelectionController.mc_q.get(testSelectionController.c_qno).choiceB);
            rdButton3.setText(testSelectionController.mc_q.get(testSelectionController.c_qno).choiceC);
            rdButton4.setText(testSelectionController.mc_q.get(testSelectionController.c_qno).choiceD);
        } else if ((user_ttype == "Combined") && c_qno <= 2 * Math.round(user_questions / 4)) {
            fqtype.setText("Multiple Answer");
            qDescription.setText(testSelectionController.c_qno + ". " + testSelectionController.ma_q.get(cmq).question);
            rdButton1.setText(testSelectionController.ma_q.get(cmq).choiceA);
            rdButton2.setText(testSelectionController.ma_q.get(cmq).choiceB);
            rdButton3.setText(testSelectionController.ma_q.get(cmq).choiceC);
            rdButton4.setText(testSelectionController.ma_q.get(cmq).choiceD);
        }

        // TODO4
    }

    @FXML

    private void nextQuestion(MouseEvent event) throws IOException, SQLException {
        
        switch (user_ttype) {

            case "Multiple Choice":
                int mcount = 0;
                if (rdButton1.isSelected()) {
                    mcount += 1;
                }
                if (rdButton2.isSelected()) {
                    mcount += 1;
                }
                if (rdButton3.isSelected()) {
                    mcount += 1;
                }
                if (rdButton4.isSelected()) {
                    mcount += 1;
                }
                if (mcount == 1) {
                    if (rdButton1.isSelected()) {

                        if (testSelectionController.mc_q.get(testSelectionController.q_no).choiceAAns.equalsIgnoreCase("correct")) {
                            if(testSelectionController.mc_q.get(q_no).Qlevel.equals("E"))
                    {
                        acount_e++;
                    }
                    else if(testSelectionController.mc_q.get(testSelectionController.q_no).Qlevel.equals("M"))
                        {
                            acount_m++;
                        }
                        else if(testSelectionController.mc_q.get(testSelectionController.q_no).Qlevel.equals("H"))
                        {
                            acount_h++;
                        }
                            
                            correct_answers++;
                        }
                    }
                    if (rdButton2.isSelected()) {

                        if (testSelectionController.mc_q.get(testSelectionController.q_no).choiceBAns.equalsIgnoreCase("correct")) {
                            if(testSelectionController.mc_q.get(q_no).Qlevel.equals("E"))
                    {
                        acount_e++;
                    }
                    else if(testSelectionController.mc_q.get(testSelectionController.q_no).Qlevel.equals("M"))
                        {
                            acount_m++;
                        }
                        else if(testSelectionController.mc_q.get(testSelectionController.q_no).Qlevel.equals("H"))
                        {
                            acount_h++;
                        }
                            correct_answers++;
                        }
                    }
                    if (rdButton3.isSelected()) {

                        if (testSelectionController.mc_q.get(testSelectionController.q_no).choiceCAns.equalsIgnoreCase("correct")) {
                            if(testSelectionController.mc_q.get(q_no).Qlevel.equals("E"))
                    {
                        acount_e++;
                    }
                    else if(testSelectionController.mc_q.get(testSelectionController.q_no).Qlevel.equals("M"))
                        {
                            acount_m++;
                        }
                        else if(testSelectionController.mc_q.get(testSelectionController.q_no).Qlevel.equals("H"))
                        {
                            acount_h++;
                        }
                            correct_answers++;
                        }
                    }
                    if (rdButton4.isSelected()) {

                        if (testSelectionController.mc_q.get(testSelectionController.q_no).choiceDAns.equalsIgnoreCase("correct")) {
                            if(testSelectionController.mc_q.get(q_no).Qlevel.equals("E"))
                    {
                        acount_e++;
                    }
                    else if(testSelectionController.mc_q.get(testSelectionController.q_no).Qlevel.equals("M"))
                        {
                            acount_m++;
                        }
                        else if(testSelectionController.mc_q.get(testSelectionController.q_no).Qlevel.equals("H"))
                        {
                            acount_h++;
                        }
                            correct_answers++;
                        }
                    }
                    if (q_no < user_questions) 
                    {
                        System.out.println("Inside If for questions number");
                        System.out.println(testSelectionController.mc_q.get(testSelectionController.q_no).Qlevel);
                        if(testSelectionController.mc_q.get(testSelectionController.q_no).Qlevel.equals("E"))
                        {
                            count_e++;
                        }
                        else if(testSelectionController.mc_q.get(testSelectionController.q_no).Qlevel.equals("M"))
                        {
                            count_m++;
                        }
                        else if(testSelectionController.mc_q.get(testSelectionController.q_no).Qlevel.equals("H"))
                        {
                            count_h++;
                        }
                        System.out.println("acount"+acount_e);
                        
                        q_no = q_no + 1;
                        Parent root = FXMLLoader.load(getClass().getResource("MQuestions.fxml"));
                        Stage stage = (Stage) next.getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } 
                    else 
                    {
                        System.out.println("aaa"+acount_e);
                        
                        Parent root = FXMLLoader.load(getClass().getResource("FinishTest.fxml"));
                        Stage stage = (Stage) next.getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Too many choices");
                    alert.setHeaderText(null);
                    alert.setContentText("Select only one option!!!");

                    alert.showAndWait();

                }
                break;
            case "Multiple Answer":

                if ((rdButton1.isSelected() == (testSelectionController.ma_q.get(testSelectionController.q_no).choiceAAns).equalsIgnoreCase("correct")) && (rdButton2.isSelected() == (testSelectionController.ma_q.get(testSelectionController.q_no).choiceBAns).equalsIgnoreCase("correct")) && (rdButton3.isSelected() == (testSelectionController.ma_q.get(testSelectionController.q_no).choiceCAns).equalsIgnoreCase("correct")) && (rdButton4.isSelected() == (testSelectionController.ma_q.get(testSelectionController.q_no).choiceDAns).equalsIgnoreCase("correct"))) {
                    if(testSelectionController.ma_q.get(q_no).Qlevel.equals("E"))
                    {
                        acount_e++;
                    }
                    else if(testSelectionController.ma_q.get(testSelectionController.q_no).Qlevel.equals("M"))
                        {
                            acount_m++;
                        }
                        else if(testSelectionController.ma_q.get(testSelectionController.q_no).Qlevel.equals("H"))
                        {
                            acount_h++;
                        }
                    correct_answers++;
                }

                if (q_no < user_questions) {
                    if(testSelectionController.ma_q.get(testSelectionController.q_no).Qlevel.equals("E"))
                        {
                            count_e++;
                           
                        }
                        else if(testSelectionController.ma_q.get(testSelectionController.q_no).Qlevel.equals("M"))
                        {
                            count_m++;
                    
                        }
                        else if(testSelectionController.ma_q.get(testSelectionController.q_no).Qlevel.equals("H"))
                        {
                            count_h++;
                        
                        }
                    q_no = q_no + 1;
                    System.out.println(q_no);
                    Parent root = FXMLLoader.load(getClass().getResource("MQuestions.fxml"));
                    Stage stage = (Stage) next.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    
                } else 
               
                {
                    Parent root = FXMLLoader.load(getClass().getResource("FinishTest.fxml"));
                    Stage stage = (Stage) next.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                break;
            case "Combined":
                if (c_qno <= Math.round(user_questions / 4)) {
                    int count = 0;
                    if (rdButton1.isSelected()) {
                        count += 1;
                    }
                    if (rdButton2.isSelected()) {
                        count += 1;
                    }
                    if (rdButton3.isSelected()) {
                        count += 1;
                    }
                    if (rdButton4.isSelected()) {
                        count += 1;
                    }

                    if (count == 1) {

                        if (rdButton1.isSelected()) {

                            if (testSelectionController.mc_q.get(testSelectionController.c_qno).choiceAAns.equalsIgnoreCase("correct")) {
                               if(testSelectionController.mc_q.get(c_qno).Qlevel.equals("E"))
                    {
                        acount_e++;
                    }
                    else if(testSelectionController.mc_q.get(testSelectionController.c_qno).Qlevel.equals("M"))
                        {
                            acount_m++;
                        }
                        else if(testSelectionController.mc_q.get(testSelectionController.c_qno).Qlevel.equals("H"))
                        {
                            acount_h++;
                        }
                                correct_answers++;
                            }
                        }
                        if (rdButton2.isSelected()) {

                            if (testSelectionController.mc_q.get(testSelectionController.c_qno).choiceBAns.equalsIgnoreCase("correct")) {
                                if(testSelectionController.mc_q.get(c_qno).Qlevel.equals("E"))
                    {
                        acount_e++;
                    }
                    else if(testSelectionController.mc_q.get(testSelectionController.c_qno).Qlevel.equals("M"))
                        {
                            acount_m++;
                        }
                        else if(testSelectionController.mc_q.get(testSelectionController.c_qno).Qlevel.equals("H"))
                        {
                            acount_h++;
                        }
                                correct_answers++;
                            }
                        }
                        if (rdButton3.isSelected()) {

                            if (testSelectionController.mc_q.get(testSelectionController.c_qno).choiceCAns.equalsIgnoreCase("correct")) {
                                if(testSelectionController.mc_q.get(c_qno).Qlevel.equals("E"))
                    {
                        acount_e++;
                    }
                    else if(testSelectionController.mc_q.get(testSelectionController.c_qno).Qlevel.equals("M"))
                        {
                            acount_m++;
                        }
                        else if(testSelectionController.mc_q.get(testSelectionController.c_qno).Qlevel.equals("H"))
                        {
                            acount_h++;
                        }
                                correct_answers++;
                            }
                        }
                        if (rdButton4.isSelected()) {

                            if (testSelectionController.mc_q.get(testSelectionController.c_qno).choiceDAns.equalsIgnoreCase("correct")) {
                                if(testSelectionController.mc_q.get(c_qno).Qlevel.equals("E"))
                    {
                        acount_e++;
                    }
                    else if(testSelectionController.mc_q.get(testSelectionController.c_qno).Qlevel.equals("M"))
                        {
                            acount_m++;
                        }
                        else if(testSelectionController.mc_q.get(testSelectionController.c_qno).Qlevel.equals("H"))
                        {
                            acount_h++;
                        }
                                correct_answers++;
                            }
                        }

                        c_qno = c_qno + 1;
                        Parent root = FXMLLoader.load(getClass().getResource("MQuestions.fxml"));
                        Stage stage = (Stage) next.getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } else {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Too many choices");
                        alert.setHeaderText(null);
                        alert.setContentText("Select only one option!!!");

                        alert.showAndWait();

                    }
                } else if (c_qno < 2 * Math.round(user_questions / 4)) {
                    if ((rdButton1.isSelected() == (testSelectionController.ma_q.get(cmq).choiceAAns).equalsIgnoreCase("correct")) && (rdButton2.isSelected() == (testSelectionController.ma_q.get(cmq).choiceBAns).equalsIgnoreCase("correct")) && (rdButton3.isSelected() == (testSelectionController.ma_q.get(cmq).choiceCAns).equalsIgnoreCase("correct")) && (rdButton4.isSelected() == (testSelectionController.ma_q.get(cmq).choiceDAns).equalsIgnoreCase("correct"))) {
                        if(testSelectionController.ma_q.get(cmq).Qlevel.equals("E"))
                    {
                        acount_e++;
                    }
                    else if(testSelectionController.ma_q.get(cmq).Qlevel.equals("M"))
                        {
                            acount_m++;
                        }
                        else if(testSelectionController.ma_q.get(cmq).Qlevel.equals("H"))
                        {
                            acount_h++;
                        }
                        correct_answers++;
                    }
                    cmq = cmq + 1;
                    c_qno = c_qno + 1;
                    Parent root = FXMLLoader.load(getClass().getResource("MQuestions.fxml"));
                    Stage stage = (Stage) next.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    if ((rdButton1.isSelected() == (testSelectionController.ma_q.get(cmq).choiceAAns).equalsIgnoreCase("correct")) && (rdButton2.isSelected() == (testSelectionController.ma_q.get(cmq).choiceBAns).equalsIgnoreCase("correct")) && (rdButton3.isSelected() == (testSelectionController.ma_q.get(cmq).choiceCAns).equalsIgnoreCase("correct")) && (rdButton4.isSelected() == (testSelectionController.ma_q.get(cmq).choiceDAns).equalsIgnoreCase("correct"))) {
                        if(testSelectionController.ma_q.get(cmq).Qlevel.equals("E"))
                    {
                        acount_e++;
                    }
                    else if(testSelectionController.ma_q.get(cmq).Qlevel.equals("M"))
                        {
                            acount_m++;
                        }
                        else if(testSelectionController.ma_q.get(cmq).Qlevel.equals("H"))
                        {
                            acount_h++;
                        }
                        correct_answers++;
                    }

                    cfib = cfib + 1;
                    c_qno = c_qno + 1;
                    Parent root = FXMLLoader.load(getClass().getResource("displayQuiz.fxml"));
                    Stage stage = (Stage) next.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                break;
            case "Fill in the blanks":
                if (q_no < user_questions) {
                    
                    q_no = q_no + 1;
                    //System.out.println(q_no);
                    Parent root = FXMLLoader.load(getClass().getResource("displayQuiz.fxml"));
                    Stage stage = (Stage) next.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    //break;  
                } else //q_no=q_no+1;
                //System.out.println(q_no);
                {
                    Parent root = FXMLLoader.load(getClass().getResource("FinishTest.fxml"));
                    Stage stage = (Stage) next.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                break;
            case "True or False":
                if (q_no < user_questions) {
                    q_no = q_no + 1;
                    System.out.println(q_no);
                    Parent root = FXMLLoader.load(getClass().getResource("displayQuiz.fxml"));
                    Stage stage = (Stage) next.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    //break;  
                } else //q_no=q_no+1;
                //System.out.println(q_no);
                {
                    Parent root = FXMLLoader.load(getClass().getResource("FinishTest.fxml"));
                    Stage stage = (Stage) next.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                break;

        }
    }

}
