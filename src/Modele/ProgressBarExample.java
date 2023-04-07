package Modele;

import javax.swing.*;

public class ProgressBarExample extends JFrame {

    private JProgressBar progressBar;
    private int progress;

    public ProgressBarExample() {
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);

        JPanel panel = new JPanel();
        panel.add(progressBar);

        add(panel);
        setTitle("Barre de chargement en Swing");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        startProgress();
    }

    public void startProgress() {
        Thread thread = new Thread() {
            public void run() {
                while (progress < 100) {
                    try {
                        Thread.sleep(250); // temps de pause entre chaque mise à jour de la barre de chargement
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progress++;
                    progressBar.setValue(progress);
                }
            }
        };
        thread.start();
    }

    public static void main(String[] args) {
        new ProgressBarExample();
    }
}

