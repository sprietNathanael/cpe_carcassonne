/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package RessourcesGlobalVariables;

import java.io.Serializable;

/**
 *
 * @author Thomas
 */
public enum eNetworkActions implements Serializable
{
    rotateRight,
    putTile,
    putMeeple,
    beginGame,
    endTurn,
    currentColor,
    allPlayers,
    serverFull,
    sendGame
}
