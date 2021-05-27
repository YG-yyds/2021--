import java.io.*;
import java.util.*;

public class Main {
    static long Max = 0;
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        nd[] nds = new nd[n];
        int root = 0;
        for (int i = 0; i < n; i++) {
            int w = in.nextInt();
            nds[i] = new nd(w);
        }
        for (int i = 0; i < n - 1; i++) {
            int i1 = in.nextInt() - 1;
            int i2 = in.nextInt() - 1;
            if (root == i2) {
                root = i1;
            }
            nds[i1].child.add(nds[i2]);
            nds[i2].child.add(nds[i1]);
        }
        dfs(nds[root], null, 0);
        for (int i = 0; i < n; i++) {
            nds[root].beauty += nds[i].w * nds[i].height;
        }
        dfs2(nds[root], nds[root], null);
        /*for (int i=0;i<n;i++){
            out.print("height: "+nds[i].height+" curSum: "+nds[i].curSum+" beauty: "+nds[i].beauty+"\n");
        }*/
        out.print(Max);
        out.close();
    }
    public static void dfs(nd nd, nd fa, int height) {
        if (nd == null)
            return;
        nd.height = height;
        nd.curSum += nd.w;
        int size = nd.child.size();
        for (int i = 0; i < size; i++) {
            nd c = nd.child.get(i);
            if (c != fa) {
                dfs(c, nd, height + 1);
            }
        }
        for (int i = 0; i < size; i++) {
            if (nd.child.get(i) != null && nd.child.get(i) != fa) {
                nd.curSum += nd.child.get(i).curSum;
            }
        }
    }
    public static void dfs2(nd root, nd nd, nd fa) {
        if (nd == null)
            return;
        if (nd != root) {
            nd.beauty = fa.beauty + root.curSum - 2 * nd.curSum;
            if (nd.beauty > Max) {
                Max = nd.beauty;
            }
        }
        int size = nd.child.size();
        for (int i = 0; i < size; i++) {
            nd c = nd.child.get(i);
            if (c != fa) {
                dfs2(root, c, nd);
            }
        }
    }
    static class nd {
        int w;
        ArrayList<nd> child;
        int height = 0;
        long curSum = 0;
        long beauty = 0;
        public nd(int w) {
            this.w = w;
            child = new ArrayList<>();
        }
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }
}