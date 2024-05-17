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
    
    public Carta_Obiettivo(String[] parametri) {
		try {
			this.ID=Integer.valueOf(parametri[0]);
			this.valorePunti=Integer.valueOf(parametri[1]);
			this.condizioneObiettivi=Enums.eCondizioneObiettivi.valueOf(parametri[2]);
		}catch(Exception e) {
			e.getStackTrace();
		}
	}

}
