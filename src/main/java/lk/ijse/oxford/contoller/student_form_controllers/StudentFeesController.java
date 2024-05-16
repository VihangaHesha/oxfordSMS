package lk.ijse.oxford.contoller.student_form_controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.oxford.db.DbConnection;
import lk.ijse.oxford.model.*;
import lk.ijse.oxford.model.tm.PayDetailTm;
import lk.ijse.oxford.model.tm.PaymentCartTm;
import lk.ijse.oxford.model.tm.StudentTm;
import lk.ijse.oxford.repository.PaymentRepo;
import lk.ijse.oxford.repository.PlacePaymentRepo;
import lk.ijse.oxford.repository.StudentRepo;
import lk.ijse.oxford.repository.SubjectRepo;
import lk.ijse.oxford.util.Regex;
import lk.ijse.oxford.util.TextFields;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class StudentFeesController {
    @FXML
    private ComboBox<String> cmbSubjectName;
    @FXML
    private JFXTextField txtStudentId;
    @FXML
    private Label lblSeats;
    @FXML
    private Label lblSubFee;
    @FXML
    private Label lblSubId;
    @FXML
    private Label lblPaymentId;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblNetTotal;
    @FXML
    private Label lblAbleSeats;
    @FXML
    private TableColumn<?,?> colSubId;
    @FXML
    private TableColumn<?,?>colSubDesc;
    @FXML
    private TableColumn<?,?>colFeeAmount;
    @FXML
    private TableColumn<?,?>colSeats;
    @FXML
    private TableColumn<?,?>colAction;
    @FXML
    private TableColumn<?,?>colTotal;
    @FXML
    private TableView<PaymentCartTm> tblFeePayment;
    private ObservableList<PaymentCartTm> paymentsTm = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?,?> colPayId;
    @FXML
    private TableColumn<?,?>colStId;
    @FXML
    private TableColumn<?,?>colFee;
    @FXML
    private TableColumn<?,?>colDate;
    @FXML
    private TableView<PayDetailTm> tblLastTransactions;
    private List<PayDetail> payDetail = new ArrayList<>();
    private double netTotal=0;

    public void initialize(){
        this.payDetail = getLastFiveTransaction();
        setCellValueFactory();
        loadTransactionTable();
        loadNextPayId();
        setDate();
        getSubjects();
        setSeat();
    }

    private void setSeat() {
        lblSeats.setText("1");
    }

    private List<PayDetail> getLastFiveTransaction() {
        List<PayDetail> payDetailList = null;
        try {
            payDetailList = PaymentRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return payDetailList;
    }

    private void loadTransactionTable() {
        ObservableList<PayDetailTm> tmList = FXCollections.observableArrayList();

        for (PayDetail payDetail : payDetail) {
            PayDetailTm payDetailTm = new PayDetailTm(
                    payDetail.getStId(),
                    payDetail.getPayId(),
                    payDetail.getFee(),
                    payDetail.getDate()
            );

            tmList.add(payDetailTm);
        }
        tblLastTransactions.setItems(tmList);
    }

    private void getSubjects() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = SubjectRepo.getIds();

            for (String id : idList) {
                obList.add(id);
            }
            cmbSubjectName.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblDate.setText(String.valueOf(now));
    }

    private void loadNextPayId() {
        try {
            String currentId = PaymentRepo.currentId();
            String nextId = nextId(currentId);

            lblPaymentId.setText(nextId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String nextId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("P");
            int id = Integer.parseInt(split[1]);
            id++;

            // Format the ID with leading zeros using String.format
            return "P" + String.format("%03d", id);
        } else {
            return "P001";
        }
    }

    private void setCellValueFactory() {
        colStId.setCellValueFactory(new PropertyValueFactory<>("stId"));
        colPayId.setCellValueFactory(new PropertyValueFactory<>("payId"));
        colSeats.setCellValueFactory(new PropertyValueFactory<>("ableSeats"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colSubId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSubDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colFeeAmount.setCellValueFactory(new PropertyValueFactory<>("fee"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));
    }
    @FXML
    void btnPayFeesOnAction(ActionEvent actionEvent) {
        if (isValidate()){
            String payId = lblPaymentId.getText();
            String subId = lblSubId.getText();
            String stId = txtStudentId.getText();
            double totalFee = Double.parseDouble(lblNetTotal.getText());
            Date date = Date.valueOf(LocalDate.now());

            var payment = new Payment(payId,totalFee, date, stId,subId);

            List<PaymentDetails> poList = new ArrayList<>();

            for (int i = 0; i < tblFeePayment.getItems().size(); i++) {
                PaymentCartTm tm = paymentsTm.get(i);

                PaymentDetails od = new PaymentDetails(
                        payId,
                        tm.getId(),
                        tm.getFee(),
                        tm.getAbleSeats()
                );
                poList.add(od);
            }

            PlacePayment po = new PlacePayment(payment, poList);
            try {
                boolean isPlaced = PlacePaymentRepo.placePayment(po);
                if(isPlaced) {
                    new Alert(Alert.AlertType.CONFIRMATION, "order placed!").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "order not placed!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    public void btnCheckOutOnAction(ActionEvent actionEvent) {
        String id = lblSubId.getText();
        String payId = lblPaymentId.getText();
        String desc = cmbSubjectName.getValue();
        double fee = Double.parseDouble(lblSubFee.getText());
        int seats = Integer.parseInt(lblSeats.getText());
        double total = seats * fee;
        JFXButton btnRemove = new JFXButton("Remove");
        btnRemove.setCursor(Cursor.HAND);

        int unChangeSeats= Integer.parseInt(lblAbleSeats.getText());
        int currentSeats = Integer.parseInt(lblAbleSeats.getText());
        currentSeats = currentSeats -seats;
        lblAbleSeats.setText(
                String.valueOf(currentSeats));

        btnRemove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if(type.orElse(no) == yes) {
                int selectedIndex = tblFeePayment.getSelectionModel().getSelectedIndex();
                paymentsTm.remove(selectedIndex);

                tblFeePayment.refresh();
                lblAbleSeats.setText(String.valueOf(unChangeSeats));
                calculateNetTotal();
            }
        });

        for (int i = 0; i < tblFeePayment.getItems().size(); i++) {
            if (id.equals(colSubId.getCellData(i))) {
                seats += paymentsTm.get(i).getAbleSeats();
                total = fee * seats;

                paymentsTm.get(i).setAbleSeats(seats);
                paymentsTm.get(i).setTotal(total);

                tblFeePayment.refresh();
                calculateNetTotal();
                return;
            }
        }

        PaymentCartTm paymentCartTm = new PaymentCartTm(id,payId,desc,fee,total,seats,btnRemove);

        paymentsTm.add(paymentCartTm);

        tblFeePayment.setItems(paymentsTm);
        System.out.println(paymentsTm.toString());
        calculateNetTotal();
    }

    private void calculateNetTotal() {
        netTotal = 0;
        for (int i = 0; i < tblFeePayment.getItems().size(); i++) {
            netTotal += (double) colTotal.getCellData(i);
        }
        lblNetTotal.setText(String.valueOf(netTotal));
    }

    public void cmbSubjectOnAction(ActionEvent actionEvent) {
        String subName = cmbSubjectName.getValue();
        System.out.println(subName);
        try {
            Subject subject = SubjectRepo.searchByName(subName);
            lblSubFee.setText(String.valueOf(subject.getFeeAmount()));
            lblAbleSeats.setText(String.valueOf(subject.getAvailableSeats()));
            lblSubId.setText(subject.getSubId());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnPrintBillOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        JasperDesign jasperDesign =
                JRXmlLoader.load("src/main/resources/report/ClassCardFee.jrxml");

        JRDesignQuery jrDesignQuery = new JRDesignQuery();
        jrDesignQuery.setText("SELECT * FROM Payment ORDER BY PayId desc LIMIT 1");

        jasperDesign.setQuery(jrDesignQuery);

        JasperReport jasperReport =
                JasperCompileManager.compileReport(jasperDesign);

        JasperPrint jasperPrint =
                JasperFillManager.fillReport(
                        jasperReport,
                        null,
                        DbConnection.getInstance().getConnection());

        JasperViewer.viewReport(jasperPrint,false);
    }

    public boolean isValidate(){
        if(!Regex.setTextColor(TextFields.SID,txtStudentId))return false;
        return true;
    }
    public void txtStIdCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.SID,txtStudentId);
    }
}
