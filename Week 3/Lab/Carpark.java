// Q2-W3: A Simple Condition

import java.util.Scanner;

public class Carpark {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();
		//Your code goes here!
		if (n < 37) System.out.println("Tails");
        else System.out.println("Heads");
	}
}