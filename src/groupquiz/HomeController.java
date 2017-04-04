/*
 * This program controlls the home fxml screen
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;


/**
 * FXML Controller class
 *
 * @author sharanya
 */
public class HomeController implements Initializable {

    @FXML
    private AnchorPane btn2;// creates the anchorpane
    @FXML
    private Button button1;//creates button 1
    @FXML
    private Button button2;//creates button 2

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    // TODO
     btn2.autosize();
    }    

    @FXML
        private void handleTeacher(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TeacherScreen.fxml"));// creates the teacher login screen
        Stage stage = (Stage) btn2.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Event1");

    }

    @FXML
        private void handleStudent(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StudentScreen.fxml"));// creates the student login screen
        Stage stage = (Stage) btn2.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Event1");
        String fileName = "PdfWithtext.pdf"; // name of our file
        PDDocument doc = new PDDocument();
        PDPage page = new PDPage();
        doc.addPage(page);
        PDPageContentStream content = new PDPageContentStream(doc, page);
        content.beginText();
        content.newLineAtOffset(100, 700);
        PDFont font = PDType1Font.HELVETICA_BOLD;
        content.setFont(font, 12);
        content.showText("Printing to new file");
        content.endText();
        content.close();
 }
 }
