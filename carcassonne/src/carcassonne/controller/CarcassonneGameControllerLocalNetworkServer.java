/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.controller;

import RessourcesGlobalVariables.PlayerTypes;
import carcassonne.model.carcassonnegame.CarcassonneGame;
import carcassonne.view.CarcassonneIHM.menuStart.ParamPlayers;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    public CarcassonneGameControllerLocalNetworkServer(CarcassonneGame model, int playersNumber)
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

            System.out.println("Le client numéro " + ActualClientNumber + " est connecté !");
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private class WaitAndAcceptsClient implements Runnable
    {

        private ServerSocket socketserver;
        private Socket socket;

        public void run()
        {
            try {
                socketserver = new ServerSocket(80);
                while (ActualClientNumber < maxClients) {
                    socket = socketserver.accept();
                    ReceiveClientInfomation(socket);
                    ActualClientNumber++;
                    sockets.add(socket);
                    //socket.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void ReceiveClientInfomation(Socket s) throws Exception
    {
        inputStream = new ObjectInputStream(s.getInputStream());
        String pseudo = (String) inputStream.readObject();
        ParamPlayers p = new ParamPlayers(pseudo, "red", PlayerTypes.player);
        players.add(p);
        inputStream.close();
    }

    private void SendPlayersList() throws Exception
    {
        for (Socket s : sockets) {
            outputStream = new ObjectOutputStream(s.getOutputStream());
            outputStream.writeObject(players);
            outputStream.close();
        }
    }

    public static void main(String[] zero) throws Exception
    {
        new CarcassonneGameControllerLocalNetworkServer(2);
    }

}
