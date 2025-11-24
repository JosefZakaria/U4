package skattjakt.view;

import javax.swing.*;
import java.awt.*;

/**
 * Panel som hanterar sidoinformation eller kontroller till vänster.
 *
 * @author Mustafa Al-Saffar
 */
public class LeftPanel extends JPanel {
    public LeftPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Vänster panel"));
        add(Box.createVerticalStrut(10)); // Lägger till lite mellanrum
        add(new JButton("Alternativ 1"));
        add(new JButton("Alternativ 2"));
    }
}
