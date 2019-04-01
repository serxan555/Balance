/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.controller;

import az.connection.BalanceRepository;
import az.model.Expense;
import az.model.Income;
import az.model.Kategory;
import az.util.Notification;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author SS555
 */
public class YeniQeydiyyatController implements Initializable {

    private BalanceController balanceController;
    public BalanceRepository balanceRepository = new BalanceRepository();

    public BalanceController getBalanceController() {
        return balanceController;
    }

    public void setBalanceController(BalanceController balanceController) {
        this.balanceController = balanceController;
    }

    @FXML
    private TextField qeyd;
    @FXML
    private TextField mebleg;
    @FXML
    private DatePicker tarix;
    @FXML
    private Label text;

    private Kategory kategory;
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Kategory getKategory() {
        return kategory;
    }

    public void setKategory(Kategory kategory) {
        this.kategory = kategory;
        text.setText("Seçilmiş kateqoriya: " + kategory.getKategory());
        tarix.setValue(LocalDate.now());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void daxilEt(ActionEvent event) {
        if (mebleg.getText() != null && !"".equals(mebleg.getText())) {
            if (kategory.getType().equals("gəlir")) {
                Income income = new Income();
                income.setAmount(Double.valueOf(mebleg.getText()));
                income.setKateqoriya(kategory.getKategory());
                income.setNote(qeyd.getText());
                java.sql.Date date = java.sql.Date.valueOf(tarix.getValue());
                income.setDate(date);
                balanceRepository.addIncome(income);
                stage.close();
                balanceController.gelirClick();
            } else {
                Expense expense = new Expense();
                expense.setAmount(Double.valueOf(mebleg.getText()));
                expense.setKateqoriya(kategory.getKategory());
                expense.setNote(qeyd.getText());
                java.sql.Date date = java.sql.Date.valueOf(tarix.getValue());
                expense.setDate(date);
                if (balanceRepository.sumBalance() > expense.getAmount()) {
                    balanceRepository.addExpence(expense);
                    stage.close();
                    balanceController.xercClick();
                } else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Yeni xərcin daxili prosesi");
                    alert.setHeaderText("Diqqet!!!");
                    alert.setContentText("Balans bu xərc üçün kifayət qədər deyil. Yenə də xərc yaradılsınmı?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        balanceRepository.addExpence(expense);
                        stage.close();
                        balanceController.xercClick();
                    }
                }
            }
            balanceController.clear();
            balanceController.cemBalance();
        } else {
            Notification.callNotification("Məbləğ boş ola bilməz!", "WARNING!!!");
        }
    }

}
