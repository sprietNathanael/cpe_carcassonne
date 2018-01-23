/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.controller;

import carcassonne.model.carcassonnegame.CarcassonneGame;
import carcassonne.view.CarcassonneIHM.menuStart.ParamPlayers;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author Thomas
 */
public class CarcassonneGameControllerLocalNetworkClient extends AbstractCarcassonneGameController
{

    private Socket socket;
    private List<ParamPlayers> players = null;

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public CarcassonneGameControllerLocalNetworkClient(String ipAddr, String pseudo) throws Exception
    {
        this(new CarcassonneGame(), ipAddr, pseudo);
    }

    public CarcassonneGameControllerLocalNetworkClient(CarcassonneGame model, String ipAddr, String pseudo) throws Exception
    {
        super(model);

        try {
            socket = new Socket(ipAddr, 6666);
            System.out.println("Socket client crée");
            SendClientInfomation(pseudo);
            ReceivePlayersList();
            sleep(100000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void SendClientInfomation(String pseudo) throws IOException
    {
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(pseudo);
        System.out.println("pseudo envoyé");
    }

    private void ReceivePlayersList() throws Exception
    {
        inputStream = new ObjectInputStream(socket.getInputStream());
        System.out.println("test");
        players = (List<ParamPlayers>) inputStream.readObject();
    }

    public List<ParamPlayers> getPlayers()
    {
        return players;
    }

    public static void main(String[] zero) throws Exception
    {
        new CarcassonneGameControllerLocalNetworkClient("localhost", "mon pseudo");
    }

}
