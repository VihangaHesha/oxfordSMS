/*
package lk.ijse.oxford.contoller.student_form_controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.oxford.model.Payment;
import lk.ijse.oxford.model.Student;
import lk.ijse.oxford.model.tm.PaymentCartTm;
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
    private Label lblSubFee;
    @FXML
    private Label lblPaymentId;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblNetTotal;
    @FXML
    private TableColumn<?,?> colSubId;
    @FXML
    private TableColumn<?,?>colSubDesc;
    @FXML
    private TableColumn<?,?>colFeeAmount;
    @FXML
    private TableColumn<?,?>colAction;
    @FXML
    private TableView<PaymentCartTm> tblFeePayment;
    private List<Payment> payments = new ArrayList<>();

    public void initialize(){
        this.payments = getAllStudents();
        setCellValueFactory();
        loadStudentTable();
    }

   */
/* private List<Payment> getAllStudents() {
        List<Payment> paymentList = null;
        try {
            paymentList = PaymentRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return paymentList;
    }*//*


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

    }

    public void btnCheckOutOnAction(ActionEvent actionEvent) {

    }

    public void cmbSubjectOnAction(ActionEvent actionEvent) {

    }
}
*/
