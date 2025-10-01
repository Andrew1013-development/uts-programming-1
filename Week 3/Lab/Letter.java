// Q6-W3: Choosing Your Condition Carefully

import java.util.Scanner;

public class Letter {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		char c = sc.next().charAt(0);
        sc.close();
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') System.out.println("Vowel");
        else System.out.println("Consonant");
		//Add your conditional statement here
	}
}