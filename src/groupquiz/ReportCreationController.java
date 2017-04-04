/*
 * Program to display the pie and bar chart based on the overall performance of the student
 */
package groupquiz;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.PieChart;
import java.util.*;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import java.io.*;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import java.util.Arrays;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.print.PrinterJob;
import javafx.scene.layout.AnchorPane;
import com.itextpdf.text.Document;
import static groupquiz.testSelectionController.correct_answers;
import static groupquiz.testSelectionController.q_no;
import static groupquiz.testSelectionController.user_level;
import static groupquiz.testSelectionController.count_e;
import static groupquiz.testSelectionController.count_h;
import static groupquiz.testSelectionController.count_m;
import static groupquiz.ExamDb.conn;
import static groupquiz.ExamDb.statements;
import static groupquiz.ExamDb.s;
import static groupquiz.ExamDb.rs;
import static groupquiz.ExamDb.psInsert;
import static groupquiz.ExamDb.psUpdate;
import static groupquiz.testSelectionController.acount_e;
import static groupquiz.testSelectionController.acount_h;
import static groupquiz.testSelectionController.acount_m;
import static groupquiz.testSelectionController.q_no;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author rituparnaj
 */
public class ReportCreationController implements Initializable {

    @FXML
    private PieChart piechart;
    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private AnchorPane mainpane;
    private XYChart.Series<String, Integer> series1;
    @FXML
    private ImageView logout;

    @FXML
    private void printer(ActionEvent event) throws IOException {
        WritableImage image = mainpane.snapshot(new SnapshotParameters(), null);
        File file = new File("Dashboard Report.png");
        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        Document document = new Document();
        String input = "Dashboard Report.png"; // .gif and .jpg are ok too!
        String output = "Dashboard Report.pdf";
        try {
            FileOutputStream fos = new FileOutputStream(output);
            PdfWriter writer = PdfWriter.getInstance(document, fos);
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
        mainpane.setVisible(true);
        }

    private void printing(ActionEvent event) {

        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList();
        piechart.setTitle("");
        piechart.setData(pieChartData);
    }

    /**
     * Initializes the controller class.
     *
     * @param url to initialize
     * @param rb   to initialize
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
        
            new UpdateTable().update();
            
        } catch (SQLException ex) {
            Logger.getLogger(ReportCreationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // creating the pie char
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Correct", correct_answers),
                new PieChart.Data("Incorrect", q_no - correct_answers));

        piechart.setData(pieChartData);
        //Creating the bar chart
        ObservableList<BarChart.Data<String, Integer>> barChartData = FXCollections.observableArrayList(
                new BarChart.Data("Easy", (acount_e)),
                new BarChart.Data("Medium", (acount_m)),
                new BarChart.Data("Hard", (acount_h)));
        series1 = new XYChart.Series();
        series1.setName("Correct Answers");
        series1.setData(barChartData);
        barChart.getData().addAll(series1);

    }
/**
 * Logs user out and takes user to main screen
 * @param event
 * @throws IOException 
 */
    @FXML
    private void logout(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Stage stage = (Stage) logout.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
