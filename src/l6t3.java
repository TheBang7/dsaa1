import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class l6t3 {
    public static boolean f;
    public static int k;
    public static nodeL6t3[] nodes;

    public static void main(String[] args) {
        QReader in = new QReader();
        int n = in.nextInt();
        int a, b, c, t;
        nodes = new nodeL6t3[n + 1];
        for (int i = 1; i <= n; i++) nodes[i] = new nodeL6t3();
        for (int i = 0; i < n - 1; i++) {
            a = in.nextInt();
            b = in.nextInt();
            nodes[a].son.add(nodes[b]);
            nodes[b].son.add(nodes[a]);
        }
        nodes[1].v = true;
        nodes[1].depth = 0;
        int l;
        int front = 0, rear = 1;
        nodeL6t3[] temp = new nodeL6t3[n + 1];
        temp[0] = nodes[1];
        while (front < rear) {
            l = temp[front].son.size();
            for (int i = 0; i < l; i++)
                if (!temp[front].son.get(i).v) {
                    temp[front].son.get(i).v = true;
                    temp[front].son.get(i).depth = temp[front].depth + 1;
                    temp[front].son.get(i).f = temp[front];
                    temp[rear++] = temp[front].son.get(i);
                }
            front++;
        }
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            a = in.nextInt();
            b = in.nextInt();
            c = in.nextInt();
            if (nodes[a].depth < nodes[b].depth)
                nodes[b].a = Math.max(c, nodes[b].a);
            else nodes[a].b = Math.max(c, nodes[a].b);
        }
        int left = 0, right = n;
        while (left <= right) {
            k = (left + right) / 2;
            f = true;
            nodes[1].max = k;
            nodes[1].min = k;
            dfs(nodes[1]);
            f = f && nodes[1].max >= k && nodes[1].min <= k;
            if (f) right = k - 1;
            else left = k + 1;
        }
        if (left > n) System.out.println("-1");
        else System.out.println(left);
    }
    public static void dfs(nodeL6t3 node) {
        int l = node.son.size();
        nodeL6t3 son;
        int min = 0;
        int max = 1;//算上自己
        node.sumOfNodes=1;
        for (int i = 0; i < l; i++) {
            son = node.son.get(i);
            if (son.depth > node.depth) {
                dfs(son);
                if (!f) return;
                min += son.min;
                node.sumOfNodes += son.sumOfNodes;
                max += son.max;
            }
        }
        node.min = Math.max(min, node.b);
        if(node.id==1)node.min=Math.max(k,node.min);
        node.max = Math.min(node.sumOfNodes, k - node.a);
        node.max = Math.min(node.max, max);
        if (node.max < node.min) f = false;
    }
}

class nodeL6t3 {
    static int count = 0;
    int depth;
    ArrayList<nodeL6t3> son;
    nodeL6t3 f;
    boolean v;
    int a, b, sumOfNodes, id, max, min;

    public nodeL6t3() {
        son = new ArrayList<nodeL6t3>();
        v = false;
        sumOfNodes = 1;
        id = ++count;
        a = 0;
        b = 0;
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