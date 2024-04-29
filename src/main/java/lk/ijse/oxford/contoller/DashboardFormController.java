package lk.ijse.oxford.contoller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.oxford.model.tm.StudentTm;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DashboardFormController {
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    @FXML
    private TableColumn<?,?> colSubjectNo;
    @FXML
    private TableColumn<?,?> colSubject;
    @FXML
    private TableColumn<?,?> colTimeFrom;
    @FXML
    private TableColumn<?,?> colTimeTo;
    @FXML
    private TableColumn<?,?> colHallId;
    @FXML
    private BarChart bcStudentChart;
    @FXML
    private TableView<StudentTm>tblSchedule;
    @FXML
    public AnchorPane rootNode;

    public void initialize(){
        setDate();
        setCellFactory();
    }

    private void setCellFactory() {
        colSubjectNo.setCellValueFactory(new PropertyValueFactory<>("No"));
        colSubject.setCellValueFactory(new PropertyValueFactory<>("Subject"));
        colTimeFrom.setCellValueFactory(new PropertyValueFactory<>("From"));
        colTimeTo.setCellValueFactory(new PropertyValueFactory<>("To"));
        colHallId.setCellValueFactory(new PropertyValueFactory<>("Hall Id"));
    }

    private void setDate() {
        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("EEEE(dd)-MMM-yyyy");
        String formattedTime = time.format(formatter);
        String formattedDate = date.format(formatter1);

        lblDate.setText(formattedDate);
        lblTime.setText(formattedTime);
    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        Parent  rootNode = FXMLLoader.load(this.getClass().getResource("/view/employee_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Employee Form");

        stage.show();
    }

    public void btnStudentOnAction(ActionEvent actionEvent) throws IOException {
        Parent  rootNode = FXMLLoader.load(this.getClass().getResource("/view/student_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Student");

        stage.show();
    }

    public void btnEquipmentOnAction(ActionEvent actionEvent) throws IOException {

        Parent  rootNode = FXMLLoader.load(this.getClass().getResource("/view/equipment_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Equipment Form");

        stage.show();
    }

    public void btnUserOnAction(ActionEvent actionEvent) throws IOException {
        Parent  rootNode = FXMLLoader.load(this.getClass().getResource("/view/user_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("User Form");

        stage.show();
    }

    public void btnBackwardOnAction(ActionEvent actionEvent) throws IOException {
        System.exit(0);
    }
}
