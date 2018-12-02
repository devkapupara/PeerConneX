import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.HashMap;
import java.sql.*;

public class findTutor extends JFrame
{
    private String subject;

    findTutor() {
        Color background = new Color(43,45,47);
        Color foreground = new Color(255,191,0);

        String[] subjects = {"Math", "Computer Science", "Physics", "Chemistry", "Biology", "Business", "Graphic Design"};
        JComboBox<String> dropdown = new JComboBox<>(subjects);

        JLabel label = new JLabel("Find Tutor", SwingConstants.CENTER);
        label.setFont(label.getFont().deriveFont(50f));
        label.setForeground(foreground);
        JLabel searchLabel = new JLabel("Select subject: ");
        searchLabel.setForeground(foreground);
        searchLabel.setFont(searchLabel.getFont().deriveFont(20f));

        JPanel center = new JPanel(new GridBagLayout());
        center.setBackground(background);
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        center.add(searchLabel);
        gc.gridx = 1;
        gc.weightx = 1;
        center.add(dropdown);
        JButton findButton = new JButton("  Find  ");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setSize(100, findButton.getHeight());
        buttonPanel.setBackground(background);
        buttonPanel.add(findButton);
        findButton.setForeground(foreground);
        findButton.setFont(findButton.getFont().deriveFont(20f));
        findButton.setBackground(background);
        findButton.setBorder(new MatteBorder(1,1,1,1, foreground));
        findButton.setSize(300,50);
        findButton.addActionListener(e -> {
            subject = (String)dropdown.getSelectedItem();
            HashMap<String, Double> ratings = getData(subject);
            remove(center);
            remove(buttonPanel);
            add(new Results(ratings), "Center");
            repaint();
            validate();
        });

        JPanel filler = new JPanel();
        filler.setBackground(background);

        setTitle("Find Tutor");
        setLayout(new BorderLayout(5,5));
        add(center, "Center");
        add(label, "North");
        add(filler, "East");
        add(filler, "West");
        add(buttonPanel, "South", SwingUtilities.CENTER);
        setSize(500,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(background);
        setVisible(true);
    }

    private static HashMap<String, Double> getData(String sub) {
        HashMap<String, Double> ratings = new HashMap<>();

        final String DB_URL = "jdbc:mysql://localhost:3306/project?useSSL=false";

        final String USER = "dev";
        final String PASS = "";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            String query = String.format("select name, avg_rating, subject from findtutor where subject = '%s'", sub);
            rs = stmt.executeQuery(query);
            //STEP 5: Process the results
            while(rs.next()){
                ratings.put(rs.getString("name"), rs.getDouble("avg_rating"));
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

        return ratings;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(findTutor::new);
    }
}
