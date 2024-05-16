package lk.ijse.oxford.contoller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lk.ijse.oxford.model.TimeTable;
import lk.ijse.oxford.model.tm.TimeTableTm;
import lk.ijse.oxford.repository.TimeTableRepo;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DashboardFormController implements Initializable {
    @FXML
    private StackPane dashContainer;
    @FXML
    private JFXButton btnUser;

    private String userId;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/view/home_form.fxml"));
            dashContainer.getChildren().removeAll();
            dashContainer.getChildren().setAll(fxml);
        } catch (IOException e) {
            Logger.getLogger(ModuleLayer.Controller.class.getName()).log(Level.SEVERE,null,e);
        }

    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/employee_form.fxml"));
        dashContainer.getChildren().removeAll();
        dashContainer.getChildren().setAll(fxml);
    }

    public void btnStudentOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/student_form.fxml"));
        dashContainer.getChildren().removeAll();
        dashContainer.getChildren().setAll(fxml);
    }

    public void btnEquipmentOnAction(ActionEvent actionEvent) throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("/view/equipment_form.fxml"));
        dashContainer.getChildren().removeAll();
        dashContainer.getChildren().setAll(fxml);
    }

    public void btnUserOnAction(ActionEvent actionEvent ) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user_form.fxml"));
//        Parent fxml = FXMLLoader.load(getClass().getResource("/view/user_form.fxml"));
        Parent fxml = loader.load();
        UserFormController userController = new UserFormController(userId);
        loader.setController(userController);


        dashContainer.getChildren().removeAll();
        dashContainer.getChildren().setAll(fxml);
    }
    public void btnUser(String uId){
        userId =uId;
    }

    public void btnExitOnAction(ActionEvent actionEvent) throws IOException {
        System.exit(0);
    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/home_form.fxml"));
        dashContainer.getChildren().removeAll();
        dashContainer.getChildren().setAll(fxml);
    }

    public void btnClassroomOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxml = null;
        try {
            fxml = FXMLLoader.load(getClass().getResource("/view/classroom_form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dashContainer.getChildren().removeAll();
        dashContainer.getChildren().setAll(fxml);
    }
}
