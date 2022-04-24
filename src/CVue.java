import com.sun.source.tree.PackageTree;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

class   CVue {

    private JFrame frame;

    private VueGrille grille;
    private VueCommandes commandes;

    private VueAction action;

    public CVue(CModele modele) throws IOException {
        frame = new JFrame("Jeu de la vie de Conway");

        frame.setLayout(null);
        frame.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
        frame.setBackground(Color.black);

        grille = new VueGrille(modele);
        grille.setBounds(200,15,120*CModele.HAUTEUR,100*CModele.LARGEUR);
        frame.add(grille);
        //Image helico = new ImageIcon(this.getClass().getResource("./helico.png")).getImage();

        commandes = new VueCommandes(modele);
        commandes.setBounds(200+120*CModele.HAUTEUR,50*CModele.LARGEUR,260,130);
        frame.add(commandes);

        action = new VueAction(modele);
        action.setBounds(200+20*CModele.HAUTEUR,105*CModele.LARGEUR,1000,60);
        frame.add(action);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}