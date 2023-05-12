package metier;

import java.util.ArrayList;

public class Produit {
    private String reference;
    private String libelle;
    private float prix;
    private boolean estDuJour;
    private String[] motCles;
    private int quantiteEnStock;
    private static ArrayList<Produit> lesProduits = new ArrayList<Produit>();

    public Produit(){
        this.libelle="produit de test";
        this.prix=1000;
    }
    public Produit(String reference, String libelle, float prix, boolean estDuJour, String[] motCles, int quantiteEnStock) {
        this.reference = reference;
        this.libelle = libelle;
        this.prix = prix;
        this.estDuJour = estDuJour;
        this.motCles = motCles;
        this.quantiteEnStock = quantiteEnStock;
        lesProduits.add(this);
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

    public float getPrix() {
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

    public String[] getMotCles() {
        return motCles;
    }

    public void setMotCles(String[] motCles) {
        this.motCles = motCles;
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

}
