package Vue;

import Modele.*;
import Vue.VueGrille;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class CVue {

    private JFrame frame = new JFrame();
    private JPanel game = new JPanel();
    private VueGrille vueGrille;

    public final static int SCREEN_SIZE = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    public final static int size = SCREEN_SIZE/ Modele.getHeight();

    public CVue(Modele modele) {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setTitle("Wotah");
        
        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(frame);
        
        JLabel background = new JLabel(new ImageIcon(Textures.texture_sea.getTextureNoScale().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_DEFAULT)));
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

        /*new Thread() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } repaintVueGrille();
                }
            }
        }.start();*/
    }

    public void newProgressBar(int x, int y){
        final int[] progress = {0};

        JProgressBar progressBar = new JProgressBar();
        progressBar.setValue(progress[0]);
        frame.add(progressBar);

        progressBar.setStringPainted(true);
        progressBar.setBounds(x, y, 100, 10);

        // test de centralisation threads bar et action du personnage
        //Modele.ActionTimer thread = new Modele.ActionTimer(300, progress, progressBar);
        //thread.run(x+(3*size)-3, y, p);

        Thread thread = new Thread() {
            public void run() {
                while (progress[0] < 100) {
                    try {
                        Thread.sleep(300); // temps de pause entre chaque mise à jour de la barre de chargement
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progress[0]++;
                    progressBar.setValue(progress[0]);
                    progressBar.setBounds(x+(3*size)-3, y, 100, 10);
                }
                frame.remove(progressBar);
                frame.repaint();
            }
        };
        thread.start();
    }

    public void addTempeteTime(){
        vueGrille.progressBar();
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