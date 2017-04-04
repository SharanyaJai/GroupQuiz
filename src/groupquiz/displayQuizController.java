/*
 * This program is for displaying the questions and selecting the right answers
 */
package groupquiz;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static groupquiz.MQuestionsController.cfib;
import static groupquiz.testSelectionController.count_e;
import static groupquiz.testSelectionController.count_h;
import static groupquiz.testSelectionController.count_m;
import static groupquiz.testSelectionController.acount_e;
import static groupquiz.testSelectionController.acount_m;
import static groupquiz.testSelectionController.acount_h;
import static groupquiz.testSelectionController.c_qno;
import static groupquiz.testSelectionController.correct_answers;
import static groupquiz.testSelectionController.q_no;
import static groupquiz.testSelectionController.user_questions;
import static groupquiz.testSelectionController.user_ttype;
import java.time.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import java.time.Duration;
import java.util.Timer;
/**
 * FXML Controller class
 *
 * @author Harish
 */
public class displayQuizController implements Initializable {

    /**
     * Declaring variables of the FXML
     */
    public static int ctf = 0;
    @FXML
    private Label question;
    @FXML
    private Label errorMessage;
    @FXML
    private Button next;
    @FXML
    private TextField answer;
    @FXML
    private Label ftqtype;
    private Timer timer;

