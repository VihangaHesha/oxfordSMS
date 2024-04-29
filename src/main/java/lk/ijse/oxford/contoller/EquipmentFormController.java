package lk.ijse.oxford.contoller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EquipmentFormController implements Initializable {
    @FXML
    private StackPane equipmentContainer;
    @FXML
    private BorderPane bdEquipment;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/view/equipment_forms/add_equipment_form.fxml"));
            equipmentContainer.getChildren().removeAll();
            equipmentContainer.getChildren().setAll(fxml);
        } catch (IOException e) {
            Logger.getLogger(ModuleLayer.Controller.class.getName()).log(Level.SEVERE,null,e);
        }
    }

    public void btnEquipmentAddOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/equipment_forms/add_equipment_form.fxml"));
        equipmentContainer.getChildren().removeAll();
        equipmentContainer.getChildren().setAll(fxml);
    }

    public void btnEquipmentDeleteOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/equipment_forms/delete_equipment_form.fxml"));
        equipmentContainer.getChildren().removeAll();
        equipmentContainer.getChildren().setAll(fxml);
    }

    public void btnEquipmentEditOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/equipment_forms/edit_equipment_form.fxml"));
        equipmentContainer.getChildren().removeAll();
        equipmentContainer.getChildren().setAll(fxml);

    }

    public void btnEquipmentToDashOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage = (Stage) bdEquipment.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }

    public void btnExit(ActionEvent actionEvent) {
        System.exit(0);
    }
}
