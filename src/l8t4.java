import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class l8t4 {
    public static int[] f;
    public static int n;
    public static boolean[] check;

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        n = in.nextInt();
        f = new int[n + 1];
        check = new boolean[n + 1];
        for (int i = 1; i <= n; i++) f[i] = in.nextInt();
        mergeSort(f, 1, n);
        if (checkU()) out.println("YES");
        else out.println("NO");
        if (checkSU()) out.println("YES");
        else out.println("NO");
        if (checkTree()) out.println("YES");
        else out.println("NO");
        out.close();
    }

    static void mergeSort(int[] s1, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) / 2;
        mergeSort(s1, l, mid);
        mergeSort(s1, mid + 1, r);
        int[] temp1 = new int[r - l + 1];
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
            } else if (temp1[i - l] >= temp1[j - l]) {
                s1[k] = temp1[i - l];
                i++;
            } else {
                s1[k] = temp1[j - l];
                j++;
            }
        }
    }


    public static boolean checkTree() {
        if (f[n] < 1) return false;
        long count = 0;
        for (int i = 1; i <= n; i++) count += f[i];
        return count == (long) (n - 1) * 2;
    }

    public static boolean checkU() {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            count += f[i] % 2;
        }
        return count % 2 == 0;
    }

    public static boolean checkSU() {
        long l = 0, r = 0;
        int pos = n;
        long[] sum = new long[n + 1];
        sum[0] = 0;
        for (int i = 1; i <= n; i++)
            sum[i] = sum[i - 1] + f[i];
        for (int i = 1; i <= n; i++) {
            l += f[i];
            while (pos > 0 && i > f[pos]) pos--;
            if (pos > i) r = (long) i * (i - 1) + sum[n] - sum[pos] + (long) (pos - i) * i;
            else r = (long) i * (i - 1) + sum[n] - sum[i];
            if (l > r) return false;
        }
        return l % 2 == 0;
    }
}

class comp implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return o2.compareTo(o1);
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