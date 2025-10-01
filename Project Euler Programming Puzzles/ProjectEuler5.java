import java.util.Scanner;
import java.util.ArrayList;

public class ProjectEuler5 {
	static final int SIZE = 100001; // declares a constant
    static boolean[] is_prime = new boolean[SIZE];
    static ArrayList<Integer> primes = new ArrayList<Integer>(); // std::vector
    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
        sc.close();
		long temp, res = 1;
        sieve(n);
        for (Integer prime : primes) {
            if (prime > n) break;
            temp = 1;
            while (temp * prime <= n) temp *= prime;
            res *= temp;
        }
        System.out.println(res);
	}
    public static void sieve(int n) {
        for (int i = 2; i <= n; i++) is_prime[i] = true;
        for (int i = 2; i * i <= n; i++)
            if (is_prime[i])
                for (int j = i * i; j <= n; j += i)
                    is_prime[j] = false;
        for (int i = 2; i <= n; i++) 
            if (is_prime[i])
                primes.add(i);
    }
}