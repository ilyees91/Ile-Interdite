import java.awt.*;
import java.util.Random;
import java.util.Scanner;

class CModele extends Observable {
    public static final int HAUTEUR=6, LARGEUR=6;
    private Cellule[][] cellules;
    private Joueur[] ensJoueur;
    private Joueur joueurEnJeu;
    private int nombreDeJoueur;
    private String phrases;
    private int nombreDeTour;

    private Color couleur;

    public void setNombreDeTour(boolean a) {
        if(a){
            this.nombreDeTour++;
        }else{
            this.nombreDeTour = 0;
        }

    }

    public int getNombreDeTour() {
        return nombreDeTour;
    }

    public String getPhrases() {
        return phrases;
    }

    public CModele() {
        cellules = new Cellule[LARGEUR+2][HAUTEUR+2];
        for(int i=0; i<LARGEUR+2; i++) {
            for(int j=0; j<HAUTEUR+2; j++) {
                cellules[i][j] = new Cellule(this,i, j);
            }
        }
        init();
    }

    public void init() {
        int nombre_joueur = 0;
        while (((nombre_joueur < 2) || (nombre_joueur > 4))){
            Scanner sc = new Scanner(System.in);
            System.out.println("veuillez choisir un nombre compris entre 2 et 4");
            System.out.println("Quel est le nombre de joeur ?");
            nombre_joueur = sc.nextInt();
            if (nombre_joueur<2  || nombre_joueur > 4){
                System.out.println("Erreure sur le nombre de joeur");
            }
        }
        nombreDeJoueur = nombre_joueur;
        ensJoueur = new Joueur[nombreDeJoueur];
        for (int i = 0; i < nombre_joueur ; i++){
            Scanner sc2 = new Scanner(System.in);
            System.out.println("veuiller saisir votre nom : ");
            String name = sc2.nextLine();
            if (i == 0){
                couleur = new Color(255,0,0);
            }if(i==1){
                couleur = Color.PINK;
            }if(i==2){
                couleur = Color.BLACK;
            }if(i==3){
                couleur = new Color(0,255,0);
            }

            ensJoueur[i] = new Joueur(this,name,cellules[(int) (Math.random()*(5-1)) + 1][(int) (Math.random()*(5-1)) + 1], i, couleur);
        }
        joueurEnJeu = ensJoueur[0];
        phrases = "C'est au tour de "+joueurEnJeu.getName()+" : Vous êtes de Couleur "+joueurEnJeu.getCouleurJ() +" et il vous reste "+(3-getNombreDeTour())+" action";
    }

    public Joueur getJoueurEnJeu() {
        return joueurEnJeu;
    }

    public void fin_de_tour() {
        int res = 0;
        while(res < 3) {
            int i = (int) (Math.random() * ((LARGEUR) + 1));
            int j = (int) (Math.random() * ((HAUTEUR) + 1));
            if(cellules[i][j].etat<2) {
                cellules[i][j].inonde();
                res++;
            }
        }
        if(joueurEnJeu.getNumero()+1 > nombreDeJoueur - 1){
            joueurEnJeu = ensJoueur[0];

        }else{
            joueurEnJeu = ensJoueur[joueurEnJeu.getNumero()+ 1];
        }
        phrases = "C'est au tour de "+joueurEnJeu.getName()+" : Vous êtes de Couleur "+joueurEnJeu.getCouleurJ() +" et il vous reste "+(3-getNombreDeTour())+" action";
        notifyObservers();
    }
    public Cellule getCellule(int x, int y) {
        return cellules[x][y];
    }
    public int[] getPosTableau(Cellule c){
        int[]tab = new int[2];
        for(int i=0; i<LARGEUR+2; i++) {
            for (int j = 0; j < HAUTEUR + 2; j++) {
                if (c ==cellules[i][j]){
                    tab[0] = i;
                    tab[1] = j;
                }
            }
        }
        return tab;
    }
    private boolean valideDonnee(int xTab, int yTab) {
        if(xTab<=0 || xTab>=HAUTEUR+1) return false;
        return !(yTab<=0 || yTab>=LARGEUR+1);
    }
    public void mouvement(String axe, int mv) {
        int[] coord = getPosTableau(joueurEnJeu.getPosition());
        if (axe == "x"){
            if (valideDonnee(coord[0]+ mv,coord[1])){
                joueurEnJeu.setPosition(cellules[coord[0]+ mv][coord[1]]);
            }
        }
        if (axe == "y"){
            if (valideDonnee(coord[0],coord[1]+ mv)){
                joueurEnJeu.setPosition(cellules[coord[0]][coord[1]+ mv]);
            }
        }
        phrases = "C'est au tour de "+joueurEnJeu.getName()+" : Vous êtes de Couleur "+joueurEnJeu.getCouleurJ() +" et il vous reste "+(3-getNombreDeTour())+" action";
        notifyObservers();
    }
    public Joueur[] getEnsJoueur() {
        return ensJoueur;
    }

