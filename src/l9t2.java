import java.io.*;
import java.util.StringTokenizer;

public class l9t2 {
    public static int[] f;

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        f = new int[n + 1];
        for (int i = 1; i <= n; i++) f[i] = i;
        edgeL9t2[] edges = new edgeL9t2[m];
        long sum1 = 0, sum2 = 0;
        for (int i = 0; i < m; i++) {
            edges[i] = new edgeL9t2();
            edges[i].a = in.nextInt();
            edges[i].b = in.nextInt();
            edges[i].c = in.nextLong();
            sum1 += edges[i].c;
        }
        quicksort(edges, 0, m - 1);
        for (int i = 0; i < m; i++) {
            if (find(edges[i].a) != find(edges[i].b)) {
                f[find(edges[i].a)] = find(edges[i].b);
                sum2 += edges[i].c;
            } else if (edges[i].c < 0) sum2 += edges[i].c;
        }
        out.println(sum1 - sum2);
        out.close();
    }

    public static int find(int x) {
        if (f[x] == x) return x;
        else {
            f[x] = find(f[x]);
            return f[x];
        }
    }

    static void quicksort(edgeL9t2[] s1, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) / 2;
        quicksort(s1, l, mid);
        quicksort(s1, mid + 1, r);
        edgeL9t2[] temp1 = new edgeL9t2[r - l + 1];
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
            } else if (temp1[i - l].c <= temp1[j - l].c) {
                s1[k] = temp1[i - l];
                i++;
            } else {
                s1[k] = temp1[j - l];
                j++;
            }
        }
    }
}

class edgeL9t2 {
    int a, b;
    long c;
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