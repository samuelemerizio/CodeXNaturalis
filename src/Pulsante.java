import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Pulsante {
    // Dichiaro e inizializzo le proprietà dei pulsanti
    public Rectangle area;
    public String testo = "";
    public boolean visibile = true;
    public BufferedImage img = null;
    public Color coloreSfondo = new Color(128, 128, 128, 230);
    public Color coloreBordo = Color.WHITE;
    public Color coloreTestoSfondo = Color.BLACK;
    public Color coloreTesto = Color.YELLOW;
    public int allineamento = 1;
    public int allineamentoLinea = 1;
    public boolean bold = false;
    public int fontSize = 30;


    public Pulsante(){}


    public Pulsante(int x, int y, int dx, int dy, String testo){
        this.testo = testo;
        area = new Rectangle(x, y, dx, dy);
    }

    public Pulsante(int x, int y, int dx, int dy, BufferedImage img){
        this.img = img;
        area = new Rectangle(x, y, dx, dy);
    }

    public void draw(Graphics2D g2d){
        // Se il pulsante non è visibile non fare nulla
        if (!visibile) return;

        // Imposto il colore di sfondo
        g2d.setColor(coloreSfondo);
        // Coloro il pulsante
        g2d.fillRect(area.x, area.y, area.width, area.height);

        // Imposto il colore del bordo del pulsante
        g2d.setColor(coloreBordo);
        // Disegno ilcontorno del pulsante
        g2d.drawRect(area.x, area.y, area.width, area.height);

        // Variabili per centrare la scritta nel pulsante
        int x = area.width / 2 + area.x;
        int y = area.height / 2 + area.y;

        Grafica.disegnaTesto(g2d, testo, x, y, coloreTestoSfondo, coloreTesto, allineamento, allineamentoLinea, fontSize, bold);
    }

    public void drawCerchio(Graphics2D g2d){
        if (!visibile) return;
        // Imposto il colore di sfondo 
        g2d.setColor(coloreSfondo);
        // Coloro l'ovale
        g2d.fillOval(area.x, area.y, area.width, area.height);
        // Imposto il colore del bordo dell'ovale
        g2d.setColor(coloreBordo);
        // Disegno il bordo dll'ovale
        g2d.drawOval(area.x, area.y, area.width, area.height);
    }

    public void drawImg(Graphics2D g2d){
        if (!visibile) return;
        if (img == null) Grafica.disegnaTesto(g2d, "X", area.x, area.y, coloreTestoSfondo, coloreTesto, allineamento, allineamentoLinea, fontSize, bold);
        else g2d.drawImage(img, area.x, area.y, area.width, area.height, null);

    }

    public void drawImg(Graphics2D g2d, boolean evidenzia){
        if (evidenzia){
            g2d.setColor(Color.WHITE);
            g2d.fillOval(area.x -5, area.y - 5, area.width + 10, area.height + 10);
        }
        drawImg(g2d);

    }

    public void drawImgCarta(Graphics2D g2d, boolean evidenzia, Color coloreEvidenziazione, int raggio){
        if (evidenzia){
            g2d.setColor(coloreEvidenziazione);
            g2d.fillRoundRect(area.x - raggio, area.y - raggio, area.width + raggio * 2, area.height + raggio * 2, raggio, raggio);
        }
        drawImg(g2d);

    }
}
