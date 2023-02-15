import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class l7t3 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt(), a, b, pos;
        nodeL7t3[] nodes = new nodeL7t3[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new nodeL7t3(in.nextLong());
            nodes[i].pos = i;
            nodes[i].arrPos = i;
        }

        nodes[1].right = nodes[2];
        nodes[n].left = nodes[n - 1];
        for (int i = 2; i <= n - 1; i++) {
            nodes[i].right = nodes[i + 1];
            nodes[i].left = nodes[i - 1];
        }
        minHeapL7t3 heap = new minHeapL7t3();
        minHeapL7t32 heap2 = new minHeapL7t32();
        for (int i = 1; i <= n; i++) heap.insert(nodes[i]);
        nodeL7t3 node, node2, node3;
        long l, r;
        int p;
        for (int i = 1; i < n; i++) {
            node = heap.remove();
            if (node.left != null) l = (node.left.val ^ node.val) + 1;
            else l = -1;
            if (node.right != null) r = (node.right.val ^ node.val) + 1;
            else r = -1;
            if (l >= r) {
                assert node.left != null;
                node2 = heap.nodes.get(node.left.pos);
                heap2.insert(node2);
                node3 = new nodeL7t3(l);
                node3.arrPos = node.arrPos;
                node3.left = node2.left;
                node3.right = node.right;
            } else {
                assert node.right != null;
                node2 = heap.nodes.get(node.right.pos);
                heap2.insert(node2);
                node3 = new nodeL7t3(r);
                node3.arrPos = node.arrPos;
                node3.left = node.left;
                node3.right = node2.right;
            }
            if (node3.right != null) node3.right.left = node3;
            if (node3.left != null) node3.left.right = node3;
            heap.insert(node3);
            while (heap2.count > 0 && heap.nodes.get(1) == heap2.nodes.get(1)) {
                node = heap.remove();
                node = heap2.remove();
            }
        }
        out.println(heap.remove().val);
        out.close();
    }

}

class nodeL7t3 {
    int pos, arrPos;
    long val;
    nodeL7t3 left, right;

    public nodeL7t3(long val) {
        this.val = val;
    }
}

class minHeapL7t3 {
    int count;
    ArrayList<nodeL7t3> nodes;

    public minHeapL7t3() {
        count = 0;
        nodes = new ArrayList<nodeL7t3>();
        nodes.add(new nodeL7t3(0));
    }

    public void insert(nodeL7t3 a) {
        nodes.add(a);
        count++;
        a.pos = count;
        int i = count;
        while (i > 1 && (nodes.get(i).val < nodes.get(i / 2).val || nodes.get(i).val == nodes.get(i / 2).val && nodes.get(i).arrPos < nodes.get(i / 2).arrPos)) {
            swap(i, i / 2);
            i /= 2;
        }
    }

    public nodeL7t3 remove() {
        if (count == 0) return new nodeL7t3(0);
        int a = 1;
        nodeL7t3 min = nodes.get(a);
        swap(a, count);
        int i = a;
        while (2 * i < count) {
            int j = 2 * i;
            if (j + 1 < count && (nodes.get(j + 1).val < nodes.get(j).val || nodes.get(j + 1).val == nodes.get(j).val && nodes.get(j + 1).arrPos < nodes.get(j).arrPos))
                j += 1;
            if (!(nodes.get(i).val > nodes.get(j).val || nodes.get(i).val == nodes.get(j).val && nodes.get(i).arrPos > nodes.get(j).arrPos))
                break;
            swap(i, j);
            i = j;
        }
        nodes.remove(count);
        count--;
        return min;
    }

    public void swap(int a, int b) {
        nodeL7t3 temp = nodes.get(a);
        nodes.set(a, nodes.get(b));
        nodes.set(b, temp);
        int t = nodes.get(a).pos;
        nodes.get(a).pos = nodes.get(b).pos;
        nodes.get(b).pos = t;
    }

}
class minHeapL7t32 {
    int count;
    ArrayList<nodeL7t3> nodes;

    public minHeapL7t32() {
        count = 0;
        nodes = new ArrayList<nodeL7t3>();
        nodes.add(new nodeL7t3(0));
    }

    public void insert(nodeL7t3 a) {
        nodes.add(a);
        count++;
        int i = count;
        while (i > 1 && (nodes.get(i).val < nodes.get(i / 2).val || nodes.get(i).val == nodes.get(i / 2).val && nodes.get(i).arrPos < nodes.get(i / 2).arrPos)) {
            swap(i, i / 2);
            i /= 2;
        }
    }

    public nodeL7t3 remove() {
        if (count == 0) return new nodeL7t3(0);
        int a = 1;
        nodeL7t3 min = nodes.get(a);
        swap(a, count);
        int i = a;
        while (2 * i < count) {
            int j = 2 * i;
            if (j + 1 < count && (nodes.get(j + 1).val < nodes.get(j).val || nodes.get(j + 1).val == nodes.get(j).val && nodes.get(j + 1).arrPos < nodes.get(j).arrPos))
                j += 1;
            if (!(nodes.get(i).val > nodes.get(j).val || nodes.get(i).val == nodes.get(j).val && nodes.get(i).arrPos > nodes.get(j).arrPos))
                break;
            swap(i, j);
            i = j;
        }
        nodes.remove(count);
        count--;
        return min;
    }

    public void swap(int a, int b) {
        nodeL7t3 temp = nodes.get(a);
        nodes.set(a, nodes.get(b));
        nodes.set(b, temp);
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