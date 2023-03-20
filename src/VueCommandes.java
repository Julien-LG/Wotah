import java.util.*;
import java.util.List;
import javax.swing.*;
import java.awt.GridLayout;
import javax.swing.JPanel;

import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class VueCommandes extends JPanel {

    private Modele modele;

    static List<JLabel> labels = new ArrayList<JLabel>();

    // static BufferedImage inventory;
    // static BufferedImage None;
    // static BufferedImage Middle_Tower;
    // static BufferedImage Top_Tower;

    // {
    //     try {
    //         inventory = ImageIO.read(new File("res\\images\\ptjgqon6k5j71.jpg"));
    //         None = ImageIO.read(new File("res\\images\\pion\\none2.png"));
    //         Middle_Tower = ImageIO.read(new File("res\\images\\Middle_tower.PNG"));
    //         Top_Tower = ImageIO.read(new File("res\\images\\Top_tower.PNG"));
    //     } catch (IOException ie) {
    //         ie.printStackTrace();
    //     }
    // }

    static JTextField TPCoordX = new JTextField();
    static JTextField TPCoordY = new JTextField();

    public VueCommandes(Modele modele) {
        this.modele = modele;

        // Controleur ctrl = new Controleur(modele);

        JPanel pannel = new JPanel();

        JLabel background = new JLabel(); // new ImageIcon(inventory.getScaledInstance(840, 500, Image.SCALE_DEFAULT))

        background.setPreferredSize(new Dimension(840, 600));
        background.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(25, 25, 25, 25);

        pannel.add(background);

        pannel.setOpaque(false);

        //////////////////////////////////////////////////////////////// TP

        JPanel TP_Panel = new JPanel();
        JPanel top_TP_Panel = new JPanel();
        JPanel bottom_TP_Panel = new JPanel();
        JButton TP = new JButton("TP cannon");
        // TP.addActionListener(ctrl);

        top_TP_Panel.setLayout(new GridLayout(1, 2));
        top_TP_Panel.add(new JLabel("Coord X : ", SwingConstants.CENTER));
        top_TP_Panel.setBackground(new Color(255, 255, 255, 128));
        top_TP_Panel.add(TPCoordX);

        bottom_TP_Panel.setLayout(new GridLayout(1, 2));
        bottom_TP_Panel.add(new JLabel("Coord Y : ", SwingConstants.CENTER));
        bottom_TP_Panel.setBackground(new Color(255, 255, 255, 128));
        bottom_TP_Panel.add(TPCoordY);

        TP_Panel.setLayout(new GridLayout(3, 1));
        TP_Panel.add(top_TP_Panel);
        TP_Panel.add(bottom_TP_Panel);
        TP_Panel.add(TP);
        
        c.gridx = 1;
        TP_Panel.setBackground(new Color(255, 255, 255, 128));
        background.add(TP_Panel, c);

        add(pannel);
        setOpaque(false);
    }
}