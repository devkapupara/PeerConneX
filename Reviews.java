import java.awt.*;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.util.*;
import java.sql.*;

public class Reviews extends JFrame {

    private int tID;

    Reviews() {
        Color background = new Color(43, 45, 47);
        Color foreground = new Color(255, 191, 0);

        JLabel label = new JLabel("Find Reviews for Tutor", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 50));
        label.setForeground(foreground);
        JLabel searchID = new JLabel("Enter Tutor ID: ");
        searchID.setForeground(foreground);
        searchID.setFont(searchID.getFont().deriveFont(30f));
        JTextField entry = new JTextField();
        entry.setColumns(20);
        entry.setMinimumSize(new Dimension(250, 50));
        entry.setMaximumSize(new Dimension(250, 50));
        entry.setFont(new Font("Arial", Font.PLAIN, 30));

        JPanel center = new JPanel(new GridBagLayout());
        center.setBackground(background);
        center.add(searchID);
        center.add(entry);
        JButton showReviewsButton = new JButton("Show Reviews");
        showReviewsButton.setFont(showReviewsButton.getFont().deriveFont(30f));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(100, 80));
        showReviewsButton.setPreferredSize(new Dimension(250, 50));
        buttonPanel.setBackground(background);
        buttonPanel.add(showReviewsButton);
        showReviewsButton.setForeground(foreground);
        showReviewsButton.setBackground(background);
        showReviewsButton.setBorder(new MatteBorder(1, 1, 1, 1, foreground));
        showReviewsButton.addActionListener(e -> {
            tID = Integer.valueOf(entry.getText());
            ArrayList<String> reviews = getData(tID);
            remove(center);
            remove(buttonPanel);
            if (reviews.isEmpty())
            {
                JLabel aLabel = new JLabel("There are no reviews available for this tutor or this tutor does not exist.", SwingConstants.CENTER);
                aLabel.setFont(label.getFont().deriveFont(20f));
                aLabel.setForeground(foreground);
                aLabel.setBackground(background);
                add(aLabel, "Center");
            }
            else
            {
                add(new SearchResults(reviews), "Center");
            }
            repaint();
            validate();
        });

        setTitle("Reviews");
        setLayout(new BorderLayout(5,5));
        add(center, "Center");
        add(label, "North");
        add(buttonPanel, "South", SwingUtilities.CENTER);
        setSize(1200,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(background);
        setLocation(400, 200);
        setVisible(true);
    }

    private class SearchResults extends JPanel {
        SearchResults(ArrayList<String> reviews) {
            Color background = new Color(43,45,47);
            Color foreground = new Color(255,191,0);
            String resultsLabel = "Reviews for " + tID + ":";
            JLabel label = new JLabel(resultsLabel, SwingConstants.CENTER);
            label.setFont(label.getFont().deriveFont(35f));
            label.setForeground(foreground);
            JTable table = new JTable(reviews.size(), 1);
            table.setFont(table.getFont().deriveFont(25f));
            table.setRowHeight(30);
            table.setForeground(foreground);
            table.setBackground(background);

            JTableHeader jth = table.getTableHeader();
            jth.setBackground(background);
            jth.setForeground(background);

            table.setFillsViewportHeight(true);
            table.setDefaultEditor(Object.class, null);
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(SwingConstants.CENTER);
            int row = 0;
            for (String s : reviews) {
                table.setValueAt(s, row, 0);
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

    private static ArrayList<String> getData(int id) {
        ArrayList<String> reviews = new ArrayList<String>();

        //final String DB_URL = "jdbc:mysql://localhost:3306/DBProject?useSSL=false";
        
	    final String DB_URL = "jdbc:mysql://localhost:3306/peerconnectionproject";

        final String USER = "root";
        final String PASS = "";

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
                reviews.add(review);
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
        return reviews;
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> new Reviews());
    }
}