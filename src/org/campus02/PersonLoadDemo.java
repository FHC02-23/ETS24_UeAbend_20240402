package org.campus02;

import java.util.ArrayList;

public class PersonLoadDemo {

    public static void main(String[] args) throws PersonLoadException {
        PersonLoader personLoader = new PersonLoader("data/persons.csv");
        ArrayList<Person> personArrayList = personLoader.load();
        System.out.println(personArrayList);
    }
}
