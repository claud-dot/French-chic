package controleur;
import metier.*;
public class TraiterAjoutPanierReponse {
    public EnumTypeEcran typeEcran;
    public Commande laCommande;
    
    public TraiterAjoutPanierReponse(EnumTypeEcran typeEcran, Commande laCommande) {
        this.typeEcran = typeEcran;
        this.laCommande = laCommande;
    }
}

