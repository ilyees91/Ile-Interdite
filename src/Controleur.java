import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class Controleur extends MouseAdapter implements ActionListener {
    int nb_tour = 0;
    CModele modele;
    boolean clikk_asseche;
    public Controleur(CModele modele) { this.modele = modele; }

    public void mousePressed(MouseEvent e) {
        int x = (e.getX()/120) + 1;
        int y = (e.getY()/100) + 1;
        //System.out.println(clikk_asseche);
        //if(clikk_asseche){
            if(modele.assecheV2(x,y)){
                //System.out.println("jure sa marche");
                //nb_tour += 1;
            }else {
                System.out.println("tromper de case");
            //}
        }

    }
    public void action(ActionEvent e){
        if (e.getActionCommand() == "fin de tour"){
            nb_tour = 0;
            modele.avance();
            clikk_asseche = false;
        }
        if (e.getActionCommand() == "bas"){
            nb_tour += 1;
            clikk_asseche = false;
            modele.mouvement("y",1);
        }
        if (e.getActionCommand() == "haut"){
            nb_tour += 1;
            clikk_asseche = false;
            modele.mouvement("y",-1);
        }
        if (e.getActionCommand() == "gauche"){
            nb_tour += 1;
            clikk_asseche = false;
            modele.mouvement("x",-1);
        }
        if (e.getActionCommand() == "droite"){
            clikk_asseche = false;
            modele.mouvement("x",1);
        }
        if (e.getActionCommand() == "Assecher une zone"){
            clikk_asseche = true;
            modele.assecheV1(true);

        }
    }
    public void actionPerformed(ActionEvent e) {
        if (nb_tour == 3){
            if (e.getActionCommand() == "fin de tour"){
                nb_tour = 0;
                modele.avance();
            }
        }else{
            //System.out.println(clikk_asseche);
            action(e);
        }
    }
}