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
 *
 * @author nathanael
 */
public class ObserverMessage
{
    private final HashMap<Coord,AbstractTile> board;
    private final AbstractTile preview;
    private final ArrayList<Coord> placements;

    public ObserverMessage(HashMap<Coord, AbstractTile> board, AbstractTile preview, ArrayList<Coord> placements)
    {
        this.board = board;
        this.preview = preview;
        this.placements = placements;
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
