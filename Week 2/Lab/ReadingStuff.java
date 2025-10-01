// Q4-W2: A Little Input Challenge

//Don't forget something goes up here.
import java.util.Scanner;

public class ReadingStuff {
	
	public static void main(String[] args) {
		
		//Read something... was there something
		//that had to be done first?
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		float f = sc.nextFloat();
        sc.close();

		//Concatenate the two things.

		//Print it out.
		System.out.println(s + Float.toString(f));
	}
	
}