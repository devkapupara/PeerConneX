import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MakeAppointment extends JFrame
{
	MakeAppointment()
	{
		Color background = new Color(43, 45, 47);
        Color foreground = new Color(255, 191, 0);

        JLabel label = new JLabel("Make Appointment", SwingConstants.CENTER);
        label.setForeground(foreground);
        label.setFont(new Font("Arial", Font.BOLD, 50));

	}
}
