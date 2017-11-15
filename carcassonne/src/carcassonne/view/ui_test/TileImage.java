/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
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
    private double rotation;

    public TileImage(int x, int y, String name, int rotation)
    {
        super(x,y);
        this.name = name;
        this.rotation = rotation * Math.PI / 180.0; //get rotation in radians
        AffineTransform tx = new AffineTransform();
        try 
        {
            this.image = ImageIO.read(new File("resources/"+this.name + ".jpg"));
            tx.rotate(this.rotation, this.image.getWidth()/2, this.image.getHeight()/2);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
            System.out.println(this.name+" "+this.rotation);
            this.image = op.filter(image, null);
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
