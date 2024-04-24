package lk.ijse.oxford.contoller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {
    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        Parent  rootNode = FXMLLoader.load(this.getClass().getResource("/view/employee_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Employee Form");

        stage.show();
    }

    public void btnStudentOnAction(ActionEvent actionEvent) {

        Parent rootNode = null;
        try {
            rootNode = FXMLLoader.load(this.getClass().getResource("/view/student_form.fxml"));
            Scene scene = new Scene(rootNode);
            Stage stage = new Stage();
            stage.setScene(scene);

            stage.setTitle("Student Form");

            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void btnEquipmentOnAction(ActionEvent actionEvent) throws IOException {

        Parent  rootNode = FXMLLoader.load(this.getClass().getResource("/view/equipment_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Equipment Form");

        stage.show();
    }

    public void btnUserOnAction(ActionEvent actionEvent) throws IOException {
        Parent  rootNode = FXMLLoader.load(this.getClass().getResource("/view/user_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("User Form");

        stage.show();
    }

    public void btnBackwardOnAction(ActionEvent actionEvent) {

    }
}
