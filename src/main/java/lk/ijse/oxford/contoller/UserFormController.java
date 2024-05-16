package lk.ijse.oxford.contoller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lk.ijse.oxford.model.TimeTable;
import lk.ijse.oxford.model.User;
import lk.ijse.oxford.repository.UserRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserFormController {
    @FXML
    private Label lblUsername;

    @FXML
    private Label lblUserPw;
    @FXML
    private Label lblContact;
    @FXML
    private Label lblEmail;
    private String uId;
    private String name;
    private String pw;
    private String contact;
    private String email;

    public void initialize(){
        setUserDetails();
    }

    private void setUserDetails() {
        List<User> userList =null;
        try {
            userList = UserRepo.getAll(uId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (User user : userList) {
            User users = new User(
                    user.getName(),
                    user.getPw(),
                    user.getContact(),
                    user.getEmail()
            );
            name = users.getName();
            pw = users.getPw();
            contact = users.getContact();
            email = users.getEmail();
        }
        lblUsername.setText(name);
        lblUserPw.setText(pw);
        lblContact.setText(contact);
        lblEmail.setText(email);
    }

    public UserFormController(String userId) {
        uId = userId;
    }

    public void btnNameInAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/user_forms/edit_name_form.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();

        stage.setTitle("Edit Username Form");

        stage.show();
    }

    public void btnPwEditOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/user_forms/edit_pw_form.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();

        stage.setTitle("Edit Password Form");

        stage.show();
    }

    public void btnPwDisplayOnAction(ActionEvent actionEvent) {

    }

    public void btnContactOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/user_forms/edit_contact_form.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();

        stage.setTitle("Edit Contact Form");

        stage.show();
    }

    public void btnEmailOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/user_forms/edit_email_form.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();

        stage.setTitle("Edit Email Form");

        stage.show();
    }
}
