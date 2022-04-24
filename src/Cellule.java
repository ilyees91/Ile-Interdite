class Cellule {
    /** On conserve un pointeur vers la classe principale du modèle. */

    private CModele modele;

    protected int etat;
    boolean choisit;
    private final int x, y;

    boolean a = false;

    public Cellule(CModele modele, int x, int y) {
        this.modele = modele;
        this.etat = 0;
        this.x = x; this.y = y;
        this.choisit = false;
    }
    public boolean isChoisit(){
        return choisit;
    }
    public void setChoisit(boolean choisit) {
        this.choisit = choisit;
    }
    boolean inonde(){
        if(this.etat < 2){
            this.etat++;
            return false;
        }
        return true;
    }
    /** Un test à l'usage des autres classes (sera utilisé par la vue). */
    public int estVivante() {
        return etat;
    }

    public void assecheV3() {
        if(this.etat == 1){
            this.etat--;
        }
    }
}