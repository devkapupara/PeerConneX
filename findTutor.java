import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.HashMap;

public class findTutor extends JFrame
{
    private String subject;

    findTutor(HashMap<String, Double> map) {
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(background);
        setVisible(true);
    }

    private static HashMap<String, Double> getData(String subject) {
        HashMap<String, Double> ratings = new HashMap<>();
        ratings.put("A", 2.5);
        ratings.put("B", 4.3);
        ratings.put("C", 1.7);
        ratings.put("D", 5.0);
        ratings.put("E", 3.8);
        return ratings;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new findTutor(null));
    }
}
