package metier;

import controleur.AccessBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Produit {
    private int idproduit;
    private String reference;
    private String libelle;
    private double prix;
    private boolean estDuJour;
    private String[] motCles;
    private int quantiteEnStock;
    private static ArrayList<Produit> lesProduits;

    public Produit(){
        initializeProduits();
    }

    public int getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Produit(String reference, String libelle, float prix, boolean estDuJour, String[] motCles, int quantiteEnStock) {
        this.reference = reference;
        this.libelle = libelle;
        this.prix = prix;
        this.estDuJour = estDuJour;
        this.motCles = motCles;
        this.quantiteEnStock = quantiteEnStock;
    }

    public Produit(int id , String libelle , double prix, int duJour , int qtStock){
        this.idproduit = id;
        this.libelle = libelle;
        this.prix = prix;
        this.estDuJour = duJour == 0;
        this.quantiteEnStock = qtStock;
    }

    public static Produit rechercherProduitDuJour() {
        for (Produit p : lesProduits) {
            if (p.estDuJour) {
                return p;
            }
        }
        return null;
    }

    public static ArrayList<Produit> rechercherProduitParMotCle(String motCle) {
        ArrayList<Produit> produitsTrouves = new ArrayList<Produit>();
        for (Produit p : lesProduits) {
            for (String mc : p.motCles) {
                if (mc.equalsIgnoreCase(motCle)) {
                    produitsTrouves.add(p);
                    break;
                }
            }
        }
        return produitsTrouves;
    }

    public void retirerDuStock(int quantite) {
        this.quantiteEnStock -= quantite;
    }

    // Getters et setters
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public boolean isEstDuJour() {
        return estDuJour;
    }

    public void setEstDuJour(boolean estDuJour) {
        this.estDuJour = estDuJour;
    }

    public int getQuantiteEnStock() {
        return quantiteEnStock;
    }

    public void setQuantiteEnStock(int quantiteEnStock) {
        this.quantiteEnStock = quantiteEnStock;
    }

    public static ArrayList<Produit> getLesProduits() {
        return lesProduits;
    }

    public static void setLesProduits(ArrayList<Produit> lesProduits) {
        Produit.lesProduits = lesProduits;
    }

    public void initializeProduits(){
        Produit produit1 = new Produit("REF001", "Cravate", 20f, true, new String[] {"chemise", "cravate"}, 50);
        Produit produit2 = new Produit("REF002", "Pantalon jean", 30f, true, new String[] {"pantalon", "jean"}, 20);
        Produit produit3 = new Produit("REF003", "Cabas Gucci Blondie petite taille", 50f, false, new String[] {"Gucci", "Cabas"}, 10);
        lesProduits=new ArrayList<>();
        lesProduits.add(produit1);
        lesProduits.add(produit2);
        lesProduits.add(produit3);
    }

    public Produit getProduitDuJour(){
        for (Produit produit : this.avoirTousLesProduits()) {
            if(produit.estDuJour)return produit;
        }
        return null;
    }

    public List<Produit> avoirTousLesProduits(){
        List<Produit> listProd = new ArrayList<Produit>();
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = AccessBase.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Produit");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String libelle = resultSet.getString("libelle");
                double prix = resultSet.getDouble("prix");
                int duJour = resultSet.getInt("estDuJour");
                int qt = resultSet.getInt("quantiteEnStock");
                System.out.println("id : "+id +" libel :"+ libelle+" ");
                listProd.add(new Produit(id , libelle ,prix ,duJour ,qt));
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
        return listProd;
    }

}
