package skattjakt.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Panel som hanterar startskärmen med knappar för New Game, Highscores, och Exit Game.
 *
 * @author Mustafa Al-Saffar
 */
public class StartPanel extends JPanel {
    public StartPanel(ActionListener newGameListener, ActionListener highscoreListener, ActionListener exitListener) {
        setLayout(new GridLayout(4, 1, 10, 10));

        JLabel titleLabel = new JLabel("Välkommen till Skattjakt!", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(newGameListener);

        JButton highscoreButton = new JButton("Highscores");
        highscoreButton.addActionListener(highscoreListener);

        JButton exitButton = new JButton("Exit Game");
        exitButton.addActionListener(exitListener);

        add(titleLabel);
        add(newGameButton);
        add(highscoreButton);
        add(exitButton);
    }
}
