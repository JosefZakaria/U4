package skattjakt.model;

/**
 * Klass som representerar en spelare i spelet.
 *
 * @author Mustafa
 */
public class Spelare {
    private String name;
    private int points;
    private int besattningsmedlemmar;

    /**
     * Konstruktor som initierar en spelare.
     *
     * @param name Spelarens namn.
     */
    public Spelare(String name) {
        this.name = name;
        this.points = 0; // Startpoäng
        this.besattningsmedlemmar = 3; // Antal besättningsmedlemmar
    }

    /**
     * Returnerar spelarens namn.
     *
     * @return Namnet på spelaren.
     */
    public String getName() {
        return name;
    }

    /**
     * Returnerar spelarens poäng.
     *
     * @return Antalet poäng.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Lägg till poäng för spelaren.
     *
     * @param points Att lägga till.
     */
    public void addPoints(int points) {
        this.points += points;
    }

    /**
     * Ta bort poäng från spelaren.
     *
     * @param points  Antalet poäng att ta bort.
     */
    public void decreasePoints(int points) {
        this.points -= points;
        if (this.points < 0) {
            this.points = 0; // Förhindra negativa poäng
        }
    }

    /**
     * Returnerar antal besättningsmedlemmar.
     *
     * @return Antalet besättningsmedlemmar.
     */
    public int getBesattningsmedlemmar() {
        return besattningsmedlemmar;
    }

    /**
     * Tar bort en besättningsmedlem.
     */
    public void decreaseBesattningsmedlem() {
        if (besattningsmedlemmar > 0) {
            besattningsmedlemmar--;
        }
    }
}
