/*
 * Name: {Kayla Vincent, Kadhia Pryce, Khaela Purvill}
 * Program: {Faculty of Engineering and Computing}
 * Course: {Object Oriented Programming}
 * Created: {November 1, 2020}
 * Updated: {November 28,2020}
 */

package TVProgram;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Channel: {Opens a GUI window to view channels and select a variety of options.
 * Also allows Users to access Admin Table using Main and specified password.}
 *
 * @author {Kayla Vincent, Kadhia Pryce, Khaela Purvill}
 * @version 1.0
 */

public class Channels extends Component {
    JFrame f;
    JTextField jtf;
    JLabel searchLbl;
    TableRowSorter sorter;
    JTable jt;
    Image ic = Toolkit.getDefaultToolkit().getImage("tv.png");

    /**
     * {    -Opens GUI
     *      -Displays Time and Table Header
     *      -Reads File to Display Channel Programs
     *      -Colour Codes Programs
     *      -Searches Text
     *      -Saves Information to new File locations
     *      -Display Menu which opens new Table}
     *
     */
    public Channels() {
        //Creation of Window Frame
        f = new JFrame("TV Program");

        //JLabel for Header and Date
        JLabel l = new JLabel("JCTCL Cable TV Programme Listing");
        JLabel timelabel = new JLabel();
        //Changes Font, Size and Alignment
        l.setFont(new Font("Times New Romans", Font.PLAIN, 15));
        l.setHorizontalAlignment(JLabel.LEFT);
        timelabel.setHorizontalAlignment(JLabel.RIGHT);

        //Displays Date in Header
        DateFormat timeFormat = new SimpleDateFormat("E, dd/MMM/yyyy HH:mm:ss");
        ActionListener timerListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Date date = new Date();
                String time = timeFormat.format(date);
                timelabel.setText(time);
            }
        };
        Timer timer = new Timer(1000, timerListener);
        //to make sure it doesn't wait one second at the start
        timer.setInitialDelay(0);
        timer.start();

        //Reads CListing File into JTable to be displayed
        File file = new File("CListing.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            // get the first line
            // get the columns name from the first line
            // set columns name to the jtable model

            String firstLine = br.readLine().trim();
            String[] columnsName = firstLine.split(",");
            //Creates JTable
            jt = new JTable();
            DefaultTableModel model = (DefaultTableModel) jt.getModel();
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

            sorter = new TableRowSorter<>(model);
            jt = new JTable(model)
            //changes colour of the rows
            {
                public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(getBackground());
                    int modelRow = convertRowIndexToModel(row);
                    String channel = (String) getModel().getValueAt(modelRow, 0);
                    //RED - Movies
                    //WHITE - General and News(background is default white so only one example used
                    //GREEN - Weather
                    //YELLOW - Comedy
                    //MAGENTA - Kids
                    //BLUE - Gospel
                    if ("207 - FAM".equals(channel)) c.setBackground(Color.RED);
                    if ("212 - SCI".equals(channel)) c.setBackground(Color.RED);
                    if ("214 - HBO".equals(channel)) c.setBackground(Color.RED);
                    if ("217 - NEX".equals(channel)) c.setBackground(Color.RED);
                    if ("237 - HLW".equals(channel)) c.setBackground(Color.RED);
                    if ("238 - VH1".equals(channel)) c.setBackground(Color.RED);
                    if ("239 - AMC".equals(channel)) c.setBackground(Color.RED);
                    if ("200 - CNN".equals(channel)) c.setBackground(Color.WHITE);
                    if ("209 - WCH".equals(channel)) c.setBackground(Color.GREEN);
                    if ("210 - COM".equals(channel)) c.setBackground(Color.YELLOW);
                    if ("244 - COM".equals(channel)) c.setBackground(Color.YELLOW);
                    if ("247 - ABC".equals(channel)) c.setBackground(Color.YELLOW);
                    if ("248 - TLC".equals(channel)) c.setBackground(Color.YELLOW);
                    if ("206 - DSN".equals(channel)) c.setBackground(Color.MAGENTA);
                    if ("208 - KID".equals(channel)) c.setBackground(Color.MAGENTA);
                    if ("211 - ANI".equals(channel)) c.setBackground(Color.MAGENTA);
                    if ("219 - CNE".equals(channel)) c.setBackground(Color.MAGENTA);
                    if ("243 - BOO".equals(channel)) c.setBackground(Color.MAGENTA);
                    if ("202 - TBN".equals(channel)) c.setBackground(Color.BLUE);
                }
                return c;
            }

            };
            jt.setRowSorter(sorter);


            //Adds Search Field to Frame
            jtf = new JTextField(15);
            searchLbl = new JLabel("Search");

            //Searches through the table and display textfield result
            jtf.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    search(jtf.getText());
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    search(jtf.getText());
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    search(jtf.getText());
                }

                public void search(String str) {
                    if (str.length() == 0) {
                        sorter.setRowFilter(null);
                    } else {
                        sorter.setRowFilter(RowFilter.regexFilter(str));
                    }
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        //Allows for cell selection instead of row selection
        jt.setCellSelectionEnabled(true);
        ListSelectionModel cellSelectionModel = jt.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                final String[] selectedData = {null};

                int[] selectedRow = jt.getSelectedRows();
                int[] selectedColumns = jt.getSelectedColumns();

                for (int i = 0; i < selectedRow.length; i++) {
                    for (int j = 0; j < selectedColumns.length; j++) {
                        selectedData[0] = (String) jt.getValueAt(selectedRow[i], selectedColumns[j]);
                    }
                    //Creates PopUp Menu
                    JPopupMenu menu = new JPopupMenu("Selection");
                    JMenu vm = new JMenu("View More");
                    JMenu vl = new JMenu("View Later");

                    menu.add(vm);
                    menu.add(vl);

                    //Adds Action to View More when clicked
                    //Opens new Window called ShowViewMore
                    vm.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            super.mouseClicked(e);
                            new ShowViewMore();
                        }
                    });

                    //Adds Action to View Later when clicked
                    //Creates WatchList file and appends new information each time menu is clicked
                    String finalSelectedData = selectedData[0];
                    vl.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            super.mouseClicked(e);

                            File file = new File("WatchList.txt");
                            //lets write to file
                            try {
                                FileWriter fw = new FileWriter(file, true);
                                BufferedWriter bw = new BufferedWriter(fw);
                                int[] selectedRow = jt.getSelectedRows();
                                int[] selectedColumns = jt.getSelectedColumns();
                                for (int i = 0; i < selectedRow.length; i++) {
                                    for (int j = 0; j < selectedColumns.length; j++) {

                                        //write
                                        bw.append(selectedData[0] = (String) jt.getValueAt(selectedRow[i], selectedColumns[j]));
                                    }
                                    bw.write("\n_____________________\n");//record per line
                                }
                                //Displays message when clicked
                                JOptionPane.showMessageDialog(null, "Added to View Later List", "Watchlist", JOptionPane.INFORMATION_MESSAGE);
                                bw.close();
                                fw.close();
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(null, "ERROR", "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });

                    jt.setComponentPopupMenu(menu);
                    jt.addMouseListener(new TableMouseListener(jt));
                }
            }
        });

        //Set size of Table and adds ScrollPane
        jt.setBounds(30, 40, 200, 300);
        JScrollPane sp = new JScrollPane(jt);

        //Creates JButton
        JButton b1 = new JButton("Watch later");
        JButton b2 = new JButton("Menu");
        JButton b3 = new JButton("Record");

        // Add ActionListener to Watch Later
        // Open new window
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Watchlist();
            }
        });


        // Add ActionListener to Menu
        //Creates Login, removes current window and opens new window
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField textArea = new JTextField();
                JTextField jtx = new JTextField();
                jtx.setBounds(50, 100, 200, 30);
                JPasswordField jpf = new JPasswordField();
                jpf.setBounds(50, 150, 200, 30);
                f.add(jtx);
                f.add(jpf);
                Object[] inputFields = {"Username", jtx, "Password", jpf};
                int option = JOptionPane.showConfirmDialog(f, inputFields, "Admin Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

                if (option == JOptionPane.OK_OPTION) {
                    String user;
                    String password;
                    user = jtx.getText();
                    password = jpf.getText();
                    if (user.equalsIgnoreCase("admin") && password.equalsIgnoreCase("12345")) {
                        JOptionPane.showMessageDialog(null, "Login Successful");
                        f.dispose();
                        new AdminTable();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid User.....Please Check Username and Password");
                    }
                }
            }
        });

        // Add ActionListener to Record
        //Writes data when pressed to a new window
        //Opens new window once data added
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String[] selectedData = {null};

                File file = new File("Recording.txt");
                //lets write to file
                try {
                    FileWriter fwrite = new FileWriter(file, true);
                    BufferedWriter bwrite = new BufferedWriter(fwrite);
                    int[] selectedRow = jt.getSelectedRows();
                    int[] selectedColumns = jt.getSelectedColumns();
                    for (int i = 0; i < selectedRow.length; i++) {
                        for (int j = 0; j < selectedColumns.length; j++) {

                            //write
                            bwrite.append(selectedData[0] = (String) jt.getValueAt(selectedRow[i], selectedColumns[j]));
                        }
                        bwrite.write("\n_____________________\n");//record per line
                    }
                    JOptionPane.showMessageDialog(null, "Recording Started", "Recoding List", JOptionPane.INFORMATION_MESSAGE);
                    bwrite.close();
                    fwrite.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "ERROR", "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
                }
                new Recording();
            }
        });

        //Adds Panel to Frame
        JPanel command = new JPanel(new FlowLayout());
        // Add the JButtons to Panel
        command.add(b1);
        command.add(b3);
        command.add(b2);
        command.add(searchLbl);
        command.add(jtf);

        //Adds Panel to Frame
        JPanel title = new JPanel(new FlowLayout());
        // Add the JButtons to Panel
        title.add(l);
        title.add(timelabel);

        //BorderLayout sets orientation of Panels and ScrollPane
        //Adds Image to GUI Frame
        f.add(sp, BorderLayout.CENTER);
        f.add(title, BorderLayout.NORTH);
        f.add(command, BorderLayout.SOUTH);
        f.setIconImage(ic);
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        f.setSize(1366, 700);
        f.setVisible(true);
    }

    /**
     * TableMouseListener: {Selects the row at which point the mouse is clicked}
     *
     * @author {Kayla Vincent, Kadhia Pryce, Khaela Purvill}
     * @version 1.0
     */
    public static class TableMouseListener extends MouseAdapter {

        private final JTable table;
        /**
         * Selects the row at which point the mouse is clicked
         *
         * @param table Calls references table
         *
         */
        public TableMouseListener(JTable table) {
            this.table = table;
        }

        @Override
        public void mousePressed(MouseEvent event) {

            Point point = event.getPoint();
            int currentRow = table.rowAtPoint(point);
            table.setRowSelectionInterval(currentRow, currentRow);

        }
    }

}