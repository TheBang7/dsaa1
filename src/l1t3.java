import java.util.Scanner;

public class l1t3 {
    public static void main(String[] args) {
        int n, s, ans = 0,t;
        Scanner input = new Scanner(System.in);
        s = input.nextInt();
        n = input.nextInt();
        int[] a = new int[n];
        int maxx=0;
        for (int i = 0; i < n; i++) {a[i] = input.nextInt();maxx=Math.max(maxx,4*a[i]*a[i]);}
        double l=0,r=maxx,m;
        while (l <= r) {
            m = (l + r) / 2;
            t=0;
            for(int i=0;i<n;i++)t+=Math.floor((Math.PI*(double) a[i]*(double) a[i])/m);
            if (t < s) r = m - 0.000001;
            else l = m + 0.000001;
        }
        System.out.println(l-0.000001);
    }
}
