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

public class RegisterFormController {
    @FXML
    public TextField txtUserId;
    @FXML
    public TextField txtName;
    @FXML
    public TextField txtPassword;
    @FXML
    public AnchorPane registerRoot;

    @FXML
    void btnRegisterOnAction(ActionEvent actionEvent) {
        String userId = txtUserId.getText();
        String name = txtName.getText();
        String password = txtPassword.getText();

        saveNewUser(userId,name,password);

    }

    private void saveNewUser(String userId, String name, String password) {

        try {
            String sql = "INSERT INTO User(UserId,Name,Password) VALUES (?,?,?)";
            Connection connection = DbConnection.getInstance().getConnection();

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1,userId);
            pstm.setObject(2,name);
            pstm.setObject(3,password);

            if (pstm.executeUpdate() > 0){
                new Alert(Alert.AlertType.CONFIRMATION,"New User Added Successfuly").show();

                AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
                Stage stage = (Stage) registerRoot.getScene().getWindow();

                stage.setScene(new Scene(anchorPane));
                stage.setTitle("Dashboard Form");
                stage.centerOnScreen();
            }
        } catch (SQLException | IOException e) {
            new Alert(Alert.AlertType.ERROR,"Somthing went Wrong!,Please try again!").show();
        }

    }
}