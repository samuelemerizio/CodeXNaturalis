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
    String nome = "";

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

    int[] ID_carteObiettivoDaScegliere = new int[2];	//Contiene ID delle 2 carte che giocatore deve scegliere all'avvio partita

    boolean fronte = true;          // Determina se è stata selezionata la carta iniziale fronte o retro durante la creazione giocatore

    boolean ob1 = true;             // Determina se è stata selezionata la carta obiettivo 1 o obiettivo 2 durante la creazione giocatore
   
    int puntiObiettivoComune1 = 0;          // Contiene il numero di punti ottenuti dal primo obiettivo comune

    int puntiObiettivoComune2 = 0;          // Contiene il numero di punti ottenuti dal secondo obiettivo comune

    int puntiObiettivoSegreto = 0;          // Contiene il numero di punti ottenuti dall'obiettivo segreto del giocatore
}
