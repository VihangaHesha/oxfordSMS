package lk.ijse.oxford.contoller.student_form_controllers;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.oxford.model.Student;
import lk.ijse.oxford.model.tm.AttedanceTm;
import lk.ijse.oxford.model.tm.StudentTm;
import lk.ijse.oxford.repository.StudentRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeleteStudentFormController {
    @FXML
    private Label lblStudentCount;
    private int studentCount;
    @FXML
    private TextField txtStudentId;
    @FXML
    private TextField txtStudentName;
    @FXML
    private TableColumn<?,?> colStId;
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
    private TableView<StudentTm> tblStudent;
    private List<Student> studentList = new ArrayList<>();

    public void initialize(){
        this.studentList = getAllStudents();
        setCellValueFactory();
        loadStudentTable();
        try {
            studentCount = StudentRepo.getStudentCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        setStudentCount(studentCount);
    }

    private void setStudentCount(int studentCount) {
        lblStudentCount.setText(String.valueOf(studentCount));
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
            StudentTm studentTm = new StudentTm(
                    student.getStId(),
                    student.getUserId(),
                    student.getName(),
                    student.getContact(),
                    student.getAddress(),
                    student.getGrade()
            );

            tmList.add(studentTm);
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
    public void btnStudentDeleteOnAction(ActionEvent actionEvent) {
        String id = txtStudentId.getText();

        try {
            boolean isDeleted = StudentRepo.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student Data Deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnStudentRefreshOnAction(ActionEvent actionEvent) {
        initialize();
        txtStudentName.setText("");
        txtStudentId.setText("");
    }

    public void deleteClickedOnAction(MouseEvent mouseEvent) {
        StudentTm selectedItem = tblStudent.getSelectionModel().getSelectedItem();
        txtStudentId.setText(selectedItem.getStId());
        txtStudentName.setText(selectedItem.getName());
    }
}
