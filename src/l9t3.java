import java.io.*;
import java.util.*;

public class l9t3 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int c = in.nextInt();
        int a, b;
        nodeL9t3[] cities = new nodeL9t3[n + 1];
        for (int i = 1; i <= n; i++) {
            cities[i] = new nodeL9t3(k);
            cities[i].color = in.nextInt();
            cities[i].id = i;
        }
        for (int i = 0; i < m; i++) {
            a = in.nextInt();
            b = in.nextInt();
            cities[a].cities.add(b);
            cities[b].cities.add(a);
        }
        Queue<nodeL9t3> q = new LinkedList<nodeL9t3>();
        nodeL9t3 node, n2;
        int l;
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                if (cities[j].color == i) {
                    q.add(cities[j]);
                    cities[j].inQ = true;
                } else cities[j].inQ = false;
            }
            while (!q.isEmpty()) {
                node = q.remove();
                l = node.cities.size();
                for (int r = 0; r < l; r++) {
                    n2 = cities[node.cities.get(r)];
                    if (!n2.inQ) {
                        n2.inQ = true;
                        q.add(n2);
                        n2.road[i] = node.road[i] + 1;
                    }
                }
            }
        }
        int move, ans = 0;
        for (int i = 1; i <= n; i++) {
            node = cities[i];
            Arrays.sort(node.road);
            move = 1;
            ans = 0;
            while (move <= k && node.road[move] == 0) move++;
            for (int j = 0; j < c - 1 && move + j <= k; j++) ans += node.road[move + j];
            if (i != n) out.print(ans + " ");
        }
        out.println(ans);
        out.close();
    }
}

class nodeL9t3 {
    ArrayList<Integer> cities;
    int[] road;
    int color;
    boolean inQ = false;
    int id;

    public nodeL9t3(int k) {
        cities = new ArrayList<Integer>();
        road = new int[k + 1];
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