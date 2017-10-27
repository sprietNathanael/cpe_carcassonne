/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.player;

import java.awt.Color;

/**
 * Represents a player
 */
public class Player
{

    /**
     * How much meeples the player has 
     */
    public static int NBMEEPLE = 8;

    private Meeple[] meeple;
    private String name;
    private int points;
    Color color;

    /**
     * Creates a new Player
     *
     * @param name the player's name
     * @param color the player's color
     */
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

    /**
     * Gets the name of the player
     *
     * @return string the name of the player
     */
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Gets the points of the player
     *
     * @return integer the points of the player
     */
    public int getPoints()
    {
        return points;
    }

    /**
     * Gets the color of the player
     *
     * @return Color the color of the player
     */
    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

}
