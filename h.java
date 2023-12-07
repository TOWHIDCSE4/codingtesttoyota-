import java.util.*;

public class Main {
    static int answer = 0, x;
    static ArrayList<Integer>[] g, gw;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        x = sc.nextInt();
        g = new ArrayList[n + 1];
        gw = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            g[i] = new ArrayList<>();
            gw[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            g[u].add(v);
            gw[u].add(w);
            g[v].add(u);
            gw[v].add(w);
        }

        for(int i = 1; i <= n; i++) dfs(i, -1, 0);
        if(answer == 1) System.out.println("Yes");
        else System.out.println("No");
    }
    static void dfs(int u, int p, long c) {
        if(c == x) answer = 1;
        for(int i = 0, sz = g[u].size(); i < sz; i++) {
            int v = g[u].get(i), w = gw[u].get(i);
            if(v != p) {
                dfs(v, u, c + w);
            }
        }
    }
}
