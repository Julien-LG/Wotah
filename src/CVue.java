import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// import javax.sound.sampled.AudioInputStream;
// import javax.sound.sampled.AudioSystem;
// import javax.sound.sampled.Clip;

import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


class CVue {

    static private JFrame frame = new JFrame();

    {
        frame.setTitle("Wotah");
    }

    static private JPanel game = new JPanel();
    // static private JPanel win = new JPanel();
    // static private JPanel end = new JPanel();
    
    static BufferedImage img;
    // static BufferedImage lose;
    // static BufferedImage victory;

    {
        try {
            img = ImageIO.read(new File("res\\images\\sea_sprite.png"));
            // lose = ImageIO.read(new File("res\\images\\GameOver.png"));
            // victory = ImageIO.read(new File("res\\images\\win.jpg"));
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    public CVue(Modele modele) {
        GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = graphics.getDefaultScreenDevice();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setResizable(false);
        device.setFullScreenWindow(frame);
        
        JLabel background = new JLabel(new ImageIcon(img.getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_DEFAULT)));
        background.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        background.add(new VueGrille(modele));

        game.setLayout(new BorderLayout());
        game.add(background);

        frame.setContentPane(game);
        frame.pack();

        KeyListener kl = new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                if(evt.getKeyCode() == KeyEvent.VK_ESCAPE)
                    System.exit(0);
            }
        }; frame.addKeyListener(kl);
    }

    // static void gameOver() {

    //     frame.remove(game);

    //     end.setLayout(new BorderLayout());
    //     JLabel background = new JLabel(new ImageIcon(lose.getScaledInstance(1280, 720, Image.SCALE_DEFAULT)));

    //     end.add(background);
        
    //     frame.setContentPane(end);

    //     frame.pack();
    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     frame.setVisible(true);

    //     try {
    //         String soundName = "res\\images\\YouDied.wav";
    //         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
    //         Clip clip = AudioSystem.getClip();
    //         clip.open(audioInputStream);
    //         clip.start();
    //     } catch (Exception ie) {
    //     }
    // }

    // static void win() {

    //     frame.remove(game);

    //     win.setLayout(new BorderLayout());
    //     JLabel background = new JLabel(new ImageIcon(victory.getScaledInstance(1280, 720, Image.SCALE_DEFAULT)));

    //     win.add(background);
        
    //     frame.setContentPane(win);

    //     frame.pack();
    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     frame.setVisible(true);

    //     try {
    //         String soundName = "res\\images\\YouWin2.wav";
    //         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
    //         Clip clip = AudioSystem.getClip();
    //         clip.open(audioInputStream);
    //         clip.start();
    //     } catch (Exception ie) {
    //     }
    // }
}