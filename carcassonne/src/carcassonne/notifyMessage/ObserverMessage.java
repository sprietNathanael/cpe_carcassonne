/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */

package carcassonne.notifyMessage;

import carcassonne.coord.Coord;
import carcassonne.model.tile.AbstractTile;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @deprecated 
 * @author nathanael
 */
public class ObserverMessage
{
    private HashMap<Coord,AbstractTile> board;
    private AbstractTile preview;
    private ArrayList<Coord> placements;
    
    private ArrayList<String> tileSplits;
    
    private String messageType;

    public ObserverMessage(String messageType, HashMap<Coord, AbstractTile> board, AbstractTile preview, ArrayList<Coord> placements)
    {
        this.board = board;
        this.preview = preview;
        this.placements = placements;
        this.messageType = messageType;
        this.tileSplits = null;
    }

    public ObserverMessage(String messageType, ArrayList<String> tileSplits)
    {
        this.tileSplits = tileSplits;
        this.messageType = messageType;
        this.board = null;
        this.preview = null;
        this.placements = null;
    }

    public ArrayList<String> getTileSplits()
    {
        return tileSplits;
    }

    public String getMessageType()
    {
        System.out.println("MassageType = "+this.messageType);
        return messageType;
    }
    
    public HashMap<Coord, AbstractTile> getBoard()
    {
        return board;
    }

    public AbstractTile getPreview()
    {
        return preview;
    }

    public ArrayList<Coord> getPlacements()
    {
        return placements;
    }
    
    
}
