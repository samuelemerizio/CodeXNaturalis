
/**
 * Rappresenta una carta gioco di tipo risorse, oro ed iniziale
 */
public class Carta_Gioco {
    
    /**
    * Identifica in modo univoco la carta in funzione del tipo
    */
    int ID;        

    /**
    * True se la carta è giocata sul fronte
    */                                    
    boolean fronte;                

    /**
    * Identifica se la carta rappresenta una carta Risorsa, Oro o Iniziale 
    */              
    Enums.eTipoCarta tipo;

    /**
    * Identifica i simboli presenti sul retro della carta
    */
    Enums.eSimbolo[] simboloRetro = new Enums.eSimbolo[3];

    /**
    * Identifica i simboli presenti sui quattro angoli frontali della carta.
    * Gli angoli vengono rappresentati dall'Enum eAngolo in senso orario partendo da quello in alto a sinistra
    */
    Enums.eSimbolo[] simboloAngolo = new Enums.eSimbolo[4];

    /**
    * Identifica i simboli necessari ad una carta oro per essere posizionata.
    * Sono presenti al massimo due tipologie di simboli. La quantità di ogni simbolo
    * è contenuta nell'array simboliXPosizionamentoQuantita
    */
    Enums.eSimbolo[] simboliXPosizionamento = new Enums.eSimbolo[2];

    /**
    * Contiene la quantità di simboli necessari per il posizionamento della carta Oro
    */
    int[] simboliXPosizionamentoQuantita = new int[2];

    /**
    * Ogni carta giocata può fornire dei punti secondo le seguenti modalità
    * 1: nessun punto, indicato dal valore 0
    * 2: punti indicato da valorePunti se non sono presenti condizioni indicate in moltiplicatorePunti
    * 3: punti indicato da valorePunti moltiplicato per le condizioni indicate da moltiplicatorePunti
    */
    int valorePunti;

    /**
     * Moltiplicatore punti carta (vedi variabile valorePunti)
     */
    Enums.eMoltiplicatorePunti moltiplicatorePunti;

    /**
     * Il costruttore inizializza le variabili della classe a partire dall'array di stringhe parametri
     * @param parametri i parametri vengono acquisiti dal file Carte.csv
     */
    public Carta_Gioco(String[] parametri){
        // Verifico che i parametri non siano nulli
        if (parametri == null){
            Funzioni.mostraMessaggioErrore("Attenzione: Richiamato il costruttore carta gioco senza parametri");
            return ;
        }
        // Verifico che parametri abbia lunghezza 15
        if (parametri.length != 15){
            Funzioni.mostraMessaggioErrore("Attenzione: Richiamato il costruttore carta gioco" +
                                            " con numero errato di parametri");
            return ;
        }
        // Racchiudo tutte le conversioni dei parametri in un costrutto try catch per evitare errori di conversione di tipi
        try{
            this.ID = Integer.valueOf(parametri[0]);
            this.tipo = Enums.eTipoCarta.getTipoCarta(Integer.valueOf(parametri[1]));
            this.simboloAngolo[0] = Enums.eSimbolo.getSimbolo(Integer.valueOf(parametri[2]));
            this.simboloAngolo[1] = Enums.eSimbolo.getSimbolo(Integer.valueOf(parametri[3]));
            this.simboloAngolo[2] = Enums.eSimbolo.getSimbolo(Integer.valueOf(parametri[4]));
            this.simboloAngolo[3] = Enums.eSimbolo.getSimbolo(Integer.valueOf(parametri[5]));
            this.simboloRetro[0] = Enums.eSimbolo.getSimbolo(Integer.valueOf(parametri[6]));
            this.simboloRetro[1] = Enums.eSimbolo.getSimbolo(Integer.valueOf(parametri[7]));
            this.simboloRetro[2] = Enums.eSimbolo.getSimbolo(Integer.valueOf(parametri[8]));
            this.simboliXPosizionamento[0] = Enums.eSimbolo.getSimbolo(Integer.valueOf(parametri[9]));
            this.simboliXPosizionamento[1] = Enums.eSimbolo.getSimbolo(Integer.valueOf(parametri[10]));
            this.simboliXPosizionamentoQuantita[0] = Integer.valueOf(parametri[11]);
            this.simboliXPosizionamentoQuantita[1] = Integer.valueOf(parametri[12]);
            this.valorePunti = Integer.valueOf(parametri[13]);
            this.moltiplicatorePunti = Enums.eMoltiplicatorePunti.getMoltiplicatorePunti(Integer.valueOf(parametri[14]));
        }
        catch(Exception e){
            Funzioni.mostraMessaggioErrore("Attenzione: il costruttore carta gioco ha sollevato un eccezione: " +
                                            e.getMessage());
        }
    }


	public boolean controllaCondizione(int ID) {
		// TODO Auto-generated method stub
		return true;
	}
	
	//scansiona entrambi i file e cerca la carta con l'ID che corrisponde
	public String cercaCarta(int ID) {
		return null;
	}

}
