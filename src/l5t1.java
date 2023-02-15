import java.io.*;
import java.util.StringTokenizer;

public class l5t1 {
    public static String ans;
    public static int tot;

    public static void main(String[] args) {
        QReader in = new QReader();
        int T = in.nextInt();
        String S;
        int t, count, move, sLen, sSub, count2, left, right;
        String[] sub = new String[11];
        boolean f;
        partL5T1[] a = new partL5T1[1002];
        partL5T1 temp;
        partL5T1[] ans = new partL5T1[101];
        for (int tt = 1; tt <= T; tt++) {
            S = in.next();
            sLen = S.length();
            t = in.nextInt();
            for (int i = 1; i <= t; i++) sub[i] = in.next();
            count = 0;
            for (int i = 1; i <= t; i++) {
                sSub = sub[i].length();
                for (int j = 0; j <= sLen-sSub; j++) {
                    if (sub[i].charAt(0) == S.charAt(j)) {
                        move = 0;
                        while (move < sSub && j + move < sLen && sub[i].charAt(move) == S.charAt(j + move)) move++;
                        if (move == sSub) {
                            count++;
                            a[count] = new partL5T1(j, j + sSub - 1, i);
                        }
                    }
                }
            }
            mergesort(a, 1, count);
            f = true;
            if (count==0||a[1].left != 0) {
                System.out.println("-1");
                f = false;
            } else {
                count2 = 1;
                move = 1;
                while (move <count && a[move+1].left == 0) move++;
                temp = a[move];
                ans[1] = a[move];
                right = a[move].right;
                move++;
                while (move <= count) {
                    if (right == sLen - 1) {
                        print(ans, count2);
                        f = false;
                        break;
                    } else if (a[move].left > right + 1) {
                        System.out.println(-1);
                        f = false;
                        break;
                    } else {
                        temp = a[move];
                        while (move < count && a[move + 1].left <= right + 1) {
                            move++;
                            if (a[move].right > temp.right) temp = a[move];
                        }
                        count2++;
                        ans[count2] =temp;
                        right = temp.right;
                        move++;
                    }
                }
                if (f) {
                    if (right == sLen - 1) print(ans, count2);
                    else System.out.println(-1);
                }
            }

        }
    }

    static void print(partL5T1[] a, int n) {
        System.out.println(n);
        for (int i = 1; i <= n; i++) {
            System.out.printf("%d %d\n",a[i].pos,(a[i].left + 1));
        }
    }

    static void mergesort(partL5T1[] s1, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) / 2;
        mergesort(s1, l, mid);
        mergesort(s1, mid + 1, r);
        partL5T1[] temp1 = new partL5T1[r - l + 1];
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
            } else if (temp1[i - l].left < temp1[j - l].left || temp1[i - l].left == temp1[j - l].left && temp1[i - l].right < temp1[j - l].right) {
                s1[k] = temp1[i - l];
                i++;
            } else {
                s1[k] = temp1[j - l];
                j++;
            }
        }
    }
}


class partL5T1 {
    int left, right, pos;

    public partL5T1(int left, int right, int pos) {
        this.left = left;
        this.right = right;
        this.pos = pos;
    }
}

//class QReader {
//    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//    private StringTokenizer tokenizer = new StringTokenizer("");
//
//    private String innerNextLine() {
//        try {
//            return reader.readLine();
//        } catch (IOException e) {
//            return null;
//        }
//    }
//
//    public boolean hasNext() {
//        while (!tokenizer.hasMoreTokens()) {
//            String nextLine = innerNextLine();
//            if (nextLine == null) {
//                return false;
//            }
//            tokenizer = new StringTokenizer(nextLine);
//        }
//        return true;
//    }
//
//    public String nextLine() {
//        tokenizer = new StringTokenizer("");
//        return innerNextLine();
//    }
//
//    public String next() {
//        hasNext();
//        return tokenizer.nextToken();
//    }
//
//    public int nextInt() {
//        return Integer.parseInt(next());
//    }
//
//    public long nextLong() {
//        return Long.parseLong(next());
//    }
//}
//
//class QWriter implements Closeable {
//    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
//
//    public void print(Object object) {
//        try {
//            writer.write(object.toString());
//        } catch (IOException e) {
//            return;
//        }
//    }
//
//    public void println(Object object) {
//        try {
//            writer.write(object.toString());
//            writer.write("\n");
//        } catch (IOException e) {
//            return;
//        }
//    }
//
//    @Override
//    public void close() {
//        try {
//            writer.close();
//        } catch (IOException e) {
//            return;
//        }
//    }
//}