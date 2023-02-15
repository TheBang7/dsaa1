import java.util.Arrays;
import java.util.Scanner;

public class l1t2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n, s;
        long ans = 0;
        n = input.nextInt();
        s = input.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = input.nextInt();
        int l, r, m, t, b, c;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                t = s - a[i] - a[j];
                if (t < a[j]) break;
                else {
                    l = 0;
                    r = n - 1;
                    while (l <= r) {
                        m = (l + r) / 2;
                        if (t < a[m]) r = m - 1;
                        else l = m + 1;
                    }
                    //System.out.println(r);
                    c = l - 1;
                    l = 0;
                    r = n - 1;
                    while (l <= r) {
                        m = (l + r) / 2;
                        if (t <= a[m]) r = m - 1;
                        else l = m + 1;
                    }
                    b = r + 1;
                    if (c - b >= 0) {
                        // System.out.printf("%d %d %d %d %d  %d\n", a[i], a[j], t, b, c,ans);
                        if (t == a[j] && t == a[i]) {
                            ans += (long) (c - b - 1) * (c - b) * (c - b + 1) / 6;
                            i = c + 1;
                            j = i + 1;
                        } else if (t == a[j]) {
                            ans += (long) (c - b) * (c - b + 1) / 2;
                            j = c + 1;
                        } else ans += c - b + 1;

                    }
                }
            }
        }
        System.out.println(ans);
    }
}
