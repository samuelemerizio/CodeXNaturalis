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

    public Carta_Obiettivo(int ID, int valorePunti, Enums.eCondizioneObiettivi condizioneObiettivi, int cartaDisponibile){
        this.ID = ID;
        this.valorePunti = valorePunti;
        this.condizioneObiettivi = condizioneObiettivi;
        this.cartaDisponibile = cartaDisponibile;
    }

}
