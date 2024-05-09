package componenti;
import java.util.ArrayList;
import java.util.List;

/**
 * Rappresenta il manoscritto di ogni singolo giocatore.
 * Contiene la lista carte rappresentante la posizione nel manoscritto dell'oggetto carta gioco.
 * L'ordine di inserimento coincide con l'ordine nella lista.
 * Fornisce inoltre delle funzioni per ricavare la disposizione dei simboli nel manoscritto stesso
 */
public class Manoscritto {

    /**
     * Lista che rappresenta le carte giocate dal giocatore nell'ordine crescente.
     * Per ogni elemento di carte si ha la carta, la riga e la colonna in cui è posizionata nel manoscritto
     */
    List<Lista_Carte> carte = new ArrayList<Lista_Carte>();

    /**
     * Identifica il simbolo presente nella mappa degli angoli.
     * le carte giocate sul retro possono contenere fino a 3 simboli.
     * L'indice della prima dimensione identifica la colonna.
     * L'indice della seconda identifica la riga.
     * l'indice della terza identifica i 3 simboli. Se non presenti contiene NULLO.
     * Viene aggiornata mediante chiamata alla funzione generaAngoliDaCarte 
     */
    Enums.eSimbolo[][][] angoli = new Enums.eSimbolo[81][81][3];

    /**
     * Identifica il numero di carte presenti sul dato angolo.
     * Per ogni angolo possono esserci al massimo 2 carte.
     * Viene aggiornata mediante chiamata alla funzione generaAngoliDaCarte
     */
    int[][] numeroCarteSuAngolo = new int[81][81];


    public void generaAngoliDaCarte(){
        // svuoto gli array angoli e numeroCarteSuAngoli
        angoli = new Enums.eSimbolo[81][81][3];
        numeroCarteSuAngolo = new int[81][81];
    }
    
    /**
     * Questo metodo seleziona una carta presente sul manoscritto
     * @param ID ID della carta che si vuole selezionare
     */
    public void selezionaCarta(int ID) {
    	
    }

	public boolean angoloSceltoLibero() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Questo metodo mette la carta nel manoscritto
	 * @param selezione la carta che viene inserita nel manoscritto
	 */
	public void posiziona(int selezione) {
		// TODO Auto-generated method stub
		
	}

}
