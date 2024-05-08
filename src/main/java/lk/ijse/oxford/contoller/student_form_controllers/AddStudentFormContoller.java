package lk.ijse.oxford.contoller.student_form_controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.oxford.model.Student;
import lk.ijse.oxford.model.tm.StudentTm;
import lk.ijse.oxford.repository.StudentRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddStudentFormContoller {
    @FXML
    private TextField txtStudentName;
    @FXML
    private TextField txtStudentId;
    @FXML
    private TextField txtContactNumber;
    @FXML
    private TextField txtUserId;
    @FXML
    private TextField txtGrade;
    @FXML
    private TextField txtAddress;
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
        String id = txtStudentId.getText();
        String name = txtStudentName.getText();
        String address = txtAddress.getText();
        String tel = txtContactNumber.getText();
        String grade = txtGrade.getText();
        String userId = txtUserId.getText();

        Student student = new Student(id, grade,name , tel,address,userId);
        System.out.println(student.toString());

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
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void btnStudentRefreshOnAction(ActionEvent actionEvent) {
        initialize();
    }
}
