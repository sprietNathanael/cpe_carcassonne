/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.set;

import carcassonne.model.player.Meeple;
import carcassonne.model.tile.AbstractTile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author nathanael
 */
public abstract class AbstractSet implements SetInterface
{
    protected final List<AbstractTile> tileList;
    protected AbstractTile firstTile;
    protected Set<Meeple> meeples;

    public AbstractSet()
    {
        tileList = new ArrayList<>();
        this.meeples = new HashSet<>();
    }
    
    public Set<Meeple> getMeeples()
    {
        return meeples;
    }
    
    public List<AbstractTile> getSet()
    {
        return tileList;
    }
    
    protected abstract void initiliazeSet();
    
    public static Set<String> retTreeSet(String... poss)
    {
        Set<String> tsPos = new HashSet<>();
        tsPos.addAll(Arrays.asList(poss));        
        return tsPos;
    }
    
    public AbstractTile getFirstTile()
    {
        return this.firstTile;
    }
    
    
}
