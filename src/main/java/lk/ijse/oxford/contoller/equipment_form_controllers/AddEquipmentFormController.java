package lk.ijse.oxford.contoller.equipment_form_controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.oxford.model.Equipment;
import lk.ijse.oxford.model.tm.EquipmentTm;
import lk.ijse.oxford.repository.EquipmentRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddEquipmentFormController {
    @FXML
    private TextField txtEquipId;
    @FXML
    private TextField txtEquiptDesc;
    @FXML
    private TextField txtEquipQty;
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
    public void btnEquipmentAddOnAction(ActionEvent actionEvent) {
        String eId = txtEquipId.getText();
        String desc = txtEquiptDesc.getText();
        int qty = Integer.parseInt(txtEquipQty.getText());

        Equipment equipment = new Equipment(eId,desc,qty);
        System.out.println(equipment.toString());

        try {
            boolean isSaved = EquipmentRepo.save(equipment);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Equipment Data Saved!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

}
