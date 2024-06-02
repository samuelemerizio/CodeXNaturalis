//import Enums.eSimbolo;
package console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Rappresenta il giocatore con le sue proprietà, il suo manoscritto ed il suo obiettivo
 */
public class Giocatore {
    /**
     * Identifica in modo univoco il giocatore
     */
    static int ID=0;

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

    private Funzioni f=new Funzioni();
  	
    
    
    
    /**
     * Questo array contiene tutti i contatori dei simboli presenti nella partita
     * ATTENZIONE: segue posizioni Enums.eSimbolo
     * ([0]=FUNGO, [1]=FOGLIA, [2]=LUPO, [3]=FARFALLA, [4]=PIUMA, [5]=VASETTO, [6]=PERGAMENA
     */
	private int[] contatoreSimboli=new int[7];
	
	public int[] initContaSimboli() {
    	for(int i=0; i<contatoreSimboli.length; i++) {
    		//contatoreSimboli[i]=Enums.eSimbolo.NULLO; 
    		contatoreSimboli[i]=0;
    	}
    	return contatoreSimboli;
    }
    


    
public int getContatoreSimboli(int i) {
		return contatoreSimboli[i];
	}




	public void setContatoreSimboli(int posizioneArray) {
		this.contatoreSimboli[posizioneArray]+=1;
	}



	

	



