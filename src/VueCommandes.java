import javax.swing.*;
import java.awt.*;

class VueCommandes extends JPanel {
    private CModele modele;

    public VueCommandes(CModele modele) {
        this.modele = modele;
        JButton fin = new JButton("fin de tour");
        this.add(fin);
        JButton cle = new JButton("chercher une cle");
        this.add(cle);
        JButton artefacts = new JButton("Recuperer Artefacts");
        this.add(artefacts);
        JButton voler = new JButton("s'envoler");
        this.add(voler);
        JButton sac_de_sable = new JButton("sac de sable");
        this.add(sac_de_sable);
        JButton haut = new JButton("haut");
        JButton bas = new JButton("bas");
        JButton gauche = new JButton("gauche");
        JButton droite = new JButton("droite");
        JButton assechez_une_zone = new JButton("Assecher une zone");
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(haut, BorderLayout.NORTH);
        panel.add(gauche, BorderLayout.WEST);
        panel.add(droite, BorderLayout.EAST);
        panel.add(bas, BorderLayout.CENTER);
        panel.add(assechez_une_zone, BorderLayout.SOUTH);
        this.add(panel);
        Controleur crtl = new Controleur(modele);
        fin.addActionListener(crtl);
        bas.addActionListener(crtl);
        haut.addActionListener(crtl);
        gauche.addActionListener(crtl);
        droite.addActionListener(crtl);
        assechez_une_zone.addActionListener(crtl);
        cle.addActionListener(crtl);
        artefacts.addActionListener(crtl);
        voler.addActionListener(crtl);
        sac_de_sable.addActionListener(crtl);
    }
}
