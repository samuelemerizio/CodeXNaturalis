
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.awt.Point;

/**
 * Rappresenta la partita in corso con mazzi, giocatori e mercato
 */
public class Gioco {
	
	Giocatore g=new Giocatore();
	private Boolean primoTurno=true;
	private static int contatoreTurni=0;
	private Funzioni f=new Funzioni();
	private List<Carta_Gioco> mazzoCarteOro=new ArrayList<Carta_Gioco>();
	private List<Carta_Gioco> mazzoCarteRisorsa=new ArrayList<Carta_Gioco>();
	private List<Carta_Gioco> mazzoCarteIniziali=new ArrayList<Carta_Gioco>();
	private Carta_Obiettivo[] mazzoCarteObiettivo = new Carta_Obiettivo[16];

    /**
     * Lista dei giocatori che partecipano alla partita.
     * Minimo 2, massimo 4 giocatori
     */
    List<Giocatore> giocatori = new ArrayList<Giocatore>();

    /**
     * Array contenente le due carte obiettivo comuni della partita
     */
    int[] obiettiviComuni = new int[2];

    /**
     * Mercato della partita contenente i mazzi risorsa e oro e le 4 carte disponibili
     */
    Mercato mercato = new Mercato();

    /**
     * Identifica il giocatore attuale: indice della lista giocatori
     */
    static int giocatoreAttuale = 0;

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

    //List <Carta_Gioco> mazzoCarteIniziali = new ArrayList<Carta_Gioco>();

    int indiceCartaGiocoScelta = 0;				//Identifica la carta 1,2 o 3 scelta dal giocatore
    Enums.eAngolo angoloCartaMercatoScelta;			//Identifica quale angolo della carta scelta dal giocatore verrà utilizzato per aggancio
    int indiceCartaManoscrittoScelta;				//Identifica l'indice della carta manoscritto scelta dal giocatore
    Enums.eAngolo angoloCartaManoscrittoScelta;		//Identifica quale angolo della carta del manoscritto è stata scelta
    boolean selezioneCarteGiocoRetroValida = false;		//Quanto a TRUE è possibile visualizzare il pulsante per effettuare giocata carta RETRO
    boolean selezioneCarteGiocoFronteValida = false; //Quanto a TRUE è possibile visualizzare il pulsante per effettuare giocata carta FRONTE
    boolean selezioneCartaRimpiazzoValida = false;	//TRUE se la carta selezionata dal mercato per il rimpiazzo è valida
    int indiceCartaMercatoRimpiazzo = -1;			//Carta selezionata dal mercato per essere caricata al giocatore, mazzo, o una delle 2 del mercato
    boolean CartaMercatoRimpiazzoTipo = false;		//TRUE ho selezionato una carta risorsa, FALSE carta oro
    int indiceCartaGiocatoreDaRimpiazzare = -1;		//Carta da rimpiazzare al giocatore in fase 1
    int punteggioTurno = 0;

    List <Funzioni.risultati> classifica = new ArrayList<>();       // Lista in cui sarà contenuta la classifica

    
    public Gioco(String nome) {
    	
    }
    /**
     * Costruttore della classe Gioco.
     * Inizializza tutti i mazzi di carte oro, risorsa, iniziali e obiettivo 
     */
    public Gioco(){
        Init_Carte();
    }
    
    public String getNome(Giocatore g) {
    	return g.nome;
    }

    /**
     * Funzione di inizializzazone dei mazzi di carte
     */
    public void Init_Carte(){
        // Inizializzo i mazzi di carte leggendoli dai vari file
        initCarteIniziali();
        initCarteOro();
        initCarteRisorse();
        initCarteObiettivi();

        // Creo i mazzi Oro e Risorsa da mettere nel mercato
        mercato.mescolaMazziCarteRisorseOro(Variabili.mazzoCarteRisorsaOriginali, Variabili.mazzoCarteOroOriginali);

        setMazzoCarteIniziali(Variabili.mazzoCarteInizialiOriginali);

        obiettiviComuni[0] = obiettivoAssegna(5);
        obiettiviComuni[1] = obiettivoAssegna(6);
        mercato.carteRisorsa[0] = pescaCartaGioco(mercato.mazzoCarteRisorse);
        mercato.carteRisorsa[1] = pescaCartaGioco(mercato.mazzoCarteRisorse);
        mercato.carteOro[0] = pescaCartaGioco(mercato.mazzoCarteOro);
        mercato.carteOro[1] = pescaCartaGioco(mercato.mazzoCarteOro);

        ultimoTurno = false;
    }

    // Assegna ad ogni giocatore le carte gioco
    public void assegnaCarteGioco(){
        // Aggiungo ad ogni utente le sue carte gioco
        //for (int i = 0; i < Variabili.partita.giocatori.size(); i++){
        for (int i = 0; i < giocatori.size(); i++){
            giocatori.get(i).carteInMano[0] = pescaCartaGioco(mercato.mazzoCarteRisorse);
            giocatori.get(i).carteInMano[1] = pescaCartaGioco(mercato.mazzoCarteRisorse);
            giocatori.get(i).carteInMano[2] = pescaCartaGioco(mercato.mazzoCarteOro);
            // Memorizzo nella variabile cella la carta iniziale dell'i-esimo giocatore e la posizione in cui verrà posta
            //Cella_Manoscritto cella=new Cella_Manoscritto(giocatori.get(i).manoscritto.carte.get(i).carta, 20,20);
            // Posiziono la carta iniziale del giocatore i-esimo
            //giocatori.get(i).manoscritto.carte.add(cella);
        }
		
    }

