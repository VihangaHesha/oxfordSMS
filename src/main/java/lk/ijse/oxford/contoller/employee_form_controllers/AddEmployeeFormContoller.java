package lk.ijse.oxford.contoller.employee_form_controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.oxford.model.Employee;
import lk.ijse.oxford.model.tm.EmployeeTm;
import lk.ijse.oxford.repository.EmployeRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddEmployeeFormContoller {
    @FXML
    private TextField txtEmployeeName;
    @FXML
    private TextField txtContactNumber;
    @FXML
    private TextArea txtAddress;
    @FXML
    private TextField txtEmpId;
    @FXML
    private TextField txtUserId;
    @FXML
    private TextField txtEmpType;
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
        String id = txtEmpId.getText();
        String name = txtEmployeeName.getText();
        String address = txtAddress.getText();
        String tel = txtContactNumber.getText();
        String type = txtEmpType.getText();
        String userId = txtUserId.getText();

        Employee employee = new Employee(id, name, tel,address,type,userId);
        System.out.println(employee.toString());

        try {
            boolean isSaved = EmployeRepo.save(employee);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Data Saved!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
