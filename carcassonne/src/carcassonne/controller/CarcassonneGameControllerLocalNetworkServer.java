/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.controller;

import RessourcesGlobalVariables.PlayerTypes;
import carcassonne.model.carcassonnegame.CarcassonneGame;
import carcassonne.view.CarcassonneIHM.menuStart.ParamPlayers;
import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Thomas
 */
public class CarcassonneGameControllerLocalNetworkServer extends AbstractCarcassonneGameController
{

    private List<Socket> sockets;
    private int maxClients;
    private int ActualClientNumber;
    private List<ParamPlayers> players;

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public CarcassonneGameControllerLocalNetworkServer(int playersNumber) throws Exception
    {
        this(new CarcassonneGame(), playersNumber);
    }

    public CarcassonneGameControllerLocalNetworkServer(CarcassonneGame model, int playersNumber) throws InterruptedException
    {
        super(model);
        maxClients = playersNumber - 1;
        ActualClientNumber = 0;
        sockets = new LinkedList<>();
        players = new LinkedList<>();

        try {
            Thread t = new Thread(new WaitAndAcceptsClient());
            t.start();
            System.out.println("Serveur prêt !");
            t.join();
        } catch (Exception e) {

            e.printStackTrace();
        }
        sleep(100000);
    }

    private class WaitAndAcceptsClient implements Runnable
    {

        private ServerSocket socketserver;
        private Socket socket;

        public void run()
        {
            try {
                socketserver = new ServerSocket(6666);
                while (ActualClientNumber < maxClients) {
                    socket = socketserver.accept();
                    System.out.println("Socket accepté");
                    ReceiveClientInfomation(socket);
                    System.out.println("Le client numéro " + ActualClientNumber + " est connecté !");
                    ActualClientNumber++;
                    sockets.add(socket);
                }
                SendPlayersList();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void ReceiveClientInfomation(Socket s) throws Exception
    {
        inputStream = new ObjectInputStream(s.getInputStream());
        String pseudo = (String) inputStream.readObject();
        System.out.println(pseudo);
        ParamPlayers p = new ParamPlayers(pseudo, "red", PlayerTypes.player);
        players.add(p);
    }

    private void SendPlayersList() throws Exception
    {
        for (Socket s : sockets) {
            outputStream = new ObjectOutputStream(s.getOutputStream());
            outputStream.writeObject(players);
            System.out.println("liste envoyée");
        }
    }

    public static void main(String[] zero) throws Exception
    {
        new CarcassonneGameControllerLocalNetworkServer(2);
    }

    public List<ParamPlayers> getPlayers()
    {
        return players;
    }

}
