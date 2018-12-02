//import com.mysql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Main 
{
	public static void main(String [] args)
	{
		Color background = new Color(43,45,47);
		Color foreground = new Color(255,191,0);
		JFrame frame = new JFrame();
		int DEFAULT_WIDTH = 600;
		int DEFAULT_HEIGHT = 800;
		frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		frame.setLocation(680, 100);
		Font title = new Font("Arial", Font.PLAIN, 50);
		Font buttonFont = new Font("Arial", Font.PLAIN, 35);
		
		JFrame frame2 = new JFrame();	
		frame2.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		frame2.setLocation(850, 160);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel buttons = new JPanel();
		buttons.setBackground(background);
		buttons.setLayout(new GridLayout(8,1));
		
		JTextField menu = new JTextField("MENU");
		menu.setFont(title);
		menu.setBackground(background);
		menu.setForeground(foreground);
		menu.setHorizontalAlignment(JTextField.CENTER);
				
		Button appointment = new Button("Make Appointment");
		appointment.setBackground(background);
		appointment.setForeground(foreground);
		appointment.setFont(buttonFont);
		Button findTutor = new Button("Find Tutors");
		findTutor.setBackground(background);
		findTutor.setForeground(foreground);
		findTutor.setFont(buttonFont);
		Button contact = new Button("Search Contact Info");
		contact.setBackground(background);
		contact.setForeground(foreground);
		contact.setFont(buttonFont);
		Button wages = new Button("Show Wages");
		wages.setBackground(background);
		wages.setForeground(foreground);
		wages.setFont(buttonFont);
		wages.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				Panel wagePanel = new Panel();
				wagePanel.setLayout(new BorderLayout());
				
				JTextField wage = new JTextField("WAGES");
				wage.setFont(title);
				wage.setBackground(background);
				wage.setForeground(foreground);
				wage.setHorizontalAlignment(JTextField.CENTER);
				
				wagePanel.add(wage, BorderLayout.NORTH);
				frame2.add(wagePanel);
				frame2.setVisible(true);
			}
		});
		Button review = new Button("Reviews");
		review.setBackground(background);
		review.setForeground(foreground);
		review.setFont(buttonFont);
		Button checkAppointments = new Button("Check Appointments");
		checkAppointments.setBackground(background);
		checkAppointments.setForeground(foreground);
		checkAppointments.setFont(buttonFont);
		Button cancelAppointments = new Button("Cancel Appointments");
		cancelAppointments.setBackground(background);
		cancelAppointments.setForeground(foreground);
		cancelAppointments.setFont(buttonFont);
		Button logout = new Button("Logout");
		logout.setBackground(background);
		logout.setForeground(foreground);
		logout.setFont(buttonFont);
		logout.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
			}
		});
		
		//buttons.add(menu);
		buttons.add(appointment);
		buttons.add(findTutor);
		buttons.add(contact);
		buttons.add(wages);
		buttons.add(review);
		buttons.add(checkAppointments);
		buttons.add(cancelAppointments);
		buttons.add(logout);

		frame.setLayout(new BorderLayout());
		frame.add(menu, BorderLayout.NORTH);
		frame.add(buttons, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}