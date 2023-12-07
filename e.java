import java.util.*;
import static java.lang.Math.min;
class Pair implements Comparable<Pair> {
    public int x;
    public int y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Pair() {
        this.x = 0;
        this.y = 0;
    }
    // Optional: Override toString() for better readability
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    // Implement Comparable interface
    @Override
    public int compareTo(Pair other) {
        // Compare based on the sum of x and y
        if (this.x == other.x) return Integer.compare(this.y, other.y);
        return Integer.compare(this.x, other.x);
    }
}
public class Main {
    // Example usage in main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        Pair[] a = new Pair[n];
        for (int i = 0; i < n; i++) a[i] = new Pair(sc.nextInt(), 0);
        for (int i = 0; i < n; i++) a[i].y = -sc.nextInt();
        Arrays.sort(a);

        int len = 0;
        for (int i = 0, l = 0; i < n; i++) {
            if (i + 1 >= n || a[i + 1].x != a[i].x) {
                len++;
                l = i + 1;
            }
        }
        if (len < k) System.out.println(-1);
        else {
            int[] v = new int[len];
            int idx = 0;
            for (int i = 0, l = 0; i < n; i++) {
                if (i + 1 >= n || a[i + 1].x != a[i].x) {
                    v[idx++] = -a[i].y;
                    l = i + 1;
                }
            }
            Arrays.sort(v);
            long ans = 0;
            for(int i = 0; i < k; i++) ans += v[i];
            System.out.println(ans);
        }
    }
}