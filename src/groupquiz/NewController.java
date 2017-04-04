/*
 * Thgis program displays the charts and scores in the form of graphs according to the score
 */
package groupquiz;

import com.itextpdf.text.Document;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author sharanya
 */
public class NewController implements Initializable {

    private String framework = "network";
    private String protocol = "jdbc:derby://localhost:1527/";
    @FXML
    private PieChart pie5;
    @FXML
    private BarChart barchart;
    @FXML
    private BarChart barchart1;
    @FXML
    private BarChart barchart11;

    private XYChart.Series<String, Integer> series1;
    private XYChart.Series<String, Integer> series2;
    private XYChart.Series<String, Integer> series3;
    private XYChart.Series<String, Integer> series4;
    @FXML
    private Button nextreport;
    @FXML
    private AnchorPane pane;
    @FXML
    private Button print;

    /**
     * Initializes the controller class using the url and password
     *
     * @param url
     * @param rb
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
                    + ";create=true", props);//connected to the database
           // System.out.println("Connected to and created database " + dbName);
            String query = "Select * from student";
            s = conn1.createStatement();
            //giving different queries to access data from the table
            String query1 = "Select count(*) as nrow from Student where grade='pass'and month between 1 and 3";
            String query2 = "Select count(*) as nrow from Student where grade='fail'and month between 1 and 3";
            String query3 = "Select count(*) as nrow from Student where grade='pass'and month between 4 and 6";
            String query4 = "Select count(*) as nrow from Student where grade='fail'and month between 4 and 6";
            String query5 = "Select count(*) as nrow from Student where grade='pass'and month between 7 and 9";
            String query6 = "Select count(*) as nrow from Student where grade='fail'and month between 7 and 9";
            String query7 = "Select count(*) as nrow from Student where grade='pass'and month between 10 and 12";
            String query8 = "Select count(*) as nrow from Student where grade='fail'and month between 10 and 12";
            String query9 = "Select count(*) as nrow from Student where grade='pass'and month between 1 and 12";
            String query10 = "Select count(*) as nrow from Student where grade='fail'and month between 1 and 12";
            String query11 = "Select count(name)as nrow from Student where month between 1 and 3";
            String query12 = "Select count(name)as nrow from Student where month between 4 and 6";
            String query13 = "Select count(name)as nrow from Student where month between 7 and 9";
            String query14 = "Select count(name)as nrow from Student where month between 10 and 12";
            String query15 = "Select count(name)as nrow from Student where month between 1 and 12";
            // calculate the average score
            String query16 = "Select avg(score) as nrow from Student where month between 1 and 3";
            String query17 = "Select avg(score) as nrow from Student where month between 4 and 6";
            String query18 = "Select avg(score) as nrow from Student where month between 7 and 9";
            String query19 = "Select avg(score) as nrow from Student where month between 10 and 12";
            String query20 = "Select avg(score) as nrow from Student where month between 1 and 12";
            String query21 = "Select count(easyscore) as nrow from Student";
            String query22 = "Select count(medscore) as nrow from Student";
            String query23 = "Select count(hardscore) as nrow from Student";

            int numPass = 0, numFail = 0, numpass1 = 0, numfail1 = 0, numpass2 = 0, numfail2 = 0, numpass3 = 0, numfail3 = 0, numpassyear = 0, numfailyear = 0;
            int numtestsq1 = 0, numtestsq2 = 0, numtestsq3 = 0, numtestsq4 = 0, numtestsyear = 0;
            int avgscoreq1 = 0, avgscoreq2 = 0, avgscoreq3, avgscoreq4 = 0, avgscoreyear = 0;
            int easyscore = 0, medscore = 0, hardscore = 0;
            try {
                rs = s.executeQuery(query1);

                while (!rs.next()) {
                    break;

                }
                numPass = rs.getInt("nrow");
            } finally {
                rs.close();
            }

            try {
                rs = s.executeQuery(query2);

                while (!rs.next()) {
                    break;

                }
                numFail = rs.getInt("nrow");

            } finally {
                rs.close();
            }

            try {
                rs = s.executeQuery(query3);

                while (!rs.next()) {
                    break;

                }
                numpass1 = rs.getInt("nrow");
            } finally {
                rs.close();
            }

            try {
                rs = s.executeQuery(query4);
                while (!rs.next()) {
                    break;
                }
                numfail1 = rs.getInt("nrow");
            } finally {
                rs.close();
            }
            try {
                rs = s.executeQuery(query5);
                while (!rs.next()) {
                    break;
                }
                numpass2 = rs.getInt("nrow");
            } finally {
                rs.close();
            }
            try {
                rs = s.executeQuery(query6);
                while (!rs.next()) {
                    break;
                }
                numfail2 = rs.getInt("nrow");
            } finally {
                rs.close();
            }
            try {
                rs = s.executeQuery(query7);
                while (!rs.next()) {
                    break;
                }
                numpass3 = rs.getInt("nrow");
            } finally {
                rs.close();
            }
            try {
                rs = s.executeQuery(query8);
                while (!rs.next()) {
                    break;
                }
                numfail3 = rs.getInt("nrow");
            } finally {
                rs.close();
            }

            try {
                rs = s.executeQuery(query9);
                while (!rs.next()) {
                    break;
                }
                numpassyear = rs.getInt("nrow");
            } finally {
                rs.close();
            }
            try {
                rs = s.executeQuery(query10);
                while (!rs.next()) {
                    break;
                }
                numfailyear = rs.getInt("nrow");
            } finally {
                rs.close();
            }
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

                //ArrayList<Integer> arrayList = new ArrayList<Integer>();
                while (!rs.next()) {
                    break;

                }
                numtestsq4 = rs.getInt("nrow");
            } finally {
                rs.close();
            }

            try {
                rs = s.executeQuery(query15);

                //ArrayList<Integer> arrayList = new ArrayList<Integer>();
                while (!rs.next()) {
                    break;

                }
                numtestsyear = rs.getInt("nrow");
            } finally {
                rs.close();
            }
            try {
                rs = s.executeQuery(query16);

                while (!rs.next()) {
                    break;

                }
                avgscoreq1 = rs.getInt("nrow");
            } finally {
                rs.close();
            }
            try {
                rs = s.executeQuery(query17);
                while (!rs.next()) {
                    break;
                }
                avgscoreq2 = rs.getInt("nrow");
            } finally {
                rs.close();
            }
            try {
                rs = s.executeQuery(query18);
                while (!rs.next()) {
                    break;

                }
                avgscoreq3 = rs.getInt("nrow");
            } finally {
                rs.close();
            }

            try {
                rs = s.executeQuery(query19);
                while (!rs.next()) {
                    break;
                }
                avgscoreq4 = rs.getInt("nrow");
            } finally {
                rs.close();
            }

            try {
                rs = s.executeQuery(query20);
                while (!rs.next()) {
                    break;
                }
                avgscoreyear = rs.getInt("nrow");
            } finally {
                rs.close();
            }

            try {
                rs = s.executeQuery(query21);

                while (!rs.next()) {
                    break;

                }
                easyscore = rs.getInt("nrow");
            } finally {
                rs.close();
            }

            try {
                rs = s.executeQuery(query22);

                while (!rs.next()) {
                    break;

                }
                medscore = rs.getInt("nrow");
            } finally {
                rs.close();
            }

            try {
                rs = s.executeQuery(query23);

                while (!rs.next()) {
                    break;

                }
                hardscore = rs.getInt("nrow");
            } finally {
                rs.close();
            }

            ObservableList<PieChart.Data> pie5Data = FXCollections.observableArrayList(
                    new PieChart.Data("Pass ", numpassyear),
                    new PieChart.Data("Fail ", numfailyear));
            pie5.setData(pie5Data);

            series1 = new XYChart.Series();
            series1.setName("Pass");
            //giving data about number of students who passed the test
            series1.getData().add(new XYChart.Data("Q1", numPass));
            series1.getData().add(new XYChart.Data("Q2", numpass1));
            series1.getData().add(new XYChart.Data("Q3", numpass2));
            series1.getData().add(new XYChart.Data("Q4", numpass3));

            series2 = new XYChart.Series();
            series2.setName("Fail");
            // giving data on number of students who failed the test
            series2.getData().add(new XYChart.Data("Q1", numFail));
            series2.getData().add(new XYChart.Data("Q2", numfail1));
            series2.getData().add(new XYChart.Data("Q3", numfail2));
            series2.getData().add(new XYChart.Data("Q4", numfail3));

            barchart.getData().addAll(series1, series2);

            series3 = new XYChart.Series();
            series3.setName("Avg. Score");
            //giving data on average score
            series3.getData().add(new XYChart.Data("Q1", avgscoreq1));
            series3.getData().add(new XYChart.Data("Q2", avgscoreq2));
            series3.getData().add(new XYChart.Data("Q3", avgscoreq3));
            series3.getData().add(new XYChart.Data("Q4", avgscoreq4));
            series3.getData().add(new XYChart.Data("Yearly", avgscoreyear));

            barchart1.getData().addAll(series3);

            series4 = new XYChart.Series();
            series4.setName("");
              //giving data on all types of score
            series4.getData().add(new XYChart.Data("Easy", easyscore));
            series4.getData().add(new XYChart.Data("Medium", medscore));
            series4.getData().add(new XYChart.Data("Hard", hardscore));

            barchart11.getData().addAll(series4);

        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(testSelectionController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
/**
 * Moves to the next screen
 * @param event
 * @throws IOException 
 */
    // TODO
    @FXML
    private void nextReport(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("nextReport.fxml"));
        Stage stage = (Stage) nextreport.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void nextReport(MouseEvent event) {
    }
/**
 * Prints pdf reports of student performances
 * @param event
 * @throws IOException 
 */
    @FXML
    private void printer(MouseEvent event) throws IOException {
          WritableImage image = pane.snapshot(new SnapshotParameters(), null);
        File file = new File("Performance Report1.png");
        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        Document document = new Document();
        String input = "Performance Report1.png"; // .gif and .jpg are ok too!
        String output = "Performance Report1.pdf";
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
}
