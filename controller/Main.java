package skattjakt.controller;

import skattjakt.view.MainFrame;

import javax.swing.SwingUtilities;

/**
 * Huvudklassen som startar spelet Skattjakt.
 *
 * @author Mustafa Al-Saffar
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Skapa GUI
            MainFrame gui = new MainFrame();

            // Skapa controller och koppla till GUI
            SpelController controller = new SpelController(gui);
            gui.setController(controller);

            // Visa GUI
            gui.setVisible(true);
        });
    }
}