    /**
     * Inizializza le carte risorsa
     */
    private void initCarteRisorse(){
        InputStream in = ClassLoader.getSystemResourceAsStream("assets/CarteRisorse.csv");
        Variabili.mazzoCarteRisorsaOriginali = new ArrayList<Carta_Gioco>();
        // Eccezione per la lettura dei file delle carte risorsa
    	try (BufferedReader br = new BufferedReader(new InputStreamReader(in))){	
    		String linea;
    		int i = 0;
            // ciclo per la lettura del file CarteRisorse.csv (leggi finchè la riga non è null)
    		while ((linea = br.readLine()) != null) {
                // la linea i=0 contiene l'esplicazione dei valori inseriti nel file (non è da leggere)
    			if (i!= 0) {
	    			String[] valori = linea.split(";");
                    // Aggiunge la carta al mazzo originale
	    			Variabili.mazzoCarteRisorsaOriginali.add(new Carta_Gioco(valori));
    			}
    			i++;
    		}
    	}
    	catch(Exception e) {
    		System.out.println(e);
    	}

    }

    /**
     * Inizializza le carte oro
     */
    private void initCarteOro(){
        InputStream in = ClassLoader.getSystemResourceAsStream("assets/CarteOro.csv");
        Variabili.mazzoCarteOroOriginali = new ArrayList<Carta_Gioco>();
        // Eccezione per la lettura dei file delle carte oro
    	try (BufferedReader br = new BufferedReader(new InputStreamReader(in))){	
    		String linea;
    		int i = 0;
            // ciclo per la lettura del file CarteOro.csv (leggi finchè la riga non è null)
    		while ((linea = br.readLine()) != null) {
                // la linea i=0 contiene l'esplicazione dei valori inseriti nel file (non è da leggere)
    			if (i!= 0) {
	    			String[] valori = linea.split(";");
                    // Aggiunge la carta al mazzo originale
	    			Variabili.mazzoCarteOroOriginali.add(new Carta_Gioco(valori));
    			}
    			i++;
    		}
    	}
    	catch(Exception e) {
    		System.out.println(e);
    	}

    }

    /**
     * Inizializza le carte iniziali
     */
    private void initCarteIniziali(){
        InputStream in = ClassLoader.getSystemResourceAsStream("assets/CarteIniziali.csv");
        Variabili.mazzoCarteInizialiOriginali = new ArrayList<Carta_Gioco>();
        // Eccezione per la lettura dei file delle carte iniziali
    	try (BufferedReader br = new BufferedReader(new InputStreamReader(in))){	
    		String linea;
    		int i = 0;
            // ciclo per la lettura del file CarteIniziali.csv (leggi finchè la riga non è null)
    		while ((linea = br.readLine()) != null) {
                // la linea i=0 contiene l'esplicazione dei valori inseriti nel file (non è da leggere)
    			if (i!= 0) {
	    			String[] valori = linea.split(";");
                    // Aggiunge la carta al mazzo originale
	    			Variabili.mazzoCarteInizialiOriginali.add(new Carta_Gioco(valori));
    			}
    			i++;
    		}
    	}
    	catch(Exception e) {
    		System.out.println(e);
    	}

    }

    /**
     * Crea le carte obiettivi
     */
    public void initCarteObiettivi(){
    	Variabili.mazzoCarteObiettivo[0] = new Carta_Obiettivo(0, 2, Enums.eCondizioneObiettivi.ORO_2_PIUME, -1);
    	Variabili.mazzoCarteObiettivo[1] = new Carta_Obiettivo(1, 2, Enums.eCondizioneObiettivi.ORO_2_VASETTI, -1);
    	Variabili.mazzoCarteObiettivo[2] = new Carta_Obiettivo(2, 2, Enums.eCondizioneObiettivi.ORO_2_PERGAMENE, -1);
    	Variabili.mazzoCarteObiettivo[3] = new Carta_Obiettivo(3, 3, Enums.eCondizioneObiettivi.ORO_3_SIMBOLI, -1);

    	Variabili.mazzoCarteObiettivo[4] = new Carta_Obiettivo(4, 2, Enums.eCondizioneObiettivi.ROSSO_FUNGHI, -1);
    	Variabili.mazzoCarteObiettivo[5] = new Carta_Obiettivo(5, 2, Enums.eCondizioneObiettivi.ROSSO_DIAGONALE, -1);
    	Variabili.mazzoCarteObiettivo[6] = new Carta_Obiettivo(6, 3, Enums.eCondizioneObiettivi.ROSSO_CAVALLO, -1);

    	Variabili.mazzoCarteObiettivo[7] = new Carta_Obiettivo(7, 2, Enums.eCondizioneObiettivi.VERDE_FOGLIE, -1);
        Variabili.mazzoCarteObiettivo[8] = new Carta_Obiettivo(8, 2, Enums.eCondizioneObiettivi.VERDE_DIAGONALE, -1);
        Variabili.mazzoCarteObiettivo[9] = new Carta_Obiettivo(9, 3, Enums.eCondizioneObiettivi.VERDE_CAVALLO, -1);

        Variabili.mazzoCarteObiettivo[10] = new Carta_Obiettivo(10, 2, Enums.eCondizioneObiettivi.BLU_LUPO, -1);
        Variabili.mazzoCarteObiettivo[11] = new Carta_Obiettivo(11, 2, Enums.eCondizioneObiettivi.BLU_DIAGONALE, -1);
        Variabili.mazzoCarteObiettivo[12] = new Carta_Obiettivo(12, 3, Enums.eCondizioneObiettivi.BLU_CAVALLO, -1);

        Variabili.mazzoCarteObiettivo[13] = new Carta_Obiettivo(13, 2, Enums.eCondizioneObiettivi.VIOLA_FARFALLA, -1);
        Variabili.mazzoCarteObiettivo[14] = new Carta_Obiettivo(14, 2, Enums.eCondizioneObiettivi.VIOLA_DIAGONALE, -1);
        Variabili.mazzoCarteObiettivo[15] = new Carta_Obiettivo(15, 3, Enums.eCondizioneObiettivi.VIOLA_CAVALLO, -1);

    }
    
    

