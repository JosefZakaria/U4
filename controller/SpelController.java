package skattjakt.controller;

import skattjakt.model.*;
import skattjakt.view.MainFrame;


/**
 * Controller-klass som hanterar spelets logik och interaktion mellan modellen och GUI:t.
 *
 * @author Mustafa
 */
public class SpelController {
    private MainFrame gui;
    private Spelplan spelplan;
    private Spelare spelare1;
    private Spelare spelare2;
    private HighscoreList highscoreList;
    private boolean spelaresTur; // true = Spelare 1, false = Spelare 2

    /**
     * Konstruktor som initierar controller med GUI och modell.
     *
     * @param gui MainFrame som hanterar GUI:t.
     */
    public SpelController(MainFrame gui) {
        this.gui = gui;
        this.spelplan = new Spelplan(10, 10); // 10x10 spelplan
        this.highscoreList = new HighscoreList();
        this.spelaresTur = true; // Spelare 1 börjar
    }

    /**
     * Sätter namnen för spelarna.
     *
     * @param spelare1Namn Namn för Spelare 1.
     * @param spelare2Namn Namn för Spelare 2.
     */
    public void setSpelareNamn(String spelare1Namn, String spelare2Namn) {
        this.spelare1 = new Spelare(spelare1Namn);
        this.spelare2 = new Spelare(spelare2Namn);
    }

    /**
     * Hanterar ett knappklick från GUI:t.
     *
     * @param x Koordinat x för knappen.
     * @param y Koordinat y för knappen.
     */
    public void hanteraKlick(int x, int y) {
        NedgravtObjekt objekt = spelplan.grävPåPosition(x, y);
        Spelare aktuellSpelare = spelaresTur ? spelare1 : spelare2;

        if (objekt != null) {
            objekt.hanteraSpelare(aktuellSpelare);
            String objektTyp;
            if (objekt instanceof Skatt) {
                objektTyp = "Skatt";
            } else if (objekt instanceof Falla) {
                objektTyp = "Fälla";
            } else if (objekt instanceof Overraskning) {
                Overraskning overraskning = (Overraskning) objekt;
                objektTyp = overraskning.getTyp().equals("extra poäng") ? "Bra Överraskning" : "Dålig Överraskning";
            } else {
                objektTyp = "Okänt";
            }
            gui.updateButtonWithColor(x, y, objektTyp);
        } else {
            gui.updateButtonWithColor(x, y, "Tom");
        }

        // Uppdatera spelarens status och tur
        gui.updateStatus(
                spelare1.getName() + ": Poäng " + spelare1.getPoints() + ", Besättningsmedlemmar " + spelare1.getBesattningsmedlemmar(),
                spelare2.getName() + ": Poäng " + spelare2.getPoints() + ", Besättningsmedlemmar " + spelare2.getBesattningsmedlemmar()
        );

        // Visa vems tur det är
        String nästaTur = spelaresTur ? spelare2.getName() : spelare1.getName();
        gui.updateCurrentPlayer(nästaTur);

        // Växla tur
        spelaresTur = !spelaresTur;

        // Kontrollera om spelet är slut
        if (ärSpeletSlut()) {
            avslutaSpel();
        }
    }

    /**
     * Kontrollerar om spelet är slut.
     *
     * @return true om spelet är slut, annars false.
     */
    private boolean ärSpeletSlut() {
        // Kontrollera om någon spelare saknar besättningsmedlemmar
        if (spelare1.getBesattningsmedlemmar() == 0 || spelare2.getBesattningsmedlemmar() == 0) {
            return true;
        }

        // Kontrollera om alla rutor har öppnats
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                if (spelplan.kollaPosition(x, y) != null) { // Kontrollera om det fortfarande finns något på spelplanen
                    return false;
                }
            }
        }
        return true; // Spelet är slut om alla rutor är öppnade
    }

    /**
     * Avslutar spelet och lägger till vinnaren i highscore-listan.
     */
    private void avslutaSpel() {
        Spelare vinnare = null;

        if (spelare1.getPoints() > spelare2.getPoints()) {
            vinnare = spelare1;
        } else if (spelare2.getPoints() > spelare1.getPoints()) {
            vinnare = spelare2;
        }

        if (vinnare != null) {
            highscoreList.läggTillHighscore(vinnare.getName(), vinnare.getPoints());
            gui.showMessage("Grattis " + vinnare.getName() + "! Du vann med " + vinnare.getPoints() + " poäng!", "Spelet är slut");
        } else {
            gui.showMessage("Det blev oavgjort!", "Spelet är slut");
        }

        // Fråga om nytt spel eller huvudmeny

        int choice = gui.startNewGame("Vill du starta ett nytt spel eller återgå till huvudmenyn?", "Spelet är slut");

        if (choice == 0) {
            startNewGame(); // Starta nytt spel
        } else
        {
            gui.resetGameBoard();
            gui.showStartPanel(); // Gå tillbaka till huvudmenyn
        }
        }



    /**
     * Startar ett nytt spel.
     */
    public void startNewGame() {
        // Be om nya namn för spelarna
        String player1 = gui.getPlayerName("Ange namn för Spelare 1:");
        String player2 = gui.getPlayerName("Ange namn för Spelare 2:");


        if (player1 == null || player2 == null || player1.isEmpty() || player2.isEmpty()) {
            gui.showErrorMessage("Båda spelarnamn måste anges!", "Fel");
            return; // Avbryt om namn inte är korrekt inmatade
        }

        // Sätt nya spelarnamn
        setSpelareNamn(player1, player2);

        // Skapa en ny spelplan
        this.spelplan = new Spelplan(10, 10);

        // Återställ turen
        this.spelaresTur = true;

        // Återställ GUI
        gui.resetGameBoard(); // Rensa och initiera spelplanen
        gui.updateStatus(
                player1 + ": Poäng 0, Besättningsmedlemmar 3",
                player2 + ": Poäng 0, Besättningsmedlemmar 3"
        );
        gui.updateCurrentPlayer(player1); // Visa att Spelare 1 börjar
        gui.showGamePanel(); // Växla till spelgränssnittet
    }


    /**
     * Visar highscore-listan i GUI:t.
     */
    public void showHighscores() {
        StringBuilder highscoreText = new StringBuilder("----- Highscores -----\n");
        for (Highscore highscore : highscoreList.getHighscores()) {
            highscoreText.append(highscore.getNamn())
                    .append(": ")
                    .append(highscore.getPoäng())
                    .append(" poäng\n");
        }
        gui.showMessage(highscoreText.toString(), "Highscores");
    }
}