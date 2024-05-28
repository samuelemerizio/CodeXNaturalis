import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Canvas;
import java.awt.Color;

/**
 * Classe principale del programma
 */
public class CodeX extends Canvas{
    JFrame tela = new JFrame();
    boolean splashCaricata = false;

    public static void main(String[] args) throws Exception {
        new CodeX();
    }
    
    
    public CodeX(){
        // Inizializzo le schermate
        Variabili.schermoSplashScreen = new Schermo_SplashScreen();
        Variabili.schermoIniziale = new Schermo_Iniziale();
        Variabili.schermoNuovaPartita = new Schermo_NuovaPartita();
        Variabili.schermoMercato = new Schermo_Mercato();
        Variabili.schermoNuovoGiocatore = new Schermo_NuovoGiocatore();
        Variabili.schermoSceltaCartaIniziale = new Schermo_SceltaCartaIniziale();
        Variabili.schermoSceltaCartaObiettivo = new Schermo_SceltaCartaObiettivo();
        Variabili.schermoGioco = new Schermo_Gioco();
        Variabili.schermoPunteggio = new Schermo_Punteggio();



        //Impostazioni proprietà JFrame
        tela.setUndecorated(true);
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

        caricaFileImmagini();

        //Genero un timer che rinfresca l'immagine nella pagina SPLASHSCREEN
        Timer timerAvvio = new Timer();
        TimerTask taskAvvio = new TimerTask() {
            @Override
            public void run(){
                repaint();
                if (Variabili.immaginiCaricate){
                    Variabili.schermoAttivo = Enums.eElencoSchermi.INIZIALE;
                    repaint();
                    timerAvvio.cancel();
                }
            }
        };

        //Genero un timer che rinfresca l'immagine nella pagina SPLASHSCREEN
        Timer timerSplash = new Timer();
        TimerTask taskSplash = new TimerTask() {
            @Override
            public void run(){
                repaint();
                if (splashCaricata)
                {
                    caricaGliAltriFileImmagine();
                    timerSplash.cancel();   
                    timerAvvio.schedule(taskAvvio, 100);
                }
            }
        };
        timerSplash.schedule(taskSplash, 100);  
    }

