/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.model;

import java.util.Date;

/**
 *
 * @author SS555
 */
public class Expense {
    private int id;
    private double amount;
    private String note;
    private String kateqoriya;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getKateqoriya() {
        return kateqoriya;
    }

    public void setKateqoriya(String kateqoriya) {
        this.kateqoriya = kateqoriya;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
