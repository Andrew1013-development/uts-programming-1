import java.util.Scanner;
//import java.util.HashMap;
//import java.time.Duration;
//import java.time.LocalTime;

public class ProjectEuler14 {
    static int LIMIT = 5_000_000;
    static int[] cache2 = new int[LIMIT+1];
    static int steps_optimized(long n) {
        long n2, n3;
        int res = 0;

        n2 = n3 = n;
        // traverse path to compute sequence length
        while (n != 1 && (n >= LIMIT || cache2[(int)n] == 0)) {
            switch ((int)(n % 2)) {
                case 1:
                    n >>= 1;
                    break;
                default:
                    n *= 3;
                    n += 1;
            }
            res++;
        }
        
        if (cache2[(int)n] != 0) res += cache2[(int)n];
        else res++;
        
        // traverse again to back-fill
        while (n2 != 1 && (n2 >= LIMIT || cache2[(int)n2] == 0)) {
            if (n2 <= LIMIT) cache2[(int)n2] = res;
            switch ((int)(n2 % 2)) {
                case 1:
                    n2 >>= 1;
                    break;
                default:
                    n2 *= 3;
                    n2 += 1;
            }
            res--;
        }
        return cache2[(int)n3];
    }
	public static void main(String[] args) {
		Scanner sc;
        long n, ans = 1;
        int seq, max_seq = 1;
        //LocalTime t1, t2;

		sc = new Scanner(System.in);
		n = sc.nextInt();
		sc.close();

        //t1 = LocalTime.now();
        for (long i = 2; i <= n; i++) {
            //seq = steps(i);
            seq = steps_optimized(i);
            if (max_seq < seq) {
                ans = i;
                max_seq = seq;
            }
            //seq2 = steps_optimized(i);
            //if (seq != seq2) System.out.printf("Mismatch found on n=%d (%d vs ans=%d)\n", i, seq2, seq);
        } 
        //t2 = LocalTime.now();
        System.out.println(ans);
        //System.out.printf("time taken: %f\n", (float)Duration.between(t1, t2).toMillis() / 1000);
    }
    /* old code starts here, only runs to n < 1e6
    static HashMap<Long, Integer> cache = new HashMap<Long, Integer>();
    static int steps(long n) {
        long n2;
        
        if (n == 1) return 1;
        if (cache.containsKey(n)) return cache.get(n);

        n2 = n;
        switch ((int)(n2 % 2)) {
            case 0:
                n2 /= 2;
                break;
            default:
                n2 *= 3;
                n2 += 1;
                break;
        }
        if (n > LIMIT) return 1 + steps(n2);
        cache.put(n, 1 + steps(n2));
        return cache.get(n);
    }
    */
}