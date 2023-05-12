package controleur;

import metier.Client;
import metier.Produit;

public class Session {
    public EnumTypeEcran ecranCourant=EnumTypeEcran.ECRAN_ACCUEIL;

    public TraiterConnexionReponse traiterConnexion() {
        // méthode permettant de traiter la connexion de l'utilisateur
        // et de retourner l'écran courant (par exemple ECRAN_ACCUEIL)
        return new TraiterConnexionReponse(ecranCourant);
    }

    public TraiterIdentificationReponse traiterIdentification(String pseudo, String motDePasse) {
        // méthode permettant de traiter l'identification de l'utilisateur
        // en utilisant les informations de connexion pseudo et mot de passe
        // et de retourner l'écran courant (par exemple ECRAN_ACCUEIL_PERSO)
        // ainsi que les informations du client et du produit (en tant que paramètres)

        Client clients=new Client();
        for (Client client : Client.getLesClients()) {
            if (client.getPseudo().trim().equals(pseudo) && client.getMotDePasse().trim().equals(motDePasse)) {
                this.ecranCourant=EnumTypeEcran.ECRAN_ACCUEIL_PERSO;
                return new TraiterIdentificationReponse(ecranCourant, client, new Produit());
            }
        }
        return new TraiterIdentificationReponse(ecranCourant, null, null);
        
    }
}
