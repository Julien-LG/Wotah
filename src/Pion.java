import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Pion extends JComponent {
    /*private int x, y;
    private int width, height;
    private BufferedImage texture;

    public Pion(int x, int y, int width, int height,BufferedImage texture){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.texture = texture;
    }

    public void paintComponent(){

    }*/
    private Personnage p;
    public Pion(Personnage p) {
        this.p = p;
    }

    public void paintComponenet(Graphics g){
        super.paintComponent(g);
        g.drawImage(p.getTexture().getScaledInstance(50, 50, Image.SCALE_DEFAULT), p.getX(), p.getY(), null);
    }
}
