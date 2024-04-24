package lk.ijse.oxford.contoller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.oxford.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ForgetPasswordFormController {
    @FXML
    public TextField txtUserId;
    @FXML
    public TextField txtNewPassword;
    @FXML
    public TextField txtReNewPassword;
    @FXML
    public AnchorPane paneForgetPW;

    public void btnSavePWOnAction(ActionEvent actionEvent) {
        String userId = txtUserId.getText();
        String password = txtNewPassword.getText();
        String rePassword = txtReNewPassword.getText();

        checkNewPasswords(password,rePassword,userId);

    }

    private void checkNewPasswords(String password, String rePassword, String userId) {
        if (password.equals(rePassword)){
            try {
                boolean isUpdated = setNewPassword(password,userId);
                if (isUpdated){
                    new Alert(Alert.AlertType.CONFIRMATION, "Password updated successfuly!").show();
                }
                MoveToLoginForm();
            } catch (SQLException | IOException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Passwords don't match!please try again!").show();
            txtReNewPassword.setText("");
            txtReNewPassword.requestFocus();
        }
    }

    private void MoveToLoginForm() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.paneForgetPW.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }

    private Boolean setNewPassword(String password, String userId) throws SQLException {
        String sql = "UPDATE User SET Password =? WHERE UserId =?";

        Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,password);
        pstm.setObject(2,userId);

        return pstm.executeUpdate() >0;
    }
}