    /**
     * Assegna carta obiettivo al giocatore 
     * @param ID_giocatore ID del giocatore a cui verrà assegnata la carta obiettivo
     * @return Numero casuale che rappresenta l'indice della carta obiettivo assegnata
     */
    public int obiettivoAssegna(int ID_giocatore){
        boolean continuaACercare = true;
        // Ciclo che assegna al giocatore la prima carta obiettivo disponibile
        while (continuaACercare){
            // Numero casuale che rappresenta un casuale indice delle carte obiettivo
            int numeroCasuale = (int)(Math.random() * Variabili.mazzoCarteObiettivo.length);
            // Controllo per verificare se la carta obiettivo considerata è disponibile
            if (Variabili.mazzoCarteObiettivo[numeroCasuale].cartaDisponibile == -1){
                Variabili.mazzoCarteObiettivo[numeroCasuale].cartaDisponibile = ID_giocatore;
                return numeroCasuale;
            }
        }
        return -1;
    }

    /**
     * Cerca casualmente due carte obiettivi disponibili
     * @return Indici della carte trovate
     */
    public int[] obiettivoGenera()
    {
    	//Cerca 2 indici disponibili.
    	//Il secondo deve essere differente dal primo
    	boolean continuaACercare = true;
    	int indice = 0;
    	int[] IDs = new int[2];

        // Ciclo che assegna al mercato le prime due carte obiettivo disponibili
    	while (continuaACercare)
    	{
            // Numero casuale che rappresenta un casuale indice delle carte obiettivo
	    	int  numeroCasuale = (int)(Math.random() * Variabili.mazzoCarteObiettivo.length);
            // Controllo per verificare se la carta obiettivo considerata è disponibile
	    	if (Variabili.mazzoCarteObiettivo[numeroCasuale].cartaDisponibile == -1)
	    	{
                // Se è la prima carta obiettivo comune la assegno alla prima posizione dell'array IDs
	    		if (indice == 0) {
	    			IDs[indice] = numeroCasuale;
	    			indice++;
	    		}
	    		else
	    		{
                    // Se non è la prima carta obiettivo comune controllo che il numero casuale
                    // sia diverso dall'indice dela prima carta, 
                    // se sono diversi assegno la carta con tale indice alla seconda posizione dell'array IDs

	    			if (numeroCasuale != IDs[0])
	    			{
		    			IDs[indice] = numeroCasuale;
		    			return IDs;
	    			}
	    		}
	    	}
    	}
    	return IDs;
    }

    /**
     * Assegna la carta obiettivo IDObiettivo al giocatore IDGiocatore
     * @param IDGiocatore
     * @param IDObiettivo
     */
    public void obiettivoSalva(int IDGiocatore, int IDObiettivo)
    {
    	Variabili.mazzoCarteObiettivo[IDObiettivo].cartaDisponibile = IDGiocatore;
    }

    /**
     * Pesca una carta e la rimuove dal mazzo
     * @param mazzo Mazzo da cui viene pescata la carta
     * @return
     */
    public Carta_Gioco pescaCartaGioco(List<Carta_Gioco> mazzo){
        Carta_Gioco cG = null;
        if (mazzo.size() > 0){
            cG = mazzo.get(0);
            mazzo.remove(0);
        }
        return cG;

    }

    /**
     * Cerca casualmente una carta disponibile
     * @return
     */
    public Carta_Gioco estraiCartaIniziale()
    {
    	// Genera il primo numero casuale compreso tra 0 ed il numero di carte iniziali rimaste
    	boolean continuaACercare = true;
        // Ciclo che assegna al giocatore la prima carta iniziale disponibile
    	while (continuaACercare)
    	{
            // Numero casuale che rappresenta un casuale indice delle carte iniziale
	    	int  numeroCasuale = (int)(Math.random() * getMazzoCarteIniziali().size() / 2);
	    	return getMazzoCarteIniziali().get(numeroCasuale);
    	}
    	return null;
    }

    /**
     * Restituisce la carta iniziale a partire dall'ID della carta iniziale
     * @param IDCartaIniziale ID della carta iniziale restituita
     * @return
     */
    public Carta_Gioco ottieniCartaInizialeDaID(int IDCartaIniziale){
        int ID = IDCartaIniziale;
        // Ciclo per trovare la carta iniziale con lo stesso ID passato come parametro
        for (Carta_Gioco cI : Variabili.partita.getMazzoCarteIniziali()){
            // Controllo per verificare se gli ID sono uguali
            if (cI.ID == ID){
                return cI;
            }
        }
        return null;
    }


