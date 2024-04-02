package org.campus02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientCommunication {

    private Socket client;

    public ClientCommunication(Socket client) {
        this.client = client;
    }

    // hier passiert die Logik
    public void handleCommands() throws PersonLoadException {
        ArrayList<Person> people = new PersonLoader("data/persons.csv").load();
        // lesen: inputStream des clients
        // schreiben: outputStream des client
        try (BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
             ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream())
        ) {
            // zum Schreiben von Textdaten => BufferedWriter / PrintWriter
            // zum Schreiben von Objekten => ObjectOutputStream

            String cmd;
            while ((cmd = br.readLine()) != null) {
                // cmd: EXIT
                // cmd: Exit
                // cmd: exit
                if (cmd.equalsIgnoreCase("EXIT")) {
                    System.out.println("client wants to exit. bye...");
                    break;
                } else if (cmd.equalsIgnoreCase("getall")) {
                    for (Person person : people) {
                        oos.writeObject(person);
                    }
                    oos.writeObject(null);
                    oos.flush();
                } else if(cmd.equalsIgnoreCase("getlist")) {
                    oos.writeObject(people);
                    oos.flush();
                } else {
                    String[] cmds = cmd.split(" ");
                    int desiredId = Integer.parseInt(cmds[1]);
                    for (Person person : people) {
                        if (person.getId() == desiredId) {
                            oos.writeObject(person);
                            oos.flush();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
