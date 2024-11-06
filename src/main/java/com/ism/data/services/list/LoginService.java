package com.ism.data.services.list;

import com.ism.core.dataSource.DataSourceImpl;
import com.ism.data.entities.Client;
import com.ism.data.entities.User;
import jakarta.persistence.EntityManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {

    private DataSourceImpl dataSource;
    private EntityManager em;

    public LoginService(DataSourceImpl dataSource) {
        this.dataSource = dataSource;
    }

    // public boolean validateUser(String login, String password) {
    //     String sql = "SELECT 1 FROM \"user\" WHERE \"login\" = ? AND \"password\" = ?";
    //     try (Connection connection = dataSource.connexion();
    //          PreparedStatement stmt = connection.prepareStatement(sql)) {
    //         stmt.setString(1, login);
    //         stmt.setString(2, password);
    //         ResultSet resultSet = stmt.executeQuery();
    //         return resultSet.next();
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //         return false;
    //     }
    // }

    public User validateUser(String login, String password) {
        String sql = "SELECT 1 FROM \"user\" WHERE \"login\" = ? AND \"password\" = ?";
        
        User user =null;
        try (Connection connection = dataSource.connexion();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, password);
            user = em.createQuery(sql,User.class).getSingleResult();

            // ResultSet resultSet = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return user;
    }

    public Client getConnectedClient(int userId) {
        String sql = "SELECT * FROM client WHERE user_id = ?";
        try (Connection connection = dataSource.connexion();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setPrenom(resultSet.getString("prenom"));
                client.setNom(resultSet.getString("nom"));
                return client;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
