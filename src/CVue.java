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
        
        JLabel background = new JLabel(new ImageIcon(Textures.texture_sea.getTextureNoScale().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_DEFAULT)));
        //afficheRessources(background.getGraphics());
        //afficheRessources(frame.getGraphics());
        //int SCREEN_SIZE = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        //.drawImage(Textures.sprite_sheet.getTexture().getScaledInstance(SCREEN_SIZE/3, SCREEN_SIZE/3, Image.SCALE_DEFAULT), 0, 0, null);
        background.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        vueGrille = new VueGrille(modele);
        background.add(vueGrille);
        //JLabel stats = new JLabel(new ImageIcon(Textures.sprite_sheet.getTexture().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
        //stats.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        game.setLayout(new BorderLayout());
        game.add(background);
        //game.add(stats);

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

    public void afficheRessources(Graphics g) {
        int SCREEN_SIZE = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        g.drawImage(Textures.sprite_sheet.getTexture().getScaledInstance(SCREEN_SIZE/3, SCREEN_SIZE/3, Image.SCALE_DEFAULT), 0, 0, null);

        JLabel label1 = new JLabel("Test");
        label1.setBounds(50, 50, 100, 100);
        //vueGrille.add(label1);
        frame.add(label1);
    }

    public void affichePersonnages(Graphics g) {
        vueGrille.paintPersonnages(g);
    }

    /*public void afficheDecors(Graphics g) {
        //Use a gif image

        //vueGrille.paintDecors(g);
        //g.drawImage(Textures.sprite_shark.getTexture(), 0, 750, null);
        //g.drawImage(SpritesAnimated.sprite_shark.getSprite(), 0, 750, null);
        //frame.add(new JLabel(new ImageIcon(Textures.sprite_shark.getTexture())));
        JLabel shark = new JLabel(SpritesAnimated.sprite_shark.getSprite());

        Thread thread = new Thread() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(300); // temps de pause entre chaque mise à jour de la barre de chargement
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("yo");
                    //shark.setLocation(0,0);
                    shark.setBounds(10, 10, 100, 100);
                }
            }
        };
        thread.start();
    }*/

    public void newProgressBar(int x, int y){
        final int[] progress = {0};

        JProgressBar progressBar = new JProgressBar();
        progressBar.setValue(progress[0]);
        frame.add(progressBar);

        progressBar.setStringPainted(true);
        progressBar.setBounds(x, y, 100, 10);

        int size = VueGrille.SCREEN_SIZE/Modele.getHeight();

        // test de centralisation threads bar et action du personnage
        //ActionTimer thread = new ActionTimer(300, progress, progressBar);
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
                //progressBar.setVisible(false);
                frame.remove(progressBar);
                frame.repaint();
            }
        };
        thread.start();
    }

    public void repaintVueGrille() {
        vueGrille.repaint();
        //afficheDecors(this.vueGrille.getGraphics());
        //afficheDecors(frame.getGraphics());
        //afficheRessources(background.getGraphics());
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