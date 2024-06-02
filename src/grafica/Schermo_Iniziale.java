package grafica;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public final class Schermo_Iniziale {
    // Creo i pulsanti per lo Schermo_Iniziale
    private Pulsante pbEsci = new Pulsante(100, Variabili.dyMonitor - 50, 200, 40, "Esci");
    private Pulsante pbInizioPartita = new Pulsante(Variabili.dxMonitor - 100 - 200, Variabili.dyMonitor - 50, 200, 40, "Inizia Partita");


/**
 * Schermata iniziale, consente di accedere alla schermata per la creazione di una nuova partita
 */
    public Schermo_Iniziale(){

    }

    public void paint(Graphics2D g2d){
        // Pitturo l'immagine di sfondo
        g2d.drawImage(Variabili.bkIniziale, 0, 0, null);
        // Pitturo i pulsanti
        pbEsci.draw(g2d);
        pbInizioPartita.draw(g2d);

    }

    public void mousePressed(MouseEvent e){
        // Condizioni per cliccare i pulsanti, verifico se il click è avvenuto nell'area del pulsante
        if (pbEsci.area.contains(e.getX(), e.getY()))
            // Condizione per conoscere se il pulsante è visualizzabile
            if (pbEsci.visibile)
                System.exit(0);
        
        // Condizioni per cliccare i pulsanti, verifico se il click è avvenuto nell'area del pulsante
        if (pbInizioPartita.area.contains(e.getX(), e.getY()))
            // Condizione per conoscere se il pulsante è visualizzabile
            if (pbInizioPartita.visibile){
                // Inizializzo Gioco
                Variabili.partita = new Gioco();
                // Cambio schermata
                Variabili.schermoAttivo = Enums.eElencoSchermi.NUOVAPARTITA;   
            }
    }
}
