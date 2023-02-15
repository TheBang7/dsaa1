import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bonusTf {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        int n, m, a, t, count;
        int tot = 1100000;
        nodeBonusTf[] ns = new nodeBonusTf[tot];
        for (int i = 0; i < tot; i++) ns[i] = new nodeBonusTf(i);
        maxHeapBonusTf heap = new maxHeapBonusTf();
        nodeBonusTf node;
        for (int i = 0; i < T; i++) {
            m = in.nextInt();
            n = in.nextInt();
            t = 0;//记录时间，因为要维持相等时，先进堆的在上面
            count = 0;
            while (heap.count > 0) {
                node = heap.remove();
                node.in = false;
                node.count = 0;
            }
            for (int j = 0; j < n; j++) {
                a = in.nextInt();
                if (m <= 0) count++;
                else if (ns[a].in) {
                    ns[a].count++;
                    heap.balance(ns[a].pos);
                }//如果在堆里面，维持平衡
                else if (heap.count < m) {
                    ns[a].in = true;
                    ns[a].t = t;
                    heap.insert(ns[a]);
                } else {
                    node = heap.remove();
                    node.in = false;
                    node.count = 0;
                    ns[a].t = t;
                    ns[a].in = true;
                    heap.insert(ns[a]);
                    count++;
                }
                t++;
            }
            out.println(count);
        }
        out.close();
    }
}

class nodeBonusTf {
    int count = 0;
    int val;
    boolean in = false;
    int pos;
    int t;//记录进堆时间

    public nodeBonusTf(int val) {
        this.val = val;
    }

}

class maxHeapBonusTf {
    int count;
    ArrayList<nodeBonusTf> nodes;

    public maxHeapBonusTf() {
        count = 0;
        nodes = new ArrayList<nodeBonusTf>();
        nodes.add(new nodeBonusTf(-1));
    }

    public void insert(nodeBonusTf a) {
        a.count = 1;
        nodes.add(a);
        count++;
        int i = count;
        a.pos = count;
        balance(i);
    }

    public void balance(int i) {
        while (i > 1 && comp(nodes.get(i), nodes.get(i / 2))) {
            swap(i, i / 2);
            i /= 2;
        }
    }

    public nodeBonusTf remove() {
        if (count == 0) return nodes.get(0);
        nodeBonusTf min = nodes.get(1);
        nodes.set(1, nodes.get(count));
        nodes.remove(count);
        count--;
        int i = 1;
        while (2 * i <= count) {
            int j = 2 * i;
            if (j + 1 <= count && comp(nodes.get(j + 1), nodes.get(j)))
                j += 1;
            if (comp(nodes.get(i), nodes.get(j))) break;
            swap(i, j);
            i = j;
        }
        return min;
    }

    public void swap(int a, int b) {
        nodeBonusTf temp = nodes.get(a);
        nodes.set(a, nodes.get(b));
        nodes.set(b, temp);

        int t = nodes.get(b).pos;
        nodes.get(b).pos = nodes.get(a).pos;
        nodes.get(a).pos = t;
    }

    public boolean comp(nodeBonusTf n1, nodeBonusTf n2) {
        return n1.count > n2.count || n1.count == n2.count && n1.t < n2.t;
    }
}

class QReader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}

class QWriter implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}