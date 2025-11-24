package skattjakt.model;

import java.io.*;
import java.util.*;

/**
 * Klass som hanterar highscore-listan.
 *
 * @author Mustafa Al-Saffar
 */
public class HighscoreList {
    private List<Highscore> highscores; // Lista över highscores
    private static final String FILNAMN = "highscores.txt"; // Filens namn

    /**
     * Konstruktor som initierar highscore-listan.
     */
    public HighscoreList() {
        this.highscores = new ArrayList<>();
        läsHighscores();
    }

    /**
     * Lägger till en ny highscore och sparar listan.
     *
     * @param namn Spelarens namn.
     * @param poäng Spelarens poäng.
     */
    public void läggTillHighscore(String namn, int poäng) {
        highscores.add(new Highscore(namn, poäng));
        highscores.sort(Comparator.comparingInt(Highscore::getPoäng).reversed());
        if (highscores.size() > 10) { // Begränsa till topp 10
            highscores.remove(highscores.size() - 1);
        }
        saveHighscore();
    }

    /**
     * Returnerar highscore-listan.
     *
     * @return Listan med highscore-inlägg.
     */
    public List<Highscore> getHighscores() {
        return highscores;
    }

    /**
     * Läser highscores från en fil.
     */
    private void läsHighscores() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILNAMN))) {
            String rad;
            while ((rad = reader.readLine()) != null) {
                String[] delar = rad.split(",");
                String namn = delar[0];
                int poäng = Integer.parseInt(delar[1]);
                highscores.add(new Highscore(namn, poäng));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ingen highscore-fil hittades. Skapar ny fil.");
        } catch (IOException e) {
            System.out.println("Fel vid läsning av highscores: " + e.getMessage());
        }
    }

    /**
     * Sparar highscores till en fil.
     */
    private void saveHighscore() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILNAMN))) {
            for (Highscore highscore : highscores) {
                writer.write(highscore.getNamn() + "," + highscore.getPoäng());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Fel vid sparande av highscores: " + e.getMessage());
        }
    }
    public void addToHighscore(String namn, int poäng) {
        highscores.add(new Highscore(namn, poäng));
        highscores.sort(Comparator.comparingInt(Highscore::getPoäng).reversed());
        if (highscores.size() > 10) { // Begränsa till topp 10
            highscores.remove(highscores.size() - 1);
        }
        saveHighscore();
    }

}
