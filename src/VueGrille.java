import javax.swing.*;
import java.awt.*;
import java.util.Timer;

class VueGrille extends JPanel implements Observer {
    /** On maintient une référence vers le modèle. */
    private CModele modele;
    boolean cligne = true;
    long instant1;
    long instant2;
    boolean debut = false;
    /** Définition d'une taille (en pixels) pour l'affichage des cellules. */
    private final static int TAILLE_WIDTH = 120;
    private final static int TAILLE_HEIGHT = 100;
    /** Constructeur. */
    public VueGrille(CModele modele) {
        this.modele = modele;
        /** On enregistre la vue [this] en tant qu'observateur de [modele]. */
        modele.addObserver(this);
        /**
         * Définition et application d'une taille fixe pour cette zone de
         * l'interface, calculée en fonction du nombre de cellules et de la
         * taille d'affichage.
         */
        Dimension dim = new Dimension(TAILLE_WIDTH*CModele.LARGEUR,
                TAILLE_HEIGHT*CModele.HAUTEUR);
        this.setPreferredSize(dim);
        Controleur crtl = new Controleur(modele);
        this.addMouseListener(crtl);
    }

    public void update() { repaint(); }


    public void paintComponent(Graphics g) {
        super.repaint();
        for(int i=1; i<=CModele.LARGEUR; i++) {
            for(int j=1; j<=CModele.HAUTEUR; j++) {
                paint(g, modele.getCellule(i, j), (i-1)*TAILLE_WIDTH, (j-1)*TAILLE_HEIGHT);
            }
        }
    }

    public void paint(Graphics g, Cellule c, int x, int y) {
        instant1 = System.currentTimeMillis();
         if (debut){
             instant2 = instant1 + 1000;
         }
        if (c.estVivante()==0) {
            g.setColor(Color.WHITE);
        }
        else if(c.estVivante()==1) {
            g.setColor(Color.CYAN);
        }
        else if (c.estVivante()==2){
            g.setColor(Color.BLUE);
        }
        g.fillRect(x, y, TAILLE_WIDTH, TAILLE_HEIGHT);
        if(c.isChoisit()) {
            if (instant1>instant2){
                cligne = !cligne;
                debut = true;
            }else {
                debut = false;
            }
            if (cligne){
                g.setColor(Color.CYAN);
            }else {
                g.setColor(new Color(255,255,0));


            }
        }
        g.fillRect(x, y, TAILLE_WIDTH, TAILLE_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, x+TAILLE_WIDTH, y);
        g.drawRect(x, y+TAILLE_HEIGHT, x+TAILLE_WIDTH, y+TAILLE_HEIGHT);
        g.drawRect(x, y, x, y+TAILLE_HEIGHT);
        g.drawRect(x+TAILLE_WIDTH, y, x+TAILLE_WIDTH, y+TAILLE_HEIGHT);

        int res = 0;
        for (Joueur i : modele.getEnsJoeur()){
            if (i.getPosition() == c){
                if(res ==0){
                    g.setColor(i.getCouleur());
                    g.fillRect(x, y, (50/2)-1, (50/2)-1);
                    res+=1;
                }
                else if(res ==1){
                    g.setColor(i.getCouleur());
                    g.fillRect(x+50/2, y+50/2, (50/2)-3, (50/2)-3);
                    res+=1;
                }
                else if(res == 2){
                    g.setColor(i.getCouleur());
                    g.fillRect(x, y+50/2, (50/2)-3, (50/2)-3);
                    res+=1;
                }
                else if(res == 3){
                    g.setColor(i.getCouleur());
                    g.fillRect(x+50/2, y, (50/2)-3, (50/2)-3);
                }
            }
        }

    }

}