    /**
     * Rimuove la carta iniziale dal mazzo delle carte iniziali
     * @param IDCartaIniziale ID della carta da rimuovere
     */
    public void rimuoviCartaIniziale(int IDCartaIniziale){
        int ID = IDCartaIniziale;
        int posI = 0;
        // Ciclo per trovare la carta iniziale con lo stesso ID passato come parametro
        for (Carta_Gioco cI : Variabili.partita.getMazzoCarteIniziali()){
            if (cI.ID == ID){
                // Rimuovo la carta iniziale
                Variabili.partita.getMazzoCarteIniziali().remove(posI);
                break;
            }
            posI++;
        }
        // Controllo per eliminare il fronte/retro associato alla carta rimossa
        if (ID > 5) ID -= 6;
        else ID += 6;
        posI = 0;
        // Ciclo per trovare il fronte/retro associato carta iniziale con lo stesso ID passato come parametro
        for (Carta_Gioco cI : Variabili.partita.getMazzoCarteIniziali()){
            if (cI.ID == ID){
                // Rimuovo la carta iniziale associata
                Variabili.partita.getMazzoCarteIniziali().remove(posI);
                break;
            }
            posI++;
        }
    }

    // Stampa le carte iniziali e obiettivo presenti nei rispettivi mazzi
    // Funzione utilizzata per il debug
    public void stampaCartePresenti()
    {
        System.out.println("");
        System.out.println("Carte iniziali disponibili");
        for(Carta_Gioco cG: Variabili.partita.getMazzoCarteIniziali()){
            System.out.print(" " + cG.ID);
        }

        System.out.println("");
        System.out.println("Carte obiettivi disponibili");
        for(int i = 0; i < Variabili.mazzoCarteObiettivo.length; i++){
            if (Variabili.mazzoCarteObiettivo[i].cartaDisponibile < 0)
                System.out.print(" " + i);
        }
    }

    /**
     * Toglie la carta al giocatore e la posiziona nel manoscritto
     * @param giocaFronte Identifica se la carta è giocata sul fronte
     */
    public void daGiocatoreAManoscritto(boolean giocaFronte){
        // Ottengo riga e colonna dalla carta del manoscritto selezionata
        int riga = giocatori.get(giocatoreAttuale).manoscritto.carte.get(indiceCartaManoscrittoScelta).riga;
        int colonna = giocatori.get(giocatoreAttuale).manoscritto.carte.get(indiceCartaManoscrittoScelta).colonna;
        // Ricaviamo riga e colonna della carta da agganciare
        Point p = rigaColonnaDaAngolo(riga, colonna, angoloCartaManoscrittoScelta);
        riga = p.y;
        colonna = p.x;
        // Otteniamo la carta selezionata dal giocatore
        Carta_Gioco cGG = giocatori.get(giocatoreAttuale).carteInMano[indiceCartaGiocoScelta];
        // Memorizzo se la carta da mettere nel manoscritto è giocata sul fronte o sul retro
        cGG.fronte = giocaFronte;
        // Posiziono la carta nel manoscritto
        giocatori.get(giocatoreAttuale).manoscritto.carte.add(new Cella_Manoscritto(cGG, riga, colonna));
        // Rimuovo la carta dalle carte del giocatore
        giocatori.get(giocatoreAttuale).carteInMano[indiceCartaGiocoScelta] = null;
        // Termino a fase di posizionamento carta e passo alla fase di rimpiazzo carta giocatore
        faseDelGiocatore = 1;
        // Resetto le variabili delle scelte fatte
        selezioneCarteGiocoRetroValida = false;
        indiceCartaGiocatoreDaRimpiazzare = indiceCartaGiocoScelta;
        angoloCartaManoscrittoScelta = null;
        angoloCartaMercatoScelta = null;

        // Se ho giocato una carta sul fronte calcolo i punti ottenuti
        if (giocaFronte)
            calcolaPuntiTurno(cGG);

    }

    /*
     * Rimpiazza la carta giocata dal giocatore
     * Se la carta è RISORSA1, RISORSA2, ORO1 o ORO2 la assegna al giocatore e rimpiazza la carta assegnata 
     * con la prima del rispettivo mazzo
     * Se la carta per il rimpiazzo arriva dal mazzo la rimpiazza
     */
    public void daMercatoAGiocatore(){
        Carta_Gioco cGMR = null;
        switch (indiceCartaMercatoRimpiazzo) {
            case 0:     // Selezionata carta mazzo risorse
                cGMR = mercato.mazzoCarteRisorse.get(0);
                if (mercato.mazzoCarteRisorse.size() > 0) mercato.mazzoCarteRisorse.remove(0);
                break;
            case 1:     // Selezionata carta mazzo oro
                cGMR = mercato.mazzoCarteOro.get(0);
                if (mercato.mazzoCarteOro.size() > 0) mercato.mazzoCarteOro.remove(0);
                break;
            case 2:     // Selezionata carta RISORSA1
                cGMR = mercato.carteRisorsa[0];  
                mercato.carteRisorsa[0] = mercato.mazzoCarteRisorse.get(0);
                if (mercato.mazzoCarteRisorse.size() > 0) mercato.mazzoCarteRisorse.remove(0);
                break;
            case 3:     // selezionata carta ORO1
                cGMR = mercato.carteOro[0];
                mercato.carteOro[0] = mercato.mazzoCarteOro.get(0);
                if (mercato.mazzoCarteOro.size() > 0) mercato.mazzoCarteOro.remove(0);
                break;
            case 4:     // selezionata carta RISORSA2
                cGMR = mercato.carteRisorsa[1];
                mercato.carteRisorsa[1] = mercato.mazzoCarteRisorse.get(0);
                if (mercato.mazzoCarteRisorse.size() > 0) mercato.mazzoCarteRisorse.remove(0);
                break;
            case 5:     // selezionata carta ORO2
                cGMR = mercato.carteOro[1];
                mercato.carteOro[1] = mercato.mazzoCarteOro.get(0);
                if (mercato.mazzoCarteOro.size() > 0) mercato.mazzoCarteOro.remove(0);
                break;
        }

        // Assegno la carta al giocatore
        giocatori.get(giocatoreAttuale).carteInMano[indiceCartaGiocatoreDaRimpiazzare] = cGMR;
        // Resetto le variabili utilizzate
        indiceCartaGiocatoreDaRimpiazzare = -1;
        indiceCartaMercatoRimpiazzo = -1;
        selezioneCarteGiocoFronteValida = false;
        selezioneCarteGiocoRetroValida = false;
        selezioneCartaRimpiazzoValida = false;

        // Passo alla fase 2 del gioco
        faseDelGiocatore = 2;

    }

