import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class l6t4 {
    public static long sum = 0;

    public static void main(String[] args) {
        QReader in = new QReader();
        int n = in.nextInt();
        int a, b, c, t;
        nodeL6t4[] nodes = new nodeL6t4[n + 1];
        for (int i = 1; i <= n; i++) nodes[i] = new nodeL6t4();
        for (int i = 0; i < n - 1; i++) {
            a = in.nextInt();
            b = in.nextInt();
            nodes[a].son.add(nodes[b]);
            nodes[b].son.add(nodes[a]);
        }
        int l;
        int front = 0, rear = 1;
        nodeL6t4[] temp = new nodeL6t4[n + 1];
        int max = 0, pos = 0;
        for (int i = 1; i <= n; i++) {
            a = in.nextInt();
            nodes[i].val = a;
            if (a > max || a == max && nodes[pos].son.size() <= 1) {
                max = a;
                pos = i;
            }
        }
        temp[0] = nodes[pos];
        nodes[pos].v = true;
        nodes[pos].depth = 0;
        while (front < rear) {
            l = temp[front].son.size();
            for (int i = 0; i < l; i++)
                if (!temp[front].son.get(i).v) {
                    temp[front].son.get(i).v = true;
                    temp[front].son.get(i).depth = temp[front].depth + 1;
                    temp[front].realSon.add(temp[front].son.get(i));
                    temp[front].son.get(i).f = temp[front];
                    temp[rear++] = temp[front].son.get(i);
                }
            front++;
        }//到这里处理完树,找到所有的儿子和父亲}
        dfs(nodes[pos]);
        sum = sum + nodes[pos].val - nodes[pos].max[1];
        System.out.println(sum);
    }

    public static void dfs(nodeL6t4 node) {
        int l = node.realSon.size();
        if (l == 0) {
            sum += node.val;
            node.max[0] = node.val;
        } else {
            for (int i = 0; i < l; i++)
                dfs(node.realSon.get(i));
            for (int i = 0; i < l; i++) {
                for (int j = 0; j < 1; j++) {
                    if (node.realSon.get(i).max[j] > node.max[0]) {
                        node.max[1] = node.max[0];
                        node.max[0] = node.realSon.get(i).max[j];
                    } else if (node.realSon.get(i).max[j] > node.max[1]) {
                        node.max[1] = node.realSon.get(i).max[j];
                    }
                }
            }
            if (node.val > node.max[0]) {
                sum = sum - node.max[0] + node.val;
                node.max[0] = node.val;
            }
        }
    }
}

class nodeL6t4 {
    static int count = 0;
    int depth, val, id;
    int[] max;
    ArrayList<nodeL6t4> son, realSon;
    nodeL6t4 f;
    boolean v;

    public nodeL6t4() {
        son = new ArrayList<nodeL6t4>();
        realSon = new ArrayList<nodeL6t4>();
        v = false;
        id = ++count;
        max = new int[2];
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