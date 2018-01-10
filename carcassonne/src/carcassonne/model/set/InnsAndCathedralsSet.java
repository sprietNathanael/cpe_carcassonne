/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.set;

import carcassonne.model.tile.AbstractTile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Bertrand
 */
public class InnsAndCathedralsSet implements SetInterface
{
    private final List<AbstractTile> tileList;
    private AbstractTile firstTile;
    
    public InnsAndCathedralsSet ()
    {
        tileList = new ArrayList<>();
        this.initiliazeSet();
    }
    
    @Override
    public List<AbstractTile> getSet()
    {
        return tileList;
    }

    @Override
    public AbstractTile getFirstTile()
    {
        return this.firstTile;
    }
    
     /**
     * Adds all the tiles of the extension
     */
    private void initiliazeSet()
    {
        Set<Set<String>> aggregates;
    }
    
    public static Set<String> retTreeSet(String... poss)
    {
        Set<String> tsPos = new HashSet<>();
        tsPos.addAll(Arrays.asList(poss));        
        return tsPos;
    }
}
