/**
 * Rappresenta una carta gioco nel manoscritto includendo la riga e la colonna su cui è posizionata
 */
public class Lista_Carte {
	
    Carta_Gioco carta;
    int riga;
    int colonna;

    /**
     * Costruttore di Lista_Carte
     * @param carta
     * @param riga
     * @param colonna
     */
    
    public Lista_Carte(Carta_Gioco carta, int riga, int colonna){
        this.carta = carta;
        this.riga = riga;
        this.colonna = colonna;
    }
    

}