    /**
     * Incrementa il numero del giocatore attuale limitandolo e verifica il fine partita
     */
    public void prossimoGiocatore(){
        giocatoreAttuale++;

        inizioTurno();
        // Se il giocatore attuale è dopo l'ultimo riseleziono il primo
        if (giocatoreAttuale > giocatori.size() - 1){
            // Se ho raggiunto l'ultimo turno la partita è terminata
            if (ultimoTurno){
                calcolaPuntiDaObiettivi();
                faseDelGiocatore = 3;
                Variabili.schermoAttivo = Enums.eElencoSchermi.PUNTEGGIO;
            }
            giocatoreAttuale = 0;
        }
        Variabili.schermoGioco.init();
    }

    /**
     * Ripristino condizioni iniziali ad inizio turno giocatore
     */
    private void inizioTurno(){
        indiceCartaGiocatoreDaRimpiazzare = -1;
        indiceCartaGiocoScelta = -1;
        indiceCartaManoscrittoScelta = -1;
        indiceCartaMercatoRimpiazzo = -1;
        angoloCartaManoscrittoScelta = null;
        angoloCartaMercatoScelta = null;
        selezioneCartaRimpiazzoValida = false;
        selezioneCarteGiocoFronteValida = false;
        selezioneCarteGiocoRetroValida = false;
        
        faseDelGiocatore = 0;
        punteggioTurno = 0;
    }

    /**
     * Data riga e colonna restituisce riga e colonna della carta "agganciata" all'angolo
     * @param riga - Riga della carta origine
     * @param colonna - Colonna della carta origine
     * @param angolo - Angolo dove si vuole posizionare la carta
     * @return - Point con riga e colonna della carta agganciata all'angolo
     */
    public Point rigaColonnaDaAngolo(int riga, int colonna, Enums.eAngolo angolo){
        int r = 20;
        int c = 20;
        switch (angolo) {
            case NO:
                r = riga - 1;
                c = colonna - 1;
                break;
            case NE:
                r = riga - 1;
                c = colonna + 1;
                break;
            case SE:
                r = riga + 1;
                c = colonna + 1;
                break;
            case SO:
                r = riga + 1;
                c = colonna - 1;
                break;
        
            default:
                break;
        }
        return new Point(c, r);
    }

    /**
     * Calcola i punti effettuati dal piazzamento di una carta (un turno)
     * @param cGG Carta piazzata
     */
    public void calcolaPuntiTurno(Carta_Gioco cGG){
        punteggioTurno = giocatori.get(giocatoreAttuale).manoscritto.contaPunti(cGG);
        giocatori.get(giocatoreAttuale).punteggio += punteggioTurno;
        verificaFineGioco();
    }

    /**
     * Verifica se far partire l'ultimo turno
     */
    public void verificaFineGioco(){
        if (giocatori.get(giocatoreAttuale).punteggio >= 20)
            ultimoTurno = true;
    }

