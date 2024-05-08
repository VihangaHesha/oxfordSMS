package lk.ijse.oxford.contoller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.oxford.model.TimeTable;
import lk.ijse.oxford.model.tm.TimeTableTm;
import lk.ijse.oxford.repository.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HomeFormController {
    @FXML
    private Label lblDates;
    @FXML
    private Label lblTimes;
    @FXML
    private Label lblStudentCount;
    private int studentCount;
    @FXML
    private Label lblEmployeeCount;
    private int employeeCount;
    @FXML
    private Label lblEquipCount;
    private int equipmentCount;
    @FXML
    private Label lblTotalSalary;
    private int totalSalary;
    @FXML
    private Label lblTotalPayment;
    @FXML
    private TableColumn<TimeTableTm,String> colSubjectNo;
    @FXML
    private TableColumn<TimeTableTm,String> colSubject;
    @FXML
    private TableColumn<TimeTableTm,String> colTimePeriod;
    @FXML
    private TableColumn<TimeTableTm, Date> colDate;

    @FXML
    private BarChart bcStudentChart;
    @FXML
    private TableView<TimeTableTm> tblSchedule;
    @FXML
    private List<TimeTable> timeTableList = new ArrayList<>();

    public void initialize(){
        this.timeTableList = getTimeTable();
        setDate();
        setCellFactory();
        loadTimeTable();
        try {
            studentCount = StudentRepo.getStudentCount();
            employeeCount = EmployeRepo.getEmployeeCount();
            equipmentCount = EquipmentRepo.getEquipmentCount();
            totalSalary = SalaryRepo.getTotalSalary();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        setStudentCount(studentCount);
        setEmployeeCount(employeeCount);
        setEquipmentCount(equipmentCount);
        setTotalSalary(totalSalary);
    }

    private void setTotalSalary(int totalSalary) {
        lblTotalSalary.setText(String.valueOf("Rs."+totalSalary+"/="));
    }

    private void setEquipmentCount(int equipmentCount) {
        lblEquipCount.setText(String.valueOf(equipmentCount));
    }

    private void setEmployeeCount(int employeeCount) {
        lblEmployeeCount.setText(String.valueOf(employeeCount));
    }

    private void setStudentCount(int studentCount) {
        lblStudentCount.setText(String.valueOf(studentCount));
    }

    private void loadTimeTable() {
        ObservableList<TimeTableTm> tmList = FXCollections.observableArrayList();

        for (TimeTable tb : timeTableList) {
            TimeTableTm tableTm = new TimeTableTm(
                    tb.getTimeId(),
                    tb.getTimePeriod(),
                    tb.getSubject(),
                    tb.getDate()
            );

            tmList.add(tableTm);
        }
        tblSchedule.setItems(tmList);
        TimeTableTm selectedItem = tblSchedule.getSelectionModel().getSelectedItem();
    }

    private List<TimeTable> getTimeTable() {
        List<TimeTable> timeTableList = null;
        try {
            timeTableList = TimeTableRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return timeTableList;
    }

    private void setCellFactory() {
        colSubjectNo.setCellValueFactory(new PropertyValueFactory<>("timeId"));
        colTimePeriod.setCellValueFactory(new PropertyValueFactory<>("timePeriod"));
        colSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

    }
    private void setDate() {
        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("EEEE(dd)-MMM-yyyy");
        String formattedTime = time.format(formatter);
        String formattedDate = date.format(formatter1);

        lblDates.setText(formattedDate);
        lblTimes.setText(formattedTime);
    }

}
