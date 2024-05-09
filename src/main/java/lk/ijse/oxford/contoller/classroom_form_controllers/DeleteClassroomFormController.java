package lk.ijse.oxford.contoller.classroom_form_controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.oxford.model.Classroom;
import lk.ijse.oxford.model.tm.ClassTm;
import lk.ijse.oxford.model.tm.EquipmentTm;
import lk.ijse.oxford.repository.ClassroomRepo;
import lk.ijse.oxford.repository.EquipmentRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeleteClassroomFormController {
    @FXML
    private Label lblClassCount;
    private int classCount;
    @FXML
    private Label lblClassCapacity;
    private int classCapacity;
    @FXML
    private TextField txtClassId;
    @FXML
    private TextField txtClassDesc;
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
    public void btnClassDeleteOnAction(ActionEvent actionEvent) {
        String id = txtClassId.getText();

        try {
            boolean isDeleted = ClassroomRepo.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Classroom Data Deleted!").show();
                txtClassDesc.setText("");
                txtClassId.setText("");
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void classDeleteOnAction(MouseEvent mouseEvent) {
        ClassTm selectedItem = (ClassTm) tblClassroom.getSelectionModel().getSelectedItem();
        txtClassId.setText(selectedItem.getClassId());
        txtClassDesc.setText(selectedItem.getDescription());
    }

    public void btnClassRefreshOnAction(ActionEvent actionEvent) {
        initialize();
        txtClassDesc.setText("");
        txtClassId.setText("");
    }
}