    /**
     * Valuta se le condizioni per il posizionamento della carta giocatore nel manoscritto sono verificate
     */
    public void valutaCarteScelte(){
        if (faseDelGiocatore != 0) return;
        if (!giocoInCorso) return;
        if (angoloCartaManoscrittoScelta == null) return;
        // Se non ho selezionato la carta del giocatore esco
        // Se non ho selezionato la carta del manoscritto esco
        if (indiceCartaGiocoScelta < 0 || indiceCartaManoscrittoScelta < 0){
            selezioneCarteGiocoRetroValida = false;
            selezioneCarteGiocoFronteValida = false;
            return;
        }
        int riga;
        int colonna;
        Giocatore gio = giocatori.get(giocatoreAttuale); 
        // Ottengo la carta da giocare
        Carta_Gioco cGG = gio.carteInMano[indiceCartaGiocoScelta];
        // Ottengo riga e colonna della carta del manoscritto
        Cella_Manoscritto cella = gio.manoscritto.carte.get(indiceCartaManoscrittoScelta);
        riga = cella.riga;
        colonna = cella.colonna;

        //Richiedo al manoscritto di generarmi la matrice degli angoli utilizzati
        gio.manoscritto.generaAngoliDaCarte();

        // Recupero la matrice degli angoli
        Enums.eSimbolo[][][] matrAngoli = gio.manoscritto.angoli;
        int[][] matrNumCarte = gio.manoscritto.numeroCarteSuAngolo;

        //Creo la matrice dei quattro angoli da valutare
        Point[] angoloG = new Point[4];
        // Passando da matrice carte a matrice angoli devo raddoppiare riga e colonna
        riga *= 2;
        colonna *= 2;

        // Determino in senso orario gli angoli della carta da verificare
        switch (angoloCartaManoscrittoScelta) {
            case NO:
                colonna -= 2;            
                riga -= 2;
                break;
            case NE:
                colonna += 2;            
                riga -= 2;
                break;
            case SE:
                colonna += 2;            
                riga += 2;
                break;
            case SO:
                colonna -= 2;            
                riga += 2;
                break;
        }
        angoloG[0] = new Point(colonna, riga);
        angoloG[1] = new Point(colonna + 2, riga);
        angoloG[2] = new Point(colonna + 2, riga + 2);
        angoloG[3] = new Point(colonna, riga + 2);

        // Confronto ogni singolo angolo, le condizioni sono:
        // 1 : Se non ho carte va bene
        // 2 : Se ho una carta non deve essere nulla
        // 3 : Se ho più di una carta non va bene
        boolean piazzamentoValido = true;
        for (int ang = 0; ang < 4; ang++){
            int numeroCarteSuAngolo = matrNumCarte[angoloG[ang].x][angoloG[ang].y];
            Enums.eSimbolo simboloSuAngolo = matrAngoli[angoloG[ang].x][angoloG[ang].y][0];
            // Verifico condizione 2
            if (numeroCarteSuAngolo == 1 && simboloSuAngolo == Enums.eSimbolo.NULLO){
                piazzamentoValido = false;
                break;
            }
            // Verifico condizione 3
            if (numeroCarteSuAngolo > 1){
                piazzamentoValido = false;
                break;
            }
           
        }
        selezioneCarteGiocoFronteValida = piazzamentoValido;
        selezioneCarteGiocoRetroValida = piazzamentoValido;
        if (!piazzamentoValido) return;

        // Nel caso di carta oro verifico se le condizioni di piazzamento sono verificate
        boolean retroPiazzabile = false;
        if (cGG.tipo == Enums.eTipoCarta.ORO){
            // Ottengo i simboli e le quantità richieste per il posizionamento delle carte giocatore nel manoscritto
            Enums.eSimbolo[] simb = cGG.simboliXPosizionamento;
            int[] quantiNeServono = cGG.simboliXPosizionamentoQuantita;
            int[] quantiNeHoTrovati = new int[2];
            // Conto la quantità di simboli richiesti presenti nel manoscritto
            quantiNeHoTrovati[0] = gio.manoscritto.contaSimboliManoscritto(simb[0]);
            if (simb[1] == Enums.eSimbolo.NULLO) quantiNeHoTrovati[1] = 10000;
            else quantiNeHoTrovati[1] = gio.manoscritto.contaSimboliManoscritto(simb[1]);
            // Confronto le quantità richieste con quelle trovate
            if (quantiNeHoTrovati[0] < quantiNeServono[0]) piazzamentoValido = false;
            if (quantiNeHoTrovati[1] < quantiNeServono[1]) piazzamentoValido = false;

            retroPiazzabile = true;
        }
        selezioneCarteGiocoFronteValida = piazzamentoValido;
        selezioneCarteGiocoRetroValida = piazzamentoValido | retroPiazzabile;


    }

    /**
     * Calcola i punti effettuati tramite gli obiettivi
     */
    public void calcolaPuntiDaObiettivi(){
        int indiceGiocatore = 0;

        // Ciclo nella lista giocatori 
        for (Giocatore gio : giocatori){
            int punti = 0;
            int obiettiviRaggiunti = 0;

            // Aggiorno il conteggio dei punti e del numero di obiettivi raggiunti considerando i due obiettivi comuni 
            // e quello segreto appartenente al giocatore corrente
            Carta_Obiettivo cO = Variabili.mazzoCarteObiettivo[obiettiviComuni[0]];
            gio.puntiObiettivoComune1 = gio.manoscritto.contaPuntiObiettivo(cO);
            if (gio.puntiObiettivoComune1 > 0) obiettiviRaggiunti++;

            cO = Variabili.mazzoCarteObiettivo[obiettiviComuni[1]];
            gio.puntiObiettivoComune2 = gio.manoscritto.contaPuntiObiettivo(cO);
            if (gio.puntiObiettivoComune2 > 0) obiettiviRaggiunti++;

            cO = gio.cartaObiettivo;
            gio.puntiObiettivoSegreto = gio.manoscritto.contaPuntiObiettivo(cO);
            if (gio.puntiObiettivoSegreto > 0) obiettiviRaggiunti++;

            punti = gio.punteggio + gio.puntiObiettivoComune1 + gio.puntiObiettivoComune2 + gio.puntiObiettivoSegreto;

            // Memorizzo i valori per ordinare la classifica
            classifica.add(new Funzioni.risultati(punti, indiceGiocatore, obiettiviRaggiunti));
            indiceGiocatore++;

        }
        // Ordino la classifica
        Collections.sort(classifica, new Funzioni.comparatoreRisultati());

    }
    
