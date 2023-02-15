//import java.io.*;
//import java.util.*;
//
//public class l2t4 {
//    public static void main(String[] args) throws IOException {
//        Reader in = new Reader();
//        PrintWriter out = new PrintWriter(System.out);
//        int n, m, k;
//        long ans = 0;
//        n = in.nextInt();
//        m = in.nextInt();
//        k = in.nextInt();
//        int[] a = new int[n + 1];
//        int[] b = new int[11];
//        int[][] c = new int[n + 1][11];
//        int[] change = new int[n * 10 + 2];
//        for (int j = 1; j <= n; j++) {
//            a[j] = in.nextInt();
//            ans += a[j];
//        }
//        int l, zs;
//        for (int i = 1; i <= n; i++) {
//            l = 0;
//            if (a[i] >= 0) zs = 1;
//            else zs = -1;
//            for (int ii = 0; ii <= 10; ii++)
//                c[i][ii] = a[i];
//            a[i] *= zs;
//            while (a[i] > 0) {
//                b[++l] = a[i] % 10;
//                a[i] /= 10;
//            }
//            solve(b, l, i, c, zs, 0);
//        }
//        int count = 0;
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= 10; j++)
//                if (c[i][j] - c[i][j - 1] > k) change[++count] = c[i][j] - c[i][j - 1];
//                else break;
//        }
//        quicksort(change, 1, count, 1);
//        int i = 1;
//        while (change[i] > k && i <= m && i <= count) {
//            ans += (long) change[i] - k;
//            i++;
//        }
//        out.println(ans);
//        out.close();
//    }
//
//    static void solve(int[] b, int l, int num, int[][] c, int zs, int ci) {
//        int sum = 0, p, temp;
//        boolean f;
//        for (int i = l; i >= 1; i--) sum = sum * 10 + b[i];
//        sum *= zs;
//        if (sum > c[num][ci]) c[num][ci] = sum;
//        f = false;
//        for (int i = l - ci; i > 1; i--) {
//            p = i;
//            for (int j = 1; j < i; j++)
//                if (b[j] * zs > b[p] * zs) p = j;
//            if (p != i) {
//                f = true;
//                for (int j = 1; j < i; j++) {
//                    if (b[j] == b[p]) {
//                        temp = b[i];
//                        b[i] = b[j];
//                        b[j] = temp;
//                        solve(b, l, num, c, zs, ci + 1);
//                        temp = b[i];
//                        b[i] = b[j];
//                        b[j] = temp;
//                    }
//                }
//            }
//            if (f) break;
//        }
//    }
//    static void quicksort(int[] s1, int l, int r, int tem) {
//        if (l >= r) {
//            return;
//        }
//        int mid = (l + r) / 2;
//        quicksort(s1, l, mid, tem);
//        quicksort(s1, mid + 1, r, tem);
//        int[] temp1 = new int[r - l + 1];
//        if (r + 1 - l >= 0) System.arraycopy(s1, l, temp1, 0, r + 1 - l);
//        int i = l;
//        int j = mid + 1;
//        for (int k = l; k <= r; k++) {
//            if (i == mid + 1) {
//                s1[k] = temp1[j - l];
//                j++;
//            } else if (j == r + 1) {
//                s1[k] = temp1[i - l];
//                i++;
//            } else if (temp1[i - l] * tem >= temp1[j - l] * tem) {
//                s1[k] = temp1[i - l];
//                i++;
//            } else {
//                s1[k] = temp1[j - l];
//                j++;
//            }
//        }
//    }
//
//
//    static void quicksort2(int[] s1, int[] s2, int l, int r, int tem) {
//        if (l >= r) {
//            return;
//        }
//        int mid = (l + r) / 2;
//        quicksort2(s1, s2, l, mid, tem);
//        quicksort2(s1, s2, mid + 1, r, tem);
//        int[] temp1 = new int[r - l + 1];
//        int[] temp2 = new int[r - l + 1];
//        if (r + 1 - l >= 0) {
//            System.arraycopy(s1, l, temp1, 0, r + 1 - l);
//            System.arraycopy(s2, l, temp2, 0, r + 1 - l);
//        }
//        int i = l;
//        int j = mid + 1;
//        for (int k = l; k <= r; k++) {
//            if (i == mid + 1) {
//                s1[k] = temp1[j - l];
//                s2[k] = temp2[j - l];
//                j++;
//            } else if (j == r + 1) {
//                s1[k] = temp1[i - l];
//                s2[k] = temp2[i - l];
//                i++;
//            } else if (temp1[i - l] * tem <= temp1[j - l] * tem) {
//                s1[k] = temp1[i - l];
//                s2[k] = temp2[i - l];
//                i++;
//            } else {
//                s1[k] = temp1[j - l];
//                s2[k] = temp2[j - l];
//                j++;
//            }
//        }
//    }
//
//
//    static class Reader {
//        final private int BUFFER_SIZE = 1 << 16;
//        private DataInputStream din;
//        private byte[] buffer;
//        private int bufferPointer, bytesRead;
//
//        public Reader() {
//            din = new DataInputStream(System.in);
//            buffer = new byte[BUFFER_SIZE];
//            bufferPointer = bytesRead = 0;
//        }
//
//        public Reader(String file_name) throws IOException {
//            din = new DataInputStream(new FileInputStream(file_name));
//            buffer = new byte[BUFFER_SIZE];
//            bufferPointer = bytesRead = 0;
//        }
//
//        public String readLine() throws IOException {
//            byte[] buf = new byte[64]; // line length
//            int cnt = 0, c;
//            while ((c = read()) != -1) {
//                if (c == '\n') break;
//                buf[cnt++] = (byte) c;
//            }
//            return new String(buf, 0, cnt);
//        }
//
//        public int nextInt() throws IOException {
//            int ret = 0;
//            byte c = read();
//            while (c <= ' ') c = read();
//            boolean neg = (c == '-');
//            if (neg) c = read();
//            do {
//                ret = ret * 10 + c - '0';
//            } while ((c = read()) >= '0' && c <= '9');
//
//            if (neg) return -ret;
//            return ret;
//        }
//
//        public long nextLong() throws IOException {
//            long ret = 0;
//            byte c = read();
//            while (c <= ' ') c = read();
//            boolean neg = (c == '-');
//            if (neg) c = read();
//            do {
//                ret = ret * 10 + c - '0';
//            } while ((c = read()) >= '0' && c <= '9');
//            if (neg) return -ret;
//            return ret;
//        }
//
//        public double nextDouble() throws IOException {
//            double ret = 0, div = 1;
//            byte c = read();
//            while (c <= ' ') c = read();
//            boolean neg = (c == '-');
//            if (neg) c = read();
//
//            do {
//                ret = ret * 10 + c - '0';
//            } while ((c = read()) >= '0' && c <= '9');
//
//            if (c == '.') {
//                while ((c = read()) >= '0' && c <= '9') {
//                    ret += (c - '0') / (div *= 10);
//                }
//            }
//
//            if (neg) return -ret;
//            return ret;
//        }
//
//        private void fillBuffer() throws IOException {
//            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
//            if (bytesRead == -1) buffer[0] = -1;
//        }
//
//        private byte read() throws IOException {
//            if (bufferPointer == bytesRead) fillBuffer();
//            return buffer[bufferPointer++];
//        }
//
//        public void close() throws IOException {
//            if (din == null) return;
//            din.close();
//        }
//    }
//
//}
import java.io.*;
import java.util.*;
public class l2t4 {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int n, m, k;
        long ans = 0;
        n = in.nextInt();
        m = in.nextInt();
        k = in.nextInt();
        int[] a = new int[n + 1];
        int[] b = new int[10];
        int[] c = new int[10 * n + 2];
        int[] d = new int[10];
        d[1] = 1;
        for (int j = 1; j <= n; j++) {
            a[j] = in.nextInt();
            ans += a[j];
        }
        for (int j = 2; j <= 9; j++) d[j] = d[j - 1] * 10;
        int j, p, temp, count = 0, aa, zs;
        for (int i = 1; i <= n; i++) {
            j = 0;
            if (a[i] >= 0) zs = 1;
            else zs = -1;
            a[i] *= zs;
            while (a[i] > 0) {
                b[++j] = a[i] % 10;
                a[i] /= 10;
            }
            for (int s = j; s > 1; s--) {
                p = s;
                for (int s2 = s - 1; s2 >= 1; s2--) {
                    if (b[s2] * zs >= b[p] * zs) p = s2;
                }
                int sum1 = zs * (b[p] * d[s] + b[s] * d[p] - b[p] * d[p] - b[s] * d[s]);
                if (p != s && sum1 > k) {
                    temp = b[s];
                    b[s] = b[p];
                    b[p] = temp;
                    c[++count] = sum1;
                }
                //if (sum1 <= k) break;
            }
        }
        quicksort(c, 1, count);
        int i = 1;
        while (c[i] > k && i <= m) {
            ans += (long) c[i] - k;
            i++;
        }
        //for(int i=1;i<=n;i++)System.out.println(a[i]);
        out.println(ans);
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
            } else if (temp1[i - l] >= temp1[j - l]) {
                s1[k] = temp1[i - l];
                i++;
            } else {
                s1[k] = temp1[j - l];
                j++;
            }
        }
    }


    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg) c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg) return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg) c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg) return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg) c = read();

            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg) return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead) fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null) return;
            din.close();
        }
    }

}