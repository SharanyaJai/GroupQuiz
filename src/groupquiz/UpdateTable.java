/*
Program to calculate the overall score of the student in all types of questions
 */
package groupquiz;

import static groupquiz.InstructorController.conn1;
import static groupquiz.InstructorController.psInsert1;
import static groupquiz.InstructorController.s1;
import static groupquiz.InstructorController.statements1;
import static groupquiz.StudentScreenController.user;
import static groupquiz.testSelectionController.acount_e;
import static groupquiz.testSelectionController.acount_h;
import static groupquiz.testSelectionController.acount_m;
import static groupquiz.testSelectionController.correct_answers;
import static groupquiz.testSelectionController.count_e;
import static groupquiz.testSelectionController.count_h;
import static groupquiz.testSelectionController.count_m;
import static groupquiz.testSelectionController.q_no;
import static groupquiz.testSelectionController.user_level;
import static groupquiz.testSelectionController.user_questions;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;

/**
 *
 * @author sharanya
 */
public class UpdateTable {

    private String framework = "network";
    private String protocol = "jdbc:derby://localhost:1527/";

    String grade;

    /**
     *Declaring a static variable
     */
    public static int score;

    /**
     *Declaring a static variable
     */
    public static int score_e;

    /**
     *Declaring a static variable
     */
    public static int score_m;

    /**
     *Declaring a static variable
     */
    public static int score_h;

    /**
     *
     * @throws SQLException
     */
    public void update() throws SQLException {
        int month;
        Random rand = new Random();
        month = 1 + rand.nextInt(12);

        if (correct_answers >= (user_questions / 2)) {
            grade = "pass";
        } else {
            grade = "fail";
        }
        System.out.println(grade);
        int score = (int) (correct_answers * 100) / (user_questions);
        int score_e = 0;
        int score_m = 0;//initializing the variables
        int score_h = 0;
        //counting based on the level type
        if (user_level == "Easy") {
            score_e = (int) (count_e * 100) / user_questions;

        } else if (user_level == "Medium") {
            score_m = (int) (count_m * 100) / user_questions;
        } else if (user_level == "Hard") {
            score_h = (int) (count_h * 100) / user_questions;
        } else if (user_level == "Combined") {
           
           // System.out.println(count_e);
           // System.out.println(count_m);
           // System.out.println(count_h);
           // System.out.println(acount_e);
           // System.out.println(acount_m);
           // System.out.println(acount_h);
            if (count_e != 0) {

                score_e = (int) (acount_e * 100) / count_e;
            } else {
                score_e = 0;
            }
            if (count_m != 0) {

                score_m = (int) (acount_m * 100) / count_m;
            } else {
                score_m = 0;
            }
            if (count_h != 0) {

                score_h = (int) (acount_h * 100) / count_h;
            } else {
                score_h = 0;
            }

        }

       // System.out.println("Inside insert");
        Properties props = new Properties();
        props.put("user", "sai");
        props.put("password", "sai");
        String dbName = "Test"; // the name of the database
        conn1 = DriverManager.getConnection(protocol + dbName
                + ";create=true", props);//connecting to the database
      //  System.out.println("Connected to and created database " + dbName);
        conn1.setAutoCommit(false);
        s1 = conn1.createStatement();
        statements1.add(s1);
        psInsert1 = conn1.prepareStatement(
                "insert into STUDENT values (?, ?, ?, ?,?,?,? )");//inserting into the table
        psInsert1.setString(1, grade);
        psInsert1.setInt(2, month);
        psInsert1.setInt(3, score);
        psInsert1.setInt(4, score_e);
        psInsert1.setInt(5, score_m);
        psInsert1.setInt(6, score_h);
        psInsert1.setString(7, user);
        psInsert1.executeUpdate();
        System.out.println(" Inserted success");
        conn1.commit();

      //  System.out.println(score_e);//printing the scores
      //  System.out.println(score_m);
       // System.out.println(score_h);
       // System.out.println(score);

    }
}