    public void turno() {
    	if(contatoreTurni!=0) {
    		//non siamo nel primo turno
    		primoTurno=false;
    		
    	}
    	while(ultimoTurno==false) {	
    		for(int i=0;i<giocatori.size();i++) {
    			giocatoreAttuale=i;
    			stampaManoscritto(giocatoreAttuale);
    			giocatori.get(giocatoreAttuale).piazzaCarta(giocatori.get(i));
    			//mostro le opzioni per fare pescare al giocatore
    			System.out.println("Opzioni disponibili di pescaggio:\n");
    			mercato.stampaMercato(mercato.carteRisorsa, mercato.carteOro);    			
    			//cerco una carta presente sia nelle carte piazzate che nella mano 
    			//(così trovo la carta appena piazzata e la sostituisco con la carta da pescare)
    			Cella_Manoscritto c=new Cella_Manoscritto(giocatori.get(giocatoreAttuale).cartaIniziale, giocatori.get(giocatoreAttuale).manoscritto,20,20);
    			/*
    			 * Per convertire l'id della carta nella mano e poi compararlo se presente nella lista carte giocate
    			 */
    			String IDCartaMano0=String.valueOf(giocatori.get(giocatoreAttuale).carteInMano[0].ID);
    			String IDCartaMano1=String.valueOf(giocatori.get(giocatoreAttuale).carteInMano[1].ID);
    			String IDCartaMano2=String.valueOf(giocatori.get(giocatoreAttuale).carteInMano[2].ID);
    			if(c.getIndex(giocatori.get(giocatoreAttuale).manoscritto.carte,IDCartaMano0)!=-1) {
    				//ho trovato la carta che ho giocato
    				giocatori.get(i).carteInMano[0]=mercato.PescaCarta(mercato);
    			}else if(c.getIndex(giocatori.get(giocatoreAttuale).manoscritto.carte,IDCartaMano1)!=-1) {
    				giocatori.get(i).carteInMano[1]=mercato.PescaCarta(mercato);
    			}else if(c.getIndex(giocatori.get(giocatoreAttuale).manoscritto.carte,IDCartaMano2)!=-1) {
    				giocatori.get(i).carteInMano[2]=mercato.PescaCarta(mercato);
    			}
    			
    			//conto i simboli presenti nelle carte piazzate
    			giocatori.get(i).manoscritto.contaSimboliPiazzati(giocatori.get(i));
    			
    			//verifico se piazzamento carta da punti in più e verifica anche se si finisce nell'ultimo turno
    			calcolaPuntiTurno(giocatori.get(i).manoscritto.carte.get(giocatori.get(i).manoscritto.carte.size()-1).carta);
    			//controllo se l'obiettivo segreto è stato raggiunto
    			giocatori.get(i).manoscritto.contaPuntiObiettivo(giocatori.get(i).cartaObiettivo);
    			
    			//stampo il manoscritto
    			stampaManoscritto(giocatoreAttuale);
    			
    			
    			if(ultimoTurno==true) {
    				//controllo obiettivi comuni se danno punti extra
    				calcolaPuntiDaObiettivi();
    			}
    		}
    		contatoreTurni+=1;
    	}
    	
    }
	public List<Carta_Gioco> getMazzoCarteIniziali() {
		return mazzoCarteIniziali;
	}
	public void setMazzoCarteIniziali(List<Carta_Gioco> mazzoCarteIniziali) {
		this.mazzoCarteIniziali = mazzoCarteIniziali;
	}
	public List<Carta_Gioco> getMazzoCarteOro() {
		return mazzoCarteOro;
	}
	public void setMazzoCarteOro(List<Carta_Gioco> mazzoCarteOro) {
		this.mazzoCarteOro = mazzoCarteOro;
	}
	public List<Carta_Gioco> getMazzoCarteRisorsa() {
		return mazzoCarteRisorsa;
	}
	public void setMazzoCarteRisorsa(List<Carta_Gioco> mazzoCarteRisorsa) {
		this.mazzoCarteRisorsa = mazzoCarteRisorsa;
	}
	public Carta_Obiettivo[] getMazzoCarteObiettivo() {
		return mazzoCarteObiettivo;
	}
	public Carta_Obiettivo CercaCartaObiettivo(int id) {
		for(int i=0;i<Variabili.mazzoCarteObiettivo.length;i++) {
			if(Variabili.mazzoCarteObiettivo[i].ID==id) {
				return Variabili.mazzoCarteObiettivo[i];
			}
		}
		return null;
	}
	public void setMazzoCarteObiettivo(Carta_Obiettivo[] mazzoCarteObiettivo) {
		this.mazzoCarteObiettivo = mazzoCarteObiettivo;
	}
	
