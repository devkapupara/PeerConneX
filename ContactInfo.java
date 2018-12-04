import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class ContactInfo extends JFrame
{
	ContactInfo()
	{
		Color background = new Color(43,45,47);
        Color foreground = new Color(255,191,0);
        setBackground(background);
        setForeground(foreground);
                
        JLabel label = new JLabel("Contact Information", SwingConstants.CENTER);
        label.setForeground(foreground);
        label.setBackground(background);
        label.setFont(new Font("Arial", Font.BOLD, 50));
        
        JLabel selectType = new JLabel("Are you looking for a tutor or tutee?", SwingConstants.CENTER);
        selectType.setForeground(foreground);
        selectType.setBackground(background);
        selectType.setFont(new Font("Arial", Font.PLAIN, 35));
        
        JButton tutorButton = new JButton("Tutor");
        tutorButton.setFont(tutorButton.getFont().deriveFont(30f));
        
        JButton tuteeButton = new JButton("Tutee");
        tuteeButton.setFont(tuteeButton.getFont().deriveFont(30f));
        
        JPanel filler = new JPanel();
        filler.setBackground(background);
        
        JPanel buttonPanel1 = new JPanel();
        tutorButton.setPreferredSize(new Dimension(100, 50));
        buttonPanel1.setBackground(background);
        buttonPanel1.add(tutorButton);
        tutorButton.setForeground(foreground);
        tutorButton.setBackground(background);
        tutorButton.setBorder(new MatteBorder(1, 1, 1, 1, foreground));
        
        JPanel buttonPanel2 = new JPanel();
        tuteeButton.setPreferredSize(new Dimension(100, 50));
        buttonPanel2.setBackground(background);
        buttonPanel1.add(tuteeButton);
        tuteeButton.setForeground(foreground);
        tuteeButton.setBackground(background);
        tuteeButton.setBorder(new MatteBorder(1, 1, 1, 1, foreground));
        
        JPanel center = new JPanel(new GridBagLayout());
        center.setBackground(background);
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridwidth = 2;
        gc.gridx = 1;
        gc.gridy = 0;
        center.add(selectType, gc);
        gc.gridx = 1;
        gc.gridy = 2;
        center.add(buttonPanel1, gc);
        gc.gridx = 3;
        center.add(buttonPanel2, gc);
                
        setTitle("Contact Information");
        setLayout(new BorderLayout(5,5));
        add(label, "North");
        add(center, "Center");
        
        tutorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HashMap<String, String[]> info = getData("tutor");
				remove(center);
				add(new contact(info, "Tutors"), "Center");
				repaint();
				validate();
			}
        });

        tuteeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HashMap<String, String[]> info = getData("tutee");
				remove(center);
				add(new contact(info, "Tutees"), "Center");
				repaint();
				validate();
			}
        });
        getContentPane().setBackground(background);
        setSize(1200,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(300, 200);
        setVisible(true);
	}
	
	   private static HashMap<String, String[]> getData(String sub) {
	        HashMap<String, String[]> contact = new HashMap<>();

	        //final String DB_URL = "jdbc:mysql://localhost:3306/project?useSSL=false";
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
	            String query = "select name, phone, email from " + sub;
	            rs = stmt.executeQuery(query);
	            
	            //STEP 5: Process the results
	            while(rs.next()){
	            	String [] a = new String[2];
	            	a[0] = rs.getString(2);
	            	a[1] = rs.getString(3);
	                contact.put(rs.getString(1), a);
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
	        return contact;
	    }
	   
	private class contact extends JPanel {
		contact(HashMap<String, String[]> info, String type) {
	          Color background = new Color(43,45,47);
	          Color foreground = new Color(255,191,0);
	          String resultsLabel = "Contact Information for " + type;
	          JLabel label = new JLabel(resultsLabel, SwingConstants.CENTER);
	          label.setFont(label.getFont().deriveFont(35f));
	          label.setForeground(foreground);
	          JTable table = new JTable(info.size(), 3);
	          table.setFont(table.getFont().deriveFont(25f));
	          table.setRowHeight(30);
	          table.setForeground(foreground);
	          table.setBackground(background);
	          
	          table.setDefaultEditor(Object.class, null);
	          JTableHeader jth = table.getTableHeader();
	          jth.setFont(jth.getFont().deriveFont(30f));
	          table.setFont(table.getFont().deriveFont(25f));
	          jth.setBackground(background);
	          jth.setForeground(foreground);
	          table.setFillsViewportHeight(true);

	          table.setFillsViewportHeight(true);
	          table.setDefaultEditor(Object.class, null);
	          DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
	          renderer.setHorizontalAlignment(SwingConstants.CENTER);
	          String headers[] = {"Name", "Phone", "Email"};
	          for (int i = 0; i < 3; i++)
	          {
	              TableColumn tc = table.getTableHeader().getColumnModel().getColumn(i);
	              tc.setHeaderValue(headers[i]);
	          }
	          int row = 0;
	          String [] temp = new String[2];
	          for (String key : info.keySet()) {
	              table.setValueAt(key, row, 0);
	              temp = info.get(key);
	              table.setValueAt(temp[0], row, 1);
	              table.setValueAt(temp[1], row, 2);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ContactInfo::new);
    }
}