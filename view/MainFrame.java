package skattjakt.view;

import skattjakt.controller.SpelController;

import javax.swing.*;
import java.awt.*;

/**
 * Huvudfönster som hanterar hela spelet Skattjakt, inklusive startskärmen och spelgränssnittet.
 *
 * @author Mustafa
 */
public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private StartPanel startPanel;
    private MainPanel gamePanel;
    private BottomPanel bottomPanel;

    private SpelController controller;

    /**
     * Konstruktor för MainFrame. Initierar layout och paneler.
     */
    public MainFrame() {
        super("Skattjakt");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        initStartPanel();
        initGamePanel();

        add(mainPanel);
    }

    /**
     * Kopplar en SpelController till GUI:t.
     *
     * @param controller SpelController att koppla.
     */
    public void setController(SpelController controller) {
        this.controller = controller;
        gamePanel.setController(controller); // Koppla kontrollern till spelpanelen
    }

    /**
     * Initierar startpanelen med menyvalen.
     */
    private void initStartPanel() {
        startPanel = new StartPanel(
                e -> showNameDialog(),
                e -> controller.showHighscores(),
                e -> System.exit(0)
        );
        mainPanel.add(startPanel, "START");
    }

    /**
     * Initierar spelgränssnittet med spelplanen och bottenpanelen.
     */
    private void initGamePanel() {
        gamePanel = new MainPanel();
        bottomPanel = new BottomPanel();

        JPanel gameContainer = new JPanel(new BorderLayout());
        gameContainer.add(gamePanel, BorderLayout.CENTER);
        gameContainer.add(bottomPanel, BorderLayout.SOUTH);

        mainPanel.add(gameContainer, "GAME");
    }

    /**
     * Visar en dialogruta för att ange spelarnamn.
     */
    private void showNameDialog() {
        String player1 = JOptionPane.showInputDialog(this, "Ange namn för Spelare 1:");
        String player2 = JOptionPane.showInputDialog(this, "Ange namn för Spelare 2:");

        if(player1.isEmpty() && player2.isEmpty()){
            JOptionPane.showMessageDialog(this, "Båda spelarnamn måste anges!", "Fel", JOptionPane.ERROR_MESSAGE);
        }
            controller.setSpelareNamn(player1, player2);
            bottomPanel.setPlayerNames(player1, player2); // Uppdatera namn i bottenpanelen
            bottomPanel.updateCurrentPlayer(player1); // Visa att Spelare 1 börjar
            showGamePanel(); // Växla till spelgränssnittet
        }


    /**
     * Visar startskärmen.
     */
    public void showStartPanel() {
        cardLayout.show(mainPanel, "START");
    }

    /**
     * Visar spelgränssnittet.
     */
    public void showGamePanel() {
        cardLayout.show(mainPanel, "GAME");
    }

    /**
     * Uppdaterar statusen för spelarna.
     *
     * @param player1Status Status för Spelare 1.
     * @param player2Status Status för Spelare 2.
     */
    public void updateStatus(String player1Status, String player2Status) {
        bottomPanel.updateStatus(player1Status, player2Status);
    }

    /**
     * Uppdaterar tur-indikatorn för den aktuella spelaren.
     *
     * @param currentPlayerName Namn för den spelare som har turen.
     */
    public void updateCurrentPlayer(String currentPlayerName) {
        bottomPanel.updateCurrentPlayer(currentPlayerName);
    }

//    /**
//     * Uppdaterar en specifik knapp på spelplanen.
//     *
//     * @param x Koordinat x för knappen.
//     * @param y Koordinat y för knappen.
//     * @param text Text att visa på knappen.
//     */
//    public void updateButton(int x, int y, String text) {
//        gamePanel.updateButton(x, y, text);
//    }
    public void resetGameBoard() {
        gamePanel.resetBoard(); // Anropa metoden i MainPanel för att återställa spelplanen
    }

    public void updateButtonWithColor(int x, int y, String objektTyp) {
        gamePanel.updateButtonWithColor(x, y, objektTyp); // Skicka vidare till MainPanel
    }

    public void showMessage(String message, String title){
        JOptionPane.showMessageDialog(this,
                message, title, JOptionPane.INFORMATION_MESSAGE);

    }
    public void showErrorMessage(String message, String title){
        JOptionPane.showMessageDialog(this,
                message, title, JOptionPane.ERROR_MESSAGE);
    }
    public int startNewGame(String message, String title){
return JOptionPane.showOptionDialog(
        this,
         message, title,
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        new String[]{"Start New Game", "Exit"},
        "Start New Game"
);
    }
    public String getPlayerName(String message){

        return JOptionPane.showInputDialog(this, message, "Info", JOptionPane.QUESTION_MESSAGE);
    }


}
