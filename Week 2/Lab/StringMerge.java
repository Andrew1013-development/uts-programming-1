// Q3-W2: Merging Strings

import java.util.Scanner;

public class StringMerge {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		String t = sc.nextLine();
		sc.close();

		//Add your code starting here.
		System.out.println(s.concat(t));
	}
}