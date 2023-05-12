package controleur;
import metier.*;
public class TraiterIdentificationReponse {
    public EnumTypeEcran typeEcran;
    public Client leClient;
    public Produit leProduit;
    
    // Constructeur
    public TraiterIdentificationReponse(EnumTypeEcran typeEcran, Client client, Produit produit) {
        this.typeEcran = typeEcran;
        this.leClient = client;
        this.leProduit = produit;
    }
}
