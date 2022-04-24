import javax.swing.*;
import java.awt.*;


class VueAction extends JPanel {
    private CModele modele;

    public VueAction(CModele modele) {
        this.modele = modele;
        JLabel jlabel = new JLabel("This is a label");
        jlabel.setFont(new Font("Verdana",1,20));
        this.add(jlabel);
    }

}
