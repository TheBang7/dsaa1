import java.util.*;

public class l9t1 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        cityL9t1[] ci = new cityL9t1[n + 1];
        long max=10000000000000000L;
        for (int i = 1; i <= n; i++) {
            ci[i] = new cityL9t1();
            ci[i].ans = max;
        }
        int a, b;
        long c;
        for (int i = 0; i < m; i++) {
            a = in.nextInt();
            b = in.nextInt();
            c = in.nextLong();
            ci[a].cities.add(b);
            ci[a].road.add(c);
        }
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(1);
        ci[1].ans = 0;
        cityL9t1 t;
        int l;
        while (!q.isEmpty()) {
            t = ci[q.remove()];
            l = t.cities.size();
            for (int i = 0; i < l; i++)
                if (t.ans + t.road.get(i) < ci[t.cities.get(i)].ans){
                    ci[t.cities.get(i)].ans = t.ans + t.road.get(i);
                    q.add(t.cities.get(i));
                }

        }
        if (ci[n].ans == max) out.println(-1);
        else
            out.println(ci[n].ans);
        out.close();
    }
}

class cityL9t1 {
    long ans = 0;
    ArrayList<Integer> cities;
    ArrayList<Long> road;

    public cityL9t1() {
        cities = new ArrayList<Integer>();
        road = new ArrayList<Long>();
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