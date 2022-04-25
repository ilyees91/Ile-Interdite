import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class Controleur extends MouseAdapter implements ActionListener { // tous ce qui est en rapport avec controler le jeux
    CModele modele;
    public Controleur(CModele modele) { this.modele = modele;}
    public void mousePressed(MouseEvent e) {
        int x = (e.getX()/120) + 1;
        int y = (e.getY()/100) + 1;
        if(modele.getClicker_asseche()){
            if(modele.assecheV2(x,y)){
                modele.setNombreDeTour(true);
                modele.setPhrases("C'est au tour de "+modele.getJoueurEnJeu().getName()+" : Vous êtes de Couleur "+modele.getJoueurEnJeu().getCouleurJ() +" et il vous reste "+(3-modele.getNombreDeTour())+ " action");
            }else {
                modele.setPhrases("Action anuller, il vous reste : "+(3-modele.getNombreDeTour())+" actions");
            }
            modele.setClicker_asseche(false);
        }
        if(modele.isCliker_sacSable() && modele.getJoueurEnJeu().isSac_sable()){
            modele.getCellule(x,y).assecheV3();
            modele.getJoueurEnJeu().setSac_sable(false);
            modele.setPhrases("C'est au tour de "+modele.getJoueurEnJeu().getName()+" : Vous êtes de Couleur "+modele.getJoueurEnJeu().getCouleurJ() +" et il vous reste "+(3-modele.getNombreDeTour())+ " action");
            modele.notifyObservers();
        }
        if(modele.isCliker_helico() && modele.getJoueurEnJeu().isSpé_helico()){
            modele.envole(x,y);
            modele.getJoueurEnJeu().setSpé_helico(false);
            modele.setCliker_helico(false);
            modele.setPhrases("C'est au tour de "+modele.getJoueurEnJeu().getName()+" : Vous êtes de Couleur "+modele.getJoueurEnJeu().getCouleurJ() +" et il vous reste "+(3-modele.getNombreDeTour())+ " action");
            modele.notifyObservers();
        }
    }
    public void action(ActionEvent e) {
        if (e.getActionCommand() == "fin de tour") {
            modele.randomCler();
            modele.setClicker_asseche(false);
            modele.setCliker_helico(false);
            modele.setCliker_sacSable(false);
            modele.setNombreDeTour(false);
            modele.fin_de_tour();

        }
        if (e.getActionCommand() == "bas") {
            modele.setClicker_asseche(false);
            modele.setCliker_helico(false);
            modele.setCliker_sacSable(false);
            if (modele.mouvement("y", 1)) {
                modele.setNombreDeTour(true);
                modele.setPhrases("C'est au tour de " + modele.getJoueurEnJeu().getName() + " : Vous êtes de Couleur " + modele.getJoueurEnJeu().getCouleurJ() + " et il vous reste " + (3 - modele.getNombreDeTour()) + " action");

            } else {
                modele.setPhrases("Impossible de passer par la, il vous reste : " + (3 - modele.getNombreDeTour()) + " actions");
            }
        }
        if (e.getActionCommand() == "haut") {
            modele.setClicker_asseche(false);
            modele.setCliker_helico(false);
            modele.setCliker_sacSable(false);
            if (modele.mouvement("y", -1)) {
                modele.setNombreDeTour(true);
                modele.setPhrases("C'est au tour de " + modele.getJoueurEnJeu().getName() + " : Vous êtes de Couleur " + modele.getJoueurEnJeu().getCouleurJ() + " et il vous reste " + (3 - modele.getNombreDeTour()) + " action");

            } else {
                modele.setPhrases("Impossible de passer par la, il vous reste : " + (3 - modele.getNombreDeTour()) + " actions");
            }

        }
        if (e.getActionCommand() == "gauche") {
            modele.setClicker_asseche(false);
            modele.setCliker_helico(false);
            modele.setCliker_sacSable(false);
            if (modele.mouvement("x", -1)) {
                modele.setNombreDeTour(true);
                modele.setPhrases("C'est au tour de " + modele.getJoueurEnJeu().getName() + " : Vous êtes de Couleur " + modele.getJoueurEnJeu().getCouleurJ() + " et il vous reste " + (3 - modele.getNombreDeTour()) + " action");


            } else {
                modele.setPhrases("Impossible de passer par la, il vous reste : " + (3 - modele.getNombreDeTour()) + " actions");
            }
        }
        if (e.getActionCommand() == "droite") {
            modele.setClicker_asseche(false);
            modele.setCliker_helico(false);
            modele.setCliker_sacSable(false);
            if (modele.mouvement("x", 1)) {
                modele.setNombreDeTour(true);
                modele.setPhrases("C'est au tour de " + modele.getJoueurEnJeu().getName() + " : Vous êtes de Couleur " + modele.getJoueurEnJeu().getCouleurJ() + " et il vous reste " + (3 - modele.getNombreDeTour()) + " action");

            } else {
                modele.setPhrases("Impossible de passer par la, il vous reste : " + (3 - modele.getNombreDeTour()) + " actions");
            }
        }
        if (e.getActionCommand() == "Assecher une zone") {
            modele.setPhrases("Pour assecher, veuillez cliker sur une zone inonder qui clignote ");
            modele.setClicker_asseche(true);
            modele.assecheV1(true);
            modele.setCliker_helico(false);
            modele.setCliker_sacSable(false);
        }
        if (e.getActionCommand() == "chercher une cle") {
            modele.setClicker_asseche(false);
            modele.setCliker_helico(false);
            modele.setCliker_sacSable(false);
                modele.setNombreDeTour(true);
                if (modele.randomClerV2()) {
                    modele.setPhrases(modele.getJoueurEnJeu().getName() + " ta trouver un truck . Il te reste " + (3 - modele.getNombreDeTour()) + " action");

                } else {
                    modele.setPhrases("Aie Aie Aie, ta zone a été inonder, fait belek. Il te reste " + (3 - modele.getNombreDeTour()) + " actions");
                }
        }
        if (e.getActionCommand() == "Recuperer Artefacts") {
            modele.setClicker_asseche(false);
            modele.setCliker_helico(false);
            modele.setCliker_sacSable(false);
            if(modele.recupereArtefacts()){
                modele.setPhrases("gg ta reussit a avoir l'arthefacts");
            }
        }if(e.getActionCommand() == "s'envoler"){
            if(modele.getJoueurEnJeu().isSpé_helico()){
                modele.setPhrases("cliker sur une case ou s'envoler");
                modele.setCliker_helico(true);
            }else{
                modele.setPhrases("ta pas d'helico frero");
            }

        }
        if(e.getActionCommand() == "sac de sable"){
            modele.setCliker_sacSable(false);
            modele.setClicker_asseche(false);
            if(modele.getJoueurEnJeu().isSac_sable()){
                modele.setPhrases("cliker sur une case innonder pour assecher");
                modele.setCliker_sacSable(true);
            }else{
                modele.setPhrases("ta pas de sac frero frero");
            }

        }
    }
    public void actionPerformed(ActionEvent e) {
        if(!modele.test_lose() || modele.victoire()){
            if (modele.getNombreDeTour() == 3){
                if (e.getActionCommand() == "fin de tour"){
                    modele.randomCler();
                    modele.setClicker_asseche(false);
                    modele.setNombreDeTour(false);
                    modele.setCliker_helico(false);
                    modele.fin_de_tour();
                }
            }else{
                action(e);}
        }
        }
}