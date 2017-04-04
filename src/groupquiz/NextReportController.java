/*
 *Program to display the piechart quartery score
 */
package groupquiz;

import com.itextpdf.text.Document;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import static groupquiz.ExamDb.conn;
import static groupquiz.InstructorController.conn1;
import static groupquiz.testSelectionController.acount_e;
import static groupquiz.testSelectionController.acount_h;
import static groupquiz.testSelectionController.acount_m;
import static groupquiz.UpdateTable.score_e;
import static groupquiz.UpdateTable.score_h;
import static groupquiz.UpdateTable.score_m;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import java.sql.ResultSetMetaData;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import java.sql.PreparedStatement;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author sharanya
 */
public class NextReportController implements Initializable {

    private String framework = "network";
    private String protocol = "jdbc:derby://localhost:1527/";
    private XYChart.Series<String, Integer> series3;
    @FXML
    private BarChart bar12;
    @FXML
    private AnchorPane pane;
    @FXML
    private Button printbutton;
    @FXML
    private Button logout;

    /**
     * Initializes the controller class.
     *
     * @param url-connecting using the url
     * @param rb-connect using the resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            Statement s = null;
            ResultSet rs = null;
            ResultSet rs1 = null;
            Properties props = new Properties();
            props.put("user", "sai");
            props.put("password", "sai");
            String dbName = "Test"; // the name of the database
            conn1 = DriverManager.getConnection(protocol + dbName
                    + ";create=true", props);
            System.out.println("Connected to and created database " + dbName);
            String query = "Select * from student";
            s = conn1.createStatement();
            // giving queries and accessing data according to number of months
            String query11 = "Select count(name)as nrow from Student where month between 1 and 3";
            String query12 = "Select count(name)as nrow from Student where month between 4 and 6";
            String query13 = "Select count(name)as nrow from Student where month between 7 and 9";
            String query14 = "Select count(name)as nrow from Student where month between 10 and 12";
            String query15 = "Select count(name)as nrow from Student where month between 1 and 12";

            int numtestsq1 = 0, numtestsq2 = 0, numtestsq3 = 0, numtestsq4 = 0, numtestsyear = 0;
            // executing the queries and giving them in the result set
            try {
                rs = s.executeQuery(query11);

                while (!rs.next()) {
                    break;

                }
                numtestsq1 = rs.getInt("nrow");
            } finally {
                rs.close();
            }

            try {
                rs = s.executeQuery(query12);

                while (!rs.next()) {
                    break;

                }
                numtestsq2 = rs.getInt("nrow");
            } finally {
                rs.close();
            }

            try {
                rs = s.executeQuery(query13);
                while (!rs.next()) {
                    break;

                }
                numtestsq3 = rs.getInt("nrow");
            } finally {
                rs.close();
            }

            try {
                rs = s.executeQuery(query14);
                while (!rs.next()) {
                    break;

                }
                numtestsq4 = rs.getInt("nrow");
            } finally {
                rs.close();
            }
            try {
                rs = s.executeQuery(query15);
                while (!rs.next()) {
                    break;

                }
                numtestsyear = rs.getInt("nrow");
            } finally {
                rs.close();
            }
            // displaying results quarterly in the form of bar chart
            series3 = new XYChart.Series();
            series3.setName("No. of Questions-Quarterly");
            series3.getData().add(new XYChart.Data("Q1", numtestsq1));
            series3.getData().add(new XYChart.Data("Q2", numtestsq2));
            series3.getData().add(new XYChart.Data("Q3", numtestsq3));
            series3.getData().add(new XYChart.Data("Q4", numtestsq4));
            series3.getData().add(new XYChart.Data("Yearly", numtestsyear));

            bar12.getData().addAll(series3);

        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(testSelectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
    }
/**
 * Prints pdf reports of student performances
 * @param event
 * @throws IOException 
 */
    @FXML
    private void printer(MouseEvent event) throws IOException {
         WritableImage image = pane.snapshot(new SnapshotParameters(), null);
        File file = new File("Performance Report2.png");
        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        Document document = new Document();
        String input = "Performance Report2.png"; // .gif and .jpg are ok too!
        String output = "Performance Report2.pdf";
        try {
            FileOutputStream fos = new FileOutputStream(output);
            com.itextpdf.text.pdf.PdfWriter writer = com.itextpdf.text.pdf.PdfWriter.getInstance(document, fos);
            writer.open();
            document.open();
            ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", byteOutput);
            com.itextpdf.text.Image graph;
            graph = com.itextpdf.text.Image.getInstance(byteOutput.toByteArray());
            graph.scaleToFit(500, 500);
            document.add((com.itextpdf.text.Element) graph);
            document.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        pane.setVisible(true);
    }

    @FXML
    private void printe(DragEvent event) {
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
