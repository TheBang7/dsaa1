import java.util.Scanner;

public class l0t4 {
    public static int[][] c = new int[5][30];
    public static void main(String[] args) {
        // write your code here
        Scanner read = new Scanner(System.in);
        int n = read.nextInt();
        for (int t = 1; t <= n; t++) {
            int shun = 0, tong = 0, queTuo = 0;
            String s = read.next();
            int l = s.length();
            boolean f = true;
            for (int i = 0; i < 5; i++)
                for (int j = 0; j < 30; j++) c[i][j] = 0;
            for (int i = 0; i < l; i += 2) {
                int a = s.charAt(i) - '0';
                if (s.charAt(i + 1) == 'w') c[1][a]++;
                else if (s.charAt(i + 1) == 's') c[2][a]++;
                else if (s.charAt(i + 1) == 'b') c[3][a]++;
                else if (s.charAt(i + 1) == 'z') c[4][a]++;
            }
            f=qiu(0,0,0);
            if (f) System.out.println("Blessing of Heaven");
            else System.out.println("Bad luck");


        }
    }
    public static boolean qiu(int shun, int tong, int queTuo) {
        if ((shun + tong == 4) && queTuo == 1) return true;
        boolean flag=false;
        for (int i = 1; i < 4; i++)
            for (int j = 1; j <= 7; j++) {
                if (c[i][j] > 0 && c[i][j + 1] > 0 && c[i][j + 2] > 0 && shun < 4) {
                    c[i][j]--;
                    c[i][j + 1]--;
                    c[i][j + 2]--;
                    if(qiu(shun + 1, tong, queTuo))flag=true;
                    c[i][j]++;
                    c[i][j + 1]++;
                    c[i][j + 2]++;
                }
            }

        for (int i = 1; i <= 4; i++)
            for (int j = 1; j <= 9; j++) {
                if (c[i][j] >= 3 && tong < 4) {
                    c[i][j] -= 3;
                    if(qiu(shun , tong+ 1, queTuo))flag=true;
                    c[i][j] += 3;
                }
            }
        for (int i = 1; i <= 4; i++)
            for (int j = 1; j <= 9; j++) {
                if (c[i][j] >= 2 && queTuo < 1) {
                    c[i][j] -= 2;
                    if(qiu(shun , tong, queTuo+ 1))flag=true;
                    c[i][j] += 2;
                }
            }
        return flag;
    }
}