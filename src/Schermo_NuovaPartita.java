import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public final class Schermo_NuovaPartita {
    // Creo i pulsanti per lo Schermo_NuovaPartita
    private Pulsante pbAnnulla = new Pulsante(20, Variabili.dyTavolo - 50, 200, 40, "Annulla");
    private Pulsante pbAvviaGioco = new Pulsante(Variabili.dxTavolo - 20 - 200, Variabili.dyTavolo - 50, 200, 40, "Avvio gioco");
    private Pulsante pbNuovoGiocatore = new Pulsante((Variabili.dxTavolo - 260) / 2  , 50, 260, 40, "Crea giocatore");


    public Schermo_NuovaPartita(){

    }

    public void paint(Graphics2D g2d){
        // Pitturo l'immagine di sfondo
        g2d.drawImage(Variabili.bkMain, 0, 0, Variabili.dxTavolo, Variabili.dyTavolo, null);
        g2d.setColor(new Color(0, 0, 0, 182));
        g2d.fillRect(0, 0, Variabili.dxTavolo, Variabili.dyTavolo);
        // Pitturo il pulsante "Annulla"
        pbAnnulla.draw(g2d);

        // Condizioni per la visualizzazione del pulsante "Nuovo Giocatore"
        pbNuovoGiocatore.visibile = (Variabili.partita.giocatori.size() < 4);
        // Pitturo il pulsante "Nuovo Giocatore" se la condizione è verificata
        pbNuovoGiocatore.draw(g2d);

        // Condizioni per la visualizzazione del pulsante "Avvio Gioco"
        pbAvviaGioco.visibile = (Variabili.partita.giocatori.size() > 1);
        // Pitturo il pulsante "Avvio Gioco" se la condizione è verificata
        pbAvviaGioco.draw(g2d);

        if (Variabili.partita.giocatori.size() > 0) {
			drawGiocatore(g2d, 0);
		}
		if (Variabili.partita.giocatori.size() > 1) {
			drawGiocatore(g2d, 1);
		}
		if (Variabili.partita.giocatori.size() > 2) {
			drawGiocatore(g2d, 2);
		}
		if (Variabili.partita.giocatori.size() > 3) {
			drawGiocatore(g2d, 3);
		}

    }

    public void mousePressed(MouseEvent e){
        // Condizione per verificare se il click è avvenuto sul pulsante
        if (pbAnnulla.area.contains(e.getX(), e.getY()))
            // Condizione per conoscere se il pulsante è visualizzabile
            if (pbAnnulla.visibile)
                // Ritorna allo Schermo_Iniziale se le condizioni sono verificate
                Variabili.schermoAttivo = Enums.eElencoSchermi.INIZIALE;   
        // Condizione per verificare se il click è avvenuto sul pulsante
        if (pbNuovoGiocatore.area.contains(e.getX(), e.getY()))
            // Condizione per conoscere se il pulsante è visualizzabile
            if (pbNuovoGiocatore.visibile){
                Variabili.schermoNuovoGiocatore.caricaImmaginiPedine();
                Variabili.schermoNuovoGiocatore.presetGiocatore();
                // Porta allo Schermo_NuovoGiocatore se le condizioni sono verificate
                Variabili.schermoAttivo = Enums.eElencoSchermi.NUOVOGIOCATORE; 
            }
            if (pbAvviaGioco.area.contains(e.getX(), e.getY()))
                if (pbAvviaGioco.visibile){
                    Variabili.schermoGioco.init();
                    Variabili.partita.giocoInCorso = true;
                    Variabili.partita.assegnaCarteGioco();
                    Variabili.schermoAttivo = Enums.eElencoSchermi.GIOCO;
                }
    }

    private void drawGiocatore(Graphics2D g2d, int numeroGiocatore)
	{
		//Ottengo i dati del giocatore
		Giocatore giocatore = Variabili.partita.giocatori.get(numeroGiocatore);
		int angolo = 0;
		int angoloTesto = 0;
		//Ricavo a quale angolo devo porre il nome
		switch (giocatore.colore)
		{
			case ROSSO:
				angolo = 45;
				angoloTesto = -45;
				break;
			case VERDE:
				angolo = -135;
				angoloTesto = 135;
				break;
			case BLU:
				angolo = -45;
				angoloTesto = 225;
				break;
			case GIALLO:
				angolo = 135;
				angoloTesto = 45;
				break;
		}
		double raggio = 350;
		double x = Math.cos(Math.toRadians(angolo)) * raggio + Variabili.dxTavolo / 2;
		double y = Math.sin(Math.toRadians(angolo)) * raggio + Variabili.dyTavolo / 2;

		Grafica.disegnaTesto(g2d, giocatore.nome, (int)x, (int)y, Color.BLACK, Color.WHITE, 1, 1, angoloTesto);

	}
}

