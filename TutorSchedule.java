import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class TutorSchedule extends JPanel
{
    TutorSchedule(String name, HashMap<String, Double> oldData)
    {
        setLayout(new BorderLayout());
        Color background = new Color(43,45,47);
        Color foreground = new Color(255,191,0);
        JLabel label = new JLabel(getData(name), SwingConstants.CENTER);
        label.setFont(label.getFont().deriveFont(20f));
        label.setForeground(foreground);
        label.setBackground(background);
        setBackground(background);
        add(label, "Center");
        
        if (getData(name) == null)
        {
        	System.out.println("YES");
        	label.setText("The tutor is not available this semester.");
        }
        add(label, "Center");
        
        JButton back = new JButton("  Back  ");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(100, 100));
        buttonPanel.setBackground(background);
        buttonPanel.add(back);
        back.setForeground(foreground);
        back.setFont(back.getFont().deriveFont(20f));
        back.setBackground(background);
        back.setBorder(new MatteBorder(1,1,1,1, foreground));
        String[] headers = {"Name", "Rating"};
        back.addActionListener(e -> {
            removeAll();
            add(new Results(oldData, headers, true));
            repaint();
            revalidate();
        });
        add(back, "South");
    }
    private static String getData(String name) {

        //final String DB_URL = "jdbc:mysql://localhost:3306/project?useSSL=false";
	    final String DB_URL = "jdbc:mysql://localhost:3306/peerconnectionproject";

        final String USER = "root";
        final String PASS = "";

        Connection conn = null;
        Statement stmt = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        StringBuilder sb = new StringBuilder("<html>" + name + "'s Schedule: <br>");
        int out;
        try{
        	
            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            cs = conn.prepareCall("{CALL getID(?, ?)}");
            cs.setString(1, name);
            cs.registerOutParameter(2, Types.INTEGER);
            boolean hasResult = cs.execute();
            out = cs.getInt(2);

            String query = String.format("Select day, start_time, end_time from tutor_schedule where id = % d", out);
            
            //STEP 4: Execute a query
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            SimpleDateFormat df = new SimpleDateFormat("hh:mm a");
            
            //STEP 5: Process the results
            while(rs.next()){
                String day = rs.getString(1);
                String startTime = df.format(rs.getTime(2));
                String endTime = df.format(rs.getTime(3));
                sb.append(String.format("%s from %s to %s.<br>", day, startTime, endTime));
            }
//            if (rs == null)
//            {
//            	sb.append("This tutor is unavailable for this semester");
//            }
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

        return sb.append("</html>").toString();
    }
}
