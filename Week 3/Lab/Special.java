// Q4-W3: Practice With Different Operators

import java.util.Scanner;

public class Special {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();
		if ((n % 3 == 0 && n >= 53) || (n % 2 == 1 && n < 12)) {
			System.out.println("This number is special");
		}
		else {
			System.out.println("This number is not special");
		}
			
	}
}