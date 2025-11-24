package skattjakt.view;

import javax.swing.*;
import java.awt.*;

/**
 * Panel som hanterar status för spelarna och tur-indikatorn.
 *
 * @author Mustafa
 */
public class BottomPanel extends JPanel {
    private JLabel player1Info;
    private JLabel player2Info;
    private JLabel currentPlayerLabel;

    /**
     * Konstruktor som initierar spelarstatuspanelen.
     */
    public BottomPanel() {
        setLayout(new BorderLayout());

        // Status för Spelare 1 (vänster)
        player1Info = new JLabel("Spelare 1: Poäng 0, Besättningsmedlemmar 3", JLabel.LEFT);
        player1Info.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0)); // Liten marginal till vänster

        // Status för Spelare 2 (höger)
        player2Info = new JLabel("Spelare 2: Poäng 0, Besättningsmedlemmar 3", JLabel.RIGHT);
        player2Info.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10)); // Liten marginal till höger

        // Tur-indikator (mitten)
        currentPlayerLabel = new JLabel("Väntar på tur...", JLabel.CENTER);
        currentPlayerLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Lägg till komponenter i panelen
        add(player1Info, BorderLayout.WEST);  // Spelare 1 längst till vänster
        add(currentPlayerLabel, BorderLayout.CENTER); // Tur-indikator i mitten
        add(player2Info, BorderLayout.EAST);  // Spelare 2 längst till höger
    }

    /**
     * Uppdaterar statusen för spelarna.
     *
     * @param player1Status Status för Spelare 1.
     * @param player2Status Status för Spelare 2.
     */
    public void updateStatus(String player1Status, String player2Status) {
        player1Info.setText(player1Status);
        player2Info.setText(player2Status);
    }

    /**
     * Sätter namnen för spelarna och initierar deras status.
     *
     * @param player1Name Namn för Spelare 1.
     * @param player2Name Namn för Spelare 2.
     */
    public void setPlayerNames(String player1Name, String player2Name) {
        player1Info.setText(player1Name + ": Poäng 0, Besättningsmedlemmar 3");
        player2Info.setText(player2Name + ": Poäng 0, Besättningsmedlemmar 3");
    }

    /**
     * Uppdaterar tur-indikatorn för den aktuella spelaren.
     *
     * @param currentPlayerName Namn för den spelare som har turen.
     */
    public void updateCurrentPlayer(String currentPlayerName) {
        currentPlayerLabel.setText(currentPlayerName + "'s tur!");
    }
}
