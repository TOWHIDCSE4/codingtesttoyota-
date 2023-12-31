// class Pair implements Comparable<Pair> {
//     public int x;
//     public int y;
//     public Pair(int x, int y) {
//         this.x = x;
//         this.y = y;
//     }
//     // Optional: Override toString() for better readability
//     @Override
//     public String toString() {
//         return "(" + x + ", " + y + ")";
//     }
//     // Implement Comparable interface
//     @Override
//     public int compareTo(Pair other) {
//         // Compare based on the sum of x and y
//         if(this.x == other.x) return Integer.compare(this.y, other.y);
//         return Integer.compare(this.x, other.x);
//     }
// }

// public class test {
// 	    // Example usage in main method
//     public static void main(String[] args) {
//         Pair pair1 = new Pair(5, 10);
//         Pair pair2 = new Pair(6, 2);

//         // Comparison using compareTo
//         if (pair1.compareTo(pair2) < 0) {
//             System.out.println(pair1 + " is less than " + pair2);
//         } else if (pair1.compareTo(pair2) > 0) {
//             System.out.println(pair1 + " is greater than " + pair2);
//         } else {
//             System.out.println(pair1 + " is equal to " + pair2);
//         }
//     }

// }


import java.util.*;
import java.io.*;

class test {
    private static final void solve() {
        final int n = ni(), q = ni(), nn = (n << 1) + 1;
        var d = new FenwickTree(n);
        for (int q_ = 0; q_ < q; q_++) {
            int t = ni(), k = ni();
            if (t == 1) {
                var x = true;
                if (k > n) {
                    k = nn - k;
                    x = false;
                }
                x ^= d.sum(0, k) % 2 == 1;
                ou.println(x ? k : nn - k);
            } else
                d.add(n - k, 1);
        }
    }

    public static void main(String[] args) {
        solve();
        ou.flush();
    }

    private static final ContestScanner sc = new ContestScanner();
    private static final ContestPrinter ou = new ContestPrinter();

    private static final int ni() {
        return sc.nextInt();
    }

    private static final int[] ni(int n) {
        return sc.nextIntArray(n);
    }

    private static final long nl() {
        return sc.nextLong();
    }

    private static final long[] nl(int n) {
        return sc.nextLongArray(n);
    }

    private static final String ns() {
        return sc.next();
    }

    private static final double nd() {
        return sc.nextDouble();
    }
}

class FenwickTree {
    private int _n;
    private long[] data;

    public FenwickTree(int n) {
        this._n = n;
        data = new long[n];
    }

    public FenwickTree(long[] data) {
        this(data.length);
        build(data);
    }

    public void set(int p, long x) {
        add(p, x - get(p));
    }

    public void add(int p, long x) {
        assert (0 <= p && p < _n);
        p++;
        while (p <= _n) {
            data[p - 1] += x;
            p += p & -p;
        }
    }

    public long sum(int l, int r) {
        assert (0 <= l && l <= r && r <= _n);
        return sum(r) - sum(l);
    }

    public long get(int p) {
        return sum(p, p + 1);
    }

    private long sum(int r) {
        long s = 0;
        while (r > 0) {
            s += data[r - 1];
            r -= r & -r;
        }
        return s;
    }

    private void build(long[] dat) {
        System.arraycopy(dat, 0, data, 0, _n);
        for (int i = 1; i <= _n; i++) {
            int p = i + (i & -i);
            if (p <= _n) {
                data[p - 1] += data[i - 1];
            }
        }
    }
}

final class ContestScanner {
    private final InputStream in;
    private final byte[] buffer = new byte[1024];
    private int ptr = 0;
    private int buflen = 0;

    public ContestScanner(InputStream in) {
        this.in = in;
    }

    public ContestScanner() {
        this(System.in);
    }