    /**
     * Initializes the controller class. And displays the required question and
     * analyzes the  answer by the student
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if (user_ttype == "Fill in the blanks") //checks the userselected type and displays the question
        {
            ftqtype.setText("Fill in the blank");
            question.setText(testSelectionController.q_no + ". " + testSelectionController.fib_q.get(testSelectionController.q_no).question);
        } else if (user_ttype == "True or False") //checks the userselected type and displays the question
        {
            ftqtype.setText("True or False");
            question.setText(testSelectionController.q_no + ". " + testSelectionController.tf_q.get(testSelectionController.q_no).question);
        } else if ((user_ttype == "Combined") && c_qno <= 3 * Math.round(user_questions / 4))  //checks the userselected type and displays the question
        {
            ftqtype.setText("Fill in the blank");
            question.setText(testSelectionController.c_qno + ". " + testSelectionController.fib_q.get(cfib).question);
        } else if ((user_ttype == "Combined") && c_qno <= user_questions) {
            ftqtype.setText("True or False");
            question.setText(testSelectionController.c_qno + ". " + testSelectionController.tf_q.get(ctf).question);

        }
        
       
    }
   // using the switch case to detect the type of question
    @FXML
    private void nextQ(MouseEvent event) throws IOException {
        switch (user_ttype) {

            case "Multiple Choice":
                q_no = q_no + 1;
                System.out.println(q_no);
                Parent root = FXMLLoader.load(getClass().getResource("MQuestions.fxml"));
                Stage stage = (Stage) next.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                break;
            case "Multiple Answer":
                q_no = q_no + 1;
                root = FXMLLoader.load(getClass().getResource("MQuestions.fxml"));
                stage = (Stage) next.getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                break;
            case "Fill in the blanks":
                if (answer.getText().equalsIgnoreCase(testSelectionController.fib_q.get(q_no).answer)) {
                    if (testSelectionController.fib_q.get(q_no).Qlevel.equals("E")) {
                        acount_e++;
                    } else if (testSelectionController.fib_q.get(testSelectionController.q_no).Qlevel.equals("M")) {
                        acount_m++;
                    } else if (testSelectionController.fib_q.get(testSelectionController.q_no).Qlevel.equals("H")) {
                        acount_h++;
                    }
                    correct_answers++;
                }

                if (q_no < user_questions) {
                    if (testSelectionController.fib_q.get(testSelectionController.q_no).Qlevel.equals("E")) {
                        count_e++;
                    } else if (testSelectionController.fib_q.get(testSelectionController.q_no).Qlevel.equals("M")) {
                        count_m++;
                    } else if (testSelectionController.fib_q.get(testSelectionController.q_no).Qlevel.equals("H")) {
                        count_h++;
                    }
                    q_no = q_no + 1;
                    root = FXMLLoader.load(getClass().getResource("displayQuiz.fxml"));
                    stage = (Stage) next.getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                } else {
                    root = FXMLLoader.load(getClass().getResource("FinishTest.fxml"));
                    stage = (Stage) next.getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                break;
            case "True or False":
                if (answer.getText().equalsIgnoreCase(testSelectionController.tf_q.get(q_no).answer)) {
                    if (testSelectionController.tf_q.get(q_no).Qlevel.equals("E")) {
                        acount_e++;
                    } else if (testSelectionController.tf_q.get(testSelectionController.q_no).Qlevel.equals("M")) {
                        acount_m++;
                    } else if (testSelectionController.tf_q.get(testSelectionController.q_no).Qlevel.equals("H")) {
                        acount_h++;
                    }
                    correct_answers++;
                }
                if (q_no < user_questions) {
                    if (testSelectionController.tf_q.get(testSelectionController.q_no).Qlevel.equals("E")) {
                        count_e++;
                    } else if (testSelectionController.tf_q.get(testSelectionController.q_no).Qlevel.equals("M")) {
                        count_m++;
                    } else if (testSelectionController.tf_q.get(testSelectionController.q_no).Qlevel.equals("H")) {
                        count_h++;
                    }
                    q_no = q_no + 1;

                    root = FXMLLoader.load(getClass().getResource("displayQuiz.fxml"));
                    stage = (Stage) next.getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                } else {
                    root = FXMLLoader.load(getClass().getResource("FinishTest.fxml"));
                    stage = (Stage) next.getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                break;

            case "Combined":
                if (c_qno <= 3 * Math.round(user_questions / 4)) {
                    if (testSelectionController.fib_q.get(cfib).Qlevel.equals("E")) {
                        count_e++;
                    } else if (testSelectionController.fib_q.get(cfib).Qlevel.equals("M")) {
                        count_m++;
                    } else if (testSelectionController.fib_q.get(cfib).Qlevel.equals("H")) {
                        count_h++;
                    }
                    if (answer.getText().equalsIgnoreCase(testSelectionController.fib_q.get(cfib).answer)) {
                        if (testSelectionController.fib_q.get(cfib).Qlevel.equals("E")) {
                            acount_e++;
                        } else if (testSelectionController.fib_q.get(cfib).Qlevel.equals("M")) {
                            acount_m++;
                        } else if (testSelectionController.fib_q.get(cfib).Qlevel.equals("H")) {
                            acount_h++;
                        }
                        correct_answers++;
                    }

                    cfib = cfib + 1;
                    c_qno = c_qno + 1;
                    root = FXMLLoader.load(getClass().getResource("displayQuiz.fxml"));
                    stage = (Stage) next.getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else if (c_qno < user_questions) {
                    if (testSelectionController.tf_q.get(ctf).Qlevel.equals("E")) {
                        count_e++;
                        System.out.println(count_e);
                    } else if (testSelectionController.tf_q.get(ctf).Qlevel.equals("M")) {
                        count_m++;
                        System.out.println(count_m);
                    } else if (testSelectionController.tf_q.get(ctf).Qlevel.equals("H")) {
                        count_h++;
                        System.out.println(count_h);
                    }
                    if (answer.getText().equalsIgnoreCase(testSelectionController.tf_q.get(ctf).answer)) {
                        if (testSelectionController.tf_q.get(ctf).Qlevel.equals("E")) {
                            acount_e++;
                        } else if (testSelectionController.tf_q.get(ctf).Qlevel.equals("M")) {
                            acount_m++;
                        } else if (testSelectionController.tf_q.get(ctf).Qlevel.equals("H")) {
                            acount_h++;
                        }
                        correct_answers++;
                    }

                    ctf = ctf + 1;
                    System.out.println(ctf);
                    c_qno = c_qno + 1;
                    root = FXMLLoader.load(getClass().getResource("displayQuiz.fxml"));
                    stage = (Stage) next.getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                } else {
                    if (answer.getText().equalsIgnoreCase(testSelectionController.tf_q.get(ctf).answer)) {
                        if (testSelectionController.tf_q.get(ctf).Qlevel.equals("E")) {
                            acount_e++;
                        } else if (testSelectionController.tf_q.get(ctf).Qlevel.equals("M")) {
                            acount_m++;
                        } else if (testSelectionController.tf_q.get(ctf).Qlevel.equals("H")) {
                            acount_h++;
                        }
                        correct_answers++;
                    }
                    System.out.println("a_count" + acount_e);
                    System.out.println("a_count" + acount_m);
                    System.out.println("a_count" + acount_h);
                    ctf = ctf + 1;
                    root = FXMLLoader.load(getClass().getResource("FinishTest.fxml"));
                    stage = (Stage) next.getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                }
                break;

        }

    }

    @FXML
    private void hello(ActionEvent event) {

    }

}
