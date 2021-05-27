import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int s = in.nextInt();
            int m = in.nextInt();
            int n = in.nextInt();
            if (s % 2 == 1) {
                System.out.println("impossible");
            } else {
                int gcd = gcd(m, n);
                if ((s / gcd) % 2 == 0) {
                    int ans = s / gcd - 1;
                    System.out.println(ans);
                } else {
                    System.out.println("impossible");
                }
            }
        }
    }
    public static int gcd(int a, int b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }
}