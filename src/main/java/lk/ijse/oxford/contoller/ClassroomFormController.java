package lk.ijse.oxford.contoller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClassroomFormController implements Initializable {
    @FXML
    private StackPane classroomContainer;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/view/classroom_forms/add_classroom_form.fxml"));
            classroomContainer.getChildren().removeAll();
            classroomContainer.getChildren().setAll(fxml);
        } catch (IOException e) {
            Logger.getLogger(ModuleLayer.Controller.class.getName()).log(Level.SEVERE,null,e);
        }

    }
    public void btnClassroomAddOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/classroom_forms/add_classroom_form.fxml"));
        classroomContainer.getChildren().removeAll();
        classroomContainer.getChildren().setAll(fxml);
    }

    public void btnClassroomDeleteOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/classroom_forms/delete_classroom_form.fxml"));
        classroomContainer.getChildren().removeAll();
        classroomContainer.getChildren().setAll(fxml);
    }

    public void btnClassroomEditOnAction(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/classroom_forms/edit_classroom_form.fxml"));
        classroomContainer.getChildren().removeAll();
        classroomContainer.getChildren().setAll(fxml);
    }
}
