package lk.ijse.oxford.contoller.employee_forms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.oxford.model.Employee;
import lk.ijse.oxford.model.Salary;
import lk.ijse.oxford.model.tm.SalaryTm;
import lk.ijse.oxford.repository.EmployeRepo;
import lk.ijse.oxford.repository.SalaryRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeSalaryFormController {
    @FXML
    private TextField txtEmpId;
    @FXML
    private Label lblEmpName;
    @FXML
    private Label lblSalaryAmount;
    @FXML
    private Label lblSalaryDate;
    @FXML
    private TableColumn<?,?> colEmpId;
    @FXML
    private TableColumn<?,?>colSalaryId;
    @FXML
    private TableColumn<?,?>colAmount;
    @FXML
    private TableColumn<?,?>colDate;
    @FXML
    private TableView<SalaryTm> tblSalary;
    private List<Salary> salaryList = new ArrayList<>();

    public void initialize(){
        this.salaryList = getAllSalaries();
        setCellValueFactory();
        loadEmployeeTable();
    }


    private List<Salary> getAllSalaries() {
        List<Salary> salaries = null;
        try {
            salaries = SalaryRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salaries;
    }

    private void loadEmployeeTable() {
        ObservableList<SalaryTm> tmList = FXCollections.observableArrayList();

        for (Salary salary : salaryList) {
            SalaryTm salaryTm = new SalaryTm(
                    salary.getSalaryId(),
                    salary.getAmount(),
                    salary.getDate(),
                    salary.getEmpId()
                    );

            tmList.add(salaryTm);
        }
        tblSalary.setItems(tmList);
    }

    private void setCellValueFactory() {
        colSalaryId.setCellValueFactory(new PropertyValueFactory<>("salaryId"));
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

    }
    public void btnEmpSalarySearchOnAction(ActionEvent actionEvent) {
        String empId = txtEmpId.getText();
        try {
            Salary salary = SalaryRepo.searchByCode(empId);
            System.out.println(salary.toString());
            if (salary != null) {
                Employee emp = EmployeRepo.searchByCode(empId);
                System.out.println(emp.toString());
                if (emp != null) {
                    lblEmpName.setText(emp.getName());
                    lblSalaryAmount.setText(String.valueOf(salary.getAmount()));
                    lblSalaryDate.setText(String.valueOf(salary.getDate()));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
