package skattjakt.model;

/**
 * Interface som representerar ett objekt nedgrävt på spelplanen.
 *
 * @author Mustafa
 */
public interface NedgravtObjekt {
    /**
     * Hanterar en spelare som interagerar med objektet.
     *
     * @param spelare Spelaren som interagerar.
     */
    void hanteraSpelare(Spelare spelare);

    /**
     * Returnerar en beskrivning av objektet.
     *
     * @return En sträng som beskriver objektet.
     */
    String beskrivning();
}
