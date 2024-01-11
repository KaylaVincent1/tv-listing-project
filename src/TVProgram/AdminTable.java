/*
 * Name: {Kayla Vincent, Kadhia Pryce, Khaela Purvill}
 * Program: {Faculty of Engineering and Computing}
 * Course: {Object Oriented Programming}
 * Created: {November 1, 2020}
 * Updated: {November 28,2020}
 */

package TVProgram;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.Arrays;

public class AdminTable {
    JFrame fr = new JFrame("Admin Table");
    JTable jt = new JTable();
    DefaultTableModel model = new DefaultTableModel();
    Image icon = Toolkit.getDefaultToolkit().getImage("admin_gui.png");

    /**
     * AdminTable: {Opens new GUI window  to display editable table
     * User allowed to add, delete, update and save channels}
     *
     * @author {Kayla Vincent, Kadhia Pryce, Khaela Purvill}
     * @version 1.0
     */
    public AdminTable() {

        //Reads CListing File into JTable to be displayed
        File file = new File("CListing.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            // get the first line
            // get the columns name from the first line
            // set columns name to the jtable model

            String firstLine = br.readLine().trim();
            String[] columnsName = firstLine.split(",");
            jt = new JTable();
            model = (DefaultTableModel) jt.getModel();
            model.setColumnIdentifiers(columnsName);

            // get lines from txt file
            Object[] tableLines = br.lines().toArray();

            // extract data from lines
            // set data to jtable model
            for (Object tableLine : tableLines) {
                String line = tableLine.toString().trim();
                String[] dataRow = line.split(",");
                model.addRow(dataRow);
            }
            jt = new JTable(model);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        //create JButton
        JButton edit = new JButton("Edit");
        JButton back = new JButton("Back");
        JButton save = new JButton("Save");
        JButton add = new JButton("Add");
        JButton delete = new JButton("Delete");
        JButton update = new JButton("Update");

        //create JLabels
        JLabel c = new JLabel("Channel:");
        JLabel t6 = new JLabel("6:00 PM:");
        JLabel t7 = new JLabel("7:00 PM:");
        JLabel t8 = new JLabel("8:00 PM:");
        JLabel t9 = new JLabel("9:00 PM:");
        JLabel t10 = new JLabel("10:00 PM:");
        JLabel t11 = new JLabel("11:00 PM:");
        JLabel t12 = new JLabel("12:00 AM:");

        // create JTextFields
        JTextField channel = new JTextField(25);
        JTextField time6 = new JTextField(25);
        JTextField time7 = new JTextField(25);
        JTextField time8 = new JTextField(25);
        JTextField time9 = new JTextField(25);
        JTextField time10 = new JTextField(25);
        JTextField time11 = new JTextField(25);
        JTextField time12 = new JTextField(25);

        // Add ActionListener to Edit
        //Opens new window to display Channel editing
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameText = new JFrame("Channel Editing");
                Image icon1 = Toolkit.getDefaultToolkit().getImage("c_edit.jpg");

                JPanel c_edit = new JPanel(new FlowLayout());
                c_edit.add(c);
                c_edit.add(channel);
                c_edit.add(t6);
                c_edit.add(time6);
                c_edit.add(t7);
                c_edit.add(time7);
                c_edit.add(t8);
                c_edit.add(time8);
                c_edit.add(t9);
                c_edit.add(time9);
                c_edit.add(t10);
                c_edit.add(time10);
                c_edit.add(t11);
                c_edit.add(time11);
                c_edit.add(t12);
                c_edit.add(time12);

                JPanel editing = new JPanel(new FlowLayout());
                editing.add(add);
                editing.add(delete);
                editing.add(update);

                frameText.add(c_edit, BorderLayout.CENTER);
                frameText.add(editing, BorderLayout.SOUTH);
                frameText.setIconImage(icon1);
                frameText.setVisible(true);
                frameText.pack();
                frameText.setLocationRelativeTo(null);
                frameText.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameText.setSize(300, 500);
            }
        });

        //Adds Action to Back Button
        //Returns Admin to User Menu
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Channels();
                fr.dispose();
            }
        });

        //Adds Action to Save Button
        //Saves the newly edited data to CListing Text File
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    File f = new File("CListing.txt");
                    FileWriter frr = new FileWriter(f);
                    frr.write(Arrays.toString(new String[]{"Channel", "6:00pm", "7:00pm", "8:00pm", "9:00pm", "10:00pm", "11:00pm", "12:00am"}));
                    frr.write("\n");
                    try (PrintWriter pr = new PrintWriter(frr)) {
                        for (int row = 0; row < jt.getRowCount(); row++) {
                            for (int col = 0; col < jt.getColumnCount(); col++) {
                                pr.print(jt.getValueAt(row, col) + ",");
                            }
                            pr.println(" ");
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Edit Saved");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error");
                }
            }
        });

        Object[] row = new Object[8];

        //Add Function - Inserts new data into rows
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                row[0] = channel.getText();
                row[1] = time6.getText();
                row[2] = time7.getText();
                row[3] = time8.getText();
                row[4] = time9.getText();
                row[5] = time10.getText();
                row[6] = time11.getText();
                row[7] = time12.getText();

                model.addRow(row);
            }
        });

        //Delete Function - removes selected row
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = jt.getSelectedRow();
                if (i >= 0) {
                    model.removeRow(i);
                } else {
                    JOptionPane.showMessageDialog(null, "Deletion Error");
                }
            }
        });

        //When selecting a row the data appears in textfield in editing menu
        jt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int i = jt.getSelectedRow();

                channel.setText(model.getValueAt(i, 0).toString());
                time6.setText(model.getValueAt(i, 1).toString());
                time7.setText(model.getValueAt(i, 2).toString());
                time8.setText(model.getValueAt(i, 3).toString());
                time9.setText(model.getValueAt(i, 4).toString());
                time10.setText(model.getValueAt(i, 5).toString());
                time11.setText(model.getValueAt(i, 6).toString());
                time12.setText(model.getValueAt(i, 7).toString());
            }
        });

        //Update Function - Changes data in rows
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = jt.getSelectedRow();

                if (i >= 0) {
                    model.setValueAt(channel.getText(), i, 0);
                    model.setValueAt(time6.getText(), i, 1);
                    model.setValueAt(time7.getText(), i, 2);
                    model.setValueAt(time8.getText(), i, 3);
                    model.setValueAt(time9.getText(), i, 4);
                    model.setValueAt(time10.getText(), i, 5);
                    model.setValueAt(time11.getText(), i, 6);
                    model.setValueAt(time12.getText(), i, 7);

                } else {
                    JOptionPane.showMessageDialog(null, "Updating Error");
                }
            }
        });


        //Adds Panel to Frame
        //BorderLayout sets orientation of Panels and ScrollPane
        //Adds Image to GUI Frame
        JPanel control = new JPanel(new FlowLayout());
        control.add(edit);
        control.add(back);
        control.add(save);
        JScrollPane sp = new JScrollPane(jt);
        fr.add(control, BorderLayout.SOUTH);
        fr.add(sp);
        fr.setIconImage(icon);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(1366, 700);
        fr.setVisible(true);
    }
}
