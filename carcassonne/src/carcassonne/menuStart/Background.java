/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.menuStart;

/**
 *
 * @author thomas
 */
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Background extends JPanel
{

    private Image img;

    public Background(String back) throws IOException
    {
        try {
            img = ImageIO.read(new File(back));
        } catch (IOException e) {
        }

    }

    @Override
    public void paintComponent(Graphics g)
    {
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
