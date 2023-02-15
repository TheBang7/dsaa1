import java.io.*;
import java.util.StringTokenizer;

public class l4t4 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n=in.nextInt();
        int l=0,r=0,t,ans=0;
        long sum=0;
        l4t4nod[] a=new l4t4nod[n+1];
        a[0]=new l4t4nod();
        a[0].sum=0;
        a[0].pos=0;
        for(int i=1;i<=n;i++){
           a[i]=new l4t4nod();
           a[i].sum=a[i-1].sum+in.nextInt();
           a[i].pos=i;
            }
        quicksort(a,0,n);
        int minP=a[0].pos;
        for(int i=0;i<=n;i++){
            minP=Math.min(minP,a[i].pos);
            ans=Math.max(ans,a[i].pos-minP);
        }
        out.println(ans);
        out.close();
    }
    static void quicksort(l4t4nod[] s1, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) / 2;
        quicksort(s1, l, mid);
        quicksort(s1, mid + 1, r);
        l4t4nod[] temp1 = new l4t4nod[r - l + 1];
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
            } else if (temp1[i - l].sum < temp1[j - l].sum||temp1[i - l].sum == temp1[j - l].sum&&temp1[i - l].pos > temp1[j - l].pos) {
                s1[k] = temp1[i - l];
                i++;
            } else {
                s1[k] = temp1[j - l];
                j++;
            }
        }
    }
}

class l4t4nod{
    int pos;
    long sum;
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