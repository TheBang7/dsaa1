import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class l7t2 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        int n, a,b;
        long sum;
        minHeap heap = new minHeap();
        for (int t = 0; t < T; t++) {
            n = in.nextInt();
            sum = 0;
            for (int i = 0; i < n; i++) {
                heap.insert(in.nextInt());
            }
            for(int i=0;i<n-1;i++){
                a=heap.remove();
                b=heap.remove();
                sum+=a+b;
                heap.insert(a+b);
            }
            out.println(sum);
            a=heap.remove();
        }
        out.close();
    }
}
class minHeap {
    int count;
    ArrayList<Integer> nodes;

    public minHeap() {
        count = 0;
        nodes = new ArrayList<Integer>();
        nodes.add(-1);
    }

    public void insert(int a) {
        nodes.add(a);
        count++;
        int i = count;
        while (i > 1 && nodes.get(i) < nodes.get(i / 2)) {
            swap(i, i / 2);
            i /= 2;
        }
    }

    public int remove() {
        if (count == 0) return 0;
        int min = nodes.get(1);
        nodes.set(1, nodes.get(count));
        nodes.remove(count);
        count--;
        int i = 1;
        while (2 * i <= count) {
            int j = 2 * i;
            if (j + 1 <= count && nodes.get(j + 1) < nodes.get(j))
                j += 1;
            if (nodes.get(i) <= nodes.get(j)) break;
            swap(i, j);
            i = j;
        }
        return min;
    }

    public void swap(int a, int b) {
        int temp = nodes.get(a);
        nodes.set(a, nodes.get(b));
        nodes.set(b, temp);
    }
}

//class minHeap {
//    int count;
//    ArrayList<Integer> nodes;
//
//    public minHeap() {
//        count = 0;
//        nodes = new ArrayList<Integer>();
//        nodes.add(-1);
//    }
//
//    public void insert(int a) {
//        nodes.add(a);
//        count++;
//        int i = count;
//        while (i > 1 && nodes.get(i) < nodes.get(i / 2)) {
//            swap(i, i / 2);
//            i/=2;
//        }
//    }
//
//    public int remove() {
//        if (count == 0) return 0;
//        int min = nodes.get(1);
//        nodes.set(1,nodes.get(count));
//        nodes.remove(count);
//        count--;
//        int i = 1;
//        while (2 * i <= count) {
//            int j = 2 * i;
//            if (j + 1 <= count && nodes.get(j + 1) < nodes.get(j))
//                j += 1;
//            if (nodes.get(i) <= nodes.get(j)) break;
//            swap(i, j);
//            i = j;
//        }
//        return min;
//    }
//
//    public void swap(int a, int b) {
//        int temp = nodes.get(a);
//        nodes.set(a, nodes.get(b));
//        nodes.set(b, temp);
//    }
//}
//
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