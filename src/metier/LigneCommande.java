package metier;

import controleur.AccessBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LigneCommande {
    private int idclient;
    public Produit produit;
    public int quantite;
    
    public LigneCommande(int idclient , Produit produit,int quantite) {
        this.idclient = idclient;
        this.produit = produit;
        this.quantite=quantite;
    }
    public Produit geProduit(){
        return this.produit;
    }
    public int getQuantite(){
        return this.quantite;
    }

    public double getMontant(){
        return this.produit.getPrix()*this.quantite;
    }

    public int getStock(){ return this.produit.getQuantiteEnStock()-this.quantite ;}

    public int getIdclient() {
        return idclient;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    public static List<LigneCommande> avoirLigneCommande(int idclient){
        List<LigneCommande> ligneCommandes = new ArrayList<LigneCommande>();
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = AccessBase.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Commande_client WHERE idclient="+idclient);

            while (resultSet.next()) {
                int idClient = resultSet.getInt("idclient");
                int idproduit = resultSet.getInt("idproduit");
                String libelle = resultSet.getString("libelle");
                double prix = resultSet.getDouble("prix");
                int qt = resultSet.getInt("quantite");
                int qtStock = resultSet.getInt("quantiteEnStock");
                Produit produit = new Produit(idproduit ,libelle , prix ,1,qtStock);
                ligneCommandes.add(new LigneCommande(idClient , produit , qt));
            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ligneCommandes;
    }
}

