import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long[] a = new long[n];
		for(int i = 0; i < n; i++) a[i] = sc.nextLong();
		PriorityQueue<Long> q = new PriorityQueue<>();
		int cnt = 0;
		for(int i = 0; i < n; i++) {
			while(a[i] % 2 == 0) {
				cnt++;
				a[i] /= 2;
			}
			q.offer(a[i]);
		}
		for(int i = 0; i < cnt; i++) {
			long top = q.poll();
			q.offer(top * 3);
		}
		long ans = q.peek();
		System.out.println(ans);
	}
}