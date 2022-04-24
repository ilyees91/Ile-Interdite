import java.awt.*;

class Joueur{
    private String name;
    private Cellule position;
    private CModele modele;
    private int numero;
    private Color couleur;

    public Joueur(CModele modele,String name, Cellule c, int numero, Color couleur){
        this.modele = modele;
        this.name = name;
        this.position =  c;
        this.numero = numero;
        this.couleur = couleur;
    }

    public Cellule getPosition(){
        return position;
    }
    public void setPosition(Cellule c){
        this.position = c;
    }
    public int getNumero() {
        return numero;
    }
    public Color getCouleur() {
        return couleur;
    }
}