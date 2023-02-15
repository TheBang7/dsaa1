import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class l6t2 {
    public static ArrayList<Integer> arrayList = new ArrayList<Integer>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a, b, c, t;
        nodeL6t2[] nodes = new nodeL6t2[n + 1];
        for (int i = 1; i <= n; i++) nodes[i] = new nodeL6t2();
        for (int i = 0; i < n - 1; i++) {
            a = in.nextInt();
            b = in.nextInt();
            nodes[a].son.add(nodes[b]);
            nodes[b].son.add(nodes[a]);
        }
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            a = in.nextInt();
            nodes[a].g = true;
        }
        nodes[1].v = true;
        nodes[1].depth = 0;
        int l = nodes[1].son.size();
        int ans = 0, time, len, move;
        nodeL6t2[] temp = new nodeL6t2[n + 1];
        for (int i = 0; i < l; i++) {
            nodes[1].son.get(i).v = true;
            nodes[1].son.get(i).depth = 1;
            arrayList.clear();
            time = 1;
            dfs(nodes[1].son.get(i),temp);
            len = arrayList.size();
            move = 0;
            while (move < len) {
                if (arrayList.get(move) <= time) {
                    time++;
                } else {
                    time = arrayList.get(move) + 1;
                }
                move++;
            }
            ans = Math.max(ans, time - 1);
        }
        System.out.println(ans);
    }
    public static void dfs(nodeL6t2 node, nodeL6t2[] temp) {
        int front = 0, rear = 1;
        temp[0] = node;
        while (front < rear) {
            if (temp[front].g) arrayList.add(temp[front].depth);
            int l = temp[front].son.size();
            for (int i = 0; i < l; i++)
                if (!temp[front].son.get(i).v) {
                    temp[front].son.get(i).v = true;
                    temp[front].son.get(i).depth = temp[front].depth + 1;
                    temp[rear++] = temp[front].son.get(i);
                }
            front++;
        }

    }
}
class nodeL6t2 {
    int depth;
    ArrayList<nodeL6t2> son;
    nodeL6t2 f;
    boolean v, g;

    public nodeL6t2() {
        son = new ArrayList<nodeL6t2>();
        v = false;
        g = false;
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