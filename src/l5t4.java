import java.util.Scanner;

public class l5t4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int l = in.nextInt();
        l--;
        int[] next = new int[2 * l + 3];
        String s1;
        String t;
        StringBuilder s2 = new StringBuilder();
        s1 = in.next();
        t = in.next();
        for (int i = l-1; i>=0; i--) s2.append(findInverse(t.charAt(i)));
        boolean f = true;
        s2.append("@#").append(s1);
        int l2 = s2.length();
        int k = -1, j = 0, i;
        next[0] = -1;
        while (j < l2 - 1) {
            if (k == -1 || s2.charAt(k) == s2.charAt(j)) {
                j++;
                k++;
                if (s2.charAt(k) == s2.charAt(j))
                    next[j] = k;
                else next[j] = next[k];
            } else k = next[k];
        }
        f = !(next[l2 - 1] >= 0 && s2.charAt(next[l2 - 1]) == s2.charAt(l2 - 1));
        if (f) System.out.println("YES");
        else System.out.println("NO");
    }

    public static char findInverse(char x) {
        if (x == 'N') return 'S';
        if (x == 'S') return 'N';
        if (x == 'E') return 'W';
        return 'E';
    }

}
