import java.io.*;
import java.util.Objects;
import java.util.StringTokenizer;

public class l4t2 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n= in.nextInt(),t;
        String s;
        nodQ begin=new nodQ(-1);
        nodQ end=begin;
        for(int nn=1;nn<=n;nn++){
            s = in.nextLine();
            String[] temp=s.split(" ");
            if(Objects.equals(temp[0], "E")){
                end.add(new nodQ(Integer.parseInt(temp[1])));
                end=end.next;
            }
            else{
                t=Integer.parseInt(temp[1]);
                for(int i=1;i<=t;i++)
                    begin.next=begin.next.next;
                out.println(begin.next.val);
            }
        }
        out.close();
    }
}
class nodQ{
    nodQ next;
    int val;
    public nodQ(int val){
        this.val=val;
    }
    public void add(nodQ x){
        x.next=next;
        next=x;
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