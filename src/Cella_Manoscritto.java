import java.awt.Rectangle;

/**
 * Rappresenta una carta gioco nel manoscritto includendo la riga e la colonna su cui è posizionata
 */
public class Cella_Manoscritto {
    Carta_Gioco carta;
    int riga;
    int colonna;
    Rectangle rectCarta = new Rectangle(0, 0, 10, 10);

    /**
     * Costruttore di Lista_Carte
     * @param carta 
     * @param riga
     * @param colonna
     */
    public Cella_Manoscritto(Carta_Gioco carta, int riga, int colonna){
        this.carta = carta;
        this.riga = riga;
        this.colonna = colonna;
    }

}
