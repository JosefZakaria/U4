package skattjakt.model;

/**
 * Klass som representerar en överraskning på spelplanen.
 *
 * @author Mustafa
 */
public class Overraskning implements NedgravtObjekt {
    private String typ; // Typ av överraskning, t.ex. "extra poäng" eller "poängförlust"

    /**
     * Konstruktor som initierar en överraskning.
     *
     * @param typ Typ av överraskning ("extra poäng" eller "poängförlust").
     */
    public Overraskning(String typ) {
        this.typ = typ;
    }

    /**
     * Returnerar typen av överraskning.
     *
     * @return Typen av överraskning.
     */
    public String getTyp() {
        return typ;
    }

    /**
     * Hanterar en spelare som interagerar med överraskningen.
     *
     * @param spelare Spelaren som interagerar.
     */
    @Override
    public void hanteraSpelare(Spelare spelare) {
        if ("extra poäng".equals(typ)) {
            spelare.addPoints(10); // Ge spelaren 10 poäng
        } else if ("poängförlust".equals(typ)) {
            spelare.decreasePoints(10); // Ta bort 10 poäng från spelaren
        }
    }

    /**
     * Returnerar en beskrivning av överraskningen.
     *
     * @return En textbeskrivning av överraskningen.
     */
    @Override
    public String beskrivning() {
        return "Överraskning: " + typ;
    }
}
