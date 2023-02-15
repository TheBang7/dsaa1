//import java.io.*;
//import java.util.*;
//
//public class l3t3 {
//    public static void main(String[] args) {
//        QReader in = new QReader();
//        QWriter out = new QWriter();
//        int n, m, q, never = -1111;
//        n = in.nextInt();
//        m = in.nextInt();
//        q = in.nextInt();
//        chess[][] chess;
//        chess = new chess[n + 2][m + 2];
//        for (int i = 0; i <= n + 1; i++)
//            for (int j = 0; j <= m + 1; j++)
//                if (i == 0 || j == 0 || i == n + 1 || j == m + 1) {
//                    chess[i][j] = new chess(never);
//                } else {
//                    chess[i][j] = new chess(in.nextInt());
//                }
//        for (int i = 0; i <= n + 1; i++)
//            for (int j = 0; j <= m + 1; j++) {
//                if (j - 1 >= 0) chess[i][j].left = chess[i][j - 1];
//                if (j + 1 <= m+1) chess[i][j].right = chess[i][j + 1];
//                if (i - 1 >= 0) chess[i][j].top = chess[i - 1][j];
//                if (i + 1 <= n+1) chess[i][j].floor = chess[i + 1][j];
//            }
//        chess a;
//        chess aa = chess[0][0];
//        //记录完毕，下面开始交换
//        int x1, y1, x2, y2, l1, l2;
//        chess sub1, sub2, first, heng;
//        for (int qq = 1; qq <= q; qq++) {
//            a = aa.right.floor;
//            x1 = in.nextInt();
//            y1 = in.nextInt();
//            x2 = in.nextInt();
//            y2 = in.nextInt();
//            l1 = in.nextInt();
//            l2 = in.nextInt();
//            sub1 = find(a, x1, y1);
//            sub2 = find(a, x2, y2);//找到正确的起始位置
//            change(sub1, sub2, l1, l2);//交换
//        }
//
//        first = aa.right.floor;
//        for (int i = 1; i <= n; i++) {
//            out.print(first.value);
//            heng = first.right;
//            for (int j = 1; j < m; j++) {
//                out.print(" ");
//                out.print(heng.value);
//                heng = heng.right;
//            }
//            out.println("");
//            if (i != n) first = first.floor;
//        }//输出
//        out.close();
//    }
//    public static chess find(chess a, int x, int y) {
//        chess sub = a;
//        for (int i = 1; i < y; i++) sub = sub.right;
//        for (int i = 1; i < x; i++) sub = sub.floor;
//        return sub;
//    }
//    public static void changeFloor(chess sub1, chess sub2) {
//        chess temp = sub1.floor;
//        sub1.floor = sub2.floor;
//        sub2.floor = temp;
//    }
//
//    public static void changeTop(chess sub1, chess sub2) {
//        chess temp = sub1.top;
//        sub1.top = sub2.top;
//        sub2.top = temp;
//    }
//
//    public static void changeLeft(chess sub1, chess sub2) {
//        chess temp = sub1.left;
//        sub1.left = sub2.left;
//        sub2.left = temp;
//    }
//
//    public static void changeRight(chess sub1, chess sub2) {
//        chess temp = sub1.right;
//        sub1.right = sub2.right;
//        sub2.right = temp;
//    }
//    public static void change(chess sub1, chess sub2, int l1, int l2) {
//        chess h1, h2;
//        h1 = sub1;
//        h2 = sub2;
//        for (int i = 1; i <= l2; i++) {
//            changeFloor(h1.top, h2.top);
//            changeTop(h1, h2);
//            h1 = h1.right;
//            h2 = h2.right;
//        }//第一行
//        h1 = h1.left;
//        h2 = h2.left;
//        for (int i = 1; i <= l1; i++) {
//            changeLeft(h1.right, h2.right);
//            changeRight(h1, h2);
//            h1 = h1.floor;
//            h2 = h2.floor;
//        }//最后一列
//        h1 = h1.top;
//        h2 = h2.top;
//        for (int i = 1; i <= l2; i++) {
//            changeTop(h1.floor, h2.floor);
//            changeFloor(h1, h2);
//            h1 = h1.left;
//            h2 = h2.left;
//        }//最后一行
//        h1 = h1.right;
//        h2 = h2.right;
//        for (int i = 1; i <= l1; i++) {
//            changeRight(h1.left, h2.left);
//            changeLeft(h1, h2);
//            h1 = h1.top;
//            h2 = h2.top;
//        }//第一列
//        //顺时针走完一圈完成交换
//    }
//}
//
//
//class chess {
//    int value;
//    public void setValue(int value) {
//        this.value = value;
//    }
//    chess left, right, top, floor;
//    public chess(int value) {
//        this.value = value;
//    }
//
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

