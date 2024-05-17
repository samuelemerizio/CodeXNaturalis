import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Canvas;
import java.awt.Color;

/**
 * Classe principale del programma
 */
public class CodeX extends Canvas{

    JFrame tela = new JFrame();
    public static void main(String[] args) throws Exception {
        new CodeX();
        Turno t=new Turno();
        t.turno();
    }
    
    
    public CodeX(){
        Variabili.schermoSplashScreen = new Schermo_SplashScreen();
        Variabili.schermoIniziale = new Schermo_Iniziale();


        //Impostazioni proprietà JFrame
        tela.setUndecorated(false);
        tela.add(this);
        tela.setLocation(0, 0);
        tela.setSize(Variabili.dxMonitor, Variabili.dyMonitor);
        tela.setVisible(true);
        tela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        tela.invalidate();
        this.addMouseListener(new mouseClick());                 //Inizializzo il gestore degli eventi mouse passandolo alla classe Click
        this.addMouseMotionListener(new mouseMoving());          //Inizializzo il gestore degli eventi di movimento del mouse
        
        // Inizializzo variabili doubleBuffering
        Variabili.immagineBack = createImage(Variabili.dxMonitor, Variabili.dyMonitor);
        Variabili.immagineBackGraphics = (Graphics2D)Variabili.immagineBack.getGraphics();
    
    }

    @Override
    public void paint(Graphics g){

        // Se non ho inizializzato lo schermo back esco
        if (Variabili.immagineBack == null)  return ;
        if (Variabili.immagineBackGraphics == null)  return ;

        try{
    
        
            // Imposto il colore di sfondo
            Variabili.immagineBackGraphics.setColor(Color.BLACK);


            Variabili.immagineBackGraphics.fillRect(0, 0, Variabili.dxMonitor, Variabili.dyMonitor);

            
            // Determino quale classe grafica richiamare in funzione dello schermo attivo
            switch (Variabili.schermoAttivo) {
                case SPLASHSCREEN:
                    Variabili.schermoSplashScreen.paint(Variabili.immagineBackGraphics);
                    break;
                case INIZIALE:
                    Variabili.schermoIniziale.paint(Variabili.immagineBackGraphics);
                    break;
                default:
                    break;
            }


            g.drawImage( Variabili.immagineBack, 0, 0, this);
        }
        catch(Exception e){
            System.out.println("ERRORE: " + e.getMessage());
        }
        g.dispose();
    }

    class mouseClick implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println(String.format("Hai cliccato: %d, %d", e.getX(), e.getY()));
            // Determino quale classe grafica richiamare in funzione dello schermo attivo
            switch (Variabili.schermoAttivo) {
                case SPLASHSCREEN:
                    Variabili.schermoSplashScreen.mousePressed(e);
                    break;
                case INIZIALE:
                    Variabili.schermoIniziale.mousePressed(e);
                    break;
                default:
                    break;
            }


            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
           
        }

        @Override
        public void mousePressed(MouseEvent e) {
           
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }
        
    }

    class mouseMoving implements MouseMotionListener{

        @Override
        public void mouseDragged(MouseEvent e) {
           
        }

        @Override
        public void mouseMoved(MouseEvent e) {
         
        }
        
    }



}
