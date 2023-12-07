import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int x = sc.nextInt();
		int c = 0;
		for(int i = 0; i < n; i++) {
			int a = sc.nextInt();
			if(a == x) c++;
		}
		System.out.println(c);
	}
}