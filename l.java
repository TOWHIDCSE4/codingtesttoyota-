import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long k = sc.nextLong();
		long[] c = new long[n + 1];
		for(int i = 1; i <= n; i++) c[i] = c[i - 1] + sc.nextInt();
		Arrays.sort(c);
		long l = 0, r = 1000000000000000000L, an = -1;
		while(l <= r) {
			long mid = (l + r) / 2;
			long temp = 0;
			int limit = 0;
			for(int i = 0; i < n; i++) {
				while(limit < n && c[limit + 1] - c[i] < mid) limit++;
				temp += limit - i;
			}
			if(temp < k) {
				an = mid;
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
		System.out.println(an);
	}
}