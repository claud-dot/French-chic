package metier;

import controleur.AccessBase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Commande {
    private int idcommande;
    private float montant;
    private List<LigneCommande> lesCommandes;
    
    public Commande(List<LigneCommande> ligneCommandes) {
        this.lesCommandes = ligneCommandes;
        for(int i=0;i<ligneCommandes.size();i++){
            this.montant+=ligneCommandes.get(i).produit.getPrix()*ligneCommandes.get(i).quantite;
        }
    }

    public Commande() {
    }

    public float getMontant() {
        return this.montant;
    }
    
    public void setMontant(float montant) {
        this.montant = montant;
    }
    
    public List<LigneCommande> getLesCommandes() {
        return this.lesCommandes;
    }
    
    public void ajouterCommande(LigneCommande commande, Connection connection) {
        System.out.println("idClient : "+commande.getIdclient());
        String sqlAjout = "INSERT INTO Commande (idclient,idproduit,quantite) VALUES (" + commande.getIdclient()  + ","+ commande.geProduit().getIdproduit()+","+commande.getQuantite()+")";
        String sqlModifie = "UPDATE Produit set quantiteEnStock= "+commande.getStock()+" WHERE id="+commande.geProduit().getIdproduit();
        Statement statement = null;
//        Connection connection = null;
        try {
//            connection = AccessBase.getConnection();
            statement = connection.createStatement();
            statement.execute(sqlAjout);
            statement.execute(sqlModifie);
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
//                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
//        LigneCommande[] newLesCommandes = new LigneCommande[this.lesCommandes.length + 1];
//        for (int i = 0; i < this.lesCommandes.length; i++) {
//            newLesCommandes[i] = this.lesCommandes[i];
//        }
//        newLesCommandes[this.lesCommandes.length] = commande;
//        this.lesCommandes = newLesCommandes;

    }

}

