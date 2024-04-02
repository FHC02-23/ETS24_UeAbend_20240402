package org.campus02;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class PersonClient {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        try (Socket server = new Socket("localhost", 1111);
             ObjectInputStream ois = new ObjectInputStream(server.getInputStream());
             BufferedWriter bw = new BufferedWriter(
                     new OutputStreamWriter(server.getOutputStream())
             )
        ) {
            // schicke id 10 an den server und gebe die antwort auf der konsole aus
            bw.write("get 10");
            bw.newLine(); // !!!!!!!
            bw.flush(); // !!!!!!!

            Person person10 = (Person) ois.readObject();
            System.out.println(person10);

            bw.write("getall");
            bw.newLine();
            bw.flush();

            // jetzt bekommen wir eine liste an personen
            // personen werden einzeln geschickt
            Person person;
            while ((person = (Person) ois.readObject()) != null) {
                System.out.println(person);
            }

            bw.write("getlist");
            bw.newLine();
            bw.flush();

            System.out.println();
            System.out.println("ganze liste:");
            ArrayList<Person> people = (ArrayList<Person>) ois.readObject();
            System.out.println(people);
        }

    }
}
