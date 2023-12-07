import java.util.*;
import static java.lang.Math.min;
import static java.lang.Math.max;

public class Main {
	static long floor(long a, long b) {
		if(a < 0) return (a + b - 1) / b;
		return a / b;
	}
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long k = sc.nextLong();
        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        for(int i = 0; i < n; i++) {
        	a[i] = sc.nextInt();
        	b[i] = sc.nextInt();
        	c[i] = sc.nextInt();
        }
        long l = 0, r = 1000000000000000000L, an = -1;
        while(l <= r) {
        	long mid = (l + r) / 2;
        	long cnt = 0;
        	for(int i = 0; i < n; i++) {
        		cnt += min(max(floor(mid - b[i] + c[i], c[i]), 0L), (long)a[i]);
        	}
        	if(cnt >= k) {
        		an = mid;
        		r = mid - 1;
        	} else {
        		l = mid + 1;
        	}
        }
        System.out.println(an);
    }
}