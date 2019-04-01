/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.controller;

import az.connection.LoginRepository;
import az.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author SS555
 */
public class RegisterController {
    
    Stage stage;
    
    public void setStage(Stage stage){
        this.stage = stage;
    }

    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField cPassword;

    LoginRepository loginRepository = new LoginRepository();
    
    @FXML
    private void signIn(ActionEvent event) {
        if (username.getText() != null && !"".equals(username.getText())) {
            if (password.getText() == null ? cPassword.getText() == null : password.getText().equals(cPassword.getText())) {
                User user = new User();
                user.setName(name.getText());
                user.setSurname(surname.getText());
                user.setEmail(email.getText());
                user.setUsername(username.getText());
                user.setPassword(password.getText());
                int id = loginRepository.addUser(user);
                if (id != 0) {
                    user.setId(id);
                    loginRepository.addInfo(user);
                }
                
                stage.close();
            }
        }
    }
    
}
