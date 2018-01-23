/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.aggregate;

import carcassonne.coord.Coord;
import carcassonne.model.player.Meeple;
import carcassonne.model.player.Player;
import carcassonne.model.tile.AbstractTile;
import carcassonne.model.type.AbstractType;
import carcassonne.model.type.RiverType;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Deals with the River
 *
 * @author Étienne
 *
 */
public class RiverAggregate extends AbstractAggregate
{

    private Coord lastTile;
    private final StreamDirection stream;

    public RiverAggregate(int col, int row, AbstractTile firstTile, Set<String> locationTypes)
    {
        super(col, row, firstTile, locationTypes);
        lastTile = new Coord(col, row);
        stream = filterEdgeLocation(locationTypes);
    }

    @Override
    public void enlargeAggregate(int col, int row, AbstractTile newTile, Set<String> locationTypes)
    {
        super.enlargeAggregate(col, row, newTile, locationTypes);
        //If the tile is the "RE" one, that means the player has closed the river
        if (newTile.getId().equals("RE")) {
            isCompleted = true;
        }
        lastTile = new Coord(col, row);
    }

    @Override
    public boolean checkIsCompleted()
    {
        return isCompleted;
    }

    public Coord getNextPositionTile()
    {
        Coord result = null, currentCoord;

        AbstractTile tile = this.aggregatedTiles.get(lastTile);

        //Récupère les locations des bords de la tuile 
        Set<String> locations = filterEdgeLocations(this.aggregatedPositionTypes.get(tile));

        //Parcours de ces locations
        for (String location : locations) {
            //Coord corespondant à cette location
            currentCoord = getCoordFromLocation(location, lastTile);
            if (!this.aggregatedTiles.containsKey(currentCoord)) {
                result = currentCoord;
                break;
            }
        }

        return result;
    }

    @Override
    public Set<Player> getWinningPlayers()
    {
        return null;
    }

    @Override
    public int getBiggestPoints()
    {
        return -1;
    }

    @Override
    protected void merge(AbstractAggregate neighborAggregate
    )
    {

    }

    @Override
    public Map<Player, Set<Meeple>> getPlayers()
    {
        return null;
    }

    @Override
    public boolean addMeeple(Player player, Meeple meeple)
    {
        return false;
    }

    @Override
    public int countPoints()
    {
        return -1;
    }

    @Override
    public String toString()
    {
        return "River{" + "aggregatedTiles=" + aggregatedTiles + ", aggregatedPositionTypes=" + aggregatedPositionTypes + ", players=" + players + ", isCompleted=" + isCompleted + "\n}";
    }

    /**
     * Get only the locations of the river that is at an edge of a tile
     *
     * @param locations
     * @return
     */
    private static Set<String> filterEdgeLocations(Set<String> locations)
    {
        Set<String> result = new HashSet<>();

        for (String location : locations) {
            if (location.equals("N")
                    || location.equals("E")
                    || location.equals("S")
                    || location.equals("W")) {
                result.add(location);
            }
        }

        return result;
    }

    /**
     * Get only the location of the river that is at an edge of a tile
     *
     * @param locations
     * @return
     */
    private static StreamDirection filterEdgeLocation(Set<String> locations)
    {
        String result = null;

        for (String location : locations) {
            if (location.equals("N")
                    || location.equals("E")
                    || location.equals("S")
                    || location.equals("W")) {
                result = location;
                break;
            }
        }

        return StreamDirection.getStreamDirectionLocation(result);
    }

    /**
     * Get neighbor coord, using current coord and location
     *
     * @param location
     * @param neighborCoord
     * @return
     */
    private static Coord getCoordFromLocation(String location, Coord neighborCoord)
    {
        Coord coord = new Coord(neighborCoord.col, neighborCoord.row);
        switch (location) {
            case "N":
                coord.row++;
                break;
            case "S":
                coord.row--;
                break;
            case "E":
                coord.col++;
                break;
            case "W":
                coord.col--;
                break;
        }

        return coord;
    }

    /**
     * Check if the new tile can be put in this rotation state, used to test all
     * rotation placements
     *
     * @param coordinates
     * @param tile
     * @return
     */
    public boolean checkNewPlacementCorrect(Coord coordinates, AbstractTile tile)
    {
        System.out.println("TErminéééé: " + isCompleted);
        boolean result = false;

        //Case of the second tile, where there is still no stream direction fixed
        if (stream == null) {
            result = true;
        }
        else {
            //Récupère les locations des bords de la tuile 
            Set<String> locations = filterEdgeLocations(tile.getRiverAggregateEmplacements());
            Set<Coord> coords;
            Coord newCoord;

            System.out.println("Liste location: " + tile.getRiverAggregateEmplacements());
            boolean modifie = false;
            //Parcours de ces locations
            for (String location : locations) {
                //Récupère les voisins existants
                coords = this.getTrueNeighboredCoordinates(coordinates.col, coordinates.row);
                newCoord = getNewCoordUsingLocation(location, coordinates.col, coordinates.row);
                //Condition pour traiter uniquement la sortie de la rivière
                if (!coords.contains(newCoord)) {
                    //Test si le placement complète la rivière en respectant le sens d'écoulement
                    result = StreamDirection.checkCorrectSreamDirection(stream, location);
                    //Test si le nouveau placement ne crée pas une situation bloquante
                    result = result && this.getNeighboredCoordinates(newCoord.col, newCoord.row).isEmpty();
                    //Utilisé pour tester la dernière tuile
                    modifie = true;
                    break;
                }
            }
            if (!modifie) {
                //Test pour dernière tuile, on retourne true
                result = true;
            }
        }

        return result;
    }

    private static Coord getNewCoordUsingLocation(String location, int col, int row)
    {
        System.out.println("Locaca " + location);

        if (location.equals("E")) {
            col = col + 1;
        }
        else if (location.equals("W")) {
            col = col - 1;
        }
        else if (location.equals("S")) {
            row = row - 1;
        }
        else {
            row = row + 1;
        }

        return new Coord(col, row);
    }
}
