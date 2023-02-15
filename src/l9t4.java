import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;
import java.util.StringTokenizer;

public class l9t4 {
    public static int[] f;
    public static nodeL9t4[] nodes;
    public static int t = 0;
    public static Stack<Integer> zh;
    public static int sccNum = 0;

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        int t = in.nextInt();
        int[] a = new int[m], b = new int[m];
        nodes = new nodeL9t4[n + 1];
        zh = new Stack<Integer>();
        for (int i = 1; i <= n; i++) nodes[i] = new nodeL9t4();
        for (int i = 0; i < m; i++) {
            a[i] = in.nextInt();
            b[i] = in.nextInt();
            nodes[a[i]].cities.add(b[i]);
        }
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(t);
        nodes[t].f = true;
        int temp, l;
        nodeL9t4 node;
        while (!q.isEmpty()) {
            temp = q.remove();
            node = nodes[temp];
            l = node.cities.size();
            for (int i = 0; i < l; i++) {
                if (!nodes[node.cities.get(i)].f) {
                    q.add(node.cities.get(i));
                    nodes[node.cities.get(i)].f = true;
                    nodes[node.cities.get(i)].ff = true;
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            if (!nodes[i].f) {
                dfs(i);
            }
        }
        int[] f = new int[sccNum + 1];
        for (int i = 1; i <= sccNum; i++) f[i] = i;
        for (int i = 0; i < m; i++) {
            if (!nodes[a[i]].ff && nodes[a[i]].ring != nodes[b[i]].ring)
                f[nodes[b[i]].ring] = nodes[a[i]].ring;
        }
        int count = 0;
        for (int i = 1; i <= sccNum; i++) if (f[i] == i) count++;
        out.println(count);
        out.close();
    }

    public static void dfs(int x) {
        nodeL9t4 node = nodes[x];
        node.dfn = ++t;
        node.low = t;
        node.f = true;
        zh.push(x);
        node.inStack = true;
        int l = node.cities.size();
        for (int i = 0; i < l; i++) {
            if (!nodes[node.cities.get(i)].f) {
                dfs(node.cities.get(i));
                if (nodes[node.cities.get(i)].low < node.low) node.low = nodes[node.cities.get(i)].low;
            } else if (nodes[node.cities.get(i)].inStack && nodes[node.cities.get(i)].low < node.low)
                node.low = nodes[node.cities.get(i)].low;
        }
        int top;
        if (node.low != node.dfn)        //如果遍当前点不是强连通分量的入口
            return;
        sccNum++;                     //更新强连通分量数量
        do                            //强连通分量出栈
        {
            top = zh.peek();
            nodes[top].inStack = false;
            nodes[top].ring = sccNum;      //填写点的强连通分量归属
            zh.pop();
        } while (top != x);

    }

}

class nodeL9t4 {
    boolean f = false,ff=false;
    ArrayList<Integer> cities;
    int dfn;
    int low;
    boolean inStack;
    int ring;

    public nodeL9t4() {
        cities = new ArrayList<Integer>();
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