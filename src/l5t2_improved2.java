import java.util.Scanner;

public class l5t2_improved2 {
    public static l5t2node[]pam1,pam2;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s1, s2;
        s1 = in.next();
        s2 = in.next();
        int l1 = s1.length();
        int l2 = s2.length();
        pam1 = build(s1);
        pam2 = build(s2);
        dfs(1,1,-1);
        dfs(2,2,0);
        if(ans>0)System.out.println(ans);
        else System.out.println(-1);
    }
    public static int ans=0;
    public static void dfs(int x,int y,int deep){
        ans=Math.max(ans,deep);
        for(int i=0;i<26;i++)
            if(pam1[x].ch[i]>1&&pam2[y].ch[i]>1)
                dfs(pam1[x].ch[i],pam2[y].ch[i],deep+2);
    }

    public static l5t2node[] build(String s) {
        int l = s.length();
        l5t2node[] pam = new l5t2node[600000];
        pam[1] = new l5t2node();
        pam[2] = new l5t2node();
        pam[1].size = -1;
        pam[2].fail = 1;
        int x, p = 2, count = 2;
        for (int i = 0; i <l; i++) {
            x = s.charAt(i) - 'a';
            while (i - pam[p].size - 1<0||s.charAt(i) != s.charAt(i - pam[p].size - 1)) p = pam[p].fail;
            if (pam[p].ch[x] > 1) p = pam[p].ch[x];//如果这个结点已经存在那就不需要扩展了
            else {//不存在则新建一个结点
                int np = ++count;
                pam[np] = new l5t2node();
                pam[p].ch[x] = np;
                pam[np].size = pam[p].size + 2;//长度是原结点+2
                if (p == 1) pam[np].fail = 2;//这样偶数空和奇数空都会判断
                else {
                    for (p = pam[p].fail; s.charAt(i - pam[p].size - 1) != s.charAt(i); ) p = pam[p].fail;
                    pam[np].fail = pam[p].ch[x];
                }//fail指针指向他父亲结点的fail指针的ch[x]，和AC自动机相同
                p = np;
            }
        }
        return pam;
    }
}

class l5t2node {
    int size, fail;
    int[] ch;

    public l5t2node() {
        ch = new int[26];
        for (int i = 0; i < 26; i++) ch[i] = 1;//所有的失败指针先都指向奇数空
        size = 0;
        fail=1;
    }
}
