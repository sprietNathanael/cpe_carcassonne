/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.board;

import carcassonne.model.tile.AbstractTile;
import carcassonne.coord.Coord;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Manages the tiles on the board
 *
 * @author Étienne
 */
public class Board implements BoardInterface
{

    // private AbstractTile[][] grid;
    private final HashMap<Coord, AbstractTile> grid;

    /**
     * Initializes an empty board
     *
     */
    public Board()
    {
        //grid = new AbstractTile[ROWS][COLUMNS];
        grid = new HashMap<>();
    }

    /**
     * adds a new tile on the board at the coordinate passed passed by
     * parameters
     *
     * @param newTile
     * @param row
     * @param column
     * @throws Exception
     */
    @Override
    public void addTile(AbstractTile newTile, int row, int column) throws Exception
    {
        try {
            if (!grid.containsKey(new Coord(column, row))) {
                grid.put(new Coord(column, row), newTile);
            }
            else {
                throw new Exception("There is already a tile in the location ["
                        + row + ":" + column + "]");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new Exception("The new tile has indexes that are out of the grid range");
        }
    }

    /**
     * gets the tile from the board on the coordinate passed by parameters
     *
     * @param row
     * @param column
     * @return
     * @throws Exception
     */
    @Override
    public AbstractTile getTile(int row, int column) throws Exception
    {
        try {
            return grid.get(new Coord(column, row));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new Exception("The position asked is out of the grid range");
        }
    }

    /**
     * Get all tiles array
     *
     * @return
     */
    public HashMap getAllTiles()
    {
        return this.grid;
    }

    /**
     * Get the board NW ans SE corner coordinates
     *
     * @return
     */
    public HashMap<String, Coord> getBoardDimensions()
    {
        Coord nw = null;
        Coord se = null;
        // Loop over the positions to get the sout-east and north-west
        for (Coord coord : this.grid.keySet()) {
            if (nw == null) {
                nw = new Coord(coord);
            }
            else {
                if (coord.col < nw.col) {
                    nw.col = coord.col;
                }

                if (coord.row < nw.row) {
                    nw.row = coord.row;
                }
            }

            if (se == null) {
                se = new Coord(coord);
            }
            else {
                if (coord.col > se.col) {
                    se.col = coord.col;
                }

                if (coord.row > se.row) {
                    se.row = coord.row;
                }
            }
        }
        HashMap<String, Coord> map = new HashMap<String, Coord>()
        {
        };
        map.put("nw", nw);
        map.put("se", se);
        return map;
    }

    /**
     * Get all the possible placements for a tile
     *
     * @param tile
     * @return
     */
    public ArrayList<Coord> getTilePossiblePlacements(AbstractTile tile)
    {
        HashMap<String, Coord> boardDimensions = this.getBoardDimensions();
        Coord nw = boardDimensions.get("nw");
        Coord se = boardDimensions.get("se");
        // Get the coordinates of board corners plus one tile
        int north = nw.row - 1;
        int west = nw.col - 1;
        int south = se.row + 1;
        int east = se.col + 1;
        ArrayList<Coord> res = new ArrayList<>();

        // Loop over all the positions between NW and SE corners
        for (int verticalIterate = north; verticalIterate <= south; verticalIterate++) {
            for (int horizontalIterate = west; horizontalIterate <= east; horizontalIterate++) {
                Coord tempCoord = new Coord(horizontalIterate, verticalIterate);
                if (!this.grid.containsKey(tempCoord)) {
                    int i = 0;
                    boolean tileCanBePlaced = false;
                    while (i < 4 && !tileCanBePlaced) {
                        if (this.canTileBePlacedHere(tempCoord, tile)) {
                            res.add(tempCoord);
                            tileCanBePlaced = true;
                            for (; i < 4; i++) {
                                tile.rotateRight();
                            }
                        }
                        else {
                            tile.rotateRight();
                        }
                        i++;
                    }
                }
            }
        }
        return res;
    }

    /**
     * Test if a tile can be placed at coordinates
     *
     * @param coordinates
     * @param tile
     * @return
     */
    public boolean canTileBePlacedHere(Coord coordinates, AbstractTile tile)
    {
        boolean res = true;
        boolean surrounded = false;
        AbstractTile tempTile;

        // Test North tile
        Coord tempCoord = new Coord(coordinates.col, coordinates.row - 1);
        if (this.grid.containsKey(tempCoord)) {
            tempTile = this.grid.get(tempCoord);
            boolean tempRes = tile.compareTileNorth(tempTile);
            res = tempRes && res;
            surrounded = true;
        }

        // Test South tile
        tempCoord.row = coordinates.row + 1;
        if (this.grid.containsKey(tempCoord)) {
            tempTile = this.grid.get(tempCoord);
            boolean tempRes = tile.compareTileSouth(tempTile);
            res = tempRes && res;
            surrounded = true;
        }

        // Test West tile
        tempCoord.row = coordinates.row;
        tempCoord.col = coordinates.col - 1;
        if (this.grid.containsKey(tempCoord)) {
            tempTile = this.grid.get(tempCoord);
            boolean tempRes = tile.compareTileWest(tempTile);
            res = tempRes && res;
            surrounded = true;
        }

        // Test East tile
        tempCoord.col = coordinates.col + 1;
        if (this.grid.containsKey(tempCoord)) {
            tempTile = this.grid.get(tempCoord);
            boolean tempRes = tile.compareTileEast(tempTile);
            res = tempRes && res;
            surrounded = true;
        }
        res = res && surrounded;
        return res;
    }

    /**
     * Retruns near tiles without the tile
     *
     * @param coordinates
     * @param tile
     * @return
     */
    public HashMap<Coord, AbstractTile> getNearTilesAbbayRange(Coord coordinates, AbstractTile tile)
    {
        HashMap<Coord, AbstractTile> nearTiles = new HashMap<>();
        int col = coordinates.col;
        int row = coordinates.row;
        Coord temp;

        for (int c = col - 1; c <= col + 1; c++) {
            for (int r = row - 1; r <= row + 1; r++) {
                if (c != col || r != row) {
                    //temp = new Coord(c, r);
                    nearTiles.put(new Coord(c, r), grid.get(convertCoord(c, r)));
                }
            }
        }
        return nearTiles;
    }

    /**
     * Converts coord from IHM to Model
     *
     * @param x
     * @param y
     * @return
     */
    private Coord convertCoord(int x, int y)
    {
        return new Coord(x, y * -1);
    }
}
