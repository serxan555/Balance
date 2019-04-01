/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.controller;

import az.connection.LoginRepository;
import az.model.User;
import az.util.Notification;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author SS555
 */
public class LoginController implements Initializable {

    private Label label;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
 public static int userId;
    LoginRepository loginRepository = new LoginRepository();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void signIn(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/az/view/RegisterView.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        Image icon = new Image(getClass().getResourceAsStream("/az/files/signup.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Balance");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        RegisterController registerController = loader.getController();
        registerController.setStage(stage);
        stage.show();
    }

    @FXML
    private void Login(ActionEvent event) throws IOException {
        User user = new User();
        username.setText("serxan555");
        password.setText("555ss777ss");
        user.setUsername(username.getText());
        user.setPassword(password.getText());
        try {
            userId = loginRepository.checkUser(user).getId();
        } catch (Exception e) {
            Notification.callNotification("İstifadəçi adı və ya Şifrə yanlışdır!!!", "WARNING!!!");
        }
        if (userId != 0) {
            Parent root = FXMLLoader.load(getClass().getResource("/az/view/BalanceView.fxml"));
            Stage stage = new Stage();
            Image icone = new Image(getClass().getResourceAsStream("/az/files/iconfinder_14_61478.png"));
            stage.getIcons().add(icone);
            Scene scene = new Scene(root);
            stage.setTitle("Balance");
            stage.setScene(scene);
//            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

}
