// Q3-W5: Implementing a Simple Method

import java.util.Scanner;

public class Divider {
	
	//Write your method here.
	//Remember that it has to have the
	//right name and right parameter list.
	
	public static int divide(int a, int b) {
		return (a - a % b) / b;
	}
	
	//You don't need to change anything below here.
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter first number: ");
		int a = sc.nextInt();
		
		System.out.print("Enter second number: ");
		int b = sc.nextInt();

		sc.close();
		
		System.out.println("The result is " + divide(a, b));
		
	}
	
}
