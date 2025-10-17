// Q2-W4-A: The Full Magilla

import java.util.Scanner;

public class Marks {
    public static String names[];
    public static double scores[];
	public static void main(String[] args) {
		int n, best_index = 0, worst_index = 0;
        double best_score = Double.MIN_VALUE, worst_score = Double.MAX_VALUE, total_score = 0.0;
        Scanner sc;

        // input
        sc = new Scanner(System.in);
        System.out.print("How many students are there? ");
        n = sc.nextInt();
        names = new String[n];
        scores = new double[n];
        for (int i = 0; i < n; i++) {
            sc.nextLine();
            System.out.printf("Enter name of student %d: ", i+1);
            names[i] = sc.nextLine();
            System.out.printf("Enter mark %d: ", i+1);
            scores[i] = sc.nextDouble();
        }
        sc.close();
        // processing
        for (int i = 0; i < n; i++) {
            total_score += scores[i];
            if (scores[i] > best_score) {
                best_index = i;
                best_score = scores[i];
            }
            if (scores[i] < worst_score) {
                worst_index = i;
                worst_score = scores[i];
            }
        }
        // result
        System.out.printf("The average mark is: %s\n", Double.toString(total_score / n));
        System.out.printf("The best student is %s\n", names[best_index]);
        System.out.printf("The worst student is %s\n", names[worst_index]);
	}
}