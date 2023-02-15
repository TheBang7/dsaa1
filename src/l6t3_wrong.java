//import java.io.*;
//import java.util.ArrayList;
//import java.util.StringTokenizer;
//
//public class l6t3_wrong {
//    public static boolean f = false;
//
//    public static void main(String[] args) {
//        QReader in = new QReader();
//        int n = in.nextInt();
//        int a, b, c, t;
//        nodeL6t3[] nodes = new nodeL6t3[n + 1];
//        for (int i = 1; i <= n; i++) nodes[i] = new nodeL6t3();
//        for (int i = 0; i < n - 1; i++) {
//            a = in.nextInt();
//            b = in.nextInt();
//            nodes[a].son.add(nodes[b]);
//            nodes[b].son.add(nodes[a]);
//        }
//        nodes[1].v = true;
//        nodes[1].depth = 0;
//        int l;
//        int front = 0, rear = 1;
//        nodeL6t3[] temp = new nodeL6t3[n + 1];
//        temp[0] = nodes[1];
//        while (front < rear) {
//            l = temp[front].son.size();
//            for (int i = 0; i < l; i++)
//                if (!temp[front].son.get(i).v) {
//                    temp[front].son.get(i).v = true;
//                    temp[front].son.get(i).depth = temp[front].depth + 1;
//                    temp[front].son.get(i).f = temp[front];
//                    temp[rear++] = temp[front].son.get(i);
//                }
//            front++;
//        }
//        int m = in.nextInt();
//        for (int i = 0; i < m; i++) {
//            a = in.nextInt();
//            b = in.nextInt();
//            c = in.nextInt();
//            if (nodes[a].depth < nodes[b].depth)
//                nodes[b].a = Math.max(c, nodes[b].a);
//            else nodes[b].b = Math.max(c, nodes[b].b);
//        }
//        dfs(nodes[1]);
//        if (f) System.out.println(-1);
//        else
//            System.out.println(nodes[1].sumOfChoose);
//    }
//
//    public static void dfs(nodeL6t3 node) {
//        boolean flag = true, ifRoot = true;
//        int min, pos;
//        int l = node.son.size();
//        if (l == 1 && node.depth != 0) {
//            if (node.b > 1) f = true;
//            else if (node.b == 1) node.sumOfChoose = 1;
//        } else {
//            for (int i = 0; i < l; i++) {
//                if (node.son.get(i).depth > node.depth) {
//                    dfs(node.son.get(i));
//                    if (f) return;
//                    node.sumOfNodes += node.son.get(i).sumOfNodes;
//                    node.sumOfChoose += node.son.get(i).sumOfChoose;
//                }
//            }
//            while (flag) {
//                flag = false;
//                min = nodeL6t3.total;
//                pos = -1;
//                for (int i = 0; i < l; i++) {
//                    if (node.son.get(i).depth > node.depth) {
//                        if (node.sumOfChoose - node.son.get(i).sumOfChoose < node.son.get(i).a) flag = true;
//                        if (node.son.get(i).sumOfNodes - node.son.get(i).sumOfChoose > 0 && node.son.get(i).a - (node.sumOfChoose - node.son.get(i).sumOfChoose) < min) {
//                            min = node.son.get(i).a - (node.sumOfChoose - node.son.get(i).sumOfChoose);
//                            pos = i;
//                        }
//                    }
//                }
//                if (pos != -1 && flag) {
//                    if (ifRoot) {
//                        node.sumOfChoose++;
//                        ifRoot = false;
//                    }
//                    node.sumOfChoose++;
//                    node.son.get(pos).sumOfChoose++;
//                } else if (flag) {
//                    f = true;
//                    return;
//                }
//            }
//            if (node.sumOfChoose < node.b && node.sumOfNodes >= node.b) node.sumOfChoose = node.b;
//            else if (node.sumOfChoose < node.b) {
//                f = true;
//                return;
//            }
//        }
//    }
//}
//
////class nodeL6t3 {
////    static int count = 0, total = 400000;
////    int depth;
////    ArrayList<nodeL6t3> son;
////    nodeL6t3 f;
////    boolean v;
////    int a, b, sumOfNodes, sumOfChoose, id;
////
////    public nodeL6t3() {
////        son = new ArrayList<nodeL6t3>();
////        v = false;
////        sumOfNodes = 1;
////        sumOfChoose = 0;
////        id = ++count;
////        a = 0;
////        b = 0;
////    }
////}
////
////class QReader {
////    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
////    private StringTokenizer tokenizer = new StringTokenizer("");
////
////    private String innerNextLine() {
////        try {
////            return reader.readLine();
////        } catch (IOException e) {
////            return null;
////        }
////    }
////
////    public boolean hasNext() {
////        while (!tokenizer.hasMoreTokens()) {
////            String nextLine = innerNextLine();
////            if (nextLine == null) {
////                return false;
////            }
////            tokenizer = new StringTokenizer(nextLine);
////        }
////        return true;
////    }
////
////    public String nextLine() {
////        tokenizer = new StringTokenizer("");
////        return innerNextLine();
////    }
////
////    public String next() {
////        hasNext();
////        return tokenizer.nextToken();
////    }
////
////    public int nextInt() {
////        return Integer.parseInt(next());
////    }
////
////    public long nextLong() {
////        return Long.parseLong(next());
////    }
////}
////
////class QWriter implements Closeable {
////    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
////
////    public void print(Object object) {
////        try {
////            writer.write(object.toString());
////        } catch (IOException e) {
////            return;
////        }
////    }
////
////    public void println(Object object) {
////        try {
////            writer.write(object.toString());
////            writer.write("\n");
////        } catch (IOException e) {
////            return;
////        }
////    }
////
////    @Override
////    public void close() {
////        try {
////            writer.close();
////        } catch (IOException e) {
////            return;
////        }
////    }
////}