    private boolean hasNextByte() {
        if (ptr < buflen)
            return true;
        ptr = 0;
        try {
            buflen = in.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buflen > 0;
    }

    private int readByte() {
        return hasNextByte() ? buffer[ptr++] : -1;
    }

    private static boolean isPrintableChar(int c) {
        return 33 <= c && c <= 126;
    }

    public boolean hasNext() {
        while (hasNextByte() && !isPrintableChar(buffer[ptr]))
            ptr++;
        return hasNextByte();
    }

    public String next() {
        if (!hasNext())
            throw new NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        int b = readByte();
        while (isPrintableChar(b)) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    public void nextThrow(int n) {
        for (int i = 0; i < n; i++)
            this.next();
    }

    public void nextThrow() {
        this.nextThrow(1);
    }

    public long nextLong() {
        if (!hasNext())
            throw new NoSuchElementException();
        long n = 0;
        boolean minus = false;
        int b = readByte();
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        if (b < '0' || '9' < b)
            throw new NumberFormatException();
        while (true) {
            if ('0' <= b && b <= '9') {
                n *= 10;
                n += b - '0';
            } else if (b == -1 || !isPrintableChar(b))
                return minus ? -n : n;
            else
                throw new NumberFormatException();
            b = readByte();
        }
    }

    public int nextInt() {
        long nl = nextLong();
        if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE)
            throw new NumberFormatException();
        return (int) nl;
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }

    public boolean[] nextBoolean(char True) {
        String s = this.next();
        int n = s.length();
        boolean[] array = new boolean[n];
        for (int i = 0; i < n; i++)
            array[i] = s.charAt(i) == True;
        return array;
    }

    public long[] nextLongArray(int length) {
        long[] array = new long[length];
        for (int i = 0; i < length; i++)
            array[i] = this.nextLong();
        return array;
    }

    public long[] nextLongArray(int length, java.util.function.LongUnaryOperator map) {
        long[] array = new long[length];
        for (int i = 0; i < length; i++)
            array[i] = map.applyAsLong(this.nextLong());
        return array;
    }

    public long[] nextLongArray(int length, long[] a) {
        long[] array = new long[length + a.length];
        for (int i = 0; i < length; i++)
            array[i] = this.nextLong();
        for (int i = length; i < array.length; i++)
            array[i] = a[i - length];
        return array;
    }

    public int[] nextIntArray(int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++)
            array[i] = this.nextInt();
        return array;
    }

    public int[] nextIntArray(int length, java.util.function.IntUnaryOperator map) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++)
            array[i] = map.applyAsInt(this.nextInt());
        return array;
    }

    public int[] nextIntArray(int length, int[] array) {
        int n = length + array.length;
        int[] a = new int[n];
        for (int i = 0; i < length; i++)
            a[i] = this.nextInt();
        for (int i = length; i < n; i++)
            a[i] = array[i - length];
        return a;
    }

    public Integer[] nextIntegerArray(int length, java.util.function.IntUnaryOperator map) {
        Integer[] array = new Integer[length];
        for (int i = 0; i < length; i++)
            array[i] = map.applyAsInt(this.nextInt());
        return array;
    }

    public Integer[] nextIntegerArray(int length) {
        Integer[] array = new Integer[length];
        for (int i = 0; i < length; i++)
            array[i] = this.nextInt();
        return array;
    }

    public double[] nextDoubleArray(int length) {
        double[] array = new double[length];
        for (int i = 0; i < length; i++)
            array[i] = this.nextDouble();
        return array;
    }

    public double[] nextDoubleArray(int length, java.util.function.DoubleUnaryOperator map) {
        double[] array = new double[length];
        for (int i = 0; i < length; i++)
            array[i] = map.applyAsDouble(this.nextDouble());
        return array;
    }

    public String[] nextArray(int length) {
        String[] array = new String[length];
        for (int i = 0; i < length; i++)
            array[i] = this.next();
        return array;
    }

    public long[][] nextLongMatrix(int height, int width) {
        long[][] mat = new long[height][width];
        for (int h = 0; h < height; h++)
            for (int w = 0; w < width; w++)
                mat[h][w] = this.nextLong();
        return mat;
    }

    public long[][] nextLongMatrix(int height, int width, java.util.function.LongUnaryOperator map) {
        long[][] mat = new long[height][width];
        for (int h = 0; h < height; h++)
            for (int w = 0; w < width; w++)
                mat[h][w] = map.applyAsLong(this.nextLong());
        return mat;
    }

    public int[][] nextIntMatrix(int height, int width) {
        int[][] mat = new int[height][width];
        for (int h = 0; h < height; h++)
            for (int w = 0; w < width; w++)
                mat[h][w] = this.nextInt();
        return mat;
    }

    public int[][] nextIntMatrix(int height, int width, java.util.function.IntUnaryOperator map) {
        int[][] mat = new int[height][width];
        for (int h = 0; h < height; h++)
            for (int w = 0; w < width; w++)
                mat[h][w] = map.applyAsInt(this.nextInt());
        return mat;
    }

    public double[][] nextDoubleMatrix(int height, int width) {
        double[][] mat = new double[height][width];
        for (int h = 0; h < height; h++)
            for (int w = 0; w < width; w++)
                mat[h][w] = this.nextDouble();
        return mat;
    }

    public boolean[][] nextBooleanMatrix(int height, int width, char True) {
        boolean[][] mat = new boolean[height][width];
        for (int h = 0; h < height; h++) {
            String s = this.next();
            for (int w = 0; w < width; w++)
                mat[h][w] = s.charAt(w) == True;
        }
        return mat;
    }

    public char[][] nextCharMatrix(int height, int width) {
        char[][] mat = new char[height][width];
        for (int h = 0; h < height; h++) {
            String s = this.next();
            for (int w = 0; w < width; w++)
                mat[h][w] = s.charAt(w);
        }
        return mat;
    }
}

