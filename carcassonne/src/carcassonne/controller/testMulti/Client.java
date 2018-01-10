/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.controller.testMulti;

/**
 *
 * @author thomas
 */
import java.io.IOException;
import java.net.*;

public class Client {

	public static void main(String[] zero){
		
		Socket socket;
		try {
		socket = new Socket("localhost",2009);
		socket.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}



