package metier;

public class LigneCommande {
    public Produit produit;
    public int quantite;
    
    public LigneCommande(Produit produit,int quantite) {
        this.produit = produit;
        this.quantite=quantite;
    }
    public Produit geProduit(){
        return this.produit;
    }
    public int getQuantite(){
        return this.quantite;
    }

    public float getMontant(){
        return this.produit.getPrix()*this.quantite;
    }

    public int getStock(){ return this.produit.getQuantiteEnStock()-this.quantite ;}

}

