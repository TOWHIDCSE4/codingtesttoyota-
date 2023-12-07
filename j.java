import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        BIT bit = new BIT(n);
        int cnt = 0;
        for(int i = 0; i < q; i++) {
            int t = sc.nextInt();
            int k = sc.nextInt();
            if(t == 1) {
                int d = (k <= n ? n - k + 1: k - n);
                int k_i = (k <= n ? n + d: n + 1 - d);
                int c = cnt - bit.query(d - 1);
                System.out.println((c % 2 == 0 ? k : k_i));
            } else {
                cnt++;
                bit.add(k, 1);
            }
        }
    }
    static class BIT {
        int n;
        int[] a;
        public BIT(int n) {
            this.n = n;
            a = new int[n + 1];
        }
        void add(int i, int v) {
            for(; i <= n; i += i & -i) a[i] += v;
        }
        int query(int i) {
            int sum = 0;
            for(; i > 0; i -= i & -i) sum += a[i];
            return sum;
        }
    }
}