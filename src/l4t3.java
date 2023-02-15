import java.io.*;
import java.util.StringTokenizer;

public class l4t3 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n, k, q;
        n = in.nextInt();
        k = in.nextInt();
        q = in.nextInt();
        int[] a = new int[n + 1];
        ji[] ans = new ji[n + 1];
        int sum = 0;
        l4t3nod[] nods = new l4t3nod[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = in.nextInt();
            nods[i] = new l4t3nod();
            nods[i].pos = i;
            nods[i].val = a[i];
            ans[i] = new ji();
            ans[i].ans = 0;
            ans[i].pos = 0;
        }
        quicksort(nods, 1, n);
        for (int i = 1; i <= n; i++) {
            int j = ans[nods[i].pos].pos;
            while( j < k && nods[i].pos - j > 0) {
                if (ans[nods[i].pos - j].ans < nods[i].val) {
                    ans[nods[i].pos - j].ans = nods[i].val;
                    ans[nods[i].pos - j].pos = k - j;
                    sum++;
                    j++;
                }
                else j+=Math.max(ans[nods[i].pos - j].pos,1);
            }
            if(sum==n)break;
        }
        for (int i = 1; i <= q; i++) {
            out.println(ans[in.nextInt()].ans);
        }
//        for (int i = 1; i <= n; i++) {
//            out.println(ans[i].ans);
//        }
        out.close();
    }
    static void quicksort(l4t3nod[] s1, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) / 2;
        quicksort(s1, l, mid);
        quicksort(s1, mid + 1, r);
        l4t3nod[] temp1 = new l4t3nod[r - l + 1];
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
            } else if (temp1[i - l].val > temp1[j - l].val) {
                s1[k] = temp1[i - l];
                i++;
            } else {
                s1[k] = temp1[j - l];
                j++;
            }
        }
    }
}

class l4t3nod {
    int val, pos;
}

class ji {
    int ans, pos;
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