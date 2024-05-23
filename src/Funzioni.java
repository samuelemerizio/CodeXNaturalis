import java.awt.Point;
import java.util.Comparator;


/**
 * Funzioni utili nel programma
 */
public class Funzioni {
	
    public static void mostraMessaggioErrore(String messaggio){
        System.out.println(messaggio);
    }

    /**
	 * Restituisce la posizione dell'angolo in alto a sinistra della carta data la riga e colonna del campo di gioco
	 * @param riga
	 * @param colonna
	 * @return
	 */
	public static Point coordinateCarta(int riga, int colonna)
	{
		Point punto = new Point();
		punto.x = (int)(colonna * Variabili.carta.stepX - Variabili.carta.dx / 2);
		punto.y = (int)(riga * Variabili.carta.stepY - Variabili.carta.dy / 2);
		return punto;
	}

	/**
	 * Classe per la registrazione dei risultati
	 */
	public static class risultati{
		int punti;
		int ID;
		int obiettiviRaggiunti;
		
		// Costruttore della classe risultati
		public risultati(int punti, int ID, int obiettiviRaggiunti){
			this.punti = punti;
			this.ID = ID;
			this.obiettiviRaggiunti = obiettiviRaggiunti;
		}
	}

	// Comparatore per la classe risultati
	public static class comparatoreRisultati implements Comparator<risultati>{
		@Override
		public int compare (risultati r1, risultati r2){
			if (r1.punti > r2.punti) return -1;
			if (r1.punti < r2.punti) return 1;

			if (r1.obiettiviRaggiunti > r2.obiettiviRaggiunti) return -1;
			if (r1.obiettiviRaggiunti < r2.obiettiviRaggiunti) return 1;

			return 0;
		}
	}
}
