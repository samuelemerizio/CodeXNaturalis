/**
 * Rappresenta il giocatore con le sue proprietà, il suo manoscritto ed il suo obiettivo
 */
public class Giocatore {
    /**
     * Identifica in modo univoco il giocatore
     */
    int ID;

    /**
     * Nome del giocatore
     */
    String nome;

    /**
     * Colore pedina scelta dal giocatore
     */
    Enums.eColoreGiocatore colore;

    /**
     * Punteggio del giocatore
     */
    int punteggio;

    /**
     * Identifica la carta iniziale scelta dal giocatore
     */
    Carta_Gioco cartaIniziale;

    /**
     * Carta obiettivo segreto del giocatore
     */
    Carta_Obiettivo cartaObiettivo;

    /**
     * Identifica le 3 carte gioco che il giocatore ha in mano
     */
    Carta_Gioco[] carteInMano = new Carta_Gioco[3];

    /**
     * Oggetto rappresentante il tabellone di gioco del giocatore
     */
    Manoscritto manoscritto = new Manoscritto();

}
