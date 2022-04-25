
class CModele extends Observable { // fait toute les calcul
    public static final int HAUTEUR = 6, LARGEUR = 6;
    private Cellule[][] cellules;
    private Joueur[] ensJoueur;
    private Joueur joueurEnJeu;
    private int nombreDeJoueur;
    private String phrases;
    private int nombreDeTour;



    private boolean clicker_asseche = false;
    private boolean cliker_helico = false;
    private boolean cliker_sacSable = false;

    public void setCliker_helico(boolean cliker_helico) {
        this.cliker_helico = cliker_helico;
    }

    public void setCliker_sacSable(boolean cliker_sacSable) {
        this.cliker_sacSable = cliker_sacSable;
    }

    public boolean isCliker_helico() {
        return cliker_helico;
    }

    public boolean isCliker_sacSable() {
        return cliker_sacSable;
    }

    public boolean getClicker_asseche() {
        return clicker_asseche;
    }

    public void setClicker_asseche(boolean clicker_asseche) {
        this.clicker_asseche = clicker_asseche;
    }

    public void setNombreDeTour(boolean a) {
        if (a) {
            this.nombreDeTour++;
        } else {
            this.nombreDeTour = 0;
        }

    }

    public int getNombreDeTour() {
        return nombreDeTour;
    }

    public String getPhrases() {
        return phrases;
    }


    public boolean recupereArtefacts() {
        if (joueurEnJeu.isCle() && joueurEnJeu.getPosition().getSpecial() == ZoneSpé.air) {
            for (Artefacts i : joueurEnJeu.getInventaire()) {
                if (i == Artefacts.air) {
                    i.setNbObject(1);
                }
            }
            joueurEnJeu.setCle(false);
            return true;
        } else if (joueurEnJeu.isCle() && joueurEnJeu.getPosition().getSpecial() == ZoneSpé.eau) {
            for (Artefacts i : joueurEnJeu.getInventaire()) {
                if (i == Artefacts.eau) {
                    i.setNbObject(1);
                }
            }
            joueurEnJeu.setCle(false);
            return true;
        } else if (joueurEnJeu.isCle() && joueurEnJeu.getPosition().getSpecial() == ZoneSpé.terre) {
            for (Artefacts i : joueurEnJeu.getInventaire()) {
                if (i == Artefacts.terre) {
                    i.setNbObject(1);
                }
            }
            joueurEnJeu.setCle(false);
            return true;
        } else if (joueurEnJeu.isCle() && joueurEnJeu.getPosition().getSpecial() == ZoneSpé.feu) {
            for (Artefacts i : joueurEnJeu.getInventaire()) {
                if (i == Artefacts.feu) {
                    i.setNbObject(1);
                }
            }
            joueurEnJeu.setCle(false);
            return true;
        } else {
            setPhrases("Erreur, action annuler");
            return false;
        }
    }

    public CModele() {
        cellules = new Cellule[LARGEUR + 2][HAUTEUR + 2];
        for (int i = 0; i < LARGEUR + 2; i++) {
            for (int j = 0; j < HAUTEUR + 2; j++) {
                cellules[i][j] = new Cellule(this, i, j);
            }
        }
        while (true) {
            int x = (int) (Math.random() * (5 - 1)) + 1;
            int y = (int) (Math.random() * (5 - 1)) + 1;
            if (cellules[x][y].getSpecial() == null) {
                cellules[x][y].setSpecial(ZoneSpé.helicoptere);
                break;

            }
        }
        while (true) {
            int x = (int) (Math.random() * (7 - 1)) + 1;
            int y = (int) (Math.random() * (7 - 1)) + 1;
            if (cellules[x][y].getSpecial() == null) {
                cellules[x][y].setSpecial(ZoneSpé.air);
                break;

            }
        }
        while (true) {
            int x = (int) (Math.random() * (7 - 1)) + 1;
            int y = (int) (Math.random() * (7 - 1)) + 1;
            if (cellules[x][y].getSpecial() == null) {
                cellules[x][y].setSpecial(ZoneSpé.eau);
                break;

            }
        }
        while (true) {
            int x = (int) (Math.random() * (7 - 1)) + 1;
            int y = (int) (Math.random() * (7 - 1)) + 1;
            if (cellules[x][y].getSpecial() == null) {
                cellules[x][y].setSpecial(ZoneSpé.feu);
                break;
            }
        }
        while (true) {
            int x = (int) (Math.random() * (7 - 1)) + 1;
            int y = (int) (Math.random() * (7 - 1)) + 1;
            if (cellules[x][y].getSpecial() == null) {
                cellules[x][y].setSpecial(ZoneSpé.terre);
                break;
            }
        }
    }

