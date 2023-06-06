package controleur;
import metier.*;

import java.util.List;

public class TraiterIdentificationReponse {
    public EnumTypeEcran typeEcran;
    public Client leClient;
    public List<Produit> lesProduit;
    public Produit leProduit;
    
    // Constructeur
    public TraiterIdentificationReponse(EnumTypeEcran typeEcran, Client client, List<Produit> lesProduit , Produit lePoruit) {
        this.typeEcran = typeEcran;
        this.leClient = client;
        this.lesProduit = lesProduit;
        this.leProduit = lePoruit;
    }
}
