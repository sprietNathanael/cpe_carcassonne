/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.modele.player;

import java.awt.Color;

/**
 *
 * @author thomas
 */
public class Player
{

    public static int NBMEEPLE = 8;

    private Meeple[] meeple;
    private String name;
    private int points;
    Color color;

    public Player(String name, Color color)
    {
        this.name = name;
        this.color = color;
        points = 0;
        meeple = new Meeple[NBMEEPLE];
        meeple[0].setIsBig(true);
    }

    public Meeple[] getMeeple()
    {
        return meeple;
    }

    public void setMeeple(Meeple[] meeple)
    {
        this.meeple = meeple;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getPoints()
    {
        return points;
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

}
