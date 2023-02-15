import java.io.*;
import java.util.Objects;
import java.util.StringTokenizer;

public class l7t4 {
    public static int n, m;

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        n = in.nextInt();
        m = in.nextInt();
        int a;
        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = in.nextInt();
            //tree.insert(new nodeL7t4(Math.min(n, Math.max(tree.lowerBound, a + tree.lowerBound)), Math.max(tree.lowerBound, Math.min(tree.upperBound, a + tree.upperBound)), a), i + 1);
        }
        AVLTree tree = new AVLTree(0, n, arr, 0, m - 1);
        int t = in.nextInt(), b;
        String s;
        commandL7t4 command;
        for (int i = 0; i < t; i++) {
            s = in.next();
            a = in.nextInt();
            if (Objects.equals(s, "ask")) {
                command = tree.rootNode.command;
                out.println(Math.min(command.upperBound, Math.max(command.lowerBound, a + command.move)));
            } else if (Objects.equals(s, "rem")) {
                tree.remove(a);
            } else {
                b = in.nextInt();
                tree.insert(new nodeL7t4(Math.min(n, Math.max(tree.lowerBound, b + tree.lowerBound)), Math.max(tree.lowerBound, Math.min(tree.upperBound, b + tree.upperBound)), b), a);
            }
        }
        out.close();

    }
}

class AVLTree {
    nodeL7t4 rootNode;
    int upperBound;
    int lowerBound;

    public AVLTree(int lowerBound, int upperBound, int[] arr, int l, int r) {
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
        if (r < l) {
            rootNode = new nodeL7t4(lowerBound, upperBound, 0);
            rootNode.size = 0;
            rootNode.h = 0;
            rootNode.l = new nodeL7t4(lowerBound, upperBound, 0);
            rootNode.l.f = rootNode;
            rootNode.l.size = 0;
            rootNode.l.h = 0;
            rootNode.l.isEmpty = true;
            rootNode.r = new nodeL7t4(lowerBound, upperBound, 0);
            rootNode.r.f = rootNode;
            rootNode.r.size = 0;
            rootNode.r.h = 0;
            rootNode.r.isEmpty = true;//给根左右两边先塞上空的
        } else if (r == l) {
            rootNode = new nodeL7t4(lowerBound, upperBound, 0);
            rootNode.size = 0;
            rootNode.h = 0;
            rootNode.l = new nodeL7t4(lowerBound, upperBound, 0);
            rootNode.l.f = rootNode;
            rootNode.l.size = 0;
            rootNode.l.h = 0;
            rootNode.l.isEmpty = true;
            rootNode.r = build(arr, l, r);
            rootNode.r.f = rootNode;
            reset(rootNode);//给根左右两边先塞上空的
        } else rootNode = build(arr, l, r);
    }

    public nodeL7t4 build(int[] arr, int l, int r) {
        if (l == r)
            return new nodeL7t4(Math.min(upperBound, Math.max(lowerBound, arr[l] + lowerBound)), Math.max(lowerBound, Math.min(upperBound, arr[l] + upperBound)), arr[l]);
        int m = (l + r) / 2;
        return new nodeL7t4(build(arr, l, m), build(arr, m + 1, r));
    }

    void insert(nodeL7t4 nodeR, int pos) {
        if (pos <= rootNode.size) {
            nodeL7t4 nodeL = findInsPos(rootNode, pos);
            nodeL7t4 f = nodeL.f;
            if (nodeL.isEmpty) if (f.l == nodeL) {
                f.l = nodeR;
                nodeR.f = f;
                reset(f);
            } else {
                f.r = nodeR;
                nodeR.f = f;
                reset(f);
            }
            else if (f.l == nodeL) {
                f.l = new nodeL7t4(nodeR, nodeL);
                f.l.f = f;
                reset(f);
            } else {
                f.r = new nodeL7t4(nodeR, nodeL);
                f.r.f = f;
                reset(f);
            }//等插入全部做完之后，维护其为平衡二叉树
            balance(f);
        } else {
            nodeL7t4 nodeL = findInsPos(rootNode, pos - 1);
            nodeL7t4 f = nodeL.f;
            if (nodeL.isEmpty) if (f.l == nodeL) {
                f.l = nodeR;
                nodeR.f = f;
                reset(f);
            } else {
                f.r = nodeR;
                nodeR.f = f;
                reset(f);
            }
            else if (f.l == nodeL) {
                f.l = new nodeL7t4(nodeL, nodeR);
                f.l.f = f;
                reset(f);
            } else {
                f.r = new nodeL7t4(nodeL, nodeR);
                f.r.f = f;
                reset(f);
            }//等插入全部做完之后，维护其为平衡二叉树
            balance(f);
        }
    }

    public void balance(nodeL7t4 f) {
        while (f != rootNode && f.lh - f.rh <= 1 && f.rh - f.lh <= 1) f = f.f;
        if (f.lh - f.rh > 1) {
            if (f.l.h >= f.r.h) turnRight(f);
            else {
                turnLeft(f.l);
                turnRight(f);
            }
        } else if (f.rh - f.lh > 1) {
            if (f.l.h <= f.r.h) turnLeft(f);
            else {
                turnRight(f.r);
                turnLeft(f);
            }
        }
    }

