import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class DragAndDrop2 extends JFrame implements MouseListener, MouseMotionListener {
    private int x, y;
    private ArrayList<Personnage> personnages;

    public DragAndDrop2(ArrayList<Personnage> personnages) {
        /*super("Drag and Drop Example");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);*/

        this.personnages = personnages;

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    /*public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.RED);
        g.fillOval(x, y, 50, 50);
    }*/

    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        /*if (mouseX >= x && mouseX <= x + 50 && mouseY >= y && mouseY <= y + 50) {
            x = mouseX - 25;
            y = mouseY - 25;
        }*/
        for (Personnage p : personnages) {
            if (mouseX >= p.getX() && mouseX <= p.getX() + 50 && mouseY >= p.getY() && mouseY <= p.getY() + 50) {
                x = mouseX - 25;
                y = mouseY - 25;

                modele.set
            }
        }

    }

    public void mouseDragged(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        /*if (mouseX >= x && mouseX <= x + 50 && mouseY >= y && mouseY <= y + 50) {
            x = mouseX - 25;
            y = mouseY - 25;
        }*/

        repaint();
    }

    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}

    /*public static void main(String[] args) {
        new DragAndDropExample();
    }*/
}