    public int getNombreDeJoueur() {
        return nombreDeJoueur;
    }


    public Joueur getJoueurEnJeu() {
        return joueurEnJeu;
    }

    public void setEnsJoueur(int nbjoeur) {
        this.ensJoueur = new Joueur[nbjoeur];
    }

    public void setJoueurEnJeu(Joueur joueurEnJeu) {
        this.joueurEnJeu = joueurEnJeu;
    }

    public void setNombreDeJoueur(int nombreDeJoueur) {
        this.nombreDeJoueur = nombreDeJoueur;
    }

    public void fin_de_tour() {
        int res = 0;
        while (res < 3) {
            int i = (int) (Math.random() * (7 - 1)) + 1;
            int j = (int) (Math.random() * (7 - 1)) + 1;
            if (cellules[i][j].etat <= 2) {
                cellules[i][j].inonde();
                res++;
            }
            else{
                res--;
            }
        }
        if (joueurEnJeu.getNumero() + 1 > nombreDeJoueur - 1) {
            joueurEnJeu = ensJoueur[0];

        } else {
            joueurEnJeu = ensJoueur[joueurEnJeu.getNumero() + 1];
        }
        phrases = "C'est au tour de " + joueurEnJeu.getName() + " : Vous êtes de Couleur " + joueurEnJeu.getCouleurJ() + " et il vous reste " + (3 - getNombreDeTour()) + " action";
        notifyObservers();
    }

    public Cellule getCellule(int x, int y) {
        return cellules[x][y];
    }

    public Cellule[][] getCellules(){
        return cellules;
    }
    public int[] getPosTableau(Cellule c) {
        int[] tab = new int[2];
        for (int i = 0; i < LARGEUR + 2; i++) {
            for (int j = 0; j < HAUTEUR + 2; j++) {
                if (c == cellules[i][j]) {
                    tab[0] = i;
                    tab[1] = j;
                }
            }
        }
        return tab;
    }

    private boolean valideDonnee(int xTab, int yTab) {
        if (xTab <= 0 || xTab >= HAUTEUR + 1) return false;
        return !(yTab <= 0 || yTab >= LARGEUR + 1);
    }

    public boolean mouvement(String axe, int mv) {
        int[] coord = getPosTableau(joueurEnJeu.getPosition());
        if (axe == "x") {
            if (valideDonnee(coord[0] + mv, coord[1]) && cellules[coord[0] + mv][coord[1]].estVivante() < 2) {
                joueurEnJeu.setPosition(cellules[coord[0] + mv][coord[1]]);
                return true;
            }
        }
        if (axe == "y") {
            if (valideDonnee(coord[0], coord[1] + mv) && cellules[coord[0]][coord[1] + mv].estVivante() < 2) {
                joueurEnJeu.setPosition(cellules[coord[0]][coord[1] + mv]);
                return true;
            }
        }
        return false;
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
        tabVoisin[1] = cellules[x - 1][y];
        tabVoisin[2] = cellules[x + 1][y];
        tabVoisin[3] = cellules[x][y - 1];
        tabVoisin[4] = cellules[x][y + 1];
        return tabVoisin;
    }

    public void assecheV1(boolean a) {
        int[] coord = getPosTableau(joueurEnJeu.getPosition());
        for (Cellule i : voisinTab(coord[0], coord[1])) {
            if (i.estVivante() == 1) {
                i.setChoisit(a);
            }
        }
        phrases = "Veuillez selectionner une case inonder clignotante";

    }

