package skattjakt.model;

/**
 * Klass som representerar en fälla på spelplanen.
 *
 * @author Mustafa
 */
public class Falla implements NedgravtObjekt {
    private String effekt;

    /**
     * Konstruktor som initierar en fälla med specifik effekt.
     *
     * @param effekt Effekten som fällan har på spelaren.
     */
    public Falla(String effekt) {
        this.effekt = effekt;
    }

    @Override
    public void hanteraSpelare(Spelare spelare) {
        if (effekt.equals("poängförlust")) {
            spelare.decreasePoints(5);// Ta bort poäng
            spelare.decreaseBesattningsmedlem(); // Ta bort en besättningsmedlem

        }
    }

    @Override
    public String beskrivning() {
        return "Fälla: " + effekt;
    }
}
