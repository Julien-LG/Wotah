package Controle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;

public class PauseInGame {
    public void menu() {
        JFrame frame = new JFrame("Pause");
        frame.setSize(200, 100);
        JLabel label = new JLabel("Pause");
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(false);
        while (true) {
            int result = JOptionPane.showOptionDialog(null, "ceci est une pause avec p", "Pause", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"p", "q"}, "p");
            if (result == 0) {
                frame.setVisible(true);
                while (frame.isVisible()) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else if (result == 1) {
                break;
            }
        }
        frame.dispose();
    }
}
