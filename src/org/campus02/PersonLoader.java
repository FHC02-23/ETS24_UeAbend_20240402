package org.campus02;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PersonLoader {

    private String pathToFile;

    public PersonLoader(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public ArrayList<Person> load() throws PersonLoadException {
        ArrayList<Person> persons = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new FileReader(pathToFile)
        )) {
            String line;
            //br.readLine();
            // wenn der header übersprungen werden sollte

            while ((line = br.readLine()) != null) {
                // 1;Susi;Sorglos
                // line.split(";")
                // => wir erhalten ein Array
                // array[0] = 1
                // array[1] = Susi
                // array[2] = Sorglos
//                System.out.println(line);
                String[] data = line.split(";");

                // konvertiere einen String zu einen int-Wert
                int id = Integer.parseInt(data[0]);
                String firstName = data[1];
                String lastName = data[2];
                Person p = new Person(id, firstName, lastName);
                persons.add(p);

                // zweite Möglichkeit
//                persons.add(new Person(Integer.parseInt(data[0]), data[1], data[2]));
            }

            return persons;
        } catch (FileNotFoundException e) {
            throw new PersonLoadException("File does not exist", e);
        } catch (IOException e) {
            throw new PersonLoadException("Can not read file", e);
        }
    }
}
