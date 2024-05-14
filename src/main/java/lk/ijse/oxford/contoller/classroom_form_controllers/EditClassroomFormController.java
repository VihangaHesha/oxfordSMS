package lk.ijse.oxford.contoller.classroom_form_controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.oxford.model.Classroom;
import lk.ijse.oxford.model.tm.ClassTm;
import lk.ijse.oxford.repository.ClassroomRepo;
import lk.ijse.oxford.repository.EquipmentRepo;
import lk.ijse.oxford.util.Regex;
import lk.ijse.oxford.util.TextFields;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EditClassroomFormController {
    @FXML
    private Label lblClassCount;
    private int classCount;
    @FXML
    private Label lblClassCapacity;
    private int classCapacity;
    @FXML
    private JFXTextField txtClassId;
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
        try {
            classCount= ClassroomRepo.getClassCount();
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

    public void btnClassEditOnAction(ActionEvent actionEvent) {
        if (isValidate()){
            String classId = txtClassId.getText();
            String desc = txtClassDesc.getText();
            String subId = txtSubId.getText();
            int capacity = Integer.parseInt(txtCapacity.getText());

            Classroom classroom = new Classroom(classId,desc,capacity,subId);

            try {
                boolean isUpdated = ClassroomRepo.update(classroom);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Student Data Is Updated!").show();
                    txtSubId.setText("");
                    txtClassDesc.setText("");
                    txtClassId.setText("");
                    txtCapacity.setText("");
                    initialize();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    public void classDetailsUpToFieldsOnAction(MouseEvent mouseEvent) {
        ClassTm selectedItem = (ClassTm) tblClassroom.getSelectionModel().getSelectedItem();
        txtClassId.setText(selectedItem.getClassId());
        txtClassDesc.setText(selectedItem.getDescription());
        txtCapacity.setText(String.valueOf(selectedItem.getCapacity()));
        txtSubId.setText(selectedItem.getSubId());
    }

    public void textDescCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.DESC,txtClassDesc);
    }

    public void textCapacityCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.CAPACITY,txtCapacity);
    }

    public void textIdCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.CID,txtClassId);
    }

    public void textSubIdCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.SUBID,txtSubId);
    }
    public boolean isValidate(){
        if(!Regex.setTextColor(TextFields.CID,txtClassId))return false;
        if(!Regex.setTextColor(TextFields.DESC,txtClassDesc))return false;
        if(!Regex.setTextColor(TextFields.CAPACITY,txtCapacity))return false;
        if(!Regex.setTextColor(TextFields.SUBID,txtSubId))return false;
        return true;
    }
}
