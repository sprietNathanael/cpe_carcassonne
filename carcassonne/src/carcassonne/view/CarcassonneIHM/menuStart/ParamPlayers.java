/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM.menuStart;

/**
 *
 * @author thomas
 */
public class ParamPlayers
{
    private final String nom;
    private final String color;

    public ParamPlayers(String nom, String color)
    {
        this.nom = nom;
        this.color = color;
    }

    /**
     * @return the nom
     */
    public String getNom()
    {
        return nom;
    }

    /**
     * @return the color
     */
    public String getColor()
    {
        return color;
    }

}
