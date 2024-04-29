package lk.ijse.oxford.contoller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentFormController implements Initializable {
    @FXML
    private Label lblDate;
    @FXML
    private StackPane container;
    @FXML
    private BorderPane bdStudent;

    public void initialize(){
        setDate();
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblDate.setText(String.valueOf(now));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
          Parent fxml = FXMLLoader.load(getClass().getResource("/view/student_forms/add_student_form.fxml"));
            container.getChildren().removeAll();
            container.getChildren().setAll(fxml);
        } catch (IOException e) {
            Logger.getLogger(ModuleLayer.Controller.class.getName()).log(Level.SEVERE,null,e);
        }

    }

    public void btnStudentAddOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/student_forms/add_student_form.fxml"));
        container.getChildren().removeAll();
        container.getChildren().setAll(fxml);
    }

    public void btnStudentDeleteOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/student_forms/delete_student_form.fxml"));
        container.getChildren().removeAll();
        container.getChildren().setAll(fxml);
    }

    public void btnStudentEditOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/student_forms/edit_student_form.fxml"));
        container.getChildren().removeAll();
        container.getChildren().setAll(fxml);
    }

    public void btnStudentAttendanceOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/student_forms/student_attendance.fxml"));
        container.getChildren().removeAll();
        container.getChildren().setAll(fxml);
    }

    public void btnStudentFeesOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/student_forms/student_fees.fxml"));
        container.getChildren().removeAll();
        container.getChildren().setAll(fxml);
    }

    public void btnStudentToDashOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage = (Stage) bdStudent.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }

    public void btnExit(ActionEvent actionEvent) {
        System.exit(0);
    }
}
