import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;


public final class Schermo_SceltaCartaObiettivo {
    // Creo i pulsanti per lo Schermo_NuovoGiocatore
    private Pulsante pbEsci = new Pulsante(100, Variabili.dyTavolo - 50, 200, 40, "Indietro");
    private Pulsante pbSalvaGiocatore = new Pulsante(Variabili.dxTavolo - 300 - 20, Variabili.dyTavolo - 50, 300, 40, "Salva giocatore");
    private Pulsante pbOb1 = new Pulsante(270, 150, 500 * 3 / 5, 330 * 3 / 5, "");
    private Pulsante pbOb2 = new Pulsante(270, 400, 500 * 3 / 5, 330 * 3 / 5, "");

    public Schermo_SceltaCartaObiettivo(){
       
    }

    public void paint(Graphics2D g2d){
        // Pitturo immagine di sfondo 
        g2d.drawImage(Variabili.bkMain, 0, 0, Variabili.dxTavolo, Variabili.dyTavolo, null);
        g2d.setColor(new Color(0, 0, 0, 182));
        g2d.fillRect(0, 0, Variabili.dxTavolo, Variabili.dyTavolo);
        // Pitturo i pulsanti
        pbEsci.draw(g2d);
        pbSalvaGiocatore.draw(g2d);

        Grafica.disegnaTesto(g2d, "Selezionare carta obiettivo segreto", Variabili.dxTavolo / 2, 50, Color.BLACK, Color.WHITE, 1, 1, 30, false);
		
		Grafica.evidenziaCarta(g2d,  Variabili.giocatoreInCreazione.ob1 ? pbOb1.area : pbOb2.area, new Color(0,255,0,128), 12);

        Grafica.drawCartaObiettivo(g2d, Variabili.mazzoCarteObiettivo[Variabili.giocatoreInCreazione.ID_carteObiettivoDaScegliere[0]], pbOb1.area, new Color(0, 0, 0, 0), new Color(0, 0, 0, 0));
        Grafica.drawCartaObiettivo(g2d, Variabili.mazzoCarteObiettivo[Variabili.giocatoreInCreazione.ID_carteObiettivoDaScegliere[1]], pbOb2.area, new Color(0, 0, 0, 0), new Color(0, 0, 0, 0));
        

    }

    public void mousePressed(MouseEvent e){
        if (pbEsci.area.contains(e.getX(), e.getY()))
            if (pbEsci.visibile)
                Variabili.schermoAttivo = Enums.eElencoSchermi.SCELTACARTAINIZIALE;   


        if (pbSalvaGiocatore.area.contains(e.getX(), e.getY()))
            if (pbSalvaGiocatore.visibile){
                // Salva il giocatore con le sue scelte

                // Memorizzo carta iniziale se ho selezionato il retro
                if (!Variabili.giocatoreInCreazione.fronte)
                    Variabili.giocatoreInCreazione.cartaIniziale = Variabili.partita.ottieniCartaInizialeDaID(Variabili.giocatoreInCreazione.cartaIniziale.ID + 6);
                
                // Salvo l'obiettivo scelto dal giocatore
                if (Variabili.giocatoreInCreazione.ob1)
                    Variabili.giocatoreInCreazione.cartaObiettivo = Variabili.mazzoCarteObiettivo[Variabili.giocatoreInCreazione.ID_carteObiettivoDaScegliere[0]];
                else
                    Variabili.giocatoreInCreazione.cartaObiettivo = Variabili.mazzoCarteObiettivo[Variabili.giocatoreInCreazione.ID_carteObiettivoDaScegliere[1]];

                // Confermo la scelta di ID iniziale e ID Obiettivo
                Variabili.partita.obiettivoSalva(Variabili.giocatoreInCreazione.ID, Variabili.giocatoreInCreazione.cartaObiettivo.ID);
                Variabili.partita.rimuoviCartaIniziale(Variabili.giocatoreInCreazione.cartaIniziale.ID);

                // Aggiungo questo giocatore alla partita
                Variabili.partita.giocatori.add(Variabili.giocatoreInCreazione);
                Variabili.giocatoreInCreazione = new Giocatore();

                Variabili.schermoAttivo = Enums.eElencoSchermi.NUOVAPARTITA;   
            }

        if (pbOb1.area.contains(e.getX(), e.getY())){
             // Evidenzio carta ob1
             Variabili.giocatoreInCreazione.ob1 = true;
        }
        if (pbOb2.area.contains(e.getX(), e.getY())){
            // Evidenzio carta ob2
            Variabili.giocatoreInCreazione.ob1 = false;
        }
                
        
    }
}
