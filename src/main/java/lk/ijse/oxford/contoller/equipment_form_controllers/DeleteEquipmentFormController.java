package lk.ijse.oxford.contoller.equipment_form_controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.oxford.model.Equipment;
import lk.ijse.oxford.model.tm.EmployeeTm;
import lk.ijse.oxford.model.tm.EquipmentTm;
import lk.ijse.oxford.repository.EquipmentRepo;
import lk.ijse.oxford.util.Regex;
import lk.ijse.oxford.util.TextFields;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeleteEquipmentFormController {
    @FXML
    private Label lblEquipCount;
    private int equipCount;
    @FXML
    private JFXTextField txtEquipId;
    @FXML
    private JFXTextField txtEquiptDesc;
    @FXML
    private TableColumn<?,?> colEquipmentId;
    @FXML
    private TableColumn<?,?>colEquipmentQty;
    @FXML
    private TableColumn<?,?>colEquipmentDesc;
    @FXML
    private TableView tblEquipment;

    private List<Equipment> equipmentList = new ArrayList<>();

    public void initialize(){
        this.equipmentList = getAllEquipment();
        setCellValueFactory();
        loadEquipmentTable();
        try {
            equipCount=EquipmentRepo.getEquipmentCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        setEquipCount(equipCount);
    }

    private void setEquipCount(int equipCount) {
        lblEquipCount.setText(String.valueOf(equipCount));
    }


    private List<Equipment> getAllEquipment() {
        List<Equipment> equipmentList = null;
        try {
            equipmentList = EquipmentRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return equipmentList;
    }

    private void loadEquipmentTable() {
        ObservableList<EquipmentTm> tmList = FXCollections.observableArrayList();

        for (Equipment equipment : equipmentList) {
            EquipmentTm equipmentTm = new EquipmentTm(
                    equipment.getEquipId(),
                    equipment.getDescription(),
                    equipment.getQty()
            );

            tmList.add(equipmentTm);
        }
        tblEquipment.setItems(tmList);
    }

    private void setCellValueFactory() {
        colEquipmentId.setCellValueFactory(new PropertyValueFactory<>("equipId"));
        colEquipmentDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colEquipmentQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }
    public void btnEquipmentDeleteOnAction(ActionEvent actionEvent) {
       if (isValidate()){
           String id = txtEquipId.getText();

           try {
               boolean isDeleted = EquipmentRepo.delete(id);
               if (isDeleted) {
                   new Alert(Alert.AlertType.CONFIRMATION, "Equipment Data Deleted!").show();
                   initialize();
               }
           } catch (SQLException e) {
               new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
           }
       }
    }

    public void equipDeleteOnAction(MouseEvent mouseEvent) {
        EquipmentTm selectedItem = (EquipmentTm) tblEquipment.getSelectionModel().getSelectedItem();
        txtEquipId.setText(selectedItem.getEquipId());
        txtEquiptDesc.setText(selectedItem.getDescription());
    }

    public boolean isValidate(){
        if(!Regex.setTextColor(TextFields.DESC,txtEquiptDesc))return false;
        if(!Regex.setTextColor(TextFields.EQID,txtEquipId))return false;
        return true;
    }
    public void txtClassDescCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.DESC,txtEquiptDesc);
    }

    public void txtClassIdCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.EQID,txtEquipId);
    }
}
