package grafica;
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
     * @param carta - Indica la carta_gioco presente nel manoscritto
     * @param riga - Riga riferita alla posizione nel manoscritto (0..39)
     * @param colonna - Colonna riferita alla posizione nel manoscritto (0..39)
     */
    public Cella_Manoscritto(Carta_Gioco carta, int riga, int colonna){
        this.carta = carta;
        this.riga = riga;
        this.colonna = colonna;
    }

}
