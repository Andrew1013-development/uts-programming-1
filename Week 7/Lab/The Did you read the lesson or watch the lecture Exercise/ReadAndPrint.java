// Q1-W7: The "Did you read the lesson or watch the lecture" Exercise

import java.util.Scanner;
import java.util.ArrayList;

public class ReadAndPrint {
	public static void main(String[] args) {
		int n = 0;
        Scanner sc;

		sc = new Scanner(System.in);
		
		//Create an ArrayList
        ArrayList<Integer> array = new ArrayList<>();
		//Read in user input, storing it in the 
		//ArrayList until -1 is entered.
		do {
            n = sc.nextInt();
            if (n == -1) break;
            array.add(n);
        } while (n != -1);

		//Print out the whole ArrayList in one go.
        System.out.println(array);
	}
}
