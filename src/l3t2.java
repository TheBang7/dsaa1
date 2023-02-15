import java.io.*;
import java.util.*;

public class l3t2 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n, never = -1111;
        long m, total = 0, sum = 0;
        n = in.nextInt();
        m = in.nextLong();
        shop a = new shop(in.nextInt());
        shop temp = a;
        total += temp.price;
        int min = temp.price;
        for (int i = 1; i < n; i++) {
            temp.next = new shop(in.nextInt());
            temp = temp.next;
            total += temp.price;
            min = Math.min(min, temp.price);
        }
        temp.next = a;
        temp = a;
        sum += (m / total) * n;
        m = m % total;
        while (m >= min) {
            if (m >= temp.price) {
                sum++;
                m -= temp.price;
            }
            temp = temp.next;
        }
        out.println(sum);
        out.close();
    }
}

class shop {
    int price;
    shop next, pre;

    public shop(int price) {
        this.price = price;
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