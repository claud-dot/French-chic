package metier;

public class Commande {
    private float montant;
    private LigneCommande[] lesCommandes;
    
    public Commande(LigneCommande[] ligneCommandes) {
        this.lesCommandes = ligneCommandes;
        for(int i=0;i<ligneCommandes.length;i++){
            this.montant+=ligneCommandes[i].produit.getPrix()*ligneCommandes[i].quantite;
        }
    }
    
    public float getMontant() {
        return this.montant;
    }
    
    public void setMontant(float montant) {
        this.montant = montant;
    }
    
    public LigneCommande[] getLesCommandes() {
        return this.lesCommandes;
    }
    
    public void ajouterCommande(LigneCommande commande) {
        LigneCommande[] newLesCommandes = new LigneCommande[this.lesCommandes.length + 1];
        for (int i = 0; i < this.lesCommandes.length; i++) {
            newLesCommandes[i] = this.lesCommandes[i];
        }
        newLesCommandes[this.lesCommandes.length] = commande;
        this.lesCommandes = newLesCommandes;
    }

}

