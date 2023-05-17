package view;

/*import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;*/

import java.text.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controleur.*;
import metier.*;

public class VueJetable {

    static Session laSession;
    static JFrame frame;

    public static void main(String[] args) {
        initialize();

        laSession = new Session();
        TraiterConnexionReponse reponse = laSession.traiterConnexion();
        if (reponse.typeEcran == EnumTypeEcran.ECRAN_ACCUEIL) {
            afficherEcranAccueil();
        }

    }

    private static void afficherEcranAccueil() {
        frame = new JFrame();
        frame.setTitle("French Chic - Accueil");
        frame.setSize(650, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        JPanel accueilPanel = new JPanel();
        accueilPanel.setBackground(Color.WHITE);
        frame.setContentPane(accueilPanel);
        frame.setLayout(null);
        //initialiserPanel(); 

        JLabel title = new JLabel("French Chic");
        title.setLocation(150, 50);
        title.setSize(1000, 100);
        Font f = new Font("", Font.PLAIN, 70);
        title.setFont(f);
        title.setForeground(Color.MAGENTA);

        JLabel pseudoLabel = null;
        JLabel mdpLabel = null;

        pseudoLabel = new JLabel("Pseudo");
        pseudoLabel.setSize(120, 20);
        pseudoLabel.setLocation(150, 200);
        mdpLabel = new JLabel("Mot de passe");
        mdpLabel.setSize(120, 20);
        mdpLabel.setLocation(150, 250);

        int longueur = 200;
        int largeur = 30;

        final JTextField pseudoField;
        final JTextField mdpField;

        pseudoField = new JTextField();
        pseudoField.setSize(longueur, largeur);
        pseudoField.setLocation(250, 200);
        mdpField = new JPasswordField();
        mdpField.setSize(longueur, largeur);
        mdpField.setLocation(250, 250);
        JButton login = new JButton("S'identifier");
        login.setLocation(250, 300);
        login.setSize(longueur, largeur);

        login.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                TraiterIdentificationReponse reponse = laSession.traiterIdentification(pseudoField.getText(), mdpField.getText());
                frame.setVisible(false);
                if (reponse.typeEcran == EnumTypeEcran.ECRAN_ACCUEIL_PERSO) {
                    afficherEcranAccueilPerso(reponse.leClient, reponse.leProduit);
                }
            }
        });

        frame.add(title);
        frame.add(pseudoLabel);
        frame.add(mdpLabel);
        frame.add(pseudoField);
        frame.add(mdpField);
        frame.add(login);
        frame.setVisible(true);

    }

    private static void afficherEcranAccueilPerso(final Client client, final Produit produit) {
        frame = new JFrame();
        frame.setTitle("French Chic - Produit du jour");
        frame.setSize(650, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        JPanel accueilPanel = new JPanel();
        accueilPanel.setBackground(Color.WHITE);
        frame.setContentPane(accueilPanel);
        frame.setLayout(null);

        JLabel title = new JLabel("French Chic");
        title.setLocation(150, 50);
        title.setSize(1000, 100);
        Font f = new Font("", Font.PLAIN, 70);
        title.setFont(f);
        title.setForeground(Color.MAGENTA);

        JLabel bonjourTexte = null;
        JLabel produitDuJourTexte = null;
        JLabel qtEnStockLabel = null;
        JLabel quantiteLabel = null;

        String bonjourTxt = "Bonjour " + client.getPrenom() + " " + client.getNom();
        bonjourTexte = new JLabel(bonjourTxt);
        bonjourTexte.setSize(250, 20);
        bonjourTexte.setLocation(150, 200);

        String produitTxt = "Le produit du jour est le \"" + produit.getLibelle() + "\" au prix de " + produit.getPrix() + " €";
        produitDuJourTexte = new JLabel(produitTxt);
        produitDuJourTexte.setSize(500, 20);
        produitDuJourTexte.setLocation(150, 250);

        String qtProduit = "Quantité en stock : "+produit.getQuantiteEnStock();
        qtEnStockLabel = new JLabel(qtProduit);
        qtEnStockLabel.setSize(200 , 20);
        qtEnStockLabel.setLocation(150 , 268);

        quantiteLabel = new JLabel("Quantite");
        quantiteLabel.setSize(120, 20);
        quantiteLabel.setLocation(250, 325);

        int longueur = 200;
        int largeur = 30;

        final JTextField quantiteField;

        quantiteField = new JTextField();
        quantiteField.setSize(longueur, largeur);
        quantiteField.setLocation(320, 320);
        quantiteField.setSize(50, largeur);

        JButton ajouterProduit = new JButton("Ajouter le produit du jour au panier");
        ajouterProduit.setLocation(250, 370);
        ajouterProduit.setSize(longueur, largeur);

        ajouterProduit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                Integer intg = new Integer(quantiteField.getText());
                TraiterAjoutPanierReponse reponse = laSession.traiterAjoutPanier(produit, intg);
                frame.setVisible(false);
                if (reponse.typeEcran == EnumTypeEcran.ECRAN_PANIER) {
                    afficherEcranPanier(reponse.laCommande);
                }
            }
        });

        frame.add(title);
        frame.add(bonjourTexte);
        frame.add(produitDuJourTexte);
        frame.add(qtEnStockLabel);
        frame.add(quantiteField);
        frame.add(quantiteLabel);
        frame.add(ajouterProduit);
        frame.setVisible(true);
    }

    private static void afficherEcranPanier(Commande laCommande) {
        frame = new JFrame();
        frame.setTitle("French Chic - Panier");
        frame.setSize(650, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        JPanel accueilPanel = new JPanel();
        accueilPanel.setBackground(Color.WHITE);
        frame.setContentPane(accueilPanel);
        frame.setLayout(null);

        JLabel title = new JLabel("Votre Panier");
        title.setLocation(30, 20);
        title.setSize(1000, 100);
        Font f = new Font("", Font.PLAIN, 50);
        title.setFont(f);
        title.setForeground(Color.MAGENTA);

        LigneCommande ligneC = laCommande.getLesCommandes()[0];
//        NumberFormat nf = NumberFormat.getInstance(Locale.FRENCH);
//        nf.setMinimumFractionDigits(2);

        String prixHTLg = ligneC.geProduit().getPrix()+"";
        String montantLg = ligneC.getMontant()+"";
        int stock = ligneC.getStock();

        String[] entetes = {"Libellé", "Prix", "Quantité", "Montant" , "Stock"};

        Object[][] donnees = {
            {ligneC.geProduit().getLibelle(), prixHTLg+" €", new Integer(ligneC.getQuantite()).toString(), montantLg+" €" , stock},};

        DefaultTableModel model = new DefaultTableModel(donnees, entetes);
        JTable table = new JTable(model);
        //entete
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setDefaultRenderer(new CustomHeaderRenderer());
        Font tableFont = table.getFont();
        Font resizedFont = tableFont.deriveFont(tableFont.getSize() + 2f); // Ajustez la taille de la police selon vos préférences
        table.setFont(resizedFont);
        //Row
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int columnIndex = 0; columnIndex < table.getColumnCount(); columnIndex++) {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        Dimension preferredSize = new Dimension(600, 200);
        scrollPane.setPreferredSize(preferredSize);
        JPanel paneTab = new JPanel();
        paneTab.setLocation(30, 150);
        paneTab.setSize(600, 200);
        paneTab.setBackground(Color.WHITE);
        paneTab.add(scrollPane);

        JLabel montantLabel = null;

        montantLabel = new JLabel("Montant panier");
        montantLabel.setSize(120, 20);
        montantLabel.setLocation(250, 423);

        int longueur = 200;
        int largeur = 30;

        final JTextField montantField;

        montantField = new JTextField();
        montantField.setSize(longueur, largeur);
        montantField.setLocation(350, 420);
        montantField.setSize(100, largeur);

        String total = laCommande.getMontant()+"";

        String montantTxt = String.valueOf(total) + " €";
        montantField.setText(montantTxt);
        montantField.setEditable(false);

        frame.add(title);
        frame.add(montantField);
        frame.add(montantLabel);
        frame.add(paneTab);
        frame.setVisible(true);
    }
    
    private static void initialize(){
        // Client.initializeClients();
        // Commande.initializeCommandes();
        // Produit.initializeProduits();
    }

    static class CustomHeaderRenderer extends DefaultTableCellRenderer {
        public CustomHeaderRenderer() {
            setHorizontalAlignment(SwingConstants.CENTER);
            setBackground(Color.MAGENTA);
            setForeground(Color.WHITE);
            Font font = getFont();
            setFont(font.deriveFont(font.getStyle() | Font.BOLD));
        }
    }
}


