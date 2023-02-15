import java.util.Scanner;

public class ceshi {
    static int top = 0;
    static boolean ifreach = false;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] queue = new int[n];
        boolean[] isvisit = new boolean[n];
        int[][] map = new int[n][n];
        for (int i = 0; i < n-1; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            map[x][y] = 1;
            map[y][x] = 1;
        }
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            top = 0;
            ifreach = false;
            for (int j = 0; j < n; j++) {
                isvisit[j] = false;
            }
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
            dfs(a,b,c,map,queue,isvisit);

            if (ifreach) System.out.println("Yes");
            else System.out.println("No");
        }
    }
    public static  void dfs(int pos,int b,int c, int[][] map,int[] queue,boolean[] isvisit){
        if (pos==b){
            for (int i = 0; i < top; i++) {
                if (queue[i]==c) ifreach = true;
            }
            return;
        }
        isvisit[pos] = true;
        queue[top++] = pos;
        for (int i = 0; i < map.length; i++) {
            if (map[pos][i]==1&&isvisit[i]==false) {
                dfs(i,b,c,map,queue,isvisit);
            }
        }
        isvisit[pos] = false;
        top--;
    }
}
