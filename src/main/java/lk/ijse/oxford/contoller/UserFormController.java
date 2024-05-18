package lk.ijse.oxford.contoller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lk.ijse.oxford.model.User;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserFormController {
    @FXML
    private Label lblUserName;

    @FXML
    private Label lblUserPw;
    @FXML
    private Label lblContact;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblTime;
    @FXML
    private Label lblDate;
    private User user;
    private volatile boolean stop = false;


    public void setUser(User user) {
        this.user = user;
        System.out.println(user.toString());
        setUserDetails(user);
        setDate();
    }
    private void setDate() {

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMM dd");
        String formattedDate = date.format(formatter);
        lblDate.setText(formattedDate);

        Thread thread = new Thread(() ->{
            SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
            while (!stop){
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    throw new RuntimeException(e);
                }
                final String timeNow = dateFormat.format(new java.util.Date());
                Platform.runLater(()->{
                    lblTime.setText(timeNow);
                });
            }
        });
        thread.start();
    }
    private void setUserDetails(User user) {

        lblUserName.setText(this.user.getName());
        lblUserPw.setText(this.user.getPw());
        lblContact.setText(this.user.getContact());
        lblEmail.setText(this.user.getEmail());
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
