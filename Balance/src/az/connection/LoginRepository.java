/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.connection;

import az.controller.LoginController;
import az.model.User;

/**
 *
 * @author SS555
 */
public class LoginRepository extends DataManager{
    
    public int addUser(User user) {
        String query = "insert into users (name, surname, username, email, password) values (?,?,?,?,?)";
        try {
            connect();
            statement = connection.prepareStatement(query, new String[]{"id"});
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                disconnect();
            } catch (Exception e) {
            }
        }
        return 0;
    }

    public User checkUser(User user) {
        String query = "select * from users where username = ? and password = ?";
        try {
            connect();
            statement = connection.prepareStatement(query);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
            } else {
                user = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (Exception e) {
            }
        }
        return user;
    }

    public void addInfo(User user) {
        String query = "insert into info (name, cem, userId) values (?,?,?)";
        try {
            connect();
            statement = connection.prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setDouble(2, 0.0);
            statement.setInt(3, user.getId());
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                disconnect();
            } catch (Exception e) {
            }
        }
    }
    
}
