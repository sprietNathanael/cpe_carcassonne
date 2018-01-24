/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.controller;

import RessourcesGlobalVariables.Colors;
import RessourcesGlobalVariables.PlayerTypes;
import carcassonne.model.carcassonnegame.CarcassonneGame;
import carcassonne.view.CarcassonneIHM.ClientWindow;
import carcassonne.view.CarcassonneIHM.menuStart.ParamPlayers;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.swing.JFrame;

/**
 *
 * @author Thomas
 */
public class CarcassonneGameControllerLocalNetworkServer extends CarcassonneGameControllerLocalNetwork
{

    private List<Socket> sockets;
    private List<ObjectOutputStream> outS;
    private List<ObjectInputStream> inS;
    
    private int maxClients;
    private int ActualClientNumber;
    private int myPlayerIndex;

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public CarcassonneGameControllerLocalNetworkServer(int playersNumber, String pseudo) throws Exception
    {
        this(new CarcassonneGame(), playersNumber, pseudo);
    }

    public CarcassonneGameControllerLocalNetworkServer(CarcassonneGame model, int playersNumber, String pseudo) throws InterruptedException
    {
        super(model);
        maxClients = playersNumber - 1;
        ActualClientNumber = 0;
        sockets = new LinkedList<>();
        paramPlayers = new LinkedList<>();
        inS = new LinkedList<>();
        outS = new LinkedList<>();
        
        paramPlayers.add(new ParamPlayers(pseudo, Colors.tab.get(0), PlayerTypes.player));
        myPlayerIndex = 0;

        try {
            Thread t = new Thread(new waitAndAcceptsClient());
            t.start();
            System.out.println("Serveur prêt !");
            t.join();
            sendToAllSockets(paramPlayers);
        } catch (Exception e) {

            e.printStackTrace();
        }
        play();
    }

    private class waitAndAcceptsClient implements Runnable
    {

        ServerSocket socketserver;
        Socket socket;

        public void run()
        {
            try {
                socketserver = new ServerSocket(6666);
                while (ActualClientNumber < maxClients) {
                    socket = socketserver.accept();
                    System.out.println("Socket accepté");
                    outS.add(new ObjectOutputStream(socket.getOutputStream()));
                    inS.add(new ObjectInputStream(socket.getInputStream()));   
                    
                    System.out.println("Le client numéro " + ActualClientNumber + " est connecté !");
                    ActualClientNumber++;
                    sockets.add(socket);
                    receiveClientInfomation(inS.get(inS.size()-1));
                    outS.get(outS.size() -1).writeObject(ActualClientNumber + 1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void receiveClientInfomation(ObjectInputStream in) throws Exception
    {
        String pseudo = (String) in.readObject();
        System.out.println("pseudo : " + pseudo);
        ParamPlayers p = new ParamPlayers(pseudo, Colors.tab.get(paramPlayers.size()), PlayerTypes.player);
        paramPlayers.add(p);
    }

    private void sendToSocket(ObjectOutputStream out, Object o) throws IOException
    {
        //outputStream = new ObjectOutputStream(s.getOutputStream());
        //outputStream.writeObject(o);
        out.writeObject(o);
    }
    
    private void sendToAllSockets(Object o) throws Exception
    {
        for (ObjectOutputStream out : outS) {
            out.writeObject(paramPlayers);
        }
    }
    
    public void sendCarcassoneGame() throws IOException
    {
        for (ObjectOutputStream out : outS) {
            out.writeObject(this.carcassonneGame);
        }
    }
    
    // à remplacer dans un click
    private void play()
    {
        Set<String> playableColors = new HashSet<>();
        playableColors.add("red");
        ClientWindow clientWindow = new ClientWindow(this.getPlayers(), playableColors);
        clientWindow.setVisible(true);
        clientWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] zero) throws Exception
    {
        new CarcassonneGameControllerLocalNetworkServer(2, "host");
    }

    public List<ParamPlayers> getPlayers()
    {
        return paramPlayers;
    }

    @Override
    public void beginTurn()
    {
        super.beginTurn();
    }
    
}
