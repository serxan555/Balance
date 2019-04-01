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
import az.model.Plan;
import az.util.Notification;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author SS555
 */
public class BalanceController implements Initializable {

    @FXML
    public ComboBox<Kategory> gelirComboBox;
    @FXML
    public ComboBox<Kategory> xerclerComboBox;
    @FXML
    private RadioButton gelirRadio;
    @FXML
    private RadioButton xerclerRadio;
    @FXML
    private TextField adTextField;

    BalanceRepository balanceRepository = new BalanceRepository();
    @FXML
    private ToggleGroup toggleRadio;
    @FXML
    private Label cemBalans;
    @FXML
    private PieChart pie;
    @FXML
    private ListView<Plan> planlar;
    @FXML
    private HBox hboxLabel;
    @FXML
    private Label meblegLabel;
    @FXML
    private Label baslaTarixLabel;
    @FXML
    private Label sonTarixLabel;
    @FXML
    private TableView<Plan> cedvel;
    @FXML
    private TableColumn<Plan, String> xerclerCol;
    @FXML
    private TableColumn<Plan, Double> ayrilmisMeblegCol;
    @FXML
    private TableColumn<Plan, ProgressBar> progresBarCol;
    @FXML
    private TableColumn<Plan, Double> xerclenmisMeblegCol;
    @FXML
    private TableColumn<Plan, Double> faizCol;
    @FXML
    private TableColumn<Plan, String> tempCol;
    @FXML
    private ProgressBar barbar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hboxLabel.setVisible(false);
        cedvel.setVisible(false);
        fillCombobox();
        cemBalance();
        fillList();
        upgradeTable();
    }

    @FXML
    private void yaddaSaxla(ActionEvent event) throws SQLException {
        Kategory gelirKategory = gelirComboBox.getSelectionModel().getSelectedItem();
        Kategory xerclerKategory = xerclerComboBox.getSelectionModel().getSelectedItem();
        if (gelirKategory == null && xerclerKategory == null) {
            if (gelirRadio.isSelected()) {
                if (!existIncome(adTextField.getText().trim())) {
                    gelirKategory = new Kategory();
                    gelirKategory.setKategory(adTextField.getText().trim());
                    gelirKategory.setType("gəlir");
                    balanceRepository.addKategory(gelirKategory);
                    Notification.callNotification("Yeni Gəlir kateqoriyası əlavə olundu!", "ADD");
                } else {
                    Notification.callNotification(adTextField.getText() + " artıq mövcuddur!", "UNSUCCESS");
                }
            } else if (xerclerRadio.isSelected()) {
                if (!existExpense(adTextField.getText().trim())) {
                    xerclerKategory = new Kategory();
                    xerclerKategory.setKategory(adTextField.getText().trim());
                    xerclerKategory.setType("xərclər");
                    balanceRepository.addKategory(xerclerKategory);
                    Notification.callNotification("Yeni Xərclər kateqoriyası əlavə olundu!", "ADD");
                } else {
                    Notification.callNotification(adTextField.getText() + " artıq mövcuddur!", "UNSUCCESS");
                }
            } else {
                Notification.callNotification("Kateqoriya növünü seçin!", "WARNING");
            }
        } else {
            if (gelirKategory != null) {
                gelirKategory.setKategory(adTextField.getText().trim());
                balanceRepository.updateKategory(gelirKategory);
                Notification.callNotification("Seçilmiş Gəlir Kateqoriyası yeniləndi!", "UPDATE");
            } else if (xerclerKategory != null) {
                xerclerKategory.setKategory(adTextField.getText().trim());
                balanceRepository.updateKategory(xerclerKategory);
                Notification.callNotification("Seçilmiş Xərc Kateqoriyası yeniləndi!", "UPDATE");
            }
        }
        clear();
        fillCombobox();
    }

    public boolean existIncome(String ad) {
        for (Kategory kategory : gelirComboBox.getItems()) {
            if (kategory.getKategory().equals(ad)) {
                return true;
            }
        }
        return false;
    }
    public boolean existExpense(String ad) {
        for (Kategory kategory : xerclerComboBox.getItems()) {
            if (kategory.getKategory().equals(ad)) {
                return true;
            }
        }
        return false;
    }

    @FXML
    private void sil(ActionEvent event) {
        if (gelirComboBox.getValue() != null) {
            balanceRepository.kategorySil(gelirComboBox.getValue());
            Notification.callNotification("Seçilmiş Gəlir Kateqoriyası silindi!", "SUCCESS!!!");
        } else if (xerclerComboBox.getValue() != null) {
            balanceRepository.kategorySil(xerclerComboBox.getValue());
            Notification.callNotification("Seçilmiş Xərc Kateqoriyası silindi!", "SUCCESS!!!");
        } else {
            Notification.callNotification("Kateqoriya növü seçin!", "WARNING!!!");
        }
        clear();
        fillCombobox();
    }

    @FXML
    private void yeniQeydiyyat(ActionEvent event) throws IOException {
        if (gelirRadio.isSelected()) {
            if (gelirComboBox.getValue() != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/az/view/YeniQeydiyyatView.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                Image icone = new Image(getClass().getResourceAsStream("/az/files/new.png"));
                stage.getIcons().add(icone);
                Scene scene = new Scene(root);
                stage.setTitle("Yeni Gəlir");
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                YeniQeydiyyatController controller = loader.getController();
                controller.setStage(stage);
                controller.setBalanceController(this);
                controller.setKategory(gelirComboBox.getValue());
                stage.show();
            } else {
                Notification.callNotification("Gəlir kateqoriyası seçin!", "WARNING!!!");
            }
        } else if (xerclerRadio.isSelected()) {
            if (xerclerComboBox.getValue() != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/az/view/YeniQeydiyyatView.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                Image icone = new Image(getClass().getResourceAsStream("/az/files/new.png"));
                stage.getIcons().add(icone);
                Scene scene = new Scene(root);
                stage.setTitle("Yeni Xərc");
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                YeniQeydiyyatController controller = loader.getController();
                controller.setStage(stage);
                controller.setBalanceController(this);
                controller.setKategory(xerclerComboBox.getValue());
                stage.show();
            } else {
                Notification.callNotification("Xərc kateqoriyası seçin!", "WARNING!!!");
            }
        } else {
            Notification.callNotification("Kateqoriya növü seçin!", "WARNING!!!");
        }
    }

    @FXML
    private void etrafliHesabat(ActionEvent event) throws IOException {
        if (gelirRadio.isSelected()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/az/view/GelirEtrafliHesabatView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Image icone = new Image(getClass().getResourceAsStream("/az/files/filter.png"));
            stage.getIcons().add(icone);
            Scene scene = new Scene(root);
            stage.setTitle("Filter Gəlir");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            clear();
        } else if (xerclerRadio.isSelected()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/az/view/XercEtrafliHesabatView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Image icone = new Image(getClass().getResourceAsStream("/az/files/filter.png"));
            stage.getIcons().add(icone);
            Scene scene = new Scene(root);
            stage.setTitle("Filter Xərc");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            clear();
        } else {
            Notification.callNotification("Kateqoriya növünü seçin!", "WARNING");
        }
    }

    @FXML
    private void yeniPlan(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/az/view/YeniPlanView.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Image icone = new Image(getClass().getResourceAsStream("/az/files/newPlan.png"));
        stage.getIcons().add(icone);
        Scene scene = new Scene(root);
        stage.setTitle("Yeni Plan");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        YeniPlanController controller = loader.getController();
        controller.setStage(stage);
        controller.setBalanceController(this);
        cemBalance();
        stage.show();
    }

    public void fillCombobox() {
        gelirComboBox.getItems().remove(0, gelirComboBox.getItems().size());
        xerclerComboBox.getItems().remove(0, xerclerComboBox.getItems().size());
        Map<String, ArrayList<Kategory>> map = balanceRepository.allKateqoriya();
        gelirComboBox.getItems().addAll(map.get("gəlir"));
        xerclerComboBox.getItems().addAll(map.get("xərclər"));
    }

    @FXML
    private void selectGelir(ActionEvent event) {
        if (gelirComboBox.getValue() != null) {
            adTextField.setText(gelirComboBox.getValue().toString());
        } else {
            adTextField.setText(null);
        }
    }

    @FXML
    private void selectXercler(ActionEvent event) {
        if (xerclerComboBox.getValue() != null) {
            adTextField.setText(xerclerComboBox.getValue().toString());
        } else {
            adTextField.setText(null);
        }
    }

    public void clear() {
        adTextField.setText(null);
        gelirComboBox.setValue(null);
        xerclerComboBox.setValue(null);
        gelirRadio.setSelected(false);
        xerclerRadio.setSelected(false);
    }

    public void cemBalance() {
        try {
            balanceRepository.updateBalance();
        } catch (Exception e) {
            balanceRepository.updateBalanceNew();
        }
        cemBalans.setText("Cəm Balans: " + String.valueOf(balanceRepository.sumBalance()));
    }

    @FXML
    public void gelirClick() {
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
        ArrayList<Income> incomeList = balanceRepository.getIncome();
        for (int i = 0; i < incomeList.size(); i++) {
            list.add(new PieChart.Data(incomeList.get(i).getKateqoriya() + " - " + String.valueOf(incomeList.get(i).getAmount()), incomeList.get(i).getAmount()));
        }
        pie.setData(list);
        pie.setTitle("Bütün Gəlir kateqoriyasının faizi");
    }

    @FXML
    public void xercClick() {
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
        ArrayList<Expense> expenceList = balanceRepository.getExpence();
        for (int i = 0; i < expenceList.size(); i++) {
            list.add(new PieChart.Data(expenceList.get(i).getKateqoriya() + " - " + String.valueOf(expenceList.get(i).getAmount()), expenceList.get(i).getAmount()));
        }
        pie.setData(list);
        pie.setTitle("Bütün Xərc kateqoriyasının faizi");
    }

    @FXML
    private void onSelectPlan(MouseEvent event) {
        Plan selectPlan = planlar.getSelectionModel().getSelectedItem();
        hboxLabel.setVisible(true);
        cedvel.setVisible(true);
        meblegLabel.setText("Məbləğ: " +selectPlan.getTotalAmount());
        baslaTarixLabel.setText("Başlanğıc tarix: "+selectPlan.getStartDate().toString());
        sonTarixLabel.setText("Son tarix: "+selectPlan.getEndDate().toString());
        fillTable();
        barbar.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
    }

    public void fillList() {
        planlar.getItems().setAll(balanceRepository.plans());
    }
    
    public void upgradeTable() {
        xerclerCol.setCellValueFactory(new PropertyValueFactory<>("kateqoriya"));
        ayrilmisMeblegCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        progresBarCol.setCellValueFactory(new PropertyValueFactory<>("bar"));
        xerclenmisMeblegCol.setCellValueFactory(new PropertyValueFactory<>("xerc"));
        faizCol.setCellValueFactory(new PropertyValueFactory<>("faiz"));
        tempCol.setCellValueFactory(new PropertyValueFactory<>("temp"));
    }
    
    public void fillTable(){
        Plan selectPlan = planlar.getSelectionModel().getSelectedItem();
        cedvel.getItems().setAll(balanceRepository.allPlans(selectPlan));
    }

    
}
