/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.carcassonnegame;

import carcassonne.model.aggregate.CityAggregate;
import java.util.Set;

/**
 *
 * @author Étienne
 */
public class CitiesUpdateResult
{
    public Set<Set<String>> citiesNotCreated;
    public Set<CityAggregate> citiesOnTile;

    public CitiesUpdateResult(Set<Set<String>> citiesNotCreated, Set<CityAggregate> citiesOnTile)
    {
        this.citiesNotCreated = citiesNotCreated;
        this.citiesOnTile = citiesOnTile;
    }
    
    
}
