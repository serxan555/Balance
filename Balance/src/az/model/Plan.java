/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.model;

import java.util.Date;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;

/**
 *
 * @author SS555
 */
public class Plan {
    private int id;
    private String name;
    private String kateqoriya;
    private double amount;
    private double totalAmount;
    private Date startDate;
    private Date endDate;
    private int status;
    private String temp;
    private Double faiz;
    private Double xerc;
    private ProgressBar bar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKateqoriya() {
        return kateqoriya;
    }

    public void setKateqoriya(String kateqoriya) {
        this.kateqoriya = kateqoriya;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public Double getFaiz() {
        return faiz;
    }

    public void setFaiz(Double faiz) {
        this.faiz = faiz;
    }

    public Double getXerc() {
        return xerc;
    }

    public void setXerc(Double xerc) {
        this.xerc = xerc;
    }

    public ProgressBar getBar() {
        return bar;
    }

    public void setBar(ProgressBar bar) {
        this.bar = bar;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    
    
}
