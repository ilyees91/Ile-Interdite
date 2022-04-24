import java.awt.*;

class Joueur{
    private String name;
    private Cellule position;
    private CModele modele;
    private int numero;
    private Color couleur;
    private String couleurJ;

    public String getCouleurJ() {
        return couleurJ;
    }

    public Joueur(CModele modele, String name, Cellule c, int numero, Color couleur){
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