package lk.ijse.oxford.contoller.student_form_controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.oxford.model.Student;
import lk.ijse.oxford.model.tm.StudentTm;
import lk.ijse.oxford.repository.StudentRepo;
import lk.ijse.oxford.util.Regex;
import lk.ijse.oxford.util.TextFields;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddStudentFormContoller {
    @FXML
    private JFXTextField txtStudentName;
    @FXML
    private JFXTextField txtStudentId;
    @FXML
    private JFXTextField txtContactNumber;
    @FXML
    private JFXTextField txtUserId;
    @FXML
    private JFXTextField txtGrade;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private TableColumn<?,?>colStId;
    @FXML
    private TableColumn<?,?>colUserId;
    @FXML
    private TableColumn<?,?>colStName;
    @FXML
    private TableColumn<?,?>colContact;
    @FXML
    private TableColumn<?,?>colStAddress;
    @FXML
    private TableColumn<?,?>colStGrade;
    @FXML
    private TableView<StudentTm>tblStudent;
    private List<Student> studentList = new ArrayList<>();

    public void initialize(){
        this.studentList = getAllStudents();
        setCellValueFactory();
        loadStudentTable();
    }

    private List<Student> getAllStudents() {
        List<Student> customerList = null;
        try {
            customerList = StudentRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerList;
    }

    private void loadStudentTable() {
        ObservableList<StudentTm> tmList = FXCollections.observableArrayList();

        for (Student student : studentList) {
            StudentTm customerTm = new StudentTm(
                    student.getStId(),
                    student.getUserId(),
                    student.getName(),
                    student.getContact(),
                    student.getAddress(),
                    student.getGrade()
            );

            tmList.add(customerTm);
        }
        tblStudent.setItems(tmList);
    }

    private void setCellValueFactory() {
        colStId.setCellValueFactory(new PropertyValueFactory<>("stId"));
        colStName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colStGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colStAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }

    public void btnStudentAddOnAction(ActionEvent actionEvent) {
        if (isValidate()){
            String id = txtStudentId.getText();
            String name = txtStudentName.getText();
            String address = txtAddress.getText();
            String tel = txtContactNumber.getText();
            String grade = txtGrade.getText();
            String userId = txtUserId.getText();

            Student student = new Student(id, grade,name , tel,address,userId);

            try {
                boolean isSaved = StudentRepo.save(student);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Student Data Saved!").show();
                    txtStudentName.setText("");
                    txtContactNumber.setText("");
                    txtAddress.setText("");
                    txtStudentId.setText("");
                    txtGrade.setText("");
                    txtUserId.setText("");
                    initialize();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    public boolean isValidate(){
        if(!Regex.setTextColor(TextFields.NAME,txtStudentName))return false;
        if(!Regex.setTextColor(TextFields.SID,txtStudentId))return false;
        if(!Regex.setTextColor(TextFields.ADDRESS,txtAddress))return false;
        if(!Regex.setTextColor(TextFields.CONTACT,txtContactNumber))return false;
        if(!Regex.setTextColor(TextFields.USERID,txtUserId))return false;
        if(!Regex.setTextColor(TextFields.GRADE,txtGrade))return false;
        return true;
    }

    public void txtGradeCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.GRADE,txtGrade);
    }

    public void txtUserIdCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.USERID,txtUserId);
    }

    public void txtStIdCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.SID,txtStudentId);
    }

    public void txtAddressCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.ADDRESS,txtAddress);
    }

    public void txtStContactOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.CONTACT,txtContactNumber);
    }

    public void txtStNameCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME,txtStudentName);
    }
}
