import java.io.*;
import java.util.*;

public class Main {
    static int[][] light;
    static ArrayList<edge>[] edges;
    static int vis[];
    static long dist[];
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        int m = in.nextInt();
        edges = new ArrayList[n];
        light = new int[n][2];
        vis = new int[n];
        dist = new long[n];
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int w = in.nextInt();
            if (edges[u] == null) {
                edges[u] = new ArrayList<>();
            }
            edge e = new edge();
            e.qian = u;
            e.hou = v;
            e.val = w;
            edges[u].add(e);
        }
        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            light[i][0] = a; // red
            light[i][1] = b; // green
        }
        for (int i = 0; i < n; i++) {
            dist[i] = Long.MAX_VALUE;
        }
        dist[0] = 0;
        // vis[0]=1;
        PriorityQueue<edge> q = new PriorityQueue<>(new Comparator<edge>() {
            @Override
            public int compare(edge o1, edge o2) {
                if (o1.actDist < o2.actDist)
                    return -1;
                else if (o1.actDist > o2.actDist)
                    return 1;
                else
                    return 0;
            }
        });
        edge head = new edge();
        head.hou = 0;
        head.val = 0;
        q.offer(head);
        while (!q.isEmpty()) {
            edge cur = q.poll();
            int next = cur.hou;
            if (vis[next] == 1)
                continue;
            vis[next] = 1;
            if (edges[next] == null)
                continue;
            int size = edges[next].size();
            for (int i = 0; i < size; i++) {
                edge e = edges[next].get(i);
                e.setActDist();
                if (vis[e.hou] == 0 && dist[e.hou] > e.actDist) {
                    dist[e.hou] = e.actDist;
                    q.offer(e);
                }
            }
        }
        out.print(dist[n - 1]);
        out.close();
    }

    static class edge {
        int qian;
        int hou;
        long val;
        long actDist;
        public edge() {
            qian = -1;
            hou = -1;
            val = -1;
            actDist = -1;
        }
        public void setActDist() {
            long d = dist[qian] + val;
            long t0 = d % (light[hou][0] + light[hou][1]);
            if (t0 < light[hou][0]) {
                actDist = light[hou][0] - t0 + d;
            } else
                actDist = d;
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