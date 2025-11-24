package skattjakt.model;

import java.util.Random;

/**
 * Klass som representerar spelplanen och dess objekt.
 *
 * @author Mustafa
 */
public class Spelplan {
    private NedgravtObjekt[][] spelplan;
    private int rader;
    private int kolumner;

    /**
     * Skapar en ny spelplan med givna dimensioner.
     *
     * @param rader    Antal rader på spelplanen.
     * @param kolumner Antal kolumner på spelplanen.
     */
    public Spelplan(int rader, int kolumner) {
        this.rader = rader;
        this.kolumner = kolumner;
        this.spelplan = new NedgravtObjekt[rader][kolumner];
        initieraSpelplan();
    }

    /**
     * Initierar spelplanen med slumpmässigt placerade objekt.
     */
    private void initieraSpelplan() {
        Random random = new Random();

        // Placera skatter enligt former
        placeraSkatter();

        // Placera fällor
        for (int i = 0; i < 5; i++) {
            placeraObjekt(new Falla("poängförlust"));
        }

        // Placera överraskningar
        for (int i = 0; i < 3; i++) {
            boolean bra = random.nextBoolean();
            String typ = bra ? "extra poäng" : "poängförlust";
            placeraObjekt(new Overraskning(typ));
        }
    }

    /**
     * Slumpmässigt placerar ett objekt på spelplanen.
     *
     * @param objekt Objektet att placera.
     */
    private void placeraObjekt(NedgravtObjekt objekt) {
        Random random = new Random();
        int x, y;
        do {
            x = random.nextInt(rader);
            y = random.nextInt(kolumner);
        } while (spelplan[x][y] != null); // Hitta en tom plats

        spelplan[x][y] = objekt;
    }


    /**
     * Gräver på en viss position och returnerar objektet där.
     *
     * @param x Radens koordinat.
     * @param y Kolumnens koordinat.
     * @return Objektet på den givna positionen, eller null om det är tomt.
     */
    public NedgravtObjekt grävPåPosition(int x, int y) {
        if (x < 0 || x >= rader || y < 0 || y >= kolumner) {
            throw new IllegalArgumentException("Ogiltig position");
        }

        NedgravtObjekt objekt = spelplan[x][y];
        spelplan[x][y] = null; // Rensa platsen efter grävning
        return objekt;
    }

    /**
     * Kontrollerar objektet på en viss position utan att gräva.
     *
     * @param x Radens koordinat.
     * @param y Kolumnens koordinat.
     * @return Objektet på den givna positionen, eller null om det är tomt.
     */
    public NedgravtObjekt kollaPosition(int x, int y) {
        if (x < 0 || x >= rader || y < 0 || y >= kolumner) {
            throw new IllegalArgumentException("Ogiltig position");
        }
        return spelplan[x][y]; // Returnerar objektet om det finns, annars null
    }

    /**
     * Placera skatter enligt tillåtna former.
     */
    private void placeraSkatter() {
        Random random = new Random();

        // Lista över tillåtna former
        int[][][] former = {
                {{0, 0}, {0, 1}, {0, 2}, {1, 1}}, // T-form
                {{0, 0}, {1, 0}, {2, 0}, {1, 1}}, // Kors
                {{0, 0}, {0, 1}, {1, 0}, {1, 1}}, // Fyrkant
                {{0, 0}, {1, 0}, {1, 1}, {1, 2}}, // L-form
                {{0, 0}, {1, 0}, {2, 0}, {2, 1}}, // Omvänd L-form
                {{0, 1}, {1, 0}, {1, 1}, {1, 2}}  // Horisontellt T
        };

        // Placera 6 slumpmässiga former
        for (int i = 0; i < 6; i++) {
            boolean placerad = false;
            while (!placerad) {
                int startX = random.nextInt(rader - 2);
                int startY = random.nextInt(kolumner - 2);
                int[][] form = former[random.nextInt(former.length)];

                // Kontrollera att formen får plats
                boolean kanPlaceras = true;
                for (int[] offset : form) {
                    int x = startX + offset[0];
                    int y = startY + offset[1];
                    if (spelplan[x][y] != null) {
                        kanPlaceras = false;
                        break;
                    }
                }

                // Placera om det går
                if (kanPlaceras) {
                    for (int[] offset : form) {
                        int x = startX + offset[0];
                        int y = startY + offset[1];
                        spelplan[x][y] = new Skatt(random.nextInt(50) + 1); // Skatt med slumpmässig poäng
                    }
                    placerad = true;
                }
            }
        }
    }
}
