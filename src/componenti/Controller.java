package componenti;

public class Controller {

	private Gioco g;
	private Vista v;
	private Turno t;
	

	//questa classe istanzia il gioco 
	
	public Controller() {
		g=new Gioco();
		v=new Vista(this);
	}
	
	public void gioca() { //chiedi 
		//chiedi in input i giocatori
		//estri chi parte (evitiamo) 
		//nuovo turno
		System.out.println("inserisci Giocatori:");
		t.turno();
		v.leggiAzioni();
		
	}
	
	public void eseguiAzione(String azione) {
		g.eseguiAzione();
	}
}
