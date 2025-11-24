package skattjakt.view;

import skattjakt.controller.SpelController;

import javax.swing.*;
import java.awt.*;

/**
 * Panel som hanterar spelplanen.
 *
 * @author Mustafa Al-Saffar
 */
public class MainPanel extends JPanel {
    private JButton[][] buttons;
    private SpelController controller; // Referens till SpelController

    /**
     * Konstruktor som initierar spelplanen.
     */
    public MainPanel() {
        setLayout(new GridLayout(10, 10));
        buttons = new JButton[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int x = i;
                int y = j;
                JButton button = new JButton();
                button.addActionListener(e -> {
                    if (controller != null) {
                        controller.hanteraKlick(x, y);
                    }
                });
                buttons[i][j] = button;
                add(button);
            }
        }
    }

    /**
     * Kopplar en SpelController till spelplanen.
     *
     * @param controller SpelController att koppla.
     */
    public void setController(SpelController controller) {
        this.controller = controller;
    }

    /**
     * Uppdaterar en specifik knapp på spelplanen.
     *
     * @param x    Radens index.
     * @param y    Kolumnens index.
     * @param text Text att visa på knappen.
     */
    public void updateButton(int x, int y, String text) {
        buttons[x][y].setText(text);
        buttons[x][y].setEnabled(false);
    }
    public void resetBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttons[i][j].setText(""); // Töm texten på knappen
                buttons[i][j].setBackground(null); // Återställ bakgrundsfärg
                buttons[i][j].setEnabled(true); // Gör knappen aktiv igen
            }
        }
    }

    public void updateButtonWithColor(int x, int y, String objektTyp) {
        JButton button = buttons[x][y];
        switch (objektTyp) {
            case "Skatt":
                button.setBackground(Color.YELLOW);
                button.setText("S");
                break;
            case "Fälla":
                button.setBackground(Color.BLACK);
                button.setForeground(Color.WHITE);
                button.setText("F");
                break;
            case "Bra Överraskning":
                button.setBackground(Color.YELLOW);
                button.setText("Ö");
                break;
            case "Dålig Överraskning":
                button.setBackground(Color.RED);
                button.setForeground(Color.WHITE);
                button.setText("Ö");
                break;
            default:
                button.setText("X");
        }
        button.setEnabled(false); // Inaktivera knappen efter öppning
    }


}
