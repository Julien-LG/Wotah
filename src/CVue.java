import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class CVue {

    private JFrame frame = new JFrame();
    private JPanel game = new JPanel();
    private VueGrille vueGrille;

    public CVue(Modele modele) {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setTitle("Wotah");
        
        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(frame);
        
        JLabel background = new JLabel(new ImageIcon(Textures.texture_sea.getTexture().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_DEFAULT)));
        background.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        vueGrille = new VueGrille(modele);
        background.add(vueGrille);

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
        repaintVueGrille();
    }
    
    public void repaintVueGrille() {
        vueGrille.repaint();
    }

    public void addKeyListener(KeyListener kl) {
        frame.addKeyListener(kl);
    }

    public void addMouseListener(MouseListener mul) {
        frame.addMouseListener(mul);
    }

    public void addMouseMotionListener(MouseMotionListener mtl) {
        frame.addMouseMotionListener(mtl);
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

    public JPanel getGame() {
        return game;
    }
}