	public Giocatore(String nome, Enums.eColoreGiocatore colore ) {
    	this.nome=nome;
    	this.colore=colore;
    	this.manoscritto=manoscritto;
    	this.ID+=1;
    }
    public Giocatore(String nome) {
    	this.nome=nome;
    	this.manoscritto=manoscritto;
    	this.ID+=1;
    }
    public Giocatore() {
    	
    }
	
	
    
    
    /**
     * Questo metodo mostra le carte che sono nella mano del giocatore
     * @param carteInMano array di carte che ha il giocatore a disposizione da giocare
     */
    public void mostraCarte(Carta_Gioco[] carteInMano) {
    	for(int i=0; i<3; i++) {
    		System.out.println("Carte in mano: "+ carteInMano[i] + ", ");
    	}
    	
    }
    /**
     * Questo metodo consente al giocatore di scegliere e piazzare la carta nel manoscritto
     * @param giocatoreAttuale giocatore che sta giocando il turno al momento
     */
    public void piazzaCarta(Giocatore giocatoreAttuale) {
    	//mostraCarte(carteInMano);
    	Scanner scanner=new Scanner(System.in);
    	System.out.println("Scegliere quale carta piazzare indicandola con un numero" +
    						"da 0 (per la prima) a 2 (per la terza)");
    	
    	
    	
    	int numeroCarta = f.ScansionaNumero();
    	
    	//controllo che la carta scelta sia posizionabile (solo su carte di tipo oro)
    	
    	while(giocatoreAttuale.carteInMano[numeroCarta].tipo.ordinal()==1) {
    		
    		if(giocatoreAttuale.carteInMano[numeroCarta].requisitiCarta(giocatoreAttuale, giocatoreAttuale.carteInMano[numeroCarta])==false) {
    			System.out.println("Carta non soddisfa i requisiti di piazzamento: scegli di giocarla sul retro (retro) o indica un'altra carta nella mano");
    			String scelta=scanner.nextLine();
    			if(scelta.equals("retro")) {
    				giocatoreAttuale.carteInMano[numeroCarta].fronte=false;
    				break;
    			}else {
    				//scansiono un altro numero e ricontrollo se la condizione viene soddisfatta
        			System.out.println("scegliere un'altra carta");
    				numeroCarta=f.ScansionaNumero();
    			}
        	}	
    	}
    	Cella_Manoscritto cartaAggiunta = manoscritto.carte.get(0); //gli assegno la prima carta delle carte piazzate nel manoscritto per evitare errori
    	try {
    		System.out.println("Inserisci ID della carta alla quale agganci la carta scelta:");
    		String IDInserito=scanner.nextLine();
    		
    		System.out.println("Inserici il tipo della carta alla quale agganci la carta scelta\n(0: risorsa, 1: oro, 2: iniziale");
    		String tipoIdInserito=scanner.nextLine();
    		//verifico che l'id inserito coincida con un id presente nella lista delle carte piazzate al momento
    		Cella_Manoscritto cella = giocatoreAttuale.manoscritto.carte.get(0);
    		Enums.eTipoCarta tipoCartaDaControllare=Enums.eTipoCarta.INIZIALE;
    		//converto in string il tipo della carta
    		if(tipoIdInserito.equals("0")) {
    			tipoCartaDaControllare=Enums.eTipoCarta.RISORSA;
    		}else if(tipoIdInserito.equals("1")) {
    			tipoCartaDaControllare=Enums.eTipoCarta.ORO;
    		}else if(tipoIdInserito.equals("2")){
    			tipoCartaDaControllare=Enums.eTipoCarta.INIZIALE;
    		}
    		
    		//controllo che l'id e il tipo della carta selezionati coincidano con la carta nella lista delle carte giocate
    		while(cella.cercaCarta(giocatoreAttuale.manoscritto.carte,IDInserito,tipoCartaDaControllare)==null) {
    			System.out.println("ID inserito non valido, riprova");
    			IDInserito=scanner.nextLine();
    			System.out.println("TIPO inserito non valido, riprova");
    			tipoIdInserito=scanner.nextLine();
    			//converto in string il tipo della carta
        		if(tipoIdInserito.equals("0")) {
        			tipoCartaDaControllare=Enums.eTipoCarta.RISORSA;
        		}else if(tipoIdInserito.equals("1")) {
        			tipoCartaDaControllare=Enums.eTipoCarta.ORO;
        		}else if(tipoIdInserito.equals("2")){
        			tipoCartaDaControllare=Enums.eTipoCarta.INIZIALE;
        		}
    		}
    		int indiceInserito=cella.getIndex(giocatoreAttuale.manoscritto.carte, IDInserito);
    		System.out.println("Inserisci angolo dove agganciare la carta.\nNO: alto-sinistra, NE: alto-sinistra, SE: basso-destra, SO: basso-sinistra");
			String selezioneAngolo=scanner.nextLine();
			
			//ciclo per ripetere la scelta dell'angolo finchè l'angolo scelto non è libero
			//se l'angolo è libero guardo dove si vuole posizionare la carta
			while(giocatoreAttuale.manoscritto.carte.get(indiceInserito).carta.angoloOccupato(giocatoreAttuale,selezioneAngolo,IDInserito)==true) {
				System.out.println("L'angolo scelto è già occupato/insesistente, inserirne un altro");
				selezioneAngolo=scanner.nextLine();	
			}
				if(numeroCarta==0) {
					//seleziono carta 0
					if(selezioneAngolo.equals("NO")) {
						//l'angolo dove si uniscono due carte sarà l'angolo opposto a quello che scelgo
						giocatoreAttuale.carteInMano[0].setAngoloIdCartaBase(Enums.eAngolo.NO);
						//aggiungo la carta alla lista delle carte piazzate aggiornando riga e colonna di piazzamento
						cartaAggiunta=new Cella_Manoscritto(giocatoreAttuale.carteInMano[0],manoscritto,cartaAggiunta.getCoordinataCartaInizialeX()-1,
									cartaAggiunta.getCoordinataCartaInizialeY()-1);
						//segno l'id della carta base a cui si aggancerà questa
						cartaAggiunta.carta.setIdCartaBase(Integer.parseInt(IDInserito));
						//segno il tipo della carta appena selezionata
						cartaAggiunta.carta.setTipoCartaBase(tipoCartaDaControllare);
						giocatoreAttuale.manoscritto.carte.add(cartaAggiunta);
			    		
					}else if(selezioneAngolo.equals("NE")) {
						giocatoreAttuale.carteInMano[0].setAngoloIdCartaBase(Enums.eAngolo.NE);
						cartaAggiunta=new Cella_Manoscritto(giocatoreAttuale.carteInMano[0],manoscritto,cartaAggiunta.getCoordinataCartaInizialeX()-1,
									cartaAggiunta.getCoordinataCartaInizialeY()+1);
						//segno l'id della carta base a cui si aggancerà questa
						cartaAggiunta.carta.setIdCartaBase(Integer.parseInt(IDInserito));
						//segno il tipo della carta appena selezionata
						cartaAggiunta.carta.setTipoCartaBase(tipoCartaDaControllare);
						giocatoreAttuale.manoscritto.carte.add(cartaAggiunta);
						
					}else if(selezioneAngolo.equals("SE")) {
						giocatoreAttuale.carteInMano[0].setAngoloIdCartaBase(Enums.eAngolo.SE);
						cartaAggiunta=new Cella_Manoscritto(giocatoreAttuale.carteInMano[0],manoscritto,cartaAggiunta.getCoordinataCartaInizialeX()+1,
									cartaAggiunta.getCoordinataCartaInizialeY()+1);
						//segno l'id della carta base a cui si aggancerà questa
						cartaAggiunta.carta.setIdCartaBase(Integer.parseInt(IDInserito));
						//segno il tipo della carta appena selezionata
						cartaAggiunta.carta.setTipoCartaBase(tipoCartaDaControllare);
						giocatoreAttuale.manoscritto.carte.add(cartaAggiunta);
					
					}else if(selezioneAngolo.equals("SO")) {
						giocatoreAttuale.carteInMano[0].setAngoloIdCartaBase(Enums.eAngolo.SO);
						cartaAggiunta=new Cella_Manoscritto(giocatoreAttuale.carteInMano[0],manoscritto,cartaAggiunta.getCoordinataCartaInizialeX()+1,
									cartaAggiunta.getCoordinataCartaInizialeY()-1);
						//segno l'id della carta base a cui si aggancerà questa
						cartaAggiunta.carta.setIdCartaBase(Integer.parseInt(IDInserito));
						//segno il tipo della carta appena selezionata
						cartaAggiunta.carta.setTipoCartaBase(tipoCartaDaControllare);
						giocatoreAttuale.manoscritto.carte.add(cartaAggiunta);
						
					}
					
				}else if(numeroCarta==1) {
					//seleziono carta 1
					if(selezioneAngolo.equals("NO")) {
						giocatoreAttuale.carteInMano[1].setAngoloIdCartaBase(Enums.eAngolo.NO);
						cartaAggiunta=new Cella_Manoscritto(giocatoreAttuale.carteInMano[1],manoscritto,cartaAggiunta.getCoordinataCartaInizialeX()-1,
									cartaAggiunta.getCoordinataCartaInizialeY()-1);
						//segno l'id della carta base a cui si aggancerà questa
						cartaAggiunta.carta.setIdCartaBase(Integer.parseInt(IDInserito));
						//segno il tipo della carta appena selezionata
						cartaAggiunta.carta.setTipoCartaBase(tipoCartaDaControllare);
						giocatoreAttuale.manoscritto.carte.add(cartaAggiunta);
					
					}else if(selezioneAngolo.equals("NE")) {
						giocatoreAttuale.carteInMano[1].setAngoloIdCartaBase(Enums.eAngolo.NE);
						cartaAggiunta=new Cella_Manoscritto(giocatoreAttuale.carteInMano[1],manoscritto,cartaAggiunta.getCoordinataCartaInizialeX()-1,
									cartaAggiunta.getCoordinataCartaInizialeY()+1);
						//segno l'id della carta base a cui si aggancerà questa
						cartaAggiunta.carta.setIdCartaBase(Integer.parseInt(IDInserito));
						//segno il tipo della carta appena selezionata
						cartaAggiunta.carta.setTipoCartaBase(tipoCartaDaControllare);
						giocatoreAttuale.manoscritto.carte.add(cartaAggiunta);
					
					}else if(selezioneAngolo.equals("SE")) {
						giocatoreAttuale.carteInMano[1].setAngoloIdCartaBase(Enums.eAngolo.SE);
						cartaAggiunta=new Cella_Manoscritto(giocatoreAttuale.carteInMano[1],manoscritto,cartaAggiunta.getCoordinataCartaInizialeX()+1,
									cartaAggiunta.getCoordinataCartaInizialeY()+1);
						//segno l'id della carta base a cui si aggancerà questa
						cartaAggiunta.carta.setIdCartaBase(Integer.parseInt(IDInserito));
						//segno il tipo della carta appena selezionata
						cartaAggiunta.carta.setTipoCartaBase(tipoCartaDaControllare);
						giocatoreAttuale.manoscritto.carte.add(cartaAggiunta);
						
					}else if(selezioneAngolo.equals("SO")) {
						giocatoreAttuale.carteInMano[1].setAngoloIdCartaBase(Enums.eAngolo.SO);
						cartaAggiunta=new Cella_Manoscritto(giocatoreAttuale.carteInMano[1],manoscritto,cartaAggiunta.getCoordinataCartaInizialeX()+1,
									cartaAggiunta.getCoordinataCartaInizialeY()-1);
						//segno l'id della carta base a cui si aggancerà questa
						cartaAggiunta.carta.setIdCartaBase(Integer.parseInt(IDInserito));
						//segno il tipo della carta appena selezionata
						cartaAggiunta.carta.setTipoCartaBase(tipoCartaDaControllare);
						giocatoreAttuale.manoscritto.carte.add(cartaAggiunta);
				
					}else {
						//la carta selezionata è la 2
						if(selezioneAngolo.equals("NO")) {
							giocatoreAttuale.carteInMano[2].setAngoloIdCartaBase(Enums.eAngolo.NO);
							cartaAggiunta=new Cella_Manoscritto(giocatoreAttuale.carteInMano[2],manoscritto,cartaAggiunta.getCoordinataCartaInizialeX()-1,
									cartaAggiunta.getCoordinataCartaInizialeY()-1);
							//segno l'id della carta base a cui si aggancerà questa
							cartaAggiunta.carta.setIdCartaBase(Integer.parseInt(IDInserito));
							//segno il tipo della carta appena selezionata
							cartaAggiunta.carta.setTipoCartaBase(tipoCartaDaControllare);
							giocatoreAttuale.manoscritto.carte.add(cartaAggiunta);
		
						}else if(selezioneAngolo.equals("NE")) {
							giocatoreAttuale.carteInMano[2].setAngoloIdCartaBase(Enums.eAngolo.NE);
							cartaAggiunta=new Cella_Manoscritto(giocatoreAttuale.carteInMano[2],manoscritto,cartaAggiunta.getCoordinataCartaInizialeX()-1,
									cartaAggiunta.getCoordinataCartaInizialeY()+1);
							//segno l'id della carta base a cui si aggancerà questa
							cartaAggiunta.carta.setIdCartaBase(Integer.parseInt(IDInserito));
							//segno il tipo della carta appena selezionata
							cartaAggiunta.carta.setTipoCartaBase(tipoCartaDaControllare);
							giocatoreAttuale.manoscritto.carte.add(cartaAggiunta);
			
						}else if(selezioneAngolo.equals("SE")) {
							giocatoreAttuale.carteInMano[2].setAngoloIdCartaBase(Enums.eAngolo.SE);
							cartaAggiunta=new Cella_Manoscritto(giocatoreAttuale.carteInMano[2],manoscritto,cartaAggiunta.getCoordinataCartaInizialeX()+1,
									cartaAggiunta.getCoordinataCartaInizialeY()+1);
							//segno l'id della carta base a cui si aggancerà questa
							cartaAggiunta.carta.setIdCartaBase(Integer.parseInt(IDInserito));
							//segno il tipo della carta appena selezionata
							cartaAggiunta.carta.setTipoCartaBase(tipoCartaDaControllare);
							giocatoreAttuale.manoscritto.carte.add(cartaAggiunta);
		
						}else if(selezioneAngolo.equals("SO")) {
							giocatoreAttuale.carteInMano[2].setAngoloIdCartaBase(Enums.eAngolo.SO);
							cartaAggiunta=new Cella_Manoscritto(giocatoreAttuale.carteInMano[2],manoscritto,cartaAggiunta.getCoordinataCartaInizialeX()+1,
									cartaAggiunta.getCoordinataCartaInizialeY()-1);
							//segno l'id della carta base a cui si aggancerà questa
							cartaAggiunta.carta.setIdCartaBase(Integer.parseInt(IDInserito));
							//segno il tipo della carta appena selezionata
							cartaAggiunta.carta.setTipoCartaBase(tipoCartaDaControllare);
							giocatoreAttuale.manoscritto.carte.add(cartaAggiunta);
						}
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    int puntiObiettivoSegreto = 0;          // Contiene il numero di punti ottenuti dall'obiettivo segreto del giocatore
	
}
