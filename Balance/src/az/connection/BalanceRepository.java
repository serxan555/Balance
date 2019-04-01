/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.connection;

import az.controller.LoginController;
import az.model.Expense;
import az.model.Income;
import az.model.Kategory;
import az.model.Plan;
import java.io.IOException;
import java.sql.Date;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author SS555
 */
public class BalanceRepository extends DataManager {

    public void kategorySil(Kategory kateqoriya) {
        String query = "delete from kateqoriya where kategory = ? and type = ? and userId ="+LoginController.userId;
        try {
            connect();
            statement = connection.prepareStatement(query);
            statement.setString(1, kateqoriya.getKategory());
            statement.setString(2, kateqoriya.getType());
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

    public void addKategory(Kategory kategory) throws SQLException {
        String query = "insert into kateqoriya (kategory, type,userId) values (?,?,"+LoginController.userId+")";
        try {
            connect();
            statement = connection.prepareStatement(query);
            statement.setString(1, kategory.getKategory());
            statement.setString(2, kategory.getType());
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public void updateKategory(Kategory kategory) {
        String query = "update kateqoriya set kategory = ? where id = ?";
        try {
            connect();
            statement = connection.prepareStatement(query);
            statement.setString(1, kategory.getKategory());
            statement.setInt(2, kategory.getId());
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

    public Map<String, ArrayList<Kategory>> allKateqoriya() {
        String query = "select * from kateqoriya where userId="+LoginController.userId;
        Map<String, ArrayList<Kategory>> map = new HashMap<>();
        ArrayList<Kategory> listGelir = new ArrayList<>();
        ArrayList<Kategory> listXercler = new ArrayList<>();
        try {
            connect();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Kategory kategory = new Kategory();
                kategory.setId(resultSet.getInt("id"));
                kategory.setKategory(resultSet.getString("kategory"));
                kategory.setType(resultSet.getString("type"));
                if (kategory.getType().equals("gəlir")) {
                    listGelir.add(kategory);
                } else {
                    listXercler.add(kategory);
                }
            }
            map.put("gəlir", listGelir);
            map.put("xərclər", listXercler);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (SQLException ex) {
            }
        }
        return null;
    }

    public void updateBalance() throws SQLException{
        String query = "UPDATE info SET cem=(select sum(amount) from income where userId="+LoginController.userId+") - (select sum(amount) from expense where userId="+LoginController.userId+") where userId="+LoginController.userId;
        try {
            connect();
            statement = connection.prepareStatement(query);
            statement.execute();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (Exception e) {
            }
        }
    }
    public void updateBalanceNew() {
        String query = "UPDATE info SET cem=(select sum(amount) from income where userId="+LoginController.userId+") where userId="+LoginController.userId;
        try {
            connect();
            statement = connection.prepareStatement(query);
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

    public double sumBalance() {
        String query = "select cem from info where userId="+LoginController.userId;
        try {
            connect();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("cem");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (Exception e) {
            }
        }
        return 0.0;
    }

    public void addIncome(Income income) {
        String query = "insert into income (amount, note, category, date, userId) values (?,?,?,?,"+LoginController.userId+")";
        try {
            connect();
            statement = connection.prepareStatement(query);
            statement.setDouble(1, income.getAmount());
            statement.setString(2, income.getNote());
            statement.setString(3, income.getKateqoriya());
            statement.setDate(4, new Date(income.getDate().getTime()));
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

    public void addExpence(Expense expense) {
        String query = "insert into expense (amount, note, category, date,userId) values (?,?,?,?,"+LoginController.userId+")";
        try {
            connect();
            statement = connection.prepareStatement(query);
            statement.setDouble(1, expense.getAmount());
            statement.setString(2, expense.getNote());
            statement.setString(3, expense.getKateqoriya());
            statement.setDate(4, new Date(expense.getDate().getTime()));
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

    public ArrayList<Income> getIncome() {
        String query = "select *, sum(amount) as sum from income where userId = "+LoginController.userId+" group by category";
        ArrayList<Income> list = new ArrayList<>();
        try {
            connect();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Income income = new Income();
                income.setId(resultSet.getInt("id"));
                income.setAmount(resultSet.getDouble("sum"));
                income.setNote(resultSet.getString("note"));
                income.setKateqoriya(resultSet.getString("category"));
                income.setDate(resultSet.getDate("date"));
                list.add(income);
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

    public ArrayList<Expense> getExpence() {
        String query = "select *, sum(amount) as sum from expense where userId = "+LoginController.userId+" group by category";
        ArrayList<Expense> list = new ArrayList<>();
        try {
            connect();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Expense expense = new Expense();
                expense.setId(resultSet.getInt("id"));
                expense.setAmount(resultSet.getDouble("sum"));
                expense.setNote(resultSet.getString("note"));
                expense.setKateqoriya(resultSet.getString("category"));
                expense.setDate(resultSet.getDate("date"));
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

    public List<Object> findAll(String text) {
        String query = "select * from " + text +" where userId = " + LoginController.userId;
        List<Object> list = new ArrayList<>();
        try {
            connect();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Income income = new Income();
                income.setId(resultSet.getInt("id"));
                income.setAmount(resultSet.getDouble("amount"));
                income.setNote(resultSet.getString("note"));
                income.setKateqoriya(resultSet.getString("category"));
                income.setDate(resultSet.getDate("date"));
                list.add(income);
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

    public List<Object> filter(Date basla, Date son, String text) {
        String query = "select * from " + text + " where userId = "+LoginController.userId+" and date between '" + basla + "' and '" + son + "'";
        List<Object> list = new ArrayList<>();
        try {
            connect();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Income income = new Income();
                income.setId(resultSet.getInt("id"));
                income.setAmount(resultSet.getDouble("amount"));
                income.setNote(resultSet.getString("note"));
                income.setKateqoriya(resultSet.getString("category"));
                income.setDate(resultSet.getDate("date"));
                list.add(income);
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

    public double sum(String text) {
        String query = "select sum(amount) sum from " + text + " where userId = " + LoginController.userId;
        try {
            connect();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("sum");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (Exception e) {
            }
        }
        return 0.0;
    }

    public Object sumFilter(Date basla, Date son, String text) {
        String query = "select sum(amount) sum from " + text + " where userId="+LoginController.userId+" and date between '" + basla + "' and '" + son + "'";
        try {
            connect();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("sum");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (Exception e) {
            }
        }
        return 0.0;
    }

    public ArrayList<Plan> allPlans(Plan selectPlan) {
        String query = "select * from plan inner join plans on plan.id = plans.planID left join (SELECT category, sum(amount) sum FROM balance.expense where userId = "+LoginController.userId+" and date between '"+selectPlan.getStartDate()+"' and '"+selectPlan.getEndDate()+"' group by category) as expense on plans.category = expense.category where plan.userId = "+LoginController.userId+" and plans.userId = "+LoginController.userId + " and plan.id ="+selectPlan.getId();
        ArrayList<Plan> list = new ArrayList<>();
        try {
            connect();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Plan plan = new Plan();
                plan.setId(resultSet.getInt("plan.id"));
                plan.setName(resultSet.getString("plan.name"));
                plan.setStartDate(resultSet.getDate("plan.startDate"));
                plan.setEndDate(resultSet.getDate("plan.endDate"));
                plan.setTotalAmount(resultSet.getDouble("plan.totalAmount"));
                plan.setKateqoriya(resultSet.getString("plans.category"));
                plan.setAmount(resultSet.getDouble("plans.amount"));
                plan.setXerc(resultSet.getDouble("expense.sum"));
                plan.setFaiz(plan.getXerc()*100/plan.getAmount());
                plan.setBar(progressBar(plan));
                if (plan.getFaiz()<33) {
                    plan.setTemp("Yaxşı");
                } else if (plan.getFaiz()>33 && plan.getFaiz()<66) {
                    plan.setTemp("Orta");
                } else {
                    plan.setTemp("Kafi");
                }
                list.add(plan);
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

    public ArrayList<Plan> plans() {
        String query = "select * from plan where userId = "+LoginController.userId;
        ArrayList<Plan> list = new ArrayList<>();
        try {
            connect();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {                
                Plan plan = new Plan();
                plan.setId(resultSet.getInt("id"));
                plan.setName(resultSet.getString("name"));
                plan.setStartDate(resultSet.getDate("startDate"));
                plan.setEndDate(resultSet.getDate("endDate"));
                plan.setTotalAmount(resultSet.getDouble("totalAmount"));
                list.add(plan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                disconnect();
            } catch (Exception e) {
            }
        }
        return list;
    }

    public ProgressBar progressBar(Plan plan){
        ProgressBar progressBar = new ProgressBar();
        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressBar.setProgress(plan.getFaiz()/100);
        progressBar.setMinWidth(160);
//        progressBar.getStylesheets().add(".progress-bar .bar");
        progressIndicator.setProgress(plan.getFaiz()/100);
        
        return progressBar;
    }
}