    public boolean assecheV2(int x, int y) {
        int[] cord = getPosTableau(joueurEnJeu.getPosition());
        Cellule[] tab = voisinTab(cord[0], cord[1]);
        if (tab[0].isChoisit() && tab[0] == cellules[x][y]) {
            assecheV1(false);
            tab[0].assecheV3();
            setPhrases("C'est au tour de " + joueurEnJeu.getName() + " : Vous êtes de Couleur " + joueurEnJeu.getCouleurJ() + " et il vous reste " + (3 - getNombreDeTour()) + "action");
            return true;
        } else if (tab[1].isChoisit() && tab[1] == cellules[x][y]) {
            assecheV1(false);
            tab[1].assecheV3();
            setPhrases("C'est au tour de " + joueurEnJeu.getName() + " : Vous êtes de Couleur " + joueurEnJeu.getCouleurJ() + " et il vous reste " + (3 - getNombreDeTour()) + "action");
            return true;
        } else if (tab[2].isChoisit() && tab[2] == cellules[x][y]) {
            assecheV1(false);
            setPhrases("C'est au tour de " + joueurEnJeu.getName() + " : Vous êtes de Couleur " + joueurEnJeu.getCouleurJ() + " et il vous reste " + (3 - getNombreDeTour()) + "action");
            tab[2].assecheV3();
            return true;
        } else if (tab[3].isChoisit() && tab[3] == cellules[x][y]) {
            assecheV1(false);
            setPhrases("C'est au tour de " + joueurEnJeu.getName() + " : Vous êtes de Couleur " + joueurEnJeu.getCouleurJ() + " et il vous reste " + (3 - getNombreDeTour()) + "action");
            tab[3].assecheV3();
            return true;
        } else if (tab[4].isChoisit() && tab[4] == cellules[x][y]) {
            assecheV1(false);
            setPhrases("C'est au tour de " + joueurEnJeu.getName() + " : Vous êtes de Couleur " + joueurEnJeu.getCouleurJ() + " et il vous reste " + (3 - getNombreDeTour()) + "action");
            tab[4].assecheV3();
            return true;
        } else {
            phrases = "Action annuler, il vous reste : " + nombreDeTour + "actions";
            notifyObservers();
            assecheV1(false);
            return false;
        }

    }

    public boolean randomCler() {
        if (Math.random() < 0.35) {
            joueurEnJeu.setCle(true);
            notifyObservers();
            return true;
        }
        return false;
    }

    public boolean randomClerV2() {
        if(Math.random() < 0.55) {
            double a = Math.random();
            if (a < 0.55) {
                joueurEnJeu.setCle(true);
                notifyObservers();
            }
            else if (a< 0.85) {
                joueurEnJeu.setSac_sable(true);
                notifyObservers();
            }
            else {
                joueurEnJeu.setSpé_helico(true);
                notifyObservers();
            }
            return true;
        }else {
            joueurEnJeu.getPosition().inonde();
            return false;
        }
    }

    public boolean victoire() {
        for(Artefacts i : joueurEnJeu.getInventaire()){
            if(i.getNbObject() == 0 ){
                return false;
            }
        }
        if(joueurEnJeu.getPosition().getSpecial()== ZoneSpé.helicoptere){
            for(Joueur i : ensJoueur){
                if (i.getPosition() != joueurEnJeu.getPosition()){
                    return false;
                }
            }
        }else {
            return false;
        }
        return true;
    }
    public boolean test_lose(){
        for(int i=0; i<LARGEUR+2; i++) {
            for(int j=0; j<HAUTEUR+2; j++) {
                if (cellules[i][j].estVivante() > 1 && cellules[i][j].getSpecial() == ZoneSpé.helicoptere) {
                    return true;
                }
            }
        }
        return false;
    }

    public void envole(int x, int y) {
        int nb1 = 0;
        int nb2 = 0;
        if(joueurEnJeu.isSpé_helico()){
            Joueur[] joueurSurLaZone = new Joueur[nombreDeJoueur];
            for(Joueur i : ensJoueur){
                if(joueurEnJeu.getPosition() == ensJoueur[nb1].getPosition()){
                    joueurSurLaZone[nb2] = ensJoueur[nb1];
                    nb2++;
                }
                nb1++;
            }
            for (Joueur i : joueurSurLaZone){
                if(i == null){
                    break;
                }
                i.setPosition(cellules[x][y]);
            }
        }
    }
}
