import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;


public final class Schermo_SceltaCartaIniziale {
    // Creo i pulsanti per lo Schermo_NuovoGiocatore
    private Pulsante pbEsci = new Pulsante(100, Variabili.dyTavolo - 50, 200, 40, "Indietro");
    private Pulsante pbSelezioneCartaObiettivo = new Pulsante(Variabili.dxTavolo - 50 - 250, Variabili.dyTavolo - 50, 250, 40, "Scelta obiettivo");
    private Pulsante pbFronte = new Pulsante(270, 150, 500 * 3 / 5, 330 * 3 / 5, "");
    private Pulsante pbRetro = new Pulsante(270, 400, 500 * 3 / 5, 330 * 3 / 5, "");


    public Schermo_SceltaCartaIniziale(){
       
    }

    public void paint(Graphics2D g2d){
        // Pitturo immagine di sfondo 
        g2d.drawImage(Variabili.bkMain, 0, 0, Variabili.dxTavolo, Variabili.dyTavolo, null);
        g2d.setColor(new Color(0, 0, 0, 182));
        g2d.fillRect(0, 0, Variabili.dxTavolo, Variabili.dyTavolo);
        // Pitturo i pulsanti
        pbEsci.draw(g2d);
        pbSelezioneCartaObiettivo.draw(g2d);

        Grafica.disegnaTesto(g2d, "Selezionare carta iniziale", Variabili.dxTavolo / 2, 50, Color.BLACK, Color.WHITE, 1, 1, 20, false);
		
		Grafica.evidenziaCarta(g2d,  Variabili.giocatoreInCreazione.fronte ? pbFronte.area : pbRetro.area, new Color(0,255,0,128), 12);

        Grafica.drawCartaGioco(g2d, Variabili.giocatoreInCreazione.cartaIniziale, pbFronte.area);
        Grafica.drawCartaGioco(g2d, Variabili.partita.ottieniCartaInizialeDaID(Variabili.giocatoreInCreazione.cartaIniziale.ID + 6), pbRetro.area);
        

    }

    public void mousePressed(MouseEvent e){
        if (pbEsci.area.contains(e.getX(), e.getY()))
            if (pbEsci.visibile)
                Variabili.schermoAttivo = Enums.eElencoSchermi.NUOVOGIOCATORE;   


        if (pbSelezioneCartaObiettivo.area.contains(e.getX(), e.getY()))
            if (pbSelezioneCartaObiettivo.visibile){

                Variabili.schermoAttivo = Enums.eElencoSchermi.SCELTACARTAOBIETTIVO;   
            }
        if (pbFronte.area.contains(e.getX(), e.getY())){
            // Evidenzio carta fronte
            Variabili.giocatoreInCreazione.fronte = true;
        }
        if (pbRetro.area.contains(e.getX(), e.getY())){
            // Evidenzio carta retro
            Variabili.giocatoreInCreazione.fronte = false;
        }
    }
}
