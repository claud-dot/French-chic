package metier;

import java.util.ArrayList;

public class Produit {
    private String reference;
    private String libelle;
    private float prix;
    private boolean estDuJour;
    private String[] motCles;
    private int quantiteEnStock;
    private static Produit[] lesProduits;

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

    public static Produit[] getLesProduits() {
        return lesProduits;
    }

    public static void setLesProduits(Produit[] lesProduits) {
        Produit.lesProduits = lesProduits;
    }

    public static void initializeProduits(){
        lesProduits = new Produit[]{
            new Produit("prod-001" ,"Cravates", 15, false , new String[]{"Chemise", "Costard"},200),
            new Produit("prod-002", "Pantalon jean", 25, true, new String[]{"pantalon","jean"}, 300),
            new Produit("prod-003", "Cabas Gucci Blondie petite taille", 50, false, new String[]{"Cab","Gucci"}, 150 )
        };
    }
}