final class ContestPrinter extends PrintWriter {
    public ContestPrinter(PrintStream stream) {
        super(stream);
    }

    public ContestPrinter() {
        super(System.out);
    }

    private static String dtos(double x, int n) {
        StringBuilder sb = new StringBuilder();
        if (x < 0) {
            sb.append('-');
            x = -x;
        }
        x += Math.pow(10, -n) / 2;
        sb.append((long) x);
        sb.append(".");
        x -= (long) x;
        for (int i = 0; i < n; i++) {
            x *= 10;
            sb.append((int) x);
            x -= (int) x;
        }
        return sb.toString();
    }

    @Override
    public void print(float f) {
        super.print(dtos(f, 20));
    }

    @Override
    public void println(float f) {
        super.println(dtos(f, 20));
    }

    @Override
    public void print(double d) {
        super.print(dtos(d, 20));
    }

    @Override
    public void println(double d) {
        super.println(dtos(d, 20));
    }

    public void printlnArray(String[] array) {
        for (String i : array)
            super.println(i);
    }

    public void printSpace(Object... o) {
        int n = o.length - 1;
        for (int i = 0; i < n; i++) {
            super.print(o[i]);
            super.print(" ");
        }
        super.println(o[n]);
    }

    public void println(Object... o) {
        int n = o.length - 1;
        for (int i = 0; i < n; i++)
            super.print(o[i]);
        super.println(o[n]);
    }

    public void printYN(boolean o) {
        super.println(o ? "Yes" : "No");
    }

    public void print(Object... o) {
        int n = o.length - 1;
        for (int i = 0; i < n; i++)
            super.print(o[i]);
        super.print(o[n]);
    }

    public void printArray(Object[] array) {
        int n = array.length - 1;
        for (int i = 0; i < n; i++) {
            super.print(array[i]);
            super.print(" ");
        }
        super.println(array[n]);
    }

    public void printlnArray(Object[] array) {
        for (Object i : array)
            super.println(i);
    }

    public void printArray(int[] array, String separator) {
        int n = array.length - 1;
        if (n == -1)
            return;
        for (int i = 0; i < n; i++) {
            super.print(array[i]);
            super.print(separator);
        }
        super.println(array[n]);
    }

    public void printArray(int[] array) {
        this.printArray(array, " ");
    }

    public void printArray(Integer[] array) {
        this.printArray(array, " ");
    }

    public void printArray(Integer[] array, String separator) {
        int n = array.length - 1;
        for (int i = 0; i < n; i++) {
            super.print(array[i]);
            super.print(separator);
        }
        super.println(array[n]);
    }

    public void printlnArray(int[] array) {
        for (int i : array)
            super.println(i);
    }

    public void printArray(int[] array, String separator, java.util.function.IntUnaryOperator map) {
        int n = array.length - 1;
        for (int i = 0; i < n; i++) {
            super.print(map.applyAsInt(array[i]));
            super.print(separator);
        }
        super.println(map.applyAsInt(array[n]));
    }

