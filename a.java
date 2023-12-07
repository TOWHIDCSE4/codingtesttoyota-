import java.util.*;
import static java.lang.Math.min;
public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int a = sc.nextInt();
    int b = sc.nextInt();
    int c = sc.nextInt();
    int d = sc.nextInt();
    System.out.println(min(d, a + b - c));
  }
}