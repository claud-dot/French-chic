package controleur;

import java.net.URL;
import java.sql.*;

public class AccessBase {
    public AccessBase() {

    }

    public static Connection getConnection(){
        Connection connection = null;
        try {
            URL resourceUrl = AccessBase.class.getClassLoader().getResource("vente_stock.db");
            if (resourceUrl == null) {
                throw new RuntimeException("Fichier de base de données introuvable !");
            }
            String chemin = resourceUrl.getPath();
            connection = DriverManager.getConnection("jdbc:sqlite:"+chemin);
        } catch (SQLException  e) {
            e.printStackTrace();
        }
        return connection;
    }
}
