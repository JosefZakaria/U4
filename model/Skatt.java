package skattjakt.model;

/**
 * Klass som representerar en skatt på spelplanen.
 *
 * @author Mustafa
 */
public class Skatt implements NedgravtObjekt {
    private int points;

    /**
     * Konstruktor som initierar en skatt med specifik poäng.
     *
     * @param points Poängen som skatten ger.
     */
    public Skatt(int points) {
        this.points = points;
    }

    @Override
    public void hanteraSpelare(Spelare spelare) {
        spelare.addPoints(points);
    }

    @Override
    public String beskrivning() {
        return "Skatt: " + points + " poäng";
    }
}
