import java.awt.*;

class Joueur{
    private String name;
    private Cellule position;
    private CModele modele;
    private int numero;
    private Color couleur;
    private String couleurJ;

    private boolean cle;
    private boolean sac_sable;

    private boolean spé_helico;

    public void setSpé_helico(boolean spé_helico) {
        this.spé_helico = spé_helico;
    }

    public boolean isSpé_helico() {
        return spé_helico;
    }

    public void setSac_sable(boolean sac_sable) {
        this.sac_sable = sac_sable;
    }

    public boolean isSac_sable() {
        return sac_sable;
    }

    public void setCle(boolean cle) {
        this.cle = cle;
    }

    public boolean isCle() {
        return cle;
    }

    private Artefacts[] inventaire;




    public String getCouleurJ() {
        return couleurJ;
    }

    public Artefacts[] getInventaire() {
        return inventaire;
    }


    public Joueur(CModele modele, String name, Cellule c, int numero, Color couleur){
        this.inventaire = Artefacts.values();
        this.modele = modele;
        this.name = name;
        this.position =  c;
        this.numero = numero;
        this.couleur = couleur;
        if (numero == 0){
            this.couleurJ = "Rouge";
        }if(numero==1){
            this.couleurJ = "Beige";
        }if(numero==2){
            this.couleurJ = "Noir";
        }if(numero==3){
            this.couleurJ = "Vert";
        }
    }

    public Cellule getPosition(){
        return position;
    }
    public void setPosition(Cellule c){
        this.position = c;
    }

    public String getName() {
        return name;
    }

    public int getNumero() {
        return numero;
    }
    public Color getCouleur() {
        return couleur;
    }
}