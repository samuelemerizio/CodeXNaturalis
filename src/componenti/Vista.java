package componenti;

import java.util.Scanner;

public class Vista {
	//questa classe gestisce tutti gli input dell'utente nella console durante la partita
	
	private Scanner sc;
	private Controller c;
	
	public Vista(Controller c) {
		sc=new Scanner(System.in);
		this.c=c;
	}
	
	public void leggiAzioni() {
		while(true) {
			String azione=sc.nextLine();
			c.eseguiAzione(azione);
		}
	}
}
