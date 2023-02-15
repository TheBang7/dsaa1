//快读模板1：更快，但没有next()用于读字符串

import java.io.*;
import java.util.*;

public class l2t2 {
    public static int n;
    public static long ans = 0;

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int k = in.nextInt();
        int[] a = new int[100002];
        for (int t = 0; t < k; t++) {
            n = in.nextInt();
            for (int i = 0; i < n; i++) {
                a[i + 1] = in.nextInt();
            }
            ans = 0;
            quicksort(a, 1, n);
            System.out.println(ans);
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
                ans += mid - i + 1;
            }
        }
    }

    static void quicksort2(int[] s1, int[] s2, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) / 2;
        quicksort2(s1, s2, l, mid);
        quicksort2(s1, s2, mid + 1, r);
        int[] temp1 = new int[r - l + 1];
        int[] temp2 = new int[r - l + 1];
        if (r + 1 - l >= 0) {
            System.arraycopy(s1, l, temp1, 0, r + 1 - l);
            System.arraycopy(s2, l, temp2, 0, r + 1 - l);
        }
        int i = l;
        int j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i == mid + 1) {
                s1[k] = temp1[j - l];
                s2[k] = temp2[j - l];
                j++;
            } else if (j == r + 1) {
                s1[k] = temp1[i - l];
                s2[k] = temp2[i - l];
                i++;
            } else if (temp1[i - l] <= temp1[j - l]) {
                s1[k] = temp1[i - l];
                s2[k] = temp2[i - l];
                i++;
            } else {
                s1[k] = temp1[j - l];
                s2[k] = temp2[j - l];
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
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }

}