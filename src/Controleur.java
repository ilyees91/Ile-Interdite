import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class Controleur extends MouseAdapter implements ActionListener {
    CModele modele;
    public Controleur(CModele modele) { this.modele = modele;}
    public void mousePressed(MouseEvent e) {
        int x = (e.getX()/120) + 1;
        int y = (e.getY()/100) + 1;
        System.out.println(modele.getClicker_asseche());
        if(modele.getClicker_asseche()){
            if(modele.assecheV2(x,y)){
                modele.setNombreDeTour(true);
                modele.setPhrases("C'est au tour de "+modele.getJoueurEnJeu().getName()+" : Vous êtes de Couleur "+modele.getJoueurEnJeu().getCouleurJ() +" et il vous reste "+(3-modele.getNombreDeTour())+ " action");
            }else {
                modele.setPhrases("Action anuller, il vous reste : "+(3-modele.getNombreDeTour())+" actions");
            }
            modele.setClicker_asseche(false);
        }


    }
    public void action(ActionEvent e){
        if (e.getActionCommand() == "fin de tour"){
            modele.setClicker_asseche(false);
            modele.setNombreDeTour(false);
            modele.fin_de_tour();

        }
        if (e.getActionCommand() == "bas"){
            modele.setClicker_asseche(false);
            if(modele.mouvement("y",1)){
                modele.setNombreDeTour(true);
                modele.setPhrases("C'est au tour de "+modele.getJoueurEnJeu().getName()+" : Vous êtes de Couleur "+modele.getJoueurEnJeu().getCouleurJ() +" et il vous reste "+(3-modele.getNombreDeTour())+ " action");

            }else{
                modele.setPhrases("Impossible de passer par la, il vous reste : "+(3-modele.getNombreDeTour())+" actions");
            }
        }
        if (e.getActionCommand() == "haut"){
            modele.setClicker_asseche(false);
            if(modele.mouvement("y",-1)){
                modele.setNombreDeTour(true);
                modele.setPhrases("C'est au tour de "+modele.getJoueurEnJeu().getName()+" : Vous êtes de Couleur "+modele.getJoueurEnJeu().getCouleurJ() +" et il vous reste "+(3-modele.getNombreDeTour())+ " action");

            }else{
                modele.setPhrases("Impossible de passer par la, il vous reste : "+(3-modele.getNombreDeTour())+" actions");
            }

        }
        if (e.getActionCommand() == "gauche"){
            modele.setClicker_asseche(false);
            if(modele.mouvement("x",-1)){
                modele.setNombreDeTour(true);
                modele.setPhrases("C'est au tour de "+modele.getJoueurEnJeu().getName()+" : Vous êtes de Couleur "+modele.getJoueurEnJeu().getCouleurJ() +" et il vous reste "+(3-modele.getNombreDeTour())+ " action");


            }else{
                modele.setPhrases("Impossible de passer par la, il vous reste : "+(3-modele.getNombreDeTour())+" actions");
            }
        }
        if (e.getActionCommand() == "droite"){
            modele.setClicker_asseche(false);
            if(modele.mouvement("x",1)){
                modele.setNombreDeTour(true);
                modele.setPhrases("C'est au tour de "+modele.getJoueurEnJeu().getName()+" : Vous êtes de Couleur "+modele.getJoueurEnJeu().getCouleurJ() +" et il vous reste "+(3-modele.getNombreDeTour())+ " action");

            }else{
                modele.setPhrases("Impossible de passer par la, il vous reste : "+(3-modele.getNombreDeTour())+" actions");
            }
        }
        if (e.getActionCommand() == "Assecher une zone"){
            modele.setPhrases("Pour assecher, veuillez cliker sur une zone inonder qui clignote ");
            modele.setClicker_asseche(true);
            modele.assecheV1(true);
        }
    }
    public void actionPerformed(ActionEvent e) {
        if (modele.getNombreDeTour() == 3){
            if (e.getActionCommand() == "fin de tour"){
                modele.setClicker_asseche(false);
                modele.setNombreDeTour(false);
                modele.fin_de_tour();
            }
        }else{
            action(e);}
    }
}