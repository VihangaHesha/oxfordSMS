package lk.ijse.oxford.contoller.student_form_controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.oxford.model.Attendance;
import lk.ijse.oxford.model.Student;
import lk.ijse.oxford.model.tm.AttedanceTm;
import lk.ijse.oxford.model.tm.StudentTm;
import lk.ijse.oxford.repository.AttendanceRepo;
import lk.ijse.oxford.repository.StudentRepo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentAttendanceController {
    @FXML
    private Label lblCurrentAttendance;
    private int currentAttendance;
    @FXML
    private Label lblStudentName;
    @FXML
    private Label lblAttendDate;
    @FXML
    private Label lblAttendMark;
    @FXML
    private TextField txtStudentId;
    @FXML
    private TableColumn<?,?> colStId;
    @FXML
    private TableColumn<?,?> colStName;
    @FXML
    private TableColumn<?,?>colAttendId;
    @FXML
    private TableColumn<?,?>colAttendMark;
    @FXML
    private TableColumn<?,?>colDate;
    @FXML
    private TableView<AttedanceTm> tblAttendance;
    private List<Attendance> attendanceList = new ArrayList<>();

    public void initialize(){
        LocalDate date = LocalDate.now();
        this.attendanceList = getAllAttendance();
        setCellValueFactory();
        loadStudentTable();
        try {
            currentAttendance = AttendanceRepo.getAttendanceCount(date);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        setAttendCount(currentAttendance);
    }

    private void setAttendCount(int currentAttendance) {
        lblCurrentAttendance.setText(String.valueOf(currentAttendance));
    }

    private List<Attendance> getAllAttendance() {
        List<Attendance> attendanceList = null;
        try {
            attendanceList = AttendanceRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return attendanceList;
    }

    private void loadStudentTable() {
        ObservableList<AttedanceTm> tmList = FXCollections.observableArrayList();

        for (Attendance attendance : attendanceList) {
            AttedanceTm attedanceTm = new AttedanceTm(
                    attendance.getAttendId(),
                    attendance.getDate(),
                    attendance.getAttendMark(),
                    attendance.getStId(),
                    attendance.getName()
            );

            tmList.add(attedanceTm);
        }
        tblAttendance.setItems(tmList);
    }

    private void setCellValueFactory() {
        colAttendId.setCellValueFactory(new PropertyValueFactory<>("attendId"));
        colStName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAttendMark.setCellValueFactory(new PropertyValueFactory<>("attendMark"));
        colStId.setCellValueFactory(new PropertyValueFactory<>("stId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }
    public void btnAttendSearchOnAction(ActionEvent actionEvent) {
        String id =txtStudentId.getText();

        try {
            Attendance attendance = AttendanceRepo.searchById(id);

            if (attendance != null) {
                lblStudentName.setText(attendance.getName());
                lblAttendDate.setText(String.valueOf(attendance.getDate()));
                lblAttendMark.setText(attendance.getAttendMark());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void getStudentDetailOnAction(MouseEvent mouseEvent) {
        AttedanceTm selectedItem = tblAttendance.getSelectionModel().getSelectedItem();
        lblStudentName.setText(selectedItem.getName());
        lblAttendDate.setText(String.valueOf(selectedItem.getDate()));
        lblAttendMark.setText(selectedItem.getAttendMark());
        txtStudentId.setText(selectedItem.getStId());
    }
}
