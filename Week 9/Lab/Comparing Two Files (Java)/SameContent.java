// Q4-W9: Comparing Two Files (Java)

import java.io.*;
import java.util.*;

public class SameContent {

    public static boolean sameContent(String filename1, String filename2) {
        String content1 = "", content2 = "";
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(filename1));
            while (br.ready()) {
                content1 += (br.readLine() + System.lineSeparator());
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            br = new BufferedReader(new FileReader(filename2));
            while (br.ready()) {
                content2 += (br.readLine() + System.lineSeparator());
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content1.equals(content2);
    }

    public static void main(String[] args) {
        if (sameContent(args[0], args[1])) {
            System.out.println("The two files have the same content.");
        }
        else {
            System.out.println("The two files do not have the same content.");
        }
    }
}
