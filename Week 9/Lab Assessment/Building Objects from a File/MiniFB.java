// Q1-W9-A: Building Objects from a File

import java.io.*;
import java.util.*;

public class MiniFB {

    public static List<Person> readInData(String filename) {
        List<Person> people = new ArrayList<Person>();
        String type, name, subject, language;
        int year;

        try (Scanner sc = new Scanner(new File(filename))) {
            while (sc.hasNext()) {
                type = sc.nextLine();
                name = sc.nextLine();
                switch (type) {
                    case "Student":
                        subject = sc.nextLine();
                        year = sc.nextInt();
                        sc.nextLine(); // force to next line
                        people.add(new Student(name, subject, year));
                        break;
                    case "Academic":
                        subject = sc.nextLine();
                        people.add(new Academic(name, subject));
                        break;
                    case "Programmer":
                        language = sc.nextLine();
                        people.add(new Programmer(name, language));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            return new ArrayList<Person>();
        }
        return people;
    }

    public static void main(String[] args) {
        List<Person> people = readInData("testFile.txt");

        System.out.println(people);
    }

}
