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
import java.io.*;

public class Watchlist {

    JFrame fe = new JFrame("Watchlist");
    Image i = Toolkit.getDefaultToolkit().getImage("wlater.png");
    JTextPane tp = new JTextPane();

    /**
     * Watchlist: {Displays data saved in WatchList Text file}
     *
     * @author {Kayla Vincent, Kadhia Pryce, Khaela Purvill}
     * @version 1.0
     */
    public Watchlist() {
        try {
            //the file path
            File file = new File("WatchList.txt");
            FileReader fr = new FileReader(file);
            while (fr.read() != -1) {
                tp.read(fr, null);
            }
            fr.close();
        } catch (
                Exception ex) {
            ex.printStackTrace();
        }

        JScrollPane js = new JScrollPane(tp);
        fe.add(js);
        fe.setIconImage(i);
        fe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fe.setSize(300, 240);
        fe.setVisible(true);
    }
}