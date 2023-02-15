import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class l6t1 {
    public static int count = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long sum = in.nextLong();
        int a, b, c, t;
        nodeL6t1[] nodes = new nodeL6t1[n + 1];
        for (int i = 1; i <= n; i++) nodes[i] = new nodeL6t1();
        for (int i = 0; i < n - 1; i++) {
            a = in.nextInt();
            b = in.nextInt();
            c = in.nextInt();
            nodes[a].son.add(nodes[b]);
            nodes[a].val.add(c);
            nodes[b].son.add(nodes[a]);
            nodes[b].val.add(c);
        }
        nodes[1].v=true;
        dfs(nodes[1], 0, sum);
        System.out.println(count);
    }

    public static void dfs(nodeL6t1 node, long sum1, long sum) {
        boolean f = true;
        int l = node.son.size();
        for (int i = 0; i < l; i++)
            if (!node.son.get(i).v) {
                f = false;
                node.son.get(i).v = true;
                dfs(node.son.get(i), sum1 + node.val.get(i), sum);
            }
        if (f && sum1 == sum) count++;
    }
}

class nodeL6t1 {
    ArrayList<Integer> val;
    ArrayList<nodeL6t1> son;
    nodeL6t2 f;
    boolean v;

    public nodeL6t1() {
        val = new ArrayList<Integer>();
        son = new ArrayList<nodeL6t1>();
        v = false;
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