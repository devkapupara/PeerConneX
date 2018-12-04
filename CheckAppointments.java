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
import java.util.Calendar;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class CheckAppointments extends JFrame{

	CheckAppointments()
	{
		Color background = new Color(43,45,47);
        Color foreground = new Color(255,191,0);
        setBackground(background);
        setForeground(foreground);
        
		Calendar use = Calendar.getInstance();
		int m = use.get(Calendar.MONTH);
		int d = use.get(Calendar.DATE);
		int y = use.get(Calendar.YEAR);
        
		HashMap<String, String[]> info = getData(m, d, y);
		JPanel center = new appointments(info,m,d,y);
		add(center, "Center");
		
        JButton prev = new JButton("Previous");
        prev.setFont(prev.getFont().deriveFont(30f));
        prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x = d - 1;
				remove(center);
				HashMap<String, String[]> info = getData(m, x, y);
				add(new appointments(info,m,d,y), "Center");
				repaint();
				validate();
			}
        });
        
        JButton next = new JButton("Next");
        next.setFont(next.getFont().deriveFont(30f));
        
        JPanel buttonPanel1 = new JPanel();
        prev.setPreferredSize(new Dimension(155, 38));
        buttonPanel1.setBackground(background);
        buttonPanel1.add(prev);
        prev.setForeground(foreground);
        prev.setBackground(background);
        prev.setBorder(new MatteBorder(1, 1, 1, 1, foreground));
        
        JPanel buttonPanel2 = new JPanel();
        next.setPreferredSize(new Dimension(155, 38));
        buttonPanel2.setBackground(background);
        buttonPanel1.add(next);
        next.setForeground(foreground);
        next.setBackground(background);
        next.setBorder(new MatteBorder(1, 1, 1, 1, foreground));
        
        JPanel low = new JPanel(new GridBagLayout());
        low.setBackground(background);
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridwidth = 2;
        gc.gridx = 1;
        gc.gridy = 2;
        low.add(buttonPanel1, gc);
        gc.gridx = 3;
        low.add(buttonPanel2, gc);
        
        setTitle("Check Appointments");
        setLayout(new BorderLayout(5,5));
        add(low, "South");
        
        getContentPane().setBackground(background);
        setSize(520,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(900, 200);
        setVisible(true);
	}
	
	   private static HashMap<String, String[]> getData(int m, int d, int y) {
	        HashMap<String, String[]> app = new HashMap<>();

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
	            String query = "select date, tutee_ID, tutor_ID from appointments where date = " + y + "-" + m + "-" + d;
	            rs = stmt.executeQuery(query);
	            
	            //STEP 5: Process the results
	            while(rs.next()){
	            	String [] a = new String[2];
	            	a[0] = rs.getString(2);
	            	a[1] = rs.getString(3);
	                app.put(rs.getString(1), a);
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
	        return app;
	    }
	   
	private class appointments extends JPanel {
		appointments(HashMap<String, String[]> info, int m, int d, int y) {
	          Color background = new Color(43,45,47);
	          Color foreground = new Color(255,191,0);
	          String resultsLabel = "Appointments for " + m + "/" + d + "/" + y;
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
	          String headers[] = {"Date", "Tutee", "Tutor"};
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
        SwingUtilities.invokeLater(CheckAppointments::new);
    }
}