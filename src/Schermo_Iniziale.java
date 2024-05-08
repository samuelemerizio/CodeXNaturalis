import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public final class Schermo_Iniziale {

    public Schermo_Iniziale(){

    }

    public void paint(Graphics2D g2d){
        g2d.setColor(Color.yellow);
        g2d.drawString("Pagina iniziale", 200, 400);
    }

    public void mousePressed(MouseEvent e){
        Variabili.schermoAttivo = Enums.eElencoSchermi.SPLASHSCREEN;
    }
}
