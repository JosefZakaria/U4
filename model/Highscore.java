package skattjakt.model;

/**
 * Klass som representerar ett highscore-inlägg.
 *
 * @author Mustafa Al-Saffar
 */
public class Highscore {
    private String namn;
    private int poäng;

    /**
     * Konstruktor som skapar ett nytt highscore-inlägg.
     *
     * @param namn Spelarens namn.
     * @param poäng Spelarens poäng.
     */
    public Highscore(String namn, int poäng) {
        this.namn = namn;
        this.poäng = poäng;
    }

    /**
     * Returnerar spelarens namn.
     *
     * @return Spelarens namn.
     */
    public String getNamn() {
        return namn;
    }

    /**
     * Returnerar spelarens poäng.
     *
     * @return Spelarens poäng.
     */
    public int getPoäng() {
        return poäng;
    }

    /**
     * Returnerar en textrepresentation av highscore-inlägget.
     *
     * @return Textrepresentation av inlägget.
     */
    @Override
    public String toString() {
        return namn + ": " + poäng + " poäng";
    }
}
