import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class Wages extends JFrame
{
	Wages()
	{
		Color background = new Color(43,45,47);
        Color foreground = new Color(255,191,0);
        setBackground(background);
        setForeground(foreground);
        
		JTextField title = new JTextField("WAGES");
		title.setFont(new Font("Arial", Font.PLAIN, 50));
		title.setBackground(background);
		title.setForeground(foreground);
		title.setHorizontalAlignment(JTextField.CENTER);
        
		add(title);
        setSize(500,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(background);
		setLocation(900, 200);
        setVisible(true);
	}
}
