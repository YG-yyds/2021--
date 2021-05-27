import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        int m = in.nextInt();
        nd[] arr = new nd[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new nd(i + 1);
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            arr[b].ru(arr[a]);
            arr[a].chu(arr[b]);
        }
        PriorityQueue<nd> q = new PriorityQueue<>(new Comparator<nd>() {
            @Override
            public int compare(nd o1, nd o2) {
                if (o1.val < o2.val)
                    return -1;
                else if (o1.val > o2.val)
                    return 1;
                else
                    return 0;
            }
        });
        for (int i = 0; i < n; i++) {
            if (arr[i].ruDu == 0) {
                q.offer(arr[i]);
            }
        }
        while (!q.isEmpty()) {
            nd nd = q.poll();
            out.print(nd.val + " ");
            if (nd.chu != null) {
                int size = nd.chu.size();
                for (int i = 0; i < size; i++) {
                    nd chu = nd.chu.get(i);
                    chu.ruDu--;
                    if (chu.ruDu == 0) {
                        q.offer(chu);
                    }
                }
            }
        }
        out.close();
    }
    static class nd {
        int ruDu = 0;
        int val;
        ArrayList<nd> ru;
        ArrayList<nd> chu;
        public nd(int val) {
            this.val = val;
        }
        public void ru(nd r) {
            if (ru == null) {
                ru = new ArrayList<>();
                ru.add(r);
                ruDu++;
            } else {
                ru.add(r);
                ruDu++;
            }
        }
        public void chu(nd c) {
            if (chu == null) {
                chu = new ArrayList<>();
                chu.add(c);
            } else {
                chu.add(c);
            }
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