import java.util.Scanner;

public class l5t2 {
    public static int[] next = new int[300002];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s1, s2;
        s1 = in.next();
        s2 = in.next();
        int l1 = s1.length();
        int l2 = s2.length();
        int count = 0;
        String temp = "";
        int l, r, max = -1;
        int x=l2/2;
        for (int i = x; i != x-1; i=(i+1)%l2) {
            temp = "" + s2.charAt(i);
            l = i;
            r = i;
            if (r - l + 1 > max) {
                if (check(s1, temp)) max = r - l + 1;
            }
            while (l - 1 >= 0 && r + 1 < l2 && s2.charAt(l - 1) == s2.charAt(r + 1)) {
                l--;
                r++;

            }
            temp=s2.substring(l,r+1);
            while (r - l + 1 > max) {
                if (check(s1, temp)) {
                    max = r - l + 1;
                    break;
                }
                r--;
                l++;
                temp=temp.substring(1,r-l+2);
            }
            if (i + 1 < l2 && s2.charAt(i) == s2.charAt(i + 1)) {
                temp = "" + s2.charAt(i) + s2.charAt(i + 1);
                l = i;
                r = i + 1;
                if (r - l + 1 > max) {
                    if (check(s1, temp)) max = r - l + 1;
                }
                while (l - 1 >= 0 && r + 1 < l2 && s2.charAt(l - 1) == s2.charAt(r + 1)) {
                    l--;
                    r++;

                }
                temp=s2.substring(l,r+1);
                while (r - l + 1 > max) {
                    if (check(s1, temp)) {
                        max = r - l + 1;
                        break;
                    }
                    r--;
                    l++;
                    temp=temp.substring(1,r-l+2);
                }
            }
        }
        System.out.println(max);
    }

    static void mergesort(String[] s1, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) / 2;
        mergesort(s1, l, mid);
        mergesort(s1, mid + 1, r);
        String[] temp1 = new String[r - l + 1];
        if (r + 1 - l >= 0) System.arraycopy(s1, l, temp1, 0, r + 1 - l);
        int i = l;
        int j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i == mid + 1) {
                s1[k] = temp1[j - l];
                j++;
            } else if (j == r + 1) {
                s1[k] = temp1[i - l];
                i++;
            } else if (temp1[i - l].length() > temp1[j - l].length()) {
                s1[k] = temp1[i - l];
                i++;
            } else {
                s1[k] = temp1[j - l];
                j++;
            }
        }
    }

    public static boolean check(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();
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
        int ans = -1;
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
        return j == l2;
    }
}
