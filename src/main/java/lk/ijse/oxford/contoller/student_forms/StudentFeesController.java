package lk.ijse.oxford.contoller.student_forms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.oxford.model.Payment;
import lk.ijse.oxford.model.Student;
import lk.ijse.oxford.model.tm.StudentTm;
import lk.ijse.oxford.repository.PaymentRepo;
import lk.ijse.oxford.repository.StudentRepo;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentFeesController {
    @FXML
    private TextField txtPayId;

    @FXML
    private TextField txtDate;
    @FXML
    private TextField txtStId;
    @FXML
    private TextField txtFeesAmount;
    @FXML
    private TextField txtSubject;
    @FXML
    private Label lblStudentName;
    @FXML
    private Label lblDate;
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
    public void btnPayFeesOnAction(ActionEvent actionEvent) {
        String payId = txtPayId.getText();
        String id = txtStId.getText();
        Date date = Date.valueOf(txtDate.getText());
        String subject = txtSubject.getText();
        double amount = Double.parseDouble(txtFeesAmount.getText());

        Payment payment = new Payment(payId, amount,date, id, subject);


        System.out.println(payment.toString());

        try {
            boolean isSaved = PaymentRepo.save(payment);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Fee Payment Successful!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
