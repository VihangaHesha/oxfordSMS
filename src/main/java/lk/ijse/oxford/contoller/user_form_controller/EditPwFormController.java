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

public class EditPwFormController {
    @FXML
    private JFXTextField txtUserId;
    @FXML
    private JFXTextField txtPw;
    public void btnPwSaveOnAction(ActionEvent actionEvent) {
        if (isValidate()){
            String id = txtUserId.getText();
            String pw = txtPw.getText();
            try {
                boolean isUpdated = UserRepo.updatePW(id,pw);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Contact Saved!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    public boolean isValidate(){
        if(!Regex.setTextColor(TextFields.PW,txtPw))return false;
        if(!Regex.setTextColor(TextFields.USERID,txtUserId))return false;
        return true;
    }

    public void txtIdCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.USERID,txtUserId);
    }

    public void txtnameCheckOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.PW,txtPw);
    }
}
