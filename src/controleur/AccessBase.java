package controleur;

import metier.Client;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccessBase {
    public AccessBase() {

    }

    public Connection getConnection(){
        Connection connection = null;
        try {
            URL resourceUrl = AccessBase.class.getClassLoader().getResource("vente_stock.db");
            if (resourceUrl == null) {
                throw new RuntimeException("Fichier de base de données introuvable !");
            }
            String chemin = resourceUrl.getPath();
            connection = DriverManager.getConnection("jdbc:sqlite:"+chemin);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public List<Client> getClients(){
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        List<Client> clients = new ArrayList<Client>();
        try {
            connection = this.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM client");
            // Parcourir les résultats
            while (resultSet.next()) {
                // Traiter les données
                int id = resultSet.getInt("idclient");
                String nom = resultSet.getString("nom");
                clients.add(new Client(id,nom));
                // ...
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermer le ResultSet, le Statement et la connexion
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return clients;
    }
}