    public void caricaFileImmagini(){
        try{
            Variabili.bkIniziale = ImageIO.read(ClassLoader.getSystemResource("assets/MainBig.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        repaint();
        splashCaricata = true;
    }

    public void caricaGliAltriFileImmagine(){
        try{
            repaint();
            Variabili.bkMain = ImageIO.read(ClassLoader.getSystemResource("assets/Main.png"));
            Variabili.schermoSplashScreen.messaggioCaricamento = "Caricamento immagini in corso";
            repaint();
            Variabili.imgTavolo = ImageIO.read(ClassLoader.getSystemResource("assets/Tavolo.png"));
            Variabili.imgTavolo2 = ImageIO.read(ClassLoader.getSystemResource("assets/Tavolo2.png"));
            Variabili.imgZoom0 = ImageIO.read(ClassLoader.getSystemResource("assets/zoom1.png"));
            Variabili.imgZoom1 = ImageIO.read(ClassLoader.getSystemResource("assets/zoom2.png"));
            Variabili.imgZoom2 = ImageIO.read(ClassLoader.getSystemResource("assets/zoom4.png"));
            repaint();
            Variabili.imgSfondoMercato = ImageIO.read(ClassLoader.getSystemResource("assets/SfondoMercato.png"));
            Variabili.imgTracciatoSegnapunti = ImageIO.read(ClassLoader.getSystemResource("assets/TracciatoSegnapunti.png"));
            repaint();
            Variabili.imgCartaRisorsaRossaRetro = ImageIO.read(ClassLoader.getSystemResource("assets/CarteRisorsa/risorseRetroRosso.png"));
            Variabili.imgCartaRisorsaVerdeRetro = ImageIO.read(ClassLoader.getSystemResource("assets/CarteRisorsa/risorseRetroVerde.png"));
            Variabili.imgCartaRisorsaBluRetro = ImageIO.read(ClassLoader.getSystemResource("assets/CarteRisorsa/risorseRetroBlu.png"));
            Variabili.imgCartaRisorsaViolaRetro = ImageIO.read(ClassLoader.getSystemResource("assets/CarteRisorsa/risorseRetroViola.png"));
            repaint();
            Variabili.imgCartaOroRossaRetro = ImageIO.read(ClassLoader.getSystemResource("assets/CarteOro/oroRetroRosso.png"));
            Variabili.imgCartaOroVerdeRetro = ImageIO.read(ClassLoader.getSystemResource("assets/CarteOro/oroRetroVerde.png"));
            Variabili.imgCartaOroBluRetro = ImageIO.read(ClassLoader.getSystemResource("assets/CarteOro/oroRetroBlu.png"));
            Variabili.imgCartaOroViolaRetro = ImageIO.read(ClassLoader.getSystemResource("assets/CarteOro/oroRetroViola.png"));
            repaint();
            Variabili.imgCartaObiettivoRetro = ImageIO.read(ClassLoader.getSystemResource("assets/CarteObiettivo/obiettivoRetro.png"));
            repaint();
            Variabili.imgPedine[0] = ImageIO.read(ClassLoader.getSystemResource("assets/Pedine/rossa.png"));
            Variabili.imgPedine[1] = ImageIO.read(ClassLoader.getSystemResource("assets/Pedine/verde.png"));
            Variabili.imgPedine[2] = ImageIO.read(ClassLoader.getSystemResource("assets/Pedine/blu.png"));
            Variabili.imgPedine[3] = ImageIO.read(ClassLoader.getSystemResource("assets/Pedine/gialla.png"));
            repaint();
            String indice;
            String nomeFile;
            //Carico immagine delle carte risorse
            for (int i= 0; i < Variabili.imgCarteRisorse.length; i++)
            {
                indice = String.format("%02d", i);
                nomeFile = "risorsa" + indice + ".png";
                Variabili.imgCarteRisorse[i] = ImageIO.read(ClassLoader.getSystemResource("assets/CarteRisorsa/" + nomeFile));
            }
            repaint();
            //Carico immagine delle carte oro
            for (int i= 0; i < Variabili.imgCarteOro.length; i++)
            {
                indice = String.format("%02d", i);
                nomeFile = "oro" + indice + ".png";
                Variabili.imgCarteOro[i] = ImageIO.read(ClassLoader.getSystemResource("assets/CarteOro/" + nomeFile));
            }
            repaint();
            //Carico immagine delle carte obiettivo
            for (int i= 0; i < Variabili.imgCarteObiettivi.length; i++)
            {
                indice = String.format("%02d", i);
                nomeFile = "obiettivo" + indice + ".png";
                Variabili.imgCarteObiettivi[i] = ImageIO.read(ClassLoader.getSystemResource("assets/CarteObiettivo/" + nomeFile));
            }
            repaint();
            //Carico immagine delle carte iniziali
            for (int i= 0; i < Variabili.imgCarteIniziali.length; i++)
            {
                indice = String.format("%02d", i);
                nomeFile = "iniziale" + indice + ".png";
                Variabili.imgCarteIniziali[i] = ImageIO.read(ClassLoader.getSystemResource("assets/CarteIniziale/" + nomeFile));
            }
            Variabili.schermoSplashScreen.messaggioCaricamento = "Caricamento immagini terminato";
            Variabili.immaginiCaricate = true;
            
            Variabili.schermoAttivo = Enums.eElencoSchermi.INIZIALE;

            repaint();
        }
        catch(IOException e){
            e.printStackTrace();
        }
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
                case NUOVAPARTITA:
                    Variabili.schermoNuovaPartita.paint(Variabili.immagineBackGraphics);
                    Variabili.schermoMercato.paint(Variabili.immagineBackGraphics);
                    break;
                case NUOVOGIOCATORE:
                    Variabili.schermoNuovoGiocatore.paint(Variabili.immagineBackGraphics);
                    Variabili.schermoMercato.paint(Variabili.immagineBackGraphics);
                    break;
                case SCELTACARTAINIZIALE:
                    Variabili.schermoSceltaCartaIniziale.paint(Variabili.immagineBackGraphics);
                    Variabili.schermoMercato.paint(Variabili.immagineBackGraphics);
                    break;
                case SCELTACARTAOBIETTIVO:
                    Variabili.schermoSceltaCartaObiettivo.paint(Variabili.immagineBackGraphics);
                    Variabili.schermoMercato.paint(Variabili.immagineBackGraphics);
                    break;
                case GIOCO:
                    Variabili.schermoGioco.paint(Variabili.immagineBackGraphics);
                    Variabili.schermoMercato.paint(Variabili.immagineBackGraphics);
                    break;
                case PUNTEGGIO:
                    Variabili.schermoPunteggio.paint(Variabili.immagineBackGraphics);
                    Variabili.schermoMercato.paint(Variabili.immagineBackGraphics);
                    break;
                default:
                    break;
            }


            g.drawImage( Variabili.immagineBack, 0, 0, this);
        }
        catch(Exception e){
            System.out.println("ERRORE: " + e.getMessage() + " Nello schermo " +Variabili.schermoAttivo);
        }
        g.dispose();
    }

    class mouseClick implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
           // System.out.println(String.format("Hai cliccato: %d, %d", e.getX(), e.getY()));
           
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
           
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // Determino quale classe grafica richiamare in funzione dello schermo attivo
            switch (Variabili.schermoAttivo) {
                case SPLASHSCREEN:
                    Variabili.schermoSplashScreen.mousePressed(e);
                    break;
                case INIZIALE:
                    Variabili.schermoIniziale.mousePressed(e);
                    break;
                case NUOVAPARTITA:
                    Variabili.schermoNuovaPartita.mousePressed(e);
                    Variabili.schermoMercato.mousePressed(e);
                    break;
                case NUOVOGIOCATORE:
                    Variabili.schermoNuovoGiocatore.mousePressed(e);
                    Variabili.schermoMercato.mousePressed(e);
                    break;
                case SCELTACARTAINIZIALE:
                    Variabili.schermoSceltaCartaIniziale.mousePressed(e);
                    Variabili.schermoMercato.mousePressed(e);
                    break;
                case SCELTACARTAOBIETTIVO:
                    Variabili.schermoSceltaCartaObiettivo.mousePressed(e);
                    Variabili.schermoMercato.mousePressed(e);
                    break;
                case GIOCO:
                    Variabili.schermoGioco.mousePressed(e);
                    if (Variabili.partita == null) {
                        repaint();
                        return;
                    }
                    Variabili.schermoMercato.mousePressed(e);
                    break;
                case PUNTEGGIO:
                    Variabili.schermoPunteggio.mousePressed(e);
                    Variabili.schermoMercato.mousePressed(e);
                    break;
                default:
                    break;
            }


            repaint();
        }

        // Registra dove è stato rilasciato il click del mouse dopo il trascinamento
        @Override
        public void mouseReleased(MouseEvent e) {
            if (Variabili.schermoAttivo == Enums.eElencoSchermi.GIOCO){
                Variabili.schermoGioco.mouseReleased(e);
                repaint();
            }
        }
        
    }
    // Registra i movimenti del mouse durante il trascinamento
    class mouseMoving implements MouseMotionListener{

        @Override
        public void mouseDragged(MouseEvent e) {
           if (Variabili.schermoAttivo == Enums.eElencoSchermi.GIOCO){
                Variabili.schermoGioco.mouseDragged(e);
                repaint();
           }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
         
        }
        
    }



}