    public void printlnArray(int[] array, java.util.function.IntUnaryOperator map) {
        for (int i : array)
            super.println(map.applyAsInt(i));
    }

    public void printlnArray(long[] array, java.util.function.LongUnaryOperator map) {
        for (long i : array)
            super.println(map.applyAsLong(i));
    }

    public void printArray(int[] array, java.util.function.IntUnaryOperator map) {
        this.printArray(array, " ", map);
    }

    public void printArray(long[] array, String separator) {
        int n = array.length - 1;
        for (int i = 0; i < n; i++) {
            super.print(array[i]);
            super.print(separator);
        }
        super.println(array[n]);
    }

    public void printArray(long[] array) {
        this.printArray(array, " ");
    }

    public void printlnArray(long[] array) {
        for (long i : array)
            super.println(i);
    }

    public void printArray(double[] array) {
        printArray(array, " ");
    }

    public void printArray(double[] array, String separator) {
        int n = array.length - 1;
        for (int i = 0; i < n; i++) {
            super.print(array[i]);
            super.print(separator);
        }
        super.println(array[n]);
    }

    public void printlnArray(double[] array) {
        for (double i : array)
            super.println(i);
    }

    public void printArray(boolean[] array, String a, String b) {
        int n = array.length - 1;
        for (int i = 0; i < n; i++)
            super.print((array[i] ? a : b) + " ");
        super.println(array[n] ? a : b);
    }

    public void printArray(boolean[] array) {
        this.printArray(array, "Y", "N");
    }

    public void printArray(char[] array) {
        for (char c : array)
            this.print(c);
        this.println();
    }

    public void printArray(long[] array, String separator, java.util.function.LongUnaryOperator map) {
        int n = array.length - 1;
        for (int i = 0; i < n; i++) {
            super.print(map.applyAsLong(array[i]));
            super.print(separator);
        }
        super.println(map.applyAsLong(array[n]));
    }

    public void printArray(long[] array, java.util.function.LongUnaryOperator map) {
        this.printArray(array, " ", map);
    }

    public void printArray(ArrayList<?> array) {
        this.printArray(array, " ");
    }

    public void printArray(ArrayList<?> array, String separator) {
        int n = array.size() - 1;
        if (n == -1)
            return;
        for (int i = 0; i < n; i++) {
            super.print(array.get(i).toString());
            super.print(separator);
        }
        super.println(array.get(n).toString());
    }

    public void printlnArray(ArrayList<?> array) {
        int n = array.size();
        for (int i = 0; i < n; i++)
            super.println(array.get(i).toString());
    }

    public void printlnArray(ArrayList<Integer> array, java.util.function.IntUnaryOperator map) {
        int n = array.size();
        for (int i = 0; i < n; i++)
            super.println(map.applyAsInt(array.get(i)));
    }

    public void printlnArray(ArrayList<Long> array, java.util.function.LongUnaryOperator map) {
        int n = array.size();
        for (int i = 0; i < n; i++)
            super.println(map.applyAsLong(array.get(i)));
    }

    public void printArray(int[][] array) {
        for (int[] a : array)
            this.printArray(a);
    }

    public void printArray(int[][] array, java.util.function.IntUnaryOperator map) {
        for (int[] a : array)
            this.printArray(a, map);
    }

    public void printArray(long[][] array) {
        int n = array.length;
        if (n == 0)
            return;
        int m = array[0].length - 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                super.print(array[i][j] + " ");
            super.println(array[i][m]);
        }
    }

    public void printArray(long[][] array, java.util.function.LongUnaryOperator map) {
        int n = array.length;
        if (n == 0)
            return;
        int m = array[0].length - 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                super.print(map.applyAsLong(array[i][j]));
                super.print(" ");
            }
            super.println(map.applyAsLong(array[i][m]));
        }
    }

    public void printArray(boolean[][] array) {
        int n = array.length;
        if (n == 0)
            return;
        int m = array[0].length - 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                super.print(array[i][j] ? "o" : ".");
            super.println(array[i][m] ? "o" : ".");
        }
    }

    public void printArray(char[][] array) {
        int n = array.length;
        if (n == 0)
            return;
        int m = array[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                super.print(array[i][j]);
            super.println();
        }
    }
}