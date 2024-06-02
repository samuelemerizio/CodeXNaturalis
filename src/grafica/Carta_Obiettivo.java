package grafica;
/**
 * Rappresenta una carta obiettivo
 */
public class Carta_Obiettivo {
    
    /**
    * Identifica in modo univoco la carta in funzione del tipo
    */
    int ID;   

   /**
    * Punti generati dalla carta da moltiplicare per le occorrenze indicate da condizioneObiettivi
    */
    int valorePunti;

    /**
     * Condizioni per ottenere il moltiplicatore dei punti obiettivo
     */
    Enums.eCondizioneObiettivi condizioneObiettivi;

    int cartaDisponibile = -1;

    /**
     * Costruttore per la classe Carta_Obiettivo
     * @param ID - Identificativo unico per la carta obiettivo. Parte da 0
     * @param valorePunti - Valore in punti forniti dalla carta
     * @param condizioneObiettivi - Enums di topo eCondizioneObiettivi. Identifica l'obiettivo della carta
     * @param cartaDisponibile - Indica con -1 se la carta è disponibile. Da 0 a 3 indica il giocatore che la possiede , 5 e 6 sono le 2 carte comuni
     */
    public Carta_Obiettivo(int ID, int valorePunti, Enums.eCondizioneObiettivi condizioneObiettivi, int cartaDisponibile){
        this.ID = ID;
        this.valorePunti = valorePunti;
        this.condizioneObiettivi = condizioneObiettivi;
        this.cartaDisponibile = cartaDisponibile;
    }

}
