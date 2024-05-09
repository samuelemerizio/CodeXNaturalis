import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public final class Schermo_SplashScreen {

    public Schermo_SplashScreen(){

    }

    public void paint(Graphics2D g2d){
        g2d.setColor(Color.yellow);
        g2d.drawString("Pagina splashscreen", 400, 400);
    }

    public void mousePressed(MouseEvent e){
        Variabili.schermoAttivo = Enums.eElencoSchermi.INIZIALE;
    }
}
