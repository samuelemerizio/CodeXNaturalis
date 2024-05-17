import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;


public final class Schermo_SplashScreen {
	public String messaggioCaricamento = "";
    // Creo i pulsanti per Schermo_SplashScreen
	private Pulsante pbAvanti = new Pulsante((Variabili.dxMonitor - 100)/2, 700, 100, 50, "Avanti");
	private Pulsante pbChiudi = new Pulsante(Variabili.dxMonitor - 100, 0, 100, 50, "Chiudi");

    public Schermo_SplashScreen(){

    }

    public void paint(Graphics2D g2d){
        // Imposto il colore della scritta per il messaggio di caricamento
		g2d.setColor(Color.yellow);
        //Carico l'immagine di sfondo se presente
        if (Variabili.bkIniziale == null){
            g2d.drawString("Sto caricando lo sfondo", 400, 400);  
        } else {
            // Pitturo l'immagine di sfondo e il messaggio di caricamento
            g2d.drawImage(Variabili.bkIniziale, 0, 0, null);
            g2d.drawString(messaggioCaricamento, 400, 50);  
            // Pitturo i pulsanti
			pbAvanti.draw(g2d);
            pbChiudi.draw(g2d);
        }

    }



    public void mousePressed(MouseEvent e){
        // Condizione per verificare se il click è avvenuto sul pulsante
        if (pbAvanti.area.contains(e.getX(), e.getY()))
            // Condizione per conoscere se il pulsante è visualizzabile
            if (pbAvanti.visibile)
                // Porta allo schermo iniziale se le condizioni sono verificate
                Variabili.schermoAttivo = Enums.eElencoSchermi.INIZIALE;

        // Condizione per verificare se il click è avvenuto sul pulsante
        if (pbChiudi.area.contains(e.getX(), e.getY()))
            // Condizione per conoscere se il pulsante è visualizzabile      
            if (pbChiudi.visibile)
                // Chiude il programma se le condizioni sono verificate
                System.exit(0);   
    }


}
