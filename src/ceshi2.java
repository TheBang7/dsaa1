import java.io.*;
import java.util.Random;
public class ceshi2 {
    public static void main(String[] args) throws IOException {
        Random random = new Random();
        PrintWriter out = new PrintWriter("in.txt");
        int n = random.nextInt(20) + 2;
        out.println(n);
        nodeL6t3[] nodes = new nodeL6t3[n + 2];
        for (int i = 1; i <= n; i++) nodes[i] = new nodeL6t3();
        for (int i = 0; i < n - 1; i++) {
            int m = random.nextInt(i + 1) + 1;
            out.printf("%d %d\n",m,i+2);
            nodes[m].son.add(nodes[i + 2]);
            nodes[i + 2].son.add(nodes[m]);
        }
        int k = random.nextInt(n) + 1, a, b, c;
        out.println(k);
        int l;
        nodes[1].v = true;
        nodes[1].depth = 0;
        int front = 0, rear = 1;
        nodeL6t3[] temp = new nodeL6t3[n + 1];
        temp[0] = nodes[1];
        while (front < rear) {
            l = temp[front].son.size();
            for (int i = 0; i < l; i++)
                if (!temp[front].son.get(i).v) {
                    temp[front].son.get(i).v = true;
                    temp[front].son.get(i).depth = temp[front].depth + 1;
                    temp[front].son.get(i).f = temp[front];
                    temp[rear++] = temp[front].son.get(i);
                }
            front++;
        }
        dfs(nodes[1]);
        for (int i = 0; i < k; i++) {
            a = random.nextInt(n) + 1;
            b = nodes[a].son.get(random.nextInt(nodes[a].son.size())).id;
            c = random.nextInt(nodes[a].sumOfNodes / 2 + 1) + 1;
            out.printf("%d %d %d\n", a, b, c);
        }
        out.close();
    }


    public static void dfs(nodeL6t3 node) {
        int l = node.son.size();
        nodeL6t3 son;
        for (int i = 0; i < l; i++) {
            son = node.son.get(i);
            if (son.depth > node.depth) {
                son.v = true;
                dfs(son);
                node.sumOfNodes += son.sumOfNodes;
            }
        }
    }
}