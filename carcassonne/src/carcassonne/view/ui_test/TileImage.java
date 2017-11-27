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
        this.rotation = rotation;
        this.buildImage();
    }
    
    private void buildImage()
    {
        AffineTransform tx = new AffineTransform();
        this.image = null;
        try 
        {
            double rad_rotation = this.rotation * Math.PI / 180.0; //get rotation in radians
            this.image = ImageIO.read(new File("resources/"+this.name + ".jpg"));
            tx.rotate(rad_rotation, this.image.getWidth()/2, this.image.getHeight()/2);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
            System.out.println(this.name+" "+this.rotation);
            this.image = op.filter(image, null);
        } catch (IOException ex) {
            Logger.getLogger(TilesLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void turnRight()
    {
        this.rotation += 90;
        this.rotation = this.rotation > 360 ? this.rotation - 360 : this.rotation; 
        this.buildImage();
    }
    
    public void turnLeft()
    {
        this.rotation -= 90;
        this.rotation = this.rotation > 360 ? this.rotation - 360 : this.rotation;       
    }
    
    public void setX(int x)
    {
        this.x = x;
    }
    
    public void setY(int y)
    {
        this.y = y;
    }
    
    public void setCoord(Coord c)
    {
        this.setX(c.getX());
        this.setY(c.getY());
    }
    
    public BufferedImage getImage()
    {
        return this.image;
    }
    
    
}
