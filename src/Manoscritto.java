import java.util.ArrayList;
import java.util.List;

/**
 * Rappresenta il manoscritto di ogni singolo giocatore.
 * Contiene la lista carte rappresentante la posizione nel manoscritto dell'oggetto carta gioco.
 * L'ordine di inserimento coincide con l'ordine nella lista.
 * Fornisce inoltre delle funzioni per ricavare la disposizione dei simboli nel manoscritto stesso
 */
public class Manoscritto {
	private ArrayList<Integer> carteSovrapposte=new ArrayList<Integer>();
	private int hashCode;

    /**
     * Questo array contiene tutti i contatori dei simboli presenti nella partita
     * ATTENZIONE: segue posizioni Enums.eSimbolo
     * ([0]=FUNGO, [1]=FOGLIA, [2]=LUPO, [3]=FARFALLA, [4]=PIUMA, [5]=VASETTO, [6]=PERGAMENA
     */
	private int[] contatoreSimboli=new int[7];
    
    /**
     * Lista che rappresenta le carte giocate dal giocatore nell'ordine crescente.
     * Per ogni elemento di carte si ha la carta, la riga e la colonna in cui è posizionata nel manoscritto
     */

    
    

    List<Cella_Manoscritto> carte = new ArrayList<Cella_Manoscritto>();


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
        int colonna, riga;
        // svuoto gli array angoli e numeroCarteSuAngoli
        angoli = new Enums.eSimbolo[81][81][3];
        numeroCarteSuAngolo = new int[81][81];
        // Iteriamo dalla prima all'ultima carta
        for (Cella_Manoscritto cella : carte){
            colonna = cella.colonna * 2;
            riga = cella.riga * 2;
            // Posiziono i simboli negli angoli della nuova matrice
            if (cella.carta.fronte){
                angoli[colonna][riga][0] = cella.carta.simboloAngolo[Enums.eAngolo.NO.ordinal()];
                angoli[colonna + 2][riga][0] = cella.carta.simboloAngolo[Enums.eAngolo.NE.ordinal()];
                angoli[colonna + 2][riga + 2][0] = cella.carta.simboloAngolo[Enums.eAngolo.SE.ordinal()];
                angoli[colonna][riga + 2][0] = cella.carta.simboloAngolo[Enums.eAngolo.SO.ordinal()];
                angoli[colonna + 1][riga + 1][0] = Enums.eSimbolo.NULLO;
                angoli[colonna + 1][riga + 1][1] = Enums.eSimbolo.NULLO;
                angoli[colonna + 1][riga + 1][2] = Enums.eSimbolo.NULLO;
                // Incremento il numero di carte sull'angolo
                numeroCarteSuAngolo[colonna][riga]++;
                numeroCarteSuAngolo[colonna + 2][riga]++;
                numeroCarteSuAngolo[colonna + 2][riga + 2]++;
                numeroCarteSuAngolo[colonna][riga + 2]++;
                numeroCarteSuAngolo[colonna +1][riga + 1]++;
            }
            else
            {
                if (cella.carta.tipo == Enums.eTipoCarta.INIZIALE){
                    angoli[colonna][riga][0] = cella.carta.simboloAngolo[Enums.eAngolo.NO.ordinal()];
                    angoli[colonna + 2][riga][0] = cella.carta.simboloAngolo[Enums.eAngolo.NE.ordinal()];
                    angoli[colonna + 2][riga + 2][0] = cella.carta.simboloAngolo[Enums.eAngolo.SE.ordinal()];
                    angoli[colonna][riga + 2][0] = cella.carta.simboloAngolo[Enums.eAngolo.SO.ordinal()];
                    angoli[colonna + 1][riga + 1][0] = cella.carta.simboloRetro[0];
                    angoli[colonna + 1][riga + 1][1] = cella.carta.simboloRetro[1];
                    angoli[colonna + 1][riga + 1][2] = cella.carta.simboloRetro[2];
                }
                else
                {
                    angoli[colonna][riga][0] = Enums.eSimbolo.NULLO;
                    angoli[colonna + 2][riga][0] = Enums.eSimbolo.NULLO;
                    angoli[colonna + 2][riga + 2][0] = Enums.eSimbolo.NULLO;
                    angoli[colonna][riga + 2][0] = Enums.eSimbolo.NULLO;
                    angoli[colonna + 1][riga + 1][0] = cella.carta.simboloRetro[0];
                    angoli[colonna + 1][riga + 1][1] = cella.carta.simboloRetro[1];
                    angoli[colonna + 1][riga + 1][2] = cella.carta.simboloRetro[2];
                }
                // Incremento il numero di carte sull'angolo
                numeroCarteSuAngolo[colonna][riga]++;
                numeroCarteSuAngolo[colonna + 2][riga]++;
                numeroCarteSuAngolo[colonna + 2][riga + 2]++;
                numeroCarteSuAngolo[colonna][riga + 2]++;
                numeroCarteSuAngolo[colonna +1][riga + 1]++;
            }
        }

    }
    
    /**
     * Questo metodo seleziona una carta presente sul manoscritto
     * @param ID ID della carta che si vuole selezionare
     * @return hashCode posizione della carta doppia nella lista
     */
    public int selezionaCarta(int ID) {
    	
		return hashCode;
    }

	public boolean angoloSceltoLibero() {
		return false;
	}

	/**
	 * Questo metodo mette la carta nel manoscritto
	 * @param selezione la carta che viene inserita nel manoscritto
	 */
	public void posiziona(int selezione) {
		
	}
	
	public ArrayList<Integer> listaCarteSovrapposte() {
		for(int i=0;i<carte.size();i++) {
			for(int j=1;j<carte.size();j++) {
				if((carte.get(i).riga==carte.get(j).riga)&&(carte.get(i).colonna==carte.get(j).colonna)) {
					//CARTE SOVRAPPOSTE
					carteSovrapposte.add(carte.get(i).carta.ID); //aggiungo solo la carta che sta sotto a quella sovrapposta
					
				}
			}
		}
		return carteSovrapposte;
	}

     /** Conteggia il numero di simboli richiesti presenti nel manoscritto
     * @param simbolo Simbolo da conteggiare
     * @return Totale simboli trovati
     */
    public int contaSimboliManoscritto(Enums.eSimbolo simbolo){ 
        int conta = 0;
        for (int r = 0; r < 81; r++)
            for (int c = 0; c < 81; c++){
                if (angoli[c][r][0] != null)
                {
                    if (angoli[c][r][0] == simbolo) 
                        conta++;
                    if (angoli[c][r][1] == simbolo) 
                        conta++;
                    if (angoli[c][r][2] == simbolo) 
                        conta++;

                }
            }
        return conta;
    }

    /**
     * Conta i punti ottenuti dal posizionamento di una carta
     * @param cGG cCarta posizionata
     * @return
     */ 
    public int contaPunti(Carta_Gioco cGG){
        // Se la carta non da punti restituisco 0
        if (cGG.valorePunti <= 0)
            return 0;
        
        // Aggiorno la matrice dei simboli sugli angoli 
        generaAngoliDaCarte();

        // Verifico le condizioni di calcolo analizzando il moltiplicatore
        switch (cGG.moltiplicatorePunti) {
            case NULLO:     // Restituisce il numero di punti senza nessun moltiplicatore
                return cGG.valorePunti;
            case PIUMA:     // Restituisce valorePunti moltiplicato per il numero di simboli PIUMA presenti nel manoscritto
                return cGG.valorePunti * contaSimboliManoscritto(Enums.eSimbolo.PIUMA);
            case VASETTO:     // Restituisce valorePunti moltiplicato per il numero di simboli PIUMA presenti nel manoscritto
                return cGG.valorePunti * contaSimboliManoscritto(Enums.eSimbolo.VASETTO);
            case PERGAMENA:     // Restituisce valorePunti moltiplicato per il numero di simboli PIUMA presenti nel manoscritto
                return cGG.valorePunti * contaSimboliManoscritto(Enums.eSimbolo.PERGAMENA);
            case ANGOLO:     // Restituisce valorePunti moltiplicato per il numero di simboli PIUMA presenti nel manoscritto
                return cGG.valorePunti * contaAngoliCopertiDaCarta(cGG);
            default:
                return 0;
        }

    }

    /**
     * Conta quante carte sono presenti su una data carta
     * @param cGG Carta gioco su cui effettuare il controllo, ogni angolo coperto incrementa di uno il totale
     * @return Totale dei punti effettuati (ogni angolo coperto assegna un punto)
     */
    public int contaAngoliCopertiDaCarta(Carta_Gioco cGG){
        int riga = carte.get(carte.size() - 1).riga;
        int colonna = carte.get(carte.size() - 1).colonna;
        riga *= 2;
        colonna *= 2;
        int totale = 0;
        if (numeroCarteSuAngolo[colonna][riga] > 1) totale++;
        if (numeroCarteSuAngolo[colonna +2][riga] > 1) totale++;
        if (numeroCarteSuAngolo[colonna + 2][riga + 2] > 1) totale++;
        if (numeroCarteSuAngolo[colonna][riga + 2] > 1) totale++;

        return totale;

    }

    public int contaPuntiObiettivo(Carta_Obiettivo cO){
        int quantitaSimboliTrovati;             // Numero di simboli trovati nel manoscritto
        int quantitaFormeTrovate;               // Numero di forme (diagonali, cavallo) trovate nel manoscritto
        int puntiObiettivo; 

        // Aggiorno la disposizione degli angoli
        generaAngoliDaCarte();
        puntiObiettivo = cO.valorePunti;

        // Verifico obiettivo raggiunto
        switch (cO.condizioneObiettivi) {
            case ORO_2_PIUME:       // Devo conteggiare il numero delle piume. Ogni due moltiplico il punteggio
                quantitaSimboliTrovati = contaSimboliManoscritto(Enums.eSimbolo.PIUMA);
                quantitaSimboliTrovati /= 2;
                return puntiObiettivo * quantitaSimboliTrovati;
            case ORO_2_VASETTI:       // Devo conteggiare il numero dei vasetti. Ogni due moltiplico il punteggio
                quantitaSimboliTrovati = contaSimboliManoscritto(Enums.eSimbolo.VASETTO);
                quantitaSimboliTrovati /= 2;
                return puntiObiettivo * quantitaSimboliTrovati;
            case ORO_2_PERGAMENE:       // Devo conteggiare il numero delle pergamene. Ogni due moltiplico il punteggio
                quantitaSimboliTrovati = contaSimboliManoscritto(Enums.eSimbolo.PERGAMENA);
                quantitaSimboliTrovati /= 2;
                return puntiObiettivo * quantitaSimboliTrovati;
            case ORO_3_SIMBOLI:         // Devo conteggiare il numero dei tre simboli indicati
                int[] quantita = new int[3];
                quantita[0] = contaSimboliManoscritto(Enums.eSimbolo.PIUMA);
                quantita[1] = contaSimboliManoscritto(Enums.eSimbolo.VASETTO);
                quantita[2] = contaSimboliManoscritto(Enums.eSimbolo.PERGAMENA);
                quantitaSimboliTrovati = quantita[0];
                if (quantita[1] < quantitaSimboliTrovati) quantitaSimboliTrovati = quantita[1];
                if (quantita[2] < quantitaSimboliTrovati) quantitaSimboliTrovati = quantita[2];
                return puntiObiettivo * quantitaSimboliTrovati;
            case ROSSO_FUNGHI:       // Devo conteggiare il numero dei funghi. Ogni tre moltiplico il punteggio
                quantitaSimboliTrovati = contaSimboliManoscritto(Enums.eSimbolo.FUNGO);
                quantitaSimboliTrovati /= 3;
                return puntiObiettivo * quantitaSimboliTrovati;
            case VERDE_FOGLIE:       // Devo conteggiare il numero delle foglie. Ogni tre moltiplico il punteggio
                quantitaSimboliTrovati = contaSimboliManoscritto(Enums.eSimbolo.FOGLIA);
                quantitaSimboliTrovati /= 3;
                return puntiObiettivo * quantitaSimboliTrovati;
            case BLU_LUPO:          // Devo conteggiare il numero dei lupi. Ogni tre moltiplico il punteggio
                quantitaSimboliTrovati = contaSimboliManoscritto(Enums.eSimbolo.LUPO);
                quantitaSimboliTrovati /= 3;
                return puntiObiettivo * quantitaSimboliTrovati;
            case VIOLA_FARFALLA:       // Devo conteggiare il numero delle farfalle. Ogni tre moltiplico il punteggio
                quantitaSimboliTrovati = contaSimboliManoscritto(Enums.eSimbolo.FARFALLA);
                quantitaSimboliTrovati /= 3;
                return puntiObiettivo * quantitaSimboliTrovati;
            case ROSSO_DIAGONALE:       // Devo conteggiare le diagonali presenti
                quantitaFormeTrovate = contaPuntiDiagonali(Enums.eSimbolo.FUNGO, Enums.eAngolo.SO);
                return puntiObiettivo * quantitaFormeTrovate;
            case VERDE_DIAGONALE:       // Devo conteggiare le diagonali presenti
                quantitaFormeTrovate = contaPuntiDiagonali(Enums.eSimbolo.FOGLIA, Enums.eAngolo.SE);
                return puntiObiettivo * quantitaFormeTrovate;
            case BLU_DIAGONALE:       // Devo conteggiare le diagonali presenti
                quantitaFormeTrovate = contaPuntiDiagonali(Enums.eSimbolo.LUPO, Enums.eAngolo.SO);
                return puntiObiettivo * quantitaFormeTrovate;
            case VIOLA_DIAGONALE:       // Devo conteggiare le diagonali presenti
                quantitaFormeTrovate = contaPuntiDiagonali(Enums.eSimbolo.FARFALLA, Enums.eAngolo.SE);
                return puntiObiettivo * quantitaFormeTrovate;
            case ROSSO_CAVALLO:         // Devo conteggiare i cavalli rossi
                quantitaFormeTrovate = contaPuntiCavalli(Enums.eSimbolo.FOGLIA, Enums.eSimbolo.FUNGO, Enums.eAngolo.SE);
                return puntiObiettivo * quantitaFormeTrovate;
            case VERDE_CAVALLO:         // Devo conteggiare i cavalli verdi
                quantitaFormeTrovate = contaPuntiCavalli(Enums.eSimbolo.FARFALLA, Enums.eSimbolo.FOGLIA, Enums.eAngolo.SO);
                return puntiObiettivo * quantitaFormeTrovate;
            case BLU_CAVALLO:         // Devo conteggiare i cavalli blu
                quantitaFormeTrovate = contaPuntiCavalli(Enums.eSimbolo.FUNGO, Enums.eSimbolo.LUPO, Enums.eAngolo.NE);
                return puntiObiettivo * quantitaFormeTrovate;
            case VIOLA_CAVALLO:         // Devo conteggiare i cavalli viola
                quantitaFormeTrovate = contaPuntiCavalli(Enums.eSimbolo.LUPO, Enums.eSimbolo.FARFALLA, Enums.eAngolo.NO);
                return puntiObiettivo * quantitaFormeTrovate;
            default:
                return 0;
        }

    }

    public int contaPuntiDiagonali(Enums.eSimbolo simbolo, Enums.eAngolo angolo){
        int totale = 0;
        // Ottengo la mappa dei colori carte del manoscritto
        Enums.eSimbolo[][] mappa = generaMappaColoriCarte();
        // itero la mappa dei colori carte del manoscritto
        for (int riga = 0; riga < 40 - 2; riga++){
            for (int colonna = 0; colonna < 40; colonna++){
                if (mappa[colonna][riga] == simbolo)
                    switch (angolo) {
                        case SE:
                            // Se ho trovato il simbolo su una colonna superiore a 37 sicuramente non sta nella mappa
                            if (colonna > 37) break;
                            // Verifico che le due laterali basse a destra siano con lo stesso simbolo
                            if (mappa[colonna + 1][riga + 1] == simbolo)
                                if (mappa[colonna + 2][riga + 2] == simbolo)
                                {
                                    totale++;
                                    // Annullo i simboli delle tre carte (non possono più essere usate per completare un altro obiettivo)
                                    mappa[colonna][riga] = Enums.eSimbolo.VUOTO;
                                    mappa[colonna + 1][riga + 1] = Enums.eSimbolo.VUOTO;
                                    mappa[colonna + 2][riga + 2] = Enums.eSimbolo.VUOTO;
                                } 
                                else 
                                {
                                    // Annullo i simboli se ho le prime due carte del simbolo ricercato e non la terza
                                    // perchè non potendo scambiare altre carte non soddisferanno mai la condizione dell'obiettivo
                                    mappa[colonna][riga] = Enums.eSimbolo.VUOTO;
                                    mappa[colonna + 1][riga + 1] = Enums.eSimbolo.VUOTO;
                                }
                            else
                                // Annullo il simbolo se ho la prima carta del simbolo ricercato e non le due seguenti
                                // perchè non potendo scambiare altre carte non soddisferanno mai la condizione dell'obiettivo
                                mappa[colonna][riga] = Enums.eSimbolo.VUOTO;
                            break;
                        case SO:
                            // Se ho trovato il simbolo su una colonna inferiore a 2 sicuramente non sta nella mappa
                            if (colonna < 2) break;
                            // Verifico che le due laterali basse a sinistra siano con lo stesso simbolo
                            if (mappa[colonna - 1][riga + 1] == simbolo)
                                if (mappa[colonna - 2][riga + 2] == simbolo)
                                {
                                    totale++;
                                    // Annullo i simboli delle tre carte (non possono più essere usate per completare un altro obiettivo)
                                    mappa[colonna][riga] = Enums.eSimbolo.VUOTO;
                                    mappa[colonna - 1][riga + 1] = Enums.eSimbolo.VUOTO;
                                    mappa[colonna - 2][riga + 2] = Enums.eSimbolo.VUOTO;
                                } 
                                else 
                                {
                                    // Annullo i simboli se ho le prime due carte del simbolo ricercato e non la terza
                                    // perchè non potendo scambiare altre carte non soddisferanno mai la condizione dell'obiettivo
                                    mappa[colonna][riga] = Enums.eSimbolo.VUOTO;
                                    mappa[colonna - 1][riga + 1] = Enums.eSimbolo.VUOTO;
                                }
                            else
                                // Annullo il simbolo se ho la prima carta del simbolo ricercato e non le due seguenti
                                // perchè non potendo scambiare altre carte non soddisferanno mai la condizione dell'obiettivo
                                mappa[colonna][riga] = Enums.eSimbolo.VUOTO;
                            break;
                        default:
                            break;
                    }
            }
        }
        return totale;

    }


    public int contaPuntiCavalli(Enums.eSimbolo simboloSingolo, Enums.eSimbolo simboloDoppio, Enums.eAngolo direzioneSimboloSingolo){
        int totale = 0;
        // Ottengo la mappa dei colori carte del manoscritto
        Enums.eSimbolo[][] mappa = generaMappaColoriCarte();
        // itero la mappa dei colori carte del manoscritto
        for (int riga = 0; riga < 40; riga++){
            for (int colonna = 0; colonna < 40; colonna++){
                if (mappa[colonna][riga] == simboloSingolo)
                    switch (direzioneSimboloSingolo) {
                        case NO:
                            // Se ho trovato il simbolo su una colonna superiore a 38 sicuramente non sta nella mappa
                            if (colonna > 38) break;
                            // Se ho trovato il simbolo su una riga superiore a 36 sicuramente non sta nella mappa
                            if (riga > 36) break;
                            // Verifico che le due laterali basse a destra siano con simboloDoppio
                            if (mappa[colonna + 1][riga + 1] == simboloDoppio)
                                if (mappa[colonna + 1][riga + 3] == simboloDoppio)
                                {
                                    totale++;
                                    // Annullo i simboli delle tre carte (non possono più essere usate per completare un altro obiettivo)
                                    mappa[colonna][riga] = Enums.eSimbolo.VUOTO;
                                    mappa[colonna + 1][riga + 1] = Enums.eSimbolo.VUOTO;
                                    mappa[colonna + 1][riga + 3] = Enums.eSimbolo.VUOTO;
                                } 
                            else
                                // Annullo il simbolo se ho la prima carta del simbolo ricercato e non le due seguenti
                                // perchè non potendo scambiare altre carte non soddisferanno mai la condizione dell'obiettivo
                                mappa[colonna][riga] = Enums.eSimbolo.VUOTO;
                            break;
                        case NE:
                            // Se ho trovato il simbolo su una colonna superiore a 38 sicuramente non sta nella mappa
                            if (colonna < 1) break;
                            // Se ho trovato il simbolo su una riga superiore a 36 sicuramente non sta nella mappa
                            if (riga > 36) break;
                            // Verifico che le due laterali basse a destra siano con simboloDoppio
                            if (mappa[colonna - 1][riga + 1] == simboloDoppio)
                                if (mappa[colonna - 1][riga + 3] == simboloDoppio)
                                {
                                    totale++;
                                    // Annullo i simboli delle tre carte (non possono più essere usate per completare un altro obiettivo)
                                    mappa[colonna][riga] = Enums.eSimbolo.VUOTO;
                                    mappa[colonna - 1][riga + 1] = Enums.eSimbolo.VUOTO;
                                    mappa[colonna - 1][riga + 3] = Enums.eSimbolo.VUOTO;
                                } 
                            else
                                // Annullo il simbolo se ho la prima carta del simbolo ricercato e non le due seguenti
                                // perchè non potendo scambiare altre carte non soddisferanno mai la condizione dell'obiettivo
                                mappa[colonna][riga] = Enums.eSimbolo.VUOTO;
                            break;
                        case SE:
                            // Se ho trovato il simbolo su una colonna superiore a 38 sicuramente non sta nella mappa
                            if (colonna < 1) break;
                            // Se ho trovato il simbolo su una riga superiore a 36 sicuramente non sta nella mappa
                            if (riga < 3) break;
                            // Verifico che le due laterali basse a destra siano con simboloDoppio
                            if (mappa[colonna - 1][riga - 1] == simboloDoppio)
                                if (mappa[colonna - 1][riga - 3] == simboloDoppio)
                                {
                                    totale++;
                                    // Annullo i simboli delle tre carte (non possono più essere usate per completare un altro obiettivo)
                                    mappa[colonna][riga] = Enums.eSimbolo.VUOTO;
                                    mappa[colonna - 1][riga - 1] = Enums.eSimbolo.VUOTO;
                                    mappa[colonna - 1][riga - 3] = Enums.eSimbolo.VUOTO;
                                } 
                            else
                                // Annullo il simbolo se ho la prima carta del simbolo ricercato e non le due seguenti
                                // perchè non potendo scambiare altre carte non soddisferanno mai la condizione dell'obiettivo
                                mappa[colonna][riga] = Enums.eSimbolo.VUOTO;
                            break;
                        case SO:
                            // Se ho trovato il simbolo su una colonna superiore a 38 sicuramente non sta nella mappa
                            if (colonna > 38) break;
                            // Se ho trovato il simbolo su una riga superiore a 36 sicuramente non sta nella mappa
                            if (riga < 3) break;
                            // Verifico che le due laterali basse a destra siano con simboloDoppio
                            if (mappa[colonna + 1][riga - 1] == simboloDoppio)
                                if (mappa[colonna + 1][riga - 3] == simboloDoppio)
                                {
                                    totale++;
                                    // Annullo i simboli delle tre carte (non possono più essere usate per completare un altro obiettivo)
                                    mappa[colonna][riga] = Enums.eSimbolo.VUOTO;
                                    mappa[colonna + 1][riga - 1] = Enums.eSimbolo.VUOTO;
                                    mappa[colonna + 1][riga - 3] = Enums.eSimbolo.VUOTO;
                                } 
                            else
                                // Annullo il simbolo se ho la prima carta del simbolo ricercato e non le due seguenti
                                // perchè non potendo scambiare altre carte non soddisferanno mai la condizione dell'obiettivo
                                mappa[colonna][riga] = Enums.eSimbolo.VUOTO;
                            break;

                        default:
                            break;
                    }
            }
        }
        return totale;

    }


    /**
     * Genera una matrice 40 x 40 con i colori delle carte
     * @return
     */
    public Enums.eSimbolo[][] generaMappaColoriCarte(){
        Enums.eSimbolo[][] mappa = new Enums.eSimbolo[40][40];
        for (Cella_Manoscritto cella : carte){
            if (cella.carta.tipo != Enums.eTipoCarta.INIZIALE){
                mappa[cella.colonna][cella.riga] = cella.carta.simboloRetro[0];
            }
        }

        return mappa;
    }

    public int[] initContaSimboli() {
    	for(int i=0; i<contatoreSimboli.length; i++) {
    		//contatoreSimboli[i]=Enums.eSimbolo.NULLO; 
    		contatoreSimboli[i]=0;
    	}
    	return contatoreSimboli;
    }
    
    /**
     * 
     * @param carte lista delle carte piazzate nel manoscritto
     * @return contatoreSimboli: array contenente il conteggio dei simboli piazzati (vedi dichiarazione attributo
     * per info sulla posizione di ogni simbolo nell'array
     */
    public int[] contaSimboliPiazzati(List<Lista_Carte> carte) {
    	for(int i=0;i<carte.size();i++) {
    		if(carteSovrapposte.contains(carte.get(i))) { //la carta i che considero è sovrapposta da un'altra carta
    			//faccio tre cicli for per passare ogni riga/colonna/simbolo possibile presente nella carta
    			for(int r=0;r<81;r++) {
    				for(int c=0;c<81;c++) {
    					for(int l=0;l<3;l++) {
    						if(angoli[r][c][l].equals(Enums.eSimbolo.FUNGO)) {
    							contatoreSimboli[0]--;
    						}else if(angoli[r][c][l].equals(Enums.eSimbolo.FOGLIA)){
    	           				contatoreSimboli[1]--;
                				
    	           			}else if(angoli[r][c][l].equals(Enums.eSimbolo.LUPO)){
    	           				contatoreSimboli[2]--;
    	            				
    	           			}else if(angoli[r][c][l].equals(Enums.eSimbolo.FARFALLA)){
    	           				contatoreSimboli[3]--;
    	           				
    	           			}else if(angoli[r][c][l].equals(Enums.eSimbolo.PIUMA)){
    	            			contatoreSimboli[4]--;
    	            				
    	            		}else if(angoli[r][c][l].equals(Enums.eSimbolo.VASETTO)){
    	            			contatoreSimboli[5]--;
    	            				
    	            		}else if(angoli[r][c][l].equals(Enums.eSimbolo.PERGAMENA)){
    	           				contatoreSimboli[6]--;
    	            				
    	          			}
    					}
    					
    				}
    			}
    		}
    		if(carte.get(i).carta.fronte==true) {
        		for(int j=0;j<carte.get(i).carta.simboloAngolo.length;j++) {
            			
           			if(carte.get(i).carta.simboloAngolo[j].equals(Enums.eSimbolo.FUNGO)){
           				contatoreSimboli[0]+=1;
           				
           			}else if(carte.get(i).carta.simboloAngolo[j].equals(Enums.eSimbolo.FOGLIA)){
           				contatoreSimboli[1]+=1;
            				
           			}else if(carte.get(i).carta.simboloAngolo[j].equals(Enums.eSimbolo.LUPO)){
           				contatoreSimboli[2]+=1;
            				
           			}else if(carte.get(i).carta.simboloAngolo[j].equals(Enums.eSimbolo.FARFALLA)){
           				contatoreSimboli[3]+=1;
           				
           			}else if(carte.get(i).carta.simboloAngolo[j].equals(Enums.eSimbolo.PIUMA)){
            			contatoreSimboli[4]+=1;
            				
            		}else if(carte.get(i).carta.simboloAngolo[j].equals(Enums.eSimbolo.VASETTO)){
            			contatoreSimboli[5]+=1;
            				
            		}else if(carte.get(i).carta.simboloAngolo[j].equals(Enums.eSimbolo.PERGAMENA)){
           				contatoreSimboli[6]+=1;
            				
          			}
            	}
        	}else {
        		//LA CARTA è GIOCATA SUL RETRO
        		for(int j=0;j<carte.get(i).carta.simboloRetro.length;j++) {
        			if(carte.get(i).carta.simboloRetro[j].equals(Enums.eSimbolo.FUNGO)){
           				contatoreSimboli[0]+=1;
            				
           			}else if(carte.get(i).carta.simboloRetro[j].equals(Enums.eSimbolo.FOGLIA)){
           				contatoreSimboli[1]+=1;
            				
           			}else if(carte.get(i).carta.simboloRetro[j].equals(Enums.eSimbolo.LUPO)){
           				contatoreSimboli[2]+=1;
            				
           			}else if(carte.get(i).carta.simboloRetro[j].equals(Enums.eSimbolo.FARFALLA)){
           				contatoreSimboli[3]+=1;
           				
           			}else if(carte.get(i).carta.simboloRetro[j].equals(Enums.eSimbolo.PIUMA)){
           				contatoreSimboli[4]+=1;
            				
           			}else if(carte.get(i).carta.simboloRetro[j].equals(Enums.eSimbolo.VASETTO)){
           				contatoreSimboli[5]+=1;
            				
            		}else if(carte.get(i).carta.simboloRetro[j].equals(Enums.eSimbolo.PERGAMENA)){
            			contatoreSimboli[6]+=1;
            				
            		}
        		}
       		}
    	

    	}
    	
    	System.out.println("Fungo: "+contatoreSimboli[0]+"\nFoglia: "+contatoreSimboli[1]+"\nLupo: "+contatoreSimboli[2]+
    			"\nFarfalla: "+contatoreSimboli[3]+ "\nPiuma: "+contatoreSimboli[4]+"\nVasetto: "+contatoreSimboli[5]+
    			"\nPergamena: "+contatoreSimboli[6]);
    	
		return contatoreSimboli;
    }
}


