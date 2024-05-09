package componenti;
import java.util.ArrayList;
import java.util.List;

/**
 * Rappresenta la partita in corso con mazzi, giocatori e mercato
 */
public class Gioco {

    /**
     * Lista dei giocatori che partecipano alla partita.
     * Minimo 2, massimo 4 giocatori
     */
    List<Giocatore> giocatori = new ArrayList<Giocatore>();

    /**
     * Array contenente le due carte obiettivo comuni della partita
     */
    Carta_Obiettivo[] obiettiviComuni = new Carta_Obiettivo[2];

    /**
     * Mercato della partita contenente i mazzi risorsa e oro e le 4 carte disponibili
     */
    Mercato mercato = new Mercato();

    /**
     * Identifica il giocatore attuale: indice della lista giocatori
     */
    int giocatoreAttuale = 0;

    /**
     * True quando tutti i giocatori sono inizializzati e la partita è in corso
     */
    boolean giocoInCorso = false;

    /**
     * True quando un giocatore ha raggiunto i 20 punti.
     * Dopo l'ultimo giocatore della sequenza di gioco si conclude la partita
     */
    boolean ultimoTurno = false;

    /**
     * Identifica le fasi di gioco del giocatore attuale, in particolare:
     * 0 = piazzamento carta sul manoscritto.
     * 1 = rimpiazzo carta giocata dal mercato.
     * 2 = calcolo punti giocata e fine turno.
     */
    int faseDelGiocatore;
    
    public void eseguiAzione() {
    	
    }





}
