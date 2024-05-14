package lk.ijse.oxford.contoller.employee_form_controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.oxford.model.Employee;
import lk.ijse.oxford.model.tm.EmployeeTm;
import lk.ijse.oxford.repository.EmployeRepo;
import lk.ijse.oxford.util.Regex;
import lk.ijse.oxford.util.TextFields;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddEmployeeFormContoller {
    @FXML
    private JFXTextField txtEmployeeName;
    @FXML
    private JFXTextField txtContactNumber;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtEmpId;
    @FXML
    private JFXTextField txtUserId;
    @FXML
    private JFXTextField txtEmpType;
    @FXML
    private TableColumn<?,?> colEmpId;
    @FXML
    private TableColumn<?,?>colUserId;
    @FXML
    private TableColumn<?,?>colEmpName;
    @FXML
    private TableColumn<?,?>colContact;
    @FXML
    private TableColumn<?,?>colEmpAddress;
    @FXML
    private TableColumn<?,?>colEmpType;
    @FXML
    private TableView<EmployeeTm> tblEmployee;
    private List<Employee> employeeList = new ArrayList<>();

    public void initialize(){
        this.employeeList = getAllEmployees();
        setCellValueFactory();
        loadEmployeeTable();
    }


    private List<Employee> getAllEmployees() {
        List<Employee> employeeList = null;
        try {
            employeeList = EmployeRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeeList;
    }

    private void loadEmployeeTable() {
        ObservableList<EmployeeTm> tmList = FXCollections.observableArrayList();

        for (Employee employee : employeeList) {
            EmployeeTm employeeTm = new EmployeeTm(
                    employee.getEmpId(),
                    employee.getUserId(),
                    employee.getName(),
                    employee.getContact(),
                    employee.getAddress(),
                    employee.getType()
            );

            tmList.add(employeeTm);
        }
        tblEmployee.setItems(tmList);
    }

    private void setCellValueFactory() {
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmpType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmpAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }
    public void btnEmpAddOnAction(ActionEvent actionEvent) {
       if (isValidate()){
           String id = txtEmpId.getText();
           String name = txtEmployeeName.getText();
           String address = txtAddress.getText();
           String tel = txtContactNumber.getText();
           String type = txtEmpType.getText();
           String userId = txtUserId.getText();

           Employee employee = new Employee(id, name, tel,address,type,userId);

           try {
               boolean isSaved = EmployeRepo.save(employee);
               if (isSaved) {
                   new Alert(Alert.AlertType.CONFIRMATION, "Employee Data Saved!").show();
                   initialize();
                   txtEmployeeName.setText("");
                   txtContactNumber.setText("");
                   txtAddress.setText("");
                   txtEmpId.setText("");
                   txtEmpType.setText("");
                   txtUserId.setText("");
               }
           } catch (SQLException e) {
               new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
           }
       }
    }

    public boolean isValidate(){
        if(!Regex.setTextColor(TextFields.NAME,txtEmployeeName))return false;
        if(!Regex.setTextColor(TextFields.EID,txtEmpId))return false;
        if(!Regex.setTextColor(TextFields.ADDRESS,txtAddress))return false;
        if(!Regex.setTextColor(TextFields.CONTACT,txtContactNumber))return false;
        if(!Regex.setTextColor(TextFields.USERID,txtUserId))return false;
        if(!Regex.setTextColor(TextFields.TYPE,txtEmpType))return false;
        return true;
    }

    public void txtEmpNameCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME,txtEmployeeName);
    }

    public void txtContactCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.CONTACT,txtContactNumber);
    }

    public void txtAddressCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.ADDRESS,txtAddress);
    }

    public void txtEmpIdCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.EID,txtEmpId);
    }

    public void txtUserIdCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.USERID,txtUserId);
    }

    public void txtEmpTypeCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.TYPE,txtEmpType);
    }
}
