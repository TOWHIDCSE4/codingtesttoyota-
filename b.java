import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] a = new int[n];
		for(int i = 0; i < n; i++) a[i] = sc.nextInt();
		int[] b = new int[m];
		for(int i = 0; i < m; i++) b[i] = sc.nextInt();
		Arrays.sort(a);
		Arrays.sort(b);
		int flg = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(a[i] == b[j]) {
					if(flg == 1) System.out.print(" ");
					flg = 1;
					System.out.print(a[i]);
				}
			}
		}
	}
}