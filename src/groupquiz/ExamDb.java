/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupquiz;

import java.io.BufferedReader;
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
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is used for creating the embedded mode database Also for updating
 * the database which includes first reading from the text file
 *
 * @author Harish
 */
public class ExamDb {

    private String framework = "embedded";
    private String protocol = "jdbc:derby:";

    /**
     *Creating the connection to the database
     */
    public static Connection conn = null;// connecting to database

    /**
     * creating an array list of statements
     */
    public static ArrayList<Statement> statements = new ArrayList<Statement>(); // list of Statements, PreparedStatements

    /**
     * Add prepared statements
     */
    public static PreparedStatement psInsert;

    /**
     *Add another prepared statement for updating the table
     */
    public static PreparedStatement psUpdate;

    /**
     *Add statement s
     */
    public static Statement s;

    /**
     * create a result set
     */
    public static ResultSet rs = null;

    /**
     * This method is used to create the database connection in embedded mode
     * The database is created in the project folder The list of questions are
     * updated from the text file into the database
     */
    void go() throws FileNotFoundException, SQLException, IOException {
        s = conn.createStatement();
        statements.add(s);
        s.execute("create table QuizType1MC(QType varchar(10), Qlevel varchar(10), question varchar(1000), choiceA varchar(1000), choiceAans varchar(1000), choiceB varchar(1000), choiceBans varchar(1000), choiceC varchar(1000), choiceCans varchar(1000), choiceD varchar(1000),choiceDans varchar(1000))");
        System.out.println("Created table quiz1");
        psInsert = conn.prepareStatement(
                "insert into QuizType1MC values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        statements.add(psInsert);
        try {
            BufferedReader br = new BufferedReader(new FileReader("MC.javaproject.txt"));//read a file
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                StringTokenizer quiz = new StringTokenizer(sCurrentLine, ",");
                psInsert.setString(1, quiz.nextToken());
                psInsert.setString(2, quiz.nextToken());
                psInsert.setString(3, quiz.nextToken());
                psInsert.setString(4, quiz.nextToken());
                psInsert.setString(5, quiz.nextToken());
                psInsert.setString(6, quiz.nextToken());
                psInsert.setString(7, quiz.nextToken());
                psInsert.setString(8, quiz.nextToken());
                psInsert.setString(9, quiz.nextToken());
                psInsert.setString(10, quiz.nextToken());
                psInsert.setString(11, quiz.nextToken());
                psInsert.executeUpdate();

            }
            System.out.println("Updated quiz1");

        } catch (IOException e) {
            e.printStackTrace();
        }

        s.execute("create table QuizType2FIB(QType varchar(10), Qlevel varchar(10), question varchar(1000),answer varchar(230))");
        psInsert = conn.prepareStatement("insert into QuizType2FIB values (?, ?, ?, ?)");//insert into the table
        System.out.println("Created table quiz2");
        statements.add(psInsert);

        try {

            BufferedReader br = new BufferedReader(new FileReader("FIB.txt"));
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                StringTokenizer quiz = new StringTokenizer(sCurrentLine, ",");
                psInsert.setString(1, quiz.nextToken());
                psInsert.setString(2, quiz.nextToken());
                psInsert.setString(3, quiz.nextToken());
                psInsert.setString(4, quiz.nextToken());
                psInsert.executeUpdate();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        s.execute("create table QuizType3TF(QType varchar(10), Qlevel varchar(10), question varchar(1000),answer varchar(30))");
        psInsert = conn.prepareStatement("insert into QuizType3TF values (?, ?, ?, ?)");
        System.out.println("Created table quiz3");
        statements.add(psInsert);

        try {

            BufferedReader br = new BufferedReader(new FileReader("TF.javaproject.txt"));
            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {

                StringTokenizer quiz = new StringTokenizer(sCurrentLine, ",");
                psInsert.setString(1, quiz.nextToken());
                psInsert.setString(2, quiz.nextToken());
                psInsert.setString(3, quiz.nextToken());
                psInsert.setString(4, quiz.nextToken());
                psInsert.executeUpdate();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        s.execute("create table QuizType4MA(QType varchar(10), Qlevel varchar(10), question varchar(1000), choiceA varchar(1000), choiceAans varchar(1000), choiceB varchar(1000), choiceBans varchar(1000), choiceC varchar(1000), choiceCans varchar(1000), choiceD varchar(1000),choiceDans varchar(1000))");
        psInsert = conn.prepareStatement("insert into QuizType4MA values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        System.out.println("Created table quiz4");
        statements.add(psInsert);

        try {

            BufferedReader br = new BufferedReader(new FileReader("MA.java project.txt"));
            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {

                StringTokenizer quiz = new StringTokenizer(sCurrentLine, ",");
                psInsert.setString(1, quiz.nextToken());
                psInsert.setString(2, quiz.nextToken());
                psInsert.setString(3, quiz.nextToken());
                psInsert.setString(4, quiz.nextToken());
                psInsert.setString(5, quiz.nextToken());
                psInsert.setString(6, quiz.nextToken());
                psInsert.setString(7, quiz.nextToken());
                psInsert.setString(8, quiz.nextToken());
                psInsert.setString(9, quiz.nextToken());
                psInsert.setString(10, quiz.nextToken());
                psInsert.setString(11, quiz.nextToken());
                psInsert.executeUpdate();

            }
            String query = "Select * from QuizType4MA";
            rs = s.executeQuery(query);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *This method throws exceptions
     * @throws FileNotFoundException
     * @throws SQLException
     */
    public static void updateDb() throws FileNotFoundException, SQLException {
    }
}
