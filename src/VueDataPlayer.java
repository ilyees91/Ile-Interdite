import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class VueDataPlayer extends JPanel implements ActionListener {

    private CModele modele;
    private JTextField text1, text2, text3;// text1:saisieNombreJoueur;2:erreur;3:saosirNom
    private JButton btn, btnNom;
    private JFrame f = new JFrame();

    private JPanel panel = new JPanel();
    private JPanel panel1 = new JPanel();

    private int CurrentPlayer = 0;
    private Color couleur;

    public VueDataPlayer(CModele modele) {
        this.modele = modele;

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        // Spécifier la position et la taille du JPanel
        panel.setBounds(20, 20, 280, 280);
        panel1.setBounds(20, 20, 280, 280);
        // Spécifier la couleur d'arrière-plan du JPanel
        panel.setBackground(Color.lightGray);
        panel1.setBackground(Color.lightGray);

        // Pour Panel
        JLabel jlabel = new JLabel("Saisir un nombre de joueur entre 2 et 4");
        jlabel.setFont(new Font("Verdana", 1, 10));// Gestion Police du texte
        text1 = new JTextField();
        text1.setBounds(20, 50, 100, 20);
        text2 = new JTextField();
        text2.setBounds(20, 70, 100, 20);
        text2.setEditable(false);
        btn = new JButton("Valider");
        btn.setBounds(100, 140, 100, 40);
        btn.addActionListener(this);// Ecouteur d'événemlent à chaque fois qu'on clique sur le boutton
        panel.add(jlabel);
        panel.add(text1);
        panel.add(text2);
        panel.add(btn);
        panel.setVisible(true);

        // Pour Panel1
        JLabel jlabel1 = new JLabel("Saisir le nom du Joueur");
        jlabel1.setFont(new Font("Verdana", 1, 10));
        text3 = new JTextField();
        text3.setBounds(20, 50, 100, 20);
        btnNom = new JButton("Valider le nom");
        btnNom.setBounds(100, 140, 100, 40);
        btnNom.addActionListener(this);
        panel1.add(jlabel1);
        panel1.add(text3);
        panel1.add(btnNom);
        panel1.setVisible(false);

        f.add(panel);
        f.add(panel1);
        f.setSize(340, 340);
        f.setLayout(null);
        f.setVisible(true);

    }

    public static boolean isStringInteger(String stringToCheck, int radix) {
        Scanner sc = new Scanner(stringToCheck.trim());
        if (!sc.hasNextInt(radix))
            return false;
        sc.nextInt(radix);
        return !sc.hasNext();
    }

    public void actionPerformed(ActionEvent e) {
        String name = text1.getText();
        boolean isOk = false;

        int nombre = 0;
        if (isStringInteger(name, 10)) {
            nombre = Integer.parseInt(name);
            if (nombre < 2 || nombre > 4) {
                text2.setText("Erreure sur le nombre de joeur");
            } else {
                isOk = true;
            }
        } else {
            text2.setText("ce que t'as saisi n'est pas un nombre");
        }

        if (e.getSource() == btn && isOk) {
            panel.setVisible(false);
            panel1.setVisible(true);

            modele.setNombreDeJoueur(nombre);
            modele.setEnsJoueur(nombre);
        }

        if (e.getSource() == btnNom) {
            Joueur[] MyJoueur = modele.getEnsJoueur();
            Cellule[][] MyCellules = modele.getCellules();
            if(CurrentPlayer == 0){
                couleur = Color.red;
            }
            if (CurrentPlayer == 1) {
                couleur = Color.PINK;
            }
            if (CurrentPlayer == 2) {
                couleur = Color.BLACK;
            }
            if (CurrentPlayer == 3) {
                couleur = new Color(0, 255, 0);
            }
            MyJoueur[CurrentPlayer] = new Joueur(this.modele, text3.getText(), MyCellules[(int) (Math.random() * (7 - 1)) + 1][(int) (Math.random() * (7 - 1)) + 1], CurrentPlayer, couleur);
            CurrentPlayer++;
            text3.setText("");
            if (CurrentPlayer == modele.getNombreDeJoueur()) {
                modele.setJoueurEnJeu(MyJoueur[0]);
                modele.setPhrases("C'est au tour de " + modele.getJoueurEnJeu().getName() + " : Vous êtes de Couleur " + modele.getJoueurEnJeu().getCouleurJ() + " et il vous reste " + (3 - modele.getNombreDeTour()) + " action");

                CVue.frame1.setVisible(false);
               CVue.frame.setVisible(true);
            }
        }

    }

}
