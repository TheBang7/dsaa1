import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class l7t1 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n, m, k,cc=0;
        n = in.nextInt();
        m = in.nextInt();
        k = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[m];
        for (int i = 0; i < n; i++) a[i] = in.nextInt();
        for (int j = 0; j < m; j++) b[j] = in.nextInt();
        quicksort(a, 0, n - 1);
        quicksort(b, 0, m - 1);
        long l = 0, r = (long) a[n - 1] * b[m - 1], mid, count;
        int i, j;
        while (l <= r) {
            count = 0;
            mid = (l + r) / 2;
            j = 0;
            for (i = n-1; i >= 0; i--) {
                while (j < m && (long) a[i] * b[j] <= mid) j++;
                count += (m - j);
            }
            if ((long) n * m - count >= k) r = mid - 1;
            else l = mid + 1;
        }
        minHeapL7t1 heap = new minHeapL7t1();
        for (i = 0; i < n; i++) {
            j = 0;
            if (heap.count != 0)
                while (heap.count > 0 && (long) a[i] * b[j] >= heap.nodes.get(1)) {
                    if(cc<k-1){
                        out.print(heap.remove()+" ");
                        cc++;
                    }
                    else {
                        out.println(heap.remove());
                        out.close();
                        return;
                    }
                }
            while (j<m&&(long) a[i] * b[j] <= l){
                heap.insert((long) a[i] * b[j]);
                j++;
            }
        }
        while (heap.count > 0) {
            if(cc<k-1){
                out.print(heap.remove()+" ");
                cc++;
            }
            else {
                out.println(heap.remove());
                out.close();
                return;
            }
        }
        out.close();
    }

    static void quicksort(int[] s1, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) / 2;
        quicksort(s1, l, mid);
        quicksort(s1, mid + 1, r);
        int[] temp1 = new int[r - l + 1];
        if (r + 1 - l >= 0) System.arraycopy(s1, l, temp1, 0, r + 1 - l);
        int i = l;
        int j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i == mid + 1) {
                s1[k] = temp1[j - l];
                j++;
            } else if (j == r + 1) {
                s1[k] = temp1[i - l];
                i++;
            } else if (temp1[i - l] <= temp1[j - l]) {
                s1[k] = temp1[i - l];
                i++;
            } else {
                s1[k] = temp1[j - l];
                j++;
            }
        }
    }
}

class minHeapL7t1 {
    int count;
    ArrayList<Long> nodes;

    public minHeapL7t1() {
        count = 0;
        nodes = new ArrayList<Long>();
        nodes.add((long) -1);
    }

    public void insert(long a) {
        nodes.add(a);
        count++;
        int i = count;
        while (i > 1 && nodes.get(i) < nodes.get(i / 2)) {
            swap(i, i / 2);
            i /= 2;
        }
    }

    public long remove() {
        if (count == 0) return 0;
        long min = nodes.get(1);
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
        long temp = nodes.get(a);
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