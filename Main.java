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
		Font title = new Font("Arial", Font.BOLD, 50);
		Font buttonFont = new Font("Arial", Font.PLAIN, 35);

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
		findTutor.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				new findTutor();
			}
		});
		Button contact = new Button("Search Contact Info");
		contact.setBackground(background);
		contact.setForeground(foreground);
		contact.setFont(buttonFont);
		contact.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				new ContactInfo();
			}
		});
		Button wages = new Button("Show Wages");
		wages.setBackground(background);
		wages.setForeground(foreground);
		wages.setFont(buttonFont);
		wages.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				new Wages();
			}
		});
		Button review = new Button("Reviews");
		review.setBackground(background);
		review.setForeground(foreground);
		review.setFont(buttonFont);
		review.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				new Reviews();
			}
		});
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