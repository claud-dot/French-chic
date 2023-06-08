package metier;

public class Client {
    private int idClient;
    private String numero;
    private String nom;
    private String prenom;
    private String adresseFacturation;
    private String pseudo;
    private String adresseLivraison;
    private String motDePasse;
    private static Client[] lesClients;

    public Client(){
        initializeClients();
    };

    public  Client(int id , String nom){
        this.idClient = id;
        this.nom = nom;
    }

    public Client(String numero, String nom, String prenom, String adresseFacturation, String pseudo,
                  String adresseLivraison, String motDePasse) {
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.adresseFacturation = adresseFacturation;
        this.pseudo = pseudo;
        this.adresseLivraison = adresseLivraison;
        this.motDePasse = motDePasse;
        
    }

    public static Client rechercherClientParPseudo(String pseudo, String motDePasse) {
        for (Client client : lesClients) {
            if (client.getPseudo().equals(pseudo) && client.getMotDePasse().equals(motDePasse)) {
                return client;
            }
        }
        return null;
    }

    public static Client creerClient() {
        // code pour créer un nouveau client
        return null;
    }

    public static void offrirChequeCadeau(float montant) {
        // code pour offrir un chèque cadeau
    }

    // getters et setters
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresseFacturation() {
        return adresseFacturation;
    }

    public void setAdresseFacturation(String adresseFacturation) {
        this.adresseFacturation = adresseFacturation;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getAdresseLivraison() {
        return adresseLivraison;
    }

    public void setAdresseLivraison(String adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public static Client[] getLesClients() {
        return lesClients;
    }

    public static void setLesClients(Client[] lesClients) {
        Client.lesClients = lesClients;
    }
    
    public void initializeClients(){
        lesClients = new Client[]{
            new Client("1", "Dupont", "Jean", "1 rue des Lilas, 75000 Paris", "jdupont", "2 rue des Roses, 75000 Paris", "mdp1"),
            new Client("2", "Martin", "Sophie", "3 rue des Tulipes, 69000 Lyon", "smartin", "4 rue des Violettes, 69000 Lyon", "mdp2"),
            new Client("3", "Lefevre", "Julien", "5 rue des Marguerites, 31000 Toulouse", "jlefevre", "6 rue des Jonquilles, 31000 Toulouse", "mdp3"),
            new Client("4", "Dubois", "Marie", "7 rue des Orchidées, 44000 Nantes", "mdubois", "8 rue des Lys, 44000 Nantes", "mdp4"),
            new Client("5", "Leroy", "Pierre", "9 rue des Glycines, 76000 Rouen", "pleroy", "10 rue des Chrysanthèmes, 76000 Rouen", "mdp5"),
            new Client("6", "Moreau", "Lucie", "11 rue des Pivoines, 67000 Strasbourg", "lmoreau", "12 rue des Coquelicots, 67000 Strasbourg", "mdp6"),
            new Client("7", "Girard", "Thomas", "13 rue des Lilas, 59000 Lille", "tgirard", "14 rue des Roses, 59000 Lille", "mdp7"),
            new Client("8", "Lecomte", "Céline", "15 rue des Tulipes, 35000 Rennes", "clecomte", "16 rue des Violettes, 35000 Rennes", "mdp8"),
            new Client("9", "Rousseau", "Hélène", "17 rue des Marguerites, 57000 Metz", "hrousseau", "18 rue des Jonquilles, 57000 Metz", "mdp9"),
            new Client("10", "Brun", "Antoine", "19 rue des Orchidées, 69001 Lyon", "abrun", "20 rue des Lys, 69001 Lyon", "mdp10")
        };
    }
}
