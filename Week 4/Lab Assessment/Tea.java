// Q1-W4-A: Nuances of java.util.Scanner

import java.util.Scanner;

public class Tea {
    public static void main(String[] args) {
        String input = "1\nEarl Grey\n2\nGyokuro\n3\nLapsang Souchong\n";
        Scanner sc = new Scanner(input);
        while (sc.hasNextLine()) {
            System.out.print(sc.nextInt());
            sc.nextLine();
            //Add something here
            System.out.print(" ");
            System.out.println(sc.nextLine());
        }
        sc.close();
    }
}