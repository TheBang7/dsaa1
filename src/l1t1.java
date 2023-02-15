import java.util.Arrays;
import java.util.Scanner;

public class l1t1 {
    public static void main(String[] args) {
        int n, q,t;
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        q = input.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = input.nextInt();
//        for(int i=0;i<n;i++)
//            for(int j=n-1;j>i;j--)
//                if(a[j]<a[j-1]){
//                     t=a[j];
//                     a[j]=a[j-1];
//                     a[j-1]=t;
//                }
        int x, y, l, r, m, b,c;
        for (int k = 1; k <= q; k++) {
            x = input.nextInt();
            y = input.nextInt();
            l = 0;
            r = n - 1;
            while (l <= r) {
                m = (l + r) / 2;
                if (x < a[m]) r = m - 1;
                else l = m + 1;
            }
            //System.out.println(r);
            b = r + 1;
            l = 0;
            r = n - 1;
            while (l <= r) {
                m = (l + r) / 2;
                if (y <= a[m]) r = m - 1;
                else l = m + 1;
            }
            c=r;
            //while (a[b] < y && b < n) b++;
           // System.out.printf("%d %d %d %d\n", n, l, b,c);
            if(c-b+1>0)System.out.printf("YES %d\n", c-b+1);
            else System.out.print("NO\n");
        }
    }
}