import java.util.Scanner;

public class l5t2_improved {
    public static int[] next = new int[300002];
    public static int[] P = new int[600002];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s1, s2;
        s1 = in.next();
        s2 = in.next();
        int l1 = s1.length();
        int l2 = s2.length();
        int ans = -1, start = 0, tempL, t;
        String temp, ts2;
        while (start < l2 && l2 - start + 1 > ans) {
            ts2 = s2.substring(start);
            temp = KMP(s1, ts2);
            tempL = temp.length();
            if (tempL > ans) {
                t = MLC(temp);
                if (t > 0) ans = Math.max(ans, t);
            }
            start ++;
        }
        System.out.println(ans);
    }

    public static int MLC(String sPre) {
        String s = preS(sPre);
        int l = s.length();
        //for (int i = 0; i < l; i++) P[i] = 0;
        int c = 0, r = 0;
        for (int i = 1; i < l - 1; i++) {
            int m = 2 * c - i;
            if (r > i) {
                P[i] = Math.min(r - i, P[m]);
            } else {
                P[i] = 0;
            }
            while (s.charAt(i + 1 + P[i]) == s.charAt(i - 1 - P[i])) {
                P[i]++;
            }
            if (i + P[i] > r) {
                c = i;
                r = i + P[i];
            }
        }
        int maxLen = 0;
        for (int i = 1; i < l - 1; i++) {
            if (P[i] > maxLen) {
                maxLen = P[i];
            }
        }
        return maxLen;

    }

    public static String preS(String s) {
        int n = s.length();
        if (n == 0) {
            return "@$";
        }
        String ret = "@";
        for (int i = 0; i < n; i++)
            ret += "#" + s.charAt(i);
        ret += "#$";
        return ret;
    }

    public static String KMP(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();
        String s;
        int k = -1, j = 0, i;
        next[0] = -1;
        while (j < l2 - 1) {
            if (k == -1 || s2.charAt(k) == s2.charAt(j)) {
                j++;
                k++;
                next[j] = k;
            } else k = next[k];
        }
        j = 0;
        i = 0;
        int ans = 0;
        while (i < l1 && j < l2) {
            if (j == -1) {
                j++;
                i++;
            } else if (s1.charAt(i) == s2.charAt(j)) {
                j++;
                i++;
                ans = Math.max(ans, j);
            } else {
                j = next[j];
            }

        }
        if (ans > 0) return s2.substring(0, ans);
        else return "";
    }
}
