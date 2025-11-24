package skattjakt.view;

import javax.swing.*;
import java.awt.*;

/**
 * Panel som hanterar kontroller eller information till höger.
 *
 * @author Mustafa Al-Saffar
 */
public class RightPanel extends JPanel {
    public RightPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Höger panel"));
        add(Box.createVerticalStrut(10)); // Lägger till lite mellanrum
        add(new JButton("Inställning 1"));
        add(new JButton("Inställning 2"));
    }
}
