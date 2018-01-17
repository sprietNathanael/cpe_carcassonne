/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.controller;

import carcassonne.model.carcassonnegame.CarcassonneGame;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import sun.security.ssl.Debug;

/**
 *
 * @author Thomas
 */
public class CarcassonneGameControllerLocalNetworkClient extends AbstractCarcassonneGameController
{

    private Socket socket;

    public CarcassonneGameControllerLocalNetworkClient() throws Exception
    {
        this(new CarcassonneGame());
    }

    public CarcassonneGameControllerLocalNetworkClient(CarcassonneGame model) throws InterruptedException, ClassNotFoundException
    {
        super(model);

        try {
            socket = new Socket("localhost", 80);
            System.out.println("Socket client crée");
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            CarcassonneGame test = (CarcassonneGame) ois.readObject();
            System.out.println(test);
            //close resources
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] zero) throws Exception {
        new CarcassonneGameControllerLocalNetworkClient();
    }
    
}
