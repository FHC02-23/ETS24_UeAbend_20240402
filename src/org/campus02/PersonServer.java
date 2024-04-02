package org.campus02;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PersonServer {

    public static void main(String[] args) {
        try (ServerSocket s = new ServerSocket(1111)) {
            // laufe bitte ewig!
            while (true) {
                System.out.println("warte auf client");
                Socket client = s.accept();
                System.out.println("client ist da");
                ClientCommunication clientCommunication = new ClientCommunication(client);
                clientCommunication.handleCommands();
            }
        } catch (IOException | PersonLoadException e) {
            e.printStackTrace();
        }
    }
}
