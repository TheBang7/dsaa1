import java.io.*;
import java.util.StringTokenizer;

public class l4t1 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        String s=in.nextLine();
        tj t=solve(s,0);
        int ans=t.val%514329;
        int l1=s.length();
        int l2=t.pos+1;
        while(l2<l1){
            t=solve(s,l2);
            l2=t.pos+1;
            ans=(ans+t.val)%514329;
        }
        out.println(ans);
        out.close();
    }
    public static tj solve(String s,int begin){
        tj temp=new tj();
        temp.val=0;
        tj temp2;
        if(s.charAt(begin)=='('&&s.charAt(begin+1)==')') {
            temp.val=1;
            temp.pos=begin+1;
            return temp;
        }
        else{
            begin++;
            while(s.charAt(begin)!=')'){
                if(s.charAt(begin)=='('){
                    temp2=solve(s,begin);
                    temp.val+=temp2.val;
                    temp.val%=514329;
                    begin= temp2.pos+1;
                }
            }
            temp.val*=2;
            temp.val%=514329;
            temp.pos=begin;
        }
        return temp;
    }
}
class tj{
    int val,pos;
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