    nodeL7t4 findInsPos(nodeL7t4 node, int pos) {
        if (node.l == null && node.r == null) return node;
        int sizeL = 0, sizeR = 0;
        if (node.l != null) sizeL = node.l.size;
        if (node.r != null) sizeR = node.r.size;
        if (pos <= sizeL && node.l != null) {
            return findInsPos(node.l, pos);
        } else {
            assert node.r != null;
            return findInsPos(node.r, pos - sizeL);
        }
    }

    public void remove(int pos) {
        nodeL7t4 node = findMovePos(rootNode, pos);
        if (node.f == rootNode) {
            node.isEmpty = true;
            node.size = 0;
            node.h = 0;
            node.command.lowerBound = lowerBound;
            node.command.upperBound = upperBound;
            node.command.move = 0;//移除就是把一个点变空
            reset(node.f);
            balance(node.f);
        } else {
            nodeL7t4 f = node.f;
            nodeL7t4 ff = f.f;
            if (f == ff.l) {
                if (node == f.l) {
                    f.r.f = ff;
                    ff.l = f.r;
                } else {
                    f.l.f = ff;
                    ff.l = f.l;
                }
            } else {
                if (node == f.l) {
                    f.r.f = ff;
                    ff.r = f.r;
                } else {
                    f.l.f = ff;
                    ff.r = f.l;
                }
            }
            reset(ff);
            balance(ff);
        }
    }

    nodeL7t4 findMovePos(nodeL7t4 node, int pos) {
        if (node.l == null && node.r == null) return node;
        int sizeL = 0, sizeR = 0;
        if (node.l != null) sizeL = node.l.size;
        if (node.r != null) sizeR = node.r.size;
        if (pos > sizeL) {
            assert node.r != null;
            return findMovePos(node.r, pos - sizeL);
        } else {
            assert node.l != null;
            return findMovePos(node.l, pos);
        }
    }

    void turnLeft(nodeL7t4 node) {//对node做根的部分左旋
        nodeL7t4 f, r;
        if (node != rootNode) {
            f = node.f;
            r = node.r;
            if (r.l != null) r.l.f = node;
            node.r = r.l;//右结点的左结点接到原来根的右边
            r.l = node;
            r.f = node.f;
            node.f = r;//根接到原来右结点的左边
            if (f.r == null || f.l == node) f.l = r;
            else f.r = r;
            reset(node);
        } else {
            r = node.r;
            if (r.l != null) r.l.f = node;
            node.r = r.l;//右结点的左结点接到原来根的右边
            r.l = node;
            node.f = r;//根接到原来右结点的左边
            rootNode = r;
            reset(node);
        }
    }

    void turnRight(nodeL7t4 node) {//对node做根的部分右旋
        nodeL7t4 f, l;
        if (node != rootNode) {
            f = node.f;
            l = node.l;
            if (l.r != null) l.r.f = node;
            node.l = l.r;//左结点的右结点接到原来根的左边
            l.r = node;
            l.f = node.f;
            node.f = l;//根接到原来左结点的右边
            if (f.l == null || f.r == node) f.r = l;
            else f.l = l;
            reset(node);
        } else {
            l = node.l;
            if (l.r != null) l.r.f = node;
            node.l = l.r;//左结点的右结点接到原来根的左边
            l.r = node;
            node.f = l;//根接到原来左结点的右边
            rootNode = l;
            reset(node);
        }
    }

    void reset(nodeL7t4 node) {
        node.reSet();
        if (node != rootNode) reset(node.f);
    }


}

class nodeL7t4 {
    int size;
    int h, lh, rh;
    nodeL7t4 l, r, f;
    commandL7t4 command;
    boolean isEmpty;

    public nodeL7t4(int lowerBound, int upperBound, int move) {
        this.command = new commandL7t4(lowerBound, upperBound, move);
        h = 1;
        size = 1;
        isEmpty = false;
    }

    public nodeL7t4(nodeL7t4 n1, nodeL7t4 n2) {
        this.l = n1;
        this.r = n2;
        this.lh = n1.h;
        this.rh = n2.h;
        this.h = Math.max(n1.h, n2.h) + 1;
        n1.f = this;
        n2.f = this;
        this.size = n1.size + n2.size;
        this.command = new commandL7t4(n1.command, n2.command);
        isEmpty = false;
    }

    void reSet() {//这里先插个眼，因为在我的设想里，删除是给一个结点搞成幺元结点而不是删指针，所以任何一个当爹的都有俩儿子
        this.size = l.size + r.size;
        this.lh = l.h;
        this.rh = r.h;
        this.h = Math.max(l.h, r.h) + 1;
        this.command.reSet(l.command, r.command);
    }

}

class commandL7t4 {
    int lowerBound;
    int upperBound;
    int move;
    boolean isBasic;

    public commandL7t4(int lowerBound, int upperBound, int move) {
        this.move = move;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        isBasic = true;
    }

    public commandL7t4(commandL7t4 c1, commandL7t4 c2) {
        this.move = c1.move + c2.move;
        this.lowerBound = Math.min(c2.upperBound, Math.max(c2.lowerBound, c1.lowerBound + c2.move));
        this.upperBound = Math.max(c2.lowerBound, Math.min(c2.upperBound, c1.upperBound + c2.move));
        this.isBasic = false;
    }

    void reSet(commandL7t4 c1, commandL7t4 c2) {
        this.move = c1.move + c2.move;
        this.lowerBound = Math.min(c2.upperBound, Math.max(c2.lowerBound, c1.lowerBound + c2.move));
        this.upperBound = Math.max(c2.lowerBound, Math.min(c2.upperBound, c1.upperBound + c2.move));
        this.isBasic = false;
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