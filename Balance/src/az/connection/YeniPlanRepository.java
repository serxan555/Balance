/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.connection;

import az.connection.DataManager;
import az.controller.LoginController;
import az.model.Expense;
import az.model.Plan;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author SS555
 */
public class YeniPlanRepository extends DataManager {

    public ArrayList<Expense> getExpence() {
        String query = "select category from expense where userId = "+LoginController.userId+" group by category";
        ArrayList<Expense> list = new ArrayList<>();
        try {
            connect();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Expense expense = new Expense();
                expense.setKateqoriya(resultSet.getString("category"));
                expense.setAmount(0.0);
                list.add(expense);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (Exception e) {
            }
        }
        return list;
    }

    public ArrayList<String> isExist() {
        String query = "select name from plan where userId="+LoginController.userId;
        ArrayList<String> list = new ArrayList<>();
        try {
            connect();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (Exception e) {
            }
        }
        return list;
    }

    public int addPlan(Plan plan) {
        String query = "insert into plan (name,startDate,endDate,totalAmount,userId) values (?,?,?,?,"+LoginController.userId+")";
        try {
            connect();
            statement = connection.prepareStatement(query,new String[]{"id"});
            statement.setString(1, plan.getName());
            statement.setDate(2, new Date(plan.getStartDate().getTime()));
            statement.setDate(3, new Date(plan.getEndDate().getTime()));
            statement.setDouble(4, plan.getTotalAmount());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (Exception e) {
            }
        }
        return 0;
    }
    
    public void addPlans(Plan plan) {
        String query = "insert into plans (planId,category,amount,userId) values (?,?,?,"+LoginController.userId+")";
        try {
            connect();
            statement = connection.prepareStatement(query);
            statement.setInt(1, plan.getId());
            statement.setString(2, plan.getKateqoriya());
            statement.setDouble(3, plan.getAmount());
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (Exception e) {
            }
        }
    }

}
