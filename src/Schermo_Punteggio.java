import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public final class Schermo_Punteggio {
    // Creo i pulsanti per lo Schermo_NuovaPartita
    private Pulsante pbFine = new Pulsante((Variabili.dxTavolo - 300) / 2, Variabili.dyTavolo - 60, 300, 50, "Fine partita");

    public Schermo_Punteggio(){

    }

    public void paint(Graphics2D g2d){
        // Pitturo l'immagine di sfondo
        g2d.drawImage(Variabili.bkMain, 0, 0, Variabili.dxTavolo, Variabili.dyTavolo, null);
        g2d.setColor(new Color(0, 0, 0, 182));
        g2d.fillRect(0, 0, Variabili.dxTavolo, Variabili.dyTavolo);
        // Pitturo il pulsante "Annulla"
        pbFine.draw(g2d);

        Grafica.disegnaTesto(g2d, "CLASSIFICA", (Variabili.dxTavolo / 2), 50, Color.YELLOW, Color.BLACK, 1, 0, 80, true);

        // Disegno i nomi dei giocatori con i relativi punteggi
        int posY = 200;
        Grafica.disegnaTesto(g2d, "Giocatore", 50, posY, Color.YELLOW, Color.BLACK, 0, 0, 40, false);
        Grafica.disegnaTesto(g2d, "Punti", 450, posY, Color.YELLOW, Color.BLACK, 1, 0, 40, false);
        Grafica.disegnaTesto(g2d, "Obiettivi", 700, posY, Color.YELLOW, Color.BLACK, 1, 0, 40, false);
        
        posY += 60;

        for (Funzioni.risultati risultato : Variabili.partita.classifica){
            posY += 60;
            Giocatore gio = Variabili.partita.giocatori.get(risultato.ID);
            String nome = gio.nome;
            int punteggio = gio.punteggio;
            int puntiObiettivoComune1 = gio.puntiObiettivoComune1;
            int puntiObiettivoComune2 = gio.puntiObiettivoComune2;
            int puntiObiettivoSegreto = gio.puntiObiettivoSegreto;
            int obiettiviRaggiunti = risultato.obiettiviRaggiunti;
            int puntiFinali = punteggio + puntiObiettivoComune1 + puntiObiettivoComune2 + puntiObiettivoSegreto;

            Grafica.disegnaTesto(g2d, nome, 50, posY, Color.GRAY, Color.GREEN, 0, 0, 40, false);
            Grafica.disegnaTesto(g2d, String.format("%d", puntiFinali), 450, posY, Color.GRAY, Color.GREEN, 1, 0, 40, false);
            Grafica.disegnaTesto(g2d, String.format("%d", obiettiviRaggiunti), 750, posY, Color.GRAY, Color.GREEN, 1, 0, 40, false);


        }


    }

    public void mousePressed(MouseEvent e){
        // Condizione per verificare se il click è avvenuto sul pulsante
        if (pbFine.area.contains(e.getX(), e.getY()))
            // Condizione per conoscere se il pulsante è visualizzabile
            if (pbFine.visibile)
                // Ritorna allo Schermo_Iniziale se le condizioni sono verificate
                Variabili.schermoAttivo = Enums.eElencoSchermi.INIZIALE;   
    }
}

