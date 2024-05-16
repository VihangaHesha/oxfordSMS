package lk.ijse.oxford.contoller.user_form_controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import lk.ijse.oxford.repository.UserRepo;
import lk.ijse.oxford.util.Regex;
import lk.ijse.oxford.util.TextFields;

import java.sql.SQLException;

public class EditEmailFormController {
    @FXML
    private JFXTextField txtUserId;
    @FXML
    private JFXTextField txtEmail;
    public void btnEmailSaveOnAction(ActionEvent actionEvent) {
        if (isValidate()){
            String id = txtUserId.getText();
            String email = txtEmail.getText();
            try {
                boolean isUpdated = UserRepo.updateEmail(id,email);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Contact Saved!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    public boolean isValidate() {
        if (!Regex.setTextColor(TextFields.EMAIL, txtEmail)) return false;
        if (!Regex.setTextColor(TextFields.USERID, txtUserId)) return false;
        return true;
    }

    public void txtIdCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.USERID,txtUserId);
    }

    public void txtEmailCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.EMAIL,txtEmail);

    }
}