import java.io.*;
import java.util.*;

public class l3t3 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n, m, q, never = -1111;
        n = in.nextInt();
        m = in.nextInt();
        q = in.nextInt();
        chess[][] chess;
        chess = new chess[n + 2][m + 2];
        for (int i = 0; i <= n + 1; i++)
            for (int j = 0; j <= m + 1; j++)
                if (i == 0 || j == 0 || i == n + 1 || j == m + 1) {
                    chess[i][j] = new chess(never);
                } else {
                    chess[i][j] = new chess(in.nextInt());
                }
        for (int i = 0; i <= n + 1; i++)
            for (int j = 0; j <= m + 1; j++) {
                // if (j - 1 >= 0) chess[i][j].left = chess[i][j - 1];
                if (j + 1 <= m + 1) chess[i][j].right = chess[i][j + 1];
                // if (i - 1 >= 0) chess[i][j].top = chess[i - 1][j];
                if (i + 1 <= n + 1) chess[i][j].floor = chess[i + 1][j];
            }
        chess a;
        chess aa = chess[0][0];
        //记录完毕，下面开始交换
        int x1, y1, x2, y2, l1, l2;
        chess sub1, sub2, first, heng;
        for (int qq = 1; qq <= q; qq++) {
            a = aa;
            x1 = in.nextInt();
            y1 = in.nextInt();
            x2 = in.nextInt();
            y2 = in.nextInt();
            l1 = in.nextInt();
            l2 = in.nextInt();
            sub1 = find(a, x1, y1);
            sub2 = find(a, x2, y2);//找到正确的起始位置
            change(sub1, sub2, l1, l2);//交换
        }

        first = aa.right.floor;
        for (int i = 1; i <= n; i++) {
            out.print(first.value);
            heng = first.right;
            for (int j = 1; j < m; j++) {
                out.print(" ");
                out.print(heng.value);
                heng = heng.right;
            }
            out.println("");
            if (i != n) first = first.floor;
        }//输出
        out.close();
    }

    public static chess find(chess a, int x, int y) {
        chess sub = a;
        for (int i = 1; i < y; i++) sub = sub.right;
        for (int i = 1; i < x; i++) sub = sub.floor;
        return sub;
    }

    public static void changeFloor(chess sub1, chess sub2) {
        chess temp = sub1.floor;
        sub1.floor = sub2.floor;
        sub2.floor = temp;
    }

    public static void changeTop(chess sub1, chess sub2) {
        chess temp = sub1.top;
        sub1.top = sub2.top;
        sub2.top = temp;
    }

    public static void changeLeft(chess sub1, chess sub2) {
        chess temp = sub1.left;
        sub1.left = sub2.left;
        sub2.left = temp;
    }

    public static void changeRight(chess sub1, chess sub2) {
        chess temp = sub1.right;
        sub1.right = sub2.right;
        sub2.right = temp;
    }

    public static void change(chess sub1, chess sub2, int l1, int l2) {
        chess h1, h2;
        h1 = sub1.right;
        h2 = sub2.right;
        for (int i = 1; i <= l2; i++) {
            changeFloor(h1, h2);
            if (i != l2) {
                h1 = h1.right;
                h2 = h2.right;
            }
        }//第一行
        h1 = h1.floor;
        h2 = h2.floor;
        for (int i = 1; i <= l1; i++) {
            changeRight(h1, h2);
            h1 = h1.floor;
            h2 = h2.floor;
        }//最后一列
        h1 = sub1.floor;
        h2 = sub2.floor;
        for (int i = 1; i <= l1; i++) {
            changeRight(h1, h2);
            if (i != l1) {
                h1 = h1.floor;
                h2 = h2.floor;
            }
        }//第一列
        h1 = h1.right;
        h2 = h2.right;
        for (int i = 1; i <= l2; i++) {
            changeFloor(h1, h2);
            h1 = h1.right;
            h2 = h2.right;
        }//最后一行
//        h1 = h1.right;
//        h2 = h2.right;

        //顺时针走完一圈完成交换
    }
}


class chess {
    int value;

    public void setValue(int value) {
        this.value = value;
    }

    chess left, right, top, floor;

    public chess(int value) {
        this.value = value;
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