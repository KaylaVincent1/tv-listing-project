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
import java.io.File;
import java.io.FileReader;

public class Recording {

    JFrame fe = new JFrame("Recording");
    Image i = Toolkit.getDefaultToolkit().getImage("rec.png");
    JTextPane tp = new JTextPane();

    /**
     * Recording: {Displays data saved in Recording Text file}
     *
     * @author {Kayla Vincent, Kadhia Pryce, Khaela Purvill}
     * @version 1.0
     */

    public Recording() {
        try {
            //the file path
            File file = new File("Recording.txt");
            FileReader fr = new FileReader(file);
            while (fr.read() != -1) {
                tp.read(fr, null);
            }
            fr.close();
        } catch (
                Exception ex) {
            ex.printStackTrace();
        }

        JScrollPane jp = new JScrollPane(tp);
        fe.add(jp);
        fe.setIconImage(i);
        fe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fe.setSize(300, 240);
        fe.setVisible(true);
    }
}
