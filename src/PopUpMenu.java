import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopUpMenu extends JPopupMenu implements ActionListener {
    JMenuItem item1;
    JMenuItem item2;
    JMenuItem item3;
    JMenuItem item4;

    private JFrame frame;

    public PopUpMenu(final JFrame frame) {
        this.frame = frame;

        item1 = new JMenuItem("Récolter");
        item2 = new JMenuItem("Planter");
        item3 = new JMenuItem("Déplacement");
        //item4 = new JMenuItem("Annuler", iconExit);
        //item4 = new JMenuItem("Annuler");

        item1.addActionListener(this);
        item2.addActionListener(this);
        item3.addActionListener(this);

        add(item1);
        add(item2);
        add(item3);
        //add(item4);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem) (e.getSource());

        if (source.getText().equals("Récolter")){
            System.out.println("Récolte");
        } else if (source.getText().equals("Planter")){
            System.out.println("Plante");
        } else if (source.getText().equals("Déplacement")){
            System.out.println("Déplace");
        }

        frame.repaint();

        //DragAndDropTexture2.activeMenu = false;        //repaint();
    }
}