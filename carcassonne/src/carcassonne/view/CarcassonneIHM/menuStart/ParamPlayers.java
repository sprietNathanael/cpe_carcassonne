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
    private final String playerType;

    public ParamPlayers(String nom, String color, String playerType)
    {
        this.nom = nom;
        this.color = color;
        this.playerType = playerType;
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

    public String getPlayerType()
    {
        return playerType;
    }
    
    

}