    public void setPhrases(String phrases) {
        notifyObservers();
        this.phrases = phrases;
    }

    public Cellule[] voisinTab(int x, int y) {
        int nb = 0;
        Cellule[] tabVoisin = new Cellule[5];

        tabVoisin[0] = cellules[x][y];
        tabVoisin[1] = cellules[x-1][y];
        tabVoisin[2] = cellules[x+1][y];
        tabVoisin[3] = cellules[x][y-1];
        tabVoisin[4] = cellules[x][y+1];
        return tabVoisin;
    }

    public void assecheV1(boolean a) {
        int[] coord = getPosTableau(joueurEnJeu.getPosition());
        for (Cellule i : voisinTab(coord[0],coord[1])){
            if (i.estVivante() == 1){
                i.setChoisit(a);
            }
        }
        phrases = "Veuillez selectionner une case inonder clignotante";

    }

    public boolean assecheV2(int x, int y) {
        int[] cord = getPosTableau(joueurEnJeu.getPosition());
        Cellule[] tab = voisinTab(cord[0],cord[1]);
        if(tab[0].isChoisit()&& tab[0]==cellules[x][y]){
            assecheV1(false);
            tab[0].assecheV3();
            setPhrases("C'est au tour de "+joueurEnJeu.getName()+" : Vous êtes de Couleur "+joueurEnJeu.getCouleurJ() +" et il vous reste "+(3-getNombreDeTour())+ "action");
            return true;
        }else if(tab[1].isChoisit() && tab[1]==cellules[x][y]){
            assecheV1(false);
            tab[1].assecheV3();
            setPhrases("C'est au tour de "+joueurEnJeu.getName()+" : Vous êtes de Couleur "+joueurEnJeu.getCouleurJ() +" et il vous reste "+(3-getNombreDeTour())+ "action");
            return true;
        }else if(tab[2].isChoisit() && tab[2]==cellules[x][y]){
            assecheV1(false);
            setPhrases("C'est au tour de "+joueurEnJeu.getName()+" : Vous êtes de Couleur "+joueurEnJeu.getCouleurJ() +" et il vous reste "+(3-getNombreDeTour())+ "action");
            tab[2].assecheV3();
            return true;
        }else if(tab[3].isChoisit() && tab[3]==cellules[x][y]){
            assecheV1(false);
            setPhrases("C'est au tour de "+joueurEnJeu.getName()+" : Vous êtes de Couleur "+joueurEnJeu.getCouleurJ() +" et il vous reste "+(3-getNombreDeTour())+ "action");
            tab[3].assecheV3();
            return true;
        }else if(tab[4].isChoisit() && tab[4]==cellules[x][y]){
            assecheV1(false);
            setPhrases("C'est au tour de "+joueurEnJeu.getName()+" : Vous êtes de Couleur "+joueurEnJeu.getCouleurJ() +" et il vous reste "+(3-getNombreDeTour())+ "action");
            tab[4].assecheV3();
            return true;
        }else {
            phrases = "Action annuler, il vous reste : "+nombreDeTour+"actions";
            notifyObservers();
            assecheV1(false);
            return false;
        }

    }

}
