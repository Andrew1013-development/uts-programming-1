// Q1-W9: Simple Read Loop

import java.io.*;
import java.util.*;

public class ReadNumbers {

    public static List<Integer> readFile(String filename) {
        //Create the list and read the data from the file.
        List<Integer> list = new ArrayList<>();
        File f = new File(filename);
        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNextInt()) {
                list.add(sc.nextInt());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {

        List<Integer> numbers = readFile(args[0]);

        System.out.println(numbers);
    }
}