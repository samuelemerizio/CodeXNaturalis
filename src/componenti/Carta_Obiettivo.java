package componenti;
/**
 * Rappresenta una carta obiettivo
 */
public class Carta_Obiettivo implements Carte{
    
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

	@Override
	public void controllaCondizione() {
		// TODO Auto-generated method stub
		
	}

}
