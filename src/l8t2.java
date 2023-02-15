import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class l8t2 {
    public static nodeL8t2[] f;

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        f = new nodeL8t2[n + 1];
        for (int i = 1; i <= n; i++) {
            f[i] = new nodeL8t2(i);
        }
        int a, b;
        for (int i = 0; i < m; i++) {
            a = in.nextInt();
            b = in.nextInt();
            f[b].nodes.add(f[a]);
        }
        nodeL8t2[] queue = new nodeL8t2[n + 1];
        int front, rear;
        int j = n;
        while (j >= 1) {
            while (j >= 1 && f[j].isInitial) j--;
            if (j > 0)
                dfs(f[j]);
        }
        for (int i = 1; i < n; i++) {
            out.print(f[i].max + " ");
        }
        out.println(f[n].max);
        out.close();
    }

    public static void dfs(nodeL8t2 x) {
        x.isInitial = true;
        int l = x.nodes.size();
        for (int i = 0; i < l; i++) {
            if (x.max > x.nodes.get(i).max) {
                x.nodes.get(i).max = x.max;
                dfs(x.nodes.get(i));
            }

        }
    }

}

class nodeL8t2 {
    int max;
    ArrayList<nodeL8t2> nodes;
    boolean isInitial;

    public nodeL8t2(int max) {
        this.max = max;
        nodes = new ArrayList<nodeL8t2>();
        isInitial = false;
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