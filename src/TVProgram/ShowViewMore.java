/*
 * Name: {Kayla Vincent, Kadhia Pryce, Khaela Purvill}
 * Program: {Faculty of Engineering and Computing}
 * Course: {Object Oriented Programming}
 * Created: {November 1, 2020}
 * Updated: {November 28,2020}
 */

package TVProgram;

import javax.swing.*;
import java.awt.*;

public class ShowViewMore {
    JFrame fr = new JFrame("Now Watching...");

    /**
     * ShowViewMore: {Opens new window displaying picture in frame}
     *
     * @author {Kayla Vincent, Kadhia Pryce, Khaela Purvill}
     * @version 1.0
     */
    public ShowViewMore() {
        ImageIcon icon = new ImageIcon("watching.jpg");
        Image i = Toolkit.getDefaultToolkit().getImage("cwatching.png");
        JLabel lab = new JLabel(icon);
        JLabel watch = new JLabel("You are now watching...........");
        JPanel pan = new JPanel(new FlowLayout());
        pan.add(lab, BorderLayout.CENTER);
        pan.add(watch, BorderLayout.NORTH);
        fr.add(pan);
        fr.setIconImage(i);
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fr.setSize(500, 500);
        fr.setVisible(true);
    }
}
