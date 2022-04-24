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

            if(modele.assecheV2(x,y)){
                modele.setNombreDeTour(true);

            }else {
                modele.setPhrases("Action anuller");
            }
    }


    public void action(ActionEvent e){
        if (e.getActionCommand() == "fin de tour"){
            modele.setNombreDeTour(false);
            modele.fin_de_tour();

        }
        if (e.getActionCommand() == "bas"){
            modele.setNombreDeTour(true);
            modele.mouvement("y",1);
        }
        if (e.getActionCommand() == "haut"){
            modele.setNombreDeTour(true);
            modele.mouvement("y",-1);
        }
        if (e.getActionCommand() == "gauche"){
            modele.setNombreDeTour(true);
            modele.mouvement("x",-1);
        }
        if (e.getActionCommand() == "droite"){
            modele.setNombreDeTour(true);
            modele.mouvement("x",1);
        }
        if (e.getActionCommand() == "Assecher une zone"){
            modele.setPhrases("Pour assecher, veuillez cliker sur une zone inonder qui clignote ");
            modele.assecheV1(true);

        }
    }
    public void actionPerformed(ActionEvent e) {
        if (modele.getNombreDeTour() == 3){
            if (e.getActionCommand() == "fin de tour"){
                modele.setNombreDeTour(false);
                modele.fin_de_tour();
            }
        }else{
            action(e);
        }
    }
}