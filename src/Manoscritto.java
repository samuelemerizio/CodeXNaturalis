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
