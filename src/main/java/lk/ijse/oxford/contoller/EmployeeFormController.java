package lk.ijse.oxford.contoller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeFormController implements Initializable {
    @FXML
    private StackPane employeeContainer;
    @FXML
    private BorderPane bdEmployee;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/view/employee_forms/add_employee_form.fxml"));
            employeeContainer.getChildren().removeAll();
            employeeContainer.getChildren().setAll(fxml);
        } catch (IOException e) {
            Logger.getLogger(ModuleLayer.Controller.class.getName()).log(Level.SEVERE,null,e);
        }

    }

    public void btnEmployeeAddOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/employee_forms/add_employee_form.fxml"));

        employeeContainer.getChildren().removeAll();
        employeeContainer.getChildren().setAll(fxml);
    }

    public void btnEmployeeDeleteOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/employee_forms/delete_employee_form.fxml"));
        employeeContainer.getChildren().removeAll();
        employeeContainer.getChildren().setAll(fxml);
    }

    public void btnEmployeeEditOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/employee_forms/edit_employee_form.fxml"));
        employeeContainer.getChildren().removeAll();
        employeeContainer.getChildren().setAll(fxml);
    }

    public void btnEmployeeSalaryOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/employee_forms/employee_salary_form.fxml"));
        employeeContainer.getChildren().removeAll();
        employeeContainer.getChildren().setAll(fxml);
    }

}
