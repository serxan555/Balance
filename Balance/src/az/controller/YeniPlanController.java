/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.controller;

import az.connection.YeniPlanRepository;
import az.connection.BalanceRepository;
import az.model.Expense;
import az.model.Plan;
import az.util.Notification;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author SS555
 */
public class YeniPlanController implements Initializable {

    @FXML
    private TextField planAdi;
    @FXML
    private DatePicker baslaTarix;
    @FXML
    private DatePicker sonTarix;
    @FXML
    private TextField amountTextField;
    @FXML
    private TableView<Expense> cedvel;
    @FXML
    private TableColumn<Expense, String> kateqoriyaCol;
    @FXML
    private TableColumn<Expense, Double> amountCol;
    @FXML
    private Label umumiMebleg;
    public double sum = 0.0;

    YeniPlanRepository planRepository = new YeniPlanRepository();
    BalanceRepository balanceRepository = new BalanceRepository();
    private BalanceController balanceController;
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public BalanceController getBalanceController() {
        return balanceController;
    }

    public void setBalanceController(BalanceController balanceController) {
        this.balanceController = balanceController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        baslaTarix.setValue(LocalDate.now());
        sonTarix.setValue(LocalDate.now());
        upgrade();
        cedvel.getItems().setAll(planRepository.getExpence());
        umumiMebleg.setText("Ümumi ayrılmış məbləğ: 0.0");
    }

    @FXML
    private void keyMebleg(KeyEvent event) {
        Expense selectExpense = cedvel.getSelectionModel().getSelectedItem();
        if (!"".equals(amountTextField.getText())) {
            selectExpense.setAmount(Double.parseDouble(amountTextField.getText()));
        } else {
            selectExpense.setAmount(0.0);
        }
        cedvel.getItems().set(cedvel.getSelectionModel().getSelectedIndex(), selectExpense);
        sum = 0.0;
        for (Expense expense : cedvel.getItems()) {
            sum += expense.getAmount();
        }
        umumiMebleg.setText(null);
        umumiMebleg.setText("Ümumi ayrılmış məbləğ: " + String.valueOf(sum));
    }

    @FXML
    private void daxilEt(ActionEvent event) {
        if (exist(planAdi.getText().trim())) {
            if (planAdi.getText().trim() != null && !"".equals(planAdi.getText().trim())) {
                java.sql.Date startDate = java.sql.Date.valueOf(baslaTarix.getValue());
                java.sql.Date endDate = java.sql.Date.valueOf(sonTarix.getValue());
                if (startDate.before(endDate)) {
                    if (sum != 0.0) {
                        if (sum < balanceRepository.sumBalance()) {
                            addPlan(startDate, endDate);
                            Notification.callNotification("Yeni Plan qeydiyyatı ugurla aparıldı", "SUCCESS!!!");
                        } else {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Yeni Planın daxili prosesi");
                            alert.setHeaderText("Diqqet!!!");
                            alert.setContentText("Balans bu Plan üçün kifayət qədər deyil. Yenə də Plan yaradılsınmı?");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK) {
                                addPlan(startDate, endDate);
                                Notification.callNotification("Yeni Plan qeydiyyatı ugurla aparıldı", "SUCCESS!!!");
                            }
                        }
                    } else {
                        Notification.callNotification("Kateqoriyalara görə məbləğ ayrılmayıb", "WARNING!!!");
                    }
                } else {
                    Notification.callNotification("Başlanğıc tarix Son tarixdən böyük və bərabər ola bilməz", "WARNING!!!");
                }
            } else {
                Notification.callNotification("Plan adı boş ola bilməz.", "WARNING!!!");
            }
        } else {
            Notification.callNotification(planAdi.getText() + " artıq mövcuddur.", "WARNING!!!");
        }
    }

    public void upgrade() {
        kateqoriyaCol.setCellValueFactory(new PropertyValueFactory<>("kateqoriya"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    public void fillTable() {
        cedvel.getItems().setAll(planRepository.getExpence());
    }

    public boolean exist(String text) {
        for (String plan : planRepository.isExist()) {
            if (plan.equalsIgnoreCase(text)) {
                return false;
            }
        }
        return true;
    }

    @FXML
    private void cedvelClick(MouseEvent event) {
        amountTextField.setText(null);
    }

    private void addPlan(Date startDate, Date endDate) {
        Plan plan = new Plan();
        plan.setName(planAdi.getText().trim());
        plan.setStartDate(startDate);
        plan.setEndDate(endDate);
        plan.setStartDate(startDate);
        plan.setEndDate(endDate);
        plan.setTotalAmount(sum);
        int planId = planRepository.addPlan(plan);
        for (Expense expense : cedvel.getItems()) {
            plan = new Plan();
            plan.setId(planId);
            plan.setKateqoriya(expense.getKateqoriya());
            plan.setAmount(expense.getAmount());
            planRepository.addPlans(plan);
        }
        stage.close();
        balanceController.clear();
        balanceController.fillList();
    }
}
