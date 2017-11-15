/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author nathanael
 */
public class TileImage extends Coord
{
    private BufferedImage image;
    private String name;

    public TileImage(int x, int y, String name)
    {
        super(x,y);
        this.name = name;
        try 
        {
            System.out.println("try to read resources/"+this.name+".png");
            this.image = ImageIO.read(new File("resources/"+this.name + ".png"));
        } catch (IOException ex) {
            Logger.getLogger(TilesLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
    public BufferedImage getImage()
    {
        System.out.println("carcassonne.view.ui_test.TileImage.getImage()");
        return this.image;
    }
    
    
}
