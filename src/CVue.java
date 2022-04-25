import com.sun.source.tree.PackageTree;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

class   CVue {  // affiche les graphiques

    public static JFrame frame;
    public static JFrame frame1;

    private VueGrille grille;
    private VueCommandes commandes;

    private VueAction action;
    private VueJoueur joueur;

    private VueDataPlayer dataPlayer;

    public CVue(CModele modele) throws IOException {
        frame = new JFrame("Jeu de la vie de Conway");
        frame1 = new JFrame("Init");

        frame.setLayout(null);
        frame1.setLayout(null);

        frame.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
        frame1.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));


        grille = new VueGrille(modele);
        grille.setBounds(200,15,120*CModele.HAUTEUR,100*CModele.LARGEUR);
        frame.add(grille);

        commandes = new VueCommandes(modele);
        commandes.setBounds(200+120*CModele.HAUTEUR,50*CModele.LARGEUR,260,300);
        frame.add(commandes);

        action = new VueAction(modele);
        action.setBounds(200+20*CModele.HAUTEUR,105*CModele.LARGEUR,1000,60);
        frame.add(action);

        joueur = new VueJoueur(modele);
        joueur.setBounds(10,10,180,700);
        frame.add(joueur);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(false);

        dataPlayer = new VueDataPlayer(modele);
        dataPlayer.setBounds(200, 15, 120 * CModele.HAUTEUR, 100 * CModele.LARGEUR);
        frame1.add(dataPlayer);

        frame1.pack();
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(false);
    }
}