import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = sc.next();
        int[] a = new int[n];
        int c = 0;
        for (int i = 0; i < n; i++) if (s.charAt(i) == '0') c++;
        if (c == 1) {
            System.out.println(-1);
        } else {
            int[] b = new int[c];
            int idx = 0;
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == '1') {
                    a[i] = i + 1;
                } else {
                    b[idx++] = i;
                }
            }
            for(int i = 0; i < c; i++) {
                a[b[i]] = b[(i + 1) % c] + 1;
            }
            int flg = 0;
            for (int i = 0; i < n; i++) {
                if (flg == 1) System.out.print(" ");
                flg = 1;
                System.out.print(a[i]);
            }
            System.out.println();
        }
    }
}