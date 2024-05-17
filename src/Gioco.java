import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    int[] obiettiviComuni = new int[2];

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

    List <Carta_Gioco> mazzoCarteIniziali = new ArrayList<Carta_Gioco>();

    int indiceCartaMercatoScelta = 0;				//Identifica la carta 1,2 o 3 scelta dal giocatore
    Enums.eAngolo angoloCartaMercatoScelta;			//Identifica quale angolo della carta scelta dal giocatore verrà utilizzato per aggancio
    int indiceCartaManoscrittoScelta;				//Identifica l'indice della carta manoscritto scelta dal giocatore
    Enums.eAngolo angoloCartaManoscrittoScelta;		//Identifica quale angolo della carta del manoscritto è stata scelta
    boolean selezioneCarteGiocoValida = false;		//Quanto a TRUE è possibile visualizzare il pulsante per effettuare giocata carta RETRO
    boolean selezioneCarteGiocoFronteValida = false;//Quanto a TRUE è possibile visualizzare il pulsante per effettuare giocata carta FRONTE
    boolean selezioneCartaRimpiazzoValida = false;	//TRUE se la carta selezionata dal mercato per il rimpiazzo è valida
    int indiceCartaMercatoRimpiazzo = -1;			//Carta selezionata dal mercato per essere caricata al giocatore, mazzo, o una delle 2 del mercato
    boolean CartaMercatoRimpiazzoTipo = false;		//TRUE ho selezionato una carta risorsa, FALSE carta oro
    int indiceCartaGiocatoreDaRimpiazzare = -1;		//Carta da rimpiazzare al giocatore in fase 1


    public Gioco(){
        Init_Carte();
    }

    public void Init_Carte(){
        // Inizializzo i mazzi di carte leggendoli dai vari file
        initCarteIniziali();
        initCarteOro();
        initCarteRisorse();
        initCarteObiettivi();

        // Creo i mazzi Oro e Risorsa da mettere nel mercato
        mercato.mescolaMazziCarteRisorseOro(Variabili.mazzoCarteRisorsaOriginali, Variabili.mazzoCarteOroOriginali);

        mazzoCarteIniziali = Variabili.mazzoCarteInizialiOriginali;

        obiettiviComuni[0] = obiettivoAssegna(5);
        obiettiviComuni[1] = obiettivoAssegna(6);
        mercato.carteRisorsa[0] = pescaCartaGioco(mercato.mazzoCarteRisorse);
        mercato.carteRisorsa[1] = pescaCartaGioco(mercato.mazzoCarteRisorse);
        mercato.carteOro[0] = pescaCartaGioco(mercato.mazzoCarteOro);
        mercato.carteOro[1] = pescaCartaGioco(mercato.mazzoCarteOro);

        ultimoTurno = false;
    }




    private void initCarteRisorse(){
        InputStream in = ClassLoader.getSystemResourceAsStream("assets/CarteRisorse.csv");
    	try (BufferedReader br = new BufferedReader(new InputStreamReader(in))){	
    		String linea;
    		int i = 0;
    		while ((linea = br.readLine()) != null) {
    			if (i!= 0) {
	    			String[] valori = linea.split(";");
	    			Variabili.mazzoCarteRisorsaOriginali.add(new Carta_Gioco(valori));
    			}
    			i++;
    		}
    	}
    	catch(Exception e) {
    		System.out.println(e);
    	}

    }

    private void initCarteOro(){
        InputStream in = ClassLoader.getSystemResourceAsStream("assets/CarteOro.csv");
    	try (BufferedReader br = new BufferedReader(new InputStreamReader(in))){	
    		String linea;
    		int i = 0;
    		while ((linea = br.readLine()) != null) {
    			if (i!= 0) {
	    			String[] valori = linea.split(";");
	    			Variabili.mazzoCarteOroOriginali.add(new Carta_Gioco(valori));
    			}
    			i++;
    		}
    	}
    	catch(Exception e) {
    		System.out.println(e);
    	}

    }

    private void initCarteIniziali(){
        InputStream in = ClassLoader.getSystemResourceAsStream("assets/CarteIniziali.csv");
    	try (BufferedReader br = new BufferedReader(new InputStreamReader(in))){	
    		String linea;
    		int i = 0;
    		while ((linea = br.readLine()) != null) {
    			if (i!= 0) {
	    			String[] valori = linea.split(";");
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
    private void initCarteObiettivi(){
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

    public int obiettivoAssegna(int ID_giocatore){
        boolean continuaACercare = true;
        while (continuaACercare){
            int numeroCasuale = (int)(Math.random() * Variabili.mazzoCarteObiettivo.length);
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

    	while (continuaACercare)
    	{
	    	int  numeroCasuale = (int)(Math.random() * Variabili.mazzoCarteObiettivo.length);
	    	if (Variabili.mazzoCarteObiettivo[numeroCasuale].cartaDisponibile == -1)
	    	{
	    		if (indice == 0) {
	    			IDs[indice] = numeroCasuale;
	    			indice++;
	    		}
	    		else
	    		{
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
    	//Genera il primo numero casuale compreso tra 0 ed il numero di carte iniziali rimaste
    	boolean continuaACercare = true;
    	while (continuaACercare)
    	{
	    	int  numeroCasuale = (int)(Math.random() * mazzoCarteIniziali.size() / 2);
	    	return mazzoCarteIniziali.get(numeroCasuale);
    	}
    	return null;
    }

    public Carta_Gioco ottieniCartaInizialeDaID(int IDCartaIniziale){
        int ID = IDCartaIniziale;
        for (Carta_Gioco cI : Variabili.partita.mazzoCarteIniziali){
            if (cI.ID == ID){
                return cI;
            }
        }
        return null;
    }

    public void rimuoviCartaIniziale(int IDCartaIniziale){
        int ID = IDCartaIniziale;
        int posI = 0;
        for (Carta_Gioco cI : Variabili.partita.mazzoCarteIniziali){
            if (cI.ID == ID){
                Variabili.partita.mazzoCarteIniziali.remove(posI);
                break;
            }
            posI++;
        }
        if (ID > 5) ID -= 6;
        else ID += 6;
        posI = 0;
        for (Carta_Gioco cI : Variabili.partita.mazzoCarteIniziali){
            if (cI.ID == ID){
                Variabili.partita.mazzoCarteIniziali.remove(posI);
                break;
            }
            posI++;
        }
    }


    public void stampaCartePresenti()
    {
        System.out.println("");
        System.out.println("Carte iniziali disponibili");
        for(Carta_Gioco cG: Variabili.partita.mazzoCarteIniziali){
            System.out.print(" " + cG.ID);
        }

        System.out.println("");
        System.out.println("Carte obiettivi disponibili");
        for(int i = 0; i < Variabili.mazzoCarteObiettivo.length; i++){
            if (Variabili.mazzoCarteObiettivo[i].cartaDisponibile < 0)
                System.out.print(" " + i);
        }

        



    }


}
