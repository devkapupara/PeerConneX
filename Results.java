import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.HashMap;

public class Results extends JPanel
{
    Results(HashMap<String, Double> ratings, String headers[])
    {
        Color background = new Color(43,45,47);
        Color foreground = new Color(255,191,0);
        JLabel label = new JLabel("Results", SwingConstants.CENTER);
        label.setFont(label.getFont().deriveFont(35f));
        label.setForeground(foreground);
        JTable table = new JTable(ratings.size(), 2);
        table.setRowHeight(30);
        table.setForeground(foreground);
        table.setBackground(background);
        JTableHeader jth = table.getTableHeader();
        jth.setFont(jth.getFont().deriveFont(20f));
        table.setFont(table.getFont().deriveFont(15f));
        jth.setBackground(background);
        jth.setForeground(foreground);
        table.setFillsViewportHeight(true);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getModel().getColumnCount(); i++)
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);

        for (int i = 0; i < 2; i++)
        {
            TableColumn tc = table.getTableHeader().getColumnModel().getColumn(i);
            tc.setHeaderValue(headers[i]);
        }
        int row = 0;
        for (String key: ratings.keySet()) {
            table.setValueAt(key, row, 0);
            table.setValueAt(ratings.get(key), row++, 1);
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
        table.setDefaultEditor(Object.class, null);
        setSize(600,600);
        setBackground(background);
        setVisible(true);
    }
}
