import javax.swing.*;
import java.awt.*;

class VueAction extends JPanel implements Observer {
    private CModele modele;
    Font fonte = new Font("TimesRoman",Font.BOLD,20);



    public VueAction(CModele modele) {
        this.modele = modele;
        modele.addObserver(this);
        Dimension dim = new Dimension(1000, 130);
        this.setPreferredSize(dim);
    }

    public void update() {repaint();}


    public void paintComponent(Graphics g) {
        super.repaint();
        paint(g);
    }
    public void paint(Graphics g) {
        g.setFont(fonte);
        g.setColor(modele.getJoueurEnJeu().getCouleur());
        g.drawString(modele.getPhrases(), 0, 20);
    }
}



