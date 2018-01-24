/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.controller;

import carcassonne.coord.Coord;
import carcassonne.model.carcassonnegame.CarcassonneGame;
import carcassonne.model.player.Meeple;
import carcassonne.model.player.Player;
import carcassonne.model.tile.AbstractTile;
import carcassonne.model.tile.CasualTile;
import carcassonne.view.CarcassonneIHM.ClientWindow;
import carcassonne.view.CarcassonneIHM.menuStart.ParamPlayers;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JFrame;

/**
 *
 * @author Thomas
 */
public class CarcassonneGameControllerLocalNetworkClient extends CarcassonneGameControllerLocalNetwork
{

    private Socket socket;
    private int myPlayerIndex;

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public CarcassonneGameControllerLocalNetworkClient(String ipAddr, String pseudo) throws Exception
    {
        this(new CarcassonneGame(), ipAddr, pseudo);
    }
    @SuppressWarnings("unchecked")
    public CarcassonneGameControllerLocalNetworkClient(CarcassonneGame model, String ipAddr, String pseudo) throws Exception
    {
        super(model);

        try {
            socket = new Socket(ipAddr, 6666);
            System.out.println("Socket client crée");
            sendClientInfomation(pseudo);
            inputStream = new ObjectInputStream(socket.getInputStream());
            myPlayerIndex = (int) inputStream.readObject();
            System.out.println("Player index : " + myPlayerIndex);
            this.paramPlayers = (List<ParamPlayers>) inputStream.readObject();
            play();
            sleep(100000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendClientInfomation(String pseudo) throws IOException
    {
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(pseudo);
        System.out.println("pseudo envoyé");
    }
    
    // à remplacer dans un click
    private void play()
    {
        ClientWindow clientWindow = new ClientWindow(this.paramPlayers);
        Set<String> playableColors = new HashSet<>();
        playableColors.add("red");
        ClientWindow clientWindow = new ClientWindow(this.getPlayers(), playableColors);
        clientWindow.setVisible(true);
        clientWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void beginTurn()
    {
        if (myPlayerIndex == this.carcassonneGame.getCurrentPlayerIndex())
        {
            super.beginTurn();
        }
        else
        {
            //ManageNetwork();
        }
    }
    
    public void ManageNetwork() throws Exception
    {
        for(int i=0 ; i < carcassonneGame.getPlayers().size()-1 ; i++)
        {
            AbstractTile tile = (AbstractTile) inputStream.readObject();
            Coord c = (Coord) inputStream.readObject();
            Meeple m = (Meeple) inputStream.readObject();
            //String coord =
        }
    }
    
    public void ReceiveCarcassonneGame() throws Exception
    {
       this.carcassonneGame = (CarcassonneGame) inputStream.readObject();
       carcassonneGame.notifyObservers();
    }
    
    

    public static void main(String[] zero) throws Exception
    {
        new CarcassonneGameControllerLocalNetworkClient("localhost",  String.valueOf((int)(Math.random()*100)));
    }

}
