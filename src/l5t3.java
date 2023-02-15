import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class l5t3 {
    public static String s = "";
    public static int[][] ans = new int[100000][26];
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        QWriter out = new QWriter();
        String s1 = in.next();
        int len = s1.length();
        int k, x = 0;
        ans[0][s1.charAt(0) - 'a'] = 1;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < 26; j++) {
                if (s1.charAt(i) == (char) ('a' + j)) {
                    ans[i][j] = i + 1;
                } else {
                    ans[i][j] = ans[x][j];
                }
            }
            x = ans[x][s1.charAt(i) - 'a'];
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < 26 - 1; j++) {
                out.print(ans[i][j]);
                out.print(" ");
            }
            out.println(ans[i][25]);
        }
        out.close();
    }

    public static boolean isSub(String s1, int k, int p, char c) {
        if (k == 0) return true;
        String s2 = s1.substring(p - k + 1, p) + c;
        int len = s2.length();
        for (int i = 0; i < k; i++)
            if (s1.charAt(i) != s2.charAt(len - k + i)) return false;
        return true;
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