	/**
	 * stampa il manoscritto di un giocatore
	 * @param giocatoreAttuale giocatore che sta giocando in questo momento
	 */
	public void stampaManoscritto(int giocatoreAttualee) {
		System.out.println("Turno numero: "+contatoreTurni);
		//giocatoreAttualee=giocatoreAttuale;	
		System.out.println("--------------------------------------------------------------------------------"+
					"\n"+giocatori.get(giocatoreAttualee).nome+
					"\nCarte in mano:\n");
			Carta_Gioco mano;
			for(int i=0;i<giocatori.get(giocatoreAttualee).carteInMano.length;i++) {
				System.out.println(i+": "+"fronte");
				mano=giocatori.get(giocatoreAttualee).carteInMano[i];
				mano.toString(mano,true);
				System.out.println(i+": "+"retro");
				mano.toString(mano,false);
				System.out.println("\n");
			}
				System.out.println("--------------------------------------------------------------------------------"+
				"\nElenco delle carte piazzate");
				 
				 //stampa l'elenco delle carte piazzate
					for(int i=0;i<giocatori.get(giocatoreAttualee).manoscritto.carte.size();i++) {
						giocatori.get(giocatoreAttualee).manoscritto.carte.get(i).carta.toString(giocatori.get(giocatoreAttualee).manoscritto.carte.get(i).carta,
								giocatori.get(giocatoreAttualee).manoscritto.carte.get(i).carta.fronte);
		
					}
					if(giocatori.get(giocatoreAttualee).manoscritto.carte.size()>1) {
						System.out.println("--------------------------------------------------------------------------------");
						//stampo la carta iniziale
						//giocatori.get(giocatoreAttualee).manoscritto.carte.get(0).carta.toString(giocatori.get(giocatoreAttualee).manoscritto.carte.get(0).carta, 
								//giocatori.get(giocatoreAttualee).manoscritto.carte.get(0).carta.fronte);
						//stampo tutte le altre carte piazzate
						for(int i=1;i<giocatori.get(giocatoreAttualee).manoscritto.carte.size();i++) {
							//stampo solo ID della carta piazzata, ID, tipo, angolo della carta a cui si è agganciata
							System.out.println("Carta "+giocatori.get(giocatoreAttualee).manoscritto.carte.get(i).carta.ID+" è piazzata nell'angolo "+
									giocatori.get(giocatoreAttualee).manoscritto.carte.get(i).carta.toString(giocatori.get(giocatoreAttualee).manoscritto.carte.get(i).carta.getAngoloIdCartaBase())+
									" della carta "+giocatori.get(giocatoreAttualee).manoscritto.carte.get(i).carta.getIdCartaBase()+" di tipo "+
									giocatori.get(giocatoreAttualee).manoscritto.carte.get(i).carta.toString(giocatori.get(giocatoreAttualee).manoscritto.carte.get(i).carta.getTipoCartaBase())); 
						}
					}
					
					
					//stampo obiettivo segreto e obiettivi comuni
					System.out.println("--------------------------------------------------------------------------------");
					System.out.println("Obiettivo segreto");
					giocatori.get(giocatoreAttualee).cartaObiettivo.toString(giocatori.get(giocatoreAttualee).cartaObiettivo);
					Carta_Obiettivo carta1=CercaCartaObiettivo(obiettiviComuni[0]);
					Carta_Obiettivo carta2=CercaCartaObiettivo(obiettiviComuni[0]);
					System.out.println("Obiettivi comuni\n1: ");
					carta1.toString(carta1);
					System.out.println("\n2: ");
					carta2.toString(carta2);
					
					//stampo i contatori dei simboli piazzati
					System.out.println("--------------------------------------------------------------------------------");	
					System.out.println("Totale dei simboli piazzati");
					System.out.println("FUNGO: "+giocatori.get(giocatoreAttualee).getContatoreSimboli(0)+", FOGLIA: "+
								giocatori.get(giocatoreAttualee).getContatoreSimboli(1)+", LUPO: "+giocatori.get(giocatoreAttualee).getContatoreSimboli(2)+
								", FARFALLA: "+giocatori.get(giocatoreAttualee).getContatoreSimboli(3)+", PIUMA: "+giocatori.get(giocatoreAttualee).getContatoreSimboli(4)+
								", VASETTO: "+giocatori.get(giocatoreAttualee).getContatoreSimboli(5)+", PERGAMENA: "+giocatori.get(giocatoreAttualee).getContatoreSimboli(6));
					System.out.println("--------------------------------------------------------------------------------");
					System.out.println("Il tuo punteggio: "+giocatori.get(giocatoreAttualee).punteggio);
					System.out.println("--------------------------------------------------------------------------------");
					System.out.println("--------------------------------------------------------------------------------\n\n");
	}
	
	/**
	 * Questo metodo si occupa di cercare a quale giocatore è associato il punteggio
	 * @param g giocatore
	 * @param punti punti da verificare se sono quelli effettuati da questo giocatore
	 * @return il nome del giocatore se viene trovato un giocatore con questo punteggio
	 */
	public String cercaPunteggioGiocatore(Giocatore g, int punti) {
		if(g.punteggio==punti) {
			return g.nome;
		}else {
			return null;
		}
	}
	/**
	 * Questo metodo stampa la classifica partendo dall'ultima posizione per arrivare al vincitore
	 */
	public void stampaClassifica() {
		//ordino i punteggi di ogni giocatore in un array così li posso ordinare
		ArrayList<Integer> classifica=new ArrayList<Integer>();
		for(int i=0;i<giocatori.size();i++) {
			classifica.add(giocatori.get(i).punteggio);
		}
		//ordino l'array in modo tale che ho i numeri in ordine crescente
		Arrays.sort(classifica.toArray());
		
		//cerco il giocatore con quel punteggio e lo mando a schermo
		for(int i=0;i<classifica.size();i++) {
			int numero=classifica.size();
			System.out.println((classifica.size()-i)+" classificato: "+cercaPunteggioGiocatore(giocatori.get(i),classifica.get(i)));
		}
		
	}
}
