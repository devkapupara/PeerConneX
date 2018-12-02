package PeerConneX;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.util.*;
import java.util.HashMap;
import java.sql.*;

public class Reviews extends JFrame {

    private int tID;

    Reviews() {
        Color background = new Color(43, 45, 47);
        Color foreground = new Color(255, 191, 0);

        JLabel label = new JLabel("Find PeerConneX.Reviews for Tutor", SwingConstants.CENTER);
        label.setFont(label.getFont().deriveFont(50f));
        label.setForeground(foreground);
        JLabel searchID = new JLabel("Enter Tutor ID: ");
        searchID.setForeground(foreground);
        searchID.setFont(searchID.getFont().deriveFont(20f));
        JTextField entry = new JTextField();
        entry.setColumns(20);

        JPanel center = new JPanel(new GridBagLayout());
        center.setBackground(background);
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        center.add(searchID);
        gc.gridx = 1;
        gc.weightx = 1;
        center.add(entry);
        JButton showReviewsButton = new JButton("Show PeerConneX.Reviews");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setSize(100, showReviewsButton.getHeight());
        buttonPanel.setBackground(background);
        buttonPanel.add(showReviewsButton);
        showReviewsButton.setForeground(foreground);
        showReviewsButton.setBackground(background);
        showReviewsButton.setFont(showReviewsButton.getFont().deriveFont(20f));
        showReviewsButton.setBorder(new MatteBorder(1, 1, 1, 1, foreground));
        showReviewsButton.setSize(300, 50);
        showReviewsButton.addActionListener(e -> {
            tID = Integer.valueOf(entry.getText());
            HashMap<Integer, String> reviews = getData(tID);
            remove(center);
            remove(buttonPanel);
            add(new SearchResults(reviews), "Center");
            repaint();
            validate();
        });

        JPanel filler = new JPanel();
        filler.setBackground(background);
        setTitle("PeerConneX.Reviews");
        setLayout(new BorderLayout(5,5));
        add(center, "Center");
        add(label, "North");
        add(filler, "East");
        add(filler, "West");
        add(buttonPanel, "South", SwingUtilities.CENTER);
        setSize(700,700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(background);
        setLocation(900, 200);
        setVisible(true);
    }

    private class SearchResults extends JPanel {
        SearchResults(HashMap<Integer, String> reviews) {
            Color background = new Color(43,45,47);
            Color foreground = new Color(255,191,0);
            String resultsLabel = "PeerConneX.Reviews for " + tID + ":";
            JLabel label = new JLabel(resultsLabel, SwingConstants.CENTER);
            label.setFont(label.getFont().deriveFont(35f));
            label.setForeground(foreground);
            JTable table = new JTable(reviews.size(), 1);
            table.setForeground(foreground);
            table.setBackground(background);

            JTableHeader jth = table.getTableHeader();
            jth.setBackground(background);
            jth.setForeground(foreground);

            table.setFillsViewportHeight(true);
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(SwingConstants.CENTER);
            int row = 0;
            for (int id : reviews.keySet()) {
                table.setValueAt(reviews.get(id), row, 0);
                row++;
            }
            JPanel east = new JPanel();
            east.setBackground(background);
            JPanel west = new JPanel();
            west.setBackground(background);
            JPanel south = new JPanel();
            south.setBackground(background);

            setLayout(new BorderLayout());
            add(label, "North");
            add(new JScrollPane(table), "Center");
            add(east, "East");
            add(west, "West");
            add(south, "South");
            setSize(500,500);
            setBackground(background);
            setVisible(true);
        }


    }

    private static HashMap<Integer, String> getData(int id) {
        LinkedHashMap<Integer, String> reviews = new HashMap<>();

        final String DB_URL = "jdbc:mysql://localhost:3306/DBProject?useSSL=false";

        final String USER = "root";
        final String PASS = "Brxvn0697!";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            String query = String.format("select tutee_id, date, rating, comment from reviews where tutor_id = '%s'", id);
            rs = stmt.executeQuery(query);
            //STEP 5: Process the results
            while(rs.next()){
                String review = "On " + rs.getString(2) + ", student " + rs.getInt(1) + " wrote: " + rs.getString(4) + " Rated " + rs.getInt(3) + "/5.";
                reviews.put(rs.getInt(1), review);
            }
            for (int tutee_id : reviews.keySet()) {
                String comments = reviews.get(tutee_id);
                System.out.println(comments);
            }

        } catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if (rs!=null)
                    rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){}// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");

        return reviews;
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> new Reviews());
    }
}


