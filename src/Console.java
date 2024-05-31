import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Console {
	private static int maxGiocatori=0;
	private String inserimento="";
	
	private Gioco gioco=new Gioco("");
	
	private Funzioni f=new Funzioni();
	private Scanner sc=new Scanner(System.in);
	private Carta_Gioco cartaIniziale;
	private Carta_Obiettivo cartaObiettivo;
	private Variabili variabili;
	private int[] sceltaObiettivi=new int[2];
	
	


	public void Console() {
		
		System.out.println("inserire true per inserimento giocatori"+
				"\n N.B: inserire almeno 2 giocatori");
		inserimento=sc.nextLine();
		while(!inserimento.equals("true")) {
			System.out.println("devi inserire true per cominciare");
			inserimento=sc.nextLine();
		}
		while(inserimento.equals("true")){
			
			System.out.println("Inserire il nome del giocatore:");
			String input=sc.nextLine();
			gioco.giocatori.add(new Giocatore(input));
			maxGiocatori+=1;
			inserimento="";
				
			if(maxGiocatori<2) {
				System.out.println("devi inserire almeno un altro giocatore prima di cominciare la partita, inserisci true");
				inserimento=sc.nextLine();
				while(!inserimento.equals("true")) {
					System.out.println("inserire true");
					inserimento=sc.nextLine();
				}
				continue;	
				}else if(maxGiocatori==4){
					break;			
				}
				System.out.println("Inserisci true se vuoi continuare a inserire altri giocatori");
				inserimento=sc.nextLine();
		}
		
		
		try {
			initPartita();
			gioco.turno();
			gioco.stampaClassifica();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initPartita() throws IOException {
		//controllo che non ci siano eccezioni nella fase di inizializzazione delle carte
		try {
			Variabili.mazzoCarteInizialiOriginali=f.initCarteIniziali();
			Variabili.mazzoCarteRisorsaOriginali=f.initCarteRisorsa();
			Variabili.mazzoCarteOroOriginali=f.initCarteOro();
			gioco.initCarteObiettivi();
			gioco.setMazzoCarteIniziali(Variabili.mazzoCarteInizialiOriginali);
			gioco.setMazzoCarteRisorsa(Variabili.mazzoCarteRisorsaOriginali);
			gioco.setMazzoCarteOro(Variabili.mazzoCarteOroOriginali);
			//gioco.setMazzoCarteObiettivo(Variabili.mazzoCarteObiettivo);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		gioco.mercato.mescolaMazziCarteRisorseOro(Variabili.mazzoCarteRisorsaOriginali,Variabili.mazzoCarteOroOriginali);
		//assegno a ogni giocatore la carta iniziale e faccio scegliere se giocarla sul fronte o sul retro
		System.out.println("Selezione delle carte iniziali");
		for(int i=0;i<gioco.giocatori.size();i++) {
			//inizializzo manoscritto.griglia di ogni giocatore e i contatori dei simboli nelle carte piazzate
			for(int j=0;j<gioco.giocatori.get(i).manoscritto.griglia.length;j++) {
				for(int k=0;k<gioco.giocatori.get(i).manoscritto.griglia.length;k++) {
					gioco.giocatori.get(i).manoscritto.griglia[j][k]=0;
					gioco.giocatori.get(i).initContaSimboli();
				}
			}for(int j=0;j<gioco.giocatori.get(i).manoscritto.rettangoloStampa.length;j++) {
				for(int k=0;k<gioco.giocatori.get(i).manoscritto.rettangoloStampa.length;k++) {
					gioco.giocatori.get(i).manoscritto.rettangoloStampa[j][k]=-1;
				}
			}
			
			
			System.out.println(gioco.getNome(gioco.giocatori.get(i))+", scegli se iniziare con il fronte (scrivere true) o retro (scrivere false) ");
			
			//estrazione carta iniziale
			cartaIniziale=gioco.estraiCartaIniziale();
			//gioco.rimuoviCartaIniziale(cartaIniziale.ID);
			//ciclo per trovare e eliminare la carta appena estratta dal mazzo
			for(int j=0;j<gioco.getMazzoCarteIniziali().size();j++) {
				if(cartaIniziale.ID==gioco.getMazzoCarteIniziali().get(j).ID) {
					gioco.getMazzoCarteIniziali().remove(j);
				}
			}
			
			System.out.println("\nCarta iniziale: ");
			//vengono fatte vedere le due facce delle carte
			cartaIniziale.toString(cartaIniziale, true);
			cartaIniziale.toString(cartaIniziale, false);
			
			if(sc.nextLine().equals("true")){
				//la carta iniziale viene giocata sul fronte e assegnata al giocatore
				cartaIniziale.fronte=true;
				gioco.giocatori.get(i).cartaIniziale=cartaIniziale;
				Cella_Manoscritto cellaCartaIniziale=new Cella_Manoscritto(cartaIniziale,gioco.giocatori.get(i).manoscritto,20,20);
				gioco.giocatori.get(i).manoscritto.carte.add(cellaCartaIniziale);
			}else if(sc.nextLine().equals("false")) {
				//la carta iniziale viene giocata sul retro e assegnata al giocatore
				cartaIniziale.fronte=false;
				gioco.giocatori.get(i).cartaIniziale=cartaIniziale;
				Cella_Manoscritto cellaCartaIniziale=new Cella_Manoscritto(cartaIniziale,gioco.giocatori.get(i).manoscritto,20,20);
				gioco.giocatori.get(i).manoscritto.carte.add(cellaCartaIniziale);
			}else {
				throw new IOException(); //viene lanciata un eccezione se la scelta non corrisponde a nessuna delle 2 opzioni
			}
			
			//la carta iniziale va posizionata nel manoscritto
			
			//scelta obiettivo segreto per ogni giocatore
		
						
			//mostro le due possibili scelte
			for(int j=0;j<sceltaObiettivi.length;j++) {
				sceltaObiettivi=gioco.obiettivoGenera();
				int idGiocatore=gioco.giocatori.get(gioco.giocatoreAttuale).ID;

				sceltaObiettivi[j]=gioco.obiettivoAssegna(idGiocatore);
				
				cartaObiettivo=gioco.CercaCartaObiettivo(sceltaObiettivi[j]);
				cartaObiettivo.toString(cartaObiettivo);
			}
			if(sc.nextLine().equals("0")) {
				//l'obiettivo scelto è il primo mostrato
				gioco.giocatori.get(i).cartaObiettivo=gioco.CercaCartaObiettivo(sceltaObiettivi[0]);
			}else if(sc.nextLine().equals("1")) {
				//l'obiettivo scelto è il secondo mostrato
				gioco.giocatori.get(i).cartaObiettivo=gioco.CercaCartaObiettivo(sceltaObiettivi[1]);
			}
			
		}
		gioco.mercato.mescolaMazziCarteRisorseOro(gioco.getMazzoCarteRisorsa(), gioco.getMazzoCarteOro());
		gioco.obiettiviComuni[0] = gioco.obiettivoAssegna(5);
        gioco.obiettiviComuni[1] = gioco.obiettivoAssegna(6);
        gioco.mercato.carteRisorsa[0] = gioco.pescaCartaGioco(gioco.mercato.mazzoCarteRisorse);
        gioco.mercato.carteRisorsa[1] = gioco.pescaCartaGioco(gioco.mercato.mazzoCarteRisorse);
        gioco.mercato.carteOro[0] = gioco.pescaCartaGioco(gioco.mercato.mazzoCarteOro);
        gioco.mercato.carteOro[1] = gioco.pescaCartaGioco(gioco.mercato.mazzoCarteOro);
        gioco.ultimoTurno = false;
		
        //assegnazione delle carte in mano a ogni giocatore + posizionamento carta iniziale nel manoscritto
		for(int i=0;i<gioco.giocatori.size();i++) {
			gioco.giocatori.get(i).manoscritto.initGriglia();
		}
		
        gioco.assegnaCarteGioco();
        
        
		
		
	}

}
