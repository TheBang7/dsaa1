import java.util.Scanner;

public class l1t4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long xr, yr, xc, yc, l, x = 0, y = 0, xx, yy;
        String s;
        xr = input.nextLong();
        yr = input.nextLong();
        xc = input.nextLong();
        yc = input.nextLong();
        l = input.nextLong();
        s = input.next();
        if (xr >= xc) xx = 1;
        else xx = -1;
        if (yr >= yc) yy = 1;
        else yy = -1;
        long sum = 0;
        long[] ax = new long[100002];
        long[] ay = new long[100002];
        for (int i = 0; i < l; i++) {
            if (s.charAt(i) == 'U') y++;
            if (s.charAt(i) == 'D') y--;
            if (s.charAt(i) == 'R') x++;
            if (s.charAt(i) == 'L') x--;
            ax[i + 1] = x;
            ay[i + 1] = y;
        }
        if (x * xx + y * yy == l) System.out.println("-1");
        else {
            long left = 0, t1, t2;
            int t3;
            long right = Long.MAX_VALUE-2, m;
            while (left <= right) {
                m = left + (right-left) / 2;
                t1 = m / l;
                t2 = m % l;
                t3 = (int) t2;
                if (m >= Math.abs(xr + t1 * x + ax[t3] - xc) + Math.abs(yr + y * t1 + ay[t3] - yc)) right = m - 1;
                else left = m + 1;
            }
            if (left >= Math.floor(Math.pow(10,15))) System.out.println("-1");
            else System.out.println(left);
        }

    }
}




