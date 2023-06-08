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

import java.sql.Connection;
import java.text.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
//            AccessBase.getConnection();
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
                    afficherEcranAccueilPerso(reponse.leClient , reponse.lesProduit , reponse.leProduit);
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

    private static void afficherEcranAccueilPerso(final Client client, final List<Produit> produits , Produit produit) {
//        JOptionPane.showMessageDialog(null, "Fichier de base de données introuvable !", "Alerte", JOptionPane.WARNING_MESSAGE);
        frame = new JFrame();
        frame.setTitle("French Chic - les Produits");
        frame.setSize(650, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        JPanel accueilPanel = new JPanel();
        accueilPanel.setBackground(Color.WHITE);
        frame.setContentPane(accueilPanel);
        frame.setLayout(null);

        JLabel title = new JLabel("French Chic");
        title.setLocation(80, 20);
        title.setSize(1000, 100);
        Font f = new Font("", Font.PLAIN, 55);
        title.setFont(f);
        title.setForeground(Color.MAGENTA);

        JLabel bonjourTexte = null;
        JLabel produitDuJourTexte = null;
        JLabel produitChoix = null;
        JLabel qtEnStockLabel = null;
        JLabel qtEnStockLabelChx = null;
        JLabel quantiteLabel = null;

        String bonjourTxt = "Bonjour " + client.getPrenom() + " " + client.getNom();
        bonjourTexte = new JLabel(bonjourTxt);
        bonjourTexte.setSize(250, 20);
        bonjourTexte.setLocation(115, 125);

        String produitTxt = "Le produit du jour est le \"" + produit.getLibelle() + "\" au prix de " + produit.getPrix() + " €";
        produitDuJourTexte = new JLabel(produitTxt);
        produitDuJourTexte.setSize(500, 20);
        produitDuJourTexte.setLocation(115, 165);

        String qtProduit = "Quantité en stock : "+produit.getQuantiteEnStock();
        qtEnStockLabel = new JLabel(qtProduit);
        qtEnStockLabel.setSize(200 , 20);
        qtEnStockLabel.setLocation(115 , 188);

//        TABLEAU
        int lineDuJour = 0;
        String[] entetes = {"Libellé", "Prix", "Stock"};
        Object[][] donnees = new Object[produits.size()][3];
        for (int i = 0; i <produits.size() ; i++) {
            if(produits.get(i).isEstDuJour()) lineDuJour = i;
            donnees[i][0] = produits.get(i).getLibelle();
            donnees[i][1] = produits.get(i).getPrix()+" €";
            donnees[i][2] = produits.get(i).getQuantiteEnStock();
        }

        DefaultTableModel model = new DefaultTableModel(donnees, entetes);
        JTable table = new JTable(model);
        //entete
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setDefaultRenderer(new CustomHeaderRenderer());
        Font tableFont = table.getFont();
        Font resizedFont = tableFont.deriveFont(tableFont.getSize() + 2f);
        table.setFont(resizedFont);
        //Row
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int columnIndex = 0; columnIndex < table.getColumnCount(); columnIndex++) {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
        }

        CustomRowRenderer rowRenderer = new CustomRowRenderer();
        rowRenderer.setRowToColor(lineDuJour); // Spécifiez le numéro de la ligne à colorer
        for (int columnIndex = 0; columnIndex < table.getColumnCount(); columnIndex++) {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(rowRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        Dimension preferredSize = new Dimension(460, 92);
        scrollPane.setPreferredSize(preferredSize);
        JPanel paneTab = new JPanel();
        paneTab.setLocation(115, 230);
        paneTab.setSize(460, 92);
        paneTab.setBackground(Color.WHITE);
        paneTab.add(scrollPane);

        //Hide element
        JPanel paneAdd = new JPanel(null);
        paneAdd.setLocation(115, 335);
        paneAdd.setSize(460, 80);
        paneAdd.setBackground(Color.white);

        String produitChx = "Le produit choisi est le \"" + produit.getLibelle() + "\" au prix de " + produit.getPrix() + " €";
        produitChoix = new JLabel(produitChx);
        produitChoix.setBounds(0, 0, 460, 20);

        String qtChx = "Quantité en stock : "+produit.getQuantiteEnStock();
        qtEnStockLabelChx = new JLabel(qtChx);
        qtEnStockLabelChx.setBounds(0, 22, 200, 20);

        quantiteLabel = new JLabel("Quantite");
        quantiteLabel.setBounds(0, 48, 120, 20);

        int longueur = 200;
        int largeur = 30;

        final JTextField quantiteField;

        quantiteField = new JTextField();
        quantiteField.setSize(longueur, largeur);
        quantiteField.setBounds(130, 48, 50, largeur);

        JButton ajouterProduit = new JButton("Ajouter le produit choisi au panier");
        ajouterProduit.setBounds(210, 48, longueur, largeur);

        paneAdd.add(produitChoix);
        paneAdd.add(qtEnStockLabelChx);
        paneAdd.add(quantiteField);
        paneAdd.add(quantiteLabel);
        paneAdd.add(ajouterProduit);
        paneAdd.setVisible(false);

        JLabel finalProduitChoix = produitChoix;
        JLabel finalQtEnStockLabelChx = qtEnStockLabelChx;

        final int[] idSelect = {0};
        //Pour avoir l'info du tabeau lor d'un click
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        String produitChx = "Le produit choisi est le \"" + produits.get(selectedRow).getLibelle() + "\" au prix de " + produits.get(selectedRow).getPrix() + " €";
                        finalProduitChoix.setText(produitChx);
                        String qtChx = "Quantité en stock : " + produits.get(selectedRow).getQuantiteEnStock();
                        finalQtEnStockLabelChx.setText(qtChx);
                        paneAdd.setVisible(true);
                        idSelect[0] = selectedRow;
                        //Pour pouboir ajouter le produit choisi
                    }else{
                        paneAdd.setVisible(false);
                    }
                }
            }
        });

        ajouterProduit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                Integer intg = new Integer(quantiteField.getText());
                TraiterAjoutPanierReponse reponse = laSession.traiterAjoutPanier(client.getId(),produits.get(idSelect[0]), intg);
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
        frame.add(paneTab);
        frame.add(paneAdd);
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

        String[] entetes = {"Libellé", "Prix", "Quantité", "Montant" , "Stock"};
        List<LigneCommande> ligneC = laCommande.getLesCommandes();
        Object[][] donnees =  new Object[ligneC.size()][5];
        for (int i = 0; i <ligneC.size() ; i++) {
            System.out.println(ligneC.get(i).geProduit().getLibelle());
            donnees[i][0] = ligneC.get(i).geProduit().getLibelle();
            donnees[i][1] = ligneC.get(i).geProduit().getPrix()+" €";
            donnees[i][2] = ligneC.get(i).getQuantite();
            donnees[i][3] = ligneC.get(i).getMontant()+" €";
            donnees[i][4] = ligneC.get(i).geProduit().getQuantiteEnStock();
        }
//        String prixHTLg = ligneC.geProduit().getPrix()+"";
//        String montantLg = ligneC.getMontant()+"";
//        int stock = ligneC.getStock();

        DefaultTableModel model = new DefaultTableModel(donnees, entetes);
        JTable table = new JTable(model);
        //entete
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setDefaultRenderer(new CustomHeaderRenderer());
        Font tableFont = table.getFont();
        Font resizedFont = tableFont.deriveFont(tableFont.getSize() + 2f);
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

    static class CustomRowRenderer extends DefaultTableCellRenderer {
        private int rowToColor;

        public void setRowToColor(int row) {
            this.rowToColor = row;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (row == rowToColor) {
                component.setBackground(Color.pink);
            } else {
                component.setBackground(table.getBackground());
            }
            return component;
        }
    }

}


