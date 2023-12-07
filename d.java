import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int y = sc.nextInt();
		int cx = 0, cy = 0;
		for(int i = 1; i <= 1000000; i++) {
			if(x % i == 0) cx++;
			if(y % i == 0) cy++;
		}
		System.out.println((cx == cy ? "Z" : (cx > cy ? "X" : "Y")));
	}
}