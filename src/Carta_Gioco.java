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
    
    public int getIdCartaBase() {
		return idCartaBase;
	}

	public void setIdCartaBase(int idCartaBase) {
		this.idCartaBase = idCartaBase;
	}

	public Enums.eAngolo getAngoloIdCartaBase() {
		return angoloCartaBase;
	}

	public void setAngoloIdCartaBase(Enums.eAngolo angoloIdCartaBase) {
		this.angoloCartaBase = angoloIdCartaBase;
	}

	/**
     * indica l'id della carta alla quale quest'ultima si aggancerà
     */
    private int idCartaBase=-1;
    /**
     * indica il tipo della carta base
     */
    private Enums.eTipoCarta tipoCartaBase=Enums.eTipoCarta.INIZIALE;
    public Enums.eTipoCarta getTipoCartaBase() {
		return tipoCartaBase;
	}

	public void setTipoCartaBase(Enums.eTipoCarta tipoCartaBase) {
		this.tipoCartaBase = tipoCartaBase;
	}

	/**
     * indica l'angolo alla quale questa carta si aggancia  
     */
    private Enums.eAngolo angoloCartaBase=Enums.eAngolo.NE;

    /**
     * Moltiplicatore punti carta (vedi variabile valorePunti)
     */
    Enums.eMoltiplicatorePunti moltiplicatorePunti;
    Gioco gioco;
    
    

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
            this.fronte = true;
        }
        catch(Exception e){
            Funzioni.mostraMessaggioErrore("Attenzione: il costruttore carta gioco ha sollevato un eccezione: " +
                                            e.getMessage());
        }
    }

    public Carta_Gioco() {
		// TODO Auto-generated constructor stub
	}

	public String versoCarta(Carta_Gioco g) {
    	if(g.fronte==true) {
    		return "fronte";
    	}
    	return "retro";
    }
    //mostra in sequenza che cosa contengono gli angoli
    public String simboliAngoliFronte(Carta_Gioco g) {
    	
    		return "NO: "+g.simboloAngolo[0].name()+", NE: "+g.simboloAngolo[1].name()
    				+", SE: "+g.simboloAngolo[2].name()+", SO: "+g.simboloAngolo[3].name();
    	
    }
    public String simboliRetro(Carta_Gioco g) {
    	
		return "Simbolo1: "+g.simboloRetro[0].name()+", Simbolo2: "+g.simboloRetro[1].name()
				+", Simbolo3: "+g.simboloRetro[2].name();
	
}
    public String requisiti(Carta_Gioco g) {
    	return (", Req1 piazzamento: "+g.simboliXPosizionamento[0].name()+" qta: "+g.simboliXPosizionamentoQuantita[0]
    			+", Req2 piazzamento: "+g.simboliXPosizionamento[1].name()+" qta: "+g.simboliXPosizionamentoQuantita[1]);
    			//+", Req3 piazzamento: "+g.simboliXPosizionamento[2]+" qta: "+g.simboliXPosizionamentoQuantita[3]);
    }
    //elenco delle caratteristiche della carta
    public void toString(Carta_Gioco c, boolean fronte) {
    	if(fronte==true) {
    		System.out.println("Carta fronte: "+c.ID+", tipo: "+c.tipo.name()+", "+simboliAngoliFronte(c)
    					+requisiti(c)+", Cond pti bonus: "+c.moltiplicatorePunti.name()+", Pti bonus: "+c.valorePunti);
    	}else {
    		System.out.println("Carta retro: "+c.ID+", tipo: "+c.tipo.name()+", "+simboliRetro(c));
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
	
	 public Enums.eSimbolo[] getSimboloAngolo() {
			return simboloAngolo;
		}
	 
	 public boolean latoCarta(boolean fronte) {
		return fronte;
		 
	 }
	 
	 /**
		 * 
		 * @param g
		 * @param angolo
		 * @param ID della carta da controllare se ha gli angoli liberi (ultima carta piazzata nel manoscritto)
		 * @return
		 */
	    public boolean angoloLibero(Giocatore giocatore,String angolo, String ID) throws Exception {
	    	//inizializzo cella manoscritto con una carta per evitare errori
	    	Cella_Manoscritto CartaCercata=new Cella_Manoscritto(giocatore.cartaIniziale,giocatore.manoscritto, 20, 20);
	    	CartaCercata.cercaCarta(giocatore.manoscritto.carte, ID);
	    	  	
	    	if(angolo.equals("NO")) {
	    		//vado a prendere ultima carta dalla lista carte e guardo angolo NO
	    		//giocatore attuale dovra essere quello in gioco (da impostare)
	    		if(CartaCercata.angoloOccupatoNO(giocatore.manoscritto.carte, CartaCercata)==true) {
	    			//ANGOLO OCCUPATO
	    			return false;
	    		}else {
	    			return true;
	    		}
	    	}else if(angolo.equals("NE")) {
	    		if(CartaCercata.angoloOccupatoNE(giocatore.manoscritto.carte, CartaCercata)==true) {
	    			//ANGOLO OCCUPATO
	    			return false;
	    		}else {
	    			return true;
	    		}
	    	}else if(angolo.equals("SE")) {
	    		if(CartaCercata.angoloOccupatoSE(giocatore.manoscritto.carte, CartaCercata)==true) {
	    			//ANGOLO OCCUPATO
	    			return false;
	    		}else {
	    			return true;
	    		}
	    	}else {
	    		if(CartaCercata.angoloOccupatoSO(giocatore.manoscritto.carte, CartaCercata)==true) {
	    			//ANGOLO OCCUPATO
	    			return false;
	    		}else {
	    			return true;
	    		}
	    	}
			
	    	
	    	
	    }
	    
	    public boolean requisitiCarta(Giocatore g,Carta_Gioco carta) {
	    	for(int i=0;i<2;i++) {
	    		if(carta.simboliXPosizionamento[i]==Enums.eSimbolo.FUNGO) {
	    			if(g.getContatoreSimboli(0)==simboliXPosizionamentoQuantita[i]) {
	    				return true; //la carta è piazzabile
	    			}else {
	    				return false;
	    			}
	    		}else if(carta.simboliXPosizionamento[i]==Enums.eSimbolo.FOGLIA) {
	    			if(g.getContatoreSimboli(1)==simboliXPosizionamentoQuantita[i]) {
	    				return true; //la carta è piazzabile
	    			}else {
	    				return false;
	    			}
	    		}else if(carta.simboliXPosizionamento[i]==Enums.eSimbolo.LUPO) {
	    			if(g.getContatoreSimboli(2)==simboliXPosizionamentoQuantita[i]) {
	    				return true; //la carta è piazzabile
	    			}else {
	    				return false;
	    			}
	    		}else if(carta.simboliXPosizionamento[i]==Enums.eSimbolo.FARFALLA) {
	    			if(g.getContatoreSimboli(3)==simboliXPosizionamentoQuantita[i]) {
	    				return true; //la carta è piazzabile
	    			}else {
	    				return false;
	    			}
	    		}
	    	}
			return false;
	    		
	    }
}
