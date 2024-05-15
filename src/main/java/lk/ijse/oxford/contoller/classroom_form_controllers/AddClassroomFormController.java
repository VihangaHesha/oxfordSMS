package lk.ijse.oxford.contoller.classroom_form_controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.oxford.model.Classroom;
import lk.ijse.oxford.model.Equipment;
import lk.ijse.oxford.model.tm.ClassTm;
import lk.ijse.oxford.model.tm.EquipmentTm;
import lk.ijse.oxford.repository.ClassroomRepo;
import lk.ijse.oxford.repository.EquipmentRepo;
import lk.ijse.oxford.repository.PaymentRepo;
import lk.ijse.oxford.util.Regex;
import lk.ijse.oxford.util.TextFields;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddClassroomFormController {
    @FXML
    private Label lblClassCount;
    private int classCount;
    @FXML
    private Label lblClassCapacity;
    private int classCapacity;
    @FXML
    private Label lblClassId;
    @FXML
    private JFXTextField txtClassDesc;
    @FXML
    private JFXTextField txtCapacity;
    @FXML
    private JFXTextField txtSubId;
    @FXML
    private TableColumn<?,?> colClassId;
    @FXML
    private TableColumn<?,?>colCapacity;
    @FXML
    private TableColumn<?,?>colClassDesc;
    @FXML
    private TableColumn<?,?>colSubId;
    @FXML
    private TableView tblClassroom;
    private List<Classroom> classroomList = new ArrayList<>();
    public void initialize(){
        this.classroomList = getAllClassrooms();
        setCellValueFactory();
        loadClassroomTable();
        loadNextClassId();
        try {
            classCount=ClassroomRepo.getClassCount();
            classCapacity=ClassroomRepo.getClassCapacity();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        setClassCount(classCount);
        setClassCapacity(classCapacity);
    }

    private void setClassCount(int classCount) {
        lblClassCount.setText(String.valueOf(classCount));
    }

    private void setClassCapacity(int classCapacity) {
        lblClassCapacity.setText(String.valueOf(classCapacity));
    }

    private List<Classroom> getAllClassrooms() {
        List<Classroom> classroomList = null;
        try {
            classroomList = ClassroomRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return classroomList;

    }

    private void loadClassroomTable() {
        ObservableList<ClassTm> tmList = FXCollections.observableArrayList();

        for (Classroom classroom : classroomList) {
            ClassTm classTm = new ClassTm(
                    classroom.getClassId(),
                    classroom.getDescription(),
                    classroom.getCapacity(),
                    classroom.getSubId()
            );

            tmList.add(classTm);
        }
        tblClassroom.setItems(tmList);
    }

    private void setCellValueFactory() {
        colClassId.setCellValueFactory(new PropertyValueFactory<>("classId"));
        colClassDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        colSubId.setCellValueFactory(new PropertyValueFactory<>("subId"));
    }

    private void loadNextClassId() {
        try {
            String currentId = ClassroomRepo.currentId();
            String nextId = nextId(currentId);

            lblClassId.setText(nextId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String nextId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("C");
            int id = Integer.parseInt(split[1]);
            id++;

            // Format the ID with leading zeros using String.format
            return "C" + String.format("%03d", id);
        } else {
            return "C001";
        }
    }

    public void btnClassAddOnAction(ActionEvent actionEvent) {
        if (isValidate()){
            String classId = lblClassId.getText();
            String desc = txtClassDesc.getText();
            String subId = txtSubId.getText();
            int capacity = Integer.parseInt(txtCapacity.getText());

            Classroom classroom = new Classroom(classId,desc,capacity,subId);


            try {
                boolean isSaved = ClassroomRepo.save(classroom);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Classroom Data Saved!").show();
                    txtSubId.setText("");
                    txtClassDesc.setText("");
                    txtCapacity.setText("");
                    initialize();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    public void textDescCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.DESC,txtClassDesc);
    }

    public void textCapacityCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.CAPACITY,txtCapacity);
    }

    public void textSubIdCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.SUBID,txtSubId);
    }
    public boolean isValidate(){
        if(!Regex.setTextColor(TextFields.DESC,txtClassDesc))return false;
        if(!Regex.setTextColor(TextFields.CAPACITY,txtCapacity))return false;
        if(!Regex.setTextColor(TextFields.SUBID,txtSubId))return false;
        return true;
    }
}
