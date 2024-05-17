import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Turno {

	Giocatore g=new Giocatore();
	private Boolean primoTurno=true;
	private Funzioni f=new Funzioni();
	private List<Carta_Gioco> mazzoCarteOro=new ArrayList<Carta_Gioco>();
	private List<Carta_Gioco> mazzoCarteRisorsa=new ArrayList<Carta_Gioco>();
	private List<Carta_Gioco> mazzoCarteIniziali=new ArrayList<Carta_Gioco>();
	private Carta_Obiettivo[] mazzoCarteObiettivo = new Carta_Obiettivo[16];
	
	
	/*
	 * 
	 * 1. mostra carte in mano (classe del giocatore)
	 * 2. piazzi la carta sul tavolo -->se piazzi carta oro bisogna controllare se può essere piazzata (da classe giocatore)
	 * 3. mostra carte nel mercato
	 * 4. peschi dal mercato (due scelte o mazzo o mercato)
 	 * 5. aggiornamento dei contatori simboli piazzati (da classe giocatore)
 	 * 6. aggiornamento conteggio punti del giocatore (da classe giocatore)
	 */
	
	public  void turno() throws Exception { //dato l'id del giocatore viene svolto il turno
		//ciclo dei turni durante tutta la partita
		/*
		 * questo if è valido solo al primo turno quando vengono inizializzati tutti i parametri
		 * in uso durante il gioco.
		 * Verranno inizializzate tutte le carte
		 * Verranno messe sul mercato le carte
		 * Verranno assegnate a ogni giocatore le proprie carte iniziali e l'obiettivo segreto
		 */
		
		if(primoTurno=true) {
			this.mazzoCarteObiettivo=f.initCarteObiettivo();
			this.mazzoCarteIniziali=f.initCarteIniziali();
			this.mazzoCarteOro=f.initCarteOro();
			this.mazzoCarteRisorsa=f.initCarteRisorsa();
			primoTurno=false;
		}
		//il giocatore inizia scegliendo dove piazzare una tra le carte in mano
		g.mostraCarte(g.carteInMano);
		g.piazzaCarta();
		//il giocatore deve pescare dal mercato la carta
		
		
	}
}
