// Q1-W2: All the Arithmetic

import java.util.Scanner;

public class Arithmetic {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the value for a: ");
		int a = sc.nextInt();
		System.out.print("Enter the value for b: ");
		int b = sc.nextInt();
		sc.close();

		//Add your code starting here.
		System.out.println(a + b);
		System.out.println(a - b);
		System.out.println(a * b);
		System.out.println(a / b);
		System.out.println(a % b);
	}	
}