// Q4-W9: Comparing Two Files (Java)

import java.io.*;
import java.util.*;

public class SameContent {

    public static boolean sameContent(String filename1, String filename2) {
        //This is the method to complete.
        return false;
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
