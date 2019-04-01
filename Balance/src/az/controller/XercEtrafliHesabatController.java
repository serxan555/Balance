/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.controller;

import az.connection.BalanceRepository;
import az.model.Income;
import az.util.Notification;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author SS555
 */
public class XercEtrafliHesabatController implements Initializable {

    @FXML
    private DatePicker baslangicTarix;
    @FXML
    private DatePicker sonTarix;
    @FXML
    private TableView<Object> cedvel;
    @FXML
    private TableColumn<Object, Integer> idCol;
    @FXML
    private TableColumn<Object, String> qeydCol;
    @FXML
    private TableColumn<Object, Double> meblegCol;
    @FXML
    private TableColumn<Object, String> kateqoriyaCol;
    @FXML
    private TableColumn<Object, DatePicker> tarixCol;
    @FXML
    private Label cemBalans;
    public BalanceRepository balanceRepository = new BalanceRepository();
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        cemBalance();
        upgradeTable();
        fillTable();
    }

    public void cemBalance() {
        cemBalans.setText("Ümumi Balans: " + String.valueOf(balanceRepository.sum("expense")));
    }

    @FXML
    private void filterEt(ActionEvent event) {
        Date basla = java.sql.Date.valueOf(baslangicTarix.getValue());
        Date son = java.sql.Date.valueOf(sonTarix.getValue());
        List<Object> list = balanceRepository.filter(basla,son, "expense");
        cedvel.getItems().setAll(list);
        cemBalans.setText("Ümumi Balans: " + String.valueOf(balanceRepository.sumFilter(basla,son,"expense")));
        Notification.callNotification(String.valueOf(list.size()) + " Sətir" , "BİLDİRİŞ!!!");
    }

    public void upgradeTable() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        qeydCol.setCellValueFactory(new PropertyValueFactory<>("note"));
        meblegCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        kateqoriyaCol.setCellValueFactory(new PropertyValueFactory<>("kateqoriya"));
        tarixCol.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    private void fillTable() {
        List<Object> list = balanceRepository.findAll("expense");
        cedvel.getItems().setAll(list);
        Notification.callNotification(String.valueOf(list.size()) + " Sətir" , "BİLDİRİŞ!!!");
    }

    @FXML
    private void onClick(MouseEvent event) {
        fillTable();
        cemBalance();
        baslangicTarix.setValue(null);
        sonTarix.setValue(null);
    }
}
