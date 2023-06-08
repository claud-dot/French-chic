package controleur;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.*;

public class AccessBase {
    public AccessBase() {

    }

    private static Connection connection;

    public static Connection getConnection(){
        Connection connection = null;
        try {
            URL resourceUrl = AccessBase.class.getClassLoader().getResource("vente_stock.db");
            if (resourceUrl == null) {
                throw new RuntimeException("Fichier de base de données introuvable !");
            }
            String chemin = resourceUrl.getPath();
//            File file = new File("test.txt");
////            file.createNewFile();
//            if (!file.exists()) file

//            AccessBase.class.getClassLoader().get

            // Créer un fichier temporaire pour extraire la base de données SQLite
//            File tempFile = File.createTempFile("temp_db", ".db");
            File file = new File("vente_stock.db");
            if (!file.exists()) {
                System.out.println("mandeha");
                file.createNewFile();
                // Extraire la base de données SQLite du JAR vers le fichier temporaire
                try (InputStream inputStream = AccessBase.class.getClassLoader().getResourceAsStream("vente_stock.db");
                     OutputStream outputStream = new FileOutputStream(file)) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
            }

//            String tempFile = System.getProperty("java.io.tmpdir" ) + "vente_stock.db";

//            System.out.println(AccessBase.class.getClassLoader().getResourceAsStream("vente_stock.db"));

            // Charger le pilote SQLite
            Class.forName("org.sqlite.JDBC");

            // URL de connexion à la base de données SQLite
//            String url = "jdbc:sqlite:" + tempFile.getAbsolutePath();
            String url = "jdbc:sqlite:" + file.getAbsolutePath();

            // Établir la connexion à la base de données SQLite
            connection = DriverManager.getConnection(url);
//            connection = DriverManager.getConnection("jdbc:sqlite:"+chemin);

            // Supprimer le fichier temporaire après utilisation
//            tempFile.delete();

//            Files.deleteIfExists();
        } catch (Exception  e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Exception", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
        return connection;
    }
}
