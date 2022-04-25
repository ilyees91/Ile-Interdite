import javax.swing.*;
import java.awt.*;

class VueJoueur extends JPanel implements Observer {
    private CModele modele;



    public VueJoueur(CModele modele) {
        this.modele = modele;
        modele.addObserver(this);
        Dimension dim = new Dimension(180, 600);
        this.setPreferredSize(dim);
    }

    public void update() {repaint();}


    public void paintComponent(Graphics g) {
        super.repaint();
        paint(g);
    }
    public void paint(Graphics g) {
        dessineJ1(g);
        dessineJ2(g);
        if(modele.getNombreDeJoueur() ==3){
            dessineJ3(g);
        }
        if(modele.getNombreDeJoueur() == 4){
            dessineJ3(g);
            dessineJ4(g);
        }


    }




    public void dessineJ1(Graphics g){
        g.drawString(modele.getEnsJoueur()[0].getName(), 20, 20);
        g.drawString("nombre de cle : "+modele.getEnsJoueur()[0].isCle(), 20, 40);
        g.drawString("Artefact air : "+modele.getEnsJoueur()[0].getInventaire()[0].getNbObject(),20,60);
        g.drawString("Artefact eau : "+modele.getEnsJoueur()[0].getInventaire()[1].getNbObject(),20,80);
        g.drawString("Artefact feu : "+modele.getEnsJoueur()[0].getInventaire()[2].getNbObject(),20,100);
        g.drawString("Artefact terre : "+modele.getEnsJoueur()[0].getInventaire()[3].getNbObject(),20,120);
        g.drawString("Sace de Sable : "+modele.getEnsJoueur()[0].isSac_sable(),20,140);
        g.drawString("Hélicopter : "+modele.getEnsJoueur()[0].isSpé_helico(),20,160);
        g.setColor(modele.getEnsJoueur()[0].getCouleur());
        g.drawRect(18, 8, 140,161 );
    }
    public void dessineJ2(Graphics g){
        g.setColor(Color.black);
        g.drawString(modele.getEnsJoueur()[1].getName(), 20, 200);
        g.drawString("nombre de cle : "+modele.getEnsJoueur()[1].isCle(), 20, 220);
        g.drawString("Artefact air : "+modele.getEnsJoueur()[1].getInventaire()[0].getNbObject(),20,240);
        g.drawString("Artefact eau : "+modele.getEnsJoueur()[1].getInventaire()[1].getNbObject(),20,260);
        g.drawString("Artefact feu : "+modele.getEnsJoueur()[1].getInventaire()[2].getNbObject(),20,280);
        g.drawString("Artefact terre : "+modele.getEnsJoueur()[1].getInventaire()[3].getNbObject(),20,300);
        g.drawString("Sace de Sable : "+modele.getEnsJoueur()[1].isSac_sable(),20,320);
        g.drawString("Hélicopter : "+modele.getEnsJoueur()[1].isSpé_helico(),20,340);
        g.setColor(modele.getEnsJoueur()[1].getCouleur());
        g.drawRect(18, 188, 140,161 );
    }
    public void dessineJ3(Graphics g){
        g.setColor(Color.black);
        g.drawString(modele.getEnsJoueur()[2].getName(), 20, 380);
        g.drawString("nombre de cle : "+modele.getEnsJoueur()[2].isCle(), 20, 400);
        g.drawString("Artefact air : "+modele.getEnsJoueur()[2].getInventaire()[0].getNbObject(),20,420);
        g.drawString("Artefact eau : "+modele.getEnsJoueur()[2].getInventaire()[1].getNbObject(),20,440);
        g.drawString("Artefact feu : "+modele.getEnsJoueur()[2].getInventaire()[2].getNbObject(),20,460);
        g.drawString("Artefact terre : "+modele.getEnsJoueur()[2].getInventaire()[3].getNbObject(),20,480);
        g.drawString("Sace de Sable : "+modele.getEnsJoueur()[2].isSac_sable(),20,500);
        g.drawString("Hélicopter : "+modele.getEnsJoueur()[2].isSpé_helico(),20,520);
        g.setColor(modele.getEnsJoueur()[2].getCouleur());
        g.drawRect(18, 368, 140,161 );
    }

    public void dessineJ4(Graphics g){
        g.setColor(Color.black);
        g.drawString(modele.getEnsJoueur()[3].getName(), 20, 560);
        g.drawString("nombre de cle : "+modele.getEnsJoueur()[3].isCle(), 20, 580);
        g.drawString("Artefact air : "+modele.getEnsJoueur()[3].getInventaire()[0].getNbObject(),20,600);
        g.drawString("Artefact eau : "+modele.getEnsJoueur()[3].getInventaire()[1].getNbObject(),20,620);
        g.drawString("Artefact feu : "+modele.getEnsJoueur()[3].getInventaire()[2].getNbObject(),20,640);
        g.drawString("Artefact terre : "+modele.getEnsJoueur()[3].getInventaire()[3].getNbObject(),20,660);
        g.drawString("Sace de Sable : "+modele.getEnsJoueur()[3].isSac_sable(),20,680);
        g.drawString("Hélicopter : "+modele.getEnsJoueur()[3].isSpé_helico(),20,700);
        g.setColor(modele.getEnsJoueur()[3].getCouleur());
        g.drawRect(18, 548, 140,161 );
    }

}
