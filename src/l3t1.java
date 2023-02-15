import java.io.*;
import java.util.*;

public class l3t1 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n, m, k1, k2, k = 0, never1 = -111, never2 = -1111;
        n = in.nextInt();
        m = in.nextInt();
        node a = new node(never1, never1);
        node t1, t2;
        t1 = a;
        for (int i = 1; i <= n; i++) {
            t1.next = new node(in.nextInt(), in.nextInt());
            t1 = t1.next;
        }
        node tail = new node(never2, never2);
        t1.next = tail;
        t2 = a;
        for (int i = 1; i <= m; i++) {
            k1 = in.nextInt();
            k2 = in.nextInt();
            while (t2.next.exp <= k2 && t2.next != tail) {
                t2 = t2.next;
            }
            if (t2.exp == k2)
                t2.coe += k1;
            else {
                t1 = new node(k1, k2);
                t1.next = t2.next;
                t2.next = t1;
            }
        }
        t2 = a.next;
        while (t2 != tail) {
            if (t2.coe != 0) k++;
            t2 = t2.next;
        }
        out.println(k);
        t2 = a.next;
        while (t2 != tail ) {
            if (t2.coe != 0) {
                out.print(t2.coe);
                out.print(" ");
                out.println(t2.exp);
            }
            t2 = t2.next;
        }
        out.close();
    }
}

class node {
    int coe, exp;
    node next, pre;

    public node(int coe, int exp) {
        this.coe = coe;
        this.exp = exp;
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