import java.io.*;
import java.util.*;

public class l3t4 {
    public static part begin;
    public static int total = 0;
    public static int[] tempArr = new int[part.large];

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        for (int i = 0; i < part.large; i++) tempArr[i] = 0;
        int n, m, x;
        n = in.nextInt();m = in.nextInt();
        begin = new part();
        begin.beg = 1;
        part move = begin;
        for (int i = 1; i <= n; i++) {
            x = in.nextInt();
            total++;
            if (move.size < part.edgeL)
                move.addX(x);
            else {
                part temp = new part();
                temp.beg = move.beg + move.size;
                temp.copySum(move);
                move.next = temp;
                move = temp;
                temp.addX(x);
            }
        }

        int len, a, b, tr = 0;
        String s;
        for (int mm = 1; mm <= m; mm++) {
            s = in.nextLine();
            String[] temp=s.split(" ");
            if(s.charAt(0)=='I'){solveI(begin,Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));}
            else if(s.charAt(0)=='M')solveM(begin,Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
            else out.println(solveQ(Integer.parseInt(temp[1]),Integer.parseInt(temp[2]),Integer.parseInt(temp[3])));
        }
//        for (int mm = 1; mm <= m; mm++) {
//            s = in.nextLine();
//            String[] temp = s.split(" ");
//            if (s.charAt(0) == 'I') {
//                solveI(begin, tr ^ Integer.parseInt(temp[1]), tr ^ Integer.parseInt(temp[2]));
//            } else if (s.charAt(0) == 'M')
//                solveM(begin, tr ^ Integer.parseInt(temp[1]), tr ^ Integer.parseInt(temp[2]));
//            else {
//                tr = solveQ(tr ^ Integer.parseInt(temp[1]), tr ^ Integer.parseInt(temp[2]), tr ^ Integer.parseInt(temp[3]));
//                System.out.println(tr);
//            }
//        }
        out.close();
    }

    public static void solveI(part now, int loc, int x) {
        if (loc > now.size && now.next != null) solveI(now.next, loc - now.size, x);
        else {
            now.interX(x, loc);
            if (now.size >= part.edgeL * 2) now.split();
            now.sum[x]++;
            while (now.next != null) {
                now = now.next;
                now.sum[x]++;
                now.beg++;
            }

        }
    }

    public static void solveM(part now, int loc, int x) {
        if (loc > now.size) solveM(now.next, loc - now.size, x);
        else {
            int t = now.ints[loc];
            now.ints[loc] = x;
            while (now.next != null) {
                now.sum[t]--;
                now.sum[x]++;
                now = now.next;
            }
            now.sum[t]--;
            now.sum[x]++;
        }
    }

    public static int solveQ(int l, int r, int k) {
        part pl = begin, pr = begin;
        while (l > pl.beg + pl.size - 1) pl = pl.next;
        while (r > pr.beg + pr.size - 1) pr = pr.next;
        int i = -1;
        part ppl = pl;
        if (pl == pr) {
            pl.count(l - pl.beg + 1, r - pr.beg + 1, tempArr);
            while (k > 0) {
                i++;
                k -= tempArr[i];
            }
            pl.remove(l - pl.beg + 1, r - pr.beg + 1, tempArr);
        } else {
            ppl.count(l - pl.beg + 1, pl.size, tempArr);
            pr.count(1, r - pr.beg + 1, tempArr);
            while (pl.next != pr) pl = pl.next;
            while (k > 0) {
                i++;
                if (pl != pr) k -= (pl.sum[i] - ppl.sum[i]);
                k -= tempArr[i];
            }
            ppl.remove(l - ppl.beg + 1, ppl.size, tempArr);
            pr.remove(1, r - pr.beg + 1, tempArr);
        }
        return i;

    }
}

class part {
    public static int maxL = 600, edgeL = 295, large = 80001;//区间长度常数
    int beg;
    int[] ints;
    int[] sum;
    int size;
    part next;

    public part() {
        ints = new int[maxL];
        sum = new int[large];//this.sum[i]:记录在这一段以及之前，i这个数出现了几次
        size = 0;
    }

    public void addX(int x) {
        size++;
        ints[size] = x;
        sum[ints[size]]++;
    }

    public void interX(int x, int loc) {
        for (int i = size; i >= loc; i--)
            ints[i + 1] = ints[i];
        size++;
        ints[loc] = x;
    }

    public void copySum(part before) {
        System.arraycopy(before.sum, 0, sum, 0, large);
    }

    public void split() {
        int x = size / 2;
        part temp = new part();//生成一个新的区块
        temp.copySum(this);
        temp.beg = this.beg + x - 1;//更新新区块的起始位置
        for (int i = x + 1; i <= size; i++) {
            temp.ints[temp.size + 1] = ints[i];
            this.sum[ints[i]]--;
            temp.size++;
        }//抄过来
        size = x;//原区块长度减半
        //temp.beg = this.beg + x;//修改新区间的起始地点
        temp.next = this.next;
        this.next = temp;//指针转移
    }

    public int countX(int l, int r, int x) {
        int count = 0;
        for (int i = l; i <= r; i++) {
            if (this.ints[i] == x) count++;
        }
        return count;
    }

    public void count(int l, int r, int[] temp) {
        int count = 0;
        for (int i = l; i <= r; i++) {
            temp[this.ints[i]]++;
        }

    }

    public void remove(int l, int r, int[] temp) {
        int count = 0;
        for (int i = l; i <= r; i++) {
            temp[this.ints[i]]--;
        }

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