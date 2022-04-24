import com.sun.source.tree.PackageTree;

import javax.swing.*;
import java.awt.*;

class   CVue {

    private JFrame frame;

    private VueGrille grille;
    private VueCommandes commandes;

    private VueAction action;

    public CVue(CModele modele) {
        frame = new JFrame("Jeu de la vie de Conway");

        frame.setLayout(null);
        frame.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));

        grille = new VueGrille(modele);
        grille.setBounds(200,15,120*CModele.HAUTEUR,100*CModele.LARGEUR);
        frame.add(grille);

        commandes = new VueCommandes(modele);
        commandes.setBounds(200+120*CModele.HAUTEUR,50*CModele.LARGEUR,260,130);
        frame.add(commandes);

        action = new VueAction(modele);
        action.setBackground(Color.MAGENTA);
        action.setBounds(200+20*CModele.HAUTEUR,105*CModele.LARGEUR,200,60);
        frame.add(action);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}