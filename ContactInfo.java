import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;

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
        
        
        JLabel searchID = new JLabel("Enter ID: ");
        searchID.setForeground(foreground);
        searchID.setFont(searchID.getFont().deriveFont(30f));
        JTextField entry = new JTextField();
        entry.setColumns(20);
        entry.setMinimumSize(new Dimension(250, 50));
        entry.setMaximumSize(new Dimension(250, 50));
        entry.setFont(new Font("Arial", Font.PLAIN, 30));
        
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
        center.add(buttonPanel1, gc); //searchID 0,0
        //gc.weightx = 1;
        gc.gridx = 3;
        center.add(buttonPanel2, gc); //entry 1
        
        
        setTitle("Contact Information");
        setLayout(new BorderLayout(5,5));
        add(label, "North");
        //add(buttonPanel, "Center"); //SwingUtilities.CENTER
        add(center, "Center");
        
        //HashMap<String, Double> theWages = getData();
        //can't do results because results is with 2 columns
        //String[] headers = {"Name", "Wages"};
        //add(new Results(theWages, headers), "Center");

        getContentPane().setBackground(background);
        setSize(600,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(900, 200);
        setVisible(true);
	}
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ContactInfo::new);
    }
}
