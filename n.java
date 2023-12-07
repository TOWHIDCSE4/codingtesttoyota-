import java.util.*;

public class Main {
    public static final int mod = 1000000007; 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        int[] s = new int[n];
        for(int i = 0; i < n; i++) s[i] = a[i] = sc.nextInt();
        Arrays.sort(s);
        int c = 0;
        for(int i = 0; i < n; i++) {
            if(i + 1 >= n || s[i + 1] != s[i]) {
                c++;
            }
        }
        int[] v = new int[c];
        for(int i = 0, idx = 0; i < n; i++) {
            if(i + 1 >= n || s[i + 1] != s[i]) {
                v[idx++] = s[i];
            }
        }
        BIT L = new BIT(c), R = new BIT(c);
        long ans = 0;
        for(int i = 0; i < n; i++) {
            int id = -1, l = 0, r = c - 1;
            while(l <= r) {
                int mid = (l + r) / 2;
                if(v[mid] <= a[i]) {
                    id = mid;
                    l = mid + 1;
                }
                else r = mid - 1;
            }
            long lft = L.query(id);
            long rgt = R.query(id + 1, c);
            L.add(id, rgt + 1);
            R.add(id, lft + 1);
            ans = (ans + lft + rgt) % mod;
        }
        System.out.println(ans);
    }
    static class BIT {
        int n;
        long[] a;
        public BIT(int n) {
            this.n = n;
            a = new long[n + 1];
        }
        void add(int i, long v) {
            i++;
            for(; i <= n; i += i & -i) a[i - 1] = (a[i - 1] + v) % mod;
        }
        long query(int i) {
            long sum = 0;
            for(; i > 0; i -= i & -i) sum = (sum + a[i - 1]) % mod;
            return sum;
        }
        long query(int l, int r) {
            return (query(r) - query(l) + mod) % mod;
        }
    }
}