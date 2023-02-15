import java.io.*;
import java.util.StringTokenizer;

public class l3t3_2 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n, t, l, r, m;
        n = in.nextInt();
        nod begin = new nod();
        int[] a = new int[n + 1];
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) a[i] = in.nextInt();
        ans[n - 1] = Math.abs(a[n] - a[n - 1]);
        int small = Math.min(a[n], a[n - 1]);
        int large = Math.max(a[n], a[n - 1]);
        begin.ints[1] = small;
        begin.ints[2] = large;
        begin.size = 2;
        begin.max = large;
        nod move, end = begin;
        for (int i = n - 2; i >= 1; i--) {
            move = begin;
            if (a[i] >= large) {
                ans[i] = a[i] - large;
                large = a[i];
                end.max = large;
                end.interX(a[i], end.size + 1);
                if (end.size > nod.edgeL * 2) {
                    end.split();
                    end = end.next;
                }

            } else if (a[i] <= small) {
                ans[i] = small - a[i];
                small = a[i];
                begin.interX(a[i], 1);
                if (begin.size > nod.edgeL * 2) begin.split();
            } else {
                t = move.max;
                while (a[i] > move.max) {
                    t = move.max;
                    move = move.next;
                }
                l = 1;
                r = move.size;
                while (l <= r) {
                    m = (l + r) / 2;
                    if (move.ints[m] >= a[i]) r = m - 1;
                    else l = m + 1;
                }
                if (l == 1) ans[i] = Math.min(Math.abs(a[i] - move.ints[l]), Math.abs(a[i] - t));
                else
                    ans[i] = Math.min(Math.abs(a[i] - move.ints[l]), Math.abs(a[i] - move.ints[l - 1]));
                move.interX(a[i], l);
                if (move.size > nod.edgeL * 2) {
                    move.split();
                    if(move==end)end=end.next;
                }


            }
        }
        for (int i = 1; i < n - 1; i++) {
            out.print(ans[i]);
            out.print(" ");
        }
        out.print(ans[n - 1]);
        out.close();
    }
}

class nod {
    public static int maxL = 3000, edgeL = 1420, large = 80001;//区间长度常数
    int max;
    int[] ints;
    int size;
    nod next;

    public nod() {
        ints = new int[maxL];
        size = 0;
    }

    public void interX(int x, int loc) {
        for (int i = size; i >= loc; i--)
            ints[i + 1] = ints[i];
        size++;
        ints[loc] = x;
    }

    public void split() {
        int x = size / 2;
        nod temp = new nod();//生成一个新的区块
        for (int i = x + 1; i <= size; i++) {
            temp.ints[temp.size + 1] = ints[i];
            temp.size++;
        }//抄过来
        size = x;//原区块长度减半
        temp.max = this.max;
        this.max = this.ints[size];
        temp.next = this.next;
        this.next = temp;//指针转移
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