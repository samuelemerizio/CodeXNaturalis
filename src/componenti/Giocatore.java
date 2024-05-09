package componenti;
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
    
    int[] conteggioSimboliPiazzati=new int[7]; //serve per tenere il conto dei simboli piazzati durante la partita
    											//segue l'ordine di dichiarazione esimboli in enums

    
    /**
     * Questo metodo mostra le carte che sono nella mano del giocatore
     * @param carteInMano array di carte che ha il giocatore a disposizione da giocare
     */
    public void mostraCarte(Carta_Gioco[] carteInMano) {
    	for(int i=0; i<3; i++) {
    		System.out.println("Carte in mano: "+ carteInMano[i] + ", ");
    	}
    	
    }
    
    public void piazzaCarta() {
    	mostraCarte(carteInMano);
    	System.out.println("Scegliere quale carta piazzare indicandola con un numero" +
    						"da 0 (per la prima) a 2 (per la terza)");
    	Funzioni f = null;
    	int numeroCarta = f.ScansionaNumero();
    	for(int i=0; i<3; i++) {
    		if(i==numeroCarta) {
    			
    			if(cartaIniziale.cercaCarta(numeroCarta).equals("Risorsa")) {
    				System.out.println("Seleziona dove vuoi piazzare la carta");
    			}else { //la carta è oro
    				if(cartaIniziale.controllaCondizione(numeroCarta)== true) { //come parametro ci deve essere id carta per capire che condizione deve essere verificata
    					System.out.println("Selezione dove vuoi piazzare la carta");
    					int selezione=f.ScansionaNumero();
    					manoscritto.selezionaCarta(selezione);
    					while(manoscritto.angoloSceltoLibero()!= true) {
    						System.out.println("Angolo occupato, scegline un altro!");
    						manoscritto.posiziona(selezione);
    					}
    				}
    			}
    		}else {
    			System.out.println("La carta non è in mano");
    		}
    	}
    }
    
    public void contaSimboliPiazzati() {
    	
